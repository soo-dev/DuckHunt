package mymain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DuckManager1 {
	

	static public List<Duck1> duck1_list = new ArrayList<Duck1>();

	static Random rand = new Random();

	int duck1_make_interval = Const.Level.MAKE_DUCK_INTERVAL1;

	// ��ٸ� ����1
	public void duck1_make() {

		Duck1 duck1 = new Duck1(); // ������ü����

		if (duck1_make_interval == Const.Level.MAKE_DUCK_INTERVAL1) {

			// ���� ������ġ �� ���ǵ�
			duck1.pos.x = rand.nextInt(Const.GamePan.GAMEPAN_W) - (duck1.pos.width / 2);
			duck1.pos.y = 768 - duck1.pos.height / 2;
			duck1.speed = rand.nextInt(7) + Const.Level.speed + Game.stage_no; // �����ӷ� ���� + ���ʽ� * ���ӽ������� Ƚ��

			duck1_list.add(duck1);
		}

		duck1_make_interval--;

		if (duck1_make_interval < 0)
			duck1_make_interval = Const.Level.MAKE_DUCK_INTERVAL1;

	}

	public void move() {

		// ��ٸ� ����1
		for (int i = 0; i < duck1_list.size(); i++) {

			Duck1 duck1 = duck1_list.get(i);

			//System.out.printf("%d�� ���� ��ٸ����� %d : %d\n",i,duck1.pos.x, duck1.pos.y);
			if (duck1.move() == false) {
				//System.out.printf("%d�� ����� ��ٸ����� %d : %d\n",i,duck1.pos.x, duck1.pos.y);
				duck1_list.remove(i); // �����
				//System.out.println("��ٸ����� ����:" + duck1_list.size());
			}
		}

	}

	public void draw(Graphics g) {

		for (Duck1 duck1 : duck1_list) {
			duck1.draw(g);
		}

	}

}
