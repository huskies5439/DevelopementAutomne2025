// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.Branche;
import frc.robot.commands.BasePilotableDefaut;
import frc.robot.commands.GoToFancy;
import frc.robot.subsystems.BasePilotable;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.FollowPathCommand;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

        // Créer les sous systèmes
        private final BasePilotable basePilotable = new BasePilotable();

        // Les deux manettes
        CommandXboxController manette = new CommandXboxController(0);
        CommandGenericHID operateur = new CommandGenericHID(1); // Voir le dossier manetteOperateur pour le code
                                                                // platformio

        // 3 triggers abitraires pour décaler le robot de gauche à droite sur la station
        Trigger stationGaucheTrigger = new Trigger(manette.leftTrigger());
        Trigger stationCentreTrigger = new Trigger(manette.leftTrigger().negate().and(manette.rightTrigger().negate()));
        Trigger stationDroiteTrigger = new Trigger(manette.rightTrigger());

        // Trigger pour se mettre automatiqument en mode gober si on est proche d'une
        // station

        /*
         * Comme il y a des tonnes de commandes associées à ces deux boutons,
         * nous avons fait un trigger arbitraire afin qu'il soit facile de modifer
         * le mapping de la manette si nécessaire
         */
        Trigger manetteA = manette.a();// Pour autoCorail
        Trigger manetteX = manette.x();// Pour autoAlgue

        private final SendableChooser<Command> autoChooser;

        public RobotContainer() {

                autoChooser = AutoBuilder.buildAutoChooser();
                SmartDashboard.putData("Auto Chooser", autoChooser);
                FollowPathCommand.warmupCommand().schedule(); // warm up la librairie pour éviter les temps d'attente
                configureButtonBindings();

                // Conduire avec joystick
                basePilotable.setDefaultCommand(new BasePilotableDefaut(manette::getLeftY,
                                manette::getLeftX, manette::getRightX, basePilotable));

        }

        private void configureButtonBindings() {
                manette.a().whileTrue(new GoToFancy(basePilotable,
                                Branche.A.plus(new Transform2d(0.0, 0.0, Rotation2d.kZero))));

                manette.b().onTrue(Commands.runOnce(basePilotable::resetGyro));
        }

        public Command getAutonomousCommand() {
                return autoChooser.getSelected();
        }

}
