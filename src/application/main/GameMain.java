package application.main;


import application.frame.GameApplication;
import application.frame.ShootingApplication;

public class GameMain {
	public static int WIDTH = 800;
	public static int HEIGHT = 600;

	public static void main(String[] args) {
		GameApplication app = new ShootingApplication("Shooting", WIDTH, HEIGHT);
		app.startGameLoop();
	}

}
