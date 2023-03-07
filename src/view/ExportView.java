package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.EquiqmentDao;
import database.ExportDao;
import entity.Equiqment;
import entity.Export;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ExportView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtaddr;
	private JTextField txtequiq;
	private JTextField txtquantity;
	private JTextField txtprice;
	DefaultTableModel model;
	DefaultTableModel model1;
	private EquiqmentDao equiqmentDao = new EquiqmentDao();
	private static List<Equiqment> listEquiqments = new ArrayList<Equiqment>();
	private static String equiqCode = null;

	private ExportDao exportDao = new ExportDao();
	private static List<Export> listExport = new ArrayList<Export>();
	private static int exportId = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportView frame = new ExportView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExportView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "thi\u1EBFt b\u1ECB \u0111\u00E3 thanh l\u00FD", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 32, 540, 250);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 520, 217);
		panel.add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "danh s\u00E1ch thi\u1EBFt b\u1ECB", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 305, 540, 283);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 520, 238);
		panel_1.add(scrollPane);

		JTable table1 = new JTable();
		scrollPane.setViewportView(table1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "b\u1EA3ng \u0111i\u1EC1u khi\u1EC3n", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(560, 32, 272, 556);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("tên khách hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));  //bold là chữ đậm
		lblNewLabel.setBounds(23, 33, 110, 23);
		panel_2.add(lblNewLabel);

		txtname = new JTextField();
		txtname.setBounds(136, 35, 126, 20);
		panel_2.add(txtname);
		txtname.setColumns(10);

		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(136, 72, 126, 20);
		panel_2.add(txtphone);

		JLabel lblSinThoi = new JLabel("số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSinThoi.setBounds(23, 70, 110, 23);
		panel_2.add(lblSinThoi);

		txtaddr = new JTextField();
		txtaddr.setColumns(10);
		txtaddr.setBounds(136, 105, 126, 20);
		panel_2.add(txtaddr);

		JLabel lblaCh = new JLabel("địa chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblaCh.setBounds(23, 103, 110, 23);
		panel_2.add(lblaCh);

		txtequiq = new JTextField();
		txtequiq.setColumns(10);
		txtequiq.setBounds(136, 138, 126, 20);
		panel_2.add(txtequiq);

		JLabel lblMThitB = new JLabel("tên thiết bị");
		lblMThitB.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMThitB.setBounds(23, 136, 110, 23);
		panel_2.add(lblMThitB);

		txtquantity = new JTextField();
		txtquantity.setColumns(10);
		txtquantity.setBounds(136, 174, 126, 20);
		panel_2.add(txtquantity);

		JLabel lblSLng = new JLabel("số lượng");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSLng.setBounds(23, 172, 110, 23);
		panel_2.add(lblSLng);

		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(136, 207, 126, 20);
		panel_2.add(txtprice);

		JLabel lblGiThanhL = new JLabel("giá thanh lý");
		lblGiThanhL.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiThanhL.setBounds(23, 205, 110, 23);
		panel_2.add(lblGiThanhL);

		JButton btnThm = new JButton("thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equiqCode != null) {
					if (exportDao.insert(
							new Export(txtname.getText(), txtphone.getText(), txtaddr.getText(), null,
									Integer.valueOf(txtquantity.getText()), new BigDecimal(txtprice.getText())),
							equiqCode)) {                         
						Noti.getMessage("them thanh cong");
						refresh();
					}
				} else {
					Noti.getMessage("hay chon 1 thiet bi");
				}
			}
		});
		btnThm.setBounds(23, 251, 89, 23);
		panel_2.add(btnThm);

		JButton btnSa = new JButton("sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exportId != -1) {
					if (exportDao.update(exportId, Integer.valueOf(txtquantity.getText()), txtname.getText(),
							txtphone.getText(), txtaddr.getText(), new BigDecimal(txtprice.getText()))) {
						Noti.getMessage("sua thanh cong");
						refresh();
					}
				} else {
					Noti.getMessage("hay chon 1 hoa don xuat");
				}
			}
		});
		btnSa.setBounds(144, 251, 89, 23);
		panel_2.add(btnSa);

		JButton btnXa = new JButton("xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (exportId != -1) {
					if (exportDao.delete(exportId)) {
						Noti.getMessage("xoa thanh cong");
						refresh();
					}
				}
				else {
					Noti.getMessage("hay chon 1 hoa don");
				}
			}
		});
		btnXa.setBounds(23, 292, 89, 23);
		panel_2.add(btnXa);

		JButton btnLmMi = new JButton("làm mới");
		btnLmMi.setBounds(144, 292, 89, 23);
		panel_2.add(btnLmMi);

		JButton btnQunLThit = new JButton("quản lý thiết bị");
		btnQunLThit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Home frame = new Home();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnQunLThit.setBounds(23, 329, 210, 23);
		panel_2.add(btnQunLThit);

		/*
		 * table thiet bi
		 */
		model1 = new DefaultTableModel();
		Object[] column = { "mã thiết bị", "tên thiết bị", "số lượng còn", "giá nhập", "ngày nhập", "loại hàng" };
		model1.setColumnIdentifiers(column);
		table1.setModel(model1);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listEquiqments = equiqmentDao.getListEquiqments();
		for (Equiqment p : listEquiqments) {
			model1.addRow(new Object[] { p.getCode(), p.getName(), p.getQuantity(), p.getPrice(), p.getCreateddate(),
					p.getType() });
		}
		table1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
					table.setSelectionBackground(Color.YELLOW);
					equiqCode = table.getValueAt(table.getSelectedRow(), 0).toString();
				}
			}
		});

		/*
		 * table xuat
		 */
		model = new DefaultTableModel();
		Object[] column1 = { "id", "tên khách hàng", "số đt", "địa chỉ", "mặt hàng", "ngày thanh lý", "số lượng",
				"giá thanh lý" };
		model.setColumnIdentifiers(column1);
		table.setModel(model);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listExport = exportDao.getListExport();
		initTable();
		clickRow();
	}

	public void clickRow() {
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
					table.setSelectionBackground(Color.green);
					exportId = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
					txtaddr.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					txtname.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtphone.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					txtprice.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					txtquantity.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					txtequiq.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					txtequiq.setEnabled(false);
				}
			}
		});
	}

	public void initTable() {
		for (Export p : listExport) {
			model.addRow(new Object[] { p.getId(), p.getCusName(), p.getPhone(), p.getAddress(),
					p.getEquiqment().getName(), p.getExportDay(), p.getQuantity(), p.getExportPrice() });
		}
	}

	public void deleteTable() {
		int rowCount = model.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		equiqCode = null;
	}

	public void clearInput() {
		txtaddr.setText("");
		txtname.setText("");
		txtprice.setText("");
		txtquantity.setText("");
		txtequiq.setEnabled(true);
		equiqCode = null;
		exportId = -1;
	}

	public void refresh() {
		clearInput();
		deleteTable();
		listExport = exportDao.getListExport();
		initTable();
		int rowCount = model1.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model1.removeRow(i);
		}
		listEquiqments = equiqmentDao.getListEquiqments();
		for (Equiqment p : listEquiqments) {
			model1.addRow(new Object[] { p.getCode(), p.getName(), p.getQuantity(), p.getPrice(), p.getCreateddate(),
					p.getType() });
		}
	}
}
