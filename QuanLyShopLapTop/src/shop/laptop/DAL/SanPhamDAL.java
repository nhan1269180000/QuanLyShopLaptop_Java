package shop.laptop.DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shop.laptop.DTO.NhanVienDTO;
import shop.laptop.DTO.SanPhamDTO;

public class SanPhamDAL {
	Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
	
	public boolean insert(SanPhamDTO sp){
		String sqlInsertSP = "INSERT INTO sanpham VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = ketnoiDB.prepareStatement(sqlInsertSP);
			ps.setString(1, sp.getMaSP());
			ps.setString(2, sp.getTenSP());
			ps.setString(3, sp.getMoTa());
			ps.setString(4, sp.getThongSo());
			ps.setString(5, sp.getAnhSP());
			ps.setInt(6, sp.getDonGia());
			ps.setInt(7, sp.getSoLuong());
			ps.setString(8, sp.getMaHSX());
			ps.setString(9, sp.getMaLSP());
			boolean themDuoc = ps.execute();
			return themDuoc;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(SanPhamDTO sp) {
		String sqlUpdateSP = "UPDATE sanpham SET TenSP = ?, MoTa = ?, ThongSo = ?, AnhSP = ?, DonGia = ?, SoLuong = ?, MaHSX = ?, MaLSP = ?  WHERE MaSP = ?";
		try
		{
			PreparedStatement ps = ketnoiDB.prepareStatement(sqlUpdateSP);
			//
			ps.setString(1, sp.getTenSP());
			ps.setString(2, sp.getMoTa());
			ps.setString(3, sp.getThongSo());
			ps.setString(4, sp.getAnhSP());
			ps.setInt(5, sp.getDonGia());
			ps.setInt(6, sp.getSoLuong());
			ps.setString(7, sp.getMaHSX());
			ps.setString(8, sp.getMaLSP());
			ps.setString(9, sp.getMaSP());
			//
			boolean suaDuoc = ps.execute();
			return suaDuoc;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(String maSP) {
		String sqlDeleteSP = "DELETE FROM sanpham WHERE MaSP ='" + maSP + "'";
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			boolean xoaDuoc = stmt.execute(sqlDeleteSP);
			return xoaDuoc;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String selectTenSP(String maSP) {
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			String sqlSelectTenSP = "SELECT TenSP FROM sanpham WHERE MaSP ='" + maSP +"'";
			ResultSet rs = stmt.executeQuery(sqlSelectTenSP);
			rs.first();
			String tenSP = rs.getString("TenSP");
			return tenSP;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SanPhamDTO> selectAll() {
		ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			String sqlSelectSanPham = "SELECT * FROM sanpham";
			ResultSet tblSanPham = stmt.executeQuery(sqlSelectSanPham);
			// Xử lý tblSanPham --> dsSanPham
			while(tblSanPham.next())  // Trong khi mà còn next được (còn dòng)
			{
				// Xử lý mỗi dòng
				// Lấy dữ liệu từng cột
				String maSP = tblSanPham.getString("MaSP"); // Cột số 0
				String tenSP = tblSanPham.getString("TenSP"); // Cột số 1
				String moTa = tblSanPham.getString("MoTa");
				String thongSo = tblSanPham.getString("ThongSo");
				String anhSP = tblSanPham.getString("AnhSP");
				int donGia = tblSanPham.getInt("DonGia");
				int soLuong = tblSanPham.getInt("SoLuong");
				String maHSX = tblSanPham.getString("MaHSX");
				String maLSP = tblSanPham.getString("MaLSP");
				SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, moTa, thongSo, anhSP, donGia, soLuong, maHSX, maLSP);
				dsSanPham.add(sp);
				
			}
			return dsSanPham;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SanPhamDTO> select() {
		ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
		Statement stmt;
		try {
			stmt = ketnoiDB.createStatement();
			String sqlSelectSanPham = "SELECT * FROM sanpham";
			ResultSet tblSanPham = stmt.executeQuery(sqlSelectSanPham);
			// Xử lý tblSanPham --> dsSanPham
			while(tblSanPham.next())  // Trong khi mà còn next được (còn dòng)
			{
				// Xử lý mỗi dòng
				// Lấy dữ liệu từng cột
				String maSP = tblSanPham.getString("MaSP"); // Cột số 0
				String tenSP = tblSanPham.getString("TenSP"); // Cột số 1
				String moTa = tblSanPham.getString("MoTa");
				String thongSo = tblSanPham.getString("ThongSo");
				String anhSP = tblSanPham.getString("AnhSP");
				int donGia = tblSanPham.getInt("DonGia");
				int soLuong = tblSanPham.getInt("SoLuong");
				String maHSX = tblSanPham.getString("MaHSX");
				String maLSP = tblSanPham.getString("MaLSP");
				SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, donGia, soLuong);
				dsSanPham.add(sp);
				
			}
			return dsSanPham;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SanPhamDTO> findByHSX(String MaHSX){
		String sqlFindByHSX = "SELECT * FROM sanpham WHERE MaHSX ='" + MaHSX +"'";
		Statement stmt;
		ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
		try {
			stmt = ketnoiDB.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFindByHSX);
			while(rs.next()) {
				String maSP = rs.getString("MaSP"); // Cột số 0
				String tenSP = rs.getString("TenSP"); // Cột số 1
				String moTa = rs.getString("MoTa");
				String thongSo = rs.getString("ThongSo");
				String anhSP = rs.getString("AnhSP");
				int donGia = rs.getInt("DonGia");
				int soLuong = rs.getInt("SoLuong");
				String maHSX = rs.getString("MaHSX");
				String maLSP = rs.getString("MaLSP");
				SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, moTa, thongSo, anhSP, donGia, soLuong, maHSX, maLSP);
				dsSanPham.add(sp);
			}
			return dsSanPham;
		}
		
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SanPhamDTO> findByLSP(String MaLSP){
		String sqlFindByLSP = "SELECT * FROM sanpham WHERE MaLSP ='" + MaLSP +"'";
		Statement stmt;
		ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
		try {
			stmt = ketnoiDB.createStatement();
			ResultSet rs = stmt.executeQuery(sqlFindByLSP);
			while(rs.next()) {
				String maSP = rs.getString("MaSP"); // Cột số 0
				String tenSP = rs.getString("TenSP"); // Cột số 1
				String moTa = rs.getString("MoTa");
				String thongSo = rs.getString("ThongSo");
				String anhSP = rs.getString("AnhSP");
				int donGia = rs.getInt("DonGia");
				int soLuong = rs.getInt("SoLuong");
				String maHSX = rs.getString("MaHSX");
				String maLSP = rs.getString("MaLSP");
				SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, moTa, thongSo, anhSP, donGia, soLuong, maHSX, maLSP);
				dsSanPham.add(sp);
			}
			return dsSanPham;
		}
		
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
