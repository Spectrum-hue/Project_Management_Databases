import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException; 
import java.time.LocalDate;

/**
 * The Projects class contains all the methods used in the 'PoisedMenu' which pertain to adding, editing, finalising, 
 * or viewing various projects for the Poised Management System.
 * It inherits input check methods from the superclass 'PoisedInputChecks' to validate user input.
 * <p>
 * 
 */
public class Projects extends PoisedInputChecks {
	/**
	 * This method allows user to create a new project object, which is added to the 'main_project_info' table in the 
	 * PoisePMS database. 
	 * <p>
	 * It prompts users to enter various information related to a new project object, and then connects to the external
	 * database to update project information. The 'PoisedMenu' class calls on this method when the user chooses to add 
	 * a new project to the system.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void addProject(Statement statement) throws SQLException {
		
		System.out.println("\nPlease add a new project number: "); 
		int proj_num = intCheck("new project number");
		
		System.out.println("\nPlease add a new project name: ");
		String proj_name = stringCheck("new project name: "); 
		
		System.out.println("\nPlease add a building type: ");
		String build_type = stringCheck("building type");
		
		System.out.println("\nPlease add a address for the project: ");
		String address = stringCheck("project address");
		
		System.out.println("\nPlease add an ERF number: ");
		String erf_num = stringCheck("erf number");
		
		System.out.println("\nPlease add a total fee for the project: ");
		float total_fee = floatCheck("total fee");
		
		System.out.println("\nPlease add the current amount paid for the project: ");
		float amount_paid = floatCheck("amount paid");
		
		System.out.println("Please add a deadline for the project (e.g. 2021-12-30): ");
		String deadline = stringCheck("deadline");
		
		String finalise = "No";
		String comp_date = "None";
		// New attributes added
		System.out.println("\nPlease add the name of the customer: "); 
		String Name = stringCheck("name of the customer");
		
		System.out.println("\nPlease add the contact number of the customer: ");
		String Contact_Num = stringCheck("contact number of the customer: ");
		
		System.out.println("\nPlease add the address of the customer: ");
		String Physical_Address = stringCheck("address of the customer");
		
		System.out.println("\nPlease add the email address of the customer: ");
		String Email_Address = stringCheck("email address of the customer");
		
		System.out.println("\nPlease add the name of the project manager: ");
		String proj_manager = stringCheck("name of the project manager");
		
		System.out.println("\nPlease add the contact number of the project manager: ");
		String manager_contact = stringCheck("contact number of the project manager");
		
		System.out.println("\nPlease add the address of the project manager: ");
		String manager_address = stringCheck("address of the project manager");
		
		System.out.println("\nPlease add the email of the project manager: ");
		String manager_email = stringCheck("email of the project manager");
		
		System.out.println("\nPlease add the name of the architect: ");
		String architect = stringCheck("name of the architect");
		
		System.out.println("\nPlease add the contact number of the architect: ");
		String arch_num = stringCheck("contact number of the architect");
		
		System.out.println("\nPlease add the address of the architect: ");
		String arch_address = stringCheck("address of the architect");
		
		System.out.println("\nPlease add the email of the architect: ");
		String arch_email = stringCheck("email of the architect");
		
		System.out.println("\nPlease add the name of the engineer: ");
		String engineer = stringCheck("name of the engineer");
		
		System.out.println("\nPlease add the contact number of the engineer: ");
		String eng_num = stringCheck("number of the engineer");
		
		System.out.println("\nPlease add the address of the engineer: ");
		String eng_address = stringCheck("address of the engineer");
		
		System.out.println("\nPlease add the email of the engineer: ");
		String eng_email = stringCheck("email of the engineer");
		
		/* The main_project_info table in the 'PoisePMS' database is then updated.
		 * The information inputed by the user is inserted into the appropriate columns, 
		 * thus creating and storing a new project object
		 */
		statement.executeUpdate(
                "INSERT INTO project_details VALUES (" + proj_num + ", " + "'" + proj_name + "'" + ", " + "'" 
                + build_type + "'" + ", " + "'" + address + "'" + ", " + "'" + erf_num + "'" + ", " + total_fee + ", " + amount_paid + ", " + 
                "'" + deadline + "'" + ", " + "'" + finalise + "'" + ", " + "'" + comp_date + "'" + ", " + "'" + Name + "'" + ", " + "'" + proj_manager + "'" + "," + "'" + architect + "'" + ", " + "'" + engineer + "'" + ");"
                );
		
		
		statement.executeUpdate(
                "INSERT INTO person_details VALUES (" + proj_num + ", " + "'" + Name + ", " + "'" + Contact_Num + ", " + "'" + Physical_Address + ", " + "'" + Email_Address  + ", " + "'" + proj_manager + ", " + "'" + manager_contact + ", " + 
                "'" + manager_address + "'" + ", " + "'" + manager_email + "'" + ", " + "'" + architect + ", " + "'" + arch_num + "'" + ", " + "'" + arch_address + "'" + ", " + "'" + arch_email + "'" + ", " + "'" + engineer + "'" + ", " + "'" + eng_num + "'" + ", " + "'" + eng_address + "'" + ", " + "'" + eng_email +  "'" + ");"
                );
		
		// A successful message is displayed and the user can then view the updated project list.
		System.out.println("\nYour new project was successfully added. View updated projects below: \n"); 
		printAllFromTable(statement); 
		
	}
	/**
	 * This method allows users to edit project information, relating to the project due date and total amount paid.
	 * <p>
	 * It displays a sub-menu to the user with two edit choices and executes the action depending on their choice. 
	 * The edited information is then written under the corresponding column of the 'project_details' table in the 
	 * external poisepms database.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void editProject(Statement statement) throws SQLException {
		
		// The user is prompted to enter a project number to edit.
		System.out.println("Please enter the number of the project you wish to update: \n");
		int proj = intCheck("project number");
		
		System.out.println("Would you like to:" + 
				"\n1. Edit the project due date or" +
				"\n2. Edit the total amount paid of the fee to date?" +  // Edit options displayed.
				"\nChoose either 1 or 2");
		
		int editChoice = intCheck("edit choice");
		
		/* If the user selects option 1, they are prompted to enter a new deadline.
		 * The new value is then written to the project_deails table with the executeUpdate() SQL statement.
		 */
		if (editChoice == 1) {
			System.out.println("Please enter a new project deadline: ");
			String new_date = stringCheck("new project deadline"); 
			
			statement.executeUpdate(
                    "UPDATE main_project_info SET deadline = '" + new_date + "'" + " WHERE proj_num = " + proj
                );
			
			// A successful message is displayed to the user and then they are able to view the list of updated projects.
			System.out.println("Your project info has been successfully updated. View projects below: ");
			printAllFromTable(statement);
		
		/* If the user selects option 2, they are prompted to enter a new amount paid.
		 * The new value is then written to the main_project_info table with the executeUpdate() SQL statement.
		 */
		} else if (editChoice == 2) {
			System.out.println("Please enter a new total amount paid: ");
			float new_amount = floatCheck("new amount paid");
			
			statement.executeUpdate(
                    "UPDATE main_project_info SET amount_paid = " + new_amount + " WHERE proj_num = " + proj
                );
			// A successful message is displayed to the user and then they are able to view the list of updated projects.
			System.out.println("Your project info has been successfully updated. View projects below: ");
			printAllFromTable(statement);
			
		}
	}
	/**
	 * This method allows users to finalise a project located in the project_overview table in the external 'PoisePMS' database.
	 * <p>
	 * The user is prompted to enter a project number to locate the project. If there is an outstanding amount on the project,
	 * an invoice is generated and displayed with customer details. Otherwise, the project is just marked as finalised and a
	 * completion date is added.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void finaliseProject(Statement statement) throws SQLException {
		
		// The user is prompted to enter a project number to finalise.
		System.out.println("Please enter the number of the project that you wish to finalise: ");
		int proj_num = intCheck("project number");
		
		// Selecting the Total_Fee and Amount_Paid columns from the table.
		ResultSet results2 = statement.executeQuery("SELECT total_fee, amount_paid, build_type, proj_name, deadline, address, erf_num, finalise, Name, proj_manager, architect, engineer FROM project_details WHERE proj_num = " + proj_num); 
		float total_fee = 0;
		float amount_paid = 0;
		String build_type = null;     
		String proj_name = null;      
		String deadline = null;
		String address = null;
		String erf_num = null;
		String finalise = null;
		String Name = null;
		String proj_manager = null;
		String architect = null;
		String engineer = null;
		
		// Iterating through the columns and storing the two float numbers into corresponding variables.
		while (results2.next()) {
			total_fee = results2.getFloat("total_fee"); 
			amount_paid = results2.getFloat("amount_paid");
			build_type = results2.getString("build_type");    
			proj_name = results2.getString("proj_name");      
			deadline = results2.getString("deadline");        
			address = results2.getString("address");
			erf_num = results2.getString("erf_num");
			finalise = results2.getString("finalise");
			Name = results2.getString("Name");
			proj_manager = results2.getString("proj_manager");
			architect = results2.getString("architect");
			engineer = results2.getString("engineer");
			
		}
		// If the project has been paid in full, the amount paid will equal the total fee for the project.
		// This means no invoice needs to be generated.
		if (total_fee == amount_paid) {
			
			System.out.println("This project has already been paid in full. No invoice to be generated.");
			// The user is then prompted to enter a completion date, which is written into the 'Completion_Date' column 
			// in the main_project_info table with the executeUpdate() statement.
			System.out.println("Please add a completion date for the project - e.g 2020-12-25: ");
			String comp_date = stringCheck("completion date");
			
			// Completion date added to user's chosen project by project number.
			statement.executeUpdate(
					"UPDATE project_details SET comp_date = " + "'" + comp_date + "'" + " WHERE proj_num = " + proj_num
					);
			
			// The project is then marked as finalised by writing 'Yes' to the finalise column in the table.
			statement.executeUpdate(
            		 "UPDATE project_details SET finalise = 'Yes' WHERE proj_num = " + proj_num
	                );
			finalise = "Yes";
			statement.executeUpdate(
	                "INSERT INTO invoice_completed VALUES (" + proj_num + ", " + "'" + proj_name + "'" + ", " + "'" 
	        + build_type + "'" + ", " + "'" + address + "'" + ", " + "'" + erf_num + "'" + ", " + total_fee + ", " + amount_paid + ", " + 
	        "'" + deadline + "'" + ", " + "'" + finalise + "'" + ", " + "'" + comp_date + "'" + ", " + "'" + Name + "'" + ", " + "'" + proj_manager + "'" + "," + "'" + architect + "'" + ", " + "'" + engineer + "'" + ");"
	            );
			
			// A successful message is displayed and the user is able to view the updated project list.
			System.out.println("Your project has been successfully finalised. View all the finalised projects: ");
			printAllFromTable(statement);
		
		/* If there is still an amount outstanding on the project, an invoice will be generated.
		 * A 'Persons' object is then created to access the 'displayPerson() method from the Persons class.
		 * The customer details for the selected project are then displayed to the user for the invoice. 
		 */
		} else if (total_fee != amount_paid) {
			System.out.println("There is still an outstanding amount to be paid for this project. View your invoice below: \n");
			
			// Added to the customer info, is the amount owing on the project.
			System.out.println("\nAmount Outstanding: R" + (total_fee - amount_paid));
			
			// The user is then prompted to enter a completion dated for the project.
			System.out.println("\nPlease add a completion date for the project: ");
			String comp_date = stringCheck("completion date");
			
			// The date entered by the user is written to the main_project_info table under the 'Completion_Date' column.
			statement.executeUpdate(
					"UPDATE project_details SET comp_date = " + "'" + comp_date + "'" + " WHERE proj_num = " + proj_num
					);
			
			// The project is then marked as finalised by writing 'Yes' to the finalise column in the table.
			statement.executeUpdate(
            		 "UPDATE project_details SET finalise = 'Yes' WHERE proj_num = " + proj_num
	                );
			//
			// A successful message is displayed and the user is able to view the updated project list.
			System.out.println("Your project has been successfully finalised. View projects below: ");
			printAllFromTable(statement);
			
		}
	}
	/**
	 * This method allows users to view all project objects that are incomplete (i.e. not finalised and no completion 
	 * date added) in the main_project_info table in the external 'PoisePMS' database.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands  
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void viewIncomplete(Statement statement) throws SQLException {
		
		System.out.println("\nPlease view all incomplete projects below: \n");
		
		ResultSet results3 = statement.executeQuery("SELECT * FROM project_details WHERE finalise = 'No' AND comp_date = 'None'");
		
		// All incomplete projects are displayed using a table iterator.
		while (results3.next()) {
			System.out.println( 
					     "\n"
					    + "___________________________________________________________________________________________________________"
					    + "\n"
					    + "\nProject Number: -----------" + results3.getInt("proj_num")
		                + "\nProject Name: -------------" + results3.getString("proj_name") 
		                + "\nBuilding Type: ------------" + results3.getString("build_type")        
		                + "\nPhysical Address: ---------" + results3.getString("address") 
		                + "\nERF Number: ---------------" + results3.getString("erf_num") 
		                + "\nTotal Fee: ----------------R" + results3.getFloat("total_Fee") 
		                + "\nAmount Paid: --------------R" + results3.getFloat("amount_paid")  
		                + "\nDeadline: -----------------" + results3.getString("deadline") 
		                + "\nFinalised: ----------------" + results3.getString("finalise") 
		                + "\nCompletion Date: ----------" + results3.getString("comp_date") 
		                + "\nCustomer: -----------------" + results3.getString("Name")                    
    	                + "\nProject Manager: ----------" + results3.getString("proj_manager")      
    	                + "\nArchitect: ----------------" + results3.getString("architect")               
    	                + "\nStructural Engineer: ------" + results3.getString("engineer")      
    	                + "\n"
    	                + "___________________________________________________________________________________________________________"
					);
		        
		        }
	
	       }
	/**
	 * The 'viewOverdue' method allows users to view all project objects that are overdue in the project_details table in the 
	 * external 'poispms' database.
	 * <p>
	 * When called on, the method runs through all deadlines of projects that are similtaniously past the deadline and
	 * defined as not finalised ('No') and where a completion date is not being set - 'None'.
	 * If there is no overdue projects then if statement will print out that there is no overdue projects
	 * appropriate error message is displayed to the user.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 * @throws ParseException occurs if a date string is in the wrong format to be parsed 
	 */
	public void viewOverdue(Statement statement) throws SQLException, ParseException {
		
		
		
		// Overdue projects will be incomplete, therefore only the deadline date info from columns of incomplete projects are located.
		ResultSet results4 = statement.executeQuery("SELECT * FROM project_details WHERE deadline < curdate() AND finalise = 'No' AND comp_date = 'None'");
		
		if (results4.next() == false) {
			System.out.println("There is no overdue projects.");
		} else {
		
		  do {
			  System.out.println( 
			         "\n"
				 + "___________________________________________________________________________________________________________"
				 + "\n"
				 + "\nProject Number: -----------" + results4.getInt("proj_num")
		                 + "\nProject Name: -------------" + results4.getString("proj_name") 
		                 + "\nBuilding Type: ------------" + results4.getString("build_type")        
		                 + "\nPhysical Address: ---------" + results4.getString("address") 
		                 + "\nERF Number: ---------------" + results4.getString("erf_num") 
		                 + "\nTotal Fee: ----------------R" + results4.getFloat("total_Fee") 
		                 + "\nAmount Paid: --------------R" + results4.getFloat("amount_paid")  
		                 + "\nDeadline: -----------------" + results4.getString("deadline") 
		                 + "\nFinalised: ----------------" + results4.getString("finalise") 
		                 + "\nCompletion Date: ----------" + results4.getString("comp_date") 
		                 + "\nCustomer: -----------------" + results4.getString("Name")                    
 	                         + "\nProject Manager: ----------" + results4.getString("proj_manager")      
 	                         + "\nArchitect: ----------------" + results4.getString("architect")               
 	                         + "\nStructural Engineer: ------" + results4.getString("engineer")      
 	                         + "\n"
 	                         + "___________________________________________________________________________________________________________"
					);
		  } while (results4.next());
		  
		
		}
		
	  
		
	}
	
	/**
	 * This method allows users to find project objects from the project_details table in the external 'poisepms' 
	 * database by either entering the project name or number.
	 * <p>
	 * Using either name or number, the project is then located from the external database and displayed in an easy-to-read-format.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
	public void findProject(Statement statement) throws SQLException {
		
		System.out.println("Would you like to search for your project by 1.) project number or 2.) project name? \nPlease select either 1 or 2.");
		int search_Choice = intCheck("Number search option");
		
		/* If they choose option 1, they are prompted to enter the project number.
		 * Once the number has been entered, the program selects all info related to that project to display to the user.
		 */
		if (search_Choice == 1) {
			System.out.println("\nPlease enter the number of the project you wish to locate: ");
			int proj_num = intCheck("Project Number");
			
			System.out.println("\nPlease view your project details below: \n");
			
			ResultSet results6 = statement.executeQuery("SELECT * from project_details WHERE proj_num = " + proj_num);
			
			// Iterating through project info by column of the project selected by the user.
			while (results6.next()) {
	    		System.out.println(
	    		   "\n"
	    		   +"________________________________________________________________________________________________________"
	    		   + "\n"
	    		   + "Project Number: \t" + results6.getInt("proj_num")
	    	           + "\nProject Name: \t" + results6.getString("proj_name") 
	    	           + "\nBuilding Type: \t" + results6.getString("build_type")        
	    	           + "\nPhysical Address: " + results6.getString("address") 
	    	           + "\nERF Number: \t" + results6.getString("erf_num") 
	    	           + "\nTotal Fee: \tR" + results6.getFloat("total_fee") 
	    	           + "\nAmount Paid: \t" + results6.getFloat("amount_paid")  
	    	           + "\nDeadline: \t" + results6.getString("deadline") 
	    	           + "\nFinalised: \t" + results6.getString("finalise") 
	    	           + "\nCompletion Date: " + results6.getString("comp_date") 
	    	           + "\nCustomer: \t" + results6.getString("Name")                     
	    	           + "\nProject Manager: \t" + results6.getString("proj_manager")      
	    	           + "\nArchitect: \t" + results6.getString("architect")              
	    	           + "\nStructural Engineer: \t" + results6.getString("engineer")      
	    	           + "\n"
	    	           + "_______________________________________________________________________________________________________"
	    	                
	                 );
			}
		/* If the user selects option 2, they are prompted to enter the project name. 
		 * Once entered, the program selecst all info related to that project to display to the user.
		 */
		} else if (search_Choice == 2) {
			System.out.println("\nPlease enter the name of the project you wish to locate: ");
			String proj_name = stringCheck("project name");
			
			System.out.println("\nPlease view your project details below: \n");
			
			ResultSet results7 = statement.executeQuery("SELECT * from project_details WHERE proj_name = '" + proj_name + "'");
			
			// Iterating through project info by column of the project selected by the user.
			while (results7.next()) {
	    		System.out.println(
	    	          "\n"
	    	          + "_______________________________________________________________________________________________________"
	    	          + "\n"
	    	          +  "Project Number: \t" + results7.getInt("proj_num")
	    	          + "\nProject Name: \t" + results7.getString("proj_name") 
	    	          + "\nBuilding Type: \t" + results7.getString("build_type")        
	    	          + "\nPhysical Address: " + results7.getString("address") 
	    	          + "\nERF Number: \t" + results7.getString("erf_num") 
	    	          + "\nTotal Fee: \tR" + results7.getFloat("total_fee") 
	    	          + "\nAmount Paid: \t" + results7.getFloat("amount_paid")  
	    	          + "\nDeadline: \t" + results7.getString("deadline") 
	    	          + "\nFinalised: \t" + results7.getString("finalise") 
	    	          + "\nCompletion Date: " + results7.getString("comp_date") 
	    	          + "\nCustomer: \t" + results7.getString("Name")                     
	    	          + "\nProject Manager: \t" + results7.getString("proj_manager")      
	    	          + "\nArchitect: \t" + results7.getString("architect")               
	    	          + "\nStructural Engineer: \t" + results7.getString("engineer")      
	    	          + "\n"
	    	          + "_______________________________________________________________________________________________________"
	            );
			}
		}
	}
	/**
	 * This method displays all information from the main_project_info table in the PoisePMS database when called on.
	 * <p>
	 * @param statement statement object linked to connection to perform SQL commands
	 * @throws SQLException occurs if there is an error accessing the database information
	 */
     public void printAllFromTable(Statement statement) throws SQLException{
        
		// Selecting all information (i.e. all rows) from the main_project_info table.
    	ResultSet results = statement.executeQuery("SELECT * FROM project_details");
    	
    	// Iterating through info in each column to display to the user.
    	while (results.next()) {
    		
    		System.out.println(
    			"\n"
    			+ "___________________________________________________________________________________________________________________"
    			+ "\n"
                        + "\nProject Number: ----------" + results.getInt("proj_num")                 
                        + "\nProject Name: ------------" + results.getString("proj_name") 
                        + "\nBuilding Type: -----------" + results.getString("build_type")        
                        + "\nPhysical Address: --------" + results.getString("address") 
                        + "\nERF Number: --------------" + results.getString("erf_Num") 
                        + "\nTotal Fee: ---------------R" + results.getFloat("total_fee") 
                        + "\nAmount Paid: -------------R" + results.getFloat("amount_paid")  
                        + "\nDeadline: ----------------" + results.getString("deadline") 
                        + "\nFinalised: ---------------" + results.getString("finalise")
                        + "\nCompletion Date: ---------" + results.getString("comp_date") 
                        + "\nCustomer: ----------------" + results.getString("Name")                     //THIS IS ADDED 1 table main_project_info with this 4 added
                        + "\nProject Manager: ---------" + results.getString("proj_manager")      // THIS IS ADDED
                        + "\nArchitect: ---------------" + results.getString("architect")               // This is ADDED
                        + "\nStructural Engineer: -----" + results.getString("engineer")
                        + "\n"
                        + "___________________________________________________________________________________________________________________"
            );
    }
}
     public void displayPersons(Statement statement) throws SQLException {
 		
 		System.out.println("Would you like to search for your project by 1.) project number or 2.) Customer? \nPlease select either 1 or 2.");
 		int search_Choice = intCheck("Number search option");
 		
 		/* If they choose option 1, they are prompted to enter the project number.
 		 * Once the number has been entered, the program selects all info related to that project to display to the user.
 		 */
 		if (search_Choice == 1) {
 			System.out.println("\nPlease enter the number of the project you wish to locate: ");
 			int proj_num = intCheck("project number");
 			
 			System.out.println("\nPlease view your project details below: \n");
 			
 			ResultSet results1 = statement.executeQuery("SELECT * from person_details WHERE proj_num = " + proj_num);
 			
 	    // Iterating through project info by column of the project selected by the user.
 		while (results1.next()) {  
 					System.out.println( 
 							"\n"
 							+ "_______________________________________________________________________________________________________"
 							+ "\n"
 							+ "\nProject Number: --------------------" + results1.getInt("proj_num")
 							+ "\nCustomer Name: ---------------------" + results1.getString("Name")
 							+ "\nContact Number: --------------------" + results1.getInt("Contact_Num")
 							+ "\nPhysical Address -------------------" + results1.getString("Physical_Address")
 							+ "\nEmail Address: ---------------------" + results1.getString("Email_Address")
 							+ "\nManager Name: ----------------------" + results1.getString("proj_manager")
 							+ "\nManager Contact Number: ------------" + results1.getInt("manager_contact")
 							+ "\nManager Physical Address: ----------" + results1.getString("manager_address")
 							+ "\nManager Email Address: -------------" + results1.getString("manager_email")
 							+ "\nArchitect Name: --------------------" + results1.getString("architect")
 							+ "\nArchitect Contact Number: ----------" + results1.getInt("arch_num")
 							+ "\nArchitect Physical Address: --------" + results1.getString("arch_address")
 							+ "\nArchitect Email Address: -----------" + results1.getString("arch_email")
 							+ "\nEngineer Name: ---------------------" + results1.getString("engineer")
 							+ "\nEngineer Contact Number: -----------" + results1.getInt("eng_num")
 							+ "\nEngineer Physical Address: ---------" + results1.getString("eng_address")
 							+ "\nEngineer Email Address: ------------" + results1.getString("eng_email")
 							+ "\n"
 							+ "_______________________________________________________________________________________________________"
 							);
 	            
 			}
 		/* If the user selects option 2, they are prompted to enter the project name. 
 		 * Once entered, the program selecst all info related to that project to display to the user.
 		 */
 		} else if (search_Choice == 2) {
 			System.out.println("\nPlease enter the name of the project you wish to locate: ");
 			String Name = stringCheck("Manager Name");
 			
 			System.out.println("\nPlease view your person details below: \n");
 			
 			ResultSet results10 = statement.executeQuery("SELECT * from person_details WHERE Name = '" + Name + "'");
 			
 			// Iterating through project info by column of the project selected by the user.
 			while (results10.next()) {  // Customer details displayed using iterator in table.
					System.out.println(
							"\n"
							+ "_______________________________________________________________________________________________________"
							+ "\n"
							+ "\nProject Number: " + results10.getInt("proj_num")
							+ "\nCustomer Name: " + results10.getString("Name")
							+ "\nContact Number: " + results10.getInt("Contact_Num")
							+ "\nPhysical Address: " + results10.getString("Physical_Address")
							+ "\nEmail Address: " + results10.getString("Email_Address")
							+ "\nManager Name: " + results10.getString("proj_manager")
							+ "\nManager Contact Number: " + results10.getInt("manager_contact")
							+ "\nManager Physical Address: " + results10.getString("manager_address")
							+ "\nManager Email Address: " + results10.getString("manager_email")
							+ "\nArchitect Name: " + results10.getString("architect")
							+ "\nArchitect Contact Number: " + results10.getInt("arch_num")
							+ "\nArchitect Physical Address: " + results10.getString("arch_address")
							+ "\nArchitect Email Address: " + results10.getString("arch_email")
							+ "\nEngineer Name: " + results10.getString("engineer")
							+ "\nEngineer Contact Number: " + results10.getInt("eng_num")
							+ "\nEngineer Physical Address: " + results10.getString("eng_address")
							+ "\nEngineer Email Address: " + results10.getString("eng_email")
							+ "\n"
							+ "_______________________________________________________________________________________________________"
							);
		
	                   }
              
                  }
	
	   }

    }