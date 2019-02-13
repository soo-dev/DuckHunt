package mymain;

import java.awt.Graphics;

public class Helper extends Item {

	int speed = 8; // helper �ӵ�
	boolean helper_go = false;

	public Helper() {

		pos.width = GameImages.img_helper_1.getWidth(null);
		pos.height = GameImages.img_helper_1.getHeight(null) / 2;

		pos.x = 2300; // helper �⵿�� ������
		pos.y = Const.GamePan.GAMEPAN_H / 10;

	}

	@Override
	public boolean move() {

		if (Const.Point.helper_point >= Const.Level.helper_go) { // helper �⵿�󵵼� ����
			helper_go = true;

			if (helper_go == true) {
				pos.x -= speed;
				pos.y = pos.y;
				if (pos.x <= -GameImages.img_helper_1.getWidth(null)) {
					pos.x = 2300; // ȭ�� ����� �ٽ� �����ҷ�
					Const.Point.helper_point -= Const.Level.helper_go; // helper �⵿�󵵼��� �������� ����
				}
			}
		}

		return (pos.x <= -GameImages.img_helper_1.getWidth(null));
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(GameImages.img_helper_1, pos.x, pos.y, null);
	}

}
