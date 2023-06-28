package bloodBank;
import java.util.Scanner;
public class Main extends Donor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		int choice, c = 1, c1 = 1, c2 = 1;
		BloodBank obj = new BloodBank();
		Hospital obj1 = new Hospital();
		while (c == 1) {
			System.out.println("--------WELCOME TO NITASH BLOOD BANK--------");
			System.out.println("Are you a: \n1.Donor? Enter 1 \n2.Hospital? Enter 2");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				// adding donors (in a Linked List)
				c1 = 1;
				while (c1 == 1) {
					System.out.println("Enter 1 to Donate Blood.\nEnter 2 to collect your Certificate.");
					int ch1 = scan.nextInt();
					switch (ch1) {
					case 1:
						obj.eligibility();
						break;
					case 2:
						obj.display();
						break;
					}
					System.out.println("Enter 1 to Continue in this Donor Interface");
					c1 = scan.nextInt();
				}
				obj.bloodQuantity1();// displaying quantity available
				break;
			case 2:
				// For Hospitals to place and collect order (Based on Priority Queue)
				c2 = 1;
				System.out.println("Please Enter the Password.");
				String password = sc.nextLine();
				if (password.equals("123")) {
					while (c2 == 1) {
						System.out
								.println("Enter one of the following options: \n1.To place Order\n2.To collect Order");
						int ch2 = scan.nextInt();
						switch (ch2) {
						case 1:
							obj1.hospitalEnqueue();
							obj.bloodQuantity2(); // accepting details and updating blood quantity
							break;
						case 2:
							obj1.hospitalDequeue();
							break;
						}
						System.out.println("Enter 1 to continue in this Hospital interface ");
						c2 = scan.nextInt();
					}

				} else {
					System.out.println("Access Denied.");
				}
				break;
			}
			System.out.println("Enter 1 to view main Interface");
			c = scan.nextInt();
		}
		obj1.summary();
		obj.display1();
		System.out.println("Do You wish to view the list of names of the Donor?\nEnter 1 if yes");
		int ch3 = scan.nextInt();
		if (ch3 == 1) {
			System.out.println("Enter the password");
			String password = sc.nextLine();
			if (password.equals("123")) {
				obj.display2();
			}
		}
		sc.close();
		scan.close();
	}

}
