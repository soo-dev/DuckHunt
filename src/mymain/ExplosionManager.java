package mymain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ExplosionManager {

	public List<Explosion> explosion_list = new ArrayList<Explosion>();

	public void make_explosion(int x, int y) {

		Explosion exp = new Explosion();
		exp.pos.x = x - GameImages.img_exp[0].getWidth(null) / 2; // getWidth �̹���ũ��
		exp.pos.y = y - GameImages.img_exp[0].getHeight(null) / 2; // ������ġ�� �߾ӿ��� �׷������� ����

		explosion_list.add(exp);
	}

	public void move() {

		for (int i = 0; i < explosion_list.size(); i++) {
			Explosion exp = explosion_list.get(i);
			if (exp.move() == false) { // �̹��� ÷�ڹ��� �����
				explosion_list.remove(i);
			}
		}
	}

	public void draw(Graphics g) {
		for (Explosion exp : explosion_list) {
			exp.draw(g);
		}

	}

}
