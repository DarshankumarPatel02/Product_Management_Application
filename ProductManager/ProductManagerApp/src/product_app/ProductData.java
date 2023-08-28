package product_app;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ProductData {

	private RandomAccessFile file;// specify the file

	public static final int INT_SIZE = 4;// 4 byte for account number

	public static final int DOUBLE_SIZE = 8;// 8 byte for balance
	
	public static final int STRING_SIZE = 30;

	public static final int RECORD__SIZE = INT_SIZE + DOUBLE_SIZE + STRING_SIZE;

	// constructs a ProductData object that is not associate with a file

	public ProductData() {
		file = null;
	}

	// open the data file
	// @Param filename the name of the file containing account information

	public void open(String filename) throws IOException {
		if (file != null)
			file.close();
		file = new RandomAccessFile(filename, "rw");
	}

	// get the number of account in the file
	// return the number of accounts

	public int size() throws IOException {
		return (int) (file.length() / RECORD__SIZE);
	}

	// close the data file

	public void close() throws IOException {
		if (file != null)
			file.close();
		file = null;
	}
	
	// Read a bank account record 
	
	// @Param n the index of the account in the data file ( index/position)
	
	// return a bank account object initialized with the file data 
	
	public Product read(int n) throws IOException 
	{
		file.seek(n * RECORD__SIZE);
		int id = file.readInt();
//		System.out.print(id);
		double price = file.readDouble();
//		System.out.print(price);
		String name = file.readUTF();
//		System.out.print(name);
		return new Product (id, name, price);
	}
	
	/* find the position of a given number 
	@Param accountNumber the number to find 
	return the position of the account with the given number or -1 if there is no such account
	*/ 
	
	public int find(int productId) throws IOException 
	{
		for(int i = 0; i < size(); i++)
		{
			file.seek(i* RECORD__SIZE);
			int id = file.readInt();
			if(id == productId)
				return i;
		}
		
		return -1;
	}
	
	// write a bank account record to the data file 
	
	
	public void write(int n, Product product) throws IOException
	{
//		System.out.println("before pointer -->"+file.getFilePointer());
		file.seek(n * RECORD__SIZE);
//		System.out.println("after pointer -->"+file.getFilePointer());
	
		file.writeInt(product.getId());
		file.writeDouble(product.getPrice());
		file.writeUTF(product.getName());
		
	}
	
	public void delete(int n) throws IOException {
		
		file.seek(n * RECORD__SIZE);
		file.writeInt(0);
		file.writeDouble(0);
		for(int i = 0; i < 15; i++)
			file.writeUTF("");
	}

}
