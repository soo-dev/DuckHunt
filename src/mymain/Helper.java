package mymain;

import java.awt.Graphics;

public class Helper extends Item {

	int speed = 8; // helper 속도
	boolean helper_go = false;

	public Helper() {

		pos.width = GameImages.img_helper_1.getWidth(null);
		pos.height = GameImages.img_helper_1.getHeight(null) / 2;

		pos.x = 2300; // helper 출동전 대기장소
		pos.y = Const.GamePan.GAMEPAN_H / 10;

	}

	@Override
	public boolean move() {

		if (Const.Point.helper_point >= Const.Level.helper_go) { // helper 출동빈도수 조절
			helper_go = true;

			if (helper_go == true) {
				pos.x -= speed;
				pos.y = pos.y;
				if (pos.x <= -GameImages.img_helper_1.getWidth(null)) {
					pos.x = 2300; // 화면 벗어나면 다시 대기장소로
					Const.Point.helper_point -= Const.Level.helper_go; // helper 출동빈도수와 연동시켜 감소
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
