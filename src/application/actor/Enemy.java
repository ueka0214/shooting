package application.actor;

import java.awt.Graphics;

import application.module.ActorID;
import application.module.BulletID;
import application.module.ImageID;
import application.module.ShotPattern;
import application.module.Vector2;
import application.world.IWorld;
import application.world.manager.ImageManager;

public class Enemy extends Actor {

	private static final int WIDTH = 64;// 幅
	private static final int HEIGHT = 64;// 高さ
	private final double SHOT_DELAY;// 射撃間隔
	private double shotTimer;// 射撃タイマー
	private double bulletAngle = 5;
	private ShotPattern pattern;
	private BulletID shotID;

	// コンストラクタ
	public Enemy(IWorld world, Vector2 position, Vector2 velocity, BulletID id,int health) {
		super(world, position, WIDTH, HEIGHT, ImageManager.getImage(ImageID.ACTOR_ENEMY), health);
		this.velocity = velocity;
		pattern = new ShotPattern();
		SHOT_DELAY = setShot(id);
		shotTimer = SHOT_DELAY;
	}

	@Override
	public void update() {
		// 移動
		move();
		// 射撃処理の更新
		pattern.update(getCenter(), world.getPlayer().getCenter(), bulletAngle);
		// 射撃
		shot();
	}

	@Override
	public void draw(Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.drawImage(img, (int) position.x, (int) position.y, this);

	}

	// 終了処理
	@Override
	public void shutdown() {
		world.addScore(10);// スコアを10加算
	}

	private void move() {
		// 移動量に応じて移動
		position = position.add(velocity);
		// 画面端に到達で反対方向へ移動を行う
		velocity.x = (isInScreen()) ? velocity.x : -velocity.x;
		velocity.y = (isInScreen()) ? velocity.y : -velocity.y;

	}

	// 射撃処理
	private void shot() {
		shotTimer--;
		if (shotTimer < 0) {
			createBullet(shotID);
			shotTimer = SHOT_DELAY;
		}
	}

	// 射撃選択処理
	private double setShot(BulletID id) {
		shotID = id;
		switch (id) {
		case SHOT_NORMAL:
			return 30;
		case SHOT_SPIRAL:
			return 9;
		case SHOT_LEAD:
			return 60;
		case SHOT_DISPERSION:
			return 90;
		}
		return 0;
	}

	// 弾丸の生成
	private void createBullet(BulletID id) {
		Vector2 pos = this.getCenter();// キャラクター中心座標を取得
		Vector2 vec;// 移動ベクトルオブジェクト
		switch (id) {
		case SHOT_NORMAL:// 下方向へ進む通常弾
			world.add(ActorID.ACTOR_ENEMY_BULLET, new EnemyBullet(world, pos));
			break;
		case SHOT_LEAD:// プレイヤーに向かって発射される誘導弾
			vec = pattern.lead();
			world.add(ActorID.ACTOR_ENEMY_BULLET, new EnemyBullet(world, pos, vec));
			break;
		case SHOT_SPIRAL:// キャラクターを中心に渦上に発射される弾
			vec = pattern.fromDirection();
			world.add(ActorID.ACTOR_ENEMY_BULLET, new EnemyBullet(world, pos, vec));
			break;
		case SHOT_DISPERSION:// 発射後拡散する弾
			world.add(ActorID.ACTOR_ENEMY_SPECIAL_BULLET, new DispersionBullet(world, pos));
		default:
			break;
		}
	}

}
