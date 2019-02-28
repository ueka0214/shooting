package application.world.manager;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import application.module.ImageID;

// 画像管理者
public class ImageManager {
	private static HashMap<ImageID,Image> imgManager = new HashMap<ImageID,Image>();

	public static void initialize() {
		Image img = Toolkit.getDefaultToolkit().getImage("img/ship0.png");
		imgManager.put(ImageID.ACTOR_PLAYER, img);
		img = Toolkit.getDefaultToolkit().getImage("img/bullet.png");
		imgManager.put(ImageID.ACTOR_PLAYER_BULLET, img);
		img = Toolkit.getDefaultToolkit().getImage("img/enemy.png");
		imgManager.put(ImageID.ACTOR_ENEMY, img);
		img = Toolkit.getDefaultToolkit().getImage("img/enemy_bullet.png");
		imgManager.put(ImageID.ACTOR_ENEMY_BULLET, img);
		img = Toolkit.getDefaultToolkit().getImage("img/explanation.png");
		imgManager.put(ImageID.SCENE_EXPLANATION, img);
		img = Toolkit.getDefaultToolkit().getImage("img/push_space.png");
		imgManager.put(ImageID.IMAGE_PUSH_SPACE, img);
	}

	public static Image getImage(ImageID id) {
		return imgManager.get(id);
	}

}
