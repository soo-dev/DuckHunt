package mymain;

import java.awt.Graphics;

public class Target extends Item {

	public Target() {

		pos.width = GameImages.img_target.getWidth(null);
		pos.height = GameImages.img_target.getHeight(null);

		pos.x = -100; // ���콺�� ���������� ȭ��ȿ� ������ ����Ⱦ
		pos.y = -100;

	}

	public void move_with_mouse(int x, int y) {
		pos.x = x - GameImages.img_target.getWidth(null) / 2; // ǥ�� ���߾Ӱ� ���콺 ��ġ ��ġ��Ű��
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
