package in.co.rays.project_4.test;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.exception.RecordNotFoundException;
import in.co.rays.project_4.exception.RecordNotFountException;
import in.co.rays.project_4.model.UserModel;

/**
 * The Class UserTest.
 */
public class UserTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		// testAdd();
		//testUpdate();
		// testDelete();
		 //testList();
		// testFindByPk();
		 testFindByLogin();
		 //testSearch();
		//testAuthenticate();
		//testGetRoles();
		//testSearch1();
		//testRegisterUser();
	     //testchangePassword();
	}

	/**
	 * Test add.
	 *
	 * @throws Exception the exception
	 */
	private static void testAdd() throws Exception {

		try {
			UserBean bean = new UserBean();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date date = sdf.parse("06/08/2012");
			bean.setFirstName("anayasha");
			bean.setLastName("agrawal");
			bean.setLogin("anayashar@gmail.com");
			bean.setPassword("Anayasha@123");

			bean.setDob(date);
			bean.setMobileNo("9896540123");
			bean.setRoleId(2);
			bean.setUnSuccessfulLogin(1);
			bean.setGender("female");
			bean.setLastLogin(new Timestamp(new Date().getTime()));
			bean.setLock("yes");
			bean.setRegisteredIP("1");
			bean.setLastLoginIP("1");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(null);
			bean.setModifiedDatetime(null);
			UserModel model = new UserModel();
			model.add(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test delete.
	 */
	public static void testDelete() {
		UserBean bean = new UserBean();
		long pk = 3L;
		bean.setId(pk);

		UserModel model = new UserModel();
		model.delete(bean);

	}

	/**
	 * Test update.
	 *
	 * @throws DuplicateRecordsException the duplicate records exception
	 */
	public static void testUpdate() throws DuplicateRecordsException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date;
		try {
			date = sdf.parse("06/08/1996");
			UserBean bean = new UserBean();

			long pk = 2L;
			bean.setFirstName("vansh");
			bean.setLastName("kumar");
			bean.setLogin("vanst12@.com");
			bean.setPassword("vansh1234");
			bean.setDob(date);
			bean.setMobileNo("5686656376");
			bean.setRoleId(3L);
			bean.setUnSuccessfulLogin(0);
			bean.setGender("male");
			bean.setLastLogin(null);
			bean.setLock(null);
			bean.setRegisteredIP("303");
			bean.setLastLoginIP(null);
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(null);
			bean.setModifiedDatetime(null);
			bean.setId(pk);

			UserModel model = new UserModel();
			model.update(bean);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * Test list.
	 *
	 * @throws SQLException the SQL exception
	 * @throws RecordNotFountException the record not fount exception
	 */
	public static void testList() throws SQLException, RecordNotFountException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		List list = model.list();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getLogin() + "\t");
			System.out.print(bean.getPassword() + "\t");
			System.out.print(bean.getDob() + "\t");
			System.out.print(bean.getRoleId() + "\t");
			System.out.print(bean.getUnSuccessfulLogin() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getLastLogin() + "\t");
			System.out.print(bean.getLock() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime() + "\t");
		}

	}

	/**
	 * Test find by pk.
	 */
	public static void testFindByPk() {
		UserBean bean = new UserBean();
		long pk = 1L;
		bean.setId(pk);
		UserModel model = new UserModel();
		bean = model.findByPK(pk);
		System.out.print(bean.getId() + "\t");
		System.out.print(bean.getFirstName() + "\t");
		System.out.print(bean.getLastName() + "\t");
		System.out.print(bean.getLogin() + "\t");
		System.out.print(bean.getPassword() + "\t");
		System.out.print(bean.getDob() + "\t");
		System.out.print(bean.getRoleId() + "\t");
		System.out.print(bean.getUnSuccessfulLogin() + "\t");
		System.out.print(bean.getGender() + "\t");
		System.out.print(bean.getLastLogin() + "\t");
		System.out.print(bean.getLock() + "\t");
		System.out.print(bean.getMobileNo() + "\t");
		System.out.print(bean.getCreatedBy() + "\t");
		System.out.print(bean.getModifiedBy() + "\t");
		System.out.print(bean.getCreatedDatetime() + "\t");
		System.out.println(bean.getModifiedDatetime() + "\t");
	}

	/**
	 * Test find by login.
	 */
	public static void testFindByLogin() {
		UserModel model = new UserModel();
		UserBean bean = model.findByLogin("ramvpandey10@gmail.com");
		System.out.print(bean.getId() + "\t");
		System.out.print(bean.getFirstName() + "\t");
		System.out.print(bean.getLastName() + "\t");
		System.out.print(bean.getLogin() + "\t");
		System.out.print(bean.getPassword() + "\t");
		System.out.print(bean.getDob() + "\t");
		System.out.print(bean.getMobileNo() + "\t");
		System.out.print(bean.getRoleId() + "\t");
		System.out.print(bean.getUnSuccessfulLogin() + "\t");
		System.out.print(bean.getGender() + "\t");
		System.out.print(bean.getLastLogin() + "\t");
		System.out.print(bean.getLock() + "\t");
		System.out.print(bean.getLastLoginIP()+"\t");
		System.out.print(bean.getRegisteredIP()+"\t");
		System.out.print(bean.getCreatedBy() + "\t");
		System.out.print(bean.getModifiedBy() + "\t");
		System.out.print(bean.getCreatedDatetime() + "\t");
		System.out.println(bean.getModifiedDatetime() + "\t");
	}

	/**
	 * Test search.
	 */
	public static void testSearch() {

		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		List list = new ArrayList();
		bean.setFirstName("pooja");
		list = model.search(bean);

		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.print(bean.getId() + "\t");
			System.out.print(bean.getFirstName() + "\t");
			System.out.print(bean.getLastName() + "\t");
			System.out.print(bean.getLogin() + "\t");
			System.out.print(bean.getPassword() + "\t");
			System.out.print(bean.getDob() + "\t");
			System.out.print(bean.getRoleId() + "\t");
			System.out.print(bean.getUnSuccessfulLogin() + "\t");
			System.out.print(bean.getGender() + "\t");
			System.out.print(bean.getLastLogin() + "\t");
			System.out.print(bean.getLock() + "\t");
			System.out.print(bean.getMobileNo() + "\t");
			System.out.print(bean.getCreatedBy() + "\t");
			System.out.print(bean.getModifiedBy() + "\t");
			System.out.print(bean.getCreatedDatetime() + "\t");
			System.out.println(bean.getModifiedDatetime() + "\t");
		}

	}
	
	/**
	 * Test authenticate.
	 *
	 * @throws ApplicationException the application exception
	 */
	public static void testAuthenticate() throws ApplicationException{
		UserBean bean= new UserBean();
		bean.setLogin("ram@gmail.com");
		bean.setPassword("ram123");
		UserModel model= new UserModel();
		bean= model.authenticate(bean.getLogin(), bean.getPassword());
		  if (bean != null) {
              System.out.println("authenticate user");
              System.out.print(bean.getId() + "\t");
				System.out.print(bean.getFirstName() + "\t");
				System.out.print(bean.getLastName() + "\t");
				System.out.print(bean.getLogin() + "\t");
				System.out.print(bean.getPassword() + "\t");
				System.out.print(bean.getDob() + "\t");
				System.out.print(bean.getRoleId() + "\t");
				System.out.print(bean.getUnSuccessfulLogin() + "\t");
				System.out.print(bean.getGender() + "\t");
				System.out.print(bean.getLastLogin() + "\t");
				System.out.print(bean.getLock() + "\t");
				System.out.print(bean.getMobileNo() + "\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime() + "\t");
          } else {
              System.out.println("Invalied login Id & password");
          }
	}
	 
 	/**
 	 * Test get roles.
 	 */
 	public static void testGetRoles() {

	        
		 UserBean bean = new UserBean();
			UserModel model = new UserModel();
			List list = new ArrayList();
			bean.setRoleId(1L);
			list = model.search(bean);

			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId() + "\t");
				System.out.print(bean.getFirstName() + "\t");
				System.out.print(bean.getLastName() + "\t");
				System.out.print(bean.getLogin() + "\t");
				System.out.print(bean.getPassword() + "\t");
				System.out.print(bean.getDob() + "\t");
				System.out.print(bean.getRoleId() + "\t");
				System.out.print(bean.getUnSuccessfulLogin() + "\t");
				System.out.print(bean.getGender() + "\t");
				System.out.print(bean.getLastLogin() + "\t");
				System.out.print(bean.getLock() + "\t");
				System.out.print(bean.getMobileNo() + "\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime() + "\t");
			}
	 }
	 
	 /**
 	 * Test search 1.
 	 *
 	 * @throws ApplicationException the application exception
 	 */
 	public static void testSearch1() throws ApplicationException {

			UserBean bean = new UserBean();
			UserModel model = new UserModel();
			List list = new ArrayList();
			bean.setFirstName("pooja");
			list = model.search(bean, 0, 0);

			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.print(bean.getId() + "\t");
				System.out.print(bean.getFirstName() + "\t");
				System.out.print(bean.getLastName() + "\t");
				System.out.print(bean.getLogin() + "\t");
				System.out.print(bean.getPassword() + "\t");
				System.out.print(bean.getDob() + "\t");
				System.out.print(bean.getRoleId() + "\t");
				System.out.print(bean.getUnSuccessfulLogin() + "\t");
				System.out.print(bean.getGender() + "\t");
				System.out.print(bean.getLastLogin() + "\t");
				System.out.print(bean.getLock() + "\t");
				System.out.print(bean.getMobileNo() + "\t");
				System.out.print(bean.getCreatedBy() + "\t");
				System.out.print(bean.getModifiedBy() + "\t");
				System.out.print(bean.getCreatedDatetime() + "\t");
				System.out.println(bean.getModifiedDatetime() + "\t");
			}

		}
	  
  	/**
  	 * Test register user.
  	 *
  	 * @throws Exception the exception
  	 */
  	public static void testRegisterUser() throws Exception {
	        try {
	            UserBean bean = new UserBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
             
	            UserModel model=new UserModel();
	            // bean.setId(8L);
	            bean.setFirstName("veru");
	            bean.setLastName("rathi");
	            bean.setLogin("uday8027@gmail.com");
	            bean.setPassword("veer");
	            bean.setConfirmPassword("veer");
	            bean.setDob(sdf.parse("11/20/2015"));
	            bean.setGender("Male");
	            bean.setRoleId(2);
	            long pk = model.registerUser(bean);
	            System.out.println("Successfully register");
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getDob());
	            UserBean registerbean = model.findByPK(pk);
	            if (registerbean != null) {
	                System.out.println("Test registation fail");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	  
	  /**
  	 * Testchange password.
  	 */
  	public static void testchangePassword() throws RecordNotFountException {

	        try {
	        	UserModel model=new UserModel();
	            UserBean bean = model.findByLogin("ram@gmail.com");
	            String oldPassword = bean.getPassword();
	            bean.setId(1);
	            bean.setPassword("1234");
	            bean.setConfirmPassword("1234");
	            String newPassword = bean.getPassword();
	            try {
					model.changePassword(1L, oldPassword, newPassword);
				} catch (RecordNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("password has been change successfully");

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
  	
	}
