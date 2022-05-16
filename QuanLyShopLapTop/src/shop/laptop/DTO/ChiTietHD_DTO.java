package shop.laptop.DTO;

public class ChiTietHD_DTO {
	private String maHD;
	private String maSP;
	private int donGia;
	private int soLuong;
	
	public ChiTietHD_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietHD_DTO(String maHD, String maSP, int donGia, int soLuong) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
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

	
}
