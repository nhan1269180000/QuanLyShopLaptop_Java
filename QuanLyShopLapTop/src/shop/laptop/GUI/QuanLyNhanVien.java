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
		setTitle("Qu???n L?? Nh??n Vi??n");
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2000, 1060); // FullScreen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Frame
	//	setLocationRelativeTo(null); // Gi???a m??n h??nh 
	//	setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); // Ch???y Full m??n h??nh, Alt F4 t???t cho l???
		
		
		// DefaultTableModel - C??c ph????ng th???c addColumn - addRow
		dataModel_NhanVien = new DefaultTableModel();
		// Qui ?????nh C???t
		dataModel_NhanVien.addColumn("M?? nh??n vi??n");
		dataModel_NhanVien.addColumn("H??? t??n nh??n vi??n");
		dataModel_NhanVien.addColumn("S??? ??i???n tho???i");
		dataModel_NhanVien.addColumn("T??n ????ng nh???p");
		dataModel_NhanVien.addColumn("M???t kh???u");
		dataModel_NhanVien.addColumn("Quy???n");
		
		Vector<String> title = new Vector<>(); // Th??m d??? li???u t???ng c???t
		title.add("M?? nh??n vi??n");
		title.add("H??? t??n");
		title.add("S??? ??i???n Tho???i");
		title.add("T??n ????ng nh???p");
		title.add("M???t kh???u");
		title.add("Quy???n");
		dataModel_NhanVien.addRow(title);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneTableNhanVien = new JScrollPane();
		scrollPaneTableNhanVien.setBounds(10, 388, 1643, 701);
		contentPane.add(scrollPaneTableNhanVien);
		
		
		tableNhanVien = new JTable();
		scrollPaneTableNhanVien.setViewportView(tableNhanVien);
		tableNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableNhanVien.setModel(dataModel_NhanVien); // Hi???n th??? title
		
		tableNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// L???y v??? tr??
				DefaultTableModel dtm = (DefaultTableModel) tableNhanVien.getModel(); // Cast (DefaultTableModel) - Tr??nh b??? l???i khi click v??ng tr???ng trong b???ng
				int vitri = tableNhanVien.getSelectedRow();
				// L???y gi?? tr??? ??? v??? tr?? ???????c ch???n theo c???t, toString ????? tr??? v??? chu???i
				// Nh???n ????ng v??o h??ng, nh???n v??o kho???ng tr???ng s??? b??o l???i k???.
				txtMaNV.setText(dtm.getValueAt(vitri, 0).toString());
				txtHoTen.setText(dtm.getValueAt(vitri, 1).toString());
				txtSDT.setText(dtm.getValueAt(vitri, 2).toString());
				txtTenDN.setText(dtm.getValueAt(vitri, 3).toString());
				txtMatKhau.setText(dtm.getValueAt(vitri, 4).toString());
				comboQuyen.setSelectedItem(dtm.getValueAt(vitri, 5).toString());
			
			}
		});
		
		hienDLBangNV(); // Hi???n th??? d??? li???u
		
		JLabel lblMaNV = new JLabel("M?? nh??n vi??n:");
		lblMaNV.setBounds(434, 71, 99, 21);
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMaNV);
		
		JLabel lblHoTen = new JLabel("H??? t??n:");
		lblHoTen.setBounds(869, 71, 83, 21);
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHoTen);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setBounds(677, 121, 66, 21);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblSDT);
		
		JLabel lblTenDN = new JLabel("T??n ????ng nh???p:");
		lblTenDN.setBounds(434, 176, 106, 21);
		lblTenDN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTenDN);
		
		JLabel lblMatKhau = new JLabel("M???t kh???u:");
		lblMatKhau.setBounds(869, 176, 83, 21);
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMatKhau);
		
		JLabel lblQuyen = new JLabel("Quy???n:");
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
		// DefaultComboBoxModel - C??c ph????ng th???c add - remove
		DefaultComboBoxModel<String> quyen = new DefaultComboBoxModel<String>();
		quyen.addElement("Admin");
		quyen.addElement("User");
		comboQuyen.setModel(quyen);
		
		JButton btnThem = new JButton("Th??m");
		btnThem.setBounds(454, 307, 122, 35);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String maNV = txtMaNV.getText();
					String hoTen = txtHoTen.getText();
					String tenDN = txtTenDN.getText();
					String strSDT = txtSDT.getText(); // Chuy???n sang ki???u int
					int sdt = Integer.parseInt(strSDT);
					// M?? ho?? m???t kh???u
					String matKhau = txtMatKhau.getText();
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(matKhau.getBytes());
					byte[] digest = md.digest();
					String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
					// ---------------------------------------------------------------------------
					String quyen = comboQuyen.getSelectedItem().toString();
					
					for(int i=0; i<tableNhanVien.getRowCount(); i++) { // Tr??ng m?? nh??n vi??n
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
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
						JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						resetNV();
						hienDLBangNV();
					}
					
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p ?????y ????? th??ng tin, m?? nh??n vi??n kh??ng ???????c tr??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xo??");
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
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						NhanVienBLL nvBLL = new NhanVienBLL();
						boolean xoaDuoc = nvBLL.delete(maNV);
						if(xoaDuoc) {
							JOptionPane.showMessageDialog(null, "X??a kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "X??a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetNV();
							hienDLBangNV();
						}
						
					}
					else 
						JOptionPane.showMessageDialog(null, "M?? nh??n vi??n kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p m?? nh??n vi??n ho???c ch???n h??ng c???n xo??", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnXoa);
		
		JButton btnSua = new JButton("S???a");
		btnSua.setBounds(764, 306, 122, 36);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maNV = txtMaNV.getText();
				String hoTen = txtHoTen.getText();
				String strSDT = txtSDT.getText();
				int sdt = Integer.parseInt(strSDT); // Chuy???n sang ki???u int
				String tenDN = txtTenDN.getText();
				// M?? ho?? m???t kh???u
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
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0))) {  // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						NhanVienBLL nvBLL = new NhanVienBLL();
						NhanVienDTO nvUpdate = new NhanVienDTO(maNV, hoTen, sdt, tenDN, myHash, quyen);
						boolean suaDuoc = nvBLL.update(nvUpdate);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "X???y ra l???i!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "S???a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetNV();
							hienDLBangNV();
						}
					}
					else 
						JOptionPane.showMessageDialog(null, "M?? nh??n vi??n kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p m?? nh??n vi??n ho???c ch???n h??ng c???n s???a", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnSua);
		
		JButton btnTimKiem = new JButton("T??m ki???m");
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
						if(maNV.equals(dataModel_NhanVien.getValueAt(i, 0).toString())) { // toString ????? tr??? v??? chu???i (V?? ??ang ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						NhanVienBLL nvBLL = new NhanVienBLL();
						ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
						dsNhanVien = nvBLL.findByID(maNV);
						dataModel_NhanVien.setRowCount(0); // Set l???i ????? add v??o
						for(NhanVienDTO nv: dsNhanVien) {
							Vector row = new Vector<>(); // Th??m d??? li???u t???ng c???t
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
						JOptionPane.showMessageDialog(null, "T??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						resetNV();
					}

					else 
						JOptionPane.showMessageDialog(null, "M?? nh??n vi??n kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p m?? nh??n vi??n c???n t??m", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnTimKiem);
		
		JButton btnHienThi = new JButton("Hi???n th???");
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
			dataModel_NhanVien.setRowCount(0); // ????? hi???n th??? l???i table sau khi Th??m - Xo?? - S???a
			NhanVienBLL nvBLL = new NhanVienBLL();
			ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
			dsNhanVien = nvBLL.selectAll();
			for(NhanVienDTO nv: dsNhanVien) {
				Vector<Object> row = new Vector<>(); // Th??m d??? li???u t???ng c???t
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
