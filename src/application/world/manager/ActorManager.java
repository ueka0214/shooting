package application.world.manager;

import java.awt.Graphics;
import java.util.ArrayList;

import application.actor.Actor;

// Actor管理者
// 同一の種類のActorを管理する
public class ActorManager {
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private boolean isRemove = false;// 削除処理が終わったか

	public ActorManager() {
	}

	// アクターの追加
	public void add(Actor actor) {
		actors.add(actor);
	}

	// アクターの削除
	public void remove() {
		do{
			isRemove = true;
			for (Actor actor : actors) {
				if (actor.isDead()) {
					actor.shutdown();
					actors.remove(actor);
					isRemove = false;
					break;
				}
			}
		}while(!isRemove);
	}

	// 更新処理
	public void update() {
		for (Actor actor : actors) {
			actor.update();
		}
	}

	// アクター同士の衝突処理
	public void collide(Actor other) {
		for (Actor actor : actors) {
			actor.collide(other);
		}
	}

	// アクター管理者の管理するアクターへの衝突処理
	public void collide(ActorManager other) {
		for (Actor actor : actors) {
			other.collide(actor);
		}
	}

	// アクターの描画処理
	public void draw(Graphics g) {
		for (Actor actor : actors) {
			actor.draw(g);
		}
	}

	// アクターの要素数を取得
	public int size() {
		return actors.size();
	}

	// アクター全削除
	public void clear() {
		actors.clear();
	}
}
