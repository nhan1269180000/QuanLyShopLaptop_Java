package shop.laptop.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shop.laptop.DTO.KhachHangDTO;
import shop.laptop.DTO.NhanVienDTO;
import shop.laptop.DTO.KhachHangDTO;

public class KhachHangDAL {
	// Truy cập dữ liệu, kết nối, thêm - xoá - sửa nhân viên
		Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
		
		// Thêm mới nhân viên, và trả về giá trị True, nếu thêm thành công
		public boolean insert(KhachHangDTO kh) {
			String sqlInsertKH = "INSERT INTO khachhang VALUES (?, ?, ?, ?, ?, ?, ?)";
			try
			{
				PreparedStatement ps = ketnoiDB.prepareStatement(sqlInsertKH);
				//
				ps.setString(1, kh.getMaKH()); // Cột số 0
				ps.setString(2, kh.getHoTenKH()); // Cột số 1
				ps.setInt(3, kh.getSDT());
				ps.setString(4, kh.getDiaChi());
				ps.setString(5, kh.getTenDN());
				ps.setString(6, kh.getMatKhau());
				ps.setString(7, kh.getQuyen());
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
		
		public boolean update(KhachHangDTO kh) {
			String sqlUpdateKH = "UPDATE khachhang SET HoTen = ?, SDT = ?, DiaChi = ?, TenDN = ?, MatKhau = ?, Quyen = ?  WHERE MaKH = ?";
			PreparedStatement ps;
			try {
				ps = ketnoiDB.prepareStatement(sqlUpdateKH);
				ps.setString(1, kh.getHoTenKH()); 
				ps.setInt(2, kh.getSDT());
				ps.setString(3, kh.getDiaChi());
				ps.setString(4, kh.getTenDN());
				ps.setString(5, kh.getMatKhau());
				ps.setString(6, kh.getQuyen());
				ps.setString(7, kh.getMaKH()); 
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
		
		public boolean delete(String makh) {
			String sqlDeleteKH = "DELETE FROM khachhang WHERE MaKH ='" + makh +"'";
			Statement stmt;
			try {
				stmt = ketnoiDB.createStatement();
				boolean xoaduoc = stmt.execute(sqlDeleteKH);
				return xoaduoc;
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		public String selectHoTen(String maKH) {
			Statement stmt;
			try {
				stmt = ketnoiDB.createStatement();
				String sqlSelectHoTen = "SELECT HoTen FROM khachhang WHERE MaKH ='" + maKH +"'";
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
		
		public String selectMaKH(String hoTen) {
			Statement stmt;
			try {
				stmt = ketnoiDB.createStatement();
				String sqlSelectMaKH = "SELECT MaKH FROM khachhang WHERE HoTen ='" + hoTen +"'";
				ResultSet rs = stmt.executeQuery(sqlSelectMaKH);
				rs.first();
				String maKH = rs.getString("MaKH");
				return maKH;
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
		}
		
		public ArrayList<KhachHangDTO> selectKH() {
			ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
			Statement stmt;
			try {
				stmt = ketnoiDB.createStatement();
				String sqlSelectKH = "SELECT MaKH, HoTen FROM khachhang";
				ResultSet rs = stmt.executeQuery(sqlSelectKH);
				while(rs.next()) {
					String maKH = rs.getString("MaKH");
					String hoTen = rs.getString("HoTen");
					KhachHangDTO kh = new KhachHangDTO(maKH, hoTen);
					dsKhachHang.add(kh);
				}
				return dsKhachHang;
			}
			catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
			
		}
		
		// Lấy danh sách khachhang
		public ArrayList<KhachHangDTO> selectAll() 
		{
			ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
			Statement stmt;
			try 
			{
				stmt = ketnoiDB.createStatement();
				String sqlSelectKhachHang = "SELECT * FROM khachhang";
				ResultSet tblKhachHang = stmt.executeQuery(sqlSelectKhachHang);
				// Xử lý tblKhachHang --> dsKhachHang
				while(tblKhachHang.next()) // Trong khi mà còn next được (còn dòng)
				{
					// Xử lý mỗi dòng
					// Lấy dữ liệu từng cột
					String makh = tblKhachHang.getString("Makh"); // Cột số 0
					String hoTen = tblKhachHang.getString("HoTen"); // Cột số 1
					int sdt = tblKhachHang.getInt("SDT");
					String diaChi = tblKhachHang.getString("DiaChi");
					String tenDN = tblKhachHang.getString("TenDN");
					String matKhau = tblKhachHang.getString("MatKhau");
					String quyen = tblKhachHang.getString("Quyen");
					KhachHangDTO kh = new KhachHangDTO(makh, hoTen, sdt, diaChi, tenDN, matKhau, quyen);
					dsKhachHang.add(kh);
				}
				return dsKhachHang;
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		// Tìm kiếm theo Mã
		public ArrayList<KhachHangDTO> findByID(String Makh){
			String sqlFindByID = "SELECT * FROM khachhang WHERE MaKH ='" + Makh +"'";
			Statement stmt;
		//	KhachHangDTO khachhang = new KhachHangDTO();
			ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
			try {
				stmt = ketnoiDB.createStatement();
				ResultSet rs = stmt.executeQuery(sqlFindByID);
				while(rs.next()) {
					String makh = rs.getString("Makh"); // Cột số 0
					String hoTen = rs.getString("HoTen"); // Cột số 1
					int sdt = rs.getInt("SDT");
					String diaChi = rs.getString("DiaChi");
					String tenDN = rs.getString("TenDN");
					String matKhau = rs.getString("MatKhau");
					String quyen = rs.getString("Quyen");
					KhachHangDTO kh = new KhachHangDTO(makh, hoTen, sdt, diaChi, tenDN, matKhau, quyen);
					dsKhachHang.add(kh);
				}
				return dsKhachHang;
			}
			
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		// Tìm kiếm theo Tên
		ArrayList<KhachHangDTO> findByName(String tenKhachHang) 
		{
			ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
			
			return dsKhachHang;
		}
		
		// Kiểm tra đăng nhập
		public boolean checkLogin(String tenDN, String matKhau) {
			String sqlCheck = "Select * from khachhang where TenDN = '" + tenDN + "' And MatKhau = '" + matKhau + "'";
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
