package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import controller.util.allutil;
import dao.entity.StudentInfo;

public class DailyChkQuery implements implDailyChkQuery{
	

	@Override
	public List<StudentInfo> getStudentInfo(String id) {
		
		String jpql="SELECT s FROM StudentInfo s where s.studentId = :id";
		
		
		EntityManager em = implDailyChkQuery.getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		List<StudentInfo> list = (List<StudentInfo>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		implDailyChkQuery.emfClose();
		
		return list;
	}

	@Override
	public int updateEmail(String mail, StudentInfo si) {
		String jpql="update StudentInfo SET parentMail = :mail where studentId = :id";
		int r=0;
		//StudentInfo std = si;
		//std.setParentMail(mail);
		
		EntityManager em = implDailyChkQuery.getEMF().createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery(jpql);
		query.setParameter("mail", mail);
		query.setParameter("id", si.getStudentId());
		r=query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		implDailyChkQuery.emfClose();
		
		return r;
	}

}
