package application.scene;

import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

import application.module.SceneID;

// シーン管理者
public class SceneManager extends JPanel {

	// シーンコンテナ
	private HashMap<SceneID,IScene> scenes = new HashMap<SceneID,IScene>();
	// 現在シーン
	private IScene currentScene = new SceneNull();

	// 初期化処理
	public void initialize() {
		// 現在シーンを初期化する
		shutdown();
		// シーンコンテナをクリアする
		scenes.clear();
	}

	// 更新処理
	public void update() {
		currentScene.update();// 現在シーンの更新処理
		// 現在シーンの終了フラグが立って
		if(currentScene.isEnd()) {
			change(currentScene.next());
		}
	}

	// 描画処理
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		currentScene.draw(g);
	}

	// シーンの追加
	public void add(SceneID id,IScene scene) {
		scenes.put(id, scene);
	}

	// シーンの変更
	public void change(SceneID id) {
		shutdown();// 現在シーンの終了
		currentScene = scenes.get(id);// シーンの切り替え
		currentScene.initialize();// 新しいシーンの初期化処理
	}

	// 現在シーンを終了し初期化する
	public void shutdown() {
		currentScene.shutdown();
		currentScene = new SceneNull();
	}

}
