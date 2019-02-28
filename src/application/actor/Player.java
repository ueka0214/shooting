package application.actor;

import java.awt.Graphics;
import java.awt.Rectangle;

import application.main.GameMain;
import application.module.BulletLevel;
import application.module.BulletSelecter;
import application.module.ImageID;
import application.module.Input;
import application.module.Vector2;
import application.world.IWorld;
import application.world.manager.AnimationManager;
import application.world.manager.ImageManager;

public class Player extends Actor {
	static final int WIDTH = 64 / 2;// キャラクターの幅
	static final int HEIGHT = 64 / 2;// キャラクターの高さ
	static final double SPEED = 1.6;// キャラクターの速さ
	static final double SHOT_DELAY = 20;// 射撃間隔
	private Input input;// 入力
	private double shotTimer = SHOT_DELAY;// 射撃タイマー
	private AnimationManager animationManager;// アニメーション管理者
	private Rectangle posRect;// 座標矩形
	private Rectangle animeRect;// アニメーション描画矩形
	private int noDamageTimer;// 無敵時間
	private BulletLevel level;// 弾丸レベル
	private BulletSelecter selecter;// 弾丸選択者

	// コンストラクタ
	public Player(IWorld world, Input input, Vector2 position) {
		super(world, position, WIDTH, HEIGHT, ImageManager.getImage(ImageID.ACTOR_PLAYER), 5);
		this.input = input;
		animationManager = new AnimationManager(input, img);
		animationManager.createAnimation(WIDTH, HEIGHT, 2, 3);
		posRect = new Rectangle();
		animeRect = new Rectangle();
		noDamageTimer = 0;
		level = BulletLevel.LEVEL_1;
		selecter = new BulletSelecter(world);
	}

	// 更新処理
	public void update() {
		// 移動する
		move();
		// 弾の発射
		shot();
		// 座標矩形の更新
		setPosRect();
		// アニメーションの更新
		animeRect = animationManager.update();
		// 無敵時間の減少
		noDamageTimer--;
	}

	// 描画処理
	public void draw(Graphics g) {
		super.paintComponents(g);
		// HP表示
		g.drawString("HP :" + helth, 600, 50);
		// 被ダメージ時に点滅
		if(noDamageTimer < 0 || (noDamageTimer%3 == 0)) {
			// 矩形表示
			g.drawImage(img, posRect.x, posRect.y, posRect.width,
					posRect.height, animeRect.x,
					animeRect.y, animeRect.width, animeRect.height, this);
		}
	}

	// 衝突時処理
	protected void onCollide(Actor other) {
		if (noDamageTimer < 0) {
			damage();
			noDamageTimer = 300;//5秒間無敵
		}
	}

	// 移動
	private void move() {
		// 移動方法を通常移動に設定
		normalMove();
		//		// 移動方法を加速移動に設定
		//		accelerationMove();
		// 移動量を座標に加算する
		position = position.add(velocity);
		// 画面外に出ないように補正をかける
		position.x = Math.min(GameMain.WIDTH - 200 - this.WIDTH, Math.max(position.x, 0));
		position.y = Math.min(GameMain.HEIGHT - this.HEIGHT * 2, Math.max(position.y, 0));

	}

	// 射撃処理
	private void shot() {
		shotTimer--;
		if (input.isSpaceState() && shotTimer < 0) {
			Vector2 pos = new Vector2(this.position.x + WIDTH * 3 / 7, this.position.y);
			selecter.createBullet(level, pos);
			shotTimer = SHOT_DELAY;
		}
	}

	// 座標矩形を設定
	private void setPosRect() {
		posRect.x = (int) position.x;
		posRect.y = (int) position.y;
		posRect.width = posRect.x + width;
		posRect.height = posRect.y + height;
	}

	// 通常移動
	private void normalMove() {
		// 移動量を初期化する
		velocity.x = 0;
		velocity.y = 0;
		//キー入力で移動する
		if (input.isUpState()) {
			velocity.y = -1 * SPEED;
		}
		if (input.isDownState()) {
			velocity.y = +1 * SPEED;
		}
		if (input.isLeftState()) {
			velocity.x = -1 * SPEED;
		}
		if (input.isRightState()) {
			velocity.x = +1 * SPEED;
		}
	}

	// 加速度のつく移動
	private void accelerationMove() {
		// 移動量を加算していく
		if (input.isUpState()) {
			velocity.y -= 1 * SPEED * 0.1;
		}
		if (input.isDownState()) {
			velocity.y += 1 * SPEED * 0.1;
		}
		if (input.isLeftState()) {
			velocity.x -= 1 * SPEED * 0.1;
		}
		if (input.isRightState()) {
			velocity.x += 1 * SPEED * 0.1;
		}
		// 入力が無い場合徐々に移動量が0に近づく
		if (!input.isUpState() && !input.isDownState() && !input.isLeftState() && !input.isRightState()) {
			velocity.y -= velocity.y * 0.04;
			velocity.x -= velocity.x * 0.04;
		}
		// 移動量を最大値以内に補正する
		velocity.x = Math.min(5, Math.max(-5, velocity.x));
		velocity.y = Math.min(5, Math.max(-5, velocity.y));
	}

	// 回復
	public void heal() {
		helth++;
	}
	// 弾丸レベル設定
	public void setBulletLevel(BulletLevel level) {
		this.level = level;
	}

}
