package in.co.rays.project_4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.StudentBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.StudentModel;

/**
 * The Class StudentModelTest.
 */
public class StudentModelTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ParseException the parse exception
	 */
	public static void main(String[] args) throws ApplicationException, DuplicateRecordsException, ParseException {
		//testAdd();
		//testDelete();
		//testFindByEmail();
		//testFindByPk();
	//testUpdate();
		//testList();
		testsearch();
		//testList1();
	}
	
	/**
	 * Test add.
	 *
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void testAdd() throws DuplicateRecordsException, ApplicationException{
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		   
		
		   try {
			   java.util.Date  date = sdf.parse("02/04/2013");
			   StudentBean bean= new StudentBean();
				bean.setCollegeId(9999);
				bean.setCollegeName("IINR");
				bean.setFirstName("sham");
				bean.setLastName("nema");
				bean.setDob(date);
				bean.setMobileNo("97959465478");
				bean.setEmail("aaaa@gmail.com");
				bean.setCollegeId(1l);
				bean.setCreatedBy("admin");
				bean.setModifiedBy("admin");
				bean.setCreatedDatetime(null);
				bean.setModifiedDatetime(null);
				
				StudentModel model= new StudentModel();
				try {
					model.add(bean);
				} catch (DuplicateRecordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Test delete.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testDelete() throws ApplicationException{
		StudentBean bean= new StudentBean();
		long id=4L;
		bean.setId(id);
		
		StudentModel model= new StudentModel();
		model.delete(bean);
	}
	
	/**
	 * Test find by email.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testFindByEmail() throws ApplicationException{
		StudentBean bean= new StudentBean();
		
		StudentModel model= new StudentModel();
		 bean = model.findByEmailId("pari@gmail.com");
		
		
		 System.out.print(bean.getId()+"\t");
         System.out.print(bean.getFirstName()+"\t");
         System.out.print(bean.getLastName()+"\t");
         System.out.print(bean.getDob()+"\t");
         System.out.print(bean.getMobileNo()+"\t");
         System.out.print(bean.getEmail()+"\t");
         System.out.print(bean.getCollegeId()+"\t");
         System.out.print(bean.getCreatedBy()+"\t");
		
	}

/**
 * Test find by pk.
 *
 * @throws ApplicationException the application exception
 */
public static void testFindByPk() throws ApplicationException{
	StudentBean bean= new StudentBean();
	long pk=1L;
	StudentModel model= new StudentModel();
	 bean = model.findByPK(pk);
	  System.out.print(bean.getId()+"\t");
      System.out.print(bean.getFirstName()+"\t");
      System.out.print(bean.getLastName()+"\t");
      System.out.print(bean.getDob()+"\t");
      System.out.print(bean.getMobileNo()+"\t");
      System.out.print(bean.getEmail()+"\t");
      System.out.println(bean.getCollegeId()+"\t");
}

/**
 * Test update.
 *
 * @throws DuplicateRecordsException the duplicate records exception
 * @throws ApplicationException the application exception
 * @throws ParseException the parse exception
 */
public static void testUpdate() throws DuplicateRecordsException, ApplicationException, ParseException{
	StudentBean bean=new StudentBean();
	//Date d=new Date();
	//Timestamp ts=new Timestamp(d.getTime());
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	//java.util.Date date = sdf.parse("06/08/1997");
	
	//long id=2L;
	bean.setId(10L);
	bean.setCollegeId(27);
	bean.setCollegeName("CEC");
	bean.setFirstName("priya");
	bean.setLastName("yadav");
	bean.setDob(sdf.parse("22/10/2000"));
	bean.setMobileNo("8989656898");
	bean.setEmail("priya@gmail.com");
	bean.setCreatedBy("admin");
	bean.setModifiedBy("admin");
	bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	
	
	StudentModel model= new StudentModel();
	try {
		model.update(bean);
	} catch (DuplicateRecordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * Test list.
 *
 * @throws ApplicationException the application exception
 */
public static void testList() throws ApplicationException{
	 StudentBean bean = new StudentBean();
     List list = new ArrayList();
      StudentModel model=new StudentModel();
      list= model.list(1, 10);
      Iterator it=list.iterator();
      while (it.hasNext()) {
          bean = (StudentBean) it.next();
          System.out.print(bean.getId()+"\t");
          System.out.print(bean.getFirstName()+"\t");
          System.out.print(bean.getLastName()+"\t");
          System.out.print(bean.getDob()+"\t");
          System.out.print(bean.getMobileNo()+"\t");
          System.out.print(bean.getEmail()+"\t");
          System.out.print(bean.getCollegeId()+"\t");
          System.out.print(bean.getCreatedBy()+"\t");
          System.out.print(bean.getModifiedBy()+"\t");
          System.out.print(bean.getCreatedDatetime()+"\t");
          
          System.out.println(bean.getModifiedDatetime()+"\t");
      }
      
}

/**
 * Testsearch.
 *
 * @throws ApplicationException the application exception
 * @throws ParseException 
 */
public static void testsearch() throws ApplicationException, ParseException{
	
        StudentBean bean = new StudentBean();
        StudentModel model= new StudentModel();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        Date date=sdf.parse("2000/01/22");
        List list = new ArrayList();
    //    bean.setFirstName("som");
       bean.setDob(date);
        list = model.search(bean, 0, 0);
        
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bean = (StudentBean) it.next();
            System.out.print(bean.getId()+"\t");
            System.out.print(bean.getFirstName()+"\t");
            System.out.print(bean.getLastName()+"\t");
            System.out.print(bean.getDob()+"\t");
            System.out.print(bean.getMobileNo()+"\t");
            System.out.print(bean.getEmail()+"\t");
            System.out.print(bean.getCollegeId()+"\t");
            System.out.print(bean.getCreatedBy()+"\t");
            System.out.print(bean.getModifiedBy()+"\t");
            System.out.print(bean.getCreatedDatetime()+"\t");
            
            System.out.println(bean.getModifiedDatetime()+"\t");
        }

   
}

/**
 * Test list 1.
 *
 * @throws ApplicationException the application exception
 */
public static void testList1() throws ApplicationException{
	 StudentBean bean = new StudentBean();
    List list = new ArrayList();
     StudentModel model=new StudentModel();
     list= model.list();
     Iterator it=list.iterator();
     while (it.hasNext()) {
         bean = (StudentBean) it.next();
         System.out.print(bean.getId()+"\t");
         System.out.print(bean.getFirstName()+"\t");
         System.out.print(bean.getLastName()+"\t");
         System.out.print(bean.getDob()+"\t");
         System.out.print(bean.getMobileNo()+"\t");
         System.out.print(bean.getEmail()+"\t");
         System.out.print(bean.getCollegeId()+"\t");
         System.out.print(bean.getCreatedBy()+"\t");
         System.out.print(bean.getModifiedBy()+"\t");
         System.out.print(bean.getCreatedDatetime()+"\t");
         
         System.out.println(bean.getModifiedDatetime()+"\t");
     }
     
}




}
