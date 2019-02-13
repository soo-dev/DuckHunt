package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
	
	// 0. Driver Loading
	// ojdbc14.jar ����̹� ���������� �ݵ�� �������. ojdbc5.jar �̻���� �� �ص� ������
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	// -- single-ton pattern : 1���� ��ü�� �������� ( ��� ���������� 1�� ���� �� ��� )
	static DBService single = null;
	public static DBService getInstance()
	{
		if (single == null) // null�̸� ������ : ó������ ����� �������� �׳� ������ return
			single = new DBService();
		return single;
	} // -- ��������� ������ single-ton pattern
	
	
	private DBService() { // public�� �ƴ϶� private ���� ����� �ٸ������� ��ü�� ������ ����°��� ������ �� �ִ�
		
	}
	
	
	
	// 1. Connection ȹ��
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
		
		// Ȥ�� �������� Build Path���� ���̺귯���� ojbdc5 ����̹� �߰�
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}