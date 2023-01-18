public class Account {
	private String Fname;
	private String Lname;
	private int age; 
	private String address; 
	private long contact;
	
	public Account(String Fn, String Ln, int a, String ad, long c) {
		this.Fname = Fn;
		this.Lname = Ln;
		this.age = a;
		this.address = ad;
		this.contact = c;
	}
	
	public String getName() {
		return Fname+" "+Lname;
	}
	public String getAge() {
		return String.format("%2d", age);
	}
	public String getAddress() {
		return address;
	}
	public String getContact() {
		return String.format("%d", contact);
	}
}
