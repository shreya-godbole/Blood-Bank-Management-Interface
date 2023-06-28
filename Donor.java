package bloodBank;
import java.util.Scanner;

public class Donor {
	Scanner sc = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);
	Node head = null, ptr;
	String name, blood_grp;
	int age, count = 0;
	int flag = 0;
	Donor d;

	class Node {
		Node prev, next;
		String name, blood_grp;
		int age, count;

		Node(String n, int a, String b, int c) {
			prev = null;
			next = null;
			name = n;
			age = a;
			blood_grp = b;
			count = c;
		}
	}

	void create() {
		// creating a linked list for donors
//		System.out.println("Enter your name ");
//		name = sc.nextLine();
		System.out.println("Enter your age");
		age = scan.nextInt();
		if (age < 17) {
			System.out.println("Sorry, Blood Donation is allowed for individuals above 18 years.");
		} else {
			System.out.println("Enter your Blood Group");
			blood_grp = sc.nextLine();
			count++;
			System.out.println("Your number is: " + count);
			Node temp = new Node(name, age, blood_grp, count);

			ptr = head;
			if (head == null) {
				head = temp;
			} else {
				while (ptr.next != null) {
					ptr = ptr.next;
				}
				ptr.next = temp;
				temp.prev = ptr;
			}
		}
	}
}
