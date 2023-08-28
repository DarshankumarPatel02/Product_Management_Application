/*************************************************************************************************
*  Course_Name – Assignment 3 - Group 5                                                                                                                           *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.      *

*  No part of this assignment has been copied manually or electronically from any other source   *

*  (including web sites) or distributed to other students/social media.                                                       *
                                                                                                                                                                             
*  Name: Darshankumar Patel Student ID: N01496781 Date: 10 June 2022                    		 *
*  Name: Mira Philip        Student ID: N01495720 Date: 10 June 2022                    		 *
*  Name: Vishesh Makwana    Student ID: N01495694 Date: 10 June 2022                    		 *

* ************************************************************************************************/

/**
* The CustomerData class 
* which handles file operations
*
* @author  Group 5 
* 
* Member 1: Darshankumar Patel(N01496781)
* Member 2: Mira Philip(N01495720)
* Member 3: Vishesh Makwana(N01495694)
* Section: ITC-5201-RIA 
* 
*/
package product_app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

// Customer Data class which does file operations
public class ProductData11 {
	
	// Member variable
	private static File file;

	// Constructor
	public ProductData11() {
		file = null;
	}
	
	/**
	 * read() method scans through customer.dat
	 * adds each row as a string to ArrayList and 
	 * returns it when EOF is reached
	 * @return ArrayList of customer records
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<String> read() throws  IOException, FileNotFoundException, ClassNotFoundException {
		
		// Create file object for referring customer.dat
		file = new File("src/product.dat");
		
		// Create ArrayList for storing customer data
		ArrayList<String> customerFields = new ArrayList<String>();
		
		// Using DataInputStream and file object, try reading
		try (DataInputStream input = new DataInputStream(new FileInputStream(file));) {
			// Until EOF is reached, the following while gets executed.
			while (true){
				// read each field from file using readUTF() method of DataInputStream
				String id = input.readUTF();
				String name = input.readUTF();
				String email = input.readUTF();
				String phone = input.readUTF();
				String postalCode = input.readUTF();
				String recordEnd = input.readUTF();
				// "*" represents end of each record, once that is reached, add to array list
				if(recordEnd.equals("*"))
					customerFields.add(id + "," + name + "," + email + "," + phone + "," + postalCode);
			}
		} 
		catch (FileNotFoundException e1) {
			// When FileNotFound, return the empty array list
			return customerFields;
		}
		catch(EOFException e) {
			// When EOF is reached, return the updated array list customerFields
			return customerFields;
		}
	}

	/**
	 * write() method scans through the parameter ArrayList
	 * with list of customers and write into customer.dat
	 * and return Boolean writeSuccess as true if write was successful, otherwise false 
	 * @param ArrayList of customer records
	 * @return Boolean writeSuccess
	 * @throws IOException
	 */
	public static Boolean write(ArrayList<String>  customerList) throws IOException {
		
		// Create file object for referring customer.dat
		file = new File("src/customer.dat");
		
		// Create boolean writeSuccess for returning after method execution
		boolean writeSuccess = false ;
		
		// Using DataOutputStream and file object, try writing data
		try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file));) {
			
			// Create customerArr using toArray method of array list
    		String [] customerArr = new String[customerList.size()];
    		customerArr = customerList.toArray(customerArr);
	     
    		// loop through each element in array
    	    for(String cust : customerArr) {
    	    	// Split each customer string record using split(",") 
      	   	  	String[] custFields = cust.split(",");
      	   	  	// write into file, all the array elements returned and end each record with *
	      	   	output.writeUTF(custFields[0]);
	      	    output.writeUTF(custFields[1]);
	      	    output.writeUTF(custFields[2]);
	      	    output.writeUTF(custFields[3]);
	      	    output.writeUTF(custFields[4]);
				output.writeUTF("*");
    	    }
    	    // set writeSuccess to true inside try
			writeSuccess = true;
			
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		//return writeSuccess
		return writeSuccess;
	}
	
	/**
	 * find() method with the parameter searchID
	 * reads customer.dat and look for customer record with searchID
	 * and return String customerFound if customer was found, otherwise null is returned
	 * @param searchID
	 * @return String of customer data found
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static String find(String searchID) throws  IOException, FileNotFoundException, ClassNotFoundException {
		
		// Create file object for referring customer.dat
		file = new File("src/customer.dat");
		
		// Create String for returning found customer's string
		String customerFound =  null;
		
		// Using DataInputStream and file object, try reading
		try (DataInputStream input = new DataInputStream(new FileInputStream(file));) {
			// Until EOF is reached, the following while gets executed.
			while (true){
				// read each field from file using readUTF() method of DataInputStream
				String customerID = input.readUTF();
				String customerName = input.readUTF();
				String email = input.readUTF();
				String phone = input.readUTF();
				String postalCode = input.readUTF();
				String recordEnd = input.readUTF();
				
				// "*" represents end of each record, once that is reached, and if searchID is matching customerID
				if(recordEnd.equals("*") && searchID.equals(customerID)) 
					customerFound = customerID + "," + customerName + "," +phone + "," +email + "," +postalCode;
			}
			
		} 
		catch (FileNotFoundException e1) {
			// When FileNotFound, return null (customerFound)
			return customerFound;
		}
		catch(EOFException e) {
			// When EOF is reached, return the updated String customerFound
			return customerFound;
		}
	}

}
