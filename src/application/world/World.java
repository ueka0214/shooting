package application.world;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import application.actor.Actor;
import application.actor.Player;
import application.module.ActorID;
import application.module.ScoreAction;

public class World extends JPanel implements IWorld {

	private WorldActor actors;// アクター管理者統括
	private Player player = null;// プレイヤー
	private JFrame frame = null;// フレーム
	private EnemyFactory factory = null;// エネミー工場
	private int score = 0;//スコア
	private ScoreAction action;

	// コンストラクタ
	public World(JFrame frame) {
		this.frame = frame;
		actors = new WorldActor();
		// 工場のインスタンス化
		factory = new EnemyFactory(this);
		action = new ScoreAction();
	}

	// アクターの追加
	public void add(ActorID id, Actor actor) {
		actors.add(id, actor);
		frame.add(actor);
	}

	// プレイヤーの取得
	public Actor getPlayer() {
		return player;
	}

	// プレイヤーの追加
	public void addPlayer(Player actor) {
		if (player == null) {
			player = actor;
			add(ActorID.ACTOR_PLAYER, actor);
		}
	}

	// スコアの追加
	public void addScore(int score) {
		this.score += score;
		scoreAction();
	}

	// 初期化処理
	public void initialize() {
		actors.initialize();
	}

	// 更新処理
	public void update() {
		actors.update();
		factory.update();
	}

	// 描画処理
	public void draw(Graphics g) {
		super.paintComponents(g);
		actors.draw(g);
	}

	// アクターの要素数の取得
	public int size(ActorID id) {
		return actors.size(id);
	}

	// スコアの取得
	public int getScore() {
		return score;
	}

	// スコアが一定になると処理を行う
	private void scoreAction() {
		action.action(score, player);
	}

}
