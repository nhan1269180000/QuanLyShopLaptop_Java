package shop.laptop.GUI.adminControlPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import shop.laptop.DTO.GlobalData;
import shop.laptop.DTO.NhanVienDTO;
import shop.laptop.GUI.Login;
import shop.laptop.GUI.QuanLyHoaDon;
import shop.laptop.GUI.QuanLyKhachHang;
import shop.laptop.GUI.QuanLyNhanVien;
import shop.laptop.GUI.QuanLySanPham;
import shop.laptop.GUI.User.QuanLyKhachHang_User;
import shop.laptop.GUI.User.QuanLyNhanVien_User;
import shop.laptop.GUI.User.QuanLySanPham_User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UserDashboard extends JFrame {

	JLabel lblDashBoard, lblNhanVien, lblSanPham;
	private JPanel contentPane, panelLeft, panel;
	private JDesktopPane desktopPane;
	private JPanel panelNV;
	private JPanel panelSP;
	private JLabel lblHome;
	private JPanel panelHome;
	private JLabel lblLogout;
	private JPanel panelLogout;
	private JLabel lblHoaDon;
	private JPanel panelHD;
	private JLabel lbl1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblTenDangNhap;
	private JLabel lblKhachHang;
	private JPanel panelKH;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserDashboard frame = new UserDashboard();
//					frame.setVisible(true);
//			//		Login login = new Login();
//			//		login.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */

	
	public UserDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setLocationRelativeTo(null); // Giữa màn hình
		setUndecorated(true); // Ẩn thanh tiêu đề => Không kéo thả tăng kích thước frame được muốn thì phải hiện lại
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Chạy Full màn hình, Alt F4 tắt cho lẹ
		setBounds(100, 100, 800, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Images
		ImageIcon homeIcon = new ImageIcon(new ImageIcon("images/home_icon_black.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
		ImageIcon khachHangIcon = new ImageIcon(new ImageIcon("images/khachhang_icon.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
		ImageIcon nhanvienIcon = new ImageIcon(new ImageIcon("images/employee_icon.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
		ImageIcon sanphamIcon = new ImageIcon(new ImageIcon("images/laptop.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
		ImageIcon hoadonIcon = new ImageIcon(new ImageIcon("images/hoadon_icon.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
		ImageIcon logoutIcon = new ImageIcon(new ImageIcon("images/logout_icon.png").getImage().getScaledInstance(70, 60, Image.SCALE_DEFAULT));
		
		ImageIcon background = new ImageIcon(this.getClass().getResource("/background.jpg"));
		Image backgroundImg = background.getImage();
		desktopPane = new JDesktopPane() {
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 15));
		
		lblNewLabel = new JLabel("Tên đăng nhập:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel);
		
		lblTenDangNhap = new JLabel("...");
		lblTenDangNhap.setForeground(Color.WHITE);
		lblTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblTenDangNhap);
		lblTenDangNhap.setText(GlobalData.getLoggedUserName());
	
		
		panelLeft = new JPanel();
		panelLeft.setBackground(Color.DARK_GRAY);
		contentPane.add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new MigLayout("", "[218px]", "[76px][100px][100px][100px][100px][100px][100px]"));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel.setBackground(Color.DARK_GRAY);
		panelLeft.add(panel, "cell 0 0,alignx center,aligny center");
		panel.setLayout(new MigLayout("", "[204px]", "[76px]"));
		
		lblDashBoard = new JLabel("DASHBOARD - USER");
		lblDashBoard.setIcon(null);
		lblDashBoard.setForeground(Color.WHITE);
		lblDashBoard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashBoard.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblDashBoard, "cell 0 0,alignx center,aligny center");
		
		panelHome = new JPanel();
		panelHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				desktopPane.removeAll();
				// Bị lỗi hiển thị nên phải tắt hiện lại
				desktopPane.setVisible(false);
				desktopPane.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelHome.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelHome.setBackground(Color.DARK_GRAY);
			}
		});
		panelHome.setBorder(new LineBorder(Color.BLUE));
		panelHome.setBackground(Color.DARK_GRAY);
		panelLeft.add(panelHome, "cell 0 1,grow");
		panelHome.setLayout(new MigLayout("", "[218px]", "[100px]"));
		
		lblHome = new JLabel("HOME");
		lblHome.setIcon(homeIcon);
		panelHome.add(lblHome, "cell 0 0,alignx center,aligny center");
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panelKH = new JPanel();
		panelKH.setBorder(new LineBorder(Color.BLUE));
		panelKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(desktopPane.getComponentCount() == 0) {
					QuanLyKhachHang_User frameKH = new QuanLyKhachHang_User();
					frameKH.setVisible(true);
					desktopPane.add(frameKH);
				}
				else {
					desktopPane.removeAll();
					QuanLyKhachHang_User frameKH = new QuanLyKhachHang_User();
					frameKH.setVisible(false);
					frameKH.setVisible(true);
					desktopPane.add(frameKH);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelKH.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelKH.setBackground(Color.DARK_GRAY);
			}
		});
		panelKH.setBackground(Color.DARK_GRAY);
		panelLeft.add(panelKH, "cell 0 2,grow");
		panelKH.setLayout(new MigLayout("", "[218px]", "[100px]"));
		
		lblKhachHang = new JLabel("KHÁCH HÀNG");
		lblKhachHang.setIcon(khachHangIcon);
		lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhachHang.setForeground(Color.WHITE);
		panelKH.add(lblKhachHang, "cell 0 0,alignx center,aligny center");
		
		panelNV = new JPanel();
		panelNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(desktopPane.getComponentCount() == 0) {
					QuanLyNhanVien_User frameNV = new QuanLyNhanVien_User();
					frameNV.setVisible(true);
					desktopPane.add(frameNV);
				}
				else {
					desktopPane.removeAll();
					QuanLyNhanVien_User frameNV = new QuanLyNhanVien_User();
					// Bị lỗi hiển thị nên phải tắt hiện lại
					frameNV.setVisible(false);
					frameNV.setVisible(true);
					desktopPane.add(frameNV);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelNV.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelNV.setBackground(Color.DARK_GRAY);
			}
		});
		panelNV.setBackground(Color.DARK_GRAY);
		panelNV.setBorder(new LineBorder(Color.BLUE));
		panelLeft.add(panelNV, "cell 0 3,grow");
		panelNV.setLayout(new MigLayout("", "[218px]", "[100px]"));
		
		lblNhanVien = new JLabel("NHÂN VIÊN");
		lblNhanVien.setIcon(nhanvienIcon);
		panelNV.add(lblNhanVien, "cell 0 0,alignx center");
		lblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhanVien.setForeground(Color.WHITE);
		
		panelSP = new JPanel();
		panelSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(desktopPane.getComponentCount() == 0) {
					QuanLySanPham_User frameSP = new QuanLySanPham_User();
					frameSP.setVisible(true);
					desktopPane.add(frameSP);
				}
				else {
					desktopPane.removeAll();
					QuanLySanPham_User frameSP = new QuanLySanPham_User();
					frameSP.setVisible(false);
					frameSP.setVisible(true);
					desktopPane.add(frameSP);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelSP.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelSP.setBackground(Color.DARK_GRAY);
			}
		});
		panelSP.setBorder(new LineBorder(Color.BLUE));
		panelSP.setBackground(Color.DARK_GRAY);
		panelLeft.add(panelSP, "cell 0 4,grow");
		panelSP.setLayout(new MigLayout("", "[218px]", "[100px]"));
		
		lblSanPham = new JLabel("SẢN PHẨM");
		lblSanPham.setIcon(sanphamIcon);
		panelSP.add(lblSanPham, "cell 0 0,alignx center");
		lblSanPham.setForeground(Color.WHITE);
		lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panelHD = new JPanel();
		panelHD.setVisible(false);
		panelHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(desktopPane.getComponentCount() == 0) {
					QuanLyHoaDon frameHD = new QuanLyHoaDon();
					frameHD.setVisible(true);
					desktopPane.add(frameHD);
				}
				else {
					desktopPane.removeAll();
					QuanLyHoaDon frameHD = new QuanLyHoaDon();
					frameHD.setVisible(false);
					frameHD.setVisible(true);
					desktopPane.add(frameHD);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelHD.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelHD.setBackground(Color.DARK_GRAY);
			}
		});
		panelHD.setBackground(Color.DARK_GRAY);
		panelHD.setBorder(new LineBorder(Color.BLUE));
		panelLeft.add(panelHD, "cell 0 6,grow");
		panelHD.setLayout(new MigLayout("", "[218px]", "[100px]"));
		
		lblHoaDon = new JLabel("HÓA ĐƠN");
		lblHoaDon.setIcon(hoadonIcon);
		panelHD.add(lblHoaDon, "cell 0 0,alignx center,aligny center");
		lblHoaDon.setForeground(Color.WHITE);
		lblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		panelLogout = new JPanel();
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				setVisible(false);       	// Tắt GUI 
				login.setVisible(true);		// Mở GUI Login
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLogout.setBackground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelLogout.setBackground(Color.DARK_GRAY);
			}
		});
		panelLogout.setBorder(new LineBorder(Color.BLUE));
		panelLogout.setBackground(Color.DARK_GRAY);
		panelLeft.add(panelLogout, "cell 0 5,grow");
		panelLogout.setLayout(new MigLayout("", "[218px]", "[100px]"));
		
		lblLogout = new JLabel("");
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setIcon(logoutIcon);
		panelLogout.add(lblLogout, "cell 0 0,alignx center,aligny center");
		lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
	
	}
}
