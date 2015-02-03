package org.usfirst.frc.team5631.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot;
	Joystick stick;
	Gyro gyro;
	Encoder encoder;
	Double pos[] = { 0.0, 0.0 }; // [x, y]; start position is [0, 0]
	final double wheelCircumference = 1.00; // set wheel circumference here
	long startTime;

	public void robotInit() {
		myRobot = new RobotDrive(0, 1, 2, 3);
		myRobot.setExpiration(0.1);
		stick = new Joystick(0);

		encoder = new Encoder(1, 2, true, EncodingType.k4X);
		encoder.setDistancePerPulse(wheelCircumference / 360); // 360 pulses per
																// revolution

		gyro = new Gyro(1); // analog channel 1
		gyro.reset();
		// posUpdate();
	}

	/**
	 * Drive left & right motors for 2 seconds then stop
	 */
	public void autonomousInit() {
		startTime = System.currentTimeMillis();
	}

	public void autonomousPeriodic() {
		if (System.currentTimeMillis() > startTime + 10000) {
			myRobot.drive(0, 0);
			return;
		}
		myRobot.setSafetyEnabled(false);
		//goToGoal(10,0);
		myRobot.drive(-0.5, 0.5); // drive forwards half speed
		// Timer.delay(2.0); // for 2 seconds
		posUpdate();
		// myRobot.drive(0.0, 0.0); // stop robot
		// System.out.println("distance: " + encoder.getDistance());
	}

	/**
	 * Runs the motors with arcade steering.
	 */
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			myRobot.arcadeDrive(stick); // drive with arcade style (use right
										// stick)
			Timer.delay(0.005); // wait for a motor update time
		}
	}

	// method to update robot position
	public void posUpdate() {
		// myRobot.drive(1, 0);
		double dist, angle;
		// while (true) { // runs the entire duration of the program

		// Timer.delay(0.005); // update position every 5 milliseconds
		dist = Math.abs(encoder.getDistance());
		encoder.reset();
		angle = gyro.getAngle();

		pos[0] += dist * Math.sin(Math.toRadians(angle));
		pos[1] += dist * Math.cos(Math.toRadians(angle));

		System.out.println("Dist: " + dist + "\tAngle: " + angle + "\tPos: ["
				+ pos[0] + ", " + pos[1] + "]");
		// }
	}

	public void goToGoal(double x, double y) { // x,y are measured from start
												// position of robot
		// compute angle to goal
		double thetaToGoal = Math.toDegrees(Math.atan2(y - pos[1], x - pos[0]));
		System.out.println("thetaToGoal: " + thetaToGoal);
		double refAngle = thetaToGoal+90;
		//while (Math.abs(x - pos[0]) > Math.abs(0.01 * x)
		//		|| Math.abs(y - pos[1]) > Math.abs(0.01 * y)) {
		//	System.out.println("Runs");
			myRobot.drive(0.5, (gyro.getAngle() - refAngle) / 100.00);
		//}
		myRobot.drive(0, 0);
	}

	public void test() {
		goToGoal(6,0);
		// System.out.println("running test...");
		// goToGoal(5, 5);
		// System.out.println("Angle: " + gyro.getAngle());
		// myRobot.drive(-0.5, 1); // drive forwards half speed
		// Timer.delay(2.0); // for 2 seconds
		// myRobot.drive(0.0, 0.0); // stop robot
		// System.out.println("Angle: " + gyro.getAngle());
		// myRobot.drive(-0.5, 1); // drive forwards half speed
		// Timer.delay(2.0); // for 2 seconds
		// myRobot.drive(0.0, 0.0); // stop robot
		// System.out.println("Angle: " + gyro.getAngle());
		// myRobot.drive(-0.5, 1); // drive forwards half speed
		// Timer.delay(2.0); // for 2 seconds
		// myRobot.drive(0.0, 0.0); // stop robot
		// System.out.println("Angle: " + gyro.getAngle());
		// myRobot.drive(-0.5, 1); // drive forwards half speed
		// Timer.delay(2.0); // for 2 seconds
		// myRobot.drive(0.0, 0.0); // stop robot
		// System.out.println("Angle: " + gyro.getAngle());
		// myRobot.drive(-0.5, 1); // drive forwards half speed
		// Timer.delay(2.0); // for 2 seconds
		// myRobot.drive(0.0, 0.0); // stop robot

	}
}
