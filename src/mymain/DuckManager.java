package mymain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DuckManager {

	static public List<Duck> duck_list = new ArrayList<Duck>();

	static Random rand = new Random();

	int duck_make_interval = Const.Level.MAKE_DUCK_INTERVAL;

	public void duck_make() {
	
		

		if (duck_make_interval == Const.Level.MAKE_DUCK_INTERVAL) {

			Duck duck = new Duck(); // 오리객체생성
			// 오리 시작위치 및 스피드
			duck.pos.x = rand.nextInt(Const.GamePan.GAMEPAN_W) - (duck.pos.width / 2);
			duck.pos.y = 768 - duck.pos.height / 2;
			duck.speed = rand.nextInt(5) + Const.Level.speed + Game.stage_no; // 오리속력 랜덤 + 보너스 * 게임스테이지 횟수

			duck_list.add(duck);
		}

		duck_make_interval--;

		if (duck_make_interval < 0)
			duck_make_interval = Const.Level.MAKE_DUCK_INTERVAL;

	}

	public void move() {

		for (int i = 0; i < duck_list.size(); i++) {

			Duck duck = duck_list.get(i);

			//System.out.printf("%d번 난다 일반오리 %d : %d\n",i,duck.pos.x, duck.pos.y);
			if (duck.move() == false) { 
				//System.out.printf("%d번 사라진 일반오리 %d : %d\n",i,duck.pos.x, duck.pos.y);
				duck_list.remove(i); // 지우기
				//System.out.println("오리갯수:" + duck_list.size());
			}
		}

	}

	public void draw(Graphics g) {

		for (Duck duck : duck_list) {
			duck.draw(g);
		}

	}

}
