package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import controller.util.allutil;
import dao.entity.StudentInfo;

public class eduJPA implements implJPA{

	//測試用，上線前移除main(String[] args)
	public static void main(String[] args)
	{
		eduJPA jpatest = new eduJPA() ;
		
		//System.out.println("SSSSSSSSSSSSSSSS");
		StudentInfo std = new StudentInfo();
		std.setStudentId("B123456789");
		std.setName("Mary");
		std.setCounty("大安區");
		std.setCity("台北市");
		std.setZip("23154");
		std.setRoad("賓夕法尼亞大道1600號");
		std.setParentMail("freddyruan@hotmail.com");
		std.setPwd_seed(allutil.get10random());
		std.setPwd_hash(allutil.getPwdHash(std.getPwd_seed(), "1234"));
		
		int regrtn = jpatest.saveUser(std);
		System.out.println("The regrtn = " + regrtn) ;
		
	}
	
	@Override
	public int saveUser(StudentInfo std) {
		int rtnInt = 0 ;
		try
		{
			EntityManager entityManager = implJPA.getEntityManagerFactory().createEntityManager();
		    EntityTransaction etx = entityManager.getTransaction();
		    
		    //begin transaction----------------------------------------------
		    etx.begin();
		    entityManager.persist(std);
		    etx.commit();
		    entityManager.close();  
		    //end transaction------------------------------------------------
		    
		    //release resources
		    implJPA.shutdown();
		}
		catch(Exception e)
		{
			rtnInt = -1 ;
		}
		finally
		{
			return rtnInt ;
		}
	}

	@Override
	public List<StudentInfo> getstudentbyId(String id) {
		List<StudentInfo> stdList = null;
		try
		{
			
			String jpql = "SELECT s FROM StudentInfo s WHERE s.studentId =:arg1";
			EntityManager entityManager = implJPA.getEntityManagerFactory().createEntityManager();
		    EntityTransaction etx = entityManager.getTransaction();
		    
		    //begin transaction----------------------------------------------
		    etx.begin();
		    Query query = entityManager.createQuery(jpql, StudentInfo.class);
		    query.setParameter("arg1", id);
		    stdList = (List<StudentInfo>)query.getResultList();
		    etx.commit();
		    entityManager.close();  
		    //end transaction------------------------------------------------
		    
		    //release resources
		    implJPA.shutdown();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			stdList = null;
		}
		finally
		{
			return stdList ;
		}
	}

}
