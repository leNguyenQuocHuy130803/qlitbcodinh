package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import database.EquiqmentDao;
import entity.Equiqment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtsearch;
	private JTextField txtcode;
	private JTextField txtname;
	private JTextField txtquantity;
	private JTextField txtprice;
	DefaultTableModel model;
	private EquiqmentDao equiqmentDao = new EquiqmentDao();
	private static List<Equiqment> listEquiqments = new ArrayList<Equiqment>();
	private static String equiqCode = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "danh s\u00E1ch thi\u1EBFt b\u1ECB", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 98, 549, 292);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 529, 252);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtsearch = new JTextField();
		txtsearch.setBounds(10, 67, 450, 20);
		contentPane.add(txtsearch);
		txtsearch.setColumns(10);

		JButton btnNewButton = new JButton("tìm kiếm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnNewButton.setBounds(470, 66, 89, 23);
		contentPane.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u0111i\u1EC1n th\u00F4ng tin", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(572, 11, 277, 379);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMThitB = new JLabel("mã thiết bị");
		lblMThitB.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMThitB.setBounds(25, 38, 86, 23);
		panel_1.add(lblMThitB);

		txtcode = new JTextField();
		txtcode.setBounds(116, 40, 151, 20);
		panel_1.add(txtcode);
		txtcode.setColumns(10);

		JLabel lblTnThitB = new JLabel("tên thiết bị");
		lblTnThitB.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTnThitB.setBounds(25, 75, 86, 23);
		panel_1.add(lblTnThitB);

		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(116, 77, 151, 20);
		panel_1.add(txtname);

		JLabel lblSLng = new JLabel("số lượng");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSLng.setBounds(25, 112, 86, 23);
		panel_1.add(lblSLng);

		txtquantity = new JTextField();
		txtquantity.setColumns(10);
		txtquantity.setBounds(116, 114, 151, 20);
		panel_1.add(txtquantity);

		JLabel lblGiNhp = new JLabel("giá nhập");
		lblGiNhp.setFont(new Font("Tahoma", Font.BOLD, 13));                   
		lblGiNhp.setBounds(25, 149, 86, 23);
		panel_1.add(lblGiNhp);

		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(116, 151, 151, 20);
		panel_1.add(txtprice);

		JLabel lblMThitB_3_1 = new JLabel("tình trạng");
		lblMThitB_3_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMThitB_3_1.setBounds(25, 183, 86, 23);
		panel_1.add(lblMThitB_3_1);

		JComboBox cbtype = new JComboBox();
		cbtype.setModel(new DefaultComboBoxModel(new String[] { "cũ", "mới" }));
		cbtype.setBounds(116, 185, 151, 20);
		panel_1.add(cbtype);

		/*
		 * them 1 thietbi
		 */
		JButton btnThm = new JButton("thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check() == true) {
					if (equiqmentDao.insert(
							new Equiqment(txtcode.getText(), txtname.getText(), Integer.valueOf(txtquantity.getText()),
									new BigDecimal(txtprice.getText()), null, (String) cbtype.getSelectedItem()))) {
						Noti.getMessage("thêm thành công");
						refresh();
					} else {
						Noti.getMessage("thêm thất bại");
					}
				} else {
					Noti.getMessage("không được để trống dữ liệu");
				}
			}
		});
		btnThm.setBounds(25, 244, 89, 23);
		panel_1.add(btnThm);

		/*
		 * sua 1 thiet bi
		 */
		JButton btnSa = new JButton("sửa");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equiqCode != null) {
					if (check() == true) {
						if (equiqmentDao.update(new Equiqment(txtcode.getText(), txtname.getText(),
								Integer.valueOf(txtquantity.getText()), new BigDecimal(txtprice.getText()), null,
								(String) cbtype.getSelectedItem()))) {
							Noti.getMessage("sửa thành công");
							refresh();
						} else {
							Noti.getMessage("sửa thất bại");
						}
					} else {
						Noti.getMessage("không được để trống dữ liệu");
					}
				} else {
					Noti.getMessage("hãy chọn 1 thiết bị");
				}
			}
		});
		btnSa.setBounds(141, 244, 89, 23);
		panel_1.add(btnSa);

		/*
		 * xoa 1 thiet bi
		 */
		JButton btnXa = new JButton("xóa");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equiqCode != null) {
					if (equiqmentDao.delet(equiqCode)) {
						Noti.getMessage("xóa thành công");
						refresh();
					} else {
						Noti.getMessage("xóa thất bại");
					}

				} else {
					Noti.getMessage("hãy chọn 1 thiết bị");
				}
			}
		});
		btnXa.setBounds(25, 293, 89, 23);
		panel_1.add(btnXa);

		JButton btnLmMi = new JButton("làm mới");
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTable();
				listEquiqments.clear();
				listEquiqments = equiqmentDao.getListEquiqments();
				initTable();
				clearInput();
			}
		});
		btnLmMi.setBounds(141, 293, 89, 23);
		panel_1.add(btnLmMi);

		JButton btnXutHng = new JButton("xuất hàng");
		btnXutHng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
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
		});
		btnXutHng.setBounds(25, 330, 205, 23); 
		panel_1.add(btnXutHng);

		model = new DefaultTableModel();
		Object[] column = { "mã thiết bị", "tên thiết bị", "số lượng còn", "giá nhập", "ngày nhập", "loại hàng" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listEquiqments = equiqmentDao.getListEquiqments();
		initTable();
		clickRow();
	}

	public void initTable() {
		for (Equiqment p : listEquiqments) {
			model.addRow(new Object[] { p.getCode(), p.getName(), p.getQuantity(), p.getPrice(), p.getCreateddate(),
					p.getType() });
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
		txtcode.setText("");
		txtname.setText("");
		txtprice.setText("");
		txtquantity.setText("");
		txtsearch.setText("");
		txtcode.setEnabled(true);
		equiqCode = null;
	}

	public boolean check() {
		if (!txtcode.getText().equals("") && !txtname.getText().equals("") && !txtprice.getText().equals("")
				&& !txtquantity.getText().equals("")) {
			return true;
		}
		return false;
	}

	public void clickRow() {
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
					table.setSelectionBackground(Color.YELLOW);
					equiqCode = table.getValueAt(table.getSelectedRow(), 0).toString();
					txtcode.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					txtcode.setEnabled(false);
					txtname.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					txtprice.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					txtquantity.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				}
			}
		});
	}

	public void search() {
		deleteTable();
		listEquiqments.clear();
		listEquiqments = equiqmentDao.findListEquiqments(txtsearch.getText());
		initTable();
		clearInput();
	}

	public void refresh() {
		deleteTable();
		listEquiqments.clear();
		listEquiqments = equiqmentDao.getListEquiqments();
		initTable();
		clearInput();
	}
}
