package in.co.rays.project_4.util;

import java.text.ParseException;


import java.util.Date;

/**
 * The Class DataValidator.
 */
public class DataValidator {

	/**
	 * Checks if is name.
	 *
	 * @param name the name
	 * @return true, if is name
	 */
	public static boolean isName(String name) { 

		String namereg = "^[^-\\s][\\p{L} .']+$";
		

		//String sname = name.trim();

		if (isNotNull(name) && name.matches(namereg)) {

			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if value of Password is in between 8 and 12 characters.
	 *
	 * @param val the val
	 * @return true, if is password length
	 */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
			
			return true;
		} else {
			return false;
		}
	}
	
	
	/*public static boolean isValidAge(String val)
	{
		
		boolean pass = false;
		if (isDate(val)) {
			Date cdate = new Date();
			try {
				Date userdate = DataUtility.formatter.parse(val);
				int age = cdate.getYear()-userdate.getYear();
				System.out.println("final age  "+age);
				if(age>=18){
					pass=true;
				}
			} catch (ParseException e) {
				
			}
		}
		
		return pass;
	}*/
	
	/**
	 * Checks if is password.
	 *
	 * @param pass the pass
	 * @return true, if is password
	 */
	public static boolean isPassword(String pass) { // my method created
        
		System.out.println("validate pass");
		String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		//String passreg="^[0-9a-zA-Z]{5}$";
		//String spass = pass.trim();
		//int checkIndex = spass.indexOf(" ");
                                //checkIndex==-1
		if (isNotNull(pass) && pass.matches(passreg) ) {
			System.out.println("true");
			return true;
		}

		else {
			return false;
		}
	}
	
	/**
	 * Checks if is roll no.
	 *
	 * @param roll the roll
	 * @return true, if is roll no
	 */
	public static boolean isRollNo(String roll) { // my method created
	
		String rollreg = "[a-zA-Z]{2}[0-9]{4}";
		//String sroll = roll.trim();

		if (DataValidator.isNotNull(roll)) {

			boolean check = roll.matches(rollreg);
			System.out.println(check);
			return check;
		}

		else {

			return false;
		}
	}
	
	/**
	 * Ckeck if value is Null.
	 *
	 * @param val :val
	 * @return boolean
	 */
 
	public static boolean isNull(String val)
	{
		if(val==null || val.trim().length()==0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	/**
	 * check if value is Not Null.
	 *
	 * @param val :value
	 * @return boolean
	 */
     
	public static boolean isNotNull(String val)
	{
		return !isNull(val);
	}
	
	/**
	 * check if an value is an Integer.
	 *
	 * @param val :value
	 * @return boolean
	 */
	 public static boolean isInteger(String val)
	 {
		 if(isNotNull(val))
		 {
			 try {
				 int i = Integer.parseInt(val);
				 return true;
			} catch (Exception e) {
				return false;
			}
		 }else
		 {
			 return false;
		 }
	 }
	 
 	/**
 	 * check if an value is an Long.
 	 *
 	 * @param val :value
 	 * @return boolean
 	 */
		 public static boolean isLong(String val)
		 {
			 if(isNotNull(val))
			 {
				 try {
					 long l = Long.parseLong(val);
					 return true;
				} catch (Exception e) {
					return false;
				}
			 }else
			 {
				 return false;
			 }
		 }
	 
 	/**
 	 * Check if value is valid EmailId.
 	 *
 	 * @param val :val
 	 * @return boolean
 	 */
	 public static boolean isEmail(String val)
	 {
		 String emailregex= "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	  
		 if(isNotNull(val))
		 {
			 try {
				 return val.matches(emailregex);
			} catch (Exception e) {
				return false;
			}	 
		 }else
		 {
			 return false;
		 } 
	 }
	 
 	/**
 	 * check if value is date.
 	 *
 	 * @param val :val
 	 * @return boolean
 	 */
	   public static boolean isDate(String val)
	   {
		    Date d=null;
//		    String s = val;
//			s = s.replace("-", "/");
		    if(isNotNull(val))
		    {
		    d =	DataUtility.getDate(val);
		    }
		    return d!=null;
	   }
	   
	   /**
   	 * Checks if is valid age.
   	 *
   	 * @param val the val
   	 * @return true, if is valid age
   	 */
   	public static boolean isValidAge(String val)
		{
			
			boolean pass = false;
			if (isDate(val)) {
				Date cdate = new Date();
				try {
					Date userdate = DataUtility.formatter.parse(val);
					int age = cdate.getYear()-userdate.getYear();
					System.out.println("final age  "+age);
					if(age>=18){
						pass=true;
					}
				} catch (ParseException e) {
					
				}
			}
			
			return pass;
		}
/**
	   /**
		 * Checks if value is valid Phone No.
		 * 
		 * @param val :val
		 * @return boolean
		 */
		public static boolean isPhoneNo(String val) {

			String phonereg = "^[6-9][0-9]{9}$";
//			String phonereg = "^[6-9]{10}$";

			if (isNotNull(val)) {
				try {
					return val.matches(phonereg);
				} catch (NumberFormatException e) {
					return false;
				}

			} else {
				return false;
			}
		}

		/**
		 * Checks if value of Mobile No is 10.
		 *
		 * @param val :value
		 * @return boolean
		 */
		public static boolean isPhoneLength(String val) {

			if (isNotNull(val) && val.length() == 10) {
				return true;
			} else {
				return false;
			}
		}
	   
	   
	 
	 /**
 	 * Test Above Methods.
 	 *
 	 * @param args :args
 	 */
	public static void main(String[] args) 
	{
	  //System.out.println(isNull(" "));
	//  System.out.println(isNotNull("aaaaa"));//dought
  //System.out.println(isInteger("2147483640"));
	 // System.out.println(isLong("5.222"));
	 // System.out.println(isEmail("ram@gmail.com"));
	 // System.out.println(isDate("18/11/2019"));
	  //System.out.println(isName("Ram sharma"));
		//System.out.println(isPhoneNo("8230456987"));
		//System.out.println(isPhoneLength("7410852963"));
		//System.out.println(isPassword("sA123444$"));
		//System.out.println(isRollNo("rr1111"));
	
	}
	   
}
