package application.world;

import application.actor.Actor;
import application.module.ActorID;

public interface IWorld {
	void add(ActorID id,Actor actor);
	Actor getPlayer();
	int size(ActorID id);
	void addScore(int score);

}
