package in.co.rays.project_4.test;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.MarksheetModel;

/**
 * The Class MarksheetModelTest.
 */
public class MarksheetModelTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordsException the duplicate records exception
	 */
	public static void main(String[] args) throws ApplicationException, DuplicateRecordsException {
		//testAdd();
		//testDelete();
		testFindByRollNo();
		//testUpdate();
		//testFindByPK();
		//testList();
		//testMeritList();
		testSearch();
	}
	
	/**
	 * Test add.
	 *
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordsException the duplicate records exception
	 */
	public static void testAdd() throws ApplicationException, DuplicateRecordsException{
		MarksheetBean bean= new MarksheetBean();
		bean.setRollNo("0818EC131001");
		bean.setStudentId(9);
		bean.setName("raima");
		bean.setPhysics(85);
		bean.setChemistry(74);
		bean.setMaths(95);
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);
		
		MarksheetModel model=new MarksheetModel();
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
		MarksheetBean bean=new MarksheetBean();
		bean.setId(1L);
		
		MarksheetModel model=new MarksheetModel();
		model.delete(bean);
	}
	
	/**
	 * Test find by roll no.
	 */
	public static void testFindByRollNo(){
		MarksheetBean bean= new MarksheetBean();
		MarksheetModel model=new MarksheetModel();
		 bean= model.findByRollNo("mr1210");
		 System.out.println(bean.getId());
         System.out.println(bean.getRollNo());
         System.out.println(bean.getName());
         System.out.println(bean.getPhysics());
         System.out.println(bean.getChemistry());
         System.out.println(bean.getMaths());

		
	}
	
	/**
	 * Test update.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testUpdate() throws ApplicationException{
		MarksheetBean bean= new MarksheetBean();
		MarksheetModel model= new MarksheetModel();
		  // bean= model.findByPK(2L);
				bean.setId(1L);
				bean.setRollNo("it1009");
				bean.setStudentId(1L);
				bean.setName("pari sharma");
				bean.setPhysics(85);
				bean.setChemistry(74);
				bean.setMaths(95);
				bean.setCreatedBy("admin");
				bean.setModifiedBy("admin");
				bean.setCreatedDatetime(null);
				bean.setModifiedDatetime(null);

		
		
		model.update(bean);
	}

/**
 * Test find by PK.
 */
public static void testFindByPK(){
	MarksheetBean bean=new MarksheetBean();
	MarksheetModel model= new MarksheetModel();
	  bean= model.findByPK(2L);
	  System.out.println(bean.getId());
      System.out.println(bean.getRollNo());
      System.out.println(bean.getName());
      System.out.println(bean.getPhysics());
      System.out.println(bean.getChemistry());
      System.out.println(bean.getMaths());
}

/**
 * Test list.
 *
 * @throws ApplicationException the application exception
 */
public static void testList() throws ApplicationException {
	MarksheetBean bean= new MarksheetBean();
	MarksheetModel model= new MarksheetModel();
   List list= new ArrayList();
      list= model.list();
      
      Iterator it=list.iterator();
      while (it.hasNext()){
    	  bean=(MarksheetBean) it.next();
    	  System.out.print(bean.getId()+"\t");
          System.out.print(bean.getRollNo()+"\t");
          System.out.print(bean.getName()+"\t");
          System.out.print(bean.getPhysics()+"\t");
          System.out.print(bean.getChemistry()+"\t");
          System.out.print(bean.getMaths()+"\t");
          System.out.print(bean.getCreatedBy()+"\t");
          System.out.print(bean.getModifiedBy()+"\t");
          System.out.print(bean.getCreatedDatetime()+"\t");
         
          System.out.println(bean.getModifiedDatetime()+"\t");
      }
}

/**
 * Test merit list.
 *
 * @throws ApplicationException the application exception
 */
public static void testMeritList() throws ApplicationException{
	MarksheetBean bean= new MarksheetBean();
	MarksheetModel model=new MarksheetModel();
	List list=new ArrayList();
     list=model.getMeritList(1, 5);
     if (list.size() < 0) {
         System.out.println("Test List fail");
     }
     
     Iterator it=list.iterator();
     while (it.hasNext()){
   	  bean=(MarksheetBean) it.next();
   	  System.out.print(bean.getId()+"\t");
         System.out.print(bean.getRollNo()+"\t");
         System.out.print(bean.getName()+"\t");
         System.out.print(bean.getPhysics()+"\t");
         System.out.print(bean.getChemistry()+"\t");
         System.out.print(bean.getMaths()+"\t");
        System.out.print(bean.getCreatedBy()+"\t");
         System.out.print(bean.getModifiedBy()+"\t");
         System.out.print(bean.getCreatedDatetime()+"\t");
        
         System.out.println(bean.getModifiedDatetime()+"\t");
     }
}

/**
 * Test search.
 *
 * @throws ApplicationException the application exception
 */
public static void testSearch() throws ApplicationException {
   
        MarksheetBean bean = new MarksheetBean();
        MarksheetModel model=new MarksheetModel();
        List list = new ArrayList();
       // bean.setName("sham");
        bean.setRollNo("mr1210");
        list = model.search(bean, 1, 10);
        if (list.size() < 0) {
            System.out.println("Test Search fail");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bean = (MarksheetBean) it.next();
            System.out.print(bean.getId()+"\t");
            System.out.print(bean.getRollNo()+"\t");
            System.out.print(bean.getName()+"\t");
            System.out.print(bean.getPhysics()+"\t");
            System.out.print(bean.getChemistry()+"\t");
            System.out.println(bean.getMaths()+"\t");
        }
    
}








}
