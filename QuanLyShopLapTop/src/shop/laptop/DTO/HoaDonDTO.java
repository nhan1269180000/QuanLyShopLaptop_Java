package shop.laptop.DTO;

import java.util.Date;

public class HoaDonDTO {
	private String maHD;
	private String ngayDat;
	private String ngayGiao;
	private String diaChiGiao;
	private String maKH;
	private String maNV;
	private String tinhTrang;
	
	public HoaDonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDonDTO(String maHD, String ngayDat, String ngayGiao, String diaChiGiao, String maKH, String maNV,
			String tinhTrang) {
		super();
		this.maHD = maHD;
		this.ngayDat = ngayDat;
		this.ngayGiao = ngayGiao;
		this.diaChiGiao = diaChiGiao;
		this.maKH = maKH;
		this.maNV = maNV;
		this.tinhTrang = tinhTrang;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(String ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(String ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public String getDiaChiGiao() {
		return diaChiGiao;
	}

	public void setDiaChiGiao(String diaChiGiao) {
		this.diaChiGiao = diaChiGiao;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	
	
	
	
}
