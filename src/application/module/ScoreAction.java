package application.module;


import application.actor.Player;

public class ScoreAction {

	// スコアに応じて処理を行う
	public void action(int score,Player player) {
		if(score%50 == 0) {
			heal(player);
		}
		if(score == 100) {
			bulletLevelUp(player, BulletLevel.LEVEL_2);
		}
		if(score == 500) {
			bulletLevelUp(player, BulletLevel.LEVEL_3);
		}
	}

	// ヒール処理
	private void heal(Player player) {
		player.heal();
	}
	// 弾丸レベルアップ処理
	private void bulletLevelUp(Player player,BulletLevel level) {
		player.setBulletLevel(level);
	}

}
