package mymain;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Item { // 2. abstract class �� �Ǿ�� �Ѵ�

	// ��ġ + ũ��
	public Rectangle pos = new Rectangle();
	public Rectangle pos_back = new Rectangle(); // Ÿ��Ʋ�̹��� ũ�� �� ��ġ
	public Rectangle pos_title = new Rectangle(); // Ÿ��Ʋ�̹��� ũ�� �� ��ġ
	public Rectangle pos_button = new Rectangle(); // start ��ư ũ�� �� ��ġ
	public Rectangle pos_end = new Rectangle(); // start ��ư ũ�� �� ��ġ

	// �����Ӽ�
	public abstract boolean move(); // 1. abstract �޼ҵ带 ������

	public abstract void draw(Graphics g);

}
