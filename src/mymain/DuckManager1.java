package mymain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DuckManager1 {
	

	static public List<Duck1> duck1_list = new ArrayList<Duck1>();

	static Random rand = new Random();

	int duck1_make_interval = Const.Level.MAKE_DUCK_INTERVAL1;

	// 깔다른 오리1
	public void duck1_make() {

		Duck1 duck1 = new Duck1(); // 오리객체생성

		if (duck1_make_interval == Const.Level.MAKE_DUCK_INTERVAL1) {

			// 오리 시작위치 및 스피드
			duck1.pos.x = rand.nextInt(Const.GamePan.GAMEPAN_W) - (duck1.pos.width / 2);
			duck1.pos.y = 768 - duck1.pos.height / 2;
			duck1.speed = rand.nextInt(7) + Const.Level.speed + Game.stage_no; // 오리속력 랜덤 + 보너스 * 게임스테이지 횟수

			duck1_list.add(duck1);
		}

		duck1_make_interval--;

		if (duck1_make_interval < 0)
			duck1_make_interval = Const.Level.MAKE_DUCK_INTERVAL1;

	}

	public void move() {

		// 깔다른 오리1
		for (int i = 0; i < duck1_list.size(); i++) {

			Duck1 duck1 = duck1_list.get(i);

			//System.out.printf("%d번 난다 깔다른오리 %d : %d\n",i,duck1.pos.x, duck1.pos.y);
			if (duck1.move() == false) {
				//System.out.printf("%d번 사라진 깔다른오리 %d : %d\n",i,duck1.pos.x, duck1.pos.y);
				duck1_list.remove(i); // 지우기
				//System.out.println("깔다른오리 갯수:" + duck1_list.size());
			}
		}

	}

	public void draw(Graphics g) {

		for (Duck1 duck1 : duck1_list) {
			duck1.draw(g);
		}

	}

}
