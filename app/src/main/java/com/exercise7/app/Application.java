package com.exercise7.app;
import com.exercise7.util.InputUtil;
import com.exercise7.core.service.EmployeeService;
import com.exercise7.core.service.RoleService;
import com.exercise7.core.service.ContactInfoService;
import com.exercise7.core.service.EmployeeRoleService;
import com.exercise7.core.dao.EmployeeDAO;
import com.exercise7.core.model.Roles;
import com.exercise7.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Application {
	public static void main (String [] args) {
		Boolean exit = false;
		Boolean subFunctionFlag = false;
		String function = new String();
		Integer rows;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		while(!exit) {
			System.out.println("\nEmployee Database");
			System.out.println("Menu");
			System.out.println(" [1]    Create Employee");
			System.out.println(" [2]    Delete Employee");
			System.out.println(" [3]    Update Employee Details");
			System.out.println(" [4]    List all Employees");
			System.out.println(" [5]    Manage Employee Roles");
			System.out.println(" [6]    Manage Employee Contacts");
			System.out.println(" [7]    Role Management");
			System.out.println(" [8]    GWA Statistics");
			System.out.println(" [9]    Exit Tool");
			System.out.print("\nChoose corresponding number to choose a function: ");

			function = InputUtil.getRequiredInput();

			switch (function) {
				case "1":
					EmployeeService.createEmployee();
					break;
				case "2":
					EmployeeService.deleteEmployee();
					break;
				case "3":
					EmployeeService.updateEmployee();
					break;
				case "4":
					Integer sortFunction;
					Integer orderFunction;

					System.out.println("\nEmployee List sorted by: ");
					System.out.println("[1]    Last Name");
					System.out.println("[2]    General Weighted Average");
					System.out.println("[3]    Date Hired");
					System.out.print("Input Sort Type: ");

					sortFunction = InputUtil.inputOptionCheck(3);
					System.out.println("\nOrder: ");
					System.out.println("[1]    Ascending");
					System.out.println("[2]    Descending");	
					System.out.print("Input Sort order: ");	
					orderFunction = InputUtil.inputOptionCheck(2);

					EmployeeService.listEmployees(sortFunction, orderFunction);
					break;
				case "5":
				String employeeRoleFunction = new String();
					System.out.println("Manage Employee's Roles");

					while (!subFunctionFlag) {
						System.out.println("\n[1]    Add Roles to Employee");
						System.out.println("[2]    Remove Roles from Employee");
						System.out.println("[3]    List All Roles from Employee");						
						System.out.println("[4]    Exit");
						System.out.print("Choose Function: ");

						employeeRoleFunction = InputUtil.getRequiredInput();

						switch (employeeRoleFunction) {
							case "1":
								EmployeeRoleService.addRemoveEmployeeRoles(1);
								break;
							case "2":
								EmployeeRoleService.addRemoveEmployeeRoles(2);
								break;
							case "3":
								EmployeeRoleService.listEmployeeRoles();
								break;
							case "4":
								subFunctionFlag = true;
								System.out.println("Exit");
								break;
							default:
								System.out.println("\nInvalid Function, choose another function below:");
								break;
						}
					}
					break;
				case "6":
					String contactInfoFunction = new String();
					System.out.println("Manage Employee's Contact Information");

					while(!subFunctionFlag) {
						System.out.println("\n[1]    Add Contact Information");
						System.out.println("[2]    Remove Contact Information");
						System.out.println("[3]    Update Contact Information");
						System.out.println("[4]    List Contact Information");
						System.out.println("[5]    Exit");
						System.out.print("Choose corresponding number select a function: ");

						contactInfoFunction = InputUtil.getRequiredInput();

						switch (contactInfoFunction) {
							case "1":
								ContactInfoService.addContactInfo();
								break;
							case "2":
								ContactInfoService.removeContactInfo();
								break;
							case "3":
								ContactInfoService.updateContactInfo();
								break;
							case "4":
								ContactInfoService.listContactInfo();
								break;
							case "5":
								subFunctionFlag = true;
								System.out.println("Exit");
								break;
							default:
								System.out.println("\nInvalid Function, choose another function below:");
								break;
						}
					}

					break;
				case "7":
					String roleFunction = new String();
					System.out.println("Role Management");
					String roleName = new String();
					String roleCode = new String();				

					while(!subFunctionFlag) {
						System.out.println("[1]    Add Role");
						System.out.println("[2]    Remove Roles");
						System.out.println("[3]    Update Roles");
						System.out.println("[4]    List Roles");
						System.out.println("[5]    Exit");
						System.out.print("\nChoose an option above: ");

						roleFunction = InputUtil.getRequiredInput();

						switch (roleFunction) {
							case "1":
								System.out.println("Adding Role with the below information:");
								System.out.print("Provide RoleCode: ");
								roleCode = InputUtil.getRequiredInput();
								System.out.print("Provide RoleName: ");
								roleName = InputUtil.getRequiredInput();
								Roles addedRole = new Roles(roleName, roleCode);		
														
								RoleService.addRoles(addedRole);
								break;
							case "2":
								RoleService.removeRoles();
								break;
							case "3":
								RoleService.updateRoles();
								break;
							case "4":
								System.out.println("Sort Roles:");
								System.out.println("[1]    by RoleID");
								System.out.println("[2]    by RoleCode");
								System.out.println("[3]    by RoleName");
								System.out.print("Choose sort rule: ");
								Integer sortRule = InputUtil.inputOptionCheck(3);
								
								System.out.println("[1]    Ascending");
								System.out.println("[2]    Descending");
								System.out.print("Choose order rule: ");
								Integer orderRule = InputUtil.inputOptionCheck(2);

								RoleService.listRoles(sortRule, orderRule);
								break;
							case "5":
								subFunctionFlag = true;
								System.out.println("Exit Role Management");
								break;
							default:
								System.out.println("\nInvalid Function.");
								break;
						}
					}
					break;
				case "8":
					System.out.println("GWA Statistics");
					System.out.println("[1]    Lowest GWA");
					System.out.println("[2]    Highest GWA");
					System.out.println("[3]    Average GWA");
					System.out.print("Choose a function: ");
					Integer option = InputUtil.inputOptionCheck(3);
					EmployeeDAO.gwaStatistics(option);
					break;
				case "9":
					exit = true;
					HibernateUtil.shutdown();
					break;
				default:
					System.out.println(function + " is not a valid function. Choose another function");
					break;	
			}
			subFunctionFlag = false;
		}
	}
}