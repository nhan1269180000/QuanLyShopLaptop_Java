package shop.laptop.BLL;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import shop.laptop.DAL.NhanVienDAL;
import shop.laptop.DTO.GlobalData;
import shop.laptop.DTO.NhanVienDTO;

public class NhanVienBLL {
	NhanVienDAL dalNhanVien = new NhanVienDAL();
	
	public boolean insert(NhanVienDTO nv) {
		return dalNhanVien.insert(nv);
	
	}
	
	public boolean update(NhanVienDTO nv) {
		
		return dalNhanVien.update(nv);
		
	}
	
	public boolean delete(String maNV) {
		
		return dalNhanVien.delete(maNV);
	}
	
	public String selectHoTen(String maNV) {
		return dalNhanVien.selectHoTen(maNV);
	}

	public String selectMaNV(String hoTen) {
		return dalNhanVien.selectMaNV(hoTen);
	}
	
	
	public ArrayList<NhanVienDTO> selectNV() 
	{
		return dalNhanVien.selectNV();
	}
	// Lấy danh sách NhanVien
	public ArrayList<NhanVienDTO> selectAll() 
	{
		return dalNhanVien.selectAll();
	}
	
	// Tìm kiếm theo Mã
	public ArrayList<NhanVienDTO> findByID(String MaNV){
		return dalNhanVien.findByID(MaNV);
		
	}
	
	// Tìm kiếm theo Tên
	public ArrayList<NhanVienDTO> findByName(String tenNhanVien) 
	{
		return null;
	}
	
	// Kiểm tra đăng nhập
	public boolean checkLogin(String tenDN, String matKhau)  {
		boolean kq = dalNhanVien.checkLogin(tenDN, matKhau);
		return kq;
	}
	
}
