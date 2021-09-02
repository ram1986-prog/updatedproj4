package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class SubjectModel.
 */
public class SubjectModel {

	/** The log. */
	private static Logger log = Logger.getLogger(SubjectModel.class);

	/**
	 * Next pk.
	 *
	 * @return the integer
	 * @throws SQLException the SQL exception
	 */
	public Integer nextPk() throws SQLException {
		log.debug("next pk debug started");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("select max(ID) from st_subject");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("nextPk completed");
		return pk + 1;

	}

	/**
	 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws SQLException the SQL exception
	 */
	public long add(SubjectBean bean) throws SQLException {
		log.debug("add debug started");

		Connection conn = null;

		
		 CourseModel comodel=new CourseModel();
		   CourseBean cobean=comodel.findByPk(bean.getCourseId());
             bean.setCourseName(cobean.getCourseName());		 

		int pk = nextPk();

		System.out.println(pk);
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("insert into st_subject values(?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getSubjectName());
			pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setLong(5, bean.getCourseId());
			pstmt.setString(6, bean.getDescription());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("Subject Data added");

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
	public void delete(SubjectBean bean) {

		log.debug("subjectModel delete stared");
		Connection conn = null;

		StringBuffer sql = new StringBuffer("Delete from st_subject where ID=?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
System.out.println("SubjectModel Delete:"+sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel data Deleted");
	}

	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	public void Update(SubjectBean bean) {

		CourseModel couModel = new CourseModel();

		CourseBean couBean = couModel.findByPk(bean.getCourseId());
         bean.setCourseName(couBean.getCourseName());
		String courseName = couBean.getCourseName();

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer(
					"Update st_subject set SUBJECT_NAME=?,SUBJECT_ID=?,COURSE_NAME=?,COURSE_ID=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? where ID=?");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bean.getSubjectName());
			pstmt.setLong(2, bean.getSubjectId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel Data Updated");
		System.out.println("Subject model Data Updated");

	}

	/**
	 * Find by subject name.
	 *
	 * @param SubjectName the subject name
	 * @return the subject bean
	 * @throws SQLException the SQL exception
	 */
	public SubjectBean findBySubjectName(String SubjectName) throws SQLException {
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_subject where SUBJECT_NAME=? ");

		SubjectBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, SubjectName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				
				bean.setSubjectName(rs.getString(2));
				bean.setSubjectId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setCourseId(rs.getLong(5));
				bean.setDescription(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				System.out.println("SubjectModel find by Subject completed");
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
	 * Find by pk.
	 *
	 * @param pk the pk
	 * @return the subject bean
	 */
	public SubjectBean findByPk(long pk) {

		log.debug("SubjectModel findByPk method started");
		System.out.println("SubjectModel findByPk");
		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_subject where ID=?");
		SubjectBean bean = new SubjectBean();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean.setId(rs.getLong(1));
				
				bean.setSubjectName(rs.getString(2));
				bean.setSubjectId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setCourseId(rs.getLong(5));
				bean.setDescription(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

				System.out.println("SubjectModel findByPk completed");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel FindByPk method ended");

		return bean;

	}

	/**
	 * Search.
	 *
	 * @param bean the bean
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	public List search(SubjectBean bean) throws SQLException {
		return search(bean, 0, 0);

	}

	/**
	 * Search.
	 *
	 * @param bean the bean
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws SQLException the SQL exception
	 */
	public List search(SubjectBean bean, int pageNo, int pageSize) throws SQLException {
		System.out.println("SubjectModel Search method started");
		log.debug("search started");
		ArrayList list = new ArrayList();
		Connection conn = null;
		PreparedStatement pstm = null;

		System.out.println("SubjectModel Search method started  1");
		
		  /*CourseModel couModel = new CourseModel();
		  
		  CourseBean couBean = couModel.findByPk(bean.getCourseId());
		  
		  String courseName = couBean.getCourseName();*/
		 

		StringBuffer sql = new StringBuffer("select * from st_subject where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and ID=" + bean.getId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" and course_name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" and course_id=" + bean.getCourseId());
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" and subject_name like '" + bean.getSubjectName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" and subject_id=" + bean.getSubjectId());
			}
			 if (bean.getDescription() != null
	                    && bean.getDescription().length() > 0) {
	                sql.append(" AND DESCRIPTION like '" + bean.getDescription()
	                        + "%'");
	            }
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
			System.out.println(sql);
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql.toString());
			ResultSet rs = pstm.executeQuery();
			bean = null;
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				
				bean.setSubjectName(rs.getString(2));
				bean.setSubjectId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setCourseId(rs.getLong(5));
				bean.setDescription(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstm.close();
			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		System.out.println("Subject Search method finished");
		log.debug("search completed");
		return list;

	}
	 
 	/**
 	 * List.
 	 *
 	 * @return the list
 	 * @throws ApplicationException the application exception
 	 */
 	public List list() throws ApplicationException {
	        return list(0, 0);
	    }

	/**
	 * List.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 */
	public List list(int pageNo, int pageSize) {
		log.debug("SubjectModel List method Started");

		Connection conn = null;
		StringBuffer sql = new StringBuffer("Select * from st_subject");
        SubjectBean bean=new SubjectBean();
		List list = new ArrayList();
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append("limit " + pageNo + "," + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();

				bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setSubjectId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setCourseId(rs.getLong(5));
				bean.setDescription(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

				list.add(bean);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("SubjectModel list method complted");

		return list;

	}

}
