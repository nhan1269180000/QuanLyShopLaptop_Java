package shop.laptop.BLL;

import java.util.ArrayList;

import shop.laptop.DAL.HoaDonDAL;
import shop.laptop.DTO.HoaDonDTO;


public class HoaDonBLL {
	HoaDonDAL dalHoaDon = new HoaDonDAL();
	
	public boolean insert(HoaDonDTO hd) {
		return dalHoaDon.insert(hd);
	
	}
	
	public boolean update(HoaDonDTO hd) {
		
		return dalHoaDon.update(hd);
		
	}
	
	public boolean delete(String maHD) {
		
		return dalHoaDon.delete(maHD);
	}

	public ArrayList<HoaDonDTO> selectAll() 
	{
		return dalHoaDon.selectAll();
	}
	
	// Tìm kiếm theo Mã
	public ArrayList<HoaDonDTO> findByID(String MaHD){
		return dalHoaDon.findByID(MaHD);
		
	}
}
