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

public class EquiqmentDao {

	private Connection connection = Connect.getConnect();

	public List<Equiqment> getListEquiqments() {
		String sql = "select * from thietbi";
		List<Equiqment> arrayList = new ArrayList<Equiqment>();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				arrayList.add(new Equiqment(res.getString("matb"), res.getString("tentb"),
						res.getInt("soluong"),res.getBigDecimal("gianhap"),
						res.getTimestamp("ngaynhap"), res.getString("loaihang")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public List<Equiqment> findListEquiqments(String name) {
		String sql = "select * from thietbi where tentb like ?";  // chỉ để tìm kiếm tên thiết bị còn lại ko đc
		List<Equiqment> arrayList = new ArrayList<Equiqment>();
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, "%"+name+"%");
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				arrayList.add(new Equiqment(res.getString("matb"), res.getString("tentb"),
						res.getInt("soluong"),res.getBigDecimal("gianhap"),
						res.getTimestamp("ngaynhap"), res.getString("loaihang")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public Equiqment getEquiqments(String code) {
		String sql = "select * from thietbi where matb = ?";
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, code);
			ResultSet res = pre.executeQuery();
			while (res.next()) {
				Equiqment equiqment = new Equiqment(res.getString("matb"), res.getString("tentb"),
						res.getInt("soluong"),res.getBigDecimal("gianhap"),
						res.getTimestamp("ngaynhap"), res.getString("loaihang"));
				return equiqment;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insert(Equiqment equiqment) {
		String sql = "insert into thietbi(matb,tentb,soluong,gianhap,ngaynhap,loaihang) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, equiqment.getCode());
			pre.setString(2, equiqment.getName());
			pre.setInt(3, equiqment.getQuantity());
			pre.setBigDecimal(4, equiqment.getPrice());
			pre.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pre.setString(6, equiqment.getType());
			return pre.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(Equiqment equiqment) {
		String sql = "update thietbi set tentb=?, soluong=?, gianhap=?, loaihang=? where matb=?";
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, equiqment.getName());
			pre.setInt(2, equiqment.getQuantity());
			pre.setBigDecimal(3, equiqment.getPrice());
			pre.setString(4, equiqment.getType());
			pre.setString(5, equiqment.getCode());
			return pre.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delet(String code) {
		String sql = "delete from thietbi where matb=?";
		try {
			PreparedStatement pre = connection.prepareStatement(sql);
			pre.setString(1, code);
			return pre.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		System.out.println(new EquiqmentDao().getEquiqments("tb01"));;
	}
}
