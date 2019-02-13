package mymain;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GameImages {

	public static Image img_back; // 배경
	public static Image img_title; // 메인시작화면 타이틀이미지
	public static Image img_start_button; // 메인시작화면 start 버튼 이미지
	public static Image img_st1;
	public static Image img_st2;
	public static Image[] img_exp = new Image[27];

	public static Image round;
	public static Image round1;
	public static Image round2;
	public static Image round3;

	public static Image img_stage; // 본게임배경
	public static Image img_stage_1; // 스테이지 1
	public static Image img_stage_2; // 스테이지 2
	public static Image img_stage_3; // 스테이지 3
	public static Image img_reed; // 하단 1
	public static Image img_reed_1; // 하단 1
	public static Image img_reed_2; // 하단 2
	public static Image img_reed_3; // 하단 3
	public static Image img_end; // 게임끝

	public static Image img_dog;

	public static Image img_duck_right;
	public static Image img_duck_left;
	// public static Image img_duck_down_1;
	public static Image img_duck_down_2;

	public static Image img_duck1_right;
	public static Image img_duck1_left;
	// public static Image img_duck1_down_1;
	public static Image img_duck1_down_2;

	public static Image img_target;
	public static Image img_helper_1;

	public static Image[] img_count1 = new Image[6]; // 카운트 이미지

	static {

		img_back = new ImageIcon("img_stage_2.jpg").getImage();
		img_title = new ImageIcon("img_title.png").getImage();
		img_start_button = new ImageIcon("img_start_button.png").getImage();
		img_st1 = new ImageIcon("img_start_button1.png").getImage();
		img_st2 = new ImageIcon("img_start_button2.png").getImage();

		// 폭발이미지
		for (int i = 0; i < img_exp.length; i++) {
			String filename = String.format("image_exp/exp_%02d.png", i + 1);
			img_exp[i] = new ImageIcon(filename).getImage();
		}

		img_stage = new ImageIcon("img_stage.png").getImage();
		img_stage_1 = new ImageIcon("img_stage_1.jpg").getImage();
		img_stage_2 = new ImageIcon("img_stage_2.jpg").getImage();
		img_stage_3 = new ImageIcon("img_stage_3.jpg").getImage();
		img_reed = new ImageIcon("img_reed.png").getImage();
		img_reed_1 = new ImageIcon("img_reed_1.png").getImage();
		img_reed_2 = new ImageIcon("img_reed_2.png").getImage();
		img_reed_3 = new ImageIcon("img_reed_3.png").getImage();

		img_end = new ImageIcon("img_end.png").getImage();

		img_dog = new ImageIcon("img_dog.png").getImage();

		img_duck_right = new ImageIcon("img_duck_right.gif").getImage();
		img_duck_left = new ImageIcon("img_duck_left.gif").getImage();
		// img_duck_down_1 = new ImageIcon("img_duck_down_1.png").getImage();
		img_duck_down_2 = new ImageIcon("img_duck_down_2.gif").getImage();

		img_duck1_right = new ImageIcon("img_duck1_right.gif").getImage();
		img_duck1_left = new ImageIcon("img_duck1_left.gif").getImage();
		// img_duck1_down_1 = new ImageIcon("img_duck1_down_1.png").getImage();
		img_duck1_down_2 = new ImageIcon("img_duck1_down_2.gif").getImage();

		img_target = new ImageIcon("img_target.png").getImage();
		img_helper_1 = new ImageIcon("img_helper_1.png").getImage();

		round = new ImageIcon("round.png").getImage();
		round1 = new ImageIcon("round1.png").getImage();
		round2 = new ImageIcon("round2.png").getImage();
		round3 = new ImageIcon("round3.png").getImage();

		// 시작 카운트 이미지
		for (int i = 0; i < img_count1.length; i++) {
			String filename = String.format("img_count1/%d.png", i + 1);
			img_count1[i] = new ImageIcon(filename).getImage();
		}

	}

}
