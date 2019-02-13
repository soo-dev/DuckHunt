package mymain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JFrame {

	// Clip clip; // ���� clip
	boolean bMouseOver = false;

	JPanel gamePan;
	Start start = new Start(); // ���� ����ȭ��, start ��ư�ִ� �� --------------
	Explosion explosion = new Explosion();
	static ExplosionManager explosionManager = new ExplosionManager();

	CountDown countdown = new CountDown();
	static Target target = new Target();
	static DuckManager duckManager = new DuckManager();
	static DuckManager1 duckManager1 = new DuckManager1();
	Stage stage = new Stage();
	Reed reed = new Reed();
	ScorePan scorePan;
	static Score score = new Score();
	static Helper helper = new Helper();
	static CollisionManager collisionManager = new CollisionManager(helper, duckManager, duckManager1, explosionManager);
	Rectangle rect_start = new Rectangle(380, 510, 522, 192); // ����ȭ�� ��ŸƮ��ư ���콺����

	static int stage_no = 0; // �������� �ѱ�Ƚ��

	static Timer timer;

	public Game() {
		super("Duck Hunter");

		// �ʱ�ȭ
		init_gamePan();
		init_mouse_event();
		init_timer();

		// Frame -------------------------------------------------------------------
		this.setLocation(308, 0);
		this.setResizable(false);
		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	// Ÿ�̸� ----------------------------------------------------------------------
	private void init_timer() {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				process();
				gamePan.repaint();

			}
		};

		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		
		timer = new Timer(10, listener);
		timer.start();

	}

	protected void process() {

		if (stage_no == 0) {
			start.start_target_move(); // ����ȭ�� Ÿ�� ������
		}

		target.move();
		explosionManager.move(); // Ŭ���� ����
		duckManager1.duck1_make(); // ���ٱ�ٸ�����1
		duckManager.duck_make(); // ���ٱ⺻����
		duckManager1.move(); // ���ٱ�ٸ�����1
		duckManager.move(); // ���ٱ⺻����
		helper.move(); // helper �⵿
		collisionManager.collision_duck_and_helper(); // ������ helper �浹
		gameover();

	}

	// ���콺 �̺�Ʈ -----------------------------------------------------------------
	private void init_mouse_event() {
		MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {

				// ����ȭ�� ���콺���� ��ŸƮ��ư
				if (rect_start.contains(e.getPoint())) {
					bMouseOver = true;
				} else {
					bMouseOver = false;
				}

				if (Game.stage_no >= 2) { // ǥ�� ���콺 ����ٴϴ°� ��ǥ ȹ��
					target.move_with_mouse(e.getX(), e.getY());
					// System.out.printf("%d %d\n",e.getX(), e.getY());
				}

				gamePan.repaint();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Point pt = e.getPoint();

				start_button_click(pt); // start ��ư Ŭ�� ��ǥ���ϱ� ---------------

				duck_click(pt); // ���� Ŭ���� ��ǥ���ϱ�

				end_click(pt); // end��ư Ŭ����

				gamePan.repaint();

			}

		};

		gamePan.addMouseListener(adapter); // pressed
		gamePan.addMouseMotionListener(adapter); // draged, moved �϶��� �̰� ���

	}

	// ���� ������� ----------------------------------------------------------------
	protected void duck_click(Point pt) {

		// �⺻ ���� ----------------------------------------------------
		for (int i = 0; i < DuckManager.duck_list.size(); i++) {
			Duck duck = DuckManager.duck_list.get(i);

			if (duck.pos.contains(pt)) {
				if (duck.die == false) { // ������������

					int x = pt.x;
					int y = pt.y;
					explosionManager.make_explosion(x, y); // Ŭ���� ����

					Const.Point.game_point++;
//					System.out.printf("���� %d\n", Const.Point.game_point);
					duck.die = true; // ��Ҵ�
					Const.Point.duck_count++;
					Const.Point.helper_point++;

					// ���� �������� �Ѿ�� ����
					if (Const.Point.duck_count == 10 && stage_no <= 7) {
						Game.stage_no++;
						count_init(); // ���� ī��Ʈ �ʱ�ȭ
						countdown.countdown();
					}

				}

			}

		}

		// ��ٸ� ����1 ----------------------------------------------------
		for (int i = 0; i < DuckManager1.duck1_list.size(); i++) {
			Duck1 duck1 = DuckManager1.duck1_list.get(i);

			if (duck1.pos.contains(pt)) {
				if (duck1.die == false) { // ������������

					int x = pt.x;
					int y = pt.y;
					explosionManager.make_explosion(x, y); // Ŭ���� ����

					Const.Point.game_point += 2; // ��ٸ� ���� ���� ����
//					System.out.printf("���� %d\n", Const.Point.game_point);
					duck1.die = true; // ��Ҵ�
					Const.Point.duck_count++;
					Const.Point.helper_point++;

					// ���� �������� �Ѿ�� ����
					if (Const.Point.duck_count == 10 && stage_no <= 7) {
						Game.stage_no++;
						count_init(); // ���� ī��Ʈ �ʱ�ȭ
						countdown.countdown();
					}

				}

			}

		}

	}

	private static void count_init() {

		Const.Point.duck_count = 0; // ���� ���� �� �ʱ�ȭ
		Const.Point.helper_point = 0; // helper ���� �ʱ�ȭ
		helper.pos.x = 2300; // helper �����ҷ� �ʱ�ȭ
		DuckManager.duck_list.clear(); // ���� �ʱ�ȭ
		DuckManager1.duck1_list.clear(); // ���� �ʱ�ȭ

	}

	// ���ν���ȭ�鿡�� start ��ư Ŭ���� --------------------------------------------
	protected void start_button_click(Point pt) {

		if (stage_no == 0) {
			// contains ����ϸ� ��ư ���� üũ
			if (rect_start.contains(pt)) {
				stage_no = 1;
				countdown.countdown(); // ī��Ʈ�ٿ����� �Ѿ
			}
		}

	}

	// end ��ư Ŭ����
	protected void end_click(Point pt) {

		if (stage.pos_end.contains(pt)) {
			timer.stop();
			scorePan = new ScorePan(); // ��������ȭ�� // hunter.sql ������ sql�� ������� �����ؾ���
		}

	}

	// ���� �ٽ� ����
	public static void restart() {

		stage_no = 0;
		Const.Point.game_point = 0;
		Const.Point.over_time = Const.Point.over_time_init;
		count_init();
		DuckManager.duck_list.clear();
		DuckManager1.duck1_list.clear();
		timer.start();

		new JPanel();

	}

	// ���ӿ���
	public static void gameover() {
		if (stage_no == 2 || stage_no == 4 || stage_no == 6) {
			Const.Point.over_time--;

			if (Const.Point.over_time <= 0) {
				Const.Point.over_time = 0;
				timer.stop();
			}
		}
	}

	// ������ �׸��� -----------------------------------------------------------------
	private void init_gamePan() {
		gamePan = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);

				// ���콺 Ŀ�� �� ���̰� ----------- ( ���� �ڲ� ���ߴ� ������ ���� )

//					Toolkit tk = Toolkit.getDefaultToolkit();
//					// Creates a cursor from an invalid image.
//					Cursor invisCursor = tk.createCustomCursor(tk.createImage(""), new Point(), null);
//					if (Game.stage_no == 0) {
//						setCursor(Cursor.getDefaultCursor());
//					} else {
//						setCursor(invisCursor);
//					}
				
				// ---------------------------------

				if (stage_no == 0) {
					start.draw(g); // ���� ����ȭ�� �׸���
					g.drawImage(bMouseOver ? GameImages.img_st2 : GameImages.img_st1, 380, 510, this);
				}

				if (stage_no == 1 || stage_no == 3 || stage_no == 5 || stage_no >= 7) {
					countdown.draw(g);
				}

				if (stage_no == 2 || stage_no == 4 || stage_no == 6 || stage_no >= 8) {

					g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);
					stage.draw(g);
					duckManager1.draw(g);
					duckManager.draw(g);
					reed.draw(g);
					explosionManager.draw(g);
					helper.draw(g);
					g.drawImage(GameImages.img_end, stage.pos_end.x, stage.pos_end.y, null);
					target.draw(g);

					// �������� ���� ���缭 ������ �׸���
					if (stage_no < 8)
						for (int i = 0; i < Const.Point.duck_count; i++) {
							g.drawImage(GameImages.img_dog, 200 + i * 90, 700, null);
						}

					score.draw(g);

				}

			}
		};

		gamePan.setPreferredSize(new Dimension(Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H));
		this.add(gamePan);

	}
	// ������ �׸��� -----------------------------------------------------------------

	public static void main(String[] args) {
		new Game();

	}

	// ���� �߰� --------------------------------
	// public void main_Sound(String file) {
	// try {
	// AudioInputStream ais = AudioSystem.getAudioInputStream(new
	// BufferedInputStream(new FileInputStream(file)));
	// clip = AudioSystem.getClip();
	// clip.open(ais);
	// clip.start();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void stop_Main_Sound() {
	// clip.stop();
	// clip.close();
	// }
	// ���� �߰� --------------------------------

}
