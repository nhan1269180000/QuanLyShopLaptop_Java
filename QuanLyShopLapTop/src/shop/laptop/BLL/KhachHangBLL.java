package shop.laptop.BLL;

import java.util.ArrayList;

import shop.laptop.DAL.KhachHangDAL;
import shop.laptop.DTO.KhachHangDTO;

public class KhachHangBLL {
	KhachHangDAL dalKhachHang = new KhachHangDAL();
	
	public boolean insert(KhachHangDTO kh) {
		return dalKhachHang.insert(kh);
	
	}
	
	public boolean update(KhachHangDTO kh) {
		
		return dalKhachHang.update(kh);
		
	}
	
	public boolean delete(String maKH) {
		
		return dalKhachHang.delete(maKH);
	}
	
	public String selectHoTen(String maKH) {
		return dalKhachHang.selectHoTen(maKH);
	}
	
	public String selectMaKH(String hoTen) {
		return dalKhachHang.selectMaKH(hoTen);
	}
	
	public ArrayList<KhachHangDTO> selectKH() 
	{
		return dalKhachHang.selectKH();
	}

	// Lấy danh sách Nhakhien
	public ArrayList<KhachHangDTO> selectAll() 
	{
		return dalKhachHang.selectAll();
	}
	
	// Tìm kiếm theo Mã
	public ArrayList<KhachHangDTO> findByID(String MaKH){
		return dalKhachHang.findByID(MaKH);
		
	}
	
	// Tìm kiếm theo Tên
	public ArrayList<KhachHangDTO> findByName(String tenKhachHang) 
	{
		return null;
	}
	
	// Kiểm tra đăng nhập
	public boolean checkLogin(String tenDN, String matKhau)  {
		boolean kq = dalKhachHang.checkLogin(tenDN, matKhau);
		return kq;
	}
}
