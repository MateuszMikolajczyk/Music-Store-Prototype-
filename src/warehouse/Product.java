package warehouse;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Serializable {

	private static final long serialVersionUID = 2146888021996446426L;
	private int id;
	private String name;
	private double price;

	Date currentDate = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String dateString = dateFormat.format(currentDate);

	public Product(String name) {
		this.name = name;

	}

	public Product(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {

		return " | Number ID: " + id + " | Product name: " + name + " |  Price: " + String.format("%, .2f", price)
				+ " | Add Time: " + dateString;

	}

}
