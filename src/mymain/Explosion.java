package mymain;

import java.awt.Graphics;

public class Explosion extends Item {

	public static int INDEX_INTERVAL = 2;
	int index; // 이미지 첨자
	int index_interval = INDEX_INTERVAL;

	public Explosion() {

	}

	@Override
	public boolean move() {

		if (index_interval == INDEX_INTERVAL) {
			index++;
		}

		index_interval--;
		if (index_interval < 0)
			index_interval = INDEX_INTERVAL;

		return (index < GameImages.img_exp.length);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(GameImages.img_exp[index], pos.x, pos.y, null);
	}

}
