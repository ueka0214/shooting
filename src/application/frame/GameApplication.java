package application.frame;

import javax.swing.JFrame;

import application.module.Input;

public abstract class GameApplication extends JFrame implements Runnable {

	private Thread th = null;
	private double sleepAddTime;
	static public final int FPS = 60;
	protected Input input;

	// ゲームウィンドウ
	public GameApplication(String title, int width, int height) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);// 閉じるボタンの処理
		setSize(width, height);//ウィンドウサイズ
		setLocationRelativeTo(null);//画面中央に配置
		setResizable(false);// リサイズ禁止
		setVisible(true);//ウィンドウ表示
		setFps(FPS);// FPSの設定
		// 入力クラスのインスタンス化
		input = new Input();
	}

	// ゲームループの開始
	public synchronized void startGameLoop() {
		if (th == null) {
			th = new Thread(this);
			th.start();
			initialize();// 初期化処理
		}
	}

	// ゲームループの終了
	public synchronized void stopGameLoop() {
		if (th != null) {
			th = null;
		}
	}

	public void run() {
		double nextTime = System.currentTimeMillis() + sleepAddTime;
		// ゲームループ
		while (th != null) {
			try {
				long res = (long) nextTime - System.currentTimeMillis();
				if (res < 0) {
					res = 0;
				}
				Thread.sleep(res);
				// 更新処理
				update();
				// 再描画処理
				repaint();
				nextTime += sleepAddTime;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ESCキーを押したら終了
			if (input.isEsc()) {
				System.exit(0);
			}
		}

	}

	// 初期化
	public abstract void initialize();

	// 更新
	public abstract void update();

	// fpsの設定
	public void setFps(int fps) {
		if (fps < 10 || fps > 60) {
			throw new IllegalArgumentException("FPSの設定は10～60で指定してください");
		}
		sleepAddTime = 1000.0 / fps;
	}

}
