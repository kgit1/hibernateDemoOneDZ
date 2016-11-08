package com.luv2code.hibernate.demo.dz;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hebernate.demo.entity.Employee;

public class MainApp {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.addAnnotatedClass(Employee.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// create employees
			Employee employee1 = new Employee("Catte", "Wildburn", "Seagul");
			Employee employee2 = new Employee("Batter", "Buren", "Krista");
			Employee employee3 = new Employee("Javax", "Pebole", "S1");
			Employee employee4 = new Employee("Orlando", "Blum", "Holly");

			// save to database
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);

			// create tempEmployee and put inside employee from database
			// by id(primary key)
			Employee tempEmployee = session.get(Employee.class, 4);

			// print result
			System.out.println(tempEmployee);

			// create tempList of employees and put inside result of
			// query by company name
			List<Employee> tempList = session
					.createQuery("from Employee e where e.company='S1'")
					.getResultList();

			// print results
			System.out.println("ResultList");
			for (Employee employee : tempList) {
				System.out.println(employee);
			}

			// delete object in database by id
			session.createQuery("delete Employee where id=5").executeUpdate();

			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}

	}

}
