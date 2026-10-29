package org.firstinspires.ftc.teamcode;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.pedropathing.api.PoseFactory;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;

@TeleOp(name = "Master")
public class master extends OpMode {
    public double speed = 0.8;
    double minSpeed = 0.6;
    double maxSpeed = 1.2;
    private Follower follower; // you added this before

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
    }
    @Override
    public void loop()
    {
        checkInput();
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y*speed,
                -gamepad1.left_stick_x*speed,
                -gamepad1.right_stick_x*speed,
                follower.pose().heading()
        );

            follower.manual(powers);
            Pose robotPose = follower.pose(); // returns a Pose object

            telemetry.addData("Robot X", robotPose.x());
            telemetry.addData("Robot Y", robotPose.y());
            telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
            // Math.toDegrees() is a built-in java method

        ManualDrive.driveOrHold(follower, powers);
        follower.update();
        telemetry.update();
    }

    public void checkInput() {
        if (gamepad1.aWasPressed()) {
            telemetry.addLine("A pressed");
            follower.update();
        }

        if (gamepad1.dpadUpWasPressed()) {
            telemetry.addLine("Increasing Speed");
            speed += 0.2;
            speed = Math.min(speed, maxSpeed);
            follower.update();
        }

        if (gamepad1.dpadDownWasPressed()) {
            telemetry.addLine("Increasing Speed");
            speed -= 0.2;
            speed = Math.max(speed, minSpeed);
            follower.update();
        }
    }
}

