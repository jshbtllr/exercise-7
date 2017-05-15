package com.exercise6.core.dao;

import com.exercise6.core.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeDAO {
	public static void addEmployee (SessionFactory sessionFactory, Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
		} catch(HibernateException he) {
			if (transaction != null)  {
				transaction.rollback();
			}
			System.out.println("Error encountered adding employee.");
			he.printStackTrace();
		} finally {
			session.close();
		}	
	}

	public static List <Employee> showEmployees(SessionFactory sessionFactory, Integer sort, Integer order) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Criteria criteria = null;
		List <Employee> list = null;
		
		try {
			transaction = session.beginTransaction();
			criteria = session.createCriteria(Employee.class);

			if(sort == 1) {
				if(order == 1) {
					criteria.addOrder(Order.asc("lastName"));
				} else {
					criteria.addOrder(Order.desc("lastName"));
				}
			} else if(sort == 3) {
				if(order == 1) {
					criteria.addOrder(Order.asc("hireDate"));
				} else {
					System.out.println("Sorts by hiredate desc");
					criteria.addOrder(Order.desc("hireDate"));
				}
			}

			list = criteria.list();			
		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

		return list;				
	}		

	public static void deleteEmployee(SessionFactory sessionFactory, Long employeeId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;

		try {
			transaction = session.beginTransaction();
			query = session.createSQLQuery("DELETE FROM CONTACTINFO WHERE EMPLOYEEID = :employeeid");
			query.setParameter("employeeid", employeeId);
			Integer deletedContacts = query.executeUpdate();
			query = session.createQuery("DELETE FROM Employee WHERE id = :employeeid");
			query.setParameter("employeeid", employeeId);
			Integer deletedEmployee = query.executeUpdate();	
			transaction.commit();			
			System.out.println(deletedEmployee + " Employee Deleted.");

		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}			
	}

	public static void deleteContactInfo(SessionFactory sessionFactory, Long employeeId, Long contactId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;

		try {
			transaction = session.beginTransaction();
			query = session.createSQLQuery("DELETE FROM CONTACTINFO WHERE EMPLOYEEID = :employeeid AND CONTACTID = :id");
			query.setParameter("employeeid", employeeId);
			query.setParameter("id", contactId);
			query.executeUpdate();
			transaction.commit();			
		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}			
	}	

	public static Employee getEmployee(SessionFactory sessionFactory, Long employeeId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Employee employee = null;
		Criteria criteria = null;

		try {
			transaction = session.beginTransaction();
			criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("id", employeeId));
			employee = (Employee) criteria.list().get(0);
		} catch(HibernateException he) {
			if(transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error getting employee");
			he.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
	}		

	public static void updateEmployee(SessionFactory sessionFactory, Employee employee) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(employee);
			transaction.commit();

		} catch(HibernateException he) {
			if (transaction != null)  {
				transaction.rollback();
			}
			System.out.println("Error occurred when updating");
			he.printStackTrace();
		} finally {
			session.close();
		}
	}		

	public static Boolean employeeCheck(SessionFactory sessionFactory, Long employeeId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;
		Boolean present = false;

		try {
			transaction = session.beginTransaction();
			query = session.createQuery("SELECT id FROM Employee WHERE id = :employeeid");
			query.setParameter("employeeid", employeeId);

			present = !(query.list().isEmpty());
		} catch(HibernateException he) {
			if (transaction != null)  {
				transaction.rollback();
			}
			System.out.println("Error occurred");
			he.printStackTrace();
		} finally {
			session.close();
		}
		
		return present;
	}

	public static void gwaStatistics(SessionFactory sessionFactory, Integer option) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;

		try {
			transaction = session.beginTransaction();
			if(option == 1) {
				System.out.print("Minimmum GWA of All Registered employee is ");
				query = session.createQuery("SELECT min(gradeWeightAverage) FROM Employee");
			} else if(option == 2) {
				System.out.print("Maximum GWA of All Registered employee is ");
				query = session.createQuery("SELECT max(gradeWeightAverage) FROM Employee");
			} else {
				System.out.print("Average GWA of All Registered employee is ");
				query = session.createQuery("SELECT avg(gradeWeightAverage) FROM Employee");
			}
			System.out.println(query.list().get(0));
		} catch(HibernateException he) {
			if (transaction != null)  {
				transaction.rollback();
			}
			System.out.println("Error occurred");
			he.printStackTrace();
		} finally {
			session.close();
		}		
	}
}

class gwaComparator implements Comparator <Employee> {
	public int compare(Employee a, Employee b) {
		return a.getGradeWeightAverage() < b.getGradeWeightAverage() ? -1 : a.getGradeWeightAverage() == b.getGradeWeightAverage() ? 0 : 1;
	}
}