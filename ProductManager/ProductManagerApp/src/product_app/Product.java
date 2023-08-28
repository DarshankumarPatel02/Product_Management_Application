package product_app;

// bank account has a balance that can be changed by deposit and withdraws
public class Product {

//	private static int count = 0;
	
	private int id;
	private String name;
	private double price;
	
	

	// constructor bank account with a zero banalce

	public Product(int id, String name, double price) {
		super();
		this.id = id;
//		this.id = count++;
		this.name = name;
		this.price = price;
	}
	
	// @param anAccountNumber the account number for this accont

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

}
