package mymain;

import java.awt.Graphics;

public class Reed extends Item {

	public Reed() {

		// �����
		pos.width = GameImages.img_reed_1.getWidth(null);
		pos.height = GameImages.img_reed_1.getHeight(null);
		pos.x = 0;
		pos.y = 0;

	}

	@Override
	public boolean move() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics g) {

		if (Game.stage_no == 2) { // 1��° �ϴ�
			g.drawImage(GameImages.img_reed_1, pos.x, pos.y, null);
		}

		if (Game.stage_no == 4) { // 2��° �ϴ�
			g.drawImage(GameImages.img_reed_2, pos.x, pos.y, null);
		}

		if (Game.stage_no == 6) { // 3��° �ϴ�
			g.drawImage(GameImages.img_reed, pos.x, pos.y, null);
		}

		if (Game.stage_no >= 8) { // 4��° �ϴ�
			g.drawImage(GameImages.img_reed_3, pos.x, pos.y, null);
		}

	}

}
