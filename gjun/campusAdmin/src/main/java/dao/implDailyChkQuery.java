package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.entity.StudentInfo;

public interface implDailyChkQuery {
		
	public static EntityManagerFactory getEMF() {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("eduAdmin_jpa");
		} catch (Throwable ex){
			Logger.getLogger(implDailyChkQuery.class.getName()).log(Level.SEVERE, null, ex);
		}
		return emf;
	}
	
	public static void emfClose() {
		getEMF().close();
	}
	
	public List<StudentInfo> getStudentInfo(String id);
	public int updateEmail(String mail, StudentInfo si);
	
	

}


