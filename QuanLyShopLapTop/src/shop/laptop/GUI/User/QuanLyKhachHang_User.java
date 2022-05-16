package shop.laptop.GUI.User;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.DatatypeConverter;

import shop.laptop.BLL.KhachHangBLL;
import shop.laptop.DAL.KetNoiDB;
import shop.laptop.DTO.KhachHangDTO;
import javax.swing.JScrollPane;

public class QuanLyKhachHang_User extends JInternalFrame {

	private JFrame frame;
	private JInternalFrame frameKH;
	private JPanel contentPane;
	DefaultTableModel dataModel_KhachHang, dataModel_TimKiem;
	private JTable tableKhachHang;
	private JTextField txtMaKH;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtTenDN;
	private JTextField txtMatKhau;
	private JComboBox<String> comboQuyen;
	public String checkMatKhau = null;
	private JTextField txtDiaChi;
	private JScrollPane scrollPaneTableKhachHang;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.ikhokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLyKhachHang frame = new QuanLyKhachHang();
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
	public QuanLyKhachHang_User() {
		try {
			setMaximum(true); // Fit FullScreen DesktopPane
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		setTitle("Quản Lý Khách Hàng");
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2000, 1060); // FullScreen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		// DefaultTableModel - Các phương thức addColumn - addRow
		dataModel_KhachHang = new DefaultTableModel();
		// Qui định Cột
		dataModel_KhachHang.addColumn("Mã khách hàng");
		dataModel_KhachHang.addColumn("Họ tên khách hàng");
		dataModel_KhachHang.addColumn("Số điện thoại");
		dataModel_KhachHang.addColumn("Địa chỉ");
		dataModel_KhachHang.addColumn("Tên đăng nhập");
		dataModel_KhachHang.addColumn("Mật khẩu");
		dataModel_KhachHang.addColumn("Quyền");
		
		contentPane.setLayout(null);
		
		scrollPaneTableKhachHang = new JScrollPane();
		scrollPaneTableKhachHang.setBounds(10, 309, 1643, 701);
		contentPane.add(scrollPaneTableKhachHang);
		
		
		tableKhachHang = new JTable();
		scrollPaneTableKhachHang.setViewportView(tableKhachHang);
		tableKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableKhachHang.setModel(dataModel_KhachHang); // Hiển thị title
		
	
		
		tableKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lấy vị trí
				DefaultTableModel dtm = (DefaultTableModel) tableKhachHang.getModel(); // Cast (DefaultTableModel) - Tránh bị lỗi khi click vùng trống trong bảng
				int vitri = tableKhachHang.getSelectedRow();
				// Lấy giá trị ở vị trí được chọn theo cột, toString để trả về chuỗi
				// Nhấn đúng vào hàng, nhấn vào khoảng trắng sẽ báo lỗi kệ.
				txtMaKH.setText(dtm.getValueAt(vitri, 0).toString());
				txtHoTen.setText(dtm.getValueAt(vitri, 1).toString());
				txtSDT.setText(dtm.getValueAt(vitri, 2).toString());
				txtDiaChi.setText(dtm.getValueAt(vitri, 3).toString());
				txtTenDN.setText(dtm.getValueAt(vitri, 4).toString());
				txtMatKhau.setText(dtm.getValueAt(vitri, 5).toString());
				comboQuyen.setSelectedItem(dtm.getValueAt(vitri, 6).toString());
			
			}
		});
		
		hienDLBangKH(); // Hiển thị dữ liệu
		
		JLabel lblMaKH = new JLabel("Mã nhân viên:");
		lblMaKH.setBounds(434, 71, 99, 21);
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMaKH);
		
		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setBounds(869, 71, 83, 21);
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHoTen);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setBounds(869, 121, 66, 21);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblSDT);
		
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDiaChi.setBounds(434, 121, 99, 21);
		contentPane.add(lblDiaChi);
		
		JLabel lblTenDN = new JLabel("Tên đăng nhập:");
		lblTenDN.setBounds(434, 176, 106, 21);
		lblTenDN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTenDN);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setBounds(869, 176, 83, 21);
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMatKhau);
		
		JLabel lblQuyen = new JLabel("Quyền:");
		lblQuyen.setBounds(677, 234, 66, 21);
		lblQuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblQuyen);
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaKH.setBounds(573, 64, 248, 35);
		txtMaKH.setText("");
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoTen.setBounds(962, 64, 248, 35);
		txtHoTen.setText("");
		txtHoTen.setColumns(10);
		contentPane.add(txtHoTen);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setBounds(962, 114, 248, 36);
		txtSDT.setText("");
		txtSDT.setColumns(10);
		contentPane.add(txtSDT);

		
		txtDiaChi = new JTextField();
		txtDiaChi.setText("");
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(573, 114, 248, 35);
		contentPane.add(txtDiaChi);
		
		txtTenDN = new JTextField();
		txtTenDN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenDN.setBounds(573, 169, 248, 35);
		txtTenDN.setText("");
		txtTenDN.setColumns(10);
		contentPane.add(txtTenDN);
		
		txtMatKhau = new JTextField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMatKhau.setBounds(962, 169, 248, 35);
		txtMatKhau.setText("");
		txtMatKhau.setColumns(10);
		contentPane.add(txtMatKhau);
		
		comboQuyen = new JComboBox<String>();
		comboQuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboQuyen.setBounds(757, 227, 248, 35);
		contentPane.add(comboQuyen);
		
		// DefaultComboBoxModel - Các phương thức add - remove
		DefaultComboBoxModel<String> quyen = new DefaultComboBoxModel<String>();
		quyen.addElement("Admin");
		quyen.addElement("User");
		comboQuyen.setModel(quyen);
		
		
	}
	
	public void reset() {
		txtMaKH.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		txtTenDN.setText("");
		txtMatKhau.setText("");
		comboQuyen.setSelectedIndex(0);
	}
	
	public void hienDLBangKH() {
		try 
		{
			dataModel_KhachHang.setRowCount(0); // Để hiển thị lại table sau khi Thêm - Xoá - Sửa
			KhachHangBLL khBLL = new KhachHangBLL();
			ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
			dsKhachHang = khBLL.selectAll();
			for(KhachHangDTO kh: dsKhachHang) {
				Vector<Object> row = new Vector<>(); // Thêm dữ liệu từng cột
				row.add(kh.getMaKH());
				row.add(kh.getHoTenKH());
				row.add(kh.getSDT());
				row.add(kh.getDiaChi());
				row.add(kh.getTenDN());
				row.add(kh.getMatKhau());
				row.add(kh.getQuyen());
				dataModel_KhachHang.addRow(row);
			}
			tableKhachHang.setModel(dataModel_KhachHang);
			tableKhachHang.updateUI();
			
			
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.err.print(ex.getMessage());
		}
	}
	}