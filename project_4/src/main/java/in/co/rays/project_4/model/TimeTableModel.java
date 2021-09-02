package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.bean.TimeTableBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class TimeTableModel.
 */
public class TimeTableModel {

	/** The log. */
	public static Logger log = Logger.getLogger(TimeTableModel.class);

	/**
	 * Next pk.
	 *
	 * @return the integer
	 */
	public Integer nextPk() {
		log.debug("Timetable nextPk");
		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Select max(id) from st_timetable");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable nextPk ended");
		return pk + 1;

	}

	/**
	 * Adds the.
	 *
	 * @param bean
	 *            the bean
	 * @return the long
	 * @throws ApplicationException
	 *             the application exception
	 * @throws DuplicateRecordsException
	 *             the duplicate records exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException, SQLException {
		log.debug("TimeTable Add method started");

		// int pk=nextPk();
		Connection conn = null;

		CourseModel coumodel = new CourseModel();
		CourseBean coubean = new CourseBean();
		coubean = coumodel.findByPk(bean.getCourseId());
		System.out.println(bean.getCourseId());
		System.out.println(coubean);
		String couname = coubean.getCourseName();

		SubjectModel submodel = new SubjectModel();
		SubjectBean subbean = new SubjectBean();
		subbean = submodel.findByPk(bean.getSubjectId());
		System.out.println(subbean);
		String subName = subbean.getSubjectName();
		System.out.println("TimeTableModel :" + subName);

		int pk = 0;

		TimeTableBean Bean = new TimeTableBean();
		Bean = FindByCourseNameSubjectName(couname, subName);
		if (Bean != null) {
			throw new DuplicateRecordException("record already existed");
		}
		Bean = findByCourseNameExamDate(couname, bean.getExamDate());
		if (Bean != null) {
			throw new DuplicateRecordException("record already existed");
		}

		Bean = findByCourseNameSubjectNameExamDateExamTime(couname, subName, bean.getExamDate(), bean.getExamTime());
		if (Bean != null) {
			throw new DuplicateRecordException("record already existed");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			pk = nextPk();
			StringBuffer sql = new StringBuffer("insert into st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			pstmt.setString(2, couname);
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, subName);
			pstmt.setLong(5, bean.getSubjectId());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getExamTime());
			pstmt.setString(8, bean.getSemester());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("Data is added");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception : add rollBack " + e2.getMessage());
			}
			throw new ApplicationException("Exception : add Timetable Exception");

		}

		finally {
			// conn.commit();
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("TimeTableModel add ended");
		return pk;

	}

	/**
	 * Delete.
	 *
	 * @param bean
	 *            the bean
	 * @throws ApplicationException
	 *             the application exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void delete(TimeTableBean bean) throws ApplicationException, SQLException {
		log.debug("TimeTableModel delete started");
		Connection conn = null;
		System.out.println("Delete");
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("Delete from st_TimeTable where id=?");
			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Find by course name subject name.
	 *
	 * @param courseName
	 *            the course name
	 * @param subjectName
	 *            the subject name
	 * @return the time table bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public TimeTableBean FindByCourseNameSubjectName(String courseName, String subjectName) throws SQLException {

		log.debug("findByCourseNameSubjectName debug started");
		Connection conn = null;

		TimeTableBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("select * from st_timetable where course_name=? and subject_name=?");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, courseName);
			pstmt.setString(2, subjectName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(7));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RecordNotFoundException("no such record exist");
		} finally {

			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("findByCourseNameSubjectName debug completed");
		return bean;

	}

	/**
	 * Find by course name subject name exam date exam time.
	 *
	 * @param courseName
	 *            the course name
	 * @param subjectName
	 *            the subject name
	 * @param exameDate
	 *            the exame date
	 * @param examTime
	 *            the exam time
	 * @return the time table bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public TimeTableBean findByCourseNameSubjectNameExamDateExamTime(String courseName, String subjectName,
			Date exameDate, String examTime) throws SQLException {
		log.debug("findByCourseNameSubjectNameDateTime debug started");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TimeTableBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer(
					"select * from st_timetable where COURSE_NAME=? and SUBJECT_NAME=? and EXAM_DATE=? and EXAM_TIME=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, courseName);
			pstmt.setString(2, subjectName);
			pstmt.setDate(3, new java.sql.Date(exameDate.getTime()));
			pstmt.setString(4, examTime);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// } finally{
		// pstmt.close();
		// conn.commit();
		// JDBCDataSource.closeConnection(conn);
		// }
		// log.debug("findByCourseNameSubjectNameDateTime debug completed");
		// //System.out.println(bean==null);
		return bean;
	}

	/**
	 * Find by pk.
	 *
	 * @param pk
	 *            the pk
	 * @return the time table bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public TimeTableBean findByPk(long pk) throws SQLException {
		log.debug("findByPk debug started");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TimeTableBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("select * from st_timetable where id=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("findByPk debug completed");
		return bean;
	}

	/**
	 * Update.
	 *
	 * @param bean
	 *            the bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public void update(TimeTableBean bean) throws SQLException {
		log.debug("update debug started");
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		CourseModel couModel = new CourseModel();
		CourseBean couBean = new CourseBean();
		couBean = couModel.findByPk(bean.getCourseId());
		String couName = couBean.getCourseName();

		SubjectModel subModel = new SubjectModel();
		SubjectBean subBean = new SubjectBean();
		subBean = subModel.findByPk(bean.getSubjectId());
		String subName = subBean.getSubjectName();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer(
					"update st_timetable set COURSE_NAME=?,COURSE_ID=?,SUBJECT_NAME=?,SUBJECT_ID=?,EXAM_DATE=?,EXAM_TIME=?,SEMESTER=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATE_TIME=?,MODIFIED_DATE_TIME=? where ID=?");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, couName);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, subName);
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setDate(5, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(6, bean.getExamTime());
			pstmt.setString(7, bean.getSemester());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("update debug completed");
	}

	public List search(TimeTableBean bean) throws SQLException {
		return search(bean,0, 0);
	}

	/**
	 * Search.
	 *
	 * @param Bean
	 *            the bean
	 * @param pageNo
	 *            the page no
	 * @param pageSize
	 *            the page size
	 * @return the list
	 * @throws SQLException
	 *             the SQL exception
	 */
	public List search(TimeTableBean Bean, int pageNo, int pageSize) throws SQLException {
		log.debug("search debug started");
		ArrayList list = new ArrayList();
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1"); /// dynamic
																						/// injection
																						/// return
																						/// true
																						/// value
																						/// and
																						/// at
																						/// run
																						/// time
																						/// it
																						/// will
																						/// provide
																						/// attribute
																						/// to
																						/// append
																						/// with
																						/// the
																						/// query
		if (Bean != null) {
		//	System.out.println(" exam date - "+DataUtility.getSearchDate((java.sql.Date) Bean.getExamDate()));
			if (Bean.getId() > 0) {
				sql.append(" and ID=" + Bean.getId());
				
			}

			// System.out.println(Bean.getCourseName());
			if(Bean.getExamDate()!=null && Bean.getExamDate().getDate()>0){
				java.sql.Date d=new java.sql.Date(Bean.getExamDate().getTime());
				sql.append(" AND EXAM_DATE like'" + d + "%'");
			}

			if (Bean.getCourseName() != null && Bean.getCourseName().length() > 0) {
				sql.append(" and COURSE_NAME like '" + Bean.getCourseName() + "%'");
			}

			// System.out.println(Bean.getSubjectName());

			if (Bean.getSubjectName() != null && Bean.getSubjectName().length() > 0) {
				sql.append(" and SUBJECT_NAME like'" + Bean.getSubjectName() + "%'");
			}

			/*if (Bean.getExamDate() != null && Bean.getExamDate().getDate() > 0) {
				sql.append(" and exam_date = '" + DataUtility.getSearchDate(Bean.getExamDate()) + "'");
			}*/

			if (Bean.getExamTime() != null && Bean.getExamTime().length() > 0) {
				sql.append(" and EXAM_TIME like'" + Bean.getExamTime() + "%'");
			}
			if (Bean.getSemester() != null && Bean.getSemester().length() > 0) {
				sql.append(" and SEMESTER like'" + Bean.getSemester() + "%'");
			}
			if (Bean.getCourseId() > 0) {
				sql.append(" and COURSE_ID=" + Bean.getCourseId());
			}
			// System.out.println(Bean.getSubjectId());
			if (Bean.getSubjectId() > 0) {
				sql.append(" and SUBJECT_ID=" + Bean.getSubjectId());
			}

		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
			System.out.println(sql);
		}

		try {
			PreparedStatement pstm = null;
			ResultSet rs = null;
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql.toString());
			System.out.println(" qry:" + sql);

			rs = pstm.executeQuery();
			TimeTableBean bean = null;
			while (rs.next()) {
				bean = new TimeTableBean();
				System.out.println(bean.getExamTime());
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));

				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		System.out.println("TimetableModel Sql:" + sql);
		log.debug("search debug completed");
		return list;
	}

	/**
	 * List.
	 *
	 * @return the list
	 * @throws SQLException
	 *             the SQL exception
	 */
	public List list() throws SQLException {
		return list(0, 0);
	}

	/**
	 * List.
	 *
	 * @param pageNo
	 *            the page no
	 * @param pageSize
	 *            the page size
	 * @return the list
	 * @throws SQLException
	 *             the SQL exception
	 */
	public List list(int pageNo, int pageSize) throws SQLException {
		log.debug("list debug started");
		Connection conn = null;
		TimeTableBean Bean = null;
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_timetable ");

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
				Bean = new TimeTableBean();
				Bean.setId(rs.getLong(1));
				Bean.setCourseName(rs.getString(2));
				Bean.setCourseId(rs.getLong(3));
				Bean.setSubjectName(rs.getString(4));
				Bean.setSubjectId(rs.getLong(5));
				Bean.setExamDate(rs.getDate(6));
				Bean.setExamTime(rs.getString(7));
				Bean.setSemester(rs.getString(8));
				Bean.setCreatedBy(rs.getString(9));
				Bean.setModifiedBy(rs.getString(10));
				Bean.setCreatedDatetime(rs.getTimestamp(11));
				Bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(Bean);
			}
		} catch (Exception e) {
			// throw new RecordNotFoundException("exception in list search");
			e.printStackTrace();
		} finally {

			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("list debug completed");
		return list;
	}

	/**
	 * Find by course name exam date.
	 *
	 * @param courseName
	 *            the course name
	 * @param examDate
	 *            the exam date
	 * @return the time table bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public TimeTableBean findByCourseNameExamDate(String courseName, Date examDate) throws SQLException {
		log.debug("findByCourseNameDate debug started");
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TimeTableBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sql = new StringBuffer("select * from st_timetable where course_name=? and exam_date=?");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, courseName);
			pstm.setDate(2, new java.sql.Date(examDate.getTime())); // doubt
			rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getString(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));

				bean.setModifiedDatetime(rs.getTimestamp(12));
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstm.close();
			conn.commit();
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("findByCourseNameDate debug completed");
		return bean;
	}

}
