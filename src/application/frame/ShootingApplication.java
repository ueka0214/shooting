package application.frame;

import application.module.SceneID;
import application.scene.ExplanationScene;
import application.scene.MainScene;
import application.scene.SceneManager;
import application.scene.TitleScene;
import application.world.World;
import application.world.manager.ImageManager;

public class ShootingApplication extends GameApplication {

	private World world = null;
	private SceneManager sceneManager = null;

	public ShootingApplication(String title, int width, int height) {
		super(title,width,height);
		// 入力クラスの登録
		addKeyListener(this.input);
		// 世界のインスタンス化
		world = new World(this);
		// シーンマネージャのインスタンス化
		sceneManager = new SceneManager();
	}



	@Override
	public void initialize() {
		world.initialize();// 世界の初期化
		ImageManager.initialize();// 画像マネージャー初期化
		sceneManager.initialize();// シーンマネージャの初期化
		sceneManager.add(SceneID.TITLE, new TitleScene(world, input,this));// タイトルシーンの追加
		sceneManager.add(SceneID.EXPLANATION, new ExplanationScene(world, input,this));// 説明シーンの追加
		sceneManager.add(SceneID.MAIN, new MainScene(world, input,this));// メインシーンの追加
		sceneManager.change(SceneID.TITLE);// タイトルシーンに設定
		add(sceneManager);// 描画コンポーネントにシーンマネージャを追加
		setVisible(true);// 描画の開始

	}

	@Override
	public void update() {
		sceneManager.update();
	}

}
