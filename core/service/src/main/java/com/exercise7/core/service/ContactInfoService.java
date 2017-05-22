package com.exercise7.core.service;
import com.exercise7.core.model.ContactInfo;
import com.exercise7.core.model.Employee;
import com.exercise7.util.InputUtil;
import com.exercise7.core.dao.EmployeeDAO;
import java.util.Set;
import java.util.Iterator;
import org.apache.commons.validator.routines.EmailValidator;

public class ContactInfoService {
	public static void addContactInfo() {
		String infoType = null;
		Employee employee = null;
		Set <ContactInfo> contacts;

		EmployeeService.listEmployees(4, 0);
		System.out.print("Add contact info to which employee: ");
		Long employeeId = InputUtil.inputOptionCheck().longValue();

		while (!(EmployeeDAO.employeeCheck(employeeId))) {
			System.out.print("Employee ID chosen does not exist. Enter a new employee id to delete: ");
			employeeId = InputUtil.inputOptionCheck().longValue();
		}	

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		contacts = employee.getContactInfo();	

		contacts = addContactSet(contacts, employee);
		employee.setContactInfo(contacts);
		EmployeeDAO.update(employee);
	}


	public static Set <ContactInfo> addContactSet(Set <ContactInfo> contacts, Employee employee) {	
		Boolean exist = false;
		System.out.println("Add Contact Information: ");
		System.out.println("[1]    Add email");
		System.out.println("[2]    Add telephone");
		System.out.println("[3]    Add cellphone");
		System.out.print("Input option: ");
		
		Integer option = InputUtil.inputOptionCheck(3);		
		System.out.print("Input Information Details: ");
		String infoDetail = InputUtil.getRequiredInput();

		ContactInfo addInfo = checkInfo(infoDetail, option);

		if(contacts.isEmpty()) {
			contacts.add(addInfo); 			
		} else {
			for(ContactInfo list : contacts) {
				if(list.getInfoDetail().equals(addInfo.getInfoDetail())) {
					exist = true;
					System.out.println("Contact Info already added to employee");
				}
			}
			if(!exist) {
				contacts.add(addInfo);		
			}
		}

		return contacts;
	}

	public static ContactInfo checkInfo(String information, Integer option) {
		String infoType = null;
		if(option == 1) {
			infoType = "email";
			while(!EmailValidator.getInstance().isValid(information)) {
				System.out.print("Input is not a valid email. Enter a valid email: ");
				information = InputUtil.getRequiredInput();				
			}
		} else if(option == 2) {
			infoType = "telephone";
			while(!information.matches("^[1-9]{1}\\d{6}")) {
				System.out.print("Input is not a valid telephone. Enter a valid 7 digit telephone: ");
				information = InputUtil.getRequiredInput();
			}
		} else {
			infoType = "cellphone";
			while(!information.matches("^09\\d{9}")) {
				System.out.print("Input is not a valid cellphone. Enter a valid 11 digit cellphone (09xxxxxxxxx): ");
				information = InputUtil.getRequiredInput();
			}			
		}

		ContactInfo addInfo = new ContactInfo(infoType, information);	
		return addInfo;		
	}

	public static void removeContactInfo() {
		EmployeeService.listEmployees(4, 0);
		String information;
		Long employeeId;
		Employee employee;
		Boolean exist = false;
		Set <ContactInfo> contacts = null;
		Iterator <ContactInfo> iterator = null;

		System.out.print("Delete a contact info from which employee: ");
		employeeId = InputUtil.inputOptionCheck().longValue();

		while (!(EmployeeDAO.employeeCheck(employeeId))) {
			System.out.print("Employee ID chosen does not exist. Enter a new employee id to delete: ");
			employeeId = InputUtil.inputOptionCheck().longValue();
		}

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		contacts = employee.getContactInfo();

		System.out.print("Employee has ");

		if(!contacts.isEmpty()) {
			System.out.println("the below Contact Info: ");
			for(ContactInfo list : contacts) {
				System.out.println("Contact Info Type: " + list.getInfoType());
				System.out.println("Contact Info: " + list.getInfoDetail());
				System.out.println("-------------------");
			}
			System.out.print("Input the Contact Info to remove: ");
			information = InputUtil.getRequiredInput();

			iterator = contacts.iterator();
			while(iterator.hasNext()) {
				if(information.equals(iterator.next().getInfoDetail())) {
					exist = true;
					iterator.remove();
				}
			}

			employee.setContactInfo(contacts);
			EmployeeDAO.update(employee);

			if(!exist) {
				System.out.println("Contact Info given not assigned to employee");
			}	
		} else {
			System.out.println("no Contact Info to delete");
		}

	}

	public static void updateContactInfo() {
		EmployeeService.listEmployees(4, 0);
		Long employeeId;
		Employee employee;
		Boolean exist = false;
		String information;
		ContactInfo newInfo = null;
		Set <ContactInfo> contacts = null;
		Iterator <ContactInfo> iterator = null;		

		EmployeeService.listEmployees(4, 0);
		System.out.print("Add contact info to which employee: ");
		employeeId = InputUtil.inputOptionCheck().longValue();

		while (!(EmployeeDAO.employeeCheck(employeeId))) {
			System.out.print("Employee ID chosen does not exist. Enter a new employee id to delete: ");
			employeeId = InputUtil.inputOptionCheck().longValue();
		}	

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		contacts = employee.getContactInfo();

		System.out.print("Employee has ");

		if(!contacts.isEmpty()) {
			System.out.println("the below Contact Info: ");
			for(ContactInfo list : contacts) {
				System.out.println("Contact Info Type: " + list.getInfoType());
				System.out.println("Contact Info: " + list.getInfoDetail());
				System.out.println("-------------------");
			}
			System.out.print("Input the Contact Info to update: ");
			information = InputUtil.getRequiredInput();
			
			for(ContactInfo list : contacts) {
				if(information.equals(list.getInfoDetail())) {
					Integer option = null;
					if (list.getInfoType().equals("email")) {
						option = 1;
					} else if (list.getInfoType().equals("telephone")) {
						option = 2;
					} else {
						option = 3;
					}

					exist = true;
					System.out.print("Input new contact detail: ");
					String infoDetail = InputUtil.getRequiredInput();
					newInfo = checkInfo(infoDetail, option);					
					list.setInfoDetail(newInfo.getInfoDetail());
				}
			}

			employee.setContactInfo(contacts);
			EmployeeDAO.update(employee);

			if(!exist) {
				System.out.println("Contact Info not assigned to employee");
			}

		} else {
			System.out.println("no contact information");
		}		
	}

	public static void listContactInfo() {
		EmployeeService.listEmployees(4, 0);
		Long employeeId;
		Employee employee = null;
		Set <ContactInfo> contacts = null;
		System.out.print("Show Contact Information of which EmployeeId: ");
		employeeId = InputUtil.inputOptionCheck().longValue();

		while (!(EmployeeDAO.employeeCheck(employeeId))) {
			System.out.print("Employee ID chosen does not exist. Enter a new employee id to delete: ");
			employeeId = InputUtil.inputOptionCheck().longValue();
		}			

		employee = EmployeeDAO.getEmployeeCollection(employeeId);	
		contacts = employee.getContactInfo();

		if(!contacts.isEmpty()) {
			System.out.println("Employee has below Contact Info: ");
			for(ContactInfo list : contacts) {
				System.out.println("Contact Info Type: " + list.getInfoType());
				System.out.println("Contact Info: " + list.getInfoDetail());
				System.out.println("-------------------");
			}
		} else {
			System.out.println("Employee has no Contact Info");
		}
	}
}