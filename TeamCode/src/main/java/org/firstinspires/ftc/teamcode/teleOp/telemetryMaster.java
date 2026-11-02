package org.firstinspires.ftc.teamcode.teleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class telemetryMaster {
    public ArrayList<telemetryStruct> telemetryStructs = new ArrayList<telemetryStruct>();

    public void Update(Telemetry telemetry) {
        if (telemetryStructs.isEmpty()) { return;}
        for (telemetryStruct call : telemetryStructs) {
            if (call.timer < 0) {
                telemetryStructs.remove(call);
                continue;
            }
            call.timer-=1;
            telemetry.addLine(call.data);
        }
    }
    public void AddData(String label, String data, int time) {
        telemetryStruct call = new telemetryStruct(time, label+":"+data);
        telemetryStructs.add(call);
    }
}
