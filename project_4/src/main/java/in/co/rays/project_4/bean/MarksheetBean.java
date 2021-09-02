package in.co.rays.project_4.bean;

/**
 * The Class MarksheetBean.
 */
public class MarksheetBean extends BaseBean {
	
	/** The roll no. */
	private String rollNo;
	
	/** The student id. */
	private long studentId;
	
	/** The name. */
	private String name;
	
	/** The physics. */
	private int physics;
	
	/** The chemistry. */
	private int chemistry;
	
	/** The maths. */
	private int maths;
	
	/**
	 * Gets the roll no.
	 *
	 * @return the roll no
	 */
	public String getRollNo() {
		return rollNo;
	}
	
	/**
	 * Sets the roll no.
	 *
	 * @param rollNo the new roll no
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
	/**
	 * Gets the student id.
	 *
	 * @return the student id
	 */
	public long getStudentId() {
		return studentId;
	}
	
	/**
	 * Sets the student id.
	 *
	 * @param studentId the new student id
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
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
	 * Gets the physics.
	 *
	 * @return the physics
	 */
	public int getPhysics() {
		return physics;
	}
	
	/**
	 * Sets the physics.
	 *
	 * @param physics the new physics
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}
	
	/**
	 * Gets the chemistry.
	 *
	 * @return the chemistry
	 */
	public int getChemistry() {
		return chemistry;
	}
	
	/**
	 * Sets the chemistry.
	 *
	 * @param i the new chemistry
	 */
	public void setChemistry(int i) {
		this.chemistry = i;
	}
	
	/**
	 * Gets the maths.
	 *
	 * @return the maths
	 */
	public int getMaths() {
		return maths;
	}
	
	/**
	 * Sets the maths.
	 *
	 * @param maths the new maths
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return id+" ";
	}
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return rollNo;
	}
	
	

}
