// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BasePilotable;

public class BasePilotableDefaut extends Command {
  BasePilotable basePilotable;
  DoubleSupplier vx;
  DoubleSupplier vy;
  DoubleSupplier omega;

  double limiter;

  public BasePilotableDefaut(DoubleSupplier vx, DoubleSupplier vy, DoubleSupplier omega, BasePilotable basePilotable) {
    this.basePilotable = basePilotable;

    this.vx = vx;
    this.vy = vy;
    this.omega = omega;

    addRequirements(basePilotable);
  }

  @Override
  public void initialize() {
    // Il faut réinitialiser le Setpoint Generator avant de conduire, car sinon le
    // robot bouge après les pathfinding commands !
    basePilotable.resetSetpoint();
  }

  @Override
  public void execute() {
    basePilotable.conduire(vx.getAsDouble(), vy.getAsDouble(), omega.getAsDouble(), true, true);

  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
