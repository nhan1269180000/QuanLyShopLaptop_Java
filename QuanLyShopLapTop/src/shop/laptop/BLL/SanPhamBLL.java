package shop.laptop.BLL;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import shop.laptop.DAL.SanPhamDAL;
import shop.laptop.DTO.NhanVienDTO;
import shop.laptop.DTO.SanPhamDTO;

public class SanPhamBLL {
	SanPhamDAL dalSanPham = new SanPhamDAL();
	
	public boolean insert(SanPhamDTO sp) {
		return dalSanPham.insert(sp);
	}
	
	public boolean update(SanPhamDTO sp) {
		return dalSanPham.update(sp);
	}
	
	public boolean delete(String maSP) {
		return dalSanPham.delete(maSP);
	}
	
	public String selectTenSP(String maSP) {
		return dalSanPham.selectTenSP(maSP);
	}
	
	public ArrayList<SanPhamDTO> selectAll() {
		return dalSanPham.selectAll();
	}
	
	public ArrayList<SanPhamDTO> select() {
		return dalSanPham.select();
	}
	
	
	
	public ArrayList<SanPhamDTO> findByHSX(String MaHSX){
		return dalSanPham.findByHSX(MaHSX);
		
	}
	
	public ArrayList<SanPhamDTO> findByLSP(String MaLSP){
		return dalSanPham.findByLSP(MaLSP);
		
	}
	
//	public ArrayList<SanPhamDTO> selectHSX() {
//		return dalSanPham.selectHSX();
//	}
//	
//	public ArrayList<SanPhamDTO> selectLSP() {
//		return dalSanPham.selectLSP();
//	}
}
