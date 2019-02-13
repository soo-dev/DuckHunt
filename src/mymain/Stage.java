package mymain;

import java.awt.Graphics;

public class Stage extends Item {

	public Stage() {

		// �������� ��� 1
		pos.width = GameImages.img_stage_1.getWidth(null);
		pos.height = GameImages.img_stage_1.getHeight(null);
		pos.x = 0;
		pos.y = 0;

		// end ��ư ũ��
		pos_end.width = GameImages.img_end.getWidth(null);
		pos_end.height = GameImages.img_end.getHeight(null);
		pos_end.x = 1150;
		pos_end.y = 645;

	}

	@Override
	public boolean move() {

		return true;
	}

	@Override
	public void draw(Graphics g) {

		if (Game.stage_no == 2) {
			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage_1, pos.x, pos.y, null);
		}

		if (Game.stage_no == 4) { // 2��° �������� ���
			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage_2, pos.x, pos.y, null);
		}

		if (Game.stage_no == 6) { // 3��° �������� ���
			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage_3, pos.x, pos.y, null);
		}

		if (Game.stage_no >= 8) { // �������ʴ� �������� ���
			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage, pos.x, pos.y, null);
		}

	}

}
