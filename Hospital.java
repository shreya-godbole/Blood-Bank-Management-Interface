package bloodBank;
import java.util.Scanner;
import java.util.PriorityQueue;

public class Hospital extends Donor {
	Scanner sc = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);
	String hosp_name;
	int priority, hosp_id, hosp_count = 0;
	node front = null, ptr;
	PriorityQueue<node> pq = new PriorityQueue<node>();

	class node {
		String hosp_name;
		int priority, hosp_id;
		node next;

		node(String n, int id, int p) {
			hosp_name = n;
			priority = p;
			hosp_id = id;
			next = null;
		}
	}

	void hospitalEnqueue() {
		hosp_count++;
		System.out.println("Enter the name of Hospital");
		hosp_name = sc.nextLine();
		System.out.println("Enter Hospital ID ");
		hosp_id = scan.nextInt();
		System.out.println(
				"On a scale of 1 to 5, Enter the urgency of Blood required (Please refer the following parameters):");
		System.out.println("1.Critical Condition (In case of an accident)");
		System.out.println("2.For ICU patients");
		System.out.println("3.Severe Anemia");
		System.out.println("4.Dialysis");
		System.out.println("5.Pre-booking for Operation");
		priority = scan.nextInt();
		node temp = new node(hosp_name, hosp_id, priority);
		if (front == null) {
			front = temp;
		} else {
			ptr = front;
			while (ptr.next != null && ptr.next.priority <= ptr.priority) {
				ptr = ptr.next;
			}
			temp.next = ptr.next;
			ptr.next = temp;
		}
	}

	void hospitalDequeue() {
		System.out.println("Enter Hospital Name");
		String h_name = sc.nextLine();
		System.out.println("Enter Hospital ID ");
		int h_id = scan.nextInt();
		ptr = front;
		int flag = 0;
		while (ptr != null) {
			if (ptr.hosp_name.equals(h_name) && h_id == ptr.hosp_id) {
				flag = 1;
			}
			ptr = ptr.next;
		}
		if (flag == 1) {
			if (front.hosp_name.equals(h_name)) {
				System.out.println("Hospital Name: " + front.hosp_name);
				System.out.println("Priority: " + front.priority);
				System.out.println("Delivery Successful.");
				front = front.next;
			} else {
				System.out
						.println("Your request cannot be proceeded right now as there are other urgent cases in queue");
			}
		} else if (flag == 0) {
			System.out.println("You have not placed your required order. Please place your requirement first.");
		}
	}

	void summary() {
		System.out.println("\nSummary of the Day:");
		System.out.println("Number of Hospitals that approached the blood bank today: " + hosp_count);
}
}
