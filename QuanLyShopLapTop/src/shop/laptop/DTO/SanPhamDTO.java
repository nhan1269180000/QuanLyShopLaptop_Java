package shop.laptop.DTO;

import java.sql.Blob;

public class SanPhamDTO {
	private String maSP;
	private String tenSP;
	private String moTa;
	private String thongSo;
	private String anhSP;
	private int donGia;
	private int soLuong;
	private String maHSX;
	private String maLSP;
	
	public SanPhamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamDTO(String maSP, String tenSP, String moTa, String thongSo, String anhSP, int donGia, int soLuong,
			String maHSX, String maLSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.moTa = moTa;
		this.thongSo = thongSo;
		this.anhSP = anhSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.maHSX = maHSX;
		this.maLSP = maLSP;
	}

	public SanPhamDTO(String maSP, String tenSP, int donGia, int soLuong) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getThongSo() {
		return thongSo;
	}

	public void setThongSo(String thongSo) {
		this.thongSo = thongSo;
	}

	public String getAnhSP() {
		return anhSP;
	}

	public void setAnhSP(String anhSP) {
		this.anhSP = anhSP;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaHSX() {
		return maHSX;
	}

	public void setMaHSX(String maHSX) {
		this.maHSX = maHSX;
	}

	public String getMaLSP() {
		return maLSP;
	}

	public void setMaLSP(String maLSP) {
		this.maLSP = maLSP;
	}
	
	
	
	
	
}
