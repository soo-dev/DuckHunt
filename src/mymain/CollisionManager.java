package mymain;

public class CollisionManager {

	Helper helper;
	DuckManager duckManager;
	DuckManager1 duckManager1;
	ExplosionManager explosionManager;

	public CollisionManager() { // 기본생성자

	}

	public CollisionManager(Helper helper, DuckManager duckManager, DuckManager1 duckManager1,
			ExplosionManager explosionManager) {
		super();
		this.helper = helper;
		this.duckManager = duckManager;
		this.duckManager1 = duckManager1;
		this.explosionManager = explosionManager;
	}

	public void collision_duck_and_helper() {

		// 기본오리 충돌
		for (int i = 0; i < duckManager.duck_list.size(); i++) {
			Duck duck = duckManager.duck_list.get(i);
			if (duck.die == false && helper.pos.x < Const.GamePan.GAMEPAN_W) { // 잡히기 전 오리만
				if (helper.pos.intersects(duck.pos)) { // helper 랑 충돌시
					explosionManager.make_explosion(duck.pos.x + duck.pos.width / 2, // 폭발
							duck.pos.y + duck.pos.height / 2);

					Const.Point.game_point++;
					duck.die = true; // 잡았다

				}
			}
		}

		// 깔다른오리 충돌
		for (int i = 0; i < duckManager1.duck1_list.size(); i++) {
			Duck1 duck1 = duckManager1.duck1_list.get(i);
			if (duck1.die == false && helper.pos.x < Const.GamePan.GAMEPAN_W) { // 잡히기 전 오리만
				if (helper.pos.intersects(duck1.pos)) { // helper 랑 충돌시
					explosionManager.make_explosion(duck1.pos.x + duck1.pos.width / 2, // 폭발
							duck1.pos.y + duck1.pos.height / 2);

					Const.Point.game_point++;
					duck1.die = true; // 잡았다

				}
			}
		}

	}

}
