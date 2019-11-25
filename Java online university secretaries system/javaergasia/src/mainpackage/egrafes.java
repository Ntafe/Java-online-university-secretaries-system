package mainpackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class egrafes {
		public static final String SALT = "my-salt-text";


	
	public String egfoit(String username, String password){
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		String showThisStudentStatement = "INSERT INTO STUDENTS (RegistrationNumber, Name,Surname,Department,password) VALUES ('"+username+"',?,?,?,'"+ hashedPassword +"')";
		return(showThisStudentStatement);
	}
	
	public String egkath(String username, String password){
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		String insertStudentStatement = "INSERT INTO PROFESSORS (AFM, Name,Surname,Department,password) VALUES ('"+username+"',?,?,?,'"+ hashedPassword +"')";
		return(insertStudentStatement);
	}
	
	public String eggram(String username, String password){
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		String insertStudentStatement = "INSERT INTO SECRETARIES (sec_id, password) VALUES ('"+username+"','"+hashedPassword+"')";
		return(insertStudentStatement);
	}
	


	public String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			
		}

		return hash.toString();
	}
}
