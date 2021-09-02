package in.co.rays.project_4.bean;

import java.util.Date;

/**
 * The Class StudentBean.
 */
public class StudentBean extends BaseBean {
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The dob. */
	private Date dob;
	
	/** The mobile no. */
	private String mobileNo;
   
   /** The email. */
   private String email;
   
   /** The college id. */
   private long collegeId;
   
   /** The college name. */
   private String collegeName;

/**
 * Gets the first name.
 *
 * @return the first name
 */
public String getFirstName() {
	return firstName;
}

/**
 * Sets the first name.
 *
 * @param firstName the new first name
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

/**
 * Gets the last name.
 *
 * @return the last name
 */
public String getLastName() {
	return lastName;
}

/**
 * Sets the last name.
 *
 * @param lastName the new last name
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}

/**
 * Gets the dob.
 *
 * @return the dob
 */
public Date getDob() {
	return dob;
}

/**
 * Sets the dob.
 *
 * @param dob the new dob
 */
public void setDob(Date dob) {
	this.dob = dob;
}

/**
 * Gets the mobile no.
 *
 * @return the mobile no
 */
public String getMobileNo() {
	return mobileNo;
}

/**
 * Sets the mobile no.
 *
 * @param mobileNo the new mobile no
 */
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}

/**
 * Gets the email.
 *
 * @return the email
 */
public String getEmail() {
	return email;
}

/**
 * Sets the email.
 *
 * @param email the new email
 */
public void setEmail(String email) {
	this.email = email;
}

/**
 * Gets the college id.
 *
 * @return the college id
 */
public long getCollegeId() {
	return collegeId;
}

/**
 * Sets the college id.
 *
 * @param collegeId the new college id
 */
public void setCollegeId(long collegeId) {
	this.collegeId = collegeId;
}

/**
 * Gets the college name.
 *
 * @return the college name
 */
public String getCollegeName() {
	return collegeName;
}

/**
 * Sets the college name.
 *
 * @param collegeName the new college name
 */
public void setCollegeName(String collegeName) {
	this.collegeName = collegeName;
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
 * Gets the Value.
 *
 * @return the Value
 */
public String getValue() {
	// TODO Auto-generated method stub
	return firstName+""+lastName;
}
   
   

}
