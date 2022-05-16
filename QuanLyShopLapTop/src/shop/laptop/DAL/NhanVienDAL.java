package shop.laptop.DAL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

import shop.laptop.DTO.GlobalData;
import shop.laptop.DTO.NhanVienDTO;

public class NhanVienDAL {
	// Truy cập dữ liệu, kết nối, thêm - xoá - sửa nhân viên
	Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
	
	// Thêm mới nhân viên, và trả về giá trị True, nếu thêm thành công
	public boolean insert(NhanVienDTO nv) {
		String sqlInsertNV = "INSERT INTO nhanvien VALUES (?, ?, ?, ?, ?, ?)";
		try
		{
			PreparedStatement ps = ketnoiDB.prepareStatement(sqlInsertNV);
			//
			ps.setString(1, nv.getMaNV()); // Cột số 0
			ps.setString(2, nv.getHoTenNV()); // Cột số 1
			ps.setInt(3, nv.getSDT());
			ps.setString(4, nv.getTenDN());
			ps.setString(5, nv.getMatKhau());
			ps.setString(6, nv.getQuyen());
			//
			boolean themDuoc = ps.execute();
			return themDuoc;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(NhanVienDTO nv) {
		String sqlUpdateNV = "UPDATE nhanvien SET HoTen = ?, SDT = ?, TenDN = ?, MatKhau = ?, Quyen = ?  WHERE MaNV = ?";
		PreparedStatement ps;
		try {
			ps = ketnoiDB.prepareStatement(sqlUpdateNV);
			ps.setString(1, nv.getHoTenNV()); 
			ps.setInt(2, nv.getSDT());
			ps.setString(3, nv.getTenDN());
			ps.setString(4, nv.getMatKhau());
			ps.setString(5, nv.getQuyen());
			ps.setString(6, nv.getMaNV()); 
			//
			boolean suaDuoc = ps.execute();
			return suaDuoc;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean delete(String maNV) {
		String sqlDeleteNV = "DELETE FROM nhanvien WHERE MaNV ='" + maNV +"'";
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			boolean xoaduoc = stmt.execute(sqlDeleteNV);
			return xoaduoc;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public String selectHoTen(String maNV) {
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			String sqlSelectHoTen = "SELECT HoTen FROM nhanvien WHERE MaNV ='" + maNV +"'";
			ResultSet rs = stmt.executeQuery(sqlSelectHoTen);
			rs.first();
			String hoTen = rs.getString("HoTen");
			return hoTen;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String selectMaNV(String hoTen) {
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			String sqlSelectMaNV = "SELECT MaNV FROM nhanvien WHERE HoTen ='" + hoTen +"'";
			ResultSet rs = stmt.executeQuery(sqlSelectMaNV);
			rs.first();
			String maNV = rs.getString("MaNV");
			return maNV;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<NhanVienDTO> selectNV() {
		ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			String sqlSelectNV = "SELECT MaNV, HoTen FROM nhanvien";
			ResultSet rs = stmt.executeQuery(sqlSelectNV);
			while(rs.next()) {
				String maNV = rs.getString("MaNV");
				String hoTen = rs.getString("HoTen");
				NhanVienDTO nv = new NhanVienDTO(maNV, hoTen);
				dsNhanVien.add(nv);
			}
			return dsNhanVien;
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	// Lấy danh sách NhanVien
	public ArrayList<NhanVienDTO> selectAll() 
	{
		ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
		Statement stmt;
		try 
		{
			stmt = ketnoiDB.createStatement();
			String sqlSelectNhanVien = "SELECT * FROM nhanvien";
			ResultSet tblNhanVien = stmt.executeQuery(sqlSelectNhanVien);
			// Xử lý tblNhanVien --> dsNhanVien
			while(tblNhanVien.next()) // Trong khi mà còn next được (còn dòng)
			{
				// Xử lý mỗi dòng
				// Lấy dữ liệu từng cột
				String maNV = tblNhanVien.getString("MaNV"); // Cột số 0
				String hoTen = tblNhanVien.getString("HoTen"); // Cột số 1
				int sdt = tblNhanVien.getInt("SDT");
				String tenDN = tblNhanVien.getString("TenDN");
				String matKhau = tblNhanVien.getString("MatKhau");
				String quyen = tblNhanVien.getString("Quyen");
				NhanVienDTO nv = new NhanVienDTO(maNV, hoTen, sdt, tenDN, matKhau, quyen);
				dsNhanVien.add(nv);
			}
			return dsNhanVien;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	// Tìm kiếm theo Mã
	public ArrayList<NhanVienDTO> findByID(String MaNV){
		String sqlFindByID = "SELECT * FROM nhanvien WHERE MaNV ='" + MaNV +"'";
		Statement stmt;
	//	NhanVienDTO nhanVien = new NhanVienDTO();
		ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
		try {
			stmt = ketnoiDB.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFindByID);
			while(rs.next()) {
				String maNV = rs.getString("MaNV"); // Cột số 0
				String hoTen = rs.getString("HoTen"); // Cột số 1
				int sdt = rs.getInt("SDT");
				String tenDN = rs.getString("TenDN");
				String matKhau = rs.getString("MatKhau");
				String quyen = rs.getString("Quyen");
				NhanVienDTO nv = new NhanVienDTO(maNV, hoTen, sdt, tenDN, matKhau, quyen);
				dsNhanVien.add(nv);
			}
			return dsNhanVien;
		}
		
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// Tìm kiếm theo Tên
	ArrayList<NhanVienDTO> findByName(String tenNhanVien) 
	{
		ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
		
		return dsNhanVien;
	}
	
	// Kiểm tra đăng nhập
	public boolean checkLogin(String tenDN, String matKhau) {
		String sqlCheck = "Select * from nhanvien where TenDN = '" + tenDN + "' And MatKhau = '" + matKhau + "'";
		try
		{
			Statement cmd = ketnoiDB.createStatement();
			// Câu truy vấn thì phải executeQuery
			ResultSet rs = cmd.executeQuery(sqlCheck);
			if(rs.next())	return true; // Login Success
			else	return false; // Login Fail
			
		
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
}}
