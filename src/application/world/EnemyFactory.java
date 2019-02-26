package application.world;

import application.actor.Enemy;
import application.module.ActorID;
import application.module.BulletID;
import application.module.CreatePattern;
import application.module.CsvToEnemy;
import application.module.Vector2;

// エネミー工場
public class EnemyFactory {
	private IWorld world;// ワールド
	private double birthTimer;//生成タイマー
	private CsvToEnemy csvToEnemy;// CSVからエネミー生成
	private CreatePattern pattern;// 生成パターン

	// コンストラクタ
	public EnemyFactory(IWorld world) {
		this.world = world;
		csvToEnemy = new CsvToEnemy();
		pattern = new CreatePattern();
		birthTimer = 60;
	}

	// ワールドにエネミーを追加する
	public void add(int row) {
		Vector2 pos = csvToEnemy.getPos(row);
		Vector2 vec = csvToEnemy.getVelocity(row);
		BulletID id = csvToEnemy.getBulletID(row);
		int health = csvToEnemy.getHelth(row);
		world.add(ActorID.ACTOR_ENEMY, new Enemy(world,pos,vec,id,health));
	}

	// 更新処理
	public void update() {
		// 生成タイマーを減少させる
		birthTimer--;
		// 次回生成までの時間が過ぎたらエネミーを生成する。
		if(birthTimer<0) {
			create();
		}
	}

	// エネミーの生成
	private void create() {
		int start = pattern.getStart()-1;// 読み込み始点行の取得
		int finish = pattern.getFinish()-1;// 読み込み終点行の取得
		// 始点行から終点行までenemy.csvからエネミーを生成する
		for(int i = start;i<=finish;i++) {
			add(i);
		}
		birthTimer = pattern.getTime();// 次回生成までの時間を取得
		// パターンCSVの現在行が最終行であれば1行目に戻る
		if(!pattern.next()) {
			pattern.initialize();
			System.out.println("初期化");
		}
	}
}
