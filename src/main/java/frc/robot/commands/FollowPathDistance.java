package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.BasePilotable;
import java.util.Set;

public class FollowPathDistance extends ParallelRaceGroup {
    public FollowPathDistance(BasePilotable basePilotable, Pose2d pose2d) {

        addRequirements(basePilotable);
        addCommands(
                Commands.defer(
                        () -> basePilotable.followPath(pose2d), Set.of()),
                new WaitUntilCommand(() -> basePilotable.isProche(pose2d, 1.0)));
    }
    /*
    * Les méthodes sont effectuées au démarrage du robot, le defer fait en sorte qu'il appèle la fonction quand il en a besoin.
    * Dans followPath, nous utilisons la pose actuelle du robot pour faire le path (sinon restait à 0,0).
    */
}
