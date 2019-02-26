package application.world.manager;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import application.module.ActorID;

// 画像管理者
public class ImageManager {
	private static HashMap<ActorID,Image> imgManager = new HashMap<ActorID,Image>();

	public static void initialize() {
		Image img = Toolkit.getDefaultToolkit().getImage("img/ship0.png");
		imgManager.put(ActorID.ACTOR_PLAYER, img);
		img = Toolkit.getDefaultToolkit().getImage("img/bullet.png");
		imgManager.put(ActorID.ACTOR_PLAYER_BULLET, img);
		img = Toolkit.getDefaultToolkit().getImage("img/enemy.png");
		imgManager.put(ActorID.ACTOR_ENEMY, img);
		img = Toolkit.getDefaultToolkit().getImage("img/enemy_bullet.png");
		imgManager.put(ActorID.ACTOR_ENEMY_BULLET, img);
	}

	public static Image getImage(ActorID id) {
		return imgManager.get(id);
	}

}
