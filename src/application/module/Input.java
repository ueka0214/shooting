package application.module;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	private boolean up = false; // 上方向の入力
	private boolean down = false; // 下方向の入力
	private boolean left = false; // 左方向の入力
	private boolean right = false; // 右方向の入力
	private boolean space = false; // スペースキーの入力
	private boolean esc = false;// エスケープキーの入力

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isSpace() {
		return space;
	}

	public boolean isEsc() {
		return esc;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 未使用のため未実装

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// キーが押されたかの検出
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_SPACE:
			space = true;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// キーが離されたかの検出
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_SPACE:
			space = false;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = false;
			break;
		}
	}

}
