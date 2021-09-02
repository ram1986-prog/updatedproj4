package in.co.rays.project_4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.RoleBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.RoleModel;

/**
 * The Class RoleModelTest.
 */
public class RoleModelTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void main(String[] args) throws DuplicateRecordsException, ApplicationException {
		 //testAdd();
		//testDelete();
		// testUpdate();
		 //testFindByPk();
		//testList();
		//testFindByName();
		//testSearch();
		//testSearch1();
	}

	/**
	 * Test add.
	 *
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public static void testAdd() throws DuplicateRecordsException, ApplicationException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date date = sdf.parse("06/08/1996");
			RoleBean bean = new RoleBean();
			bean.setName("ram");
			bean.setDescription("sharma");
			bean.setDescription("student");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(null);
			bean.setModifiedDatetime(null);

			RoleModel model = new RoleModel();
			try {
				model.add(bean);
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Test delete.
	 */
	public static void testDelete() {
		RoleBean bean = new RoleBean();
		long id = 5L;
		bean.setId(id);

		RoleModel model = new RoleModel();
		model.delete(bean);
	}

	/**
	 * Test update.
	 */
	public static void testUpdate() {
		RoleBean bean = new RoleBean();
		long id = 1L;
		bean.setId(id);
		bean.setName("rohan");

		RoleModel model = new RoleModel();
		model.update(bean);

	}

	/**
	 * Test find by pk.
	 */
	public static void testFindByPk() {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		long pk = 1L;

		bean = model.findByPK(pk);
		System.out.print(bean.getId()+"\t");
		System.out.print(bean.getName()+"\t");
		System.out.print(bean.getDescription()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime());
	}

	/**
	 * Test list.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testList() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		List list = new ArrayList();
		list = model.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (RoleBean) it.next();
			System.out.print(bean.getId()+"\t");
			System.out.print(bean.getName()+"\t");
			System.out.print(bean.getDescription()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getModifiedBy()+"\t");
			System.out.print(bean.getCreatedDatetime()+"\t");
			System.out.println(bean.getModifiedDatetime());

		}
	}
 
 /**
  * Test find by name.
  */
 public static void testFindByName(){
	 RoleBean bean= new RoleBean();
	 RoleModel model= new RoleModel();
	 bean=model.findByName("rohan");
	 
	 System.out.print(bean.getId()+"\t");
		System.out.print(bean.getName()+"\t");
		System.out.print(bean.getDescription()+"\t");
		System.out.print(bean.getCreatedBy()+"\t");
		System.out.print(bean.getModifiedBy()+"\t");
		System.out.print(bean.getCreatedDatetime()+"\t");
		System.out.println(bean.getModifiedDatetime());
 }

/**
 * Test search.
 *
 * @throws ApplicationException the application exception
 */
public static void testSearch() throws ApplicationException{
	
	  
          RoleBean bean = new RoleBean();
          RoleModel model=new RoleModel();
          List list = new ArrayList();
          bean.setName("ram");
          list = model.search(bean);
         
          Iterator it = list.iterator();
          while (it.hasNext()) {
              bean = (RoleBean) it.next();
              System.out.print(bean.getId()+"\t");
      		System.out.print(bean.getName()+"\t");
      		System.out.print(bean.getDescription()+"\t");
      		System.out.print(bean.getCreatedBy()+"\t");
      		System.out.print(bean.getModifiedBy()+"\t");
      		System.out.print(bean.getCreatedDatetime()+"\t");
      		System.out.println(bean.getModifiedDatetime());
          }

} 

/**
 * Test search 1.
 */
public static void testSearch1() {

    try {
        RoleBean bean = new RoleBean();
        RoleModel model= new RoleModel();
        List list = new ArrayList();
        bean.setName("admin");
        list = model.search(bean, 0, 0);
        if (list.size() < 0) {
            System.out.println("Test Serach fail");
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bean = (RoleBean) it.next();
            System.out.print(bean.getId()+"\t");
      		System.out.print(bean.getName()+"\t");
      		System.out.print(bean.getDescription()+"\t");
      		System.out.print(bean.getCreatedBy()+"\t");
      		System.out.print(bean.getModifiedBy()+"\t");
      		System.out.print(bean.getCreatedDatetime()+"\t");
      		System.out.println(bean.getModifiedDatetime());
        }

    } catch (ApplicationException e) {
        e.printStackTrace();
    }

}

}
