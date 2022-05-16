package shop.laptop.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.bind.DatatypeConverter;

import shop.laptop.BLL.KhachHangBLL;
import shop.laptop.BLL.NhanVienBLL;
import shop.laptop.DAL.KetNoiDB;
import shop.laptop.DTO.GlobalData;
import shop.laptop.DTO.NhanVienDTO;
import shop.laptop.GUI.adminControlPanel.AdminDashboard;
import shop.laptop.GUI.adminControlPanel.UserDashboard;
import shop.laptop.GUI.adminControlPanel.UserDashboard;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Image;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JLabel lblDangNhap;
	private JPanel panelNutThoat;
	private JLabel lblThoat;
	private JLabel lblTaiKhoan;
	private JLabel lblMatKhau;
	private JLabel lblAnhLogo;
	


	/**
	 * Launch the application.
	 */
//	 Comment lại để chạy chương trình chính
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 300);
		setLocationRelativeTo(null); // Giữa màn hình
		setUndecorated(true); // Ẩn thanh tiêu đề => Không kéo thả tăng kích thước frame được muốn thì phải hiện lại
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new LineBorder(new Color(0, 255, 255), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTaiKhoan.setBorder(null);
		txtTaiKhoan.setBounds(231, 63, 236, 33);
		contentPane.add(txtTaiKhoan);
		txtTaiKhoan.setColumns(10);
		
		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMatKhau.setBorder(null);
		txtMatKhau.setBounds(231, 107, 236, 33);
		contentPane.add(txtMatKhau);
		
		JPanel panelNutDangNhap = new JPanel();
		panelNutDangNhap.setBackground(Color.RED);
		panelNutDangNhap.setBounds(231, 157, 110, 33);
		contentPane.add(panelNutDangNhap);
		panelNutDangNhap.setLayout(new BorderLayout(0, 0));
		
		lblDangNhap = new JLabel("ĐĂNG NHẬP");
		lblDangNhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
				NhanVienBLL nvBLL = new NhanVienBLL();
				KhachHangBLL khBLL = new KhachHangBLL();
				// Mã hoá mật khẩu
				String tenDN = txtTaiKhoan.getText();
				String matKhau = txtMatKhau.getText();
				MessageDigest md = null;
				try 
				{
					md = MessageDigest.getInstance("MD5");
				} 
				catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				md.update(matKhau.getBytes());	
				byte[] digest = md.digest();
				String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
				boolean checkNV = nvBLL.checkLogin(tenDN, myChecksum);
				boolean checkKH = khBLL.checkLogin(tenDN, myChecksum);
				if(checkNV==true) {
					GlobalData.setLoggedUserName(tenDN); // Lấy tên đăng nhập để hiển thị bên Dashboard, lấy trước khi tắt GUI Login
					
					// Lấy quyền
					Statement stmt;
					try {
						stmt = ketnoiDB.createStatement();
						String sqlSelectQuyen = "SELECT Quyen FROM nhanvien WHERE TenDN ='" + GlobalData.getLoggedUserName() +"'";
						ResultSet rs = stmt.executeQuery(sqlSelectQuyen);
						rs.first(); // Fix lỗi Before start of result set
						String quyen = rs.getString("Quyen");
						GlobalData.setLoggedRole(quyen);
						
					}
					catch(SQLException ex) {
						ex.printStackTrace();
					}
					// Phân quyền, 2 trang dashboard giống nhau nhưng setVisible khác
					if(GlobalData.getLoggedRole().equals("Admin")) { // Admin
						setVisible(false); // Tắt GUI Login 
						AdminDashboard adminDashboard = new AdminDashboard();
						adminDashboard.setVisible(true);  // Mở cửa số chính
					}
					else { // User
						setVisible(false);
						UserDashboard userDashboard = new UserDashboard();
						userDashboard.setVisible(true);
					}
				
					
				}
				else if(checkKH == true) {
					GlobalData.setLoggedUserName(tenDN); // Lấy tên đăng nhập để hiển thị bên Dashboard, lấy trước khi tắt GUI Login
					
					Statement stmt; 
					try {
						stmt = ketnoiDB.createStatement();
						String sqlSelectQuyen = "SELECT Quyen FROM khachhang WHERE TenDN ='" + GlobalData.getLoggedUserName() +"'";
						ResultSet rs = stmt.executeQuery(sqlSelectQuyen);
						rs.first(); // Fix lỗi Before start of result set
						String quyen = rs.getString("Quyen");
						GlobalData.setLoggedRole(quyen);
					}
					catch (SQLException exx) {
						// TODO: handle exception
						exx.printStackTrace();
					}
					
					// Phân quyền, 2 trang dashboard giống nhau nhưng setVisible khác
					if(GlobalData.getLoggedRole().equals("User")) { // User
						setVisible(false);
						UserDashboard userDashboard = new UserDashboard();
						userDashboard.setVisible(true);
					}
					else { // Admin
						setVisible(false); // Tắt GUI Login 
						AdminDashboard adminDashboard = new AdminDashboard();
						adminDashboard.setVisible(true);  // Mở cửa số chính
					}
				}
				else {
						JOptionPane.showMessageDialog(null, "Đăng nhập sai", "Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblDangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelNutDangNhap.add(lblDangNhap);
		lblDangNhap.setBackground(new Color(4, 147, 114));
		lblDangNhap.setForeground(Color.WHITE);
		lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelNutThoat = new JPanel();
		panelNutThoat.setBackground(Color.RED);
		panelNutThoat.setBounds(357, 157, 110, 33);
		contentPane.add(panelNutThoat);
		panelNutThoat.setLayout(new BorderLayout(0, 0));
		
		lblThoat = new JLabel("THOÁT");
		lblThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Đổi nền sang màu mới
				panelNutThoat.setBackground(new Color(0, 200, 0));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// Trở về màu cũ
				panelNutThoat.setBackground(new Color(255,0,0));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// Đóng Form
				dispose();
			}
		});
		lblThoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblThoat.setHorizontalAlignment(SwingConstants.CENTER);
		lblThoat.setForeground(Color.WHITE);
		lblThoat.setBackground(new Color(4, 147, 114));
		panelNutThoat.add(lblThoat);
		
		lblTaiKhoan = new JLabel("");
	//	ImageIcon imagetest = new ImageIcon(getClass().getResource("/login_user_icon.png"));
		lblTaiKhoan.setIcon(new ImageIcon(new ImageIcon("images/login_user_icon.png").getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
	//	lblTaiKhoan.setIcon(new ImageIcon(Login.class.getResource("/login_user_icon.png")));
		lblTaiKhoan.setBounds(175, 63, 46, 33);
		contentPane.add(lblTaiKhoan);
		
		lblMatKhau = new JLabel("");
		lblMatKhau.setIcon(new ImageIcon(new ImageIcon("images/lock_key_icon.png").getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT)));
		lblMatKhau.setBounds(175, 107, 46, 33);
		contentPane.add(lblMatKhau);
		
		lblAnhLogo = new JLabel("Ảnh");
	//	lblAnhLogo.setIcon(new ImageIcon(getClass().getResource("/laptop.png")));
		lblAnhLogo.setIcon(new ImageIcon(new ImageIcon("images/laptop.png").getImage().getScaledInstance(160, 300, Image.SCALE_DEFAULT)));
		lblAnhLogo.setBounds(10, 11, 155, 278);
		contentPane.add(lblAnhLogo);
		
		JLabel lblNewLabel = new JLabel("HỆ THỐNG QUẢN LÝ CỬA HÀNG LAPTOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(175, 22, 316, 19);
		contentPane.add(lblNewLabel);
	}
}
