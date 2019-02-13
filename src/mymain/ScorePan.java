package mymain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import dao.PointTBDao;
import vo.PointVo;

public class ScorePan extends JFrame {

	// Hunter.sql 파일의 sql문 순서대로 실행

	// 입,출력필드
	JTextField jtf_no, jtf_name, jtf_gamepoint, jtf_helperpoint, jtf_rank;
	// 작업버튼
	JButton jbt_new, jbt_delete, jbt_prev, jbt_next, jbt_restart, jbt_paused;
	// 조회
	JTable jtb_display;
	// 전체데이터 목록
	List<PointVo> sung_list;
	JPanel scorePan;

	boolean bAdd = false; // 추가 or 수정작업여부
	int current_pos = -1; // 현재 출력되는 데이터 인덱스

	public ScorePan() {
		super("랭킹");

		// 입출력필드 및 작업버튼 초기화
		init_inputs();
		// 조회창 초기화
		init_display();

		// 전체데이터 출력 메소드 생성
		display_total_list();

		// 버튼 비활성 메소드 호출해줘야함
		check_enable_button();

		// 데이터가 존재(sung_list.size() > 0) 하면 current_pos 에 0을 준다
		// 데이터가 존재하면 1번째 데이터를 입력창에 출력
		if (sung_list.size() > 0)
			current_pos = 0;
		// 입력창에 현재데이터 출력
		display_input_data();

		setLocation(750, 200);
		setResizable(false);
		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init_inputs() {
		// TODO Auto-generated method stub
		scorePan = new JPanel(new BorderLayout());
		// 입력창
		JPanel p1 = new JPanel(new GridLayout(5, 1));

		p1.add(jbt_restart = new JButton("게임 다시 시작"));
		p1.add(new JLabel(" Player Name"));
		p1.add(jtf_name = new JTextField());

		p1.add(new JLabel(" hunter.sql 순서대로 실행, ojdbc5 드라이버 로드 필요")).setVisible(true);
		p1.add(jtf_no = new JTextField()).setVisible(false); // 안보이게

		// 출력창
		JPanel p2 = new JPanel(new GridLayout(1, 5));
		p2.add(jbt_prev = new JButton("이전"));
		p2.add(jbt_new = new JButton("저장"));
		p2.add(jbt_delete = new JButton("삭제"));
		p2.add(jbt_next = new JButton("다음"));
		p2.add(jbt_paused = new JButton("재시작"));

		scorePan.add(p1, "Center");
		scorePan.add(p2, "South");

		this.add(scorePan, "Center");

		// 버튼이벤트 초기화
		init_button_event();

		// 읽기전용, JTextField 수정 못 하게
		jtf_no.setEditable(false);

	}

	private void init_button_event() {

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 이벤트를 발생시킨 버튼
				Object evt_src = e.getSource();

				if (evt_src == jbt_new)
					on_new();
				else if (evt_src == jbt_delete)
					on_delete();
				else if (evt_src == jbt_prev)
					on_prev();
				else if (evt_src == jbt_next)
					on_next();
				else if (evt_src == jbt_restart)
					on_restart();
				else if (evt_src == jbt_paused)
					on_paused();

			}
		};

		jbt_new.addActionListener(listener);
		jbt_delete.addActionListener(listener);
		jbt_prev.addActionListener(listener);
		jbt_next.addActionListener(listener);
		jbt_restart.addActionListener(listener);
		jbt_paused.addActionListener(listener);

	}

	// 재시작 버튼
	protected void on_paused() {

		Game.timer.start();
		dispose(); // 창 닫기

	}

	// 게임 다시 시작 버튼
	protected void on_restart() {

		if (JOptionPane.showConfirmDialog(this, "정말 다시 시작하시겠습니까?", "재확인",
			JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
			return;
		
		Game.restart();
		dispose();

	}

	protected void on_new() {

		sung_insert();

	}

	private void sung_insert() {

		String name;
		if (jtf_name.getText().trim().isEmpty()) {
			name = Const.ScorePan.PLAYERNAME;
		} else {
			name = jtf_name.getText().trim();
		}
		int gamepoint = Const.Point.game_point;
		int helperpoint = Const.Point.helper_point;

		PointVo vo = new PointVo();
		vo.setName(name);
		vo.setGamepoint(gamepoint);
		vo.setHelperpoint(helperpoint);

		int res = PointTBDao.getInstance().insert(vo);

		display_total_list();

		current_pos = sung_list.size() - 1;
		display_input_data();

		// 버튼 활성, 비활성
		if (current_pos > 0) {
			jbt_prev.setEnabled(true);
		}
		jbt_new.setEnabled(false);
		jbt_delete.setEnabled(true);

	}

	protected void on_delete() {

		int idx = Integer.parseInt(jtf_no.getText());

		if (JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제확인",
				JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
			return;

		int res = PointTBDao.getInstance().delete(idx);

		display_total_list();

		if (current_pos >= sung_list.size() - 1) {
			current_pos = sung_list.size() - 1;
			clear_input();
		}

		check_enable_button();

		display_input_data();

	}

	protected void on_prev() {

		current_pos--;
		display_input_data();

	}

	protected void on_next() {

		current_pos++;
		display_input_data();

	}

	private void clear_input() {

		jtf_name.setText("");

		// 입력포커스를 이름항목에 가져다 놓는다
		jtf_name.requestFocus();

	}

	private void init_display() {

		jtb_display = new JTable();
		JScrollPane jsp = new JScrollPane(jtb_display);

		jsp.setPreferredSize(new Dimension(400, 200));

		this.add(jsp, "South");

		// 그리드 선택시 마우스 이벤트 처리
		jtb_display.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 선택한 Row 행 index를 구한다
				current_pos = jtb_display.getSelectedRow();
				display_input_data();
			}
		});

	}

	private void display_total_list() {

		// DB내용을 가져온다
		sung_list = PointTBDao.getInstance().selectList();

		// JTable에 가져온 데이터를 셋팅
		jtb_display.setModel(new MyTableModel());

	}

	class MyTableModel extends AbstractTableModel {

		// 타이틀
		String[] title = { "No.", "Name", "Score", "Rank" };

		@Override
		public String getColumnName(int column) {

			return title[column];
		}

		@Override
		public int getColumnCount() { // JTable열수 (Column수)

			return title.length;
		}

		@Override
		public int getRowCount() { // JTable행수 (Row수)

			return sung_list.size(); // DB에서 읽어온 데이터 개수
		}

		@Override // 데이터 채우기
		public Object getValueAt(int row, int col) {

			PointVo vo = sung_list.get(row);
			if (col == 0)
				return vo.getIdx();
			else if (col == 1)
				return vo.getName();
			else if (col == 2)
				return vo.getGamepoint();
			else if (col == 3)
				return vo.getRank();

			return "";
		}

	}

	private void check_enable_button() {

		jbt_prev.setEnabled(bAdd == false && current_pos > 0);
		// current_pos < 마지막 인덱스
		jbt_next.setEnabled(bAdd == false && current_pos < sung_list.size() - 1);
		// 데이터가 있을때만 활성화
		jbt_delete.setEnabled(bAdd == false && current_pos != -1);
		// 저장 후 저장버튼 비활성화
		jbt_new.setEnabled(bAdd == false);

	}

	private void display_input_data() {

		if (current_pos == -1)
			return;
		PointVo vo = sung_list.get(current_pos);
		jtf_no.setText(vo.getIdx() + "");

		// 버튼 비활성 상태 체크
		check_enable_button();

		// 현재위치를 이용한 row 선택
		jtb_display.setRowSelectionInterval(current_pos, current_pos);

	}

	public static void main(String[] args) {
		new ScorePan();

	}

}
