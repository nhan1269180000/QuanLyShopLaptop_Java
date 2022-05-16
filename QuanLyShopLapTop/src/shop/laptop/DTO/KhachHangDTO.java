package shop.laptop.DTO;

public class KhachHangDTO {
	private String maKH;
	private String hoTenKH;
	private int SDT;
	private String diaChi;
	private String tenDN;
	private String matKhau;
	private String quyen;
	
	public KhachHangDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public KhachHangDTO(String maKH, String hoTenKH) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
	}


	public KhachHangDTO(String maKH, String hoTenKH, int sDT, String diaChi, String tenDN, String matKhau,
			String quyen) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		SDT = sDT;
		this.diaChi = diaChi;
		this.tenDN = tenDN;
		this.matKhau = matKhau;
		this.quyen = quyen;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTenKH() {
		return hoTenKH;
	}

	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}

	public int getSDT() {
		return SDT;
	}

	public void setSDT(int sDT) {
		SDT = sDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getTenDN() {
		return tenDN;
	}

	public void setTenDN(String tenDN) {
		this.tenDN = tenDN;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}


	
	
	
}
