package shop.laptop.GUI;

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
import shop.laptop.BLL.KhachHangBLL;
import shop.laptop.DAL.KetNoiDB;
import shop.laptop.DTO.KhachHangDTO;
import shop.laptop.DTO.KhachHangDTO;
import javax.swing.JScrollPane;

public class QuanLyKhachHang extends JInternalFrame {

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
	public QuanLyKhachHang() {
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
		
		JScrollPane scrollPaneTableKhachHang = new JScrollPane();
		scrollPaneTableKhachHang.setBounds(10, 388, 1643, 701);
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
		
		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setBounds(434, 71, 106, 21);
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
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(454, 307, 122, 35);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String maKH = txtMaKH.getText();
					String hoTen = txtHoTen.getText();
					String strSDT = txtSDT.getText(); // Chuyển sang kiểu int
					int sdt = Integer.parseInt(strSDT);
					String diaChi = txtDiaChi.getText();
					String tenDN = txtTenDN.getText();
					
					// Mã hoá mật khẩu
					String matKhau = txtMatKhau.getText();
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(matKhau.getBytes());
					byte[] digest = md.digest();
					String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
					// ---------------------------------------------------------------------------
					String quyen = comboQuyen.getSelectedItem().toString();
					
					for(int i=0; i<tableKhachHang.getRowCount(); i++) { // Trùng mã nhân viên
						if(maKH.equals(dataModel_KhachHang.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							throw new Exception();
						}
					}
					if(maKH.isBlank() || hoTen.isBlank() || strSDT.isBlank() || diaChi.isBlank() || tenDN.isBlank() || matKhau.isBlank() || quyen.isBlank()) {
						throw new Exception();
					}
					else {
						KhachHangBLL khBLL = new KhachHangBLL();
						KhachHangDTO khThem = new KhachHangDTO(maKH, hoTen, sdt, diaChi, tenDN, myHash, quyen);
						khBLL.insert(khThem);
						JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						resetKH();
						hienDLBangKH();
					}
					
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin, mã khách hàng không được trùng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setBounds(609, 307, 122, 35);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				String maKH = txtMaKH.getText();
				boolean result = false;
				
				try {
					if(maKH.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableKhachHang.getRowCount(); i++) {
						if(maKH.equals(dataModel_KhachHang.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						KhachHangBLL khBLL = new KhachHangBLL();
						boolean xoaDuoc = khBLL.delete(maKH);
						if(xoaDuoc) {
							JOptionPane.showMessageDialog(null, "Xóa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetKH();
							hienDLBangKH();
						}
						
					}
					else 
						JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập mã khách hàng hoặc chọn hàng cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(764, 306, 122, 36);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKH = txtMaKH.getText();
				String hoTen = txtHoTen.getText();
				String strSDT = txtSDT.getText();
				int sdt = Integer.parseInt(strSDT); // Chuyển sang kiểu int
				String diaChi = txtDiaChi.getText();
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
				Connection ketnoiDB = KetNoiDB.MoKetNoi("quanlyshoplaptop", "root", "");
				Statement stmt;
				try {
					if(maKH.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableKhachHang.getRowCount(); i++) {
						if(maKH.equals(dataModel_KhachHang.getValueAt(i, 0))) {  // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						KhachHangBLL khBLL = new KhachHangBLL();
						KhachHangDTO khUpdate = new KhachHangDTO(maKH, hoTen, sdt, diaChi, tenDN, myHash, quyen);
						boolean suaDuoc = khBLL.update(khUpdate);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "Xảy ra lỗi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetKH();
							hienDLBangKH();
						}
					}
					else 
						JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập mã khách hàng hoặc chọn hàng cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnSua);
		
		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(914, 307, 122, 35);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maKH = txtMaKH.getText();
				boolean result = false;

				try {
					if(maKH.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableKhachHang.getRowCount(); i++) {
						if(maKH.equals(dataModel_KhachHang.getValueAt(i, 0).toString())) { // toString để trả về chuỗi (Vì đang kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						KhachHangBLL khBLL = new KhachHangBLL();
						ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
						dsKhachHang = khBLL.findByID(maKH);
						dataModel_KhachHang.setRowCount(0); // Set lại để add vào
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
						JOptionPane.showMessageDialog(null, "Tìm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						resetKH();
					}

					else 
						JOptionPane.showMessageDialog(null, "Mã khách hàng không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập mã khách hàng cần tìm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnTimKiem);
		
		JButton btnHienThi = new JButton("Hiển thị");
		btnHienThi.setBounds(1066, 307, 122, 34);
		btnHienThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienDLBangKH();
			}
		});
		btnHienThi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnHienThi);
		
		
	}
	
	public void resetKH() {
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

