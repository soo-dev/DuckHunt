package mymain;

import java.util.Random;

public class Const {

	static Random rand = new Random();

	public static class GamePan {

		public static final int GAMEPAN_W = 1280;
		public static final int GAMEPAN_H = 768;

	}

	public static class ScorePan {

		public static final int SCOREPAN_W = 1280;
		public static final int SCOREPAN_H = 250;
		public static final String PLAYERNAME = "Player " + rand.nextInt(10000) + 1;

	}

	public static class Point {

		public static int game_point = 0;
		public static int helper_point = 0;
		public static int duck_count = 0;
		public static int over_time = 0;
		public static final int over_time_init = 1000; // ���ӿ��� �ð� ����

	}
	
	// ���� ���̵� ����
	public static class Level {
		
		public static final int MAKE_DUCK_INTERVAL = 40; // �⺻���� ���� �ӵ� // �������� ���� ����
		public static final int MAKE_DUCK_INTERVAL1 = 60; // ��ٸ�����1 ���� �ӵ�
		public static final int speed = 2; // ���� ���� �ӵ�
		public static final int helper_go = 5;
	}

}
