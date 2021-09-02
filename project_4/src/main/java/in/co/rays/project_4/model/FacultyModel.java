package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.CollegeBean;
import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.FacultyBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * The Class FacultyModel.
 */
/**
 * @author Himshikha
 *
 */
public class FacultyModel {
	
	/** The log. */
	
	
	private static Logger log= Logger.getLogger(FacultyModel.class);
	
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
			PreparedStatement pstmt=conn.prepareStatement("select max(ID) from st_faculty");
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pk+1;
		
	}
	
	/**
		 * Adds the.
	 *
	 * @param bean the bean
	 * @return the long
	 * @throws DuplicateRecordsException 
	 * @throws SQLException 
	 */
	
	public long add(FacultyBean bean) throws DuplicateRecordException, SQLException{
		
		log.debug("Faculty model add method started");
		Connection conn=null;
		int pk=nextPK();
		FacultyBean Bean=new FacultyBean();
		
			CourseModel cmodel=new CourseModel();
			CourseBean cbean=cmodel.findByPk(bean.getCourseId());
		         
			bean.setCourseName(cbean.getCourseName());
		 
		 
				 SubjectModel submodel=new SubjectModel();
				 SubjectBean subbean=submodel.findByPk(bean.getSubjectId());
				 
				 bean.setSubjectName(subbean.getSubjectName());
				 
			 CollegeModel colmodel=new CollegeModel();
			 CollegeBean colbean=colmodel.findByPK(bean.getCollegeId());
			 
			 String collegeName= colbean.getName();
			 
			 Bean=findByLoginId(bean.getEmailId());
			
			 if(Bean!=null)
			{
				throw new DuplicateRecordException("LoginId already exist..!!! ");
			}
			else
			{
		
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt= conn.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			pstmt.setLong(1, pk );
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getGender());
			pstmt.setDate(5, new java.sql.Date(bean.getDoj().getTime()));
	        pstmt.setString(6, bean.getQualification());
	        pstmt.setString(7, bean.getEmailId());
	        pstmt.setString(8, bean.getMobileNo());
            pstmt.setLong(9, bean.getCollegeId());
            pstmt.setString(10, collegeName);
            pstmt.setLong(11, bean.getCourseId());
            pstmt.setString(12, bean.getCourseName());
            pstmt.setLong(13, bean.getSubjectId());
            pstmt.setString(14, bean.getSubjectName());
            pstmt.setString(15, bean.getCreatedBy());
            pstmt.setString(16, bean.getModifiedBy());
            pstmt.setTimestamp(17, bean.getCreatedDatetime());
            pstmt.setTimestamp(18, bean.getModifiedDatetime());
            
            pstmt.executeUpdate();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
			}
		log.debug("facultyModel Add method ended");
		
		return pk;
		
	}

	public  FacultyBean findByLoginId(String loginId) throws SQLException
	{
		Connection conn= null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		FacultyBean Bean=null;
	
		try {
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			 StringBuffer sql=new StringBuffer("select * from st_faculty where EMAIL_ID=?");
			 pstm=conn.prepareStatement(sql.toString());
			pstm.setString(1, loginId);
			rs=pstm.executeQuery();
			while(rs.next())
			{
			    Bean = new FacultyBean();
				Bean.setId(rs.getLong(1));
				Bean.setFirstName(rs.getString(2));
				Bean.setLastName(rs.getString(3));
				Bean.setEmailId(rs.getString(4));
				Bean.setDoj(rs.getDate(5));
				Bean.setMobileNo(rs.getString(6));
				Bean.setCollegeName(rs.getString(7));
				Bean.setCollegeId(rs.getLong(8));
				Bean.setSubjectName(rs.getString(9));
				Bean.setSubjectId(rs.getLong(10));
				Bean.setCreatedBy(rs.getString(11));
				Bean.setModifiedBy(rs.getString(12));
				Bean.setCreatedDatetime(rs.getTimestamp(13));
				
				Bean.setModifiedDatetime(rs.getTimestamp(14));
				return Bean;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			pstm.close();
			conn.commit();
			JDBCDataSource.closeConnection(conn);
		}
		return Bean;
		
}
	
	/**
	 * Delete.
	 *
	 * @param bean the bean
	 */
	public void Delete(FacultyBean  bean) {
		log.debug("Facultymodel Delete method started");
		Connection conn=null;
		StringBuffer sql= new StringBuffer("DELETE from st_faculty where ID=?");
		
		
		try {
			conn=JDBCDataSource.getConnection();
		    PreparedStatement pstmt= conn.prepareStatement(sql.toString());
		     pstmt.setLong(1, bean.getId());
		     
		     
		     pstmt.executeUpdate();
		     System.out.println("FacultyModel Data Deleted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Facultymodel Deletemethod ended");
		
		
		
	}
	
	/**
	 * Update.
	 *
	 * @param bean the bean
	 */
	public void update(FacultyBean bean){
		log.debug("FacultyModel UpdateMethod started");
		System.out.println("FacultyModel UpdateMethod started");
		
		
		Connection conn=null;
		StringBuffer sql= new StringBuffer("Update st_faculty set FIRST_NAME=?,LAST_NAME=?,GENDER=?,DOJ=?,QUALIFICATION=?,EMAIL_ID=?,MOBILE_NO=?,COLLEGE_ID=?,COLLEGE_NAME=?,COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATE_TIME=?,MODIFIED_DATE_TIME=? where ID=?");
		
		
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getGender());
			pstmt.setDate(4, new java.sql.Date(bean.getDoj().getTime()));
	        pstmt.setString(5, bean.getQualification());
	        pstmt.setString(6, bean.getEmailId());
	        pstmt.setString(7, bean.getMobileNo());
            pstmt.setLong(8,bean.getCollegeId());
            pstmt.setString(9, bean.getCollegeName());
            pstmt.setLong(10, bean.getCourseId());
            pstmt.setString(11, bean.getCourseName());
            pstmt.setLong(12, bean.getSubjectId());
            pstmt.setString(13, bean.getSubjectName());
            pstmt.setString(14, bean.getCreatedBy());
            pstmt.setString(15, bean.getModifiedBy());
            pstmt.setTimestamp(16, bean.getCreatedDatetime());
            pstmt.setTimestamp(17, bean.getModifiedDatetime());
            pstmt.setLong(18, bean.getId());
            
            
            pstmt.executeUpdate();
					System.out.println("FacultyModel Data Updated");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("facultyModel UpdateMethod ended");
		
	}
	
	/**
	 * Find byemail id.
	 *
	 * @param emailId the email id
	 * @return the faculty bean
	 */
	public FacultyBean findByemailId(String emailId){
		
		log.debug("FacultyModel findByEmail started");
		
		Connection conn=null;
		StringBuffer sql=new StringBuffer("Select * from st_faculty where EMAIL_ID=?");
		FacultyBean bean=null;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, emailId );
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				bean= new FacultyBean();
				bean.setId(rs.getLong(1));
			    bean.setFirstName(rs.getString(2));
			    bean.setLastName(rs.getString(3));
			    bean.setGender(rs.getString(4));
			    bean.setDoj(rs.getDate(5));
			    bean.setQualification(rs.getString(6));
			    bean.setEmailId(rs.getString(7));
			    bean.setMobileNo(rs.getString(8));
			    bean.setCollegeId(rs.getLong(9));
			    bean.setCollegeName(rs.getString(10));
			    bean.setCourseId(rs.getLong(11));
			    bean.setCourseName(rs.getString(12));
			    bean.setSubjectId(rs.getLong(13));
			    bean.setSubjectName(rs.getString(14));
			    bean.setCreatedBy(rs.getString(15));
			    bean.setModifiedBy(rs.getString(16));
			    bean.setCreatedDatetime(rs.getTimestamp(17));
			    bean.setModifiedDatetime(rs.getTimestamp(18));
			    	}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
		
	}
	
	/**
	 * Find by pk.
	 *
	 * @param pk the pk
	 * @return the faculty bean
	 */
	public FacultyBean findByPk(long pk){
		log.debug("FacultyModel findByPk method started");
		
		Connection conn=null;
		StringBuffer sql=new StringBuffer("select * from st_faculty where ID=?");
		FacultyBean bean=null;
		try {
			conn=JDBCDataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk );
			
           ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				bean= new FacultyBean();
				bean.setId(rs.getLong(1));
			    bean.setFirstName(rs.getString(2));
			    bean.setLastName(rs.getString(3));
			    bean.setGender(rs.getString(4));
			    bean.setDoj(rs.getDate(5));
			    bean.setQualification(rs.getString(6));
			    bean.setEmailId(rs.getString(7));
			    bean.setMobileNo(rs.getString(8));
			    bean.setCollegeId(rs.getLong(9));
			    bean.setCollegeName(rs.getString(10));
			    bean.setCourseId(rs.getLong(11));
			    bean.setCourseName(rs.getString(12));
			    bean.setSubjectId(rs.getLong(13));
			    bean.setSubjectName(rs.getString(14));
			    bean.setCreatedBy(rs.getString(15));
			    bean.setModifiedBy(rs.getString(16));
			    bean.setCreatedDatetime(rs.getTimestamp(17));
			    bean.setModifiedDatetime(rs.getTimestamp(18));
			    	}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bean ;
		
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
	public List search(FacultyBean bean, int pageNo , int pageSize) throws SQLException{
		 log.debug("search debug started");
		  List list=new ArrayList(); 
		 Connection conn=null;
		 PreparedStatement pstmt=null;
			
			StringBuffer sql=new StringBuffer("select * from st_faculty where 1=1");  ///dynamic injection return true value and at run time it will provide attribute to append with the query 
			if(bean!=null)
			{
				if(bean.getId()>0)
				{
					sql.append(" and ID= " +bean.getId());
				}
				if(bean.getFirstName()!=null && bean.getFirstName().length()>0)
				{
				//System.out.println("first name of fac in model"+Bean.getFirstName());
			    sql.append(" and first_name like '"+bean.getFirstName()+"%'");
				}
				if(bean.getLastName()!=null && bean.getLastName().length()>0)
				{
					sql.append(" and last_name like '"+bean.getLastName()+"%'");
				}
				if(bean.getGender()!=null && bean.getGender().length()>0)
				{
					sql.append(" and gender like '"+bean.getGender()+"%'");
				}
				if(bean.getDoj()!=null && bean.getDoj().getDate()>0)
				{
					sql.append(" and doj = " + bean.getDoj());
					System.out.println(sql);
				}
				if(bean.getQualification()!=null && bean.getQualification().length()>0)
				{
					sql.append(" and qualification  like '"+bean.getQualification()+"%'");
				}
				if(bean.getEmailId()!=null && bean.getEmailId().length()>0)
				{
				sql.append(" and EMAIL_ID like '"+bean.getEmailId()+"%'");
				}
				if(bean.getMobileNo()!=null && bean.getMobileNo().length()>0)
				{
					sql.append(" and mobile_no like '"+bean.getMobileNo()+"%'");
				}
				if(bean.getCollegeName()!=null && bean.getCollegeName().length()>0)
				{
					sql.append(" and college_name like '"+bean.getCollegeName()+"%'");
				}
				if(bean.getCollegeId()>0)
				{
					sql.append(" and college_id="+bean.getCollegeId());
				}
				if(bean.getCourseId()>0)
				{
					
					sql.append(" and course_id="+bean.getCourseId());
				}
				if(bean.getCourseName()!=null && bean.getCourseName().length()>0)
				{
					sql.append(" and course_name like '"+bean.getCourseName()+"%'");
				}
				if(bean.getSubjectName()!=null && bean.getSubjectName().length()>0)
				{
					sql.append(" and subject_name like '"+bean.getSubjectName()+"%'");
				}
				if(bean.getSubjectId()>0)
				{
					sql.append(" and subject_id="+bean.getSubjectId());
				}
				System.out.println("facultyModel sql:"+ sql);
				
			}
			if(pageSize>0)
			{
				pageNo=(pageNo-1)*pageSize;         
				sql.append(" limit "+pageNo+","+pageSize);
			}
			
			try 
			{
			
			ResultSet rs=null;
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql.toString());
		    rs=pstmt.executeQuery();
		    bean=null;
			while(rs.next())
			{
				bean= new FacultyBean();
				bean.setId(rs.getLong(1));
			    bean.setFirstName(rs.getString(2));
			    bean.setLastName(rs.getString(3));
			    bean.setGender(rs.getString(4));
			    bean.setDoj(rs.getDate(5));
			    bean.setQualification(rs.getString(6));
			    bean.setEmailId(rs.getString(7));
			    bean.setMobileNo(rs.getString(8));
			    bean.setCollegeId(rs.getLong(9));
			    bean.setCollegeName(rs.getString(10));
			    bean.setCourseId(rs.getLong(11));
			    bean.setCourseName(rs.getString(12));
			    bean.setSubjectId(rs.getLong(13));
			    bean.setSubjectName(rs.getString(14));
			    bean.setCreatedBy(rs.getString(15));
			    bean.setModifiedBy(rs.getString(16));
			    bean.setCreatedDatetime(rs.getTimestamp(17));
			    bean.setModifiedDatetime(rs.getTimestamp(18));
		     
			    list.add(bean);
		}
			
			} 
			catch (Exception e) 
			{
			e.printStackTrace();
			}
			finally 
			{
				pstmt.close();
		        conn.commit();
				JDBCDataSource.closeConnection(conn);
			}
	log.debug("search debug completed");
	//System.out.println("hi fac model "+list.size());
		return list;
		
	}
	
	/**
	 * List.
	 *
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 */
	public List list(int pageNo, int pageSize ) {
      
		Connection conn= null;
		PreparedStatement pstmt=null;
		List list=new ArrayList();
		StringBuffer sql= new StringBuffer("select * from st_faculty");
		FacultyBean bean=null;
		if(pageSize>0){
			pageNo=(pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+","+pageSize);
			
		}
		
		 try {
			conn= JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
	        pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs= pstmt.executeQuery();
			
			while(rs.next())
			{
				bean= new FacultyBean();
				bean.setId(rs.getLong(1));
			    bean.setFirstName(rs.getString(2));
			    bean.setLastName(rs.getString(3));
			    bean.setGender(rs.getString(4));
			    bean.setDoj(rs.getDate(5));
			    bean.setQualification(rs.getString(6));
			    bean.setEmailId(rs.getString(7));
			    bean.setMobileNo(rs.getString(8));
			    bean.setCollegeId(rs.getLong(9));
			    bean.setCollegeName(rs.getString(10));
			    bean.setCourseId(rs.getLong(11));
			    bean.setCourseName(rs.getString(12));
			    bean.setSubjectId(rs.getLong(13));
			    bean.setSubjectName(rs.getString(14));
			    bean.setCreatedBy(rs.getString(15));
			    bean.setModifiedBy(rs.getString(16));
			    bean.setCreatedDatetime(rs.getTimestamp(17));
			    bean.setModifiedDatetime(rs.getTimestamp(18));
		     
			    list.add(bean);
		} 
		 }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		   JDBCDataSource.closeConnection(conn);
		}
		 
		return list;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
