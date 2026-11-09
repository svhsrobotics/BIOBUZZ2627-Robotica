package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class teleOp extends LinearOpMode {
    DcMotor shoot1;
    DcMotor shoot2;
    DcMotor frontLeft;
    DcMotor backLeft;
    DcMotor frontRight;
    DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        shoot1 = hardwareMap.get(DcMotor.class, "shoot1");
        shoot2 = hardwareMap.get(DcMotor.class, "shoot2");
        shoot2.setDirection(DcMotorSimple.Direction.REVERSE);
        Debouncer upbouncer = new Debouncer();
        Debouncer downbouncer = new Debouncer();
        double shootPower = 0;
        waitForStart();
        while(opModeIsActive()){
            if(upbouncer.update(gamepad1.dpad_up)) {
               shootPower += 0.01;
            }else if(downbouncer.update(gamepad1.dpad_down)){
                shootPower -= 0.01;
            }
            System.out.println("Shoot power: " + shootPower);
            shoot1.setPower(shootPower);
            shoot2.setPower(shootPower);

            //drivetrain
            double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            frontLeft.setPower(y + x + rx);
            backLeft.setPower(y - x + rx);
            frontRight.setPower(y - x - rx);
            backRight.setPower(y + x - rx);
            //


        }
    }
}
