package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
	
	// 0. Driver Loading
	// ojdbc14.jar 드라이버 버젼에서는 반드시 해줘야함. ojdbc5.jar 이상부턴 안 해도 괜찮음
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	// -- single-ton pattern : 1개의 객체로 서비스하자 ( 계속 만들지말고 1개 만들어서 잘 사용 )
	static DBService single = null;
	public static DBService getInstance()
	{
		if (single == null) // null이면 만들어라 : 처음에만 만들고 다음부턴 그냥 기존꺼 return
			single = new DBService();
		return single;
	} // -- 여기까지가 순수한 single-ton pattern
	
	
	private DBService() { // public이 아니라 private 으로 만들면 다른곳에서 객체를 여러번 만드는것을 방지할 수 있다
		
	}
	
	
	
	// 1. Connection 획득
	public Connection getConnection() {
		
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test";
		String pwd = "test";
		
		try {
			
			conn = DriverManager.getConnection(url, user, pwd);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
		// 혹시 오류나면 Build Path에서 라이브러리에 ojbdc5 드라이버 추가
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}