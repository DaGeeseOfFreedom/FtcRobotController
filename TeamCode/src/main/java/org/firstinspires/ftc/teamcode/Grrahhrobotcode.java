package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 * <p>
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Grrahh", group = "Iterative Op-mode")

public class Grrahhrobotcode extends OpMode {
    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;
    public static Servo intake;
    public static Servo arm;

    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        intake = hardwareMap.get(Servo.class, "intake");
        arm = hardwareMap.get(Servo.class, "arm");
    }

    public void loop() {
        double forwardSpeed = -gamepad1.left_stick_y;
        double sideSpeed = gamepad1.left_stick_x;
        double backRight_sideSpeed = -sideSpeed;
        double backLeft_sideSpeed = sideSpeed;
        double frontRight_sideSpeed = sideSpeed;
        double frontLeft_sideSpeed = -sideSpeed;
        double backRight_forwardSpeed = -forwardSpeed;
        double backLeft_forwardSpeed = -forwardSpeed;
        double frontRight_forwardSpeed = -forwardSpeed;
        double frontLeft_forwardSpeed = -forwardSpeed;
        double turnSpeed = gamepad1.right_stick_x;
        double frontRight_turnSpeed = turnSpeed;
        double frontLeft_turnSpeed = -turnSpeed;
        double backLeft_turnSpeed = -turnSpeed;
        double backRight_turnSpeed = turnSpeed;
        double numberOfinputs = 0;
        if (Math.abs(turnSpeed) > 0.02) {
            numberOfinputs += 0.5;
        }
        if (Math.abs(forwardSpeed) > 0.02) {

            numberOfinputs += 0.5;
        }
        if (Math.abs(sideSpeed) > 0.02) {
            numberOfinputs += 0.5;
        }
        double frontRightspeed = 0;
        double frontLeftspeed = 0;
        double backRightspeed = 0;
        double backLeftspeed = 0;
        if (numberOfinputs > 0) {
            frontRightspeed = -1 * (frontRight_forwardSpeed + frontRight_sideSpeed + frontRight_turnSpeed) / numberOfinputs;
            frontLeftspeed = -0.7 * (frontLeft_forwardSpeed + frontLeft_sideSpeed + frontLeft_turnSpeed) / numberOfinputs;
            backRightspeed = 1 * (backRight_forwardSpeed + backRight_sideSpeed + backRight_turnSpeed) / numberOfinputs;
            backLeftspeed = -1 * (backLeft_forwardSpeed + backLeft_sideSpeed + backLeft_turnSpeed) / numberOfinputs;
        }
        telemetry.addData("forwardSpeed", forwardSpeed);
        telemetry.addData("sideSpeed", sideSpeed);
        frontLeft.setPower(frontLeftspeed);
        frontRight.setPower(frontRightspeed);
        backLeft.setPower(backLeftspeed);
        backRight.setPower(backRightspeed);

        boolean intakeIn = gamepad1.x;
        boolean intakeOut = gamepad1.b;
        boolean armUp = gamepad1.y;
        boolean armDown = gamepad1.a;
        if (intakeIn) {
            intake.setPosition(1);
        } else if (intakeOut) {
            intake.setPosition(0);
        } else {
            intake.setPosition(1);
        }
        if (armUp) {
            arm.setPosition(0.7);
        } else if (armDown) {
           arm.setPosition(0);
        }
    }
}


