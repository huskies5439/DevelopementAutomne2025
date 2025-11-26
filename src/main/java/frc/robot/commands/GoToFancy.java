package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.BasePilotable;

public class GoToFancy extends SequentialCommandGroup {

    public GoToFancy(BasePilotable basePilotable, Pose2d pose2d) {
        addCommands(new FollowPathDistance(basePilotable, pose2d), new DeplacementPID(basePilotable, pose2d));
    }
}
