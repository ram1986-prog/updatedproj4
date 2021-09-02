package in.co.rays.project_4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.CollegeBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.CollegeModel;

/**
 * The Class CollegeModelTest.
 */
public class CollegeModelTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void main(String[] args) throws DuplicateRecordsException, ApplicationException {
		//testAdd();
		testDelete();
		//testUpdate();
		//testFindByName();
		//testFindByPk();
		//testList1();
		//testSearch();
		//testList();
		//testSearch1();
	}
	
	/**
	 * Test add.
	 *
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void testAdd() throws DuplicateRecordsException, ApplicationException{
		CollegeBean bean=new CollegeBean();
		bean.setName("SVCE");
		bean.setAddress("vijay nager");
		bean.setState("mp");
		bean.setCity("indore");
		bean.setPhoneNo("998998989");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
        bean.setModifiedDatetime(null);
        
        CollegeModel model= new CollegeModel();
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
		CollegeBean bean= new CollegeBean();
		long pk=5L;
		bean.setId(pk);
		CollegeModel model= new CollegeModel();
		model.delete(bean);
		
	}
	
	/**
	 * Test update.
	 */
	public static void testUpdate(){
		CollegeBean bean= new CollegeBean();
		long pk=1L;
		bean.setName("cc");
		
		bean.setAddress("ganesh vihar");
		bean.setState("mp");
		bean.setCity("indore");
		bean.setPhoneNo("898998989");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
        bean.setModifiedDatetime(null);
		bean.setId(pk);
		
		CollegeModel model= new CollegeModel();
		model.update(bean);
	}
	
	/**
	 * Test find by name.
	 */
	public static void testFindByName(){
		CollegeBean bean= new CollegeBean();
		String name="iist";
	
		CollegeModel model= new CollegeModel();
		bean=model.findByName(name);
		System.out.print(bean.getId()+"\t");
        System.out.print(bean.getName()+"\t");
        System.out.print(bean.getAddress()+"\t");
        System.out.print(bean.getState()+"\t");
        System.out.print(bean.getCity()+"\t");
        System.out.print(bean.getPhoneNo()+"\t");
        System.out.print(bean.getCreatedBy()+"\t");
        System.out.print(bean.getCreatedDatetime()+"\t");
        System.out.print(bean.getModifiedBy()+"\t");
        System.out.println(bean.getModifiedDatetime()+"\t");
		
		
		
	}
	
	/**
	 * Test find by pk.
	 */
	public static void testFindByPk(){
		CollegeBean bean= new CollegeBean();
		long pk=1L;
		CollegeModel model= new CollegeModel();
		bean=model.findByPK(pk);
		System.out.print(bean.getId()+"\t");
        System.out.print(bean.getName()+"\t");
        System.out.print(bean.getAddress()+"\t");
        System.out.print(bean.getState()+"\t");
        System.out.print(bean.getCity()+"\t");
        System.out.print(bean.getPhoneNo()+"\t");
        System.out.print(bean.getCreatedBy()+"\t");
        System.out.print(bean.getCreatedDatetime()+"\t");
        System.out.print(bean.getModifiedBy()+"\t");
        System.out.println(bean.getModifiedDatetime()+"\t");

	}
	
	/**
	 * Test list 1.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testList1() throws ApplicationException{
		CollegeBean bean=new CollegeBean();
		List list=new ArrayList();
		CollegeModel model= new CollegeModel();
		 list = model.list();
		Iterator it=list.iterator();
		while(it.hasNext()){
			 bean = (CollegeBean) it.next();
			 System.out.print(bean.getId()+"\t");
		        System.out.print(bean.getName()+"\t");
		        System.out.print(bean.getAddress()+"\t");
		        System.out.print(bean.getState()+"\t");
		        System.out.print(bean.getCity()+"\t");
		        System.out.print(bean.getPhoneNo()+"\t");
		        System.out.print(bean.getCreatedBy()+"\t");
		        System.out.print(bean.getCreatedDatetime()+"\t");
		        System.out.print(bean.getModifiedBy()+"\t");
		        System.out.println(bean.getModifiedDatetime()+"\t");
		}
	}	
	
	/**
	 * Test search 1.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testSearch1() throws ApplicationException{
		  
	            CollegeBean bean = new CollegeBean();
	            CollegeModel model= new CollegeModel();
	            List list = new ArrayList();
	            bean.setName("cc");
	            // bean.setAddress("ganesh vihar");
	            list = model.search(bean);
	            if (list.size() < 0) {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.print(bean.getId()+"\t");
	                System.out.print(bean.getName()+"\t");
	                System.out.print(bean.getAddress()+"\t");
	                System.out.print(bean.getState()+"\t");
	                System.out.print(bean.getCity()+"\t");
	                System.out.print(bean.getPhoneNo()+"\t");
	                System.out.print(bean.getCreatedBy()+"\t");
	                System.out.print(bean.getCreatedDatetime()+"\t");
	                System.out.print(bean.getModifiedBy()+"\t");
	                System.out.println(bean.getModifiedDatetime()+"\t");
	            }
	        
	 
				
	}
	 
 	/**
 	 * Test list.
 	 */
 	public static void testList() {

	        try {
	            CollegeBean bean = new CollegeBean();
	            CollegeModel model= new CollegeModel();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.print(bean.getId()+"\t");
	                System.out.print(bean.getName()+"\t");
	                System.out.print(bean.getAddress()+"\t");
	                System.out.print(bean.getState()+"\t");
	                System.out.print(bean.getCity()+"\t");
	                System.out.print(bean.getPhoneNo()+"\t");
	                System.out.print(bean.getCreatedBy()+"\t");
	                System.out.print(bean.getCreatedDatetime()+"\t");
	                System.out.print(bean.getModifiedBy()+"\t");
	                System.out.println(bean.getModifiedDatetime()+"\t");
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	 
 	/**
 	 * Test search.
 	 */
 	public static void testSearch() {
	        try {
	            CollegeBean bean = new CollegeBean();
	            CollegeModel model=new CollegeModel();
	            
	            List list = new ArrayList();
	            bean.setName("anu");
	            // bean.setAddress("borawan");
	            list = model.search(bean, 1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.print(bean.getId()+"\t");
	                System.out.print(bean.getName()+"\t");
	                System.out.print(bean.getAddress()+"\t");
	                System.out.print(bean.getState()+"\t");
	                System.out.print(bean.getCity()+"\t");
	                System.out.print(bean.getPhoneNo()+"\t");
	                System.out.print(bean.getCreatedBy()+"\t");
	                System.out.print(bean.getCreatedDatetime()+"\t");
	                System.out.print(bean.getModifiedBy()+"\t");
	                System.out.println(bean.getModifiedDatetime()+"\t");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	
}
