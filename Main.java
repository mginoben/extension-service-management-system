import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main {
		private static int eventQuantity = 0;
		private static int eventNumber = 0;
		public static HashMap <String, Event> eventList = new HashMap<String, Event>();

	public static void main(String[] args) {

		JOptionPane.showMessageDialog(null, "Extension Services\nManagement System\n\nDevelopers:\nGino Ben Magsino\nDavid Rafael Sumawang", "", JOptionPane.PLAIN_MESSAGE);
		//USER TYPE LOGIN
		while(true) {
		String[] options = {"Admin", "Default", "Exit"};
		int login = JOptionPane.showOptionDialog(null, "Select Login Type", "Welcome!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		
			//ADMIN
			if(login == 0) {
				boolean password;
				do{
					 password = loginPassword();
					
					//CORRECT PASS!
					if(password == true) {
						while(true) {
							//SHOW EVENT LIST with OPERATIONS
							String[] options1 = {"Create Event", "Remove Event", "Show Details", "Sign Out"};
							int adminOperation = JOptionPane.showOptionDialog(null, showEventList(), "Event List", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
								
								//OPERATIONS: 
								
								//CREATE EVENT
								if(adminOperation == 0) createEvent();
								//REMOVE EVENT
								else if(adminOperation == 1) {
									if(eventList.isEmpty()) JOptionPane.showMessageDialog(null, "Event List is Empty!", "Error", JOptionPane.WARNING_MESSAGE);
									else{
									String eventID = selectEvent();
										if(eventList.containsKey(eventID)) removeEvent(eventID);
										else JOptionPane.showMessageDialog(null, "Event ID Not Found!", "Error", JOptionPane.WARNING_MESSAGE);
									}
								}
								//SHOW DETAILS
								else if(adminOperation == 2) {
									if(eventList.isEmpty()) JOptionPane.showMessageDialog(null, "Event List is Empty!", "Error", JOptionPane.WARNING_MESSAGE);
									else {
										String eventID = selectEvent();
										if(eventList.containsKey(eventID)) {
										String[] userListOption = {"Show User List", "Back"};
										int userListOperation = JOptionPane.showOptionDialog(null, showEventDetails(eventID), "Event List", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, userListOption, null);
											
											//SHOW USER LIST
											if(userListOperation == 0) {
												while(true) {
													String[] add_remove_Option = {"Add User", "Remove User", "Back"};
													int add_remove = JOptionPane.showOptionDialog(null, showUserList(eventID), "Event List", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, add_remove_Option, null);
														
														//ADD USER
														if(add_remove == 0) eventList.get(eventID).addUser();
														//REMOVE USER
														if(add_remove == 1) {
															if( eventList.get(eventID).accountList.isEmpty()) JOptionPane.showMessageDialog(null, "Account List is Empty!", "Error", JOptionPane.WARNING_MESSAGE);
															else {
															eventList.get(eventID).removeUser();
															}
														}
														else if(add_remove == 2) break;
												}
											}
										}
										else JOptionPane.showMessageDialog(null, "Event ID Not Found!", "Error", JOptionPane.WARNING_MESSAGE); 
											
										 
									}
								}
								//SIGN OUT
								else if(adminOperation == 3) {
									int signOut = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Sign Out", JOptionPane.YES_NO_OPTION);
									if(signOut == 0) break;
								}
						}
					}
					
					//WRONG PASS!
					else {
						String[] passOptions = {"Try Again", "Back"};
						int wrongPass = JOptionPane.showOptionDialog(null, "Wrong Password!", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, passOptions, passOptions[0]);
						//EXIT LOGIN
						if(wrongPass == 1) break;
					}
				}
				while(password != true);
			}
			
			//DEFAULT USER
			else if(login == 1) {
				if(eventList.isEmpty()) JOptionPane.showMessageDialog(null, "Event List is Empty!", "Error", JOptionPane.WARNING_MESSAGE);
				else {
					while(true) {
					String[] defaultOptions = {"Show Details", "Back"};
					int defaultOperation = JOptionPane.showOptionDialog(null, showEventList(), "Event List", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, defaultOptions, defaultOptions[0]);
					
						//SHOW DETAILS
						if(defaultOperation == 0) {
							String eventID = selectEvent();
							String[] showDetails_Option = {"Join", "Back"};
							int showDetails_Operation = JOptionPane.showOptionDialog(null, showEventDetails(eventID), "Event List", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, showDetails_Option, showDetails_Option[0]);
								
								//SIGN UP
								if(showDetails_Operation == 0) {
									eventList.get(eventID).signUp();
									break;
								}
						}
						else break;
					}
				}
			}	
			//EXIT
			else break;
		}
	}
	
	
	
	
	
	//METHODS	
	public static boolean loginPassword() {
			boolean pass = false;
			JLabel label = new JLabel("Enter Password");
	        JTextField password = new JPasswordField();
	        Object[] adminLogin = {label, password};
	        int result = JOptionPane.showConfirmDialog(null, adminLogin, "Admin", JOptionPane.OK_CANCEL_OPTION);
	        
	        if(result == 0) {
	        	if(password.getText().equals("admin")) {
	        		pass = true;//SUCCESS
	        	}
	        	else {
	        		pass = false;//FAILED
	        	}
	        }
	        
	        return pass;
	}

	public static void createEvent() {
		
		++eventQuantity;
		//EVENT LIMIT REACHED!
		if(eventQuantity > 7) JOptionPane.showMessageDialog(null, "Event Limit Reached!", "Error", JOptionPane.WARNING_MESSAGE);
		else{
			eventNumber++;
		String.valueOf(eventNumber);
		eventList.put("event"+eventNumber, new Event());
			
			//SETTING TITLE
			String title = JOptionPane.showInputDialog("Enter Event Title");
			
			//SETTING TARGET AUDIENCE
			String target = JOptionPane.showInputDialog("Enter Target Audience");
			
			//SETTING LOCATION
			String location = JOptionPane.showInputDialog("Enter Location");
				
			//SETTING DATE
			String month = JOptionPane.showInputDialog(null, "Enter Month (word)", "Date", JOptionPane.PLAIN_MESSAGE);
			int day = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Day", "Date", JOptionPane.PLAIN_MESSAGE));
			int year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Year", "Date", JOptionPane.PLAIN_MESSAGE));	
				
			//SETTING TIME
			String[] options= {"AM","PM"};
			int sHour = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Starting Time (hour)", "Time Start", JOptionPane.PLAIN_MESSAGE));
			int sMin = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Starting Time (minutes)", "Time Start", JOptionPane.PLAIN_MESSAGE));	
			int p1 = JOptionPane.showOptionDialog(null, "Select Period", "Time", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
			int eHour = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Ending Time (hour)", "Time End", JOptionPane.PLAIN_MESSAGE));
			int eMin = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Ending Time (minutes)", "Time End", JOptionPane.PLAIN_MESSAGE));
			int p2 = JOptionPane.showOptionDialog(null, "Select Period", "Time", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, 0);
			
			//SETTING DESCRIPTION
			String description = JOptionPane.showInputDialog("Enter Description");
				
			//INITIALIZING ALL INPUTS TO EVENT
				eventList.get("event"+eventNumber).setTitle(title);
				eventList.get("event"+eventNumber).setTarget(target);
				eventList.get("event"+eventNumber).setDate(month, day, year);
				eventList.get("event"+eventNumber).setLocation(location);
				eventList.get("event"+eventNumber).setTime(sHour, sMin, p1, eHour, eMin, p2);
				eventList.get("event"+eventNumber).setDescription(description);
		}
	}
	
	public static void removeEvent(String eventID) {
		eventList.remove(eventID);
		--eventQuantity;
	}
	
	public static JScrollPane showEventList() {
		String[] columnNames = {"Event ID", "Name", "Date", "Signed Up"};
		String[][] eventData = new String[7][4];
		int count = 0;
		
		//ITERATE THROUGH EVENT ID
		for(String x: eventList.keySet()) {
			eventData[count][0] =	x;
			eventData[count][1] = eventList.get(x).getTitle();
			eventData[count][2] = eventList.get(x).getDate();
			eventData[count][3] = eventList.get(x).getSignedUp();
			count++;
		}
		
		//JTABLE
		JTable eventTable = new JTable(eventData, columnNames);
		eventTable.setVisible(true);
		eventTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	
		eventTable.getColumn(columnNames[0]).setMaxWidth(60);
		eventTable.getColumn(columnNames[1]).setMinWidth(120);
		eventTable.getColumn(columnNames[2]).setMinWidth(60);
		eventTable.getColumn(columnNames[3]).setMaxWidth(70);
		
		//JSCROLLPANE
		return new JScrollPane(eventTable);
	}

	
	public static String selectEvent() {
		return JOptionPane.showInputDialog(null, "Enter Event ID", "", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static String showEventDetails(String eventID) {
		return eventList.get(eventID).toString();
	}
	
	public static JScrollPane showUserList(String eventID) {
		return eventList.get(eventID).showUserList();
	}
}
