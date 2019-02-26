package application.module;

// 弾丸の移動ベクトルを生成し返すクラス
public class ShotPattern {
	Vector2 thisPosition;// 発射位置
	Vector2 targetPosition;// 目標位置
	double angle;// 発射角度

	// 更新処理
	public void update(Vector2 m, Vector2 t, double rotate) {
		thisPosition = m;// 発射位置の更新
		targetPosition = t;//目標位置の更新
		angle += rotate;// 発射角度の更新
	}

	public void update(Vector2 m, Vector2 t) {
		update(m, t, 0);
	}

	//	角度から移動量を返す
	public Vector2 fromDirection() {
		Vector2 result = Vector2.fromDirection(angle);
		return result.normalize();
	}
	public Vector2 fromDirection(double angle) {
		Vector2 result = Vector2.fromDirection(angle);
		return result.normalize();
	}

	// 自身からターゲットへ向かうベクトルを返す
	public Vector2 lead() {
		Vector2 result = targetPosition.sub(thisPosition);
		return result.normalize();
	}

}
