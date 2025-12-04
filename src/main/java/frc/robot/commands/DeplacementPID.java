// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BasePilotable;


public class DeplacementPID extends Command {
  private BasePilotable basePilotable; 
  private Pose2d pose2d; 
  public DeplacementPID(BasePilotable basePilotable,Pose2d pose2d) {
    this.basePilotable = basePilotable; 
    this.pose2d = pose2d; 
    addRequirements(basePilotable);
  }

  @Override
  public void initialize() {
      //On as rien a faire ici
  }

  @Override
  public void execute() {
    basePilotable.setPID(pose2d);

  }

  @Override
  public void end(boolean interrupted) {
    basePilotable.setX();
  }

  @Override
  public boolean isFinished() {
    return basePilotable.isProche(pose2d, 0.05) && isAngleProche(5.0); 
  }

  private boolean isAngleProche(double tolerance) {
    return Math.abs(basePilotable.getAngle()-pose2d.getRotation().getDegrees()) <= tolerance;
  }
}
