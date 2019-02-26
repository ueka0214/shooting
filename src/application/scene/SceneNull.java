package application.scene;

import java.awt.Graphics;

import application.module.SceneID;

// 何もないシーン
public class SceneNull implements IScene {

	@Override
	public void initialize() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ

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
