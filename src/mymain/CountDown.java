package mymain;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class CountDown {

	int count = 6;
	Timer count_timer;
	static Helper helper = new Helper();
	static DuckManager duckManager = new DuckManager();

	public void countdown() {

		count = 5;
		count_timer = new Timer(300, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				count--;

				if (count < 0) {
					count = 0;

					Const.Point.over_time = Const.Point.over_time_init;
					Game.stage_no++;

					count_timer.stop();
				}

			}
		});

		count_timer.start();

	}

	public void draw(Graphics g) {

		if (count >= 0 && count <= 5 && Game.stage_no == 1) {

			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage_1, 0, 0, null);
			g.drawImage(GameImages.img_count1[count], 520, 100, null);
			g.drawImage(GameImages.round, 180, 110, null);
			g.drawImage(GameImages.round1, 530, 400, null);

		}

		if (count >= 0 && count <= 5 && Game.stage_no == 3) {

			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage_2, 0, 0, null);
			g.drawImage(GameImages.img_count1[count], 520, 100, null);
			g.drawImage(GameImages.round, 180, 110, null);
			g.drawImage(GameImages.round2, 530, 400, null);

		}

		if (count >= 0 && count <= 5 && Game.stage_no == 5) {

			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage_3, 0, 0, null);
			g.drawImage(GameImages.img_count1[count], 520, 100, null);
			g.drawImage(GameImages.round, 180, 110, null);
			g.drawImage(GameImages.round3, 530, 400, null);

		}

		if (count >= 0 && count <= 5 && Game.stage_no >= 7) {

			g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
			g.drawImage(GameImages.img_stage, 0, 0, null);
			g.drawImage(GameImages.img_count1[count], 520, 100, null);
			g.drawImage(GameImages.round, 180, 110, null);

		}

	}

}
