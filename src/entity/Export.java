package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Export {

	private int id;
	
	private String cusName;
	
	private String phone;
	
	private String address;
	
	private Equiqment equiqment;
	
	private Timestamp exportDay;
	
	private int quantity;
	
	private BigDecimal exportPrice;

	public Export() {
		
	}

	public Export(String cusName, String phone, String address, Timestamp exportDay, int quantity,
			BigDecimal exportPrice) {
		this.cusName = cusName;
		this.phone = phone;
		this.address = address;
		this.exportDay = exportDay;
		this.quantity = quantity;
		this.exportPrice = exportPrice;
	}

	public Export(int id, String cusName, String phone, String address, Timestamp exportDay, int quantity,
			BigDecimal exportPrice) {
		this.id = id;
		this.cusName = cusName;
		this.phone = phone;
		this.address = address;
		this.exportDay = exportDay;
		this.quantity = quantity;
		this.exportPrice = exportPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Equiqment getEquiqment() {
		return equiqment;
	}

	public void setEquiqment(Equiqment equiqment) {
		this.equiqment = equiqment;
	}

	public Timestamp getExportDay() {
		return exportDay;
	}

	public void setExportDay(Timestamp exportDay) {
		this.exportDay = exportDay;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getExportPrice() {
		return exportPrice;
	}

	public void setExportPrice(BigDecimal exportPrice) {
		this.exportPrice = exportPrice;
	}

	@Override
	public String toString() {
		return "Export [id=" + id + ", cusName=" + cusName + ", phone=" + phone + ", address=" + address
				+ ", equiqment=" + equiqment + ", exportDay=" + exportDay + ", quantity=" + quantity + ", exportPrice="
				+ exportPrice + "]";
	}
	
	
}
