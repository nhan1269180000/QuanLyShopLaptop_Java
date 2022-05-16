package shop.laptop.DTO;

// import java.sql.Date;

public class NhanVienDTO {
	private String maNV;
	private String hoTenNV;
	private int SDT;
	private String tenDN;
	private String matKhau;
	private String quyen;
	
	public NhanVienDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public NhanVienDTO(String maNV, String hoTenNV) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
	}

	public NhanVienDTO(String maNV, String hoTenNV, int sDT, String tenDN, String matKhau, String quyen) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		SDT = sDT;
		this.tenDN = tenDN;
		this.matKhau = matKhau;
		this.quyen = quyen;
	}

	

	public NhanVienDTO(String maNV, String hoTenNV, int sDT, String tenDN, String quyen) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		SDT = sDT;
		this.tenDN = tenDN;
		this.quyen = quyen;
	}

	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHoTenNV() {
		return hoTenNV;
	}
	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}
	public int getSDT() {
		return SDT;
	}
	public void setSDT(int sDT) {
		SDT = sDT;
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
