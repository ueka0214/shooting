package application.actor;

import java.awt.Graphics;

import application.module.ImageID;
import application.module.Vector2;
import application.world.IWorld;
import application.world.manager.ImageManager;

public class Bullet extends Actor {

	private static final double SPEED = 5.0;// 移動速度
	private static final int WIDTH = 8;// 幅
	private static final int HEIGHT = 8;// 高さ
	private double aliveTimer = 180.0;// 生存時間

	public Bullet(IWorld world, Vector2 position, Vector2 vec) {
		super(world, position, WIDTH, HEIGHT, ImageManager.getImage(ImageID.ACTOR_PLAYER_BULLET));
		velocity.x = vec.x * SPEED;
		velocity.y = vec.y * SPEED;
	}

	public Bullet(IWorld world, Vector2 position) {
		this(world, position, new Vector2(0, -1.0));
	}

	@Override
	public void update() {
		// 移動量に応じて移動
		position = position.add(velocity);
		// 生存時間を減少させる
		aliveTimer--;
		//画面外に出るか、生存時間が0以下になったら死亡
		if (!isInScreen() || aliveTimer <= 0) {
			dead();
		}

	}

	@Override
	public void draw(Graphics g) {
		super.paintComponents(g);
		g.drawImage(img, (int) position.x, (int) position.y, this);
	}

}
