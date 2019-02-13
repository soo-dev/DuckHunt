package mymain;

import java.awt.Graphics;
import java.util.Random;

public class Duck1 extends Item {

	Random rand = new Random();

	boolean bRight = rand.nextBoolean(); // true false 랜덤하게
	boolean pass = rand.nextBoolean(); // 방향전환하는 오리, 그냥 날라가는 오리
	boolean bDown; // 아래쪽이동여부
	boolean die;
	int speed;
	int down = 0;

	public Duck1() {

		// 오리 크기
		// 오른쪽으로 날때
		if (bRight) {
			pos.width = 80; // GameImages.img_duck_right.getWidth(null);
			pos.height = 100; // GameImages.img_duck_right.getHeight(null);
		}
		// 왼쪽으로 날때
		if (bRight == false) {
			pos.width = 80; // GameImages.img_duck_left.getWidth(null);
			pos.height = 100; // GameImages.img_duck_left.getHeight(null);
		}

	}

	@Override
	public boolean move() {
		// TODO Auto-generated method stub

		// 좌우이동
		if (bRight && die == false) { // 잡혔으면 수직낙하
			pos.x += speed;
		} else if (bRight == false && die == false) {
			pos.x -= speed;
		}

		// if 오른쪽벽에 닿았을때 else 왼쪽벽에 닿았을때
		if (pos.x + pos.width > Const.GamePan.GAMEPAN_W && pass == false) {
			pos.x = Const.GamePan.GAMEPAN_W - pos.width; // 닿을때 정확한 좌표조정
			bRight = false; // 이동방향전환
		} else if (pos.x < 0 && pass == false) {
			pos.x = 0; // 닿을때 정확한 좌표조정
			bRight = true;
		}

		// 상하이동
		if (bDown) {
			pos.y += pos.y;
		} else
			pos.y -= speed / 1; //(rand.nextInt(5) + 1); // 비행각도조절

		if (pos.y + pos.height > Const.GamePan.GAMEPAN_H) {
			bDown = false;
		} else if (pos.y < 0) {
			bDown = false; // 하늘로 사라지게
		}

		// 잡혔을때
		if (die) {
			pos.y += (8 + speed); // 떨어지는 속도
			// pos.x = pos.x;
		}

		// 오리 사라지면 false값 return
		return ( pos.y > -100 && pos.y < Const.GamePan.GAMEPAN_H  && pos.x > -100 && pos.x < Const.GamePan.GAMEPAN_W );
	}

	@Override
	public void draw(Graphics g) {

		// 오른쪽으로 날때
		if (bRight && die == false) {
			g.drawImage(GameImages.img_duck1_right, pos.x, pos.y, null);
		}
		// 왼쪽으로 날때
		if (bRight == false && die == false) {
			g.drawImage(GameImages.img_duck1_left, pos.x, pos.y, null);
		}

		// 떨어질때
		if (die) {
			g.drawImage(GameImages.img_duck1_down_2, pos.x, pos.y, null);
		}

		// // 범위가 맞는지 체크
		// g.setColor(Color.RED);
		// g.drawRect(pos.x, pos.y,pos.width,pos.height);

	}

}
