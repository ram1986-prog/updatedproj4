package in.co.rays.project_4.test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.model.SubjectModel;

/**
 * The Class SubjectModelTest.
 */
public class SubjectModelTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws SQLException the SQL exception
	 */
	public static void main(String[] args) throws SQLException {
		// testAdd();
		// testDelete();
		 //testUpdate();
		  testFindBySubjectName();
		 //testFindBYPk();
		// testSearch();
	//	testList();
	}

	/**
	 * Test add.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void testAdd() throws SQLException {

		SubjectBean bean = new SubjectBean();
		bean.setCourseName("MTech");
		bean.setCourseId(2L);
		bean.setSubjectName("EC");
		bean.setSubjectId(401L);
		bean.setDescription("good ");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);

		SubjectModel model = new SubjectModel();
		model.add(bean);
	}

	/**
	 * Test delete.
	 */
	public static void testDelete() {
		SubjectBean bean = new SubjectBean();
		bean.setId(1L);
		SubjectModel model = new SubjectModel();
		model.delete(bean);

	}

	/**
	 * Test update.
	 */
	public static void testUpdate() {
		SubjectBean bean = new SubjectBean();
		bean.setId(1l);
		bean.setCourseName("BE");
		bean.setCourseId(1L);
		bean.setSubjectName("EC");
		bean.setSubjectId(402L);
		bean.setDescription("easy");
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);

		SubjectModel model = new SubjectModel();
		model.Update(bean);

	}

	/**
	 * Test find by subject name.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void testFindBySubjectName() throws SQLException {
		SubjectBean bean = new SubjectBean();

		SubjectModel model = new SubjectModel();
		bean = model.findBySubjectName("history");

		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getSubjectName());
		System.out.println(bean.getSubjectId());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	/**
	 * Test find BY pk.
	 */
	public static void testFindBYPk() {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		bean = model.findByPk(1L);

		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getSubjectName());
		System.out.println(bean.getSubjectId());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	/**
	 * Test search.
	 *
	 * @throws SQLException the SQL exception
	 */
	public static void testSearch() throws SQLException {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		List list = new ArrayList();

		bean.setCourseName("Aerodynamics");

		list = model.search(bean, 0, 0);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getDescription());
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
	public static void testList() throws SQLException {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		List list = new ArrayList();

		list = model.list(0, 0);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			/*System.out.println(bean.getId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getSubjectId());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
*/
			System.out.println(bean.getSubjectName());
		}
	}

}
