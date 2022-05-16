package shop.laptop.BLL;

import java.util.ArrayList;

import shop.laptop.DAL.ChiTietHoaDon_DAL;
import shop.laptop.DTO.ChiTietHD_DTO;
import shop.laptop.DTO.HoaDonDTO;


public class ChiTietHD_BLL {
	ChiTietHoaDon_DAL dalChiTietHD = new ChiTietHoaDon_DAL();
	
	public boolean insert(ChiTietHD_DTO cthd) {
		return dalChiTietHD.insert(cthd);
	
	}
	
	public boolean update(ChiTietHD_DTO cthd) {
		
		return dalChiTietHD.update(cthd);
		
	}
	
	public boolean delete(String maHD, String maSP) {
		
		return dalChiTietHD.delete(maHD, maSP);
	}

	public ArrayList<ChiTietHD_DTO> selectAll() 
	{
		return dalChiTietHD.selectAll();
	}
	
	public ArrayList<ChiTietHD_DTO> findByID(String MaHD){
		return dalChiTietHD.findByID(MaHD);
		
	}
	
}
