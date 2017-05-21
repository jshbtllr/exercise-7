package com.exercise7.core.dao;

import com.exercise7.core.model.Employee;
import com.exercise7.core.model.EmployeeGradeDTO;
import com.exercise7.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
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
import org.hibernate.transform.Transformers;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeDAO extends GenericDAO {
	public static List <Employee> showEmployees(Integer sort, Integer order) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Criteria criteria = null;
		List <Employee> list = null;
		
		try {
			transaction = session.beginTransaction();
			criteria = session.createCriteria(Employee.class, "employee");
			//criteria.createAlias

			if(sort == 1) {
				if(order == 1) {
					criteria.addOrder(Order.asc("employee.person.lastName"));
				} else {
					criteria.addOrder(Order.desc("employee.person.lastName"));
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
			if(order != 0) {	
				for ( Employee employee : list ) {
					Hibernate.initialize(employee.getRole());
					Hibernate.initialize(employee.getContactInfo());
				}
			}	
			System.out.println("Number of employees: " + list.size());	
		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

		return list;				
	}	

	public static Employee getEmployeeCollection(Long employeeId) {
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
			Hibernate.initialize(employee.getRole());
			Hibernate.initialize(employee.getContactInfo());
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

			if(option != 3) {
				criteria = session.createCriteria(Employee.class, "employee");
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("employee.person.firstName"), "firstName");
				projectionList.add(Projections.property("employee.person.lastName"), "lastName");
				projectionList.add(Projections.property("gradeWeightAverage"), "gradeWeightAverage");
				criteria.setProjection(projectionList);

				DetachedCriteria gwa = DetachedCriteria.forClass(Employee.class);
				
				if(option == 1) {
					System.out.print("Employee with the lowest GWA is ");
					gwa.setProjection(Property.forName("gradeWeightAverage").min());
					criteria.add(Property.forName("gradeWeightAverage").eq(gwa));

				} else if(option == 2) {
					System.out.print("Employee with the highest GWA is ");
					gwa.setProjection(Property.forName("gradeWeightAverage").max());
					criteria.add(Property.forName("gradeWeightAverage").eq(gwa));
				}

				criteria.setResultTransformer(Transformers.aliasToBean(EmployeeGradeDTO.class));
				List <EmployeeGradeDTO> employeeGWA = criteria.list();
				for(EmployeeGradeDTO employee : employeeGWA) {
					System.out.println(employee.getFirstName() + " " +  employee.getLastName() + " with " + employee.getGradeWeightAverage() + " GWA.");
				}
			} else {
				System.out.print("Average GWA of All Registered employee is ");
				query = session.createQuery("SELECT avg(gradeWeightAverage) FROM Employee");
				System.out.println(query.list().get(0));
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