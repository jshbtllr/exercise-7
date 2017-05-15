package com.exercise7.core.dao;

import com.exercise7.core.model.Employee;
import com.exercise7.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeDAO {
	public static void addEmployee (Employee employee) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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

	public static List <Employee> showEmployees(Integer sort, Integer order) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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

	public static void deleteEmployee(Employee employee) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;

		try {
			transaction = session.beginTransaction();
			session.delete(employee);
			transaction.commit();		

		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}			
	}

	public static void deleteContactInfo(Long contactId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;

		try {
			transaction = session.beginTransaction();
			query = session.createQuery("DELETE FROM ContactInfo WHERE id = :id");
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

	public static Employee getEmployee(Long employeeId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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

	public static void updateEmployee(Employee employee) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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

	public static Boolean employeeCheck(Long employeeId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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

	public static void gwaStatistics(Integer option) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;
		Criteria criteria = null;

		try {
			transaction = session.beginTransaction();
			DetachedCriteria minGWA = DetachedCriteria.forClass(Employee.class)
				.setProjection(Property.forName("gradeWeightAverage").min());
			criteria = session.createCriteria(Employee.class);
			ProjectionList projectionList = Projections.projectionList();
			if(option == 1) {
				System.out.print("Minimmum GWA of All Registered employee is ");
				//query = session.createQuery("SELECT a.firstName, a.lastName, a.gradeWeightAverage FROM Employee a where a.gradeWeightAverage = (SELECT min(b.gradeWeightAverage) FROM Employee b)");
				
				projectionList.add(Projections.property("firstName"));
				projectionList.add(Projections.property("lastName"));
				projectionList.add(Projections.property("gradeWeightAverage"));
				criteria.setProjection(projectionList);
				criteria.add(Property.forName("gradeWeightAverage").eq(minGWA));
				//criteria.setProjection(Projections.min("gradeWeightAverage"));

			} else if(option == 2) {
				System.out.print("Maximum GWA of All Registered employee is ");
				query = session.createQuery("SELECT max(gradeWeightAverage) FROM Employee");
			} else {
				System.out.print("Average GWA of All Registered employee is ");
				query = session.createQuery("SELECT avg(gradeWeightAverage) FROM Employee");
			}



			List <Object []> test = criteria.list();
			for(Object [] employee : test) {
				System.out.println(employee[0] + " " +  employee[1] + " " + employee[2]);
			}
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