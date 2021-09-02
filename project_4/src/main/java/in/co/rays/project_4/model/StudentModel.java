package in.co.rays.project_4.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.CollegeBean;
import in.co.rays.project_4.bean.StudentBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;

import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class StudentModel.
 */
public class StudentModel {

	/** The log. */
	private static Logger log = Logger.getLogger(StudentModel.class);
	
	/**
	 * Next PK.
	 *
	 * @return the integer
	 */
	public Integer nextPK() {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(ID)from st_student");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public long add(StudentBean bean) throws DuplicateRecordException, ApplicationException {
		
		  log.debug("Model add Started");
		  // get College Name
	        CollegeModel cModel = new CollegeModel();
	        System.out.println("student model add() cmodel: "+cModel);
	        CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
	        System.out.println("<");
	       
	         bean.setCollegeName(collegeBean.getName());
	        
               System.out.println("student model add() collegebean: "+collegeBean);
	       StudentBean duplicateName = findByEmailId(bean.getEmail());
	        int pk = 0;

	       if (duplicateName != null) {
	            throw new DuplicateRecordException("Email already exists");
	        }

		  
		Connection conn = null;
		         System.out.println("<<");
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into st_student value(?,?,?,?,?,?,?,?,?,?,?,?) ");
			System.out.println("<<<"+bean.getCollegeId()+""+bean.getDob()+""+nextPK());
			pk = nextPK();
			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			pstmt.setString(3, bean.getCollegeName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("student added");
			pstmt.close();

		} catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add Student");
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
	 * @throws ApplicationException the application exception
	 */
	public void delete(StudentBean bean) throws ApplicationException {
		
		 log.debug("Model delete Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from st_student where ID=?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();

			System.out.println("Student record deleted");
			pstmt.close();
		} catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
	}

	/**
	 * Find by email id.
	 *
	 * @param Email the email
	 * @return the student bean
	 * @throws ApplicationException the application exception
	 */
	public StudentBean findByEmailId(String Email) throws ApplicationException {
		
		 log.debug("Model findBy Email Started");
		Connection conn = null;
		StudentBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_student where Email=?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, Email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();

		} catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy Email End");

		return bean;

	}

	/**
	 * Find by PK.
	 *
	 * @param pk the pk
	 * @return the student bean
	 * @throws ApplicationException the application exception
	 */
	public StudentBean findByPK(long pk) throws ApplicationException {
		
		 log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM st_student WHERE ID=?");
		StudentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setCollegeId(rs.getLong(2));
				bean.setCollegeName(rs.getString(3));
				bean.setFirstName(rs.getString(4));
				bean.setLastName(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setEmail(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();

		} catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");

		return bean;

	}
    
    /**
     * Update.
     *
     * @param bean the bean
     * @throws DuplicateRecordsException the duplicate records exception
     * @throws ApplicationException the application exception
     */
    public void update(StudentBean bean) throws DuplicateRecordException, ApplicationException{
    	
                
    	      log.debug("Model update Started");
    	        Connection conn = null;
             System.out.println("jjjjjjjjjjj..in model update method"+bean.getId());
    	        StudentBean beanExist = findByEmailId(bean.getEmail());

    	        // Check if updated Roll no already exist
    	        if (beanExist != null && beanExist.getId() != bean.getId()) {
    	            throw new DuplicateRecordException("Email Id is already exist");
    	        }

    	        // get College Name
    	        CollegeModel cModel = new CollegeModel();
    	        CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
    	        bean.setCollegeName(collegeBean.getName());
         System.out.println("<>>>>"+new java.sql.Date(bean.getDob().getTime()));
    	        try {

    	            conn = JDBCDataSource.getConnection();

    	           conn.setAutoCommit(false); // Begin transaction
    	            PreparedStatement pstmt = conn
    	                    .prepareStatement("UPDATE st_student SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,MOBILE_N0=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
    	            pstmt.setLong(1, bean.getCollegeId());
    	            pstmt.setString(2, bean.getCollegeName());
    	            pstmt.setString(3, bean.getFirstName());
    	            pstmt.setString(4, bean.getLastName());
    	            System.out.println("kjjj");
    	            pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
    	            pstmt.setString(6, bean.getMobileNo());
    	            pstmt.setString(7, bean.getEmail());
    	            pstmt.setString(8, bean.getCreatedBy());
    	            pstmt.setString(9, bean.getModifiedBy());
    	            pstmt.setTimestamp(10, bean.getCreatedDatetime());
    	            pstmt.setTimestamp(11, bean.getModifiedDatetime());
    	            pstmt.setLong(12, bean.getId());
    	            pstmt.executeUpdate();
    	            System.out.println("updated");
    	          conn.commit(); // End transaction
    	            pstmt.close();
    	        } catch (Exception e) {
    	        	e.printStackTrace();
    	            log.error("Database Exception..", e);
    	            try {
    	                conn.rollback();
    	            } 
    	            catch (Exception ex) {
    	                throw new ApplicationException(
    	                        "Exception : Delete rollback exception "+ ex.getMessage());
    	            }
    	            //throw new ApplicationException("Exception in updating Student ");
    	        } finally {
    	            JDBCDataSource.closeConnection(conn);
    	        }
    	       log.debug("Model update End");
    	 
    	

    }

/**
 * List.
 *
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the list
 * @throws ApplicationException the application exception
 */
public List list(int pageNo, int pageSize) throws ApplicationException{
	
	 log.debug("Model list Started");
	 ArrayList list = new ArrayList();
	 
	  Connection conn = null;
	  StringBuffer sql = new StringBuffer("select * from st_student");
	
	  // if page size is greater than zero then apply pagination
      if (pageSize > 0) {
          // Calculate start record index
          pageNo = (pageNo - 1) * pageSize;
          sql.append(" limit " + pageNo + "," + pageSize);
      }

	  
	  try {
		conn= JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            StudentBean bean = new StudentBean();
            bean.setId(rs.getLong(1));
            bean.setCollegeId(rs.getLong(2));
            bean.setCollegeName(rs.getString(3));
            bean.setFirstName(rs.getString(4));
            bean.setLastName(rs.getString(5));
            bean.setDob(rs.getDate(6));
            bean.setMobileNo(rs.getString(7));
            bean.setEmail(rs.getString(8));
            bean.setCreatedBy(rs.getString(9));
            bean.setModifiedBy(rs.getString(10));
            bean.setCreatedDatetime(rs.getTimestamp(11));
            bean.setModifiedDatetime(rs.getTimestamp(12));          
            list.add(bean);
        }
        rs.close();
	}  catch (Exception e) {
        log.error("Database Exception..", e);
        throw new ApplicationException(
                "Exception : Exception in getting list of Student");
    } finally {
        JDBCDataSource.closeConnection(conn);
    }

    log.debug("Model list End");
	  return list;
	
}

/**
 * Search.
 *
 * @param bean the bean
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the list
 * @throws ApplicationException the application exception
 */
public List search(StudentBean bean, int pageNo, int pageSize) throws ApplicationException{
	
	  log.debug("Model search Started");
    
    StringBuffer sql = new StringBuffer("SELECT * FROM st_student WHERE 1=1");

    if (bean != null) {
        if (bean.getId() > 0) {
            sql.append(" AND ID = " + bean.getId());
        }
        if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
            sql.append(" AND FIRST_NAME like '" + bean.getFirstName()
                    + "%'");
        }
        if (bean.getLastName() != null && bean.getLastName().length() > 0) {
            sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
        }
        if (bean.getDob() != null && bean.getDob().getDate() > 0) {
            sql.append(" AND DATE_OF_BIRTH = " + bean.getDob());
        }
        if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
            sql.append(" AND MOBILE_NO like '" + bean.getMobileNo() + "%'");
        }
        if (bean.getEmail() != null && bean.getEmail().length() > 0) {
            sql.append(" AND EMAIL like '" + bean.getEmail() + "%'");
        }
        if (bean.getCollegeName() != null
                && bean.getCollegeName().length() > 0) {
            sql.append(" AND COLLEGE_NAME = " + bean.getCollegeName());
        }

    }

    // if page size is greater than zero then apply pagination
    if (pageSize > 0) {
        // Calculate start record index
        pageNo = (pageNo - 1) * pageSize;

        sql.append(" Limit " + pageNo + ", " + pageSize);
        // sql.append(" limit " + pageNo + "," + pageSize);
    }

    ArrayList list = new ArrayList();
    Connection conn = null;
    try {
        conn = JDBCDataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            bean = new StudentBean();
            bean.setId(rs.getLong(1));
            bean.setCollegeId(rs.getLong(2));
            bean.setCollegeName(rs.getString(3));
            bean.setFirstName(rs.getString(4));
            bean.setLastName(rs.getString(5));
            bean.setDob(rs.getDate(6));
            bean.setMobileNo(rs.getString(7));
            bean.setEmail(rs.getString(8));
            bean.setCreatedBy(rs.getString(9));
            bean.setModifiedBy(rs.getString(10));
            bean.setCreatedDatetime(rs.getTimestamp(11));
            bean.setModifiedDatetime(rs.getTimestamp(12));
            list.add(bean);
        }
        rs.close();
    }  catch (Exception e) {
        log.error("Database Exception..", e);
        throw new ApplicationException(
                "Exception : Exception in search Student");
    } finally {
        JDBCDataSource.closeConnection(conn);
    }

    log.debug("Model search End");

   
    return list;
}

/**
 * List.
 *
 * @return the list
 * @throws ApplicationException the application exception
 */
public List list() throws ApplicationException{
	
	 log.debug("Model list Started");
	 ArrayList list = new ArrayList();
	 
	  Connection conn = null;
	  StringBuffer sql = new StringBuffer("select * from st_student");
	
	   try {
		conn= JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
       ResultSet rs = pstmt.executeQuery();
       while (rs.next()) {
           StudentBean bean = new StudentBean();
           bean.setId(rs.getLong(1));
           bean.setCollegeId(rs.getLong(2));
           bean.setCollegeName(rs.getString(3));
           bean.setFirstName(rs.getString(4));
           bean.setLastName(rs.getString(5));
           bean.setDob(rs.getDate(6));
           bean.setMobileNo(rs.getString(7));
           bean.setEmail(rs.getString(8));
           bean.setCreatedBy(rs.getString(9));
           bean.setModifiedBy(rs.getString(10));
           bean.setCreatedDatetime(rs.getTimestamp(11));
           bean.setModifiedDatetime(rs.getTimestamp(12));          
           list.add(bean);
       }
       rs.close();
	}  catch (Exception e) {
       log.error("Database Exception..", e);
       throw new ApplicationException(
               "Exception : Exception in getting list of Student");
   } finally {
       JDBCDataSource.closeConnection(conn);
   }

   log.debug("Model list End");
	  return list;
	
}



}
