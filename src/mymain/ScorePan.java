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

	// Hunter.sql ������ sql�� ������� ����

	// ��,����ʵ�
	JTextField jtf_no, jtf_name, jtf_gamepoint, jtf_helperpoint, jtf_rank;
	// �۾���ư
	JButton jbt_new, jbt_delete, jbt_prev, jbt_next, jbt_restart, jbt_paused;
	// ��ȸ
	JTable jtb_display;
	// ��ü������ ���
	List<PointVo> sung_list;
	JPanel scorePan;

	boolean bAdd = false; // �߰� or �����۾�����
	int current_pos = -1; // ���� ��µǴ� ������ �ε���

	public ScorePan() {
		super("��ŷ");

		// ������ʵ� �� �۾���ư �ʱ�ȭ
		init_inputs();
		// ��ȸâ �ʱ�ȭ
		init_display();

		// ��ü������ ��� �޼ҵ� ����
		display_total_list();

		// ��ư ��Ȱ�� �޼ҵ� ȣ���������
		check_enable_button();

		// �����Ͱ� ����(sung_list.size() > 0) �ϸ� current_pos �� 0�� �ش�
		// �����Ͱ� �����ϸ� 1��° �����͸� �Է�â�� ���
		if (sung_list.size() > 0)
			current_pos = 0;
		// �Է�â�� ���絥���� ���
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
		// �Է�â
		JPanel p1 = new JPanel(new GridLayout(5, 1));

		p1.add(jbt_restart = new JButton("���� �ٽ� ����"));
		p1.add(new JLabel(" Player Name"));
		p1.add(jtf_name = new JTextField());

		p1.add(new JLabel(" hunter.sql ������� ����, ojdbc5 ����̹� �ε� �ʿ�")).setVisible(true);
		p1.add(jtf_no = new JTextField()).setVisible(false); // �Ⱥ��̰�

		// ���â
		JPanel p2 = new JPanel(new GridLayout(1, 5));
		p2.add(jbt_prev = new JButton("����"));
		p2.add(jbt_new = new JButton("����"));
		p2.add(jbt_delete = new JButton("����"));
		p2.add(jbt_next = new JButton("����"));
		p2.add(jbt_paused = new JButton("�����"));

		scorePan.add(p1, "Center");
		scorePan.add(p2, "South");

		this.add(scorePan, "Center");

		// ��ư�̺�Ʈ �ʱ�ȭ
		init_button_event();

		// �б�����, JTextField ���� �� �ϰ�
		jtf_no.setEditable(false);

	}

	private void init_button_event() {

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// �̺�Ʈ�� �߻���Ų ��ư
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

	// ����� ��ư
	protected void on_paused() {

		Game.timer.start();
		dispose(); // â �ݱ�

	}

	// ���� �ٽ� ���� ��ư
	protected void on_restart() {

		if (JOptionPane.showConfirmDialog(this, "���� �ٽ� �����Ͻðڽ��ϱ�?", "��Ȯ��",
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

		// ��ư Ȱ��, ��Ȱ��
		if (current_pos > 0) {
			jbt_prev.setEnabled(true);
		}
		jbt_new.setEnabled(false);
		jbt_delete.setEnabled(true);

	}

	protected void on_delete() {

		int idx = Integer.parseInt(jtf_no.getText());

		if (JOptionPane.showConfirmDialog(this, "���� �����Ͻðڽ��ϱ�?", "����Ȯ��",
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

		// �Է���Ŀ���� �̸��׸� ������ ���´�
		jtf_name.requestFocus();

	}

	private void init_display() {

		jtb_display = new JTable();
		JScrollPane jsp = new JScrollPane(jtb_display);

		jsp.setPreferredSize(new Dimension(400, 200));

		this.add(jsp, "South");

		// �׸��� ���ý� ���콺 �̺�Ʈ ó��
		jtb_display.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// ������ Row �� index�� ���Ѵ�
				current_pos = jtb_display.getSelectedRow();
				display_input_data();
			}
		});

	}

	private void display_total_list() {

		// DB������ �����´�
		sung_list = PointTBDao.getInstance().selectList();

		// JTable�� ������ �����͸� ����
		jtb_display.setModel(new MyTableModel());

	}

	class MyTableModel extends AbstractTableModel {

		// Ÿ��Ʋ
		String[] title = { "No.", "Name", "Score", "Rank" };

		@Override
		public String getColumnName(int column) {

			return title[column];
		}

		@Override
		public int getColumnCount() { // JTable���� (Column��)

			return title.length;
		}

		@Override
		public int getRowCount() { // JTable��� (Row��)

			return sung_list.size(); // DB���� �о�� ������ ����
		}

		@Override // ������ ä���
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
		// current_pos < ������ �ε���
		jbt_next.setEnabled(bAdd == false && current_pos < sung_list.size() - 1);
		// �����Ͱ� �������� Ȱ��ȭ
		jbt_delete.setEnabled(bAdd == false && current_pos != -1);
		// ���� �� �����ư ��Ȱ��ȭ
		jbt_new.setEnabled(bAdd == false);

	}

	private void display_input_data() {

		if (current_pos == -1)
			return;
		PointVo vo = sung_list.get(current_pos);
		jtf_no.setText(vo.getIdx() + "");

		// ��ư ��Ȱ�� ���� üũ
		check_enable_button();

		// ������ġ�� �̿��� row ����
		jtb_display.setRowSelectionInterval(current_pos, current_pos);

	}

	public static void main(String[] args) {
		new ScorePan();

	}

}
