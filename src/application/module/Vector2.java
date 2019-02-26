package application.module;

/* 2次元ベクトルを表す*/
public class Vector2 {

	public double x;// x座標
	public double y;// y座標

	// コンストラクタ
	// 引数無
	// 座標空間の原点 (0, 0) に点を構築して初期化
	public Vector2() {
		this(0, 0);
	}

	// 引数 double x, double y
	// 指定された座標に点を構築して初期化
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// 引数 Position p
	// 指定されたPositionオブジェクトと同じ位置に点を構築して初期化
	public Vector2(Vector2 p) {
		this(p.x, p.y);
	}

	// 加算
	public Vector2 add(Vector2 p) {
		return new Vector2(this.x + p.x, this.y + p.y);

	}

	// 減算
	public Vector2 sub(Vector2 p) {
		return new Vector2(this.x - p.x, this.y - p.y);

	}

	// 内積
	public double dot(Vector2 v) {
		return x * v.x + y * v.y;
	}

	// 長さを求める（二乗和のみで平方根を取らない）
	public double lengthSq() {
		return dot(this);
	}

	// 長さを求める
	public double length() {
		return Math.sqrt(lengthSq());
	}

	// ベクトルのスケーリング
	public Vector2 scale(double s) {
		return new Vector2(x * s, y * s);
	}

	// 正規化する（単位ベクトル化する）
	public Vector2 normalize() {
		double len = length();
		if (len != 0) {
			x /= len;
			y /= len;
		}
		return this;
	}


	// 2つのベクトルの作る角度を求める
	public double innerDegree(Vector2 v) {
		double cos_theta = this.dot(v) / (this.length() * v.length());
		return Math.acos(Math.max(-1.0, Math.min(1.0, cos_theta)));
	}

	// 方位角からベクトルを求める
	public static Vector2 fromDirection(double direction) {
		double x = Math.sin(direction);
		double y = Math.cos(direction);
		return new Vector2(x, y);
	}

}
