package application.actor;

import application.module.ActorID;
import application.module.Vector2;
import application.world.IWorld;

public class DispersionBullet extends EnemyBullet {

	private double aliveTimer = 60.0;// 生存時間

	public DispersionBullet(IWorld world, Vector2 position) {
		super(world, position);
	}

	public DispersionBullet(IWorld world, Vector2 position, Vector2 velocity) {
		super(world, position,velocity);

	}

	@Override
	public void update() {
		// 移動量に応じて移動
		position = position.add(velocity);
		// 生存時間を減少させる
		aliveTimer--;
		// 生存時間が0以下になったら拡散し死亡する
		if (aliveTimer <= 0) {
			for (int i = 0; i < 11; i++) {
				Vector2 pos = this.getCenter();
				Vector2 vec = Vector2.fromDirection(i * 36);
				vec.normalize();
				world.add(ActorID.ACTOR_ENEMY_BULLET, new EnemyBullet(world, pos, vec));
			}
			dead();
		}

		//画面外に出たら死亡
		if (!isInScreen()) {
			dead();
		}

	}
}
