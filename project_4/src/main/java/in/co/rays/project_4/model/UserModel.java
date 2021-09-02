package in.co.rays.project_4.model;

import java.io.IOException;


import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DatabaseException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.RecordNotFoundException;
import in.co.rays.project_4.util.EmailBuilder;
import in.co.rays.project_4.util.EmailMessage;
import in.co.rays.project_4.util.EmailUtility;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class UserModel.
 */
public class UserModel {

	/** The log. */
	private static Logger log = Logger.getLogger(UserModel.class);

	/**
	 * Next PK.
	 *
	 * @return the integer
	 */
	public Integer nextPK() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pk = 0;

		try {
			
			conn = JDBCDataSource.getConnection();
			System.out.println("ok");
			pstmt = conn.prepareStatement("SELECT MAX(ID) FROM st_user");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk + 1;
	}

	/**
	 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public long add(UserBean bean) throws DuplicateRecordException, ApplicationException {

		Connection conn = null;
		int pk = 0;
		
		UserBean existbean = findByLogin(bean.getLogin());

        if (existbean != null) {
            throw new DuplicateRecordException("Login Id already exists");
        }

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO st_user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			System.out.println("UserModel add");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setInt(9, bean.getUnSuccessfulLogin());
			pstmt.setString(10, bean.getGender());
			pstmt.setTimestamp(11, bean.getLastLogin());
			pstmt.setString(12, bean.getLock());
			pstmt.setString(13, bean.getRegisteredIP());
			pstmt.setString(14, bean.getLastLoginIP());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("data added");
			pstmt.close();
		} catch (Exception e) {
			log.error("Database exception...", e);
			  /*try {
	                conn.rollback();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                throw new ApplicationException(
	                        "Exception : add rollback exception " + ex.getMessage());
	            }*/
			e.printStackTrace();
	            throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;

	}

	/**
	 * Delete.
	 *
	 * @param bean the bean
	 */
	public void delete(UserBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();

			System.out.println("user deleted");
			pstmt.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	public void update(UserBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set FIRST_NAME=? ,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? where ID=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setInt(8, bean.getUnSuccessfulLogin());
			pstmt.setString(9, bean.getGender());
			pstmt.setTimestamp(10, bean.getLastLogin());
			pstmt.setString(11, bean.getLock());
			pstmt.setString(12, bean.getRegisteredIP());
			pstmt.setString(13, bean.getLastLoginIP());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setLong(18, bean.getId());
			pstmt.executeUpdate();
			System.out.println("user Updated");
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * List.
	 *
	 * @return the list
	 */
	public List list() {
		ArrayList list = new ArrayList();
		Connection conn = null;

		try {
			try {
				conn = JDBCDataSource.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM st_user;");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);

			}
			System.out.println("list");
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}

	/**
	 * Find by PK.
	 *
	 * @param pk the pk
	 * @return the user bean
	 */
	public UserBean findByPK(long pk) {
		Connection conn = null;
		UserBean bean = null;
		// StringBuffer sql=new StringBuffer("select * from st_user where
		// ID=?");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where ID=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
			rs.close();
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return the user bean
	 */
	public UserBean findByLogin(String login) {
		Connection conn = null;
		UserBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM st_user WHERE LOGIN=?");

			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
			    bean=new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	/**
	 * Search.
	 *
	 * @param bean the bean
	 * @return the list
	 */
	public List search(UserBean bean) {

		StringBuffer sql = new StringBuffer("SELECT * from st_user WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + bean.getRoleId());
			}
			if (bean.getUnSuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFUL_LOGIN = " + bean.getUnSuccessfulLogin());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
			}
			if (bean.getLastLogin() != null && bean.getLastLogin().getTime() > 0) {
				sql.append(" AND LAST_LOGIN = " + bean.getLastLogin());
			}
			if (bean.getRegisteredIP() != null && bean.getRegisteredIP().length() > 0) {
				sql.append(" AND REGISTERED_IP like '" + bean.getRegisteredIP() + "%'");
			}
			if (bean.getLastLoginIP() != null && bean.getLastLoginIP().length() > 0) {
				sql.append(" AND LAST_LOGIN_IP like '" + bean.getLastLoginIP() + "%'");
			}

		}

		System.out.println(sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user bean
	 */
	public UserBean authenticate(String login, String password) {
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_user where login=? and password=?");
		UserBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	/**
	 * Gets the roles.
	 *
	 * @param bean the bean
	 * @return the roles
	 */
	public List getRoles(UserBean bean) {
		StringBuffer sql = new StringBuffer("select * from st_user where ROLE_ID=?");
		Connection conn = null;
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, bean.getRoleId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;

	}

	/**
	 * Search.
	 *
	 * @param bean the bean
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 */
	public List search(UserBean bean, int pageNo, int pageSize) {
		// log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_user WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN like '" + bean.getLogin() + "%'");
			}
			if (bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" AND PASSWORD like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getDob());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO = " + bean.getMobileNo());
			}
			if (bean.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + bean.getRoleId());
			}
			if (bean.getUnSuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFUL_LOGIN = " + bean.getUnSuccessfulLogin());
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER like '" + bean.getGender() + "%'");
			}
			if (bean.getLastLogin() != null && bean.getLastLogin().getTime() > 0) {
				sql.append(" AND LAST_LOGIN = " + bean.getLastLogin());
			}
			if (bean.getRegisteredIP() != null && bean.getRegisteredIP().length() > 0) {
				sql.append(" AND REGISTERED_IP like '" + bean.getRegisteredIP() + "%'");
			}
			if (bean.getLastLoginIP() != null && bean.getLastLoginIP().length() > 0) {
				sql.append(" AND LAST_LOGIN_IP like '" + bean.getLastLoginIP() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setUnSuccessfulLogin(rs.getInt(9));
				bean.setGender(rs.getString(10));
				bean.setLastLogin(rs.getTimestamp(11));
				bean.setLock(rs.getString(12));
				bean.setRegisteredIP(rs.getString(13));
				bean.setLastLoginIP(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDatetime(rs.getTimestamp(17));
				bean.setModifiedDatetime(rs.getTimestamp(18));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}

	/**
	 * Register user.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordsException the duplicate records exception
	 */
	public long registerUser(UserBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return pk;

	}

	
	/**
	 * Change password.
	 *
	 * @param id the id
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @return true, if successful
	 * @throws RecordNotFountException the record not fount exception
	 * @throws ApplicationException the application exception
	 */
	public boolean changePassword(Long id, String oldPassword, String newPassword)
			throws RecordNotFoundException, ApplicationException {

		log.debug("model changePassword Started");
		System.out.println("User model changePassword Started");
		boolean flag = false;
		UserBean beanExist = null;
        System.out.println("<<<<<"+flag);
		beanExist = findByPK(id);
		if (beanExist != null && beanExist.getPassword().equals(oldPassword)) {
			
			beanExist.setPassword(newPassword);
			System.out.println("userModel changepassword Method if "+beanExist);
			try {
				update(beanExist);
			} catch (Exception e) { // DuplicateRecordsException
				log.error(e);
				throw new ApplicationException("LoginId is already exist");
			}
			flag = true;
			System.out.println(">>>>"+flag);
		} else {
			throw new RecordNotFoundException("Login not exist");
		}
		System.out.println("after change Password");
		HashMap<String, String> map = new HashMap<String, String>();
		System.out.println("<<"+map);
		map.put("login", beanExist.getLogin());
		map.put("password", beanExist.getPassword());
		map.put("firstName", beanExist.getFirstName());
		map.put("lastName", beanExist.getLastName());

		String message = EmailBuilder.getChangePasswordMessage(map);
		System.out.println("<<"+message);
		EmailMessage msg = new EmailMessage();
		System.out.println("<<<<<<"+msg);
		msg.setTo(beanExist.getLogin());
		msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		System.out.println(">"+flag);
		EmailUtility.sendMail(msg);
		System.out.println(">>>>"+msg+flag);
		log.debug("Model changePassword End");
		System.out.println("USERModel changePassword End"+flag);
		return flag;

	}
	
	 /**
 	 * Forget password.
 	 *
 	 * @param login the login
 	 * @return true, if successful
 	 * @throws ApplicationException the application exception
 	 * @throws RecordNotFountException the record not fount exception
 	 */
	public  boolean resetPassword(UserBean Bean) throws DuplicateRecordException, ApplicationException, SQLException, DatabaseException
	{
		String newPassword=String.valueOf(new Date().getTime()).substring(9, 13); //randomly generates password 
		UserModel userModel=new UserModel();
		UserBean userData=userModel.findByPK(Bean.getId());
		userData.setPassword(newPassword);
	
		if(userData!=null)
		{
		 update(userData);
		 HashMap< String, String> map=new HashMap<String, String>();
		 map.put("login", Bean.getLogin());
		 map.put("password", Bean.getPassword());
		 map.put("firstName", Bean.getFirstName());
		 map.put("lastName", Bean.getLastName());
		 String message=EmailBuilder.getForgetPasswordMessage(map);
		 EmailMessage msg=new EmailMessage();
		 msg.setTo(Bean.getLogin());
		 msg.setSubject("password reset successfully");
		 msg.setMessage(message);
		 msg.setMessageType(EmailMessage.HTML_MSG);
		 
		 EmailUtility.sendMail(msg);
		//return true;
		
		}
		
	return true;	
	}
	
	
	/**
     * Send the password of User to his Email
     * 
     * @return boolean : true if success otherwise false
     * @param login
     *            : User Login
     * @throws ApplicationException
     * @throws RecordNotFoundException
     *             : if user not found
     */
	
 	public boolean forgetPassword(String login) throws ApplicationException,
     RecordNotFoundException {
 
		 UserBean userData = findByLogin(login);
		 System.err.println("UserData :"+userData);
		 
 try {  
	URL url=new URL("http://www.google.com");
	URLConnection con=url.openConnection();
	con.connect();
} catch (IOException e) {
	// TODO: handle exception
	throw new ApplicationException("gfhfhg"+e.getMessage());
}
 boolean flag = false;
 if (userData == null) {
	 System.err.println("ForgetPassword if userData ==null block");
     throw new RecordNotFoundException("Email ID does not exists !");

 }

 HashMap<String, String> map = new HashMap<String, String>();
 map.put("login", userData.getLogin());
 map.put("password", userData.getPassword());
 map.put("firstName", userData.getFirstName());
 map.put("lastName", userData.getLastName());
 String message = EmailBuilder.getForgetPasswordMessage(map);
 EmailMessage msg = new EmailMessage();
 msg.setTo(login);
 msg.setSubject("SUNARYS ORS Password reset");
 msg.setMessage(message);
 msg.setMessageType(EmailMessage.HTML_MSG);
 EmailUtility.sendMail(msg);
 flag = true;

 

 return flag;
}
	
	
	
	

}
