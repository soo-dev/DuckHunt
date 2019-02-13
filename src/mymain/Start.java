package mymain;

import java.awt.Graphics;
import java.util.Random;

public class Start extends Item {

	public Start() {

		// ����̹���
		pos_back.width = GameImages.img_back.getWidth(null); // Ÿ��Ʋ�̹��� ���� ũ��
		pos_back.height = GameImages.img_back.getHeight(null); // Ÿ��Ʋ�̹��� ���� ũ��
		pos_back.x = 0; // Ÿ��Ʋ�̹��� ������ġ (���߾�)
		pos_back.y = 0; // Ÿ��Ʋ�̹��� ������ġ

		// Ÿ��Ʋ�̹���
		pos_title.width = GameImages.img_title.getWidth(null); // Ÿ��Ʋ�̹��� ���� ũ��
		pos_title.height = GameImages.img_title.getHeight(null); // Ÿ��Ʋ�̹��� ���� ũ��
		pos_title.x = 230; // Ÿ��Ʋ�̹��� ������ġ (���߾�)
		pos_title.y = 100; // Ÿ��Ʋ�̹��� ������ġ

		// target �̹���
		pos_button.width = GameImages.img_start_button.getWidth(null);
		pos_button.height = GameImages.img_start_button.getHeight(null);
		pos_button.x = 400;
		pos_button.y = 210;

	}

	// target ����
	public void start_target_move() {
		Random rand = new Random();
		pos_button.x = pos_button.x + rand.nextInt(10);
		pos_button.x = pos_button.x - rand.nextInt(10);
		pos_button.y = pos_button.y + rand.nextInt(10);
		pos_button.y = pos_button.y - rand.nextInt(10);

		// target�� ȭ������� ��������
		if (pos_button.x + pos_button.width > Const.GamePan.GAMEPAN_W) {
			pos_button.x = 0;
		} else if (pos_button.x < 0) {
			pos_button.x = Const.GamePan.GAMEPAN_W - pos_button.width;
		}

		if (pos_button.y + pos_button.width > Const.GamePan.GAMEPAN_H) {
			pos_button.y = 0;
		} else if (pos_button.y < 0) {
			pos_button.y = Const.GamePan.GAMEPAN_W - pos_button.width;
		}

	}

	public void draw(Graphics g) {

		if (Game.stage_no == 0) {
			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_back, pos_back.x, pos_back.y, null);
			g.drawImage(GameImages.img_title, pos_title.x, pos_title.y, null);
			g.drawImage(GameImages.img_start_button, pos_button.x, pos_button.y, null);
			// // ������ �´��� üũ
			// g.setColor(Color.RED);
			// g.drawRect(pos_button.x, pos_button.y,pos_button.width,pos_button.height);
		}

	}

	@Override
	public boolean move() {
		return false;
	}

}
