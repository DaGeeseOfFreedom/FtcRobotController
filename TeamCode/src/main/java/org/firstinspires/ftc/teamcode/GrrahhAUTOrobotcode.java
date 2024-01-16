package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


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

@Autonomous(name = "GrrahhAUTO", group = "Robot", preselectTeleOp="Grrahh")

public class
GrrahhAUTOrobotcode extends LinearOpMode {
    private DcMotor frontLeft = null;

    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;

    private Servo arm1;
    private Servo arm2;
    private Servo clawleft;

    public static Servo clawright;

    private ElapsedTime runtime = new ElapsedTime();
    public void driveStraight(double speed,float distance){
        frontRight.setPower(speed);
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
        sleep(Math.round((distance*1000)/10.25));
        // 10.25 - 41in/4sec - 10.25in/1sec
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "backLeft");
        backLeft = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        arm1 = hardwareMap.get(Servo.class, "arm1");
        arm2 = hardwareMap.get(Servo.class, "arm2");
        clawleft = hardwareMap.get(Servo.class, "claw1");
        clawright = hardwareMap.get(Servo.class, "claw2");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        if (opModeIsActive()) {
            clawleft.setPosition(0.1);
            clawright.setPosition(0.8);
            double armPositionUp = 0.13;
            //0.13 was armPositionUp originally
            arm1.setPosition(armPositionUp);
            arm2.setPosition(1 - armPositionUp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                telemetry.addData("%s", e.toString());

            }
            driveStraight( -0.5,40);
            //og is 48 not 46
        }

    }
}