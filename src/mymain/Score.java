package mymain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Score {

	JPanel scorePan;
	static Font font = new Font("돋움체", Font.BOLD, 30);
	static Font font_over = new Font("돋움체", Font.BOLD, 150);
	static Color color = new Color(255, 255, 255);

	public void draw(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(String.format("Game Score %d", Const.Point.game_point), 30, 730);
		g.drawString(String.format("%d", Const.Level.helper_go), 30, 760);
		g.drawString(String.format("Point Helper %d", Const.Point.helper_point), 60, 760);

		if (Game.stage_no < 8) {
			g.drawString(String.format("Game Over Count %d", Const.Point.over_time), 30, 700); // 게임오버
			g.setFont(font_over);

			if (Const.Point.over_time <= 0) {
				g.drawString("Game Over", 250, 350); // 게임오버
			}
		}

	}

}
