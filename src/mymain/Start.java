package mymain;

import java.awt.Graphics;
import java.util.Random;

public class Start extends Item {

	public Start() {

		// 배경이미지
		pos_back.width = GameImages.img_back.getWidth(null); // 타이틀이미지 가로 크기
		pos_back.height = GameImages.img_back.getHeight(null); // 타이틀이미지 세로 크기
		pos_back.x = 0; // 타이틀이미지 가로위치 (정중앙)
		pos_back.y = 0; // 타이틀이미지 세로위치

		// 타이틀이미지
		pos_title.width = GameImages.img_title.getWidth(null); // 타이틀이미지 가로 크기
		pos_title.height = GameImages.img_title.getHeight(null); // 타이틀이미지 세로 크기
		pos_title.x = 230; // 타이틀이미지 가로위치 (정중앙)
		pos_title.y = 100; // 타이틀이미지 세로위치

		// target 이미지
		pos_button.width = GameImages.img_start_button.getWidth(null);
		pos_button.height = GameImages.img_start_button.getHeight(null);
		pos_button.x = 400;
		pos_button.y = 210;

	}

	// target 진동
	public void start_target_move() {
		Random rand = new Random();
		pos_button.x = pos_button.x + rand.nextInt(10);
		pos_button.x = pos_button.x - rand.nextInt(10);
		pos_button.y = pos_button.y + rand.nextInt(10);
		pos_button.y = pos_button.y - rand.nextInt(10);

		// target이 화면밖으로 나갔을때
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
			// // 범위가 맞는지 체크
			// g.setColor(Color.RED);
			// g.drawRect(pos_button.x, pos_button.y,pos_button.width,pos_button.height);
		}

	}

	@Override
	public boolean move() {
		return false;
	}

}
