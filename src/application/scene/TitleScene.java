package application.scene;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.actor.Player;
import application.main.GameMain;
import application.module.Input;
import application.module.SceneID;
import application.module.Vector2;
import application.world.World;

public class TitleScene extends JPanel implements IScene {
	private World world;// 世界
	private Input input;// 入力
	private JFrame frame;// フレーム
	private boolean endFlg = false;

	public TitleScene(World world, Input input,JFrame frame) {
		this.world = world;
		this.input = input;
		this.frame = frame;

	}

	@Override
	public void initialize() {
		// プレイヤーの追加
		Player player = new Player(world, input, new Vector2(300, 500));
		world.addPlayer(player);
	}

	@Override
	public void update() {
		// スペースキーを押したらシーン終了
		if (input.isSpace()) {
			endFlg = true;
		}

	}

	@Override
	public void draw(Graphics g) {
		g.drawString("タイトルシーン", GameMain.WIDTH/2, GameMain.HEIGHT/2);
		g.drawString("push SpaceKey", GameMain.WIDTH/2, GameMain.HEIGHT/2+100);

	}

	@Override
	public boolean isEnd() {
		return endFlg;
	}

	@Override
	public SceneID next() {
		// 次はメインシーン
		return SceneID.Main;
	}

	@Override
	public void shutdown() {
		frame.remove(this);

	}

}
