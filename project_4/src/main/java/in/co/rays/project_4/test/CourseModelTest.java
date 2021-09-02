package in.co.rays.project_4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.CourseModel;

/**
 * The Class CourseModelTest.
 */
public class CourseModelTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void main(String[] args) throws DuplicateRecordsException, ApplicationException {
		//addTest();
		//testDelete();
		//testFindByCourseName();
	//	testFindByPk();
		//testUpdate();
		//testsearch();
		//testList();
	}
	
	
	
	/**
	 * Adds the test.
	 *
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void addTest() throws DuplicateRecordsException, ApplicationException{
		CourseBean bean= new CourseBean();
		bean.setCourseName("PGDCA");
		bean.setDescription("degre");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);
		
		CourseModel model= new CourseModel();
		try {
			model.add(bean);
		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test delete.
	 */
	public static void testDelete(){
		CourseBean bean= new CourseBean();
		long id=6L;
		bean.setId(id);
		CourseModel model=new CourseModel();
		model.delete(bean);
	}
	
	/**
	 * Test find by course name.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testFindByCourseName() throws ApplicationException{
		CourseBean bean= new CourseBean();
		
		CourseModel model= new CourseModel();
		bean=model.findByCourseName("BE");
		System.out.print(bean.getId()+"\t");
		System.out.print(bean.getCourseName()+"\t");
		System.out.print(bean.getDescription()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime()+"\t");
		
	}
	
	/**
	 * Test find by pk.
	 */
	public static void testFindByPk(){
		CourseBean bean= new CourseBean();
		CourseModel model= new CourseModel();
		bean= model.findByPk(2L);
		System.out.print(bean.getId()+"\t");
		System.out.print(bean.getCourseName()+"\t");
		System.out.print(bean.getDescription()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime()+"\t");
		
		
	}
	
	/**
	 * Test update.
	 */
	public static void testUpdate(){
		CourseBean bean= new CourseBean();
		bean.setId(1L);
		bean.setCourseName("MTech");
		bean.setDescription("master Degree");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);
		CourseModel model= new CourseModel();
		model.update(bean);
	}
	
	/**
	 * Testsearch.
	 */
	public static void testsearch(){
		CourseBean bean=new CourseBean();
		CourseModel model= new CourseModel();
		bean.setCourseName("mtech");
		List list= new ArrayList();
		     list=model.search(bean, 0, 0);
		     Iterator it=list.iterator();
		     while (it.hasNext()) {
		    	 bean = (CourseBean) it.next();
		    	 System.out.print(bean.getId()+"\t");
		 		System.out.print(bean.getCourseName()+"\t");
		 		System.out.print(bean.getDescription()+"\t");
		 		System.out.print(bean.getCreatedBy()+"\t");
		 		System.out.print(bean.getModifiedBy()+"\t");
		 		System.out.print(bean.getCreatedDatetime()+"\t");
		 		System.out.println(bean.getModifiedDatetime()+"\t");
				
			}
	}
	
	/**
	 * Test list.
	 */
	public static void testList(){
		CourseBean bean=new CourseBean();
		CourseModel model= new CourseModel();
		List list= new ArrayList();
		     list=model.list(0, 0);
		     Iterator it=list.iterator();
		     while (it.hasNext()) {
		    	 bean = (CourseBean) it.next();
		    	 System.out.print(bean.getId()+"\t");
		 		System.out.print(bean.getCourseName()+"\t");
		 		System.out.print(bean.getDescription()+"\t");
		 		System.out.print(bean.getCreatedBy()+"\t");
		 		System.out.print(bean.getModifiedBy()+"\t");
		 		System.out.print(bean.getCreatedDatetime()+"\t");
		 		System.out.println(bean.getModifiedDatetime()+"\t");
				
			}
	}


}

