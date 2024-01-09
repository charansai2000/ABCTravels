package com.ABCTravels;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main implements Comparator<Locations>{
	//static Scanner sc=new Scanner(System.in);
    static ABCTravelUser user1=new ABCTravelUser();
    static Locations location=new Locations();
    static Locations location2=new Locations();
    static Locations location3=new Locations();
    static Locations location4=new Locations();
    static Locations userinput=new Locations();
    static LocalDate traveldate;
    static LocalDate date2;       
	static Map<String,ABCTravelUser> map=new HashMap<>();
	static Map<String,Locations> loc=new HashMap<>();
	static Set<Locations> sourceDest=new HashSet<>();
 	static int exitPointer=1;
	public static void main(String[] args) throws IOException {
		location.source="Vijayawada";
	    location.destination="Tiruvuru";
	    location.cost=399;
	    location2.source="Vijayawada";
	    location2.destination="Vizag";
	    location2.cost=1999;
	    location3.source="vizag";
	    location3.destination="Machilipatnam";
	    location3.cost=799;
	    location4.source="Amaravathi";
	    location4.destination="Guntur";
	    location4.cost=499;
	    sourceDest.add(location);
	    sourceDest.add(location2);
	    sourceDest.add(location3);
	    sourceDest.add(location4);
	    loc.put(location.source, location);
	    loc.put(location2.source, location2);
	    loc.put(location3.source, location3);
	    loc.put(location4.source, location4);
		travels();
		}
	public static void travels() throws IOException {
		Scanner sc=new Scanner(System.in);
		FileInputStream fs=new FileInputStream("banner.txt");
		int i=0;
		while((i=fs.read())!=-1) {
			
			System.out.print((char)i);
		}
		fs.close();
		while(exitPointer!=0) {
			System.out.println();
			System.out.println("Enter 1 for Registration");
			System.out.println("Enter 2 for Login");
			System.out.println("Enter 3 for Travel");
			System.out.println("Enter 4 for Travel Date Modifications");
			
			int option=sc.nextInt();
			switch(option) {
			case 1:
				registration();
				break;
			case 2:
				logIn();
				break;
			case 3:
				travelBooking();
				break;
			case 4:
				dateModification();
				break;
			}
		}
		
	}
	public static void registration() {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Welcome to ABC Travels Registration page");
		
		System.out.println("Please enter your first name");
		String fname=sc.nextLine();
		user1.setFirstName(fname);
		System.out.println("Please enter your Last name");
		String lastname=sc.nextLine();
		user1.setLastName(lastname);
		System.out.println("Please enter your 10 digit mobile number");
		user1.setMobileNumber(sc.nextLine());
		
		System.out.println("Please enter your gender ");
		System.out.println("if male enter\"m\"");
		System.out.println("if female enter\"f\"");
		user1.setGender(sc.nextLine().charAt(0));
		System.out.println("Please enter your EmailId");
		String gmail=sc.nextLine();
		user1.setGmail(gmail);
		System.out.println("Please enter your password");
		user1.setPassword(sc.nextLine());
		
		map.put(gmail, user1);

	}
	public static void logIn() throws IOException {
		Scanner sc=new Scanner(System.in);
		int i=0;
		System.out.println("please enter your gmail to login");
		String userGmailInput=sc.nextLine();
		System.out.println("Please enter your password to log in");
		String pass=sc.nextLine();
		if((user1.getGmail().equalsIgnoreCase(userGmailInput) && user1.getPassword().equals(pass))) {
			System.out.println("successfully loggedin");
		}
		else {
			System.out.println("failed to logged in enter valid emailid");
			i++;
			user1.setFailedAttempts(i);
            if(user1.getFailedAttempts()>=3) {
            	System.out.println(user1.getAccountStatus());
            	exitPointer=1;
                travels();
            }
            else {
			logIn();
            }
		}
		
		
	}
	public static void travelBooking() {
	    
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter yors source location");
		String userSource=sc.nextLine();
		userinput.source=userSource;
		System.out.println("Please enter your destination");
		userinput.destination=sc.nextLine();
		System.out.println("Please enter your Travel Date in yyy/mm/dd format ");
		userinput.date=sc.nextLine();
		travelBookingOperation();
		
		
	}
	public static void travelBookingOperation() {
		String[] arr=userinput.date.split("/");
		int year=Integer.parseInt(arr[0]);
		int month=Integer.parseInt(arr[1]);
		int date=Integer.parseInt(arr[2]);
		traveldate=LocalDate.of(year, month, date);
		date2=LocalDate.now();
		double price=0;
		if(traveldate.isBefore(date2)) {
			System.out.println("Please enter a valid date ");
			travelBooking();
		}
		else if(traveldate.isAfter(date2)) {
			Main m=new Main();
			DayOfWeek dayofweek=traveldate.getDayOfWeek();
			int day=dayofweek.getValue();
			int result=-1;
			for(Locations obj : sourceDest) {
				try {
					if(m.compare(obj,userinput)==0) {
						result=0;
						if(day==6 || day==7) {
							//price increase by 200
							
							price=obj.getCost(200);
							
						}
						else {
							//price no increase
							price=obj.getCost(0);
						}
					}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(result!=0) {
				System.out.println("Please enter a valid input ");
				travelBooking();
			}
			System.out.println("The cost of your trip is"+ price);
		}
	}
	public static void dateModification() {
		System.out.println("Please enter the date you want to change yyy/mm/dd");
		Scanner sc=new Scanner(System.in);
		userinput.date=sc.nextLine();
		travelBookingOperation();
		
	}
	@Override
	public int compare(Locations o1, Locations o2) {
	   if(o1.source.equalsIgnoreCase(o2.source)){
		   if(o1.destination.equalsIgnoreCase(o2.destination)) {
			   return 0;
		   }
		   else {
			   return -1;
		   }
	   }
		return -1;
	}
}
