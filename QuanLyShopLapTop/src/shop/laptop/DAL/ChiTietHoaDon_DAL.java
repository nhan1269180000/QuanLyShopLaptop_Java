package shop.laptop.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shop.laptop.DTO.ChiTietHD_DTO;

public class ChiTietHoaDon_DAL {
			Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
			
			public boolean insert(ChiTietHD_DTO cthd) {
				String sqlInsertCTHD = "INSERT INTO chitiethoadon VALUES (?, ?, ?, ?)";
				try
				{
					PreparedStatement ps = ketnoiDB.prepareStatement(sqlInsertCTHD);
					//
					ps.setString(1, cthd.getMaHD());
					ps.setString(2, cthd.getMaSP());
					ps.setInt(3, cthd.getDonGia());
					ps.setInt(4, cthd.getSoLuong());
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
			
			public boolean update(ChiTietHD_DTO cthd) {
				// Khoá chính MaHD với MaSP
				String sqlUpdateCTHD = "UPDATE chitiethoadon SET DonGia = ?, SoLuong = ? WHERE MaHD = ? And MaSP = ?";
				PreparedStatement ps;
				try {
					ps = ketnoiDB.prepareStatement(sqlUpdateCTHD);
					ps.setInt(1, cthd.getDonGia());
					ps.setInt(2, cthd.getSoLuong());
					ps.setString(3, cthd.getMaHD());
					ps.setString(4, cthd.getMaSP());
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
			
			public boolean delete(String maHD, String maSP) {
				String sqlDeleteCTHD = "DELETE FROM chitiethoadon WHERE MaHD ='" + maHD +"' AND MaSP ='" + maSP +"' " ;
				Statement stmt;
				try {
					stmt = ketnoiDB.createStatement();
					boolean xoaduoc = stmt.execute(sqlDeleteCTHD);
					return xoaduoc;
				}
				catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			}
			
			public ArrayList<ChiTietHD_DTO> selectAll() 
			{
				ArrayList<ChiTietHD_DTO> dsChiTietHoaDon = new ArrayList<ChiTietHD_DTO>();
				Statement stmt;
				try 
				{
					stmt = ketnoiDB.createStatement();
					String sqlSelectCTHD = "SELECT * FROM chitiethoadon";
					ResultSet rs = stmt.executeQuery(sqlSelectCTHD);
					while(rs.next()) // Trong khi mà còn next được (còn dòng)
					{
						// Xử lý mỗi dòng
						// Lấy dữ liệu từng cột
						String maHD = rs.getString("MaHD");
						String maSP = rs.getString("MaSP");
						int donGia = rs.getInt("DonGia");
						int soLuong = rs.getInt("SoLuong");
						ChiTietHD_DTO cthd = new ChiTietHD_DTO(maHD, maSP, donGia, soLuong);
						dsChiTietHoaDon.add(cthd);
					}
					return dsChiTietHoaDon;
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
			
			// Tìm kiếm theo Mã
			public ArrayList<ChiTietHD_DTO> findByID(String MaHD){
				String sqlFindByID = "SELECT * FROM chitiethoadon WHERE MaHD ='" + MaHD +"'";
				Statement stmt;
				ArrayList<ChiTietHD_DTO> dsChiTietHoaDon = new ArrayList<ChiTietHD_DTO>();
				try {
					stmt = ketnoiDB.createStatement();
					ResultSet rs = stmt.executeQuery(sqlFindByID);
					while(rs.next()) {
						String maHD = rs.getString("MaHD");
						String maSP = rs.getString("MaSP");
						int donGia = rs.getInt("DonGia");
						int soLuong = rs.getInt("SoLuong");
						ChiTietHD_DTO cthd = new ChiTietHD_DTO(maHD, maSP, donGia, soLuong);
						dsChiTietHoaDon.add(cthd);
					}
					return dsChiTietHoaDon;
				}
				
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
}
