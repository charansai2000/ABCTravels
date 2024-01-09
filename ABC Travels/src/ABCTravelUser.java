import java.util.Scanner;
import java.util.regex.Pattern;

public class ABCTravelUser {
	private String firstname;
	private String lastname;
	private String mobileNumber;
	private char gender;
	private String gmail;
	private String password;
	private int failedcount;
	private String accountStatus;
	
	//Getters
	public String getName() {
		return this.firstname +" "+this.lastname;
	}
	public String getMobileNumber() {
		return this.mobileNumber;
	}
	public String getGender() {
		if(this.gender=='M' || this.gender=='m') {
			return "Male";
		}
		else {
			return "Female";
		}
	}
	public String getGmail() {
		return this.gmail;
	}
	public String getPassword() {
		return this.password;
	}
	public int getFailedAttempts() {
		return this.failedcount;
	}
	public String getAccountStatus() {
		return this.accountStatus;
	}
	
	//Setters
	
	public void setFirstName(String firstname) {
		this.firstname=firstname;
	}
	public void setLastName(String lastname) {
		this.lastname=lastname;
	}
	public void setMobileNumber(String number) {
		if(number.length()==10) {
			this.mobileNumber=number;
		}
		else {
			System.out.println("Please enter the valid mobile number");
			Scanner sc=new Scanner(System.in);
			String newNumber=sc.nextLine();
			setMobileNumber(newNumber);
			//sc.close();
		}
	}
	public void setGender(char gender) {
		if(gender=='M' || gender=='m' || gender=='F' || gender=='f') {
			this.gender=gender;
		}
		else {
			System.out.println("Please enter \"M\" if you are a male");
			System.out.println("Please enter \"F\" if you are a female");
			Scanner sc=new Scanner(System.in);
			char newchar=sc.next().charAt(0);
			setGender(newchar);
			//sc.close();
		}
	}
	public void setGmail(String gmail) {
		if(Pattern.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", gmail)) {
			this.gmail=gmail;
		}
		else {
			System.out.println("please enter a valid email id");
			Scanner sc=new Scanner(System.in);
			String newemail=sc.nextLine();
			setGmail(newemail);
			//sc.close();
		}
	}
	public void setPassword(String password) {
		if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password)) {
			this.password=password;
		}
		else {
			System.out.println("Please enter the valid password with a special charecter and minimum 8 charecters");
			Scanner sc=new Scanner(System.in);
			String newPass=sc.nextLine();
			setPassword(newPass);
			//sc.close();
		}
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedcount=failedAttempts;
	}
	public void setStatus() {
		if(this.failedcount>=3) {
			this.accountStatus="Blocked";
		}
		else if(this.failedcount==2) {
			this.accountStatus="Active one attempt left";
		}
		else if(this.failedcount==1) {
			this.accountStatus="Active two attempts left";
		}
		else {
			this.accountStatus="Active";
		}
	}
	

}
