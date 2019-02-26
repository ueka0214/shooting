package application.actor;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import application.main.GameMain;
import application.module.Vector2;
import application.world.IWorld;

// アクター抽象クラス
public abstract class Actor extends JPanel {
	protected IWorld world;// 世界
	protected Vector2 position;// 座標
	protected Vector2 velocity;// 移動量
	protected int width;// 幅
	protected int height;// 高さ
	protected int helth = 1;// 体力(初期設定1)
	private boolean alive;// 生存しているか
	protected Image img;//画像

	// コンストラクタ
	public Actor(IWorld world, Vector2 position, int width, int height, Image img, int helth) {
		this.world = world;
		this.position = new Vector2(position);
		this.velocity = new Vector2();
		this.width = width;
		this.height = height;
		this.img = img;
		this.alive = true;
		this.helth = helth;
	}

	public Actor(IWorld world, Vector2 position, int width, int height, Image img) {
		this(world, position, width, height, img, 1);
	}

	// 更新処理（抽象メソッド）
	public abstract void update();

	// 描画処理（抽象メソッド）
	public abstract void draw(Graphics g);

	// 衝突時処理
	protected void onCollide(Actor other) {
		damage();
	}

	// 衝突判定
	public boolean isCollide(Actor other) {
		if ((position.x < other.position.x + other.width) && // 自身の左側より相手の右側が右にある
				(other.position.x < position.x + width) && // 相手の左側より自身の右側が右にある
				(position.y < other.position.y + other.height) && // 自身の上側より相手の下側が下にある
				(other.position.y < position.y + height)) // 相手の上側より自身の下側が下にある
		{
			return true;

		} else {
			return false;
		}
	}

	// 衝突
	public void collide(Actor other) {
		if (isCollide(other)) {
			onCollide(other);
			other.onCollide(this);
		}
	}

	// ダメージ
	public void damage() {
		helth--;
		if (!isHelth()) {
			dead();
		}
	}

	// 体力が残っているか
	public boolean isHelth() {
		return helth > 0;
	}

	// 死亡
	public void dead() {
		alive = false;
	}

	// 死亡しているか
	public boolean isDead() {
		return !alive;
	}

	// 終了処理
	public void shutdown() {
	}

	// ターゲットへの方向を取得
	public Vector2 direction(Actor target) {
		Vector2 result = target.position.sub(this.position);
		return result.normalize();
	}

	// 画面内かどうか
	public boolean isInScreen() {
		return (position.x >= 0) && (position.y >= 0) && (position.x + width <= GameMain.WIDTH - 200)
				&& (position.y + height <= GameMain.HEIGHT);
	}

	// 中心位置を求める
	public Vector2 getCenter() {
		Vector2 center = new Vector2(position);
		center.x += width / 2;
		center.y += height / 2;
		return center;
	}

}
