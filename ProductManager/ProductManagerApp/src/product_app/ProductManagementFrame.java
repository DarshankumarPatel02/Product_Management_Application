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
* The CustomerRegistryFrame class 
* creates a JFrame with required components
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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;


public class ProductManagementFrame extends JFrame {
	
	//Product_ID auto-assigned start with 5001
	private static int PRODUCT_ID=5001;
	
	// TabbedPane for hold 3 tabs
	private JTabbedPane tabbedPane;
	
	// Register panel
	private JPanel addPanel;
	
	// addPanel tab labels and textfields
	private JLabel productIDLabel;
	private JTextField productIDField;
	private JLabel productNameLabel;
	private JTextField productNameField;
	private JTextField productPriceField;
	private JLabel productPriceLabel;
	
	// addPanel tab button
	private JButton addButton;
	
	// Find/edit panel
	private JPanel updatePanel;
	
	// Find/Edit tab labels and textfields
	private JLabel searchIDLabel;
	private JTextField searchIDField;
	private JLabel editPNameLabel;
	private JTextField editPNameField;
	private JLabel editPPriceLabel;
	private JTextField editPPriceField;
	private JLabel editPIDLabel;
	private JTextField editPIDField;
	
	// Find/Edit tab buttons
	private JButton searchButton;
	private JButton saveButton;
	private JButton deleteButton;
	
	// View panel
	private JPanel viewPanel;
	
	// View tab components
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable table; 
	
	// View tab ArrayList, String[] for JTable
	private ArrayList<String> customerList;
	private String[] customerArr;

	// Constructor
	public ProductManagementFrame() {
		
		// Set bounds for the Frame
		setBounds(500, 230, 500, 290);

		// Create a new tabbed pane and set bounds
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 11, 321, 205);
		
		//Creating addPanel to add to Tab one
		addPanel = new JPanel();
		
		//Creating Components for addPanel (Tab one - Add customer)
		productIDLabel = new JLabel("Product ID:");
		productIDLabel.setBounds(100, 18, 217, 14);
		//productIDLabel.setForeground(Color.BLUE);
		
		productIDField = new JTextField(8);
		productIDField.setBounds(210, 18, 150, 20);
		productIDField.setColumns(8);
		productIDField.setEditable(false);
		
		
		productNameLabel = new JLabel("Product Name:");
		productNameLabel.setBounds(100, 48, 217, 14);
		//productNameLabel.setForeground(Color.BLUE);
		
		productNameField = new JTextField(8);
		productNameField.setBounds(210, 48, 150, 20);
		productNameField.setColumns(8);
		
		productPriceLabel = new JLabel("Price $:");
		productPriceLabel.setBounds(100, 80, 217, 14);
		//productPriceLabel.setForeground(Color.BLUE);
		
		productPriceField = new JTextField(2);
		productPriceField.setBounds(210, 80, 150, 20);
		
		addButton = new JButton("Add Product");
		addButton.setBounds(200, 129, 109, 23);
		addButton.setForeground(Color.BLUE);
		
		//ADDING ALL THE COMPONENTS TO THE addPanel(in tab 1)
		addPanel.setLayout(null);
		addPanel.add(productIDLabel);
		addPanel.add(productIDField);
		addPanel.add(productNameLabel);
		addPanel.add(productNameField);
		addPanel.add(productPriceLabel);
		addPanel.add(productPriceField);
		addPanel.add(addButton);
		
		// Add addPanel creates to tabbedPane at first index
		tabbedPane.addTab("Add Product", null, addPanel, "Add Product");
		
		//Creating updatePanel to add to Tab 2
		updatePanel = new JPanel();
		
		//Creating Components for updatePanel (Tab 2 - find/edit customer)
		searchIDLabel = new JLabel("Enter ID:");
		searchIDLabel.setBounds(100, 11, 217, 14);
		
		searchIDField = new JTextField(10);
		searchIDField.setBounds(200, 8, 150, 20);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(180, 45, 120, 23);
		
		editPIDLabel = new JLabel("Product ID:");
		editPIDLabel.setBounds(100, 90, 217, 14);
		
		editPIDField = new JTextField(10);
		editPIDField.setBounds(200, 90, 150, 20);
		editPIDField.setEditable(false);
		
		editPNameLabel = new JLabel("Product Name:");
		editPNameLabel.setBounds(100, 120, 217, 14);
		
		editPNameField = new JTextField(10);
		editPNameField.setBounds(200, 120, 150, 20);
		
		editPPriceLabel = new JLabel("Price $:");
		editPPriceLabel.setBounds(100, 150, 217, 14);
		
		editPPriceField = new JTextField(10);
		editPPriceField.setBounds(200, 150, 150, 20);
		
		saveButton = new JButton("Save Changes");
		saveButton.setBounds(100, 189, 120, 23);
		saveButton.setForeground(Color.BLUE);
		
		deleteButton = new JButton("Delete Product");
		deleteButton.setBounds(230, 189, 130, 23);
		deleteButton.setForeground(Color.RED);
		
		//disable deletebutton before searching in file
		deleteButton.setEnabled(false);
		
		//ADDING ALL THE COMPONENTS TO THE updatePanel(in tab 2)
		updatePanel.setLayout(null);
		updatePanel.add(editPNameLabel);
		updatePanel.add(editPNameField);
		updatePanel.add(editPPriceLabel);
		updatePanel.add(editPPriceField);
		updatePanel.add(searchButton);
		updatePanel.add(searchIDField);
		updatePanel.add(searchIDLabel);
		updatePanel.add(editPIDLabel);
		updatePanel.add(editPIDField);
		updatePanel.add(saveButton);
		updatePanel.add(deleteButton);

		// Add updatePanel creates to tabbedPane at second index
		tabbedPane.addTab("Edit/Delete", null, updatePanel, "Edit/Delete");
		
		//Creating viewPanel to add to Tab 3
		viewPanel = new JPanel();

		// Add viewPanel creates to tabbedPane at third index
		tabbedPane.addTab("View All", null, viewPanel, "View All");

		// Add tabbedPane to the Frame
		add(tabbedPane);
		
		customerList = new ArrayList<String>();
		tableModel = new DefaultTableModel();

		tableModel.addColumn("Id");		            		
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
     
		table = new JTable(tableModel);
	    scrollPane = new JScrollPane(table);
	    Border empty =new EmptyBorder(0,0,0,0);
	    scrollPane.setBorder(empty);
	    viewPanel.add(scrollPane);
	
	//validation section
    //auto-assigned value to productid textbox and edit property to false so no-one can change it's value.
	productIDField.setText(Integer.toString(PRODUCT_ID));
	
	/* add Product Button clickEvent
	 * Validate if the user has entered a valid name and price  
	 * show message dialog otherwise
	 */
	addButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		    //getting all textbox value into variables
		    String name=productNameField.getText();
		    String price=productPriceField.getText();
		    
			/*
			 * Validation is done step-by-step as the textfield proceed 
			 * id is auto-assigned and integer is used so no need to validate 
			 * for name and product
			 */
		    if (validateProductName(name) == false) {
				JOptionPane.showMessageDialog(null, "Product Name cannot be blank. or \nProduct Name(Only contains Alphabets).");
			} 
		    else if (validatePrice(price) == false) {
				JOptionPane.showMessageDialog(null, "Price cannot be blank. or \nInValid Price(It should be in double) and non-negative.");
			} else {
				//writing into files
				ProductData data = new ProductData();
				try {
					data.open("src/product.dat");
					if(name.length() < 30 )
						name = String.format("%-30s", name);
					Product product = new Product(PRODUCT_ID, name, Double.parseDouble(price));
					int position = data.size();
					data.write(position, product);
					PRODUCT_ID++;
					clearAddProductForm();
					productIDField.setText(Integer.toString(PRODUCT_ID));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		    
		}
	});
	
	/* Search button - click event
	 * Validation Section(Searching product data present in file (click on button event(search)))
	 * Validate if the user has entered a valid product id
	 * Show message dialog accordingly
	 */
	searchButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//getting value of textbox
			String searchPID = searchIDField.getText();
			
			if (validateProductID(searchPID) == false) {
				JOptionPane.showMessageDialog(null, "ProductID cannot be blank. or \nEnter Valid and non-negative Value of Product_ID assigned(start with 5001).");
			}
			else {
				//also check if it is in file
				//reading/searching from files
				
				int productID = Integer.parseInt(searchPID);
				
				ProductData data = new ProductData();
				
				try {
					data.open("src/product.dat");
					int position = data.find(Math.abs(productID));
					if(position != -1) {
						// finding the position of the account number in the file
						//deleteButton enable when search is found.
						Product p = data.read(position);
						editPIDField.setText(Integer.toString(p.getId()));
						editPNameField.setText(p.getName().trim());
						editPPriceField.setText(Double.toString(p.getPrice()));
						deleteButton.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Product with ID " + searchPID + " not found");
						clearModifyForm();
					}
						
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				
			}	
		}
	});
	
	/* Save Button clickEvent.
	 * Validate the data updated and if valid,call the write() to update the file row
	 */
	saveButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/*
			 * Validation is done step-by-step as the textfield proceed
			 * id which need be integer and price need to be double
			 * also name can only contains alphabets 
			 */
			
			//getting value of each textbox and store it in variable
		    String editPID=searchIDField.getText();
		    String editPName=editPNameField.getText();
		    String editPPrice=editPPriceField.getText();
			
		    if (validateProductName(editPName) == false) {
				JOptionPane.showMessageDialog(null, "Product Name cannot be blank. or \nProduct Name(Only contains Alphabets)");
			} 
			else if (validatePrice(editPPrice) == false) {
				JOptionPane.showMessageDialog(null, "Price cannot be blank. or \nInValid Price(It should be in double) and non-negative.");
			} else {
				//updating values in file
				
				int productID = Integer.parseInt(editPID);
				
				ProductData data = new ProductData();
				
				try {
					data.open("src/product.dat");
					int position = data.find(Math.abs(productID));
					if(position != -1) {
						if(editPName.length() < 30 )
							editPName = String.format("%-30s", editPName);
						Product product = new Product(productID, editPName, Double.parseDouble(editPPrice));
//						data.delete(position);
						data.write(position, product);
						clearModifyForm();
						JOptionPane.showMessageDialog(null, "Product updated successfully");
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
	});
	
	deleteButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String editPID = searchIDField.getText();
			String editPName=editPNameField.getText();
			int answer = JOptionPane.showConfirmDialog(null, 
					"Are you sure you want to delete product " + editPName + "?", "Delete Product", 
			        JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.NO_OPTION) {
				//do nothing
			} else if(answer == JOptionPane.YES_OPTION) {
			
				int productID = Integer.parseInt(editPID);
				
				ProductData data = new ProductData();
				
				try {
					data.open("src/product.dat");
					int position = data.find(Math.abs(productID));
					if(position != -1) {
						data.delete(position);
						clearModifyForm();
						JOptionPane.showMessageDialog(null, "Product deleted successfully");
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	});
	
	// Tabbed Pane Change event listener to listen to View All tab, to call read() method to read from file
	tabbedPane.addChangeListener(new ChangeListener() { 
		
        public void stateChanged(ChangeEvent e) {
        	
        	// If View All tab is clicked, the block gets executed
            if(tabbedPane.getSelectedIndex() == 2) {
          
            	// Call read() method defined in CustomerData ( with try as it involves file,class and IO )
            	try 
            	{
            		
            		// Clear rows in the table, every time data is read
            		tableModel.setRowCount(0);
            		
            		ProductData data = new ProductData();
            		data.open("src/product.dat");
            		for(int i=0 ; i< data.size(); i++) {
            			Product p = data.read(i);
            			tableModel.addRow(new String[] { Integer.toString(p.getId()), p.getName(), Double.toString(p.getPrice()) });
            		}
				} 
            	catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        }

    });
	
  }
	
	/**
	 * Validating productID function checking that entered data is integer only
	 * then check it should be greater than zero. 
	 */
	protected boolean validateProductID(String p_id) {
		try {
				int id = Integer.parseInt(p_id);
				if (id >0) {
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
	}
	
	/**function for validating product name with regex
	 * ^ mean starting point and $ means ending
	 * all alphabet with capital and small letter and space too and length less than 50.
	 * another regex is used to check if textbox have null value or it's empty string. 
	 */
	protected boolean validateProductName(String productName) {
		return (productName.matches("^[a-zA-Z\\s]*$") && productName.matches("[\\S\\s]+[\\S]") && productName.length()<=30);
	}
	
	/**function for validating price 
	 * it should not negative and it should be in double
	 * 
	 */ 
	protected boolean validatePrice(String price) {
		try {
				double p = Double.parseDouble(price);
				if (p >0.0) {
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
	}		 
	
	// Method to clear all textfields in Register tab after successful save
	public void clearAddProductForm() {
		productIDField.setText("");
		productNameField.setText("");
		productPriceField.setText("");
	}
	
	// Method to clear all textfields in Find/Edit tab after successful save
	public void clearModifyForm() {
		searchIDField.setText("");
		editPIDField.setText("");
		editPNameField.setText("");
		editPPriceField.setText("");
	}
}
		

