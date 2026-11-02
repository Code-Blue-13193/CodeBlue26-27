package org.firstinspires.ftc.teamcode.teleOp;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;

public class poseMaster {
    private final PoseFactory p = PoseFactory.degrees();
    public final Pose loadingZone = p.of(132.3026, 42.6889, 270);
    public final Pose blueLineZone = p.of(130,130, 270);
}
