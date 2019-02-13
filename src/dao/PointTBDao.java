package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.PointVo;

public class PointTBDao {

	static PointTBDao single = null;

	public static PointTBDao getInstance() {
		if (single == null)
			single = new PointTBDao();
		return single;
	}

	public PointTBDao() {

	}

	public List<PointVo> selectList() {

		List<PointVo> list = new ArrayList<PointVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from hunter_view order by rank";

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int idx = rs.getInt("idx");
				String name = rs.getString("name");
				int gamepoint = rs.getInt("gamepoint");
				int helperpoint = rs.getInt("helperpoint");
				int rank = rs.getInt("rank");

				PointVo vo = new PointVo();
				vo.setIdx(idx);
				vo.setName(name);
				vo.setGamepoint(gamepoint);
				vo.setHelperpoint(helperpoint);
				vo.setRank(rank);

				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public int insert(PointVo vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into hunter values(seq_hunter_idx.nextVal, ?, ?, ?)";

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getGamepoint());
			pstmt.setInt(3, vo.getHelperpoint());
			res = pstmt.executeUpdate();

		} catch (Exception e) {
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return res;
	}

	public int delete(int idx) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from hunter where idx = ?";

		try {

			conn = DBService.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt == null)
					pstmt.close();
				if (conn == null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return res;
	}

}
