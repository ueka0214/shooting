package application.module;

import application.actor.Bullet;
import application.world.IWorld;

public class BulletSelecter {
	private IWorld world;

	public BulletSelecter(IWorld world) {
		this.world = world;
	}

	// 弾丸生成
	public void createBullet(BulletLevel level,Vector2 position) {
		switch(level) {
		case LEVEL_1:
			level1Bullet(position);
			break;
		case LEVEL_2:
			level2Bullet(position);
			break;
		case LEVEL_3:
			level3Bullet(position);
			break;
		}
	}

	// Level1弾丸
	public void level1Bullet(Vector2 position) {
		world.add(ActorID.ACTOR_PLAYER_BULLET, new Bullet(world, position));
	}

	// Level2弾丸
	public void level2Bullet(Vector2 position) {
		level1Bullet(position);
		Vector2 rightVec = new Vector2(0.4,-1.0);
		Vector2 leftVec = new Vector2(-0.4,-1.0);
		world.add(ActorID.ACTOR_PLAYER_BULLET, new Bullet(world, position,leftVec));
		world.add(ActorID.ACTOR_PLAYER_BULLET, new Bullet(world, position,rightVec));
	}

	// Level3弾丸
	public void level3Bullet(Vector2 position) {
		level2Bullet(position);
		Vector2 rightVec = new Vector2(0.8,-1.0);
		Vector2 leftVec = new Vector2(-0.8,-1.0);
		world.add(ActorID.ACTOR_PLAYER_BULLET, new Bullet(world, position,leftVec));
		world.add(ActorID.ACTOR_PLAYER_BULLET, new Bullet(world, position,rightVec));
	}

}
