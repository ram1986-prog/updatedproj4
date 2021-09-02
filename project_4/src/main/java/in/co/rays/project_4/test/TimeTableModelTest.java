package in.co.rays.project_4.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.TimeTableBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.TimeTableModel;

/**
 * The Class TimeTableModelTest.
 */
public class TimeTableModelTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		//testAdd();
		//testDelete();
		//testFindByCourseNameSubjectName();
		//testfindByCourseNameSubjectNameExamDateExamTime();
		//testFindByPk();
		//testUpdate();
		testSearch();
		//testList();
		
	}
	
	
	
	/**
	 * Test add.
	 *
	 * @throws Exception the exception
	 */
	public static void testAdd() throws Exception{

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		java.util.Date date = sdf.parse("06/08/1996");
try{
		TimeTableBean bean=new TimeTableBean();
		bean.setCourseName("Mechanical Engineering");
		bean.setCourseId(7);
		bean.setSubjectName("Basics of Electronics Engineering");
		bean.setSubjectId(95);
		bean.setExamDate(date);
		bean.setExamTime("8 august");
		bean.setSemester("6");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
        bean.setModifiedDatetime(new Timestamp(new Date().getTime()));	
        
        TimeTableModel model =new TimeTableModel();
       long pk= model.add(bean);
       TimeTableBean addbean=model.findByPk(pk);
       if(addbean==null){
    	   System.out.println("fAIL");
       }
}catch(ApplicationException e){
    	   e.printStackTrace();
    	   
       }


	}

	/**
	 * Test delete.
	 *
	 * @throws SQLException the SQL exception
	 * @throws ApplicationException the application exception
	 */
	public static void testDelete() throws SQLException, ApplicationException{
		TimeTableBean bean=new TimeTableBean();
		bean.setId(2L);
		TimeTableModel model=new TimeTableModel();
		model.delete(bean);
	}
	
	/**
	 * Test find by course name subject name.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void testFindByCourseNameSubjectName() throws SQLException{
	            TimeTableBean bean=new TimeTableBean();
	            
	            TimeTableModel model=new TimeTableModel();
	            bean=model.FindByCourseNameSubjectName("BE", "EC");
	            System.out.println(bean.getId());
	            System.out.println(bean.getCourseId());
	            System.out.println(bean.getCourseName());
	            System.out.println(bean.getSubjectId());
	            System.out.println(bean.getSubjectName());
	            System.out.println(bean.getExamDate());
	            System.out.println(bean.getExamTime());
	            System.out.println(bean.getCreatedBy());
	            System.out.println(bean.getModifiedBy());
	            System.out.println(bean.getCreatedDatetime());
	            System.out.println(bean.getModifiedDatetime());
	}
	
	
	/**
	 * Testfind by course name subject name exam date exam time.
	 *
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 */
	public static void testfindByCourseNameSubjectNameExamDateExamTime() throws SQLException, ParseException{
	try{	TimeTableBean bean=new TimeTableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = sdf.parse("06/08/1996");
        
        TimeTableModel model=new TimeTableModel();
        bean=model.findByCourseNameSubjectNameExamDateExamTime("BE", "EC", date, "10 PM");
       // System.out.println(bean.getId());
        System.out.println(bean.getCourseId());
        System.out.println(bean.getCourseName());
        System.out.println(bean.getSubjectId());
        System.out.println(bean.getSubjectName());
        System.out.println(bean.getExamDate());
        System.out.println(bean.getExamTime());
        System.out.println(bean.getCreatedBy());
        System.out.println(bean.getModifiedBy());
        System.out.println(bean.getCreatedDatetime());
        System.out.println(bean.getModifiedDatetime());
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	/**
	 * Test find by pk.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void testFindByPk() throws SQLException{
		TimeTableBean bean=new TimeTableBean();
        
        TimeTableModel model=new TimeTableModel();
        bean=model.findByPk(1l);
        System.out.println(bean.getId());
        System.out.println(bean.getCourseId());
        System.out.println(bean.getCourseName());
        System.out.println(bean.getSubjectId());
        System.out.println(bean.getSubjectName());
        System.out.println(bean.getExamDate());
        System.out.println(bean.getExamTime());
        System.out.println(bean.getCreatedBy());
        System.out.println(bean.getModifiedBy());
        System.out.println(bean.getCreatedDatetime());
        System.out.println(bean.getModifiedDatetime());

		
	}
	
	/**
	 * Test update.
	 *
	 * @throws SQLException the SQL exception
	 * @throws ParseException the parse exception
	 */
	public static void testUpdate() throws SQLException, ParseException{
		TimeTableBean bean=new TimeTableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = sdf.parse("06/08/1996");

		
		bean.setCourseName("mca");
		bean.setCourseId(101);
		bean.setSubjectName("SoftwareSystem");
		bean.setSubjectId(404);
		bean.setExamDate(date);
		bean.setExamTime("8 august");
		bean.setSemester("4");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
        bean.setModifiedDatetime(null);	
        bean.setId(2L);
        TimeTableModel model =new TimeTableModel();
        model.update(bean);
		
	}
	
	/**
	 * Test search.
	 *
	 * @throws SQLException the SQL exception
	 * @throws ParseException 
	 */
	public static void testSearch() throws SQLException, ParseException{
		
		TimeTableBean bean=new TimeTableBean();
		TimeTableModel model =new TimeTableModel();
		List list=new ArrayList();
		
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
Date date =sdf.parse("1996-06-08");
		bean.setExamDate(date);
	//	bean.setId(8);
		
		//bean.setCourseName("Mechanical Engineering");
		list=model.search(bean,0,0);
		
		if(list==null){
			System.out.println("incorrect date");
		}
		  Iterator it=list.iterator();
		  while(it.hasNext()){
			  bean=(TimeTableBean) it.next();
			  System.out.println(bean.getId());
		        System.out.println(bean.getCourseId());
		        System.out.println(bean.getCourseName());
		        System.out.println(bean.getSubjectId());
		        System.out.println(bean.getSubjectName());
		        System.out.println(bean.getExamDate());
		        System.out.println(bean.getExamTime());
		        System.out.println(bean.getCreatedBy());
		        System.out.println(bean.getModifiedBy());
		        System.out.println(bean.getCreatedDatetime());
		        System.out.println(bean.getModifiedDatetime());
		  }
	}
	
	/**
	 * Test list.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void testList() throws SQLException{
		TimeTableBean bean=new TimeTableBean();
		TimeTableModel model =new TimeTableModel();
		List list=new ArrayList();

		list=model.list(0, 0);
		  Iterator it=list.iterator();
		  while(it.hasNext()){
			  bean=(TimeTableBean) it.next();
			  System.out.println(bean.getId());
		        System.out.println(bean.getCourseId());
		        System.out.println(bean.getCourseName());
		        System.out.println(bean.getSubjectId());
		        System.out.println(bean.getSubjectName());
		        System.out.println(bean.getExamDate());
		        System.out.println(bean.getExamTime());
		        System.out.println(bean.getCreatedBy());
		        System.out.println(bean.getModifiedBy());
		        System.out.println(bean.getCreatedDatetime());
		        System.out.println(bean.getModifiedDatetime());
		  }

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
