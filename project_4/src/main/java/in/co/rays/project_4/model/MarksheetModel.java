package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mchange.util.DuplicateElementException;

import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.bean.StudentBean;
import in.co.rays.project_4.exception.ApplicationException;

import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class MarksheetModel.
 */
public class MarksheetModel {
	
	 /** The log. */
 	Logger log = Logger.getLogger(MarksheetModel.class);
	
	/**
	 * Next PK.
	 *
	 * @return the integer
	 */
	public Integer nextPK() {
		Connection conn;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(ID) from st_marksheet");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return pk + 1;
	}

	/**
	 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordsException the duplicate records exception
	 */
	public long add(MarksheetBean bean) throws ApplicationException,DuplicateRecordException {
		
	Connection conn = null;
		int pk = 0;
		
		StudentModel smodel=new StudentModel();
		StudentBean sbean=smodel.findByPK(bean.getStudentId());
		System.out.println(bean.getStudentId());
		System.out.println("MarksheetModel: "+sbean);
		bean.setName(sbean.getFirstName()+" "+sbean.getLastName());
		
		MarksheetBean duplicateMarksheet = findByRollNo(bean.getRollNo());
       

        if (duplicateMarksheet != null) {
            throw new DuplicateRecordException("Roll Number already exists");
        }
		
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");
			pk = nextPK();
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("marksheet add");
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	/**
	 * Delete.
	 *
	 * @param bean the bean
	 */
	public void delete(MarksheetBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_marksheet where ID=?");

			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			System.out.println("marksheet details deleted");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Find by roll no.
	 *
	 * @param rollno the rollno
	 * @return the marksheet bean
	 */
	public MarksheetBean findByRollNo(String rollno) {
		Connection conn = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM st_marksheet where ROLL_NO=?");
		MarksheetBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rollno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return bean;

	}

	/**
	 * Update.
	 *
	 * @param bean the bean
	 * @throws ApplicationException the application exception
	 */
	public void update(MarksheetBean bean) throws ApplicationException {
		System.out.println("MarksheetModel Update");
		
		StudentModel stumodel=new StudentModel();
		StudentBean stubean=stumodel.findByPK(bean.getStudentId());
		bean.setName(stubean.getFirstName()+" "+stubean.getLastName());
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_marksheet set ROLL_NO=?,STUDENT_ID=?,NAME=?,PHYSICS=?,CHEMISTRY=?,MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

			pstmt.executeUpdate();
			System.out.println("marksheet updated");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Find by PK.
	 *
	 * @param pk the pk
	 * @return the marksheet bean
	 */
	public MarksheetBean findByPK(long pk) {
		StringBuffer sql = new StringBuffer("select * from st_marksheet where ID=?");
		MarksheetBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	/**
	 * List.
	 *
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
	/*public List list() {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_marksheet");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}
*/
	 public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	    /**
    	 * get List of Marksheet with pagination.
    	 *
    	 * @param pageNo            : Current Page No.
    	 * @param pageSize            : Size of Page
    	 * @return list : List of Marksheets
    	 * @throws ApplicationException the application exception
    	 */

	    public List list(int pageNo, int pageSize) throws ApplicationException {

	        log.debug("Model  list Started");

	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from ST_MARKSHEET");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	            System.out.println(sql);
	        }

	        Connection conn = null;

	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                MarksheetBean bean = new MarksheetBean();
	                bean.setId(rs.getLong(1));
	                bean.setRollNo(rs.getString(2));
	                bean.setStudentId(rs.getLong(3));
	                bean.setName(rs.getString(4));
	                bean.setPhysics(rs.getInt(5));
	                bean.setChemistry(rs.getInt(6));
	                bean.setMaths(rs.getInt(7));
	                bean.setCreatedBy(rs.getString(8));
	                bean.setModifiedBy(rs.getString(9));
	                bean.setCreatedDatetime(rs.getTimestamp(10));
	                bean.setModifiedDatetime(rs.getTimestamp(11));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error(e);
	            throw new ApplicationException(
	                    "Exception in getting list of Marksheet");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model  list End");
	        return list;

	    }
	
	/**
	 * Gets the merit list.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the merit list
	 * @throws ApplicationException the application exception
	 */
	public List getMeritList(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model  MeritList Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				"select ID,ROLL_NO,STUDENT_ID,NAME,PHYSICS,CHEMISTRY,MATHS,CREATED_BY,MODIFIED_BY,CREATED_DATETIME,MODIFIED_DATETIME,(PHYSICS+CHEMISTRY+MATHS) as total from st_marksheet where physics>33 and chemistry>33 and maths>33 order by total desc");
		  if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
            log.error(e);
            throw new ApplicationException(
                    "Exception in getting merit list of Marksheet");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model  MeritList End");
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
	public List search(MarksheetBean bean, int pageNo, int pageSize) throws ApplicationException {
          System.out.println("llllllll"+bean.getId());
		log.debug("Model  search Started");
		StringBuffer sql = new StringBuffer("select * from st_marksheet where true");

		if (bean != null) {
			System.out.println("service" + bean.getName());
			if (bean.getId() > 0) {
				sql.append(" AND ID = " + bean.getId());
			}
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" AND ROLL_NO like '" + bean.getRollNo() + "%'");
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if(bean.getPhysics()>0){
              sql.append(" AND PHYSICS=" +bean.getPhysics());				
			}
			if(bean.getChemistry()>0){
				sql.append(" AND CHEMISTRY =" +bean.getChemistry());
			}
			if(bean.getMaths()>0){
				sql.append(" AND MATHS ="+bean.getMaths());
				
			}

		} if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        System.out.println("MarksheetModel :" +sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error(e);
	            throw new ApplicationException("Update rollback exception "
	                    + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model  search End");
		return list;
	}
	 
 	/**
 	 * Search.
 	 *
 	 * @param bean the bean
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
 	public List search(MarksheetBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }

}
