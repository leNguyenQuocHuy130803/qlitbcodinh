package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.Connect;
import entity.Equiqment;
import entity.Export;

public class ExportDao {
	private Connection connection = Connect.getConnect();
	private EquiqmentDao equiqmentDao = new EquiqmentDao();
	
	public List<Export> getListExport() {
		String sql = "select * from xuatkho";
		List<Export> arrayList = new ArrayList<Export>();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				Export export = new Export(res.getInt("id"), res.getString("tenkh"), res.getString("sdt"),
						res.getString("diachi"), res.getTimestamp("ngaythanhly"), res.getInt("soluong"),
						res.getBigDecimal("giathanhly"));
				export.setEquiqment(equiqmentDao.getEquiqments(res.getString("matb")));
				arrayList.add(export);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public boolean insert(Export export, String equiqCode) {
		Equiqment equiqment = equiqmentDao.getEquiqments(equiqCode);
		int quan = equiqment.getQuantity();
		String sql ="insert into xuatkho(tenkh, sdt, diachi, matb, ngaythanhly, soluong, giathanhly) values(?,?,?,?,?,?,?)";
		String update = "update thietbi set soluong=? where matb=?";
		try {
			PreparedStatement pre = connection.prepareStatement(update);
			pre.setInt(1, quan-export.getQuantity());
			pre.setString(2, equiqCode);
			if (pre.executeUpdate()>0) {
				PreparedStatement pre1 = connection.prepareStatement(sql);
				pre1.setString(1, export.getCusName()); 
				pre1.setString(2, export.getPhone()); 
				pre1.setString(3, export.getAddress()); 
				pre1.setString(4, equiqCode);
				pre1.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				pre1.setInt(6, export.getQuantity());
				pre1.setBigDecimal(7, export.getExportPrice());
				return pre1.executeUpdate()>0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean update(int exportid, int quantity, String name, String phone, String addr,BigDecimal price) {
		String sql = "select * from xuatkho where id=?";
		Equiqment equiqment = null;
		int quan = 0;
		int chenhlech = 0;
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setInt(1, exportid);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				quan = res.getInt("soluong");
				equiqment = equiqmentDao.getEquiqments(res.getString("matb"));
			}
			chenhlech = quantity-quan;
			int soluongthaythoi = equiqment.getQuantity()-chenhlech;
			
			String query = "update xuatkho set soluong=? , tenkh=?, sdt=?, diachi=?, giathanhly=? where id=?";
			String query1 = "update thietbi set soluong=? where matb=?";
			
			PreparedStatement pre1 = connection.prepareStatement(query);
			pre1.setInt(1, quantity);
			pre1.setString(2, name);
			pre1.setString(3, phone);
			pre1.setString(4, addr);
			pre1.setBigDecimal(5, price);
			pre1.setInt(6, exportid);
			pre1.executeUpdate();
			
			PreparedStatement pre2 = connection.prepareStatement(query1);
			pre2.setInt(1, soluongthaythoi);
			pre2.setString(2, equiqment.getCode());
			return pre2.executeUpdate()>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int exportid) {
		String sql = "select * from xuatkho where id=?";
		Equiqment equiqment = null;
		int quan = 0;
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setInt(1, exportid);
			ResultSet res = pre.executeQuery();
			if (res.next()) {
				quan = res.getInt("soluong");
				equiqment = equiqmentDao.getEquiqments(res.getString("matb"));
			}
			
			String query = "delete from xuatkho where id=?";
			String query1 = "update thietbi set soluong=? where matb=?";
			PreparedStatement pre1 = connection.prepareStatement(query);
			pre1.setInt(1, exportid);
			
			
			PreparedStatement pre2 = connection.prepareStatement(query1);
			pre2.setInt(1, quan+equiqment.getQuantity());
			pre2.setString(2, equiqment.getCode());
			if (pre2.executeUpdate()>0) {
				return pre1.executeUpdate()>0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		new ExportDao().getListExport().forEach(p->{System.out.println(p);});
	}
}
