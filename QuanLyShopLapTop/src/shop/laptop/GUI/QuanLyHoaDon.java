package shop.laptop.GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import shop.laptop.BLL.ChiTietHD_BLL;
import shop.laptop.BLL.HoaDonBLL;
import shop.laptop.BLL.KhachHangBLL;
import shop.laptop.BLL.NhanVienBLL;
import shop.laptop.BLL.SanPhamBLL;
import shop.laptop.DAL.KetNoiDB;
import shop.laptop.DTO.ChiTietHD_DTO;
import shop.laptop.DTO.HoaDonDTO;
import shop.laptop.DTO.KhachHangDTO;
import shop.laptop.DTO.NhanVienDTO;
import shop.laptop.DTO.SanPhamDTO;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

public class QuanLyHoaDon extends JInternalFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panelHoaDon;
	private JPanel panelThanhToan;
	private JPanel panelThongTinHoaDon, panelThongTinSanPham;
	DefaultTableModel dataModel_KhachHang, dataModel_NhanVien, dataModel_SanPham, dataModel_TimKiem, dataModel_HoaDon, dataModel_ChiTietHD;
	private JTextField txtMaHD;
	private JTextField txtDiaChiGiao;
	private JComboBox<String> comboMaKH;
	private JComboBox<String> comboMaNV;
	private JComboBox<String> comboTinhTrangHD;
	private JDateChooser txtNgayDat, txtNgayGiao;

	private JTable tableChiTietHoaDon;

	private JLabel lblTongTien;
	private JButton btnXoaHD;
	private JTextField txtMaSP;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JButton btnHienLaiCTHD;
	private JButton btnXoaCTHD;
	private JLabel lblBangSP;
	private JTable tableSanPham;
	private JLabel lblBangHD;
	private JTable tableHoaDon;
	private JLabel lblBangCTHD;
	private JLabel lblMaHD;
	private JLabel lblNgayDat;
	private JLabel lblNgayGiao;
	private JLabel lblKhachHangDat;
	private JLabel lblNhanVienLap;
	private JLabel lblMaHD_CTHD;
	private JLabel lblTenSP;
	private JTextField txtMaHD_CTHD;
	private JTextField txtTenSP;
	private JScrollPane scrollPaneTableChiTietHD;
	private JScrollPane scrollPaneTableHoaDon;
	private JScrollPane scrollPaneTableSanPham;
	private JButton btnSuaCTHD;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					QuanLyHoaDon frame = new QuanLyHoaDon();
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
	public QuanLyHoaDon() {
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

		// 105 - 117 contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1644, 1012);
		contentPane.add(tabbedPane);
		
		panelHoaDon = new JPanel();
		tabbedPane.addTab("L???p ho?? ????n", null, panelHoaDon, null);
		panelHoaDon.setLayout(null);
		
		panelThanhToan = new JPanel();
		tabbedPane.addTab("Thanh to??n", null, panelThanhToan, null);
		panelThanhToan.setLayout(null);
		
		// panelThanhToan -----------------------------------------------------------------	
		
		
		// panelHoaDon --------------------------------------------------------------------		

		// B???ng s???n ph???m
		dataModel_SanPham = new DefaultTableModel();
		// Qui ?????nh C???t
		dataModel_SanPham.addColumn("M?? s???n ph???m");
		dataModel_SanPham.addColumn("T??n s???n ph???m");
		dataModel_SanPham.addColumn("????n gi??");
		dataModel_SanPham.addColumn("S??? l?????ng");
		
		// B???ng chi ti???t ho?? ????n
		dataModel_ChiTietHD = new DefaultTableModel();
		// Qui ?????nh C???t
		dataModel_ChiTietHD.addColumn("M?? ho?? ????n");
		dataModel_ChiTietHD.addColumn("M?? s???n ph???m");
		dataModel_ChiTietHD.addColumn("????n gi??");
		dataModel_ChiTietHD.addColumn("S??? l?????ng");

		// B???ng ho?? ????n
		dataModel_HoaDon = new DefaultTableModel();
		// Qui ?????nh C???t
		dataModel_HoaDon.addColumn("M?? ho?? ????n");
		dataModel_HoaDon.addColumn("Ng??y ?????t");
		dataModel_HoaDon.addColumn("Ng??y giao");
		dataModel_HoaDon.addColumn("?????a ch??? giao");
		dataModel_HoaDon.addColumn("M?? kh??ch h??ng");
		dataModel_HoaDon.addColumn("M?? nh??n vi??n");
		dataModel_HoaDon.addColumn("T??nh tr???ng");

		// B???ng kh??ch h??ng
		// DefaultTableModel - C??c ph????ng th???c addColumn - addRow
		dataModel_KhachHang = new DefaultTableModel();
		// Qui ?????nh C???t
		dataModel_KhachHang.addColumn("M?? kh??ch h??ng");
		dataModel_KhachHang.addColumn("H??? t??n kh??ch h??ng");
		dataModel_KhachHang.addColumn("S??? ??i???n tho???i");
		dataModel_KhachHang.addColumn("?????a ch???");		

		// panelThongTinHoaDon -------------------------------------------------------------------------------------------------
		panelThongTinHoaDon = new JPanel();
		panelThongTinHoaDon.setBorder(new TitledBorder(null, "Th\u00F4ng tin ho\u00E1 \u0111\u01A1n", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		panelThongTinHoaDon.setBounds(10, 11, 837, 167);
		panelHoaDon.add(panelThongTinHoaDon);
		panelThongTinHoaDon.setLayout(null);

		lblMaHD = new JLabel("M?? ho?? ????n:");
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHD.setBounds(33, 32, 86, 17);
		panelThongTinHoaDon.add(lblMaHD);

		lblNgayDat = new JLabel("Ng??y ?????t:");
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayDat.setBounds(33, 78, 77, 17);
		panelThongTinHoaDon.add(lblNgayDat);

		lblNgayGiao = new JLabel("Ng??y giao:");
		lblNgayGiao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayGiao.setBounds(306, 78, 77, 17);
		panelThongTinHoaDon.add(lblNgayGiao);

		lblKhachHangDat = new JLabel("Kh??ch h??ng:");
		lblKhachHangDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhachHangDat.setBounds(306, 32, 78, 17);
		panelThongTinHoaDon.add(lblKhachHangDat);

		lblNhanVienLap = new JLabel("Nh??n vi??n:");
		lblNhanVienLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhanVienLap.setBounds(576, 30, 77, 17);
		panelThongTinHoaDon.add(lblNhanVienLap);

		JLabel lblDiaChiGiao = new JLabel("?????a ch??? giao:");
		lblDiaChiGiao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChiGiao.setBounds(31, 116, 79, 17);
		panelThongTinHoaDon.add(lblDiaChiGiao);

		JLabel lblTinhTrangHD = new JLabel("T??nh tr???ng:");
		lblTinhTrangHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTinhTrangHD.setBounds(574, 78, 79, 17);
		panelThongTinHoaDon.add(lblTinhTrangHD);

		txtMaHD = new JTextField();
		txtMaHD.setBounds(120, 32, 155, 20);
		panelThongTinHoaDon.add(txtMaHD);
		txtMaHD.setColumns(10);

		txtNgayDat = new JDateChooser();
		txtNgayDat.setBounds(120, 75, 155, 20);
		panelThongTinHoaDon.add(txtNgayDat);
		txtNgayDat.setDate(new Date());

		txtNgayGiao = new JDateChooser();
		txtNgayGiao.setBounds(393, 75, 153, 20);
		panelThongTinHoaDon.add(txtNgayGiao);
		txtNgayGiao.setDate(new Date());

		txtNgayDat.setDateFormatString("yyyy-MM-dd"); 
		txtNgayGiao.setDateFormatString("yyyy-MM-dd"); 

		txtDiaChiGiao = new JTextField();
		txtDiaChiGiao.setBounds(120, 116, 686, 20);
		panelThongTinHoaDon.add(txtDiaChiGiao);
		txtDiaChiGiao.setColumns(10);
		
		// L???y m?? v?? t??n nh??n vi??n t??? Database
		NhanVienBLL nvBLL = new NhanVienBLL();
		ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
		dsNhanVien = nvBLL.selectNV();
		DefaultComboBoxModel<String> cbMaNV = new DefaultComboBoxModel<String>();
		for(NhanVienDTO nv: dsNhanVien) {
			cbMaNV.addElement(nv.getHoTenNV());
		}

		//L???y m?? v?? t??n kh??ch h??ng t??? Database
		KhachHangBLL khBLL = new KhachHangBLL();
		ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
		dsKhachHang = khBLL.selectKH();
		DefaultComboBoxModel<String> cbMaKH = new DefaultComboBoxModel<String>();
		for(KhachHangDTO kh: dsKhachHang) {
			cbMaKH.addElement(kh.getHoTenKH());
		}


		// T??nh tr???ng ho?? ????n
		DefaultComboBoxModel<String> cbTinhTrangHD = new DefaultComboBoxModel<String>();
		cbTinhTrangHD.addElement("??ang ti???n h??nh");
		cbTinhTrangHD.addElement("???? thanh to??n");


		comboMaKH = new JComboBox();
		comboMaKH.setBounds(394, 27, 153, 22);
		panelThongTinHoaDon.add(comboMaKH);

		comboMaKH.setModel(cbMaKH);

		comboMaNV = new JComboBox();
		comboMaNV.setBounds(653, 27, 153, 22);
		panelThongTinHoaDon.add(comboMaNV);

		comboMaNV.setModel(cbMaNV);

		comboTinhTrangHD = new JComboBox();
		comboTinhTrangHD.setBounds(653, 73, 153, 22);
		panelThongTinHoaDon.add(comboTinhTrangHD);

		comboTinhTrangHD.setModel(cbTinhTrangHD);

		// panelThongTinSanPham ---------------------------------------------------------------------------
		panelThongTinSanPham = new JPanel();
		panelThongTinSanPham.setBorder(new TitledBorder(null, "Th\u00F4ng tin chi ti\u1EBFt ho\u00E1 \u0111\u01A1n", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		panelThongTinSanPham.setBounds(866, 11, 763, 167);
		panelHoaDon.add(panelThongTinSanPham);
		panelThongTinSanPham.setLayout(null);

		JLabel lblMaSP = new JLabel("M?? s???n ph???m:");
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaSP.setBounds(396, 33, 86, 17);
		panelThongTinSanPham.add(lblMaSP);
		
		JLabel lblDonGia = new JLabel("????n gi??:");
		lblDonGia.setBounds(134, 74, 52, 17);
		panelThongTinSanPham.add(lblDonGia);
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));


		JLabel lblSoLuong = new JLabel("S??? l?????ng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoLuong.setBounds(396, 74, 60, 17);
		panelThongTinSanPham.add(lblSoLuong);

		txtMaSP = new JTextField();
		txtMaSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaSP.setBounds(492, 30, 139, 23);
		panelThongTinSanPham.add(txtMaSP);
		txtMaSP.setColumns(10);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(230, 71, 139, 23);
		panelThongTinSanPham.add(txtDonGia);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(492, 74, 139, 20);
		panelThongTinSanPham.add(txtSoLuong);
		
		lblMaHD_CTHD = new JLabel("M?? ho?? ????n:");
		lblMaHD_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHD_CTHD.setBounds(134, 33, 86, 17);
		panelThongTinSanPham.add(lblMaHD_CTHD);
		
		lblTenSP = new JLabel("T??n s???n ph???m:");
		lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenSP.setBounds(134, 119, 97, 17);
		panelThongTinSanPham.add(lblTenSP);
		
		txtMaHD_CTHD = new JTextField();
		txtMaHD_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMaHD_CTHD.setColumns(10);
		txtMaHD_CTHD.setBounds(230, 30, 139, 23);
		panelThongTinSanPham.add(txtMaHD_CTHD);
		
		txtTenSP = new JTextField();
		txtTenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTenSP.setColumns(10);
		txtTenSP.setBounds(230, 116, 401, 23);
		panelThongTinSanPham.add(txtTenSP);
		
		// T???ng ti???n
		JLabel lblSum = new JLabel("T???ng ti???n:");
		lblSum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSum.setBounds(652, 239, 78, 17);
		panelHoaDon.add(lblSum);

		lblTongTien = new JLabel("...");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongTien.setBounds(740, 239, 107, 17);
		panelHoaDon.add(lblTongTien);


		lblBangSP = new JLabel("B???ng s???n ph???m");
		lblBangSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBangSP.setBounds(10, 657, 107, 17);
		panelHoaDon.add(lblBangSP);



		lblBangHD = new JLabel("B???ng ho?? ????n");
		lblBangHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBangHD.setBounds(10, 239, 107, 17);
		panelHoaDon.add(lblBangHD);



		lblBangCTHD = new JLabel("B???ng chi ti???t ho?? ????n");
		lblBangCTHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBangCTHD.setBounds(876, 239, 158, 17);
		panelHoaDon.add(lblBangCTHD);
		panelHoaDon.setLayout(null);
		
		
		// B???ng s???n ph???m ---------------------------------------------------------------------------
		scrollPaneTableSanPham = new JScrollPane();
		scrollPaneTableSanPham.setBounds(10, 685, 1619, 263);
		panelHoaDon.add(scrollPaneTableSanPham);
		
				tableSanPham = new JTable();
				scrollPaneTableSanPham.setViewportView(tableSanPham);
				tableSanPham.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel dtm = (DefaultTableModel) tableSanPham.getModel();
						int vitri = tableSanPham.getSelectedRow();
						txtMaSP.setText(dtm.getValueAt(vitri, 0).toString());
						txtTenSP.setText(dtm.getValueAt(vitri, 1).toString());
						txtDonGia.setText(dtm.getValueAt(vitri, 2).toString());
						//	txtSoLuong.setText(dtm.getValueAt(vitri, 3).toString());
						txtSoLuong.setText("1");

					}
				});
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSanPham.setModel(dataModel_SanPham);
		
		
		// B???ng chi ti???t ho?? ????n -------------------------------------------------------------------
		scrollPaneTableChiTietHD = new JScrollPane();
		scrollPaneTableChiTietHD.setBounds(876, 267, 753, 379);
		panelHoaDon.add(scrollPaneTableChiTietHD);
		
				tableChiTietHoaDon = new JTable();
				scrollPaneTableChiTietHD.setViewportView(tableChiTietHoaDon);
				tableChiTietHoaDon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel dtm = (DefaultTableModel) tableChiTietHoaDon.getModel();
						int vitri = tableChiTietHoaDon.getSelectedRow();
						txtMaHD_CTHD.setText(dtm.getValueAt(vitri, 0).toString());
						txtMaSP.setText(dtm.getValueAt(vitri, 1).toString());
						txtDonGia.setText(dtm.getValueAt(vitri, 2).toString());
						txtSoLuong.setText(dtm.getValueAt(vitri, 3).toString());
						
						
						// L???y m?? s???n ph???m r???i d???a v??o m?? l???y t??n s???n ph???m
						SanPhamBLL spBLL = new SanPhamBLL();
						String maSP = txtMaSP.getText();
						String tenSP = spBLL.selectTenSP(maSP);
						
						txtTenSP.setText(tenSP);

						
						
					}
				});
		tableChiTietHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableChiTietHoaDon.setModel(dataModel_ChiTietHD);
		
		JButton btnThemCTHD = new JButton("Th??m s???n ph???m");
		btnThemCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThemCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try 
				{
					String maHD = txtMaHD.getText();
					String maSP = txtMaSP.getText();
					String strDonGia = txtDonGia.getText();
					int donGia = Integer.parseInt(strDonGia);  // Chuy???n sang ki???u int
					String strSoLuong = txtSoLuong.getText();
					int soLuong = Integer.parseInt(strSoLuong);
					for(int i=0; i<tableChiTietHoaDon.getRowCount(); i++) { // Tr??ng m?? nh??n vi??n
						if(maSP.equals(dataModel_ChiTietHD.getValueAt(i, 1))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							throw new Exception();
						}
					}
					if(maHD.isBlank() || maSP.isBlank() || strDonGia.isBlank() || strSoLuong.isBlank()) {
						throw new Exception();
					}

					else {
						ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
						ChiTietHD_DTO cthd = new ChiTietHD_DTO(maHD, maSP, donGia, soLuong);
						boolean themDuoc = cthdBLL.insert(cthd);
						if(themDuoc) {
							JOptionPane.showMessageDialog(null, "Th??m th???t b???i", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetCTHD();
							hienDLBangCTHD();
						}

					}

				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nh???p ?????y ????? th??ng tin, m?? ho?? ????n v?? m?? s???n ph???m kh??ng ???????c tr??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}


			}
		});
		btnThemCTHD.setBounds(893, 189, 122, 25);
		panelHoaDon.add(btnThemCTHD);

		btnXoaCTHD = new JButton("X??a s???n ph???m");
		btnXoaCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String maHD = txtMaHD.getText();
					String maSP = txtMaSP.getText();
					
					if(maHD.isBlank() || maSP.isBlank()) {
						throw new Exception();
					}
					
					else {
						ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
						cthdBLL.delete(maHD, maSP);
						JOptionPane.showMessageDialog(null, "Xo?? th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						resetCTHD();
						hienDLBangCTHD();

					}
				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ch???n h??ng c???n xo??", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnXoaCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoaCTHD.setBounds(1045, 189, 122, 25);
		panelHoaDon.add(btnXoaCTHD);
		
		btnSuaCTHD = new JButton("S???a s???n ph???m");
		btnSuaCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				try {
					String maHD = txtMaHD_CTHD.getText();
					String maSP = txtMaSP.getText();
					String strDonGia = txtDonGia.getText();
					int donGia = Integer.parseInt(strDonGia);
					String strSoLuong = txtSoLuong.getText();
					int soLuong = Integer.parseInt(strSoLuong);
					
					if(maHD.isBlank() || maSP.isBlank() || strDonGia.isBlank() || strSoLuong.isBlank()) {
						throw new Exception();
					}
					
					for(int i=0; i<tableChiTietHoaDon.getRowCount(); i++) {
						if(maHD.equals(dataModel_ChiTietHD.getValueAt(i, 0)) || maSP.equals(dataModel_ChiTietHD.getValueAt(i, 1))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
						ChiTietHD_DTO cthd = new ChiTietHD_DTO(maHD, maSP, donGia, soLuong);
						boolean suaDuoc = cthdBLL.update(cthd);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "S???a kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "S???a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetCTHD();
							hienDLBangCTHD();
						}
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "M?? ho?? ????n kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
				}
			}
		});
		btnSuaCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSuaCTHD.setBounds(1194, 189, 122, 25);
		panelHoaDon.add(btnSuaCTHD);
		
		btnHienLaiCTHD = new JButton("L??m m???i");
		btnHienLaiCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienDLBangCTHD();
			}
		});
		btnHienLaiCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHienLaiCTHD.setBounds(1522, 236, 107, 25);
		panelHoaDon.add(btnHienLaiCTHD);
		
		
		// B???ng ho?? ????n -------------------------------------------------------------------
		scrollPaneTableHoaDon = new JScrollPane();
		scrollPaneTableHoaDon.setBounds(10, 267, 837, 379);
		panelHoaDon.add(scrollPaneTableHoaDon);
		
				tableHoaDon = new JTable();
				scrollPaneTableHoaDon.setViewportView(tableHoaDon);
				tableHoaDon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						DefaultTableModel dtm = (DefaultTableModel) tableHoaDon.getModel(); // Cast (DefaultTableModel) - Tr??nh b??? l???i khi click v??ng tr???ng trong b???ng
						int vitri = tableHoaDon.getSelectedRow();
						String maHD = dtm.getValueAt(vitri, 0).toString();

						// 
						txtMaHD.setText(dtm.getValueAt(vitri, 0).toString());
						txtMaHD_CTHD.setText(dtm.getValueAt(vitri, 0).toString());
						txtDiaChiGiao.setText(dtm.getValueAt(vitri, 3).toString());
						//
						
						// Hi???n th??? date tr??n jDatechooser khi click v??o b???ng
						String date = dtm.getValueAt(vitri, 2).toString();
						java.util.Date ngayDat = new Date();
						java.util.Date ngayGiao = new Date();
						try 
						{
							ngayDat = new SimpleDateFormat("yyyy-MM-dd").parse((String)dtm.getValueAt(vitri, 1));
							ngayGiao = new SimpleDateFormat("yyyy-MM-dd").parse((String)dtm.getValueAt(vitri, 2));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtNgayDat.setDate(ngayDat);
						txtNgayGiao.setDate(ngayGiao);
						
						NhanVienBLL nvBLL = new NhanVienBLL();
						KhachHangBLL khBLL = new KhachHangBLL();
						
						// L???y t??n kh??ch h??ng qua m?? kh??ch h??ng
						String maKH = dtm.getValueAt(vitri, 4).toString();
						String tenKH = khBLL.selectHoTen(maKH);
						
						// L???y t??n nh??n vi??n qua m?? nh??n vi??n
						String maNV = dtm.getValueAt(vitri, 5).toString();
						String tenNV = nvBLL.selectHoTen(maNV);
						
						// Set Combobox hi???n th??? t??n theo m??
						comboMaKH.setSelectedItem(tenKH);
						comboMaNV.setSelectedItem(tenNV);
						comboTinhTrangHD.setSelectedItem(dtm.getValueAt(vitri, 6).toString());
					
						ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
						

						// Hi???n th??? b???ng chi ti???t ho?? ????n theo m?? ho?? ????n b??n ho?? ????n
						dataModel_ChiTietHD.setRowCount(0);

						ArrayList<ChiTietHD_DTO> dsChiTietHD = new ArrayList<ChiTietHD_DTO>();
						dsChiTietHD = cthdBLL.findByID(maHD);
						for(ChiTietHD_DTO cthd: dsChiTietHD) {
							Vector<Object> row = new Vector<>();
							row.add(cthd.getMaHD());
							row.add(cthd.getMaSP());
							row.add(cthd.getDonGia());
							row.add(cthd.getSoLuong());

							dataModel_ChiTietHD.addRow(row);
						}
						tableChiTietHoaDon.setModel(dataModel_ChiTietHD);
						tableChiTietHoaDon.updateUI();

						// T??nh t???ng ti???n
						int tongTien = 0;
						for(int i =1; i<tableChiTietHoaDon.getRowCount(); i++) {
							int amount = Integer.parseInt(tableChiTietHoaDon.getValueAt(i, 2).toString());
							int soluong = Integer.parseInt(tableChiTietHoaDon.getValueAt(i, 3).toString());
							tongTien = tongTien + (amount * soluong);
						}
						lblTongTien.setText(String.valueOf(tongTien));
					}
				});


		tableHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableHoaDon.setModel(dataModel_HoaDon);

		JButton btnThemHD = new JButton("Th??m ho?? ????n");
		btnThemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHangBLL khBLL = new KhachHangBLL();
				NhanVienBLL nvBLL = new NhanVienBLL();
				try {

					String maHD = txtMaHD.getText();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Format ????? gi???ng ki???u Date trong SQL
					String ngayDat = df.format(txtNgayDat.getDate());
					String ngayGiao = df.format(txtNgayGiao.getDate());
					String diaChiGiao = txtDiaChiGiao.getText();

					// L???y t??n kh??ch h??ng r???i d???a v??o t??n l???y m?? kh??ch h??ng
					String tenKH = comboMaKH.getSelectedItem().toString();
					String maKH = khBLL.selectMaKH(tenKH);

					// L???y t??n nh??n vi??n r???i d???a v??o t??n l???y m?? nh??n vi??n
					String tenNV = comboMaNV.getSelectedItem().toString();
					String maNV = nvBLL.selectMaNV(tenNV);

					String tinhTrang = comboTinhTrangHD.getSelectedItem().toString();

					for(int i=0; i<tableHoaDon.getRowCount(); i++) {
						if(maHD.equals(dataModel_HoaDon.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							throw new Exception();
						}
					}
					if(maHD.isBlank() || ngayDat.isBlank() || ngayGiao.isBlank() || diaChiGiao.isBlank() || tinhTrang.isBlank()) {
						throw new Exception();
					}

					HoaDonBLL hdBLL = new HoaDonBLL();
					HoaDonDTO hd = new HoaDonDTO(maHD, ngayDat, ngayGiao, diaChiGiao, maKH, maNV, tinhTrang);
					hdBLL.insert(hd);
					JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					resetHD();
					hienDLBangHD();
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nh???p ?????y ????? th??ng tin, m?? ho?? ????n kh??ng ???????c tr??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThemHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThemHD.setBounds(33, 189, 122, 25);
		panelHoaDon.add(btnThemHD);

		btnXoaHD = new JButton("X??a ho?? ????n");
		btnXoaHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				try 
				{
					String maHD = txtMaHD.getText();

					if(maHD.isBlank()) {
						throw new Exception();
					}
					for(int i=0; i<tableHoaDon.getRowCount(); i++) {
						if(maHD.equals(dataModel_HoaDon.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						HoaDonBLL hdBLL = new HoaDonBLL();
						hdBLL.delete(maHD);
						JOptionPane.showMessageDialog(null, "Xo?? th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						resetHD();
						hienDLBangHD();
					}
					else {
						JOptionPane.showMessageDialog(null, "M?? ho?? ????n kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ch???n h??ng c???n xo??", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoaHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoaHD.setBounds(184, 189, 122, 25);
		panelHoaDon.add(btnXoaHD);
		
		JButton btnSuaHD = new JButton("S???a ho?? ????n");
		btnSuaHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				NhanVienBLL nvBLL = new NhanVienBLL();
				KhachHangBLL khBLL = new KhachHangBLL();
				try {
					String maHD = txtMaHD.getText();
					
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Format ????? gi???ng ki???u Date trong SQL
					String ngayDat = df.format(txtNgayDat.getDate());
					String ngayGiao = df.format(txtNgayGiao.getDate());
					
					String diaChiGiao = txtDiaChiGiao.getText();
					
					// L???y m?? d???a theo t??n
					String tenKH = comboMaKH.getSelectedItem().toString();
					String maKH = khBLL.selectMaKH(tenKH);
					
					// L???y m?? d???a theo t??n
					String tenNV = comboMaNV.getSelectedItem().toString();
					String maNV = nvBLL.selectMaNV(tenNV);
					
					String tinhTrang = comboTinhTrangHD.getSelectedItem().toString();
					
					if(maHD.isBlank() || ngayDat.isBlank() || ngayGiao.isBlank() || diaChiGiao.isBlank() || tenKH.isBlank() || tenNV.isBlank() || tinhTrang.isBlank()) {
						throw new Exception();
					}
					
					for(int i=0; i<tableHoaDon.getRowCount(); i++) {
						if(maHD.equals(dataModel_HoaDon.getValueAt(i, 0))) { // toString ????? tr??? v??? chu???i (N???u ki???u int)
							result = true;
							break;
						}
					}
					if(result == true) {
						HoaDonBLL hdBLL = new HoaDonBLL();
						HoaDonDTO hd = new HoaDonDTO(maHD, ngayDat, ngayGiao, diaChiGiao, maKH, maNV, tinhTrang);
						boolean suaDuoc = hdBLL.update(hd);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "S???a kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "S???a th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
							resetHD();
							hienDLBangHD();
						}
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "M?? ho?? ????n kh??ng t???n t???i trong b???ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nh???p ?????y ????? th??ng tin v?? ch???n h??ng c???n s???a", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnSuaHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSuaHD.setBounds(333, 189, 122, 25);
		panelHoaDon.add(btnSuaHD);

		
		
		
		
	
		
		// ????? ??? d?????i c??ng tr??nh b??o l???i ch??a setModel => null
		hienDLBangHD(); // Hi???n th??? d??? li???u ho?? ????n

		hienDLBangCTHD(); // Hi???n th??? d??? li???u chi ti???t ho?? ????n

		hienDLBangSP(); // Hi???n th??? d??? li???u s???n ph???m

		hienDLBangKH(); // Hi???n th??? d??? li???u kh??ch h??ng

	}

	public void resetHD() {
		txtMaHD.setText("");
		txtNgayDat.setDate(new Date());
		txtNgayGiao.setDate(new Date());
		//	txtNgayDat.setCalendar(null); // Field tr???ng
		//	txtNgayGiao.setCalendar(null);
		txtDiaChiGiao.setText("");
		comboMaKH.setSelectedIndex(1);
		comboMaNV.setSelectedIndex(1);
		comboTinhTrangHD.setSelectedIndex(1);
	}
	
	public void resetCTHD() {
		txtMaHD_CTHD.setText("");
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		
	}

	public void hienDLBangHD() {
		try 
		{
			dataModel_HoaDon.setRowCount(0); // ????? hi???n th??? l???i table sau khi Th??m - Xo?? - S???a
			HoaDonBLL hdBLL = new HoaDonBLL();
			ArrayList<HoaDonDTO> dsHoaDon = new ArrayList<HoaDonDTO>();
			dsHoaDon = hdBLL.selectAll();
			for(HoaDonDTO hd: dsHoaDon) {
				Vector<Object> row = new Vector<>();
				row.add(hd.getMaHD());
				row.add(hd.getNgayDat());
				row.add(hd.getNgayGiao());
				row.add(hd.getDiaChiGiao());
				row.add(hd.getMaKH());
				row.add(hd.getMaNV());
				row.add(hd.getTinhTrang());
				dataModel_HoaDon.addRow(row);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void hienDLBangCTHD() {
		try 
		{
			dataModel_ChiTietHD.setRowCount(0);
			ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
			ArrayList<ChiTietHD_DTO> dsChiTietHD = new ArrayList<ChiTietHD_DTO>();
			dsChiTietHD = cthdBLL.selectAll();
			for(ChiTietHD_DTO cthd: dsChiTietHD) {
				Vector<Object> row = new Vector<>();
				row.add(cthd.getMaHD());
				row.add(cthd.getMaSP());
				row.add(cthd.getDonGia());
				row.add(cthd.getSoLuong());
				dataModel_ChiTietHD.addRow(row);
			}
			tableChiTietHoaDon.setModel(dataModel_ChiTietHD);
			tableChiTietHoaDon.updateUI();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void hienDLBangKH() {
		try 
		{
			dataModel_KhachHang.setRowCount(0); // ????? hi???n th??? l???i table sau khi Th??m - Xo?? - S???a
			KhachHangBLL khBLL = new KhachHangBLL();
			ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
			dsKhachHang = khBLL.selectAll();
			for(KhachHangDTO kh: dsKhachHang) {
				Vector<Object> row = new Vector<>(); // Th??m d??? li???u t???ng c???t
				row.add(kh.getMaKH());
				row.add(kh.getHoTenKH());
				row.add(kh.getSDT());
				row.add(kh.getDiaChi());
				row.add(kh.getTenDN());
				row.add(kh.getMatKhau());
				row.add(kh.getQuyen());
				dataModel_KhachHang.addRow(row);
			}


		}
		catch (Exception ex) {
			// TODO: handle exception
			System.err.print(ex.getMessage());
		}
	}

	public void hienDLBangSP() {
		try 
		{
			dataModel_SanPham.setRowCount(0); // ????? hi???n th??? l???i table sau khi Th??m - Xo?? - S???a
			SanPhamBLL spBLL = new SanPhamBLL();
			ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
			dsSanPham = spBLL.select();
			for(SanPhamDTO sp: dsSanPham) {
				Vector<Object> row = new Vector<>(); // Th??m d??? li???u t???ng c???t
				row.add(sp.getMaSP());
				row.add(sp.getTenSP());
				row.add(sp.getDonGia());
				row.add(sp.getSoLuong());

				dataModel_SanPham.addRow(row);
			}


		}
		catch (Exception ex) {
			// TODO: handle exception
			System.err.print(ex.getMessage());
		}
	}
}
