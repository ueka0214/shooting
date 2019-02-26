package application.world;

import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JFrame;

import application.actor.Actor;
import application.module.ActorID;
import application.world.manager.ActorManager;

// ActorManagerを統括する
public class WorldActor{
	// アクターの種類毎にアクター管理者を管理する
	HashMap<ActorID, ActorManager> managers = new HashMap<ActorID, ActorManager>();
	private JFrame frame = null;// フレーム

	public WorldActor(JFrame frame) {
		this.frame = frame;
	}


	// 初期化処理
	public void initialize() {
		clear();
		managers.put(ActorID.ACTOR_PLAYER, new ActorManager(frame));
		managers.put(ActorID.ACTOR_PLAYER_BULLET, new ActorManager(frame));
		managers.put(ActorID.ACTOR_ENEMY, new ActorManager(frame));
		managers.put(ActorID.ACTOR_ENEMY_BULLET, new ActorManager(frame));
		managers.put(ActorID.ACTOR_ENEMY_SPECIAL_BULLET, new ActorManager(frame));
	}

	// 更新処理
	public void update() {
		for (ActorID id : managers.keySet()) {
			managers.get(id).update();// 各アクターの更新
			managers.get(id).remove();// 死亡しているアクターの削除
		}
		// 衝突処理
		managers.get(ActorID.ACTOR_PLAYER).collide(managers.get(ActorID.ACTOR_ENEMY));
		managers.get(ActorID.ACTOR_PLAYER).collide(managers.get(ActorID.ACTOR_ENEMY_BULLET));
		managers.get(ActorID.ACTOR_PLAYER).collide(managers.get(ActorID.ACTOR_ENEMY_SPECIAL_BULLET));
		managers.get(ActorID.ACTOR_PLAYER_BULLET).collide(managers.get(ActorID.ACTOR_ENEMY));
	}

	//アクターの追加
	public void add(ActorID id, Actor actor) {
		managers.get(id).add(actor);
	}

	// 描画処理
	public void draw(Graphics g) {
		for (ActorID id : managers.keySet()) {
			managers.get(id).draw(g);// 各アクターの描画処理
		}
	}

	// アクターの要素数の取得
	public int size(ActorID id) {
		return managers.get(id).size();
	}

	// アクター全削除
	public void clear() {
		for (ActorID id : managers.keySet()) {
			managers.get(id).clear();
		}
		managers.clear();
	}

}
