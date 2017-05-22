package com.exercise7.core.dao;

import com.exercise7.core.model.Roles;
import com.exercise7.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import java.util.List;


public class RoleDAO extends GenericDAO {
	public static List <Roles> showRoles(Integer sortRule, Integer orderRule) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Query query = null;
		Integer rows = new Integer(0);
		List <Roles> list = null;

		try {
			transaction = session.beginTransaction();

			if(sortRule == 1) {
				if(orderRule == 1) {
					query = session.createQuery("FROM Roles ORDER BY id");
				} else {
					query = session.createQuery("FROM Roles ORDER BY id DESC");
				}
			} else if(sortRule == 2) {
				if(orderRule == 1) {
					query = session.createQuery("FROM Roles ORDER BY roleCode");
				} else {
					query = session.createQuery("FROM Roles ORDER BY roleCode DESC");
				} 
			} else if(sortRule == 3) {
				if(orderRule == 1) {
					query = session.createQuery("FROM Roles ORDER BY roleName");
				} else {
					query = session.createQuery("FROM Roles ORDER BY roleName desc");
				}
			}

			list = query.setCacheable(true).list();
		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}

		return list;
	}		

	public static Boolean checkDuplicateRole(Roles role, Integer option) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Boolean existing = false;
		Query query = null;

		try {
			transaction = session.beginTransaction();
			if (option == 1) {										/*Check Duplicate given rolecode*/
				query = session.createQuery("SELECT id FROM Roles WHERE roleCode = :rolecode");		
				query.setParameter("rolecode", role.getRoleCode());
			} else if (option == 2) {								/*Check Duplicate given rolecode and rolename*/
				query = session.createQuery("SELECT id FROM Roles WHERE roleCode = :rolecode AND roleName = :rolename");
				query.setParameter("rolecode", role.getRoleCode());
				query.setParameter("rolename", role.getRoleName());
			} else if (option == 3) {								/*Check Duplicate assigned to employee given roleid*/
				query = session.createQuery("SELECT a.id from Employee a join a.role as b WHERE b.id = :paramId");			
				query.setParameter("paramId", role.getId());
			} else if (option == 4) {								/*Check duplicate given roleId*/
				query = session.createQuery("SELECT id FROM Roles WHERE id = :roleid");		
				query.setParameter("roleid", role.getId());
			}

			existing = !(query.setCacheable(true).list().isEmpty());

		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error occurred");
			he.printStackTrace();
		} finally {
			session.close();
		}

		return existing;
	}
}