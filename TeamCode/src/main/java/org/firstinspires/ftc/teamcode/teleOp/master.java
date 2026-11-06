package org.firstinspires.ftc.teamcode.teleOp;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "Master")
public class master extends OpMode {
    public double speed = 0.8;
    double minSpeed = 0.6;
    double maxSpeed = 1.2;
    private Follower follower;

    poseMaster poseMaster;

    telemetryMaster telemetryMaster;

    DcMotor intake;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);

        poseMaster = new poseMaster();

        telemetryMaster = new telemetryMaster();

        intake = hardwareMap.get(DcMotor.class, "intake");
    }

    @Override
    public void start(){
        follower.setPose(OpModeStorage.autonomousEndPose);
        follower.update();
    }

    @Override
    public void loop()
    {
        checkInput();
        DrivePowers powers = ManualDrive.fieldCentric(
                gamepad1.left_stick_y*speed,
                gamepad1.left_stick_x*speed,
                -gamepad1.right_stick_x*speed,
                follower.pose().heading()
        );

            follower.manual(powers);
            Pose robotPose = follower.pose(); // returns a Pose object

        telemetryMaster.AddData("Robot X", Double.toString(robotPose.x()),0);
        telemetryMaster.AddData("Robot Y", Double.toString(robotPose.y()),0);
        telemetryMaster.AddData("Robot Heading", Double.toString(Math.toDegrees(robotPose.heading())),0);
            // Math.toDegrees() is a built-in java method

        ManualDrive.driveOrHold(follower, powers);
        follower.update();
        telemetryMaster.Update(telemetry);
        telemetry.update();
    }

    public void checkInput() {
        if (gamepad1.aWasPressed()) {
            telemetryMaster.AddData("Input", "A", 30);
            follower.update();
        }

        if (gamepad1.dpadUpWasPressed()) {
            speed += 0.2;
            speed = Math.min(speed, maxSpeed);
            telemetryMaster.AddData("Increasing Speed", Double.toString(speed), 30);
            follower.update();
        }

        if (gamepad1.dpadDownWasPressed()) {
            speed -= 0.2;
            speed = Math.max(speed, minSpeed);
            telemetryMaster.AddData("Decreasing Speed", Double.toString(speed), 30);
            follower.update();
        }

        if (gamepad1.leftBumperWasPressed()) {
            follower.setPose(poseMaster.blueLineZone);
            telemetryMaster.AddData("Position", "Recalibrated", 30);
            follower.update();
        }

        intake.setPower(gamepad1.right_trigger_pressed?0.5:0);

        intake.setPower(gamepad1.left_trigger_pressed?-0.5:0);
    }
}

