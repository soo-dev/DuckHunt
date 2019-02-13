package mymain;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ExplosionManager {

	public List<Explosion> explosion_list = new ArrayList<Explosion>();

	public void make_explosion(int x, int y) {

		Explosion exp = new Explosion();
		exp.pos.x = x - GameImages.img_exp[0].getWidth(null) / 2; // getWidth 이미지크기
		exp.pos.y = y - GameImages.img_exp[0].getHeight(null) / 2; // 폭발위치가 중앙에서 그려지도록 보정

		explosion_list.add(exp);
	}

	public void move() {

		for (int i = 0; i < explosion_list.size(); i++) {
			Explosion exp = explosion_list.get(i);
			if (exp.move() == false) { // 이미지 첨자범위 벗어나면
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
