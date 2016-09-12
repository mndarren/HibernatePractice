package com.pPoint.dto;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.pPoint.dto.UserProfile;


public class MainApp {

	public static void main(String[] args) {
		UserProfile user = new UserProfile();
		user.setFirstName("Merry");
		user.setLastName("Xie");
		user.setJoinedDate(new Date());
		user.setEmail("mnmonicaxie@gmail.com");
		user.setDescription("falksdfaoeiupoiejf");
		
		Address addr = new Address("1810 Hayes St.","Minneapolis","MN","55418");
		Address addr2 = new Address("1810 Hayes St #16.","St. Paul","MN","55418");
		user.getListOfAddresses().add(addr);
		user.getListOfAddresses().add(addr2);
		
		Vehicle vehicle = new Vehicle("Car");
		Vehicle vehicle1 = new Vehicle("Jeep");
		user.getVehicles().add(vehicle);
		user.getVehicles().add(vehicle1);
		vehicle.setUser(user);
		vehicle1.setUser(user);
		
		Session session = HibernateUtil.INSTANCE.session;

		session.beginTransaction();
		session.save(user);
		session.save(vehicle);
		session.save(vehicle1);
		
		session.getTransaction().commit();
		
		user = null;
		user = (UserProfile)session.get(UserProfile.class, 1);
//		session.close(); //proxy object and lazy initialization lead to Error, but not happened in this program.
		System.out.println(user);
		session.close();
		
	}

}
