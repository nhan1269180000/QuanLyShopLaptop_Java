package shop.laptop.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import shop.laptop.BLL.SanPhamBLL;
import shop.laptop.DAL.KetNoiDB;
import shop.laptop.DTO.SanPhamDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTabbedPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;

public class QuanLySanPham extends JInternalFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelSP;
	private JPanel panelTimKiem;
	private JTable tableSanPham;
	DefaultTableModel dataModel_SanPham, dataModel_TimKiem, dataModel_HSX, dataModel_LSP;
	private JLabel AnhSP;
	private JLabel lblMaSP;
	private JLabel lblTenSP;
	private JLabel lblMoTa;
	private JLabel lblThongSo;
	private JLabel lblAnhSP;
	private JLabel lblDonGia;
	private JLabel lblSoLuong;
	private JLabel lblMaHSX;
	private JLabel lblMaLSP;
	
	private JLabel lblTimKiem;
	
	private JTextField txtMaSP;
	private JTextField txtTenSP;
	private JTextField txtAnhSP;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JComboBox<String> comboHSX, comboLSP, comboTimKiem;
	private JTextArea textAreaMoTa, textAreaThongSo;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnChonAnh;
	
	public String imgPath = null;
	public String imgName = null;
	private JTable tableTimKiem;
	private JTabbedPane tabbedPane_AnhSP;
	private JScrollPane scrollPaneTableSanPham;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					QuanLySanPham frame = new QuanLySanPham();
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
	public QuanLySanPham() {
		
		try {
			setMaximum(true); // Fit FullScreen DesktopPane
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		setMaximizable(true);
		setClosable(true);
		setResizable(true);
		setTitle("Qu???n L?? S???n Ph???m");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1680, 1060); // FullScreen
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// D??ng chung th?? add v??o contentPane ---------------------------------------------------------------------------------------------------------
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// Hi???n l???i to??n b??? b???ng s???n ph???m khi ?????i tab
				hienDLBangSP();
			}
		});
		tabbedPane.setBounds(10, 11, 1104, 551);
		contentPane.add(tabbedPane);
	
		
		Date date = new Date();
		
	
		
		tabbedPane_AnhSP = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_AnhSP.setBounds(1137, 11, 517, 551);
		contentPane.add(tabbedPane_AnhSP);
		
		
		
		// panelSP -------------------------------------------------------------------------------------------------------------------------------
		
		AnhSP = new JLabel("???nh SP");
		
		tabbedPane_AnhSP.addTab("???nh s???n ph???m", null, AnhSP, null);
		
		AnhSP.setIcon(new ImageIcon("D:\\Eclipse-Workspace\\QuanLyShopLapTop\\images\\laptop.png"));
		AnhSP.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		// DefaultTableModel - C??c ph????ng th???c addColumn - addRow
		dataModel_SanPham = new DefaultTableModel();
		// Qui ?????nh C???t
		dataModel_SanPham.addColumn("M?? s???n ph???m");
		dataModel_SanPham.addColumn("T??n s???n ph???m");
		dataModel_SanPham.addColumn("M?? t???");
		dataModel_SanPham.addColumn("Th??ng s???");
		dataModel_SanPham.addColumn("???nh s???n ph???m");
		dataModel_SanPham.addColumn("????n gi??");
		dataModel_SanPham.addColumn("S??? l?????ng");
		dataModel_SanPham.addColumn("M?? h??ng s???n xu???t");
		dataModel_SanPham.addColumn("M?? lo???i s???n ph???m");
		
		scrollPaneTableSanPham = new JScrollPane();
		scrollPaneTableSanPham.setBounds(10, 581, 1644, 400);
		contentPane.add(scrollPaneTableSanPham);
		
		tableSanPham = new JTable();
		scrollPaneTableSanPham.setViewportView(tableSanPham);
		tableSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSanPham.setModel(dataModel_SanPham); // Hi???n th??? title
		
		hienDLBangSP(); // Hi???n th??? d??? li???u

		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// L???y v??? tr??
				DefaultTableModel dtm = (DefaultTableModel) tableSanPham.getModel(); // Cast (DefaultTableModel) - Tr??nh b??? l???i khi click v??ng tr???ng trong b???ng
				int vitri = tableSanPham.getSelectedRow();
				// L???y gi?? tr??? ??? v??? tr?? ???????c ch???n theo c???t, toString ????? tr??? v??? chu???i
				// Nh???n ????ng v??o h??ng, nh???n v??o kho???ng tr???ng s??? b??o l???i k???.
				txtMaSP.setText(dtm.getValueAt(vitri, 0).toString());
				txtTenSP.setText(dtm.getValueAt(vitri, 1).toString());
				textAreaMoTa.setText(dtm.getValueAt(vitri, 2).toString());
				textAreaThongSo.setText(dtm.getValueAt(vitri, 3).toString());
				txtAnhSP.setText(dtm.getValueAt(vitri, 4).toString());
				String imgName = txtAnhSP.getText(); // L???y t??n file ???nh
				txtDonGia.setText(dtm.getValueAt(vitri, 5).toString());
				txtSoLuong.setText(dtm.getValueAt(vitri, 6).toString());
				comboHSX.setSelectedItem(dtm.getValueAt(vitri, 7).toString());
				comboLSP.setSelectedItem(dtm.getValueAt(vitri, 8).toString());
				
				
				ImageIcon image = new ImageIcon(new ImageIcon("images/" + imgName).getImage().getScaledInstance(AnhSP.getWidth(), AnhSP.getHeight(), Image.SCALE_SMOOTH));

				AnhSP.setIcon(null);
				AnhSP.setIcon(image);
				
			
			}
		});
		// DefaultComboBoxModel - C??c ph????ng th???c add - remove
		DefaultComboBoxModel<String> maHSX = new DefaultComboBoxModel<String>();
		maHSX.addElement("Acer");
		maHSX.addElement("Asus");
		maHSX.addElement("Dell");
		maHSX.addElement("HP");
		maHSX.addElement("Lenovo");
		maHSX.addElement("MSI");
		// DefaultComboBoxModel - C??c ph????ng th???c add - remove
		DefaultComboBoxModel<String> maLSP = new DefaultComboBoxModel<String>();
		maLSP.addElement("Business");
		maLSP.addElement("Gaming");
		maLSP.addElement("Home");
		
		panelSP = new JPanel();
		tabbedPane.addTab("Danh s??ch s???n ph???m", null, panelSP, null);
		panelSP.setLayout(null);
		

		// panelTimKiem -------------------------------------------------------------------------------------------------------------------------------
		
		panelTimKiem = new JPanel();
		tabbedPane.addTab("T??m ki???m", null, panelTimKiem, null);
		panelTimKiem.setLayout(null);
		
		
		lblTimKiem = new JLabel("T??m ki???m theo:");
		lblTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTimKiem.setBounds(194, 90, 99, 19);
		panelTimKiem.add(lblTimKiem);
		
		comboTimKiem = new JComboBox<String>();
		comboTimKiem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Object item = comboTimKiem.getSelectedItem().toString(); // L???y Text
				if(item.equals("")) {
					tableTimKiem.setModel(new DefaultTableModel(
							new Object[][] {

							},
							new String[] {
								""
							}
						));
				}
				else if(item.equals("H??ng s???n xu???t")) {
					tableTimKiem.setModel(new DefaultTableModel(
							new Object[][] {
								{"Acer"},
								{"Asus"},
								{"Dell"},
								{"HP"},
								{"Lenovo"},
								{"MSI"},
							},
							new String[] {
								"H??ng s???n xu???t"
							}
						));
				}
				else if(item.equals("Lo???i s???n ph???m")) {
					tableTimKiem.setModel(new DefaultTableModel(
							new Object[][] {
								{"Business"},
								{"Gaming"},
								{"Home"},
							},
							new String[] {
								"Lo???i s???n ph???m"
							}
						));
				}
			}
		});
		comboTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboTimKiem.setBounds(303, 81, 157, 36);
		panelTimKiem.add(comboTimKiem);
		
		DefaultComboBoxModel<String> timKiem = new DefaultComboBoxModel<String>();
		timKiem.addElement("");
		timKiem.addElement("H??ng s???n xu???t");
		timKiem.addElement("Lo???i s???n ph???m");	
		comboTimKiem.setModel(timKiem);
		
		tableTimKiem = new JTable();
		tableTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tableTimKiem.setShowGrid(false);
		
		tableTimKiem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTimKiem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) tableTimKiem.getModel(); // Cast (DefaultTableModel) - Tr??nh b??? l???i khi click v??ng tr???ng trong b???ng
				int vitri = tableTimKiem.getSelectedRow();
				// L???y gi?? tr??? ??? v??? tr?? ???????c ch???n theo c???t, toString ????? tr??? v??? chu???i
				// Nh???n ????ng v??o h??ng, nh???n v??o kho???ng tr???ng s??? b??o l???i k???.
				String maTimKiem = dtm.getValueAt(vitri, 0).toString();
				
				SanPhamBLL spBLL = new SanPhamBLL();
				ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
				
				// Ki???m tra t??m ki???m theo m???c n??o
				Object item = comboTimKiem.getSelectedItem();
				if(item.equals("H??ng s???n xu???t")) { // T??m ki???m theo H??ng s???n xu???t
					dsSanPham = spBLL.findByHSX(maTimKiem);
					dataModel_SanPham.setRowCount(0); // Set l???i ????? add v??o
					for(SanPhamDTO sp: dsSanPham) {
						Vector<Object> row = new Vector<>(); // Th??m d??? li???u t???ng c???t
						row.add(sp.getMaSP());
						row.add(sp.getTenSP());
						row.add(sp.getMoTa());
						row.add(sp.getThongSo());
						row.add(sp.getAnhSP());
						row.add(sp.getDonGia());
						row.add(sp.getSoLuong());
						row.add(sp.getMaHSX());
						row.add(sp.getMaLSP());
						dataModel_SanPham.addRow(row);
					}
				}
				else if (item.equals("Lo???i s???n ph???m")) { // T??m ki???m theo Lo???i s???n ph???m
					dsSanPham = spBLL.findByLSP(maTimKiem);
					dataModel_SanPham.setRowCount(0); // Set l???i ????? add v??o
					for(SanPhamDTO sp: dsSanPham) {
						Vector<Object> row = new Vector<>(); // Th??m d??? li???u t???ng c???t
						row.add(sp.getMaSP());
						row.add(sp.getTenSP());
						row.add(sp.getMoTa());
						row.add(sp.getThongSo());
						row.add(sp.getAnhSP());
						row.add(sp.getDonGia());
						row.add(sp.getSoLuong());
						row.add(sp.getMaHSX());
						row.add(sp.getMaLSP());
						dataModel_SanPham.addRow(row);
				}
				tableSanPham.setModel(dataModel_SanPham);
				tableSanPham.updateUI();
			}
		}
			});
		tableTimKiem.setBounds(194, 141, 631, 238);
		panelTimKiem.add(tableTimKiem);
		
		JButton btnHienThi = new JButton("Hi???n th???");
		btnHienThi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienDLBangSP();
				
			}
		});
		btnHienThi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHienThi.setBounds(500, 81, 157, 36);
		panelTimKiem.add(btnHienThi);
	
		
		
		lblMaSP = new JLabel("M?? s???n ph???m:");
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaSP.setBounds(49, 51, 95, 19);
		panelSP.add(lblMaSP);
		
		lblTenSP = new JLabel("T??n s???n ph???m:");
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenSP.setBounds(49, 103, 109, 19);
		panelSP.add(lblTenSP);
		
		lblMoTa = new JLabel("M?? t???:");
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMoTa.setBounds(49, 222, 95, 19);
		panelSP.add(lblMoTa);
		
		lblThongSo = new JLabel("Th??ng s???:");
		lblThongSo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblThongSo.setBounds(49, 423, 95, 19);
		panelSP.add(lblThongSo);
		
		lblAnhSP = new JLabel("???nh s???n ph???m:");
		lblAnhSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnhSP.setBounds(578, 51, 109, 19);
		panelSP.add(lblAnhSP);
		
		lblDonGia = new JLabel("????n gi??:");
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDonGia.setBounds(578, 112, 95, 19);
		panelSP.add(lblDonGia);
		
		lblSoLuong = new JLabel("S??? L?????ng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoLuong.setBounds(578, 164, 95, 19);
		panelSP.add(lblSoLuong);
		
		lblMaHSX = new JLabel("H??ng s???n xu???t:");
		lblMaHSX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaHSX.setBounds(576, 222, 109, 19);
		panelSP.add(lblMaHSX);
		
		lblMaLSP = new JLabel("Lo???i s???n ph???m:");
		lblMaLSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaLSP.setBounds(576, 281, 109, 19);
		panelSP.add(lblMaLSP);
		
		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaSP.setBounds(166, 44, 247, 33);
		panelSP.add(txtMaSP);
		txtMaSP.setColumns(10);
		
		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(166, 96, 247, 33);
		panelSP.add(txtTenSP);
		
		txtAnhSP = new JTextField();
		txtAnhSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAnhSP.setColumns(10);
		txtAnhSP.setBounds(696, 42, 136, 33);
		panelSP.add(txtAnhSP);
		
		btnChonAnh = new JButton("Ch???n ???nh");
		btnChonAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Code For Button
				JFileChooser jfile = new JFileChooser();
				jfile.setCurrentDirectory(new File(System.getProperty("user.dir"))); // user.home
				
				// Filter only to select image files
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif");
				jfile.addChoosableFileFilter(filter);
				
				int result = jfile.showSaveDialog(null); // Show SaveDialog will return 0 if it is success
					File selectedFile = jfile.getSelectedFile();
					String filename = selectedFile.getName();
					txtAnhSP.setText(filename);
					imgName = filename;
					if(filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".PNG") || filename.endsWith(".png")) {
						if(result == JFileChooser.APPROVE_OPTION) { // 0 == 0 because JFileChooser.APPROVE_OPTION returns 0
							String path = selectedFile.getAbsolutePath();
							ImageIcon myImage = new ImageIcon(path);
							
							Image img = myImage.getImage();
							Image newImage = img.getScaledInstance(AnhSP.getWidth(), AnhSP.getHeight(), Image.SCALE_SMOOTH);
							
							ImageIcon image = new ImageIcon(newImage);
							AnhSP.setIcon(image);
							
							imgPath = path;
							
						
						}
			}
					else {
						JOptionPane.showMessageDialog(null, "Please Select Image File", "Try Again", 1);
					}
				
			}
		});
		btnChonAnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChonAnh.setBounds(842, 40, 101, 37);
		panelSP.add(btnChonAnh);
		
		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(696, 103, 247, 33);
		panelSP.add(txtDonGia);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(696, 155, 247, 35);
		panelSP.add(txtSoLuong);
		
		
		
		comboHSX = new JComboBox<String>();
		comboHSX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboHSX.setBounds(695, 217, 245, 33);
		panelSP.add(comboHSX);
		comboHSX.setModel(maHSX);
		
		comboLSP = new JComboBox<String>();
		comboLSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboLSP.setBounds(695, 276, 245, 33);
		panelSP.add(comboLSP);
		comboLSP.setModel(maLSP);
		
		textAreaMoTa = new JTextArea();
		textAreaMoTa.setLineWrap(true);  // Xu???ng d??ng
		textAreaMoTa.setWrapStyleWord(true); // Tr??nh xu???ng d??ng thi???u ch???
		textAreaMoTa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaMoTa.setBounds(165, 165, 342, 153);
		panelSP.add(textAreaMoTa);
		
		textAreaThongSo = new JTextArea();
		textAreaThongSo.setLineWrap(true);  // Xu???ng d??ng
		textAreaThongSo.setWrapStyleWord(true); // Tr??nh xu???ng d??ng thi???u ch???
		textAreaThongSo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaThongSo.setBounds(165, 344, 342, 153);
		panelSP.add(textAreaThongSo);
		
		btnThem = new JButton("Th??m");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(imgPath != null) {
					try 
					{
						String maSP = txtMaSP.getText();
						String tenSP = txtTenSP.getText();
						String moTa = textAreaMoTa.getText();
						String thongSo = textAreaThongSo.getText();
						String anhSP = imgName;
						String strDonGia = txtDonGia.getText();  // Chuy???n sang ki???u int
						int donGia = Integer.parseInt(strDonGia);
						String strSoLuong = txtSoLuong.getText();  // Chuy???n sang ki???u int
						int soLuong = Integer.parseInt(strSoLuong);
						String maHSX = comboHSX.getSelectedItem().toString();
						String maLSP = comboLSP.getSelectedItem().toString();
						
						for(int i=0; i<tableSanPham.getRowCount(); i++) { // Tr??ng m?? s???n ph???m
							if(maSP.equals(dataModel_SanPham.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
								throw new Exception();
							}
						}
						
						if(maSP.isBlank() || tenSP.isBlank() || moTa.isBlank() || thongSo.isBlank() || strDonGia.isBlank() || strSoLuong.isBlank() || maHSX.isBlank() || maLSP.isBlank()) {
							throw new Exception();
						}
						if(donGia<0 || soLuong<0) {
							throw new Exception();
						}
						else {
							SanPhamBLL spBLL = new SanPhamBLL();
							SanPhamDTO spThem = new SanPhamDTO(maSP, tenSP, moTa, thongSo, anhSP, donGia, soLuong, maHSX, maLSP);
							spBLL.insert(spThem);
							JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetSP();
							hienDLBangSP();
						}
					}
					catch (Exception ex) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Nh???p ?????y ????? th??ng tin v?? ????ng ?????nh d???ng, m?? s???n ph???m kh??ng ???????c tr??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}
				
					
			}
			else {
					JOptionPane.showMessageDialog(null, "M???i ch???n ???nh", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
		}
			});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnThem.setBounds(594, 397, 109, 45);
		panelSP.add(btnThem);
		
		btnXoa = new JButton("X??a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				try 
				{
					String maSP = txtMaSP.getText();
					
					if(maSP.isBlank()) {
						throw new Exception();
					}
					
					for(int i=0; i<tableSanPham.getRowCount(); i++) {
						if(maSP.equals(dataModel_SanPham.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						SanPhamBLL spBLL = new SanPhamBLL();
						boolean xoaDuoc = spBLL.delete(maSP);
						if(xoaDuoc) {
							JOptionPane.showMessageDialog(null, "X??a kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "X??a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetSP();
							hienDLBangSP();
						}
						
					}
					else 
						JOptionPane.showMessageDialog(null, "M?? s???n ph???m kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p m?? s???n ph???m ho???c ch???n h??ng c???n xo??", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnXoa.setBounds(753, 397, 109, 45);
		panelSP.add(btnXoa);
		
		btnSua = new JButton("S???a");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				String anhSP = null;
				String imgNameSelected = txtAnhSP.getText();
				
				try {
					String maSP = txtMaSP.getText();
					String tenSP = txtTenSP.getText();
					String moTa = textAreaMoTa.getText();
					String thongSo = textAreaThongSo.getText();
					if(imgNameSelected != null)  // N???u kh??ng thay ?????i ???nh th?? gi??? nguy??n
						anhSP = imgNameSelected;
					else 	
						anhSP = imgName;
					String strDonGia = txtDonGia.getText();  // Chuy???n sang ki???u int
					int donGia = Integer.parseInt(strDonGia);
					String strSoLuong = txtSoLuong.getText();  // Chuy???n sang ki???u int
					int soLuong = Integer.parseInt(strSoLuong);
					String maHSX = comboHSX.getSelectedItem().toString();
					String maLSP = comboLSP.getSelectedItem().toString();
					
					if(maSP.isBlank() || tenSP.isBlank() || moTa.isBlank() || thongSo.isBlank() || anhSP.isBlank() || strDonGia.isBlank() || strSoLuong.isBlank() || maHSX.isBlank() || maLSP.isBlank()) {
						throw new Exception();
					}
					
					for(int i=0; i<tableSanPham.getRowCount(); i++) {
						if(maSP.equals(dataModel_SanPham.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						SanPhamBLL spBLL = new SanPhamBLL();
						SanPhamDTO spUpdate = new SanPhamDTO(maSP, tenSP, moTa, thongSo, anhSP, donGia, soLuong, maHSX, maLSP);
						boolean suaDuoc = spBLL.update(spUpdate);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "S???a kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "S???a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetSP();
							hienDLBangSP();
						}
						
					}
					else 
						JOptionPane.showMessageDialog(null, "M?? s???n ph???m kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p ?????y ????? th??ng tin ho???c ch???n h??ng c???n s???a", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSua.setBounds(917, 397, 109, 45);
		panelSP.add(btnSua);
		
		
	
	}
	
	public void resetSP() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		textAreaMoTa.setText("");
		textAreaThongSo.setText("");
		txtAnhSP.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		comboHSX.setSelectedIndex(0);
		comboLSP.setSelectedIndex(0);
	}
	
	
	public void hienDLBangSP() {
		try 
		{
			dataModel_SanPham.setRowCount(0); // ????? hi???n th??? l???i table sau khi Th??m - Xo?? - S???a
			SanPhamBLL spBLL = new SanPhamBLL();
			ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
			dsSanPham = spBLL.selectAll();
			for(SanPhamDTO sp: dsSanPham) {
				Vector<Object> row = new Vector<>(); // Th??m d??? li???u t???ng c???t
				row.add(sp.getMaSP());
				row.add(sp.getTenSP());
				row.add(sp.getMoTa());
				row.add(sp.getThongSo());
				row.add(sp.getAnhSP());
				row.add(sp.getDonGia());
				row.add(sp.getSoLuong());
				row.add(sp.getMaHSX());
				row.add(sp.getMaLSP());

				dataModel_SanPham.addRow(row);
			}
			tableSanPham.setModel(dataModel_SanPham);
			tableSanPham.updateUI();
			
			
		}
		catch (Exception ex) {
			// TODO: handle exception
			System.err.print(ex.getMessage());
		}
	}
}
