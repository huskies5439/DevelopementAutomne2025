// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
//Ceci n'est surtout pas un test pour le github!

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.BasePilotable;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GoToFancy extends SequentialCommandGroup {
  /** Creates a new GoToFancy. */
  public GoToFancy(BasePilotable basePilotable, Pose2d pose2d) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new ParallelRaceGroup(
            Commands.defer(() -> {
              return basePilotable.followPath(pose2d);
            }, getRequirements()),
            new WaitUntilCommand(() -> basePilotable.isProche(pose2d, 1.0))),
        new DeplacementPID(basePilotable, pose2d)

    );
  }
}
