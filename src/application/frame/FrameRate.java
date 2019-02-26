package application.frame;

public class FrameRate {
	private long before;// 前回測定時刻
	private int count;// 経過時刻
	private float frameRate;// フレームレート
	private final int updateTimeMillis;// 指定されたFPS（ミリ秒）

	// FPS測定用クラス
	public FrameRate(int updateTimeMillis) {
		this.updateTimeMillis = updateTimeMillis;
		before = System.currentTimeMillis();
	}

	public Boolean process() {
		// 現在時間を取得
		long now = System.currentTimeMillis();
		// 前回時間を取得してからの経過を増加
		count++;
		// 現在時刻-前回測定時刻が指定FPSを超えたか
		if(now - before >= updateTimeMillis) {
			// フレームレートの設定
			frameRate = (float)(count * 1000) / (float)(now - before);
			// 前回時刻と経過時刻の再設定
			before = now;
			count = 0;
			return true;

		}
		return false;
	}

	public float getFrameRate() {
		return frameRate;
	}



}
