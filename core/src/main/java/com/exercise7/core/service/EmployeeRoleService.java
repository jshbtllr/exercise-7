package com.exercise7.core.service;
import com.exercise7.core.model.Roles;
import com.exercise7.core.model.Employee;
import com.exercise7.util.InputUtil;
import com.exercise7.core.dao.RoleDAO;
import com.exercise7.core.dao.EmployeeDAO;
import com.exercise7.core.service.RoleService;
import java.util.Set;
import java.util.Iterator;

public class EmployeeRoleService {
	public static void addRemoveEmployeeRoles(Integer option) {			/*Option 1 add, Option 2 remove*/
		Employee employee = null;
		Long employeeId = null;
		Set <Roles> employeeRoles;

		EmployeeService.listEmployees(4, 0);
		if(option == 1) {
			System.out.print("Add role to which EmployeeId: ");
		} else {
			System.out.print("Remove role to which EmployeeId: ");
		}

		employeeId = InputUtil.inputOptionCheck().longValue();

		while (!(EmployeeDAO.employeeCheck(employeeId))) {
			System.out.print("Employee ID chosen does not exist. Enter a valid Employee ID: ");
			employeeId = InputUtil.inputOptionCheck().longValue();
		}

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		employeeRoles = employee.getRole();	

		if(option == 1) {
			employeeRoles = addRoleSet(employeeRoles);	
		} else {
			if(employeeRoles.isEmpty()) {
				System.out.println("No Roles to remove");
				return;
			}
			employeeRoles = removeRoleSet(employeeRoles);
		}

		employee.setRole(employeeRoles);
		EmployeeDAO.update(employee);
	}

	public static Set <Roles> addRoleSet(Set <Roles> roles) {
		Roles newRole = new Roles(" ", " ");
		Long roleId = null;
		Boolean exist = false;

		RoleService.listRoles(1, 1);
		System.out.print("Input the Role Id to add: ");
		roleId = InputUtil.inputOptionCheck().longValue();
		newRole.setId(roleId);

		if(!(RoleDAO.checkDuplicateRole(newRole, 4))) {
			System.out.println("Role ID is not a valid Role ID. Role not added");
			return roles;
		}
		newRole = RoleDAO.get(Roles.class, roleId);
		
		if(roles.isEmpty()) {
			roles.add(newRole);
		} else {
			for(Roles list : roles) {
				if(newRole.getId().equals(list.getId())) {
					exist = true;
					System.out.println("Role is already added to employee");
				}
			}
			if(!exist) {
				roles.add(newRole);
			}
		}
		return roles;
	}

	public static Set <Roles> removeRoleSet(Set <Roles> roles) {
		Roles deleteRole = new Roles(" ", " ");
		Long roleId = null;
		Boolean exist = false;
		Iterator <Roles> iterator = null;
			
		System.out.println("Available Roles for Employee: ");
		System.out.println("---------------");
		for (Roles list : roles) {
			System.out.println("Role ID: " + list.getId());	
			System.out.println("Role Code: " + list.getRoleCode());
			System.out.println("Role Name: " + list.getRoleName());
			System.out.println("---------------");
		}

		System.out.print("Input the Role Id to remove: ");
		roleId = InputUtil.inputOptionCheck().longValue();
		deleteRole.setId(roleId);

		if(!(RoleDAO.checkDuplicateRole(deleteRole, 4))) {
			System.out.println("Role ID is not a valid Role ID. Role not removed");
			return roles;
		}
		deleteRole = RoleDAO.get(Roles.class, roleId);
		
		iterator = roles.iterator();
		while(iterator.hasNext()) {
			if(deleteRole.getId().equals(iterator.next().getId())) {
				exist = true;
				iterator.remove();
			}
		}		


		if(!exist) {
			System.out.println("Role not assigned to employee");
		}

		return roles;
	}	

	public static void listEmployeeRoles() {
		EmployeeService.listEmployees(4, 0);
		Long employeeId;
		Employee employee = null;
		Set <Roles> roles = null;

		System.out.print("Show the roles of which EmployeeId: ");
		employeeId = InputUtil.inputOptionCheck().longValue();

		while (!(EmployeeDAO.employeeCheck(employeeId))) {
			System.out.print("Employee ID chosen does not exist. Enter a new employee id to delete: ");
			employeeId = InputUtil.inputOptionCheck().longValue();
		}		

		employee = EmployeeDAO.getEmployeeCollection(employeeId);
		roles = employee.getRole();

		System.out.print("Employee has ");

		if(roles.isEmpty()) {
			System.out.println("no available roles");
			System.out.println("---------------");
		} else {
			System.out.println("the below rolecode and rolename as roles");
			System.out.println("---------------");
			for (Roles list : roles) {
				System.out.println("Role Code: " + list.getRoleCode());
				System.out.println("Role Name: " + list.getRoleName());
				System.out.println("---------------");
			}
		}
	}	
}