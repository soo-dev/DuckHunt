package mymain;

import java.awt.Graphics;
import java.util.Random;

public class Duck extends Item {

	Random rand = new Random();

	boolean bRight = rand.nextBoolean(); // true false �����ϰ�
	boolean pass = rand.nextBoolean(); // ������ȯ�ϴ� ����, �׳� ���󰡴� ����
	boolean bDown; // �Ʒ����̵�����
	boolean die;
	int speed;
	int down = 0;

	public Duck() {

		// ���� ũ��
		// ���������� ����
		if (bRight) {
			pos.width = 80; // GameImages.img_duck_right.getWidth(null);
			pos.height = 100; // GameImages.img_duck_right.getHeight(null);
		}
		// �������� ����
		if (bRight == false) {
			pos.width = 80; // GameImages.img_duck_left.getWidth(null);
			pos.height = 100; // GameImages.img_duck_left.getHeight(null);
		}

	}

	@Override
	public boolean move() {
		// TODO Auto-generated method stub

		// �¿��̵�
		if (bRight && die == false) { // �������� ��������
			pos.x += speed;
		} else if (bRight == false && die == false) {
			pos.x -= speed;
		}

		// if �����ʺ��� ������� else ���ʺ��� �������
		if (pos.x + pos.width > Const.GamePan.GAMEPAN_W && pass == false) {
			pos.x = Const.GamePan.GAMEPAN_W - pos.width; // ������ ��Ȯ�� ��ǥ����
			bRight = false; // �̵�������ȯ
		} else if (pos.x < 0 && pass == false) {
			pos.x = 0; // ������ ��Ȯ�� ��ǥ����
			bRight = true;
		}

		// �����̵�
		if (bDown) {
			pos.y += pos.y;
		} else
			pos.y -= speed / 5; // ���ఢ������

		if (pos.y + pos.height > Const.GamePan.GAMEPAN_H) {
			bDown = false;
		} else if (pos.y < 0) {
			bDown = false; // �ϴ÷� �������
		}

		// ��������
		if (die) {
			pos.y += (8 + speed); // �������� �ӵ�
			// pos.x = pos.x;
		}

		// ������ ȭ�� �ȿ� �ִ��� Ȯ���ϰ�, ������ false�� return
		return ( pos.y > -100 && pos.y < Const.GamePan.GAMEPAN_H  && pos.x > -100 && pos.x < Const.GamePan.GAMEPAN_W );
	}

	@Override
	public void draw(Graphics g) {

		// ���������� ����
		if (bRight && die == false) {
			g.drawImage(GameImages.img_duck_right, pos.x, pos.y, null);
		}
		// �������� ����
		if (bRight == false && die == false) {
			g.drawImage(GameImages.img_duck_left, pos.x, pos.y, null);
		}

		// ��������
		if (die) {
			g.drawImage(GameImages.img_duck_down_2, pos.x, pos.y, null);
		}

		// // ������ �´��� üũ
		// g.setColor(Color.RED);
		// g.drawRect(pos.x, pos.y,pos.width,pos.height);

	}

}
