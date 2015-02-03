package org.usfirst.frc.team5631.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class PositionUpdate extends Thread{
	Encoder encoder;
	Gyro gyro;
	Double[] pos;
	
	public PositionUpdate(Robot robot){
		encoder = robot.encoder;
		gyro = robot.gyro;
		pos = robot.pos;
	}
    
	
	
	public void run(){
    	//myRobot.drive(1, 0);
    	double dist, angle;
    	while(true){				//runs the entire duration of the program
    	encoder.reset();
    	Timer.delay(0.005);			//update position every 5 milliseconds
    	dist = Math.abs(encoder.getDistance());
    	angle = gyro.getAngle() + 90.00;
    	
    	pos[0] += dist * Math.cos(Math.toRadians(angle));
    	pos[1] += dist * Math.sin(Math.toRadians(angle));
    	
    	System.out.println("Dist: " + dist + "\tAngle: " + angle + "\tPos: [" + pos[0] + ", " + pos[1] + "]");
    	}
    	
    }
}