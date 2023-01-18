

import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Event {
	//LIST of USERS
	private int userQuantity = 0;
	private int userNumber = 1;
	HashMap<String, Account> accountList = new HashMap<String, Account>();
	
	private String title;
	private String target;
	private String month;
	private int day;
	private int year;
	private String description;
	private int startHour;
	private int startMinute;
	private int endHour;
	private int endMinute;
	private String period1;
	private String period2;
	private String location;

	//SETTERS
	public void setTitle(String n) {
		title = n;
	}
	public void setTarget(String t) {
		target = t;
	}
	public void setDescription(String d) {
		description = d;
	}
	public void setLocation(String l) {
		location = l;
	}
	public void setTime(int sh, int sm, int p1, int eh, int em, int p2) {
		if(sh<=12&&sh>0)startHour = sh;
		else startHour = 0;
		if(sm<=60&&sm>0)startMinute = sm;
		else startMinute = 0;
		if(eh<=12&&eh>0)endHour = eh;
		else endHour = 0;
		if(em<=60&&em>0)startMinute = em;
		else endMinute = 0;
		if(p1==0) period1 = "AM";
		else period1 = "PM";
		if(p2==0) period2 = "AM";
		else period2 = "PM";
	}
	public void setDate(String m, int d, int y) {
		month = m;
		if(d<=31&&d>0) day=d;
		if(y>=2019) year = y;
	}

	//GETTERS
	public String getTime() {
		return String.format("%02d:%02d %2s - %02d:%02d %2s", startHour, startMinute, period1, endHour, endMinute, period2);
	}	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getLocation() {
		return location;
	}
	public String getDate() {
		return String.format("%s %2d, %4d", month, day, year);
	}
	public String getTarget() {
		return target;
	}
	public String getSignedUp() {
		return String.format("%5d/30", userQuantity);
	}
	
	//TO STRING
	public String toString() {
		return String.format("WHAT: %s%nWHO: %s%nWHEN: %s , %s%nWHERE: %s%n%nDESCRIPTION:%n%s", getTitle(), getTarget(), getDate(), getTime(), getLocation(), getDescription());
	}

	
	
	//SHOW EVENT DETAILS : ADMIN
	public void showDetailsAdmin() {
		
		String[] options = {"Show User List", "Back"};
		int operation = JOptionPane.showOptionDialog(null, toString(), "Event Details", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
		 	
		//SHOW ACCOUNT
		if(operation == 0) showUserList();
	}
	
	//ADDING NEW ACCOUNT : ADMIN
	public void addUser() {
			String Fname = JOptionPane.showInputDialog("Enter First Name");
			String Lname = JOptionPane.showInputDialog("Enter Last Name");
			int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age"));
			String address = JOptionPane.showInputDialog("Enter Address");
			long contact = Long.parseLong(JOptionPane.showInputDialog("Enter Contact Number"));
		
			String.valueOf(userNumber);
			accountList.put("user"+userNumber, new Account(Fname, Lname, age, address, contact));
			userNumber++;
			userQuantity++;
	}

	//REMOVING AN ACCOUNT : ADMIN
	public void removeUser() {
			String userID = JOptionPane.showInputDialog(null, "Enter User ID", "Account", JOptionPane.PLAIN_MESSAGE);
			if(accountList.containsKey(userID)) {
			accountList.remove(userID);
			userQuantity--;
			}
			else JOptionPane.showMessageDialog(null, "User ID Not Found!", "Error", JOptionPane.WARNING_MESSAGE);
		
	}

	//SHOWING ACOUNT LIST and its OPERATIONS: ADMIN
	public JScrollPane showUserList() {
		
		 String[] columnNames = {"User ID", "Name", "Age", "Address", "Contact #"};
		 String[][] accData = new String[30][5];
		 
		int count = 0;
		
		for(String x: accountList.keySet()) {
			
			accData[count][0] =	x;		
			accData[count][1] = accountList.get(x).getName();
			accData[count][2] = accountList.get(x).getAge();
			accData[count][3] = accountList.get(x).getAddress();
			accData[count][4] = accountList.get(x).getContact();	
			if(count<accountList.size())count++;
		}
		
		JTable eventTable = new JTable(accData, columnNames);
		eventTable.setVisible(true);
		eventTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		eventTable.getColumn(columnNames[0]).setMaxWidth(60);
		eventTable.getColumn(columnNames[1]).setMinWidth(150);
		eventTable.getColumn(columnNames[1]).setMaxWidth(1000);
		eventTable.getColumn(columnNames[2]).setMaxWidth(30);
		eventTable.getColumn(columnNames[3]).setMinWidth(125);
		eventTable.getColumn(columnNames[3]).setMaxWidth(1000);
		eventTable.getColumn(columnNames[4]).setMinWidth(30);
	
		return new JScrollPane(eventTable);
		
		
	}
	
	//ADDING NEW ACCOUNT : DEFAULT USER
	public void signUp() {
		String Fname = JOptionPane.showInputDialog("Enter First Name");
		String Lname = JOptionPane.showInputDialog("Enter Last Name");
		int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age"));
		String address = JOptionPane.showInputDialog("Enter Address");
		long contact = Long.parseLong(JOptionPane.showInputDialog("Enter Contact Number"));
		
		String.valueOf(userNumber);
		accountList.put("user"+userNumber, new Account(Fname, Lname, age, address, contact));
		userNumber++;
		userQuantity++;
		
		JOptionPane.showMessageDialog(null, "Thank You for Signing Up!", "", JOptionPane.WARNING_MESSAGE);
	}
}
