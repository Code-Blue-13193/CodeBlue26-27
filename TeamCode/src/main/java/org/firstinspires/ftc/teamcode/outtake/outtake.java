package org.firstinspires.ftc.teamcode.outtake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class outtake {
    private boolean shootActive = false;
    DcMotor shooty;

    public void init(HardwareMap hardwareMap) {
        shooty = hardwareMap.get(DcMotor.class, "shooty");
    }
    public void Shoot(HardwareMap hardwareMap) {
        shootActive =!shootActive;
        shooty.setPower(shootActive?0.5:0);
    }

}
