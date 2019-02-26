package application.module;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// カンマ区切りファイルの読み込みクラス
public class CsvReader {
	// 外側を行、内側を列とする多次元可変長配列
	private ArrayList<ArrayList<String>> datas;
	// コンストラクタ
	public CsvReader(String name) {
		datas = new ArrayList<ArrayList<String>>();
		load(name);
	}
	// ファイルの読み込み
	public void load(String name) {
		try(FileInputStream fi=new FileInputStream(name);
				InputStreamReader is=new InputStreamReader(fi);
				BufferedReader br = new BufferedReader(is)){
			datas.clear();
			// 読み込み行
			String line;
			// 読み込み行数の管理
			int i = 0;
			// 1行ずつ読み込みを行う
			while((line=br.readLine())!=null) {
				// 先頭行は列名
				if(i!=0) {
					// 新しい行を追加
					datas.add(new ArrayList<String>());
					// 1行のデータをカンマで区切り配列に格納
					String[] data=line.split(",");
					for(String s:data) {
						datas.get(i-1).add(s);
					}
				}
				// 行数のインクリメント
				i++;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// 行数を取得
	public int rows() {
		return datas.size();
	}
	// 列数を取得
	public int columns(int row) {
		return datas.get(row).size();
	}
	// データを取得
	public String get(int row, int col) {
		return datas.get(row).get(col);
	}
	// データを取得（int)
	public int geti(int row,int col) {
		return Integer.parseInt(get(row,col));
	}
	// データを取得(double)
	public double getd(int row,int col) {
		return Double.parseDouble(get(row,col));
	}
}
