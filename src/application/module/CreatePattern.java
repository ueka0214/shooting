package application.module;

public class CreatePattern {
	private CsvReader csv;// CSVリーダー
	private int row;// 読み込み行
	public CreatePattern() {
		csv = new CsvReader("csv/pattern.csv");
		row = 0;// 読み込み行を1行目に設定
	}
	// 読み込みの始点
	public int getStart() {
		return csv.geti(row, 0)-1;
	}
	// 読み込みの終点
	public int getFinish() {
		return csv.geti(row, 1)-1;
	}
	// 次回までの生成間隔を取得
	public int getTime() {
		return csv.geti(row, 2);
	}
	// 次の行へ
	public boolean next() {
		// 現在が最終行でなければ真を返す
		return (++row<csv.rows());
	}
	// 最初の行へ
	public void initialize() {
		row = 0;
	}

	// 行数を取得
	public int getRow() {
		return row;
	}

}
