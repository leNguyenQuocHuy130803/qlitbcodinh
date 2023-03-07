package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Equiqment {

	private String code;
	
	private String name;
	
	private int quantity;
	
	private BigDecimal price;
	
	private Timestamp createddate;
	
	private String type;

	public Equiqment() {
		
	}

	public Equiqment(String name, int quantity, BigDecimal price, Timestamp createddate, String type) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.createddate = createddate;
		this.type = type;
	}

	public Equiqment(String code, String name, int quantity, BigDecimal price, Timestamp createddate, String type) {
		super();
		this.code = code;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.createddate = createddate;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Equiqment [code=" + code + ", name=" + name + ", quantity=" + quantity + ", price=" + price
				+ ", createddate=" + createddate + ", type=" + type + "]";
	}
	
	
}
