package application.world.manager;

import java.awt.Image;
import java.awt.Rectangle;

import application.frame.ShootingApplication;
import application.module.Input;

// アニメーション管理者
public class AnimationManager {

	private int animationCount;// アニメーションカウンター
	private boolean animationFlag;// アニメーションフラグ
	private Input input;// 入力
	private Rectangle[][] rectangles;// 矩形配列

	public AnimationManager(Input input,Image img) {
		animationCount = 0;
		animationFlag = false;
		this.input = input;
	}

	// アニメーション矩形配列生成
	// 幅、高さ、行、列を引数で受け取る
	public void createAnimation(int width,int height,int row,int col) {
		rectangles = new Rectangle[row][col];
		for(int i = 0;i<row;i++) {
			for(int j = 0;j<col;j++) {
				rectangles[i][j] = new Rectangle(width*j, height*i,width*(j+1), height*(i+1));
			}
		}
	}
	// 更新処理
	public Rectangle update() {
		// アニメーションカウンタとフラグを更新する
		// 1/4秒毎にアニメーション変化する
		if (++animationCount > ShootingApplication.FPS / 4) {
			animationFlag = !animationFlag;
			animationCount = 0;
		}

		return getRect();
	}

	// アニメーションに応じた画像矩形の取得
	public Rectangle getRect() {
		int row = 0;// 配列の行
		int col = 0;// 配列の列
		// フラグが立っていれば行を切り替える
		if(animationFlag) {
			row = 0;
		}else {
			row = 1;
		}
		// 左右入力に応じて列を切り替える
		if(input.isLeftState()) {
			col = 1;
		}else if(input.isRightState()) {
			col = 2;
		}else {
			col = 0;
		}
		return rectangles[row][col];
	}

}
