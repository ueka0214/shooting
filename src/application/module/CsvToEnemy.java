package application.module;

// CSVからエネミー生成に必要な情報を取得する
public class CsvToEnemy {
	private CsvReader csv;
	// コンストラクタ
	public CsvToEnemy() {
		csv = new CsvReader("csv/enemy.csv");
	}
	// 指定行の座標を取得
	public Vector2 getPos(int row) {
		Vector2 result = new Vector2();
		result.x = csv.getd(row, 0);
		result.y = csv.getd(row, 1);
		return result;
	}
	// 指定行の移動量を取得
	public Vector2 getVelocity(int row) {
		Vector2 result = new Vector2();
		result.x = csv.getd(row, 2);
		result.y = csv.getd(row, 3);
		return result;
	}
	// 指定行の体力を取得
	public int getHelth(int row) {
		return csv.geti(row, 4);
	}
	// 指定行の弾種を取得
	public BulletID getBulletID(int row) {
		switch(csv.geti(row, 5)) {
		case 1:
			return BulletID.SHOT_NORMAL;
		case 2:
			return BulletID.SHOT_LEAD;
		case 3:
			return BulletID.SHOT_SPIRAL;
		case 4:
			return BulletID.SHOT_DISPERSION;
		}
		return BulletID.SHOT_NORMAL;
	}

}
