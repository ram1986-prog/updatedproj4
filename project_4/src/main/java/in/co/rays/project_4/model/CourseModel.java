package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class CourseModel.
 */
public class CourseModel {
	
	/** The log. */
	private static Logger log=Logger.getLogger(CourseModel.class);
	
	/** The model. */
	public static CourseModel model=new CourseModel();
	
	/**
	 * Next PK.
	 *
	 * @return the integer
	 */
	public Integer nextPK(){
		Connection conn=null;
		int pk=0;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("select max(ID) from st_course");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk+1;
		
	}
	
	/**
	 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws DuplicateRecordsException the duplicate records exception
	 * @throws ApplicationException the application exception
	 */
	public long add(CourseBean bean) throws DuplicateRecordException, ApplicationException{
		log.debug("add debug started");
		Connection conn=null;
		int pk=0;
		CourseBean Bean=new CourseBean();
		Bean=findByCourseName(bean.getCourseName());
		if(Bean!=null)
		{
			throw new DuplicateRecordException("course already exist...!!!");
		}else{
			try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement("insert into st_course values(?,?,?,?,?,?,?)");
			pk=nextPK();
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCourseName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			
			pstmt.executeUpdate();
			System.out.println("Course Added");
			CourseBean beanadd=model.findByPk(pk);
			if(beanadd!=null){
				System.out.println("sucess");
			}else{
				System.out.println("error");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(conn);
		
		}
		
		//}
		
		log.debug("courseModel data added");
	return pk;
		
		}
		
	}
	
	/**
	 * Delete.
	 *
	 * @param bean the bean
	 */
	public void delete(CourseBean bean) {
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where ID=?");
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
	 * Find by course name.
	 *
	 * @param courseName the course name
	 * @return the course bean
	 * @throws ApplicationException the application exception
	 */
	public CourseBean findByCourseName(String courseName) throws ApplicationException{
		log.debug("findByCourse method started");
		StringBuffer sql=new StringBuffer("select * from st_course where course_name=?");

	    Connection conn=null;
	    CourseBean bean=null;
	    try {
			conn= JDBCDataSource.getConnection();
						PreparedStatement pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, courseName);
			ResultSet rs=pstmt.executeQuery();
		  while(rs.next()){
			  bean=new CourseBean();
			 bean.setId(rs.getLong(1));
			 bean.setCourseName(rs.getString(2));
			 bean.setDescription(rs.getString(3));
			 bean.setCreatedBy(rs.getString(4));
			 bean.setModifiedBy(rs.getString(5));
			 bean.setCreatedDatetime(rs.getTimestamp(6));
			 bean.setModifiedDatetime(rs.getTimestamp(7));
			 
			  
		  }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ApplicationException("Exception: Exception in getting CourseName");
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	    log.debug("findByCourse method completed");
		return bean;
		
	}
	
	/**
	 * Find by pk.
	 *
	 * @param pk the pk
	 * @return the course bean
	 */
	public CourseBean findByPk(long pk){
		log.debug("courseModel findByPk started");
		StringBuffer sql=new StringBuffer("select * from st_course where ID=?");
		System.out.println("Course Find by pk");
		Connection conn=null;
		PreparedStatement pstmt=null;
		CourseBean bean=null;
		
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			System.err.println("pk on course model "+pk);
			 
			ResultSet rs= pstmt.executeQuery();
			 while(rs.next()){
					        bean= new CourseBean();
							bean.setId(rs.getLong(1));
							bean.setCourseName(rs.getString(2));
							bean.setDescription(rs.getString(3));
							bean.setCreatedBy(rs.getString(4));
							bean.setModifiedBy(rs.getString(5));
							bean.setCreatedDatetime(rs.getTimestamp(6));
							bean.setModifiedDatetime(rs.getTimestamp(7));
					 
		 				}
	//	 System.err.println(" c name in  "+bean.getCourseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("courseModel findByPk completed");
		return bean;
		
	}

/**
 * Update.
 *
 * @param bean the bean
 */
public void update(CourseBean bean){
	log.debug("courseModel update started");
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	StringBuffer sql=new StringBuffer("update st_course set COURSE_NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATE_TIME=?,MODIFIED_DATE_TIME=? where ID=?");
	 try {
		conn= JDBCDataSource.getConnection();
		pstmt=conn.prepareStatement(sql.toString());
		pstmt.setString(1, bean.getCourseName());
		pstmt.setString(2, bean.getDescription());
		pstmt.setString(3, bean.getCreatedBy());
		pstmt.setString(4, bean.getModifiedBy());
		pstmt.setTimestamp(5, bean.getCreatedDatetime());
		pstmt.setTimestamp(6, bean.getModifiedDatetime());
		pstmt.setLong(7, bean.getId());
		pstmt.executeUpdate();
		System.out.println("courseModel Updated");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCDataSource.closeConnection(conn);
	}
	 
}

/**
 * Search.
 *
 * @param bean the bean
 * @param pageNo the page no
 * @param pageSize the page size
 * @return the list
 */
public List search(CourseBean bean){
	return search(bean,0,0);
	
}
public List search(CourseBean bean, int pageNo, int pageSize){
	 
	log.debug("course model search stared");
	ArrayList list=new ArrayList();
	Connection conn=null;
	StringBuffer sql=new StringBuffer("select * from st_course where 1=1");

	if(bean!=null)
	{
		if(bean.getId()>0)
		{
			System.out.println("CourseModel list if()");
			
			sql.append(" and ID=" +bean.getId());
		}
		if(bean.getCourseName()!=null && bean.getCourseName().length()>0)
		{
			
			sql.append(" and COURSE_NAME like '"+bean.getCourseName()+"%'");
			System.out.println("CourseModel Searchmethod : "+ sql);
		}
	}
	if(pageSize>0)
	{
		pageNo=(pageNo-1)*pageSize;         
		sql.append(" limit "+pageNo+","+pageSize);
	}
	try {
		conn=JDBCDataSource.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(sql.toString());
	
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			bean=new CourseBean();
			bean.setId(rs.getLong(1));
			bean.setCourseName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDatetime(rs.getTimestamp(6));
			bean.setModifiedDatetime(rs.getTimestamp(7));
			list.add(bean);
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCDataSource.closeConnection(conn);
	}
	System.out.println(sql);
	return list;
	
}

/**
 * List.
 *
 * @return the list
 */
public List list(){
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
	log.debug("courseModel list method started");
	System.out.println("courseModel list method started");
	Connection conn=null;
	CourseBean bean=null;
	List list=new ArrayList();
	StringBuffer sql= new StringBuffer("select * from st_course" );
	
		if(pageSize>0)
			
		{
			pageNo=(pageNo-1)*pageSize;
			System.out.println("coursemodel list pageNo: "+pageNo);
			sql.append("limit "+pageNo+","+pageSize);
		}
	
	try {
		conn = JDBCDataSource.getConnection();
		conn.setAutoCommit(false);
	PreparedStatement pstmt=conn.prepareStatement(sql.toString());
	System.out.println(pstmt);
	ResultSet rs=pstmt.executeQuery();
	while(rs.next()){
		bean=new CourseBean();
		bean.setId(rs.getLong(1));
		bean.setCourseName(rs.getString(2));
		bean.setDescription(rs.getString(3));
		bean.setCreatedBy(rs.getString(4));
		bean.setModifiedBy(rs.getString(5));
		bean.setCreatedDatetime(rs.getTimestamp(6));
		bean.setModifiedDatetime(rs.getTimestamp(7));
    	list.add(bean);
    	
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCDataSource.closeConnection(conn);
	}
	log.debug("CourseModel list complited");
	System.out.println("courseModel list completed");
	System.out.println(">>>>>>>____----"+list);
	return list;
	
}



















}
