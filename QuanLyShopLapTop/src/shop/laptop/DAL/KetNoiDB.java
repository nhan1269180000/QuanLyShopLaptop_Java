package shop.laptop.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KetNoiDB{
	public static Connection MoKetNoi(String TenCSDL, String user, String pass) {
		// Bước 1. 
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Bước 2. Tạo và giữ kết nối đến một CSDL cụ thể
		// localhost của MySQL trên XAMPP = 3306 và qlsinhvien là Database
		String urlDB ="jdbc:mysql://localhost:3306/" + TenCSDL;
		try 
		{
			// Import java.sql.Connection;
		//	Connection ketNoi = DriverManager.getConnection(urlDB, user, pass);
			return DriverManager.getConnection(urlDB,"root","");
		} 
		// Bắt lỗi khi sai localhost và tên của Database
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
}
