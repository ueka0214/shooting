package application.scene;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import application.module.Input;
import application.module.SceneID;
import application.world.World;

public class MainScene implements IScene {

	private World world;
	private Input input;
	private JFrame frame;// フレーム
	private boolean endFlg = false;

	public MainScene(World world, Input input, JFrame frame) {
		this.world = world;
		this.input = input;
		this.frame = frame;
	}

	@Override
	public void initialize() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update() {
		world.update();

	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(600, 0, 200, 600);
		String score = "スコア :"+world.getScore();
		g.setColor(Color.WHITE);
		g.drawString(score, 600, 10);
		world.draw(g);

	}

	@Override
	public boolean isEnd() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public SceneID next() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void shutdown() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
