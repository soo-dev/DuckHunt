package mymain;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Item { // 2. abstract class 가 되어야 한다

	// 위치 + 크기
	public Rectangle pos = new Rectangle();
	public Rectangle pos_back = new Rectangle(); // 타이틀이미지 크기 및 위치
	public Rectangle pos_title = new Rectangle(); // 타이틀이미지 크기 및 위치
	public Rectangle pos_button = new Rectangle(); // start 버튼 크기 및 위치
	public Rectangle pos_end = new Rectangle(); // start 버튼 크기 및 위치

	// 행위속성
	public abstract boolean move(); // 1. abstract 메소드를 가지면

	public abstract void draw(Graphics g);

}
