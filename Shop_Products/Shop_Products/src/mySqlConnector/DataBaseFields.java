package mySqlConnector;

import java.util.Date;
import java.util.Random;

public class DataBaseFields {

	private String productName = null;
	private double aPrice = 0.0;
	private double fPrice = 0.0;
	private int quantity = 0;
	private Date dateOfArrival = null;
	private String manufacturer = null;
	private String id = null;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getaPrice() {
		return aPrice;
	}
	public void setaPrice(double aPrice) {
		this.aPrice = aPrice;
	}
	
	public double getfPrice() {
		return fPrice;
	}
	public void setfPrice(double fPrice) {
		this.fPrice = fPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Date getDateOfArrival() {
		return dateOfArrival;
	}
	public void setDateOfArrival(Date dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getId() {
		return id;
	}
	
	private String generateId() {
		int a = 0;
		String code = "";
		for(int i = 0; i < 7; i++) {
			if (randInt(0
					, 1) == 1) {
				a = randInt(48, 57);
			} else {
				a = randInt(97, 122);
			}
			code += Character.toString((char)a);
		}
		
		return code;
	}

	public DataBaseFields(String productName, double aPrice, int quantity, Date dateOfArrival, String manufacturer) {
		this.productName = productName;
		this.aPrice = aPrice;
		this.quantity = quantity;
		this.dateOfArrival = dateOfArrival;
		this.manufacturer = manufacturer;
		this.id = generateId();
	}
	
	private int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	
}
