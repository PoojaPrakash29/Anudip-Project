package EmployeePortel.Portel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;





/**
 * DAO
 *
 */


public class Main
{
	Scanner sc = new Scanner(System.in);
	
	static Configuration config;
	static SessionFactory factory;
	static Session session;
	static Transaction transaction;
	
	public void Insert()
	{
		Employeedetails emp = new Employeedetails(); //creating object for the Entity
		
		//Scanner sc = new Scanner(System.in);
		System.out.println("Enter EmployeeId : ");
		int id = sc.nextInt();
		emp.setEmployeeid(id);
		
		System.out.println("Enter Employee's FirstName : ");
		String fname = sc.next();
		emp.setFirst_Name(fname);
		
		System.out.println("Enter Employee's LastName : ");
		String lname = sc.next();
		emp.setLast_Name(lname);
		
		System.out.println("Enter Employee's Date-Of-Date : ");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String date = sc.next();
		emp.setDob(date);
		
		System.out.println("Enter Employee's Salary : ");
		double salary = sc.nextDouble();
		emp.setSalary(salary);
		
		System.out.println("Enter Employee's Department : ");
		String dept = sc.next();
		emp.setDepartment(dept);
		
		session.saveOrUpdate(emp);
		transaction =  session.beginTransaction();
		transaction.commit();
	}
	public void ViewAllData()
	{
		System.out.println("+------------+----------------------+--------------------+--------------+-----------------+---------------------+");
		System.out.println("| EmployeeId |  Employee-FirstName  | Employee-FirstName | Employee-DOB | Employee-Salary | Employee-Department |");
		System.out.println("+------------+----------------------+--------------------+--------------+-----------------+---------------------+");
		
		String hqlQuery = "from Employeedetails";
		List<Employeedetails> employee = session.createQuery(hqlQuery,Employeedetails.class).list();
		for(Employeedetails emp : employee)
		{
			System.out.printf("| %-10s | %-20s | %-18s | %-12s | %-15s | %-19s |\n",
					emp.getEmployeeid(), emp.getFirst_Name(), emp.getLast_Name(),
					emp.getDob(),emp.getSalary(),emp.getDepartment());
			System.out.println("+------------+----------------------+--------------------+--------------+-----------------+---------------------+");
		}
	}
	public void ViewDatabyId(int id)
	{
		System.out.println("+------------+----------------------+--------------------+--------------+-----------------+---------------------+");
		System.out.println("| EmployeeId |  Employee-FirstName  | Employee-FirstName | Employee-DOB | Employee-Salary | Employee-Department |");
		System.out.println("+------------+----------------------+--------------------+--------------+-----------------+---------------------+");
		Employeedetails emp  = session.get(Employeedetails.class,id); //return data
		
		System.out.printf("| %-10s | %-20s | %-18s | %-12s | %-15s | %-19s |\n",
				emp.getEmployeeid(), emp.getFirst_Name(), emp.getLast_Name(),
				emp.getDob(),emp.getSalary(),emp.getDepartment());
		System.out.println("+------------+----------------------+--------------------+--------------+-----------------+---------------------+");
		
	}
	public void show()
	{
		System.out.println("-------------------------+++++------------------------");
		System.out.println("How do you want to view the Employees Information");
		System.out.println("-------------------------+++++------------------------");
		while(true)
		{
			System.out.println("1. View All details of the Employee");
			System.out.println("2. View Individual Employee's Detail");
			System.out.println("3. Exit");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			
			switch(ch)
			{
			case 1 : ViewAllData();
					 break;
					 
			case 2 : System.out.println("Enter Employee-Id : ");
					 int id = sc.nextInt();
					 ViewDatabyId(id);
					 break; 
					 
			case 3 : return;
			
			default : System.out.println("Enter Valid Option");
			}
		}	
	}
	

	public void deletebyid(int id)
	{
		Employeedetails emp = session.get(Employeedetails.class,id);
		transaction = session.beginTransaction();
		String fname = emp.getFirst_Name();
		String lname = emp.getLast_Name();
		session.delete(emp);
		//session.remove(fname);
		transaction.commit();
		System.out.println("Employee " + fname +"-"+ lname + " Removed Succesfully...." );
	}
	public void deletedata()
	{
		System.out.println("Are you sure, You want to delete Entire data");
		System.out.println("Yes / No");
		String choice = sc.next();
		switch(choice)
		{
		case "yes || Y || Yes || YES" : System.out.println("yes");
					 transaction = session.beginTransaction();
					 String hql = "delete from Employeedetails";
					 Query query = session.createQuery(hql);
					 query.executeUpdate();
					 transaction.commit();	
					 System.out.println("All Data's Deleted Sucessfully");
					 break;
					 
		case "No" : return;
		
		default : System.out.println("Enter Valid Option");
		}
	}
	public void delete()
	{
		System.out.println("-------------------------+++++------------------------");
		System.out.println("How do you wish to remove an  Employee");
		System.out.println("-------------------------+++++------------------------");
		
		while(true)
		{
			System.out.println("1. Remove Individual Employee");
			System.out.println("2. Remove All Employee Data");
			System.out.println("3. Exit");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			
			switch(ch)
			{
			case 1 : System.out.println("Enter Employee-Id : ");
			 		 int id = sc.nextInt();
			         deletebyid(id);
			         break; 
					 
			case 2 : deletedata();
					 break; 
					 
			case 3 :return;
			
			default : System.out.println("Enter Valid Option");
			}
		}
		
	}
	public void update(int id)
	{
		update up = new update();
		System.out.println("---------------+++++---------------");
		System.out.println("What Details You want to Update ?");
		System.out.println("---------------+++++---------------");
		Employeedetails emp = session.get(Employeedetails.class,id);
		//transaction = session.beginTransaction();
		while(true)
		{
			System.out.println("1. Update Employee's FirstName");
			System.out.println("2. Update Employee's LastName");
			System.out.println("3. Update Employee's DOB");
			System.out.println("4. Update Employee's Salary");
			System.out.println("5. Update Employee's Department");
			System.out.println("6. Exit");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			switch (ch)
			{
			case 1 : up.fname(id);
					 break;
					
					
			case 2 : up.lname(id);	
					 break;
					 
			case 3 : up.dob(id);
			 		 break;	
				
			case 4 : up.salary(id);
			 		 break;	
				
			case 5 : up.dept(id);
	 		 		 break;	
				
			case 6 : 
				     return;
				     
			default : System.out.println("Enter Valid Option");
			}
		}
		
	}
	public class update
	{
		public void fname(int id)
		{
			Employeedetails emp = session.get(Employeedetails.class,id);
			transaction = session.beginTransaction();
			System.out.println("Enter employee's Firstname to update:");
			String name = sc.nextLine();
			emp.setFirst_Name(name);
			session.update(emp);
			transaction.commit();
			System.out.println("Employee's Firstname updated to " +name);
		}
		public void lname(int id)
		{
			Employeedetails emp = session.get(Employeedetails.class,id);
			transaction = session.beginTransaction();
			System.out.println("Enter employee's Lastname to update:");
			String lname = sc.nextLine();
			emp.setLast_Name(lname);
			session.update(emp);
			transaction.commit();
			System.out.println("Employee's Lastname updated to " +lname);
		}
		public void dob(int id)
		{
			Employeedetails emp = session.get(Employeedetails.class,id);
			transaction = session.beginTransaction();
			System.out.println("Enter employee's DOB to update:");
			String dob = sc.nextLine();
			emp.setDob(dob);
			session.update(emp);
			transaction.commit();
			System.out.println("Employee's Lastname updated to " +dob);
		}
		public void salary(int id)
		{
			Employeedetails emp = session.get(Employeedetails.class,id);
			transaction = session.beginTransaction();
			System.out.println("Enter employee's Recent-Pay to update:");
			double salary =sc.nextDouble();
			emp.setSalary(salary);
			session.update(emp);
			transaction.commit();
			System.out.println("Employee's Recent-Pay updated to " +salary);
		}
		public void dept(int id)
		{
			Employeedetails emp = session.get(Employeedetails.class,id);
			transaction = session.beginTransaction();
			System.out.println("Enter employee's Department to update:");
			String dept = sc.nextLine();
			emp.setDepartment(dept);
			session.update(emp);
			transaction.commit();
			System.out.println("Employee's Department updated to " +dept);
		}
		
	}
    public static void main( String[] args )
    {
        Main main = new Main(); // creating object for class-Main
        
        config = new Configuration(); //class which reads both entity class and configuration data
   	 	config.configure();//checks the config file syntax
   	
   	 	factory = config.buildSessionFactory();//interface which takes metadata and build connection
   	 
   	 	session = factory.openSession(); //create Session object
   	 	
   	 	System.out.println("===========================================");
   	 	System.out.println("-------------EMPLOYEES PORTEL-------------");
   	 	System.out.println("===========================================");
   	 	System.out.println("1. Register an Employee.");
   	 	System.out.println("2. View Employees.");
   	 	System.out.println("3. Update an Employee Details.");
   	 	System.out.println("4. Delete Employee.");
   	 	
   	 	
   	 	Scanner sc = new Scanner(System.in);
   	 	int choice = sc.nextInt();
   	 	
   	 	switch(choice)
   	 	{
   	 	case 1 : main.Insert();
   	 			 System.out.println("Employee Data Registered Sucessfully!...");
   	 			 break;
   	 			 
   	 	case 2 : main.show(); 			
			 	 break;
   	 			 
   	 	case 3 : System.out.println("Enter Employee-Id : ");
   	 			 int id = sc.nextInt();
   	 			 main.update(id);
   	 			 break;
   	 			 
   	 	case 4 : main.delete();
   	 			 break;
   	 			 
   	 	default : System.out.println("Enter Valid Option");
   	 	}
   	 	
   	 	//closing configurations
        session.close();
        factory.close();
        
    }
}
