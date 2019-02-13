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

	// Clip clip; // 사운드 clip
	boolean bMouseOver = false;

	JPanel gamePan;
	Start start = new Start(); // 메인 시작화면, start 버튼있는 곳 --------------
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
	Rectangle rect_start = new Rectangle(380, 510, 522, 192); // 시작화면 스타트버튼 마우스오버

	static int stage_no = 0; // 스테이지 넘김횟수

	static Timer timer;

	public Game() {
		super("Duck Hunter");

		// 초기화
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
	

	// 타이머 ----------------------------------------------------------------------
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
			start.start_target_move(); // 시작화면 타켓 움직임
		}

		target.move();
		explosionManager.move(); // 클릭시 폭발
		duckManager1.duck1_make(); // 났다깔다른오리1
		duckManager.duck_make(); // 났다기본오리
		duckManager1.move(); // 난다깔다른오리1
		duckManager.move(); // 난다기본오리
		helper.move(); // helper 출동
		collisionManager.collision_duck_and_helper(); // 오리와 helper 충돌
		gameover();

	}

	// 마우스 이벤트 -----------------------------------------------------------------
	private void init_mouse_event() {
		MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {

				// 시작화면 마우스오버 스타트버튼
				if (rect_start.contains(e.getPoint())) {
					bMouseOver = true;
				} else {
					bMouseOver = false;
				}

				if (Game.stage_no >= 2) { // 표적 마우스 따라다니는거 좌표 획득
					target.move_with_mouse(e.getX(), e.getY());
					// System.out.printf("%d %d\n",e.getX(), e.getY());
				}

				gamePan.repaint();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Point pt = e.getPoint();

				start_button_click(pt); // start 버튼 클릭 좌표구하기 ---------------

				duck_click(pt); // 오리 클릭시 좌표구하기

				end_click(pt); // end버튼 클릭시

				gamePan.repaint();

			}

		};

		gamePan.addMouseListener(adapter); // pressed
		gamePan.addMouseMotionListener(adapter); // draged, moved 일때는 이거 사용

	}

	// 오리 잡았을때 ----------------------------------------------------------------
	protected void duck_click(Point pt) {

		// 기본 오리 ----------------------------------------------------
		for (int i = 0; i < DuckManager.duck_list.size(); i++) {
			Duck duck = DuckManager.duck_list.get(i);

			if (duck.pos.contains(pt)) {
				if (duck.die == false) { // 잡히기전에만

					int x = pt.x;
					int y = pt.y;
					explosionManager.make_explosion(x, y); // 클릭시 폭발

					Const.Point.game_point++;
//					System.out.printf("점수 %d\n", Const.Point.game_point);
					duck.die = true; // 잡았다
					Const.Point.duck_count++;
					Const.Point.helper_point++;

					// 다음 스테이지 넘어가는 기준
					if (Const.Point.duck_count == 10 && stage_no <= 7) {
						Game.stage_no++;
						count_init(); // 각종 카운트 초기화
						countdown.countdown();
					}

				}

			}

		}

		// 깔다른 오리1 ----------------------------------------------------
		for (int i = 0; i < DuckManager1.duck1_list.size(); i++) {
			Duck1 duck1 = DuckManager1.duck1_list.get(i);

			if (duck1.pos.contains(pt)) {
				if (duck1.die == false) { // 잡히기전에만

					int x = pt.x;
					int y = pt.y;
					explosionManager.make_explosion(x, y); // 클릭시 폭발

					Const.Point.game_point += 2; // 깔다른 오리 점수 높음
//					System.out.printf("점수 %d\n", Const.Point.game_point);
					duck1.die = true; // 잡았다
					Const.Point.duck_count++;
					Const.Point.helper_point++;

					// 다음 스테이지 넘어가는 기준
					if (Const.Point.duck_count == 10 && stage_no <= 7) {
						Game.stage_no++;
						count_init(); // 각종 카운트 초기화
						countdown.countdown();
					}

				}

			}

		}

	}

	private static void count_init() {

		Const.Point.duck_count = 0; // 오리 잡은 수 초기화
		Const.Point.helper_point = 0; // helper 점수 초기화
		helper.pos.x = 2300; // helper 대기장소로 초기화
		DuckManager.duck_list.clear(); // 오리 초기화
		DuckManager1.duck1_list.clear(); // 오리 초기화

	}

	// 메인시작화면에서 start 버튼 클릭시 --------------------------------------------
	protected void start_button_click(Point pt) {

		if (stage_no == 0) {
			// contains 사용하면 버튼 범위 체크
			if (rect_start.contains(pt)) {
				stage_no = 1;
				countdown.countdown(); // 카운트다운으로 넘어감
			}
		}

	}

	// end 버튼 클릭시
	protected void end_click(Point pt) {

		if (stage.pos_end.contains(pt)) {
			timer.stop();
			scorePan = new ScorePan(); // 점수저장화면 // hunter.sql 파일의 sql문 순서대로 실행해야함
		}

	}

	// 게임 다시 시작
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

	// 게임오버
	public static void gameover() {
		if (stage_no == 2 || stage_no == 4 || stage_no == 6) {
			Const.Point.over_time--;

			if (Const.Point.over_time <= 0) {
				Const.Point.over_time = 0;
				timer.stop();
			}
		}
	}

	// 게임판 그리기 -----------------------------------------------------------------
	private void init_gamePan() {
		gamePan = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.clearRect(0, 0, Const.GamePan.GAMEPAN_W, Const.GamePan.GAMEPAN_H);

				// 마우스 커서 안 보이게 ----------- ( 사용시 자꾸 멈추는 문제가 있음 )

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
					start.draw(g); // 메인 시작화면 그리기
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

					// 오리잡은 수에 맞춰서 강아지 그리기
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
	// 게임판 그리기 -----------------------------------------------------------------

	public static void main(String[] args) {
		new Game();

	}

	// 사운드 추가 --------------------------------
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
	// 사운드 추가 --------------------------------

}
