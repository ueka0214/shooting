package application.scene;

import java.awt.Graphics;

import application.module.SceneID;

// シーンインターフェース
public interface IScene {
	void initialize();// 初期化
	void update();// 更新
	void draw(Graphics g);// 描画
	boolean isEnd();// シーン終了か？
	SceneID next();// 次のシーン
	void shutdown();// 現在のシーンの終了

}
