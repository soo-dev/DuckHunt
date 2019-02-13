package mymain;

import java.awt.Graphics;

public class Target extends Item {

	public Target() {

		pos.width = GameImages.img_target.getWidth(null);
		pos.height = GameImages.img_target.getHeight(null);

		pos.x = -100; // 마우스랑 만나기전에 화면안에 있으면 보기싫어서
		pos.y = -100;

	}

	public void move_with_mouse(int x, int y) {
		pos.x = x - GameImages.img_target.getWidth(null) / 2; // 표적 정중앙과 마우스 위치 일치시키기
		pos.y = y - GameImages.img_target.getHeight(null) / 2;
	}

	@Override
	public boolean move() {

		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(GameImages.img_target, pos.x, pos.y, null);
	}

}
