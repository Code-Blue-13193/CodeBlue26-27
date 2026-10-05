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
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                follower.pose().heading()
        );
        {
            powers = ManualDrive.fieldCentric(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    gamepad1.right_stick_x,
                    follower.pose().heading()
            );

            follower.manual(powers);
            Pose robotPose = follower.pose(); // returns a Pose object

            telemetry.addData("Robot X", robotPose.x());
            telemetry.addData("Robot Y", robotPose.y());
            telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
            // Math.toDegrees() is a built-in java method
        }

        ManualDrive.driveOrHold(follower, powers);
        follower.update();
        telemetry.update();
    }

    public void checkInput() {
        if (gamepad1.aWasPressed()) {
            telemetry.addLine("A pressed");
            follower.update();
        }
    }
}

