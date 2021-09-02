package in.co.rays.project_4.bean;

/**
 * The Class RoleBean.
 */
public class RoleBean extends BaseBean {
	
	/** The Constant ADMIN. */
	public static final int ADMIN = 1;
	
	/** The Constant STUDENT. */
	public static final int STUDENT = 2;
	
	/** The Constant COLLEGE_SCHOOL. */
	public static final int COLLEGE = 4;

	public static final int FACULTY=3;
	/** The Constant KIOSK. */
	public static final int KIOSK = 5;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the Value
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return name+"";
	}

}
