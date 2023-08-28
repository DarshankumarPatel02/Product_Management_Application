/*************************************************************************************************
*  Course_Name – Assignment 3 - Group 5                                                                                                                           *

*  I declare that this assignment is my own work in accordance with Humber Academic Policy.      *

*  No part of this assignment has been copied manually or electronically from any other source   *

*  (including web sites) or distributed to other students/social media.                          *
                                                                                                                                                                             
*  Name: Darshankumar Patel Student ID: N01496781 Date: 10 June 2022                    		 *
*  Name: Mira Philip        Student ID: N01495720 Date: 10 June 2022                    		 *
*  Name: Vishesh Makwana    Student ID: N01495694 Date: 10 June 2022                    		 *

* ************************************************************************************************/

/**
* The MainApp program implements an application that
* records customer data and view/update records
* based on customer id entered
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

import javax.swing.JFrame;

public class MainApp {

	public static void main(String[] args) {
		
		/*
		 * Create a Jframe using CustomerRegistryFrame class's
		 * constructor, add title and set default close as EXIT_ON_CLOSE
		 * make it visible
		 */
		
		JFrame frame = new ProductManagementFrame();
		frame.setTitle("Product Data Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
