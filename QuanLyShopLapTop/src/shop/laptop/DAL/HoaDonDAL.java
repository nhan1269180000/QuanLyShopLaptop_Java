package shop.laptop.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import shop.laptop.DTO.HoaDonDTO;

public class HoaDonDAL {
		Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
		
		public boolean insert(HoaDonDTO hd) {
			String sqlInsertHD = "INSERT INTO hoaDon VALUES (?, ?, ?, ?, ?, ?, ?)";
			try
			{
				PreparedStatement ps = ketnoiDB.prepareStatement(sqlInsertHD);
				//
				ps.setString(1, hd.getMaHD()); // Cột số 0
				ps.setString(2, hd.getNgayDat());
				ps.setString(3, hd.getNgayGiao());
				ps.setString(4, hd.getDiaChiGiao());
				ps.setString(5, hd.getMaKH());
				ps.setString(6, hd.getMaNV());
				ps.setString(7, hd.getTinhTrang());
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
		
		public boolean update(HoaDonDTO hd) {
			String sqlUpdateHD = "UPDATE hoaDon SET NgayDat = ?, NgayGiao = ?, DiaChiGiao = ?, MaKH = ?, MaNV = ?, TinhTrang = ? WHERE MaHD = ?";
			PreparedStatement ps;
			try {
				ps = ketnoiDB.prepareStatement(sqlUpdateHD);
				ps.setString(1, hd.getNgayDat());
				ps.setString(2, hd.getNgayGiao());
				ps.setString(3, hd.getDiaChiGiao());
				ps.setString(4, hd.getMaKH());
				ps.setString(5, hd.getMaNV());
				ps.setString(6, hd.getTinhTrang());
				ps.setString(7, hd.getMaHD());
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
		
		public boolean delete(String maHD) {
			String sqlDeleteHD = "DELETE FROM hoaDon WHERE MaHD ='" + maHD +"'";
			Statement stmt;
			try {
				stmt = ketnoiDB.createStatement();
				boolean xoaduoc = stmt.execute(sqlDeleteHD);
				return xoaduoc;
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		// Lấy danh sách hoaDon
		public ArrayList<HoaDonDTO> selectAll() 
		{
			ArrayList<HoaDonDTO> dsHoaDon = new ArrayList<HoaDonDTO>();
			Statement stmt;
			try 
			{
				stmt = ketnoiDB.createStatement();
				String sqlSelecthoaDon = "SELECT * FROM hoaDon";
				ResultSet tblHoaDon = stmt.executeQuery(sqlSelecthoaDon);
				// Xử lý tblHoaDon --> dsHoaDon
				while(tblHoaDon.next()) // Trong khi mà còn next được (còn dòng)
				{
					// Xử lý mỗi dòng
					// Lấy dữ liệu từng cột
					String maHD = tblHoaDon.getString("MaHD");
					String ngayDat = tblHoaDon.getString("NgayDat");
					String ngayGiao = tblHoaDon.getString("NgayGiao");
					String diaChiGiao = tblHoaDon.getString("DiaChiGiao");
					String maKH = tblHoaDon.getString("MaKH");
					String maNV = tblHoaDon.getString("MaNV");
					String tinhTrang = tblHoaDon.getString("TinhTrang");
					
					
					HoaDonDTO hd = new HoaDonDTO(maHD, ngayDat, ngayGiao, diaChiGiao, maKH, maNV, tinhTrang);
					dsHoaDon.add(hd);
				}
				return dsHoaDon;
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		// Tìm kiếm theo Mã
		public ArrayList<HoaDonDTO> findByID(String MaHD){
			String sqlFindByID = "SELECT * FROM hoadon WHERE maHD ='" + MaHD +"'";
			Statement stmt;
		//	HoaDonDTO hoaDon = new HoaDonDTO();
			ArrayList<HoaDonDTO> dsHoaDon = new ArrayList<HoaDonDTO>();
			try {
				stmt = ketnoiDB.createStatement();
				ResultSet tblHoaDon = stmt.executeQuery(sqlFindByID);
				while(tblHoaDon.next()) {
					String maHD = tblHoaDon.getString("MaHD");
					String ngayDat = tblHoaDon.getString("NgayDat");
					String ngayGiao = tblHoaDon.getString("NgayGiao");
					String diaChiGiao = tblHoaDon.getString("DiaChiGiao");
					String maKH = tblHoaDon.getString("MaKH");
					String maNV = tblHoaDon.getString("MaNV");
					String tinhTrang = tblHoaDon.getString("TinhTrang");
					HoaDonDTO hd = new HoaDonDTO(maHD, ngayDat, ngayGiao, diaChiGiao, maKH, maNV, tinhTrang);
					dsHoaDon.add(hd);
				}
				return dsHoaDon;
			}
			
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
}
