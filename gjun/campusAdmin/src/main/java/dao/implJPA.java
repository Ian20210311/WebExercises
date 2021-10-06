package dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.entity.StudentInfo;

public interface implJPA {
	static EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory entityManagerFactory = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("eduAdmin_jpa");
		} catch (Throwable ex) {
			Logger.getLogger(implJPA.class.getName()).log(Level.SEVERE, null, ex);
		}
		return entityManagerFactory;
	}

	public static void shutdown() {
		getEntityManagerFactory().close();
	}
	
	//add your jpa code 
	//insert
    int saveUser(StudentInfo std);
    
    List<StudentInfo> getstudentbyId(String id);
}
