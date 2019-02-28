package application.module;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

	private boolean currentUp = false; // 上方向の入力
	private boolean currentDown = false; // 下方向の入力
	private boolean currentLeft = false; // 左方向の入力
	private boolean currentRight = false; // 右方向の入力
	private boolean currentSpace = false; // スペースキーの入力
	private boolean esc = false;// エスケープキーの入力

	public boolean isUpState() {
		return currentUp;
	}

	public boolean isDownState() {
		return currentDown;
	}

	public boolean isLeftState() {
		return currentLeft;
	}

	public boolean isRightState() {
		return currentRight;
	}

	public boolean isSpaceState() {
		return currentSpace;
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
			currentUp = true;
			break;
		case KeyEvent.VK_S:
			currentDown = true;
			break;
		case KeyEvent.VK_A:
			currentLeft = true;
			break;
		case KeyEvent.VK_D:
			currentRight = true;
			break;
		case KeyEvent.VK_SPACE:
			currentSpace = true;
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
			currentUp = false;
			break;
		case KeyEvent.VK_S:
			currentDown = false;
			break;
		case KeyEvent.VK_A:
			currentLeft = false;
			break;
		case KeyEvent.VK_D:
			currentRight = false;
			break;
		case KeyEvent.VK_SPACE:
			currentSpace = false;
			break;
		case KeyEvent.VK_ESCAPE:
			esc = false;
			break;
		}
	}

}
