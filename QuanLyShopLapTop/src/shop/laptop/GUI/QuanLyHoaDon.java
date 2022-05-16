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
		setTitle("Quản Lý Sản Phẩm");
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
		tabbedPane.addTab("Lập hoá đơn", null, panelHoaDon, null);
		panelHoaDon.setLayout(null);
		
		panelThanhToan = new JPanel();
		tabbedPane.addTab("Thanh toán", null, panelThanhToan, null);
		panelThanhToan.setLayout(null);
		
		// panelThanhToan -----------------------------------------------------------------	
		
		
		// panelHoaDon --------------------------------------------------------------------		

		// Bảng sản phẩm
		dataModel_SanPham = new DefaultTableModel();
		// Qui định Cột
		dataModel_SanPham.addColumn("Mã sản phẩm");
		dataModel_SanPham.addColumn("Tên sản phẩm");
		dataModel_SanPham.addColumn("Đơn giá");
		dataModel_SanPham.addColumn("Số lượng");
		
		// Bảng chi tiết hoá đơn
		dataModel_ChiTietHD = new DefaultTableModel();
		// Qui định Cột
		dataModel_ChiTietHD.addColumn("Mã hoá đơn");
		dataModel_ChiTietHD.addColumn("Mã sản phẩm");
		dataModel_ChiTietHD.addColumn("Đơn giá");
		dataModel_ChiTietHD.addColumn("Số lượng");

		// Bảng hoá đơn
		dataModel_HoaDon = new DefaultTableModel();
		// Qui định Cột
		dataModel_HoaDon.addColumn("Mã hoá đơn");
		dataModel_HoaDon.addColumn("Ngày đặt");
		dataModel_HoaDon.addColumn("Ngày giao");
		dataModel_HoaDon.addColumn("Địa chỉ giao");
		dataModel_HoaDon.addColumn("Mã khách hàng");
		dataModel_HoaDon.addColumn("Mã nhân viên");
		dataModel_HoaDon.addColumn("Tình trạng");

		// Bảng khách hàng
		// DefaultTableModel - Các phương thức addColumn - addRow
		dataModel_KhachHang = new DefaultTableModel();
		// Qui định Cột
		dataModel_KhachHang.addColumn("Mã khách hàng");
		dataModel_KhachHang.addColumn("Họ tên khách hàng");
		dataModel_KhachHang.addColumn("Số điện thoại");
		dataModel_KhachHang.addColumn("Địa chỉ");		

		// panelThongTinHoaDon -------------------------------------------------------------------------------------------------
		panelThongTinHoaDon = new JPanel();
		panelThongTinHoaDon.setBorder(new TitledBorder(null, "Th\u00F4ng tin ho\u00E1 \u0111\u01A1n", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLUE));
		panelThongTinHoaDon.setBounds(10, 11, 837, 167);
		panelHoaDon.add(panelThongTinHoaDon);
		panelThongTinHoaDon.setLayout(null);

		lblMaHD = new JLabel("Mã hoá đơn:");
		lblMaHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHD.setBounds(33, 32, 86, 17);
		panelThongTinHoaDon.add(lblMaHD);

		lblNgayDat = new JLabel("Ngày đặt:");
		lblNgayDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayDat.setBounds(33, 78, 77, 17);
		panelThongTinHoaDon.add(lblNgayDat);

		lblNgayGiao = new JLabel("Ngày giao:");
		lblNgayGiao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayGiao.setBounds(306, 78, 77, 17);
		panelThongTinHoaDon.add(lblNgayGiao);

		lblKhachHangDat = new JLabel("Khách hàng:");
		lblKhachHangDat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhachHangDat.setBounds(306, 32, 78, 17);
		panelThongTinHoaDon.add(lblKhachHangDat);

		lblNhanVienLap = new JLabel("Nhân viên:");
		lblNhanVienLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhanVienLap.setBounds(576, 30, 77, 17);
		panelThongTinHoaDon.add(lblNhanVienLap);

		JLabel lblDiaChiGiao = new JLabel("Địa chỉ giao:");
		lblDiaChiGiao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChiGiao.setBounds(31, 116, 79, 17);
		panelThongTinHoaDon.add(lblDiaChiGiao);

		JLabel lblTinhTrangHD = new JLabel("Tình trạng:");
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
		
		// Lấy mã và tên nhân viên từ Database
		NhanVienBLL nvBLL = new NhanVienBLL();
		ArrayList<NhanVienDTO> dsNhanVien = new ArrayList<NhanVienDTO>();
		dsNhanVien = nvBLL.selectNV();
		DefaultComboBoxModel<String> cbMaNV = new DefaultComboBoxModel<String>();
		for(NhanVienDTO nv: dsNhanVien) {
			cbMaNV.addElement(nv.getHoTenNV());
		}

		//Lấy mã và tên khách hàng từ Database
		KhachHangBLL khBLL = new KhachHangBLL();
		ArrayList<KhachHangDTO> dsKhachHang = new ArrayList<KhachHangDTO>();
		dsKhachHang = khBLL.selectKH();
		DefaultComboBoxModel<String> cbMaKH = new DefaultComboBoxModel<String>();
		for(KhachHangDTO kh: dsKhachHang) {
			cbMaKH.addElement(kh.getHoTenKH());
		}


		// Tình trạng hoá đơn
		DefaultComboBoxModel<String> cbTinhTrangHD = new DefaultComboBoxModel<String>();
		cbTinhTrangHD.addElement("Đang tiến hành");
		cbTinhTrangHD.addElement("Đã thanh toán");


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

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaSP.setBounds(396, 33, 86, 17);
		panelThongTinSanPham.add(lblMaSP);
		
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(134, 74, 52, 17);
		panelThongTinSanPham.add(lblDonGia);
		lblDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));


		JLabel lblSoLuong = new JLabel("Số lượng:");
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
		
		lblMaHD_CTHD = new JLabel("Mã hoá đơn:");
		lblMaHD_CTHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHD_CTHD.setBounds(134, 33, 86, 17);
		panelThongTinSanPham.add(lblMaHD_CTHD);
		
		lblTenSP = new JLabel("Tên sản phẩm:");
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
		
		// Tổng tiền
		JLabel lblSum = new JLabel("Tổng tiền:");
		lblSum.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSum.setBounds(652, 239, 78, 17);
		panelHoaDon.add(lblSum);

		lblTongTien = new JLabel("...");
		lblTongTien.setForeground(Color.RED);
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongTien.setBounds(740, 239, 107, 17);
		panelHoaDon.add(lblTongTien);


		lblBangSP = new JLabel("Bảng sản phẩm");
		lblBangSP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBangSP.setBounds(10, 657, 107, 17);
		panelHoaDon.add(lblBangSP);



		lblBangHD = new JLabel("Bảng hoá đơn");
		lblBangHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBangHD.setBounds(10, 239, 107, 17);
		panelHoaDon.add(lblBangHD);



		lblBangCTHD = new JLabel("Bảng chi tiết hoá đơn");
		lblBangCTHD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBangCTHD.setBounds(876, 239, 158, 17);
		panelHoaDon.add(lblBangCTHD);
		panelHoaDon.setLayout(null);
		
		
		// Bảng sản phẩm ---------------------------------------------------------------------------
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
		
		
		// Bảng chi tiết hoá đơn -------------------------------------------------------------------
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
						
						
						// Lấy mã sản phẩm rồi dựa vào mã lấy tên sản phẩm
						SanPhamBLL spBLL = new SanPhamBLL();
						String maSP = txtMaSP.getText();
						String tenSP = spBLL.selectTenSP(maSP);
						
						txtTenSP.setText(tenSP);

						
						
					}
				});
		tableChiTietHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableChiTietHoaDon.setModel(dataModel_ChiTietHD);
		
		JButton btnThemCTHD = new JButton("Thêm sản phẩm");
		btnThemCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThemCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try 
				{
					String maHD = txtMaHD.getText();
					String maSP = txtMaSP.getText();
					String strDonGia = txtDonGia.getText();
					int donGia = Integer.parseInt(strDonGia);  // Chuyển sang kiểu int
					String strSoLuong = txtSoLuong.getText();
					int soLuong = Integer.parseInt(strSoLuong);
					for(int i=0; i<tableChiTietHoaDon.getRowCount(); i++) { // Trùng mã nhân viên
						if(maSP.equals(dataModel_ChiTietHD.getValueAt(i, 1))) { // toString để trả về chuỗi (Nếu kiểu int)
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
							JOptionPane.showMessageDialog(null, "Thêm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetCTHD();
							hienDLBangCTHD();
						}

					}

				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin, mã hoá đơn và mã sản phẩm không được trùng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}


			}
		});
		btnThemCTHD.setBounds(893, 189, 122, 25);
		panelHoaDon.add(btnThemCTHD);

		btnXoaCTHD = new JButton("Xóa sản phẩm");
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
						JOptionPane.showMessageDialog(null, "Xoá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						resetCTHD();
						hienDLBangCTHD();

					}
				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Chọn hàng cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnXoaCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoaCTHD.setBounds(1045, 189, 122, 25);
		panelHoaDon.add(btnXoaCTHD);
		
		btnSuaCTHD = new JButton("Sửa sản phẩm");
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
						if(maHD.equals(dataModel_ChiTietHD.getValueAt(i, 0)) || maSP.equals(dataModel_ChiTietHD.getValueAt(i, 1))) { // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
						ChiTietHD_DTO cthd = new ChiTietHD_DTO(maHD, maSP, donGia, soLuong);
						boolean suaDuoc = cthdBLL.update(cthd);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "Sửa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetCTHD();
							hienDLBangCTHD();
						}
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Mã hoá đơn không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
		
		btnHienLaiCTHD = new JButton("Làm mới");
		btnHienLaiCTHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienDLBangCTHD();
			}
		});
		btnHienLaiCTHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHienLaiCTHD.setBounds(1522, 236, 107, 25);
		panelHoaDon.add(btnHienLaiCTHD);
		
		
		// Bảng hoá đơn -------------------------------------------------------------------
		scrollPaneTableHoaDon = new JScrollPane();
		scrollPaneTableHoaDon.setBounds(10, 267, 837, 379);
		panelHoaDon.add(scrollPaneTableHoaDon);
		
				tableHoaDon = new JTable();
				scrollPaneTableHoaDon.setViewportView(tableHoaDon);
				tableHoaDon.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						DefaultTableModel dtm = (DefaultTableModel) tableHoaDon.getModel(); // Cast (DefaultTableModel) - Tránh bị lỗi khi click vùng trống trong bảng
						int vitri = tableHoaDon.getSelectedRow();
						String maHD = dtm.getValueAt(vitri, 0).toString();

						// 
						txtMaHD.setText(dtm.getValueAt(vitri, 0).toString());
						txtMaHD_CTHD.setText(dtm.getValueAt(vitri, 0).toString());
						txtDiaChiGiao.setText(dtm.getValueAt(vitri, 3).toString());
						//
						
						// Hiển thị date trên jDatechooser khi click vào bảng
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
						
						// Lấy tên khách hàng qua mã khách hàng
						String maKH = dtm.getValueAt(vitri, 4).toString();
						String tenKH = khBLL.selectHoTen(maKH);
						
						// Lấy tên nhân viên qua mã nhân viên
						String maNV = dtm.getValueAt(vitri, 5).toString();
						String tenNV = nvBLL.selectHoTen(maNV);
						
						// Set Combobox hiển thị tên theo mã
						comboMaKH.setSelectedItem(tenKH);
						comboMaNV.setSelectedItem(tenNV);
						comboTinhTrangHD.setSelectedItem(dtm.getValueAt(vitri, 6).toString());
					
						ChiTietHD_BLL cthdBLL = new ChiTietHD_BLL();
						

						// Hiển thị bảng chi tiết hoá đơn theo mã hoá đơn bên hoá đơn
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

						// Tính tổng tiền
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

		JButton btnThemHD = new JButton("Thêm hoá đơn");
		btnThemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHangBLL khBLL = new KhachHangBLL();
				NhanVienBLL nvBLL = new NhanVienBLL();
				try {

					String maHD = txtMaHD.getText();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Format để giống kiểu Date trong SQL
					String ngayDat = df.format(txtNgayDat.getDate());
					String ngayGiao = df.format(txtNgayGiao.getDate());
					String diaChiGiao = txtDiaChiGiao.getText();

					// Lấy tên khách hàng rồi dựa vào tên lấy mã khách hàng
					String tenKH = comboMaKH.getSelectedItem().toString();
					String maKH = khBLL.selectMaKH(tenKH);

					// Lấy tên nhân viên rồi dựa vào tên lấy mã nhân viên
					String tenNV = comboMaNV.getSelectedItem().toString();
					String maNV = nvBLL.selectMaNV(tenNV);

					String tinhTrang = comboTinhTrangHD.getSelectedItem().toString();

					for(int i=0; i<tableHoaDon.getRowCount(); i++) {
						if(maHD.equals(dataModel_HoaDon.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							throw new Exception();
						}
					}
					if(maHD.isBlank() || ngayDat.isBlank() || ngayGiao.isBlank() || diaChiGiao.isBlank() || tinhTrang.isBlank()) {
						throw new Exception();
					}

					HoaDonBLL hdBLL = new HoaDonBLL();
					HoaDonDTO hd = new HoaDonDTO(maHD, ngayDat, ngayGiao, diaChiGiao, maKH, maNV, tinhTrang);
					hdBLL.insert(hd);
					JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					resetHD();
					hienDLBangHD();
				}
				catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin, mã hoá đơn không được trùng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThemHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnThemHD.setBounds(33, 189, 122, 25);
		panelHoaDon.add(btnThemHD);

		btnXoaHD = new JButton("Xóa hoá đơn");
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
						if(maHD.equals(dataModel_HoaDon.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						HoaDonBLL hdBLL = new HoaDonBLL();
						hdBLL.delete(maHD);
						JOptionPane.showMessageDialog(null, "Xoá thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						resetHD();
						hienDLBangHD();
					}
					else {
						JOptionPane.showMessageDialog(null, "Mã hoá đơn không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Chọn hàng cần xoá", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoaHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXoaHD.setBounds(184, 189, 122, 25);
		panelHoaDon.add(btnXoaHD);
		
		JButton btnSuaHD = new JButton("Sửa hoá đơn");
		btnSuaHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				NhanVienBLL nvBLL = new NhanVienBLL();
				KhachHangBLL khBLL = new KhachHangBLL();
				try {
					String maHD = txtMaHD.getText();
					
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // Format để giống kiểu Date trong SQL
					String ngayDat = df.format(txtNgayDat.getDate());
					String ngayGiao = df.format(txtNgayGiao.getDate());
					
					String diaChiGiao = txtDiaChiGiao.getText();
					
					// Lấy mã dựa theo tên
					String tenKH = comboMaKH.getSelectedItem().toString();
					String maKH = khBLL.selectMaKH(tenKH);
					
					// Lấy mã dựa theo tên
					String tenNV = comboMaNV.getSelectedItem().toString();
					String maNV = nvBLL.selectMaNV(tenNV);
					
					String tinhTrang = comboTinhTrangHD.getSelectedItem().toString();
					
					if(maHD.isBlank() || ngayDat.isBlank() || ngayGiao.isBlank() || diaChiGiao.isBlank() || tenKH.isBlank() || tenNV.isBlank() || tinhTrang.isBlank()) {
						throw new Exception();
					}
					
					for(int i=0; i<tableHoaDon.getRowCount(); i++) {
						if(maHD.equals(dataModel_HoaDon.getValueAt(i, 0))) { // toString để trả về chuỗi (Nếu kiểu int)
							result = true;
							break;
						}
					}
					if(result == true) {
						HoaDonBLL hdBLL = new HoaDonBLL();
						HoaDonDTO hd = new HoaDonDTO(maHD, ngayDat, ngayGiao, diaChiGiao, maKH, maNV, tinhTrang);
						boolean suaDuoc = hdBLL.update(hd);
						if(suaDuoc) {
							JOptionPane.showMessageDialog(null, "Sửa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							resetHD();
							hienDLBangHD();
						}
						
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Mã hoá đơn không tồn tại trong bảng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}

				}
				catch (Exception ex) {
					// TODO: handle exception
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nhập đầy đủ thông tin và chọn hàng cần sửa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnSuaHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSuaHD.setBounds(333, 189, 122, 25);
		panelHoaDon.add(btnSuaHD);

		
		
		
		
	
		
		// Để ở dưới cùng tránh báo lỗi chưa setModel => null
		hienDLBangHD(); // Hiển thị dữ liệu hoá đơn

		hienDLBangCTHD(); // Hiển thị dữ liệu chi tiết hoá đơn

		hienDLBangSP(); // Hiển thị dữ liệu sản phẩm

		hienDLBangKH(); // Hiển thị dữ liệu khách hàng

	}

	public void resetHD() {
		txtMaHD.setText("");
		txtNgayDat.setDate(new Date());
		txtNgayGiao.setDate(new Date());
		//	txtNgayDat.setCalendar(null); // Field trống
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
			dataModel_HoaDon.setRowCount(0); // Để hiển thị lại table sau khi Thêm - Xoá - Sửa
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


		}
		catch (Exception ex) {
			// TODO: handle exception
			System.err.print(ex.getMessage());
		}
	}

	public void hienDLBangSP() {
		try 
		{
			dataModel_SanPham.setRowCount(0); // Để hiển thị lại table sau khi Thêm - Xoá - Sửa
			SanPhamBLL spBLL = new SanPhamBLL();
			ArrayList<SanPhamDTO> dsSanPham = new ArrayList<SanPhamDTO>();
			dsSanPham = spBLL.select();
			for(SanPhamDTO sp: dsSanPham) {
				Vector<Object> row = new Vector<>(); // Thêm dữ liệu từng cột
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
