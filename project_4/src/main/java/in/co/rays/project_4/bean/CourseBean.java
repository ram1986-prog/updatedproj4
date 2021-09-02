package in.co.rays.project_4.bean;

/**
 * The Class CourseBean.
 */
public class CourseBean extends BaseBean{
	
	/** The course name. */
	private String courseName;
	
	/** The description. */
	private String description;
	
	
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 
	 *
	 * GetKey() 
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	
	/**
	 * 
	 *
	 * GetValue() 
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return courseName+"";
	}
	

}
