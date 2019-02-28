package application.scene;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.module.ImageID;
import application.module.Input;
import application.module.SceneID;
import application.world.World;
import application.world.manager.ImageManager;

public class ExplanationScene extends JPanel implements IScene {

	private World world;// 世界
	private Input input;// 入力
	private JFrame frame;// フレーム
	private boolean endFlg = false;
	private double frameTimer = 120;

	public ExplanationScene(World world, Input input,JFrame frame) {
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
		frameTimer--;
		// スペースキーを押したらシーン終了
		if (frameTimer<0&&input.isSpaceState()) {
			endFlg = true;
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(ImageManager.getImage(ImageID.SCENE_EXPLANATION),0,0, this);
		if (frameTimer<0) {
			g.drawImage(ImageManager.getImage(ImageID.IMAGE_PUSH_SPACE),550,450, this);
		}

	}

	@Override
	public boolean isEnd() {
		return endFlg;
	}

	@Override
	public SceneID next() {
		// 次はメインシーン
		return SceneID.MAIN;
	}

	@Override
	public void shutdown() {
		frame.remove(this);
	}

}
