package shop.laptop.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import shop.laptop.BLL.NhanVienBLL;
import shop.laptop.DAL.KetNoiDB;
import shop.laptop.DAL.NhanVienDAL;
import shop.laptop.DTO.GlobalData;
import shop.laptop.DTO.NhanVienDTO;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JScrollPane;

public class QuanLyNhanVien extends JInternalFrame {

	private JFrame frame;
	private JInternalFrame frameNV;
	private JPanel contentPane;
	DefaultTableModel dataModel_NhanVien, dataModel_TimKiem;
	private JTable tableNhanVien;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtSDT;
	private JTextField txtTenDN;
	private JTextField txtMatKhau;
	private JComboBox<String> comboQuyen;
	public String checkMatKhau = null;
	

	/**
	 * Create the frame.
	 */
	public QuanLyNhanVien() {
		try {
			setMaximum(true); // Fit FullScreen DesktopPane
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		setTitle("Quản Lý Nhân Viên");
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2000, 1060); // FullScreen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Frame
	//	setLocationRelativeTo(null); // Giữa màn hình 
	//	setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Chạy Full màn hình, Alt F4 tắt cho lẹ
		
		
		// DefaultTableModel - Các phương thức addColumn - addRow
		dataModel_NhanVien = new DefaultTableModel();
		// Qui định Cột
		dataModel_NhanVien.addColumn("Mã nhân viên");
		dataModel_NhanVien.addColumn("Họ tên nhân viên");
		dataModel_NhanVien.addColumn("Số điện thoại");
		dataModel_NhanVien.addColumn("Tên đăng nhập");
		dataModel_NhanVien.addColumn("Mật khẩu");
		dataModel_NhanVien.addColumn("Quyền");
		
		Vector<String> title = new Vector<>(); // Thêm dữ liệu từng cột
		title.add("Mã nhân viên");
		title.add("Họ tên");
		title.add("Số Điện Thoại");
		title.add("Tên đăng nhập");
		title.add("Mật khẩu");
		title.add("Quyền");
		dataModel_NhanVien.addRow(title);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneTableNhanVien = new JScrollPane();
		scrollPaneTableNhanVien.setBounds(10, 388, 1643, 701);
		contentPane.add(scrollPaneTableNhanVien);
		
		
		tableNhanVien = new JTable();
		scrollPaneTableNhanVien.setViewportView(tableNhanVien);
		tableNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNhanVien.setModel(dataModel_NhanVien); // Hiển thị title
		
		tableNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Lấy vị trí
				DefaultTableModel dtm = (DefaultTableModel) tableNhanVien.getModel(); // Cast (DefaultTableModel) - Tránh bị lỗi khi click vùng trống trong bảng
				int vitri = tableNhanVien.getSelectedRow();
				// Lấy giá trị ở vị trí được chọn theo cột, toString để trả về chuỗi
				// Nhấn đúng vào hàng, nhấn vào khoảng trắng sẽ báo lỗi kệ.
				txtMaNV.setText(dtm.getValueAt(vitri, 0).toString());
				txtHoTen.setText(dtm.getValueAt(vitri, 1).toString());
				txtSDT.setText(dtm.getValueAt(vitri, 2).toString());
				txtTenDN.setText(dtm.getValueAt(vitri, 3).toString());
				txtMatKhau.setText(dtm.getValueAt(vitri, 4).toString());
				comboQuyen.setSelectedItem(dtm.getValueAt(vitri, 5).toString());
			
			}
		});
		
		hienDLBangNV(); // Hiển thị dữ liệu
		
		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setBounds(434, 71, 99, 21);
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMaNV);
		
		JLabel lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setBounds(869, 71, 83, 21);
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHoTen);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setBounds(677, 121, 66, 21);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblSDT);
		
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
		
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaNV.setBounds(573, 64, 248, 35);
		txtMaNV.setText("");
		contentPane.add(txtMaNV);
		txtMaNV.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtHoTen.setBounds(962, 64, 248, 35);
		txtHoTen.setText("");
		txtHoTen.setColumns(10);
		contentPane.add(txtHoTen);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSDT.setBounds(757, 114, 248, 36);
		txtSDT.setText("");
		txtSDT.setColumns(10);
		contentPane.add(txtSDT);
		
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
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(454, 307, 122, 35);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String maNV = txtMaNV.getText();
					String hoTen = txtHoTen.getText();
					String tenDN = txtTenDN.getText();
					String strSDT = txtSDT.getText(); // Chuyển sang kiểu int
					int sdt = Integer.parseInt(strSDT);
					// Mã hoá mật khẩu
					String matKhau = txtMatKhau.getText();
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(matKhau.getBytes());
					byte[] digest = md.digest();
					String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
					// ---------------------------------------------------------------------------
					String quyen = comboQuyen.getSelectedItem().toString();
					
					for(int i=0; i<tableNhanVien.getRowCount(); i++) { // Trùng mã nhân viên
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							throw new Exception();
						}
					}
					if(maNV.isBlank() || hoTen.isBlank() || strSDT.isBlank() || tenDN.isBlank() || matKhau.isBlank() || quyen.isBlank()) {
						throw new Exception();
					}
					else {
//						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//						String strDate = txtNgaySinh.getText();
//						Date ngaySinh;
//						ngaySinh = (Date) formatter.parse(strDate);
						
						NhanVienBLL nvBLL = new NhanVienBLL();
						NhanVienDTO nvThem = new NhanVienDTO(maNV, hoTen, sdt, tenDN, myHash, quyen);
						nvBLL.insert(nvThem);
						JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						resetNV();
						hienDLBangNV();
					}
					
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin, mã nhân viên không được trùng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setBounds(609, 307, 122, 35);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String maNV = txtMaNV.getText();
				boolean result = false;
				
				try {
					if(maNV.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableNhanVien.getRowCount(); i++) {
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						NhanVienBLL nvBLL = new NhanVienBLL();
						boolean xoaDuoc = nvBLL.delete(maNV);
						if(xoaDuoc) {
							JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetNV();
							hienDLBangNV();
						}
						
					}
					else 
						JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập mã nhân viên hoặc chọn hàng cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(764, 306, 122, 36);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maNV = txtMaNV.getText();
				String hoTen = txtHoTen.getText();
				String strSDT = txtSDT.getText();
				int sdt = Integer.parseInt(strSDT); // Chuyển sang kiểu int
				String tenDN = txtTenDN.getText();
				// Mã hoá mật khẩu
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
				String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
				// ----------------------------------------------------------------
				String quyen = comboQuyen.getSelectedItem().toString();
				
				boolean result = false;
				Statement stmt;
				try {
					if(maNV.isBlank() || hoTen.isBlank() || strSDT.isBlank() || tenDN.isBlank() || matKhau.isBlank() || quyen.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableNhanVien.getRowCount(); i++) {
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0))) {  // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						NhanVienBLL nvBLL = new NhanVienBLL();
						NhanVienDTO nvUpdate = new NhanVienDTO(maNV, hoTen, sdt, tenDN, myHash, quyen);
						boolean suaDuoc = nvBLL.update(nvUpdate);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "Xảy ra lỗi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetNV();
							hienDLBangNV();
						}
					}
					else 
						JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập mã nhân viên hoặc chọn hàng cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnSua);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(914, 307, 122, 35);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maNV = txtMaNV.getText();
				boolean result = false;
				
				try {
					if(maNV.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableNhanVien.getRowCount(); i++) {
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0).toString())) { // toString để trả về chuỗi (Vì đang kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						NhanVienBLL nvBLL = new NhanVienBLL();
						ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
						dsNhanVien = nvBLL.findByID(maNV);
						dataModel_NhanVien.setRowCount(0); // Set lại để add vào
						for(NhanVienDTO nv: dsNhanVien) {
							Vector row = new Vector<>(); // Thêm dữ liệu từng cột
							row.add(nv.getMaNV());
							row.add(nv.getHoTenNV());
							row.add(nv.getSDT());
							row.add(nv.getTenDN());
							row.add(nv.getMatKhau());
							row.add(nv.getQuyen());
							dataModel_NhanVien.addRow(row);
						}
						tableNhanVien.setModel(dataModel_NhanVien);
						tableNhanVien.updateUI();
						JOptionPane.showMessageDialog(null, "Tìm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						resetNV();
					}

					else 
						JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập mã nhân viên cần tìm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnTimKiem);
		
		JButton btnHienThi = new JButton("Hiển thị");
		btnHienThi.setBounds(1066, 307, 122, 34);
		btnHienThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienDLBangNV();
			}
		});
		btnHienThi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnHienThi);
		
		
	}
	
	public void resetNV() {
		txtMaNV.setText("");
		txtHoTen.setText("");
		txtSDT.setText("");
		txtTenDN.setText("");
		txtMatKhau.setText("");
		comboQuyen.setSelectedIndex(0);
	}
	
	
	public void hienDLBangNV() {
		try 
		{
			dataModel_NhanVien.setRowCount(0); // Để hiển thị lại table sau khi Thêm - Xoá - Sửa
			NhanVienBLL nvBLL = new NhanVienBLL();
			ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
			dsNhanVien = nvBLL.selectAll();
			for(NhanVienDTO nv: dsNhanVien) {
				Vector<Object> row = new Vector<>(); // Thêm dữ liệu từng cột
				row.add(nv.getMaNV());
				row.add(nv.getHoTenNV());
				row.add(nv.getSDT());
				row.add(nv.getTenDN());
				row.add(nv.getMatKhau());
				row.add(nv.getQuyen());
				dataModel_NhanVien.addRow(row);
			}
			tableNhanVien.setModel(dataModel_NhanVien);
			tableNhanVien.updateUI();
			
			
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.err.print(ex.getMessage());
		}
	}
}
