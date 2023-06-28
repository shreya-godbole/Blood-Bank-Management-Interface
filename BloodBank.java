package bloodBank;
import java.util.Scanner;
import java.util.Formatter;

public class BloodBank extends Donor {
	Scanner sc = new Scanner(System.in);
	Scanner scan = new Scanner(System.in);
	int donor_no, HCV, HbSag;
	float A = 1050, a = 1050, B = 1050, b = 1050, O = 1050, o = 1050, AB = 1050, ab = 1050; // Quantity of Blood																						// according to the bloo																						// groups
	float hb;
	float total_blood;
	String hosp_blood;
		void eligibility() {
			// Checking if the donor is eligible to donate blood
			int flag = 0;
			System.out.println("Enter your name ");
			name = sc.nextLine();
			System.out.println("Enter blood test reports as asked : ");
			System.out.println("Enter your heamoglobin levels : ");
			hb = scan.nextFloat();
			if (hb < 12.5) {
				System.out.println("Not eligible to donate blood due to low heamoglobin!");
				flag = 1;

			}
			if (flag == 0) {
				System.out.println("Hepatatis B(HbSag)reports :");
				System.out.println("Press 1 for NR(Non-Reactive), press 2 for R(Reactive)");
				HbSag = scan.nextInt();
				if (HbSag == 2) {
					System.out.println(
							"Not eligible for blood donation as you are hepatatis b postive, please visit doctor at earliest ");
					flag = 1;
				}
			}
			if (flag == 0) {
				System.out.println("Hepatatis B(HbSag)reports :");
				System.out.println("Press 1 for NR(Non-Reactive), press 2 for R(Reactive)");
				HbSag = scan.nextInt();
				if (HbSag == 2) {
					System.out.println(
							"Not eligible for blood donation as you are hepatatis b postive, please visit doctor at earliest ");
					flag = 1;
				}
				if (flag == 0) {
					System.out.println("Hepatatis C(HCV) reports");
					System.out.println("Press 1 for Negative, Press 2 for Positive");
					HCV = scan.nextInt();
					if (HCV == 2) {
						System.out.println("You can't donate blood due to hepatatis c, please visit doctor at earliest");
						flag = 1;
					}
				}
				if (flag == 0) {
					System.out.println("Have you Donated Blood in the past three months ?\nEnter 1 if not and 2 if yes.");
					int blood_donation = scan.nextInt();
					if (blood_donation == 2) {
						System.out.println("You cannot donate blood within 3 months of your previous blood donation");
						flag = 1;
					}
				}
			}

			if (flag == 1) {
				System.out.println("You are not eligible to donate blood");
			} else {
				System.out.println("You can proceed to next the counter");
				create(); // creates new node for the donor
				// display(); // displays certificate of donation
			}
		}

		void bloodQuantity1() {
			// method to calculate the blood quantity according to donated blood
//			A = 1050; // Quantity of A+
//			a = 1050; // Quantity of A-
//			B = 1050; // Quantity of B+
//			b = 1050; // Quantity of B-
//			O = 1050; // Quantity of O+
//			o = 1050; // Quantity of O-
//			AB = 1050; // Quantity of AB+
//			ab = 1050; // Quantity of AB-
			ptr = head;
			while (ptr != null) {
				switch (ptr.blood_grp) {
				case "A+":
					A = A + 350;
					break;
				case "A-":
					a = a + 350;
					break;
				case "B+":
					B = B + 350;
					break;
				case "B-":
					b = b + 350;
					break;
				case "O+":
					O = O + 350;
					break;
				case "O-":
					o = o + 350;
					break;
				case "AB+":
					AB = AB + 350;
					break;
				case "AB-":
					ab = ab + 350;
					break;
				}
				ptr = ptr.next;
			}
			total_blood = A + a + B + b + O + o + AB + ab;
		}

		void bloodQuantity2() {
			// method to give the blood to hospitals and simultaneously calculate the
			// quantity
			ptr = head;
			int c;
			do {
				System.out.println("1.Blood");
				System.out.println("2.Plasma");
				System.out.println("3.RBCs");
				System.out.println("Enter your choice : ");
				int choice = scan.nextInt();
				switch (choice) {
				// calling methods based on user's choice
				case 1: {
					blood();
					break;
				}
				case 2: {
					plasma();
					break;
				}
				case 3: {
					RBC();
					break;
				}
				}
				System.out.println("Enter 1 if you want to continue placing order.");
				c = scan.nextInt();
			} while (c == 1);
			Formatter fmt = new Formatter();
			System.out.println("The Quantity of Blood Available according to the blood groups is:");
			System.out.printf("%15s %15s %15s %15s %15s %15s %15s %15s\n", "A+", "A-", "B+", "B-", "O+", "O-", "AB+",
					"AB-");
			fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s\n", A + " ml", a + " ml", B + " ml", b + " ml", O + " ml",
					o + " ml", AB + " ml", ab + "ml");
			System.out.println(fmt);
		}

		void plasma() {
			System.out.println("Enter the blood group required");
			String hosp_blood = sc.nextLine();
			switch (hosp_blood) {
			case "A+": {
				float plm; // Available plasma in blood group A+
				plm = (A * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float Aq = scan.nextInt(); // Requirement from hospital
				if (Aq <= plm) { // Checking if we have sufficient amount
					plm = plm - Aq; // Fulfilling requirement and updating stock in system
					A = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							A = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "A-": {
				float plm; // Available plasma in blood group A-
				plm = (a * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float aq = scan.nextInt(); // Requirement from hospital
				if (aq <= plm) { // Checking if we have sufficient amount
					plm = plm - aq; // Fulfilling requirement and updating stock in system
					a = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							a = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "B+": {
				float plm; // Available plasma in blood group B+
				plm = (B * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float Bq = scan.nextInt(); // Requirement from hospital
				if (Bq <= plm) { // Checking if we have sufficient amount
					plm = plm - Bq; // Fulfilling requirement and updating stock in system
					B = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							B = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "B-": {
				float plm; // Available plasma in blood group B-
				plm = (b * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float bq = scan.nextInt(); // Requirement from hospital
				if (bq <= plm) { // Checking if we have sufficient amount
					plm = plm - bq; // Fulfilling requirement and updating stock in system
					b = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							b = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "O+": {
				float plm; // Available plasma in blood group O+
				plm = (O * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float Oq = scan.nextInt(); // Requirement from hospital
				if (Oq <= plm) { // Checking if we have sufficient amount
					plm = plm - Oq; // Fulfilling requirement and updating stock in system
					O = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							O = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "O-": {
				float plm; // Available plasma in blood group o-
				plm = (o * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float oq = scan.nextInt(); // Requirement from hospital
				if (oq <= plm) { // Checking if we have sufficient amount
					plm = plm - oq; // Fulfilling requirement and updating stock in system
					o = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							o = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "AB+": {
				float plm; // Available plasma in blood group AB+
				plm = (AB * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float ABq = scan.nextInt(); // Requirement from hospital
				if (ABq <= plm) { // Checking if we have sufficient amount
					plm = plm - ABq; // Fulfilling requirement and updating stock in system
					AB = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							AB = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			case "AB-": {
				float plm; // Available plasma in blood group AB-
				plm = (ab * 35) / 100;
				System.out.println("Enter the quantity required in ml");
				float abq = scan.nextInt(); // Requirement from hospital
				if (abq <= plm) { // Checking if we have sufficient amount
					plm = plm - abq; // Fulfilling requirement and updating stock in system
					ab = (plm * 100) / 35;
				} else {
					// If we can't fulfill hospitals requirement then giving them as much as we have
					// and updating quantity in system
					System.out.println("Sufficient quantity not available");
					System.out.println("We have " + plm + " quantity of plasma");
					if (plm != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order is being processed ");
							ab = 0;
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements ");
					}
				}
				break;
			}
			}
		}

		void RBC() {
			System.out.println("Enter the blood group required");
			String hosp_blood = sc.nextLine();
			switch (hosp_blood) {
			case "A+": {
				float rbc; // Available RCBs for blood group A+
				rbc = A / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int Aq = scan.nextInt(); // Required RBC count
				if (Aq <= rbc) {
					rbc = rbc - Aq; // Providing hospital, and updating quantity in system
					A = (2 * rbc);
				} else {
					// If bloodbank doesn't have sufficient stock, we give the hospital as much as
					// we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + "ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							A = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "A-": {
				float rbc; // Available RCBs for blood group A-
				rbc = a / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int aq = scan.nextInt(); // Required RBC count
				if (aq <= rbc) {
					rbc = rbc - aq; // Providing hospital, and updating quantity in system
					a = (2 * rbc);
				} else {
					// If bloodbank doesn't have suffient stock, we give the hospital as much as we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							a = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "B+": {
				float rbc; // Available RCBs for blood group B+
				rbc = B / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int Bq = scan.nextInt(); // Required RBC count
				if (Bq <= rbc) {
					rbc = rbc - Bq; // Providing hospital, and updating quantity in system
					B = (2 * rbc);
				} else {
					// If bloodbank doesn't have suffient stock, we give the hospital as much as we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							B = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "B-": {
				float rbc; // Available RCBs for blood group B-
				rbc = b / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int bq = scan.nextInt(); // Required RBC count
				if (bq <= rbc) {
					rbc = rbc - bq; // Providing hospital, and updating quantity in system
					b = (2 * rbc);
				} else {
					// If bloodbank doesn't have suffient stock, we give the hospital as much as we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							b = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "O+": {
				float rbc; // Available RCBs for blood group O+
				rbc = O / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int Oq = scan.nextInt(); // Required RBC count
				if (Oq <= rbc) {
					rbc = rbc - Oq; // Providing hospital, and updating quantity in system
					O = (2 * rbc);
				} else {
					// If bloodbank doesn't have suffient stock, we give the hospital as much as we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							O = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "O-": {
				float rbc; // Available RCBs for blood group O-
				rbc = o / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int oq = scan.nextInt(); // Required RBC count
				if (oq <= rbc) {
					rbc = rbc - oq; // Providing hospital, and updating quantity in system
					o = (2 * rbc);
				} else {
					// If bloodbank doesn't have suffient stock, we give the hospital as much as we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							o = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "AB+": {
				float rbc; // Available RCBs for blood group AB+
				rbc = AB / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int ABq = scan.nextInt(); // Required RBC count
				if (ABq <= rbc) {
					rbc = rbc - ABq; // Providing hospital, and updating quantity in system
					AB = (2 * rbc);
				} else {
					// If bloodbank doesn't have suffient stock, we give the hospital as much as we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							AB = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			case "AB-": {

				float rbc; // Available RCBs for blood group AB-
				rbc = ab / 2; // Blood contains 50% RBCs
				System.out.println("Enter the quantity required in ml");
				int abq = scan.nextInt(); // Required RBC count
				if (abq <= rbc) {
					rbc = rbc - abq; // Providing hospital, and updating quantity in system
					ab = (2 * rbc);
				} else {
					// If bloodbank doesn't have sufficient stock, we give the hospital as much as
					// we
					// have
					System.out.println("Sufficient RBCs not available");
					System.out.println("We have " + rbc + " ml RBCs");
					if (rbc != 0) {
						System.out.println("Press 1 if you want to place order for this amount.");
						int flag = scan.nextInt();
						if (flag == 1) {
							System.out.println("Order processing");
							ab = 0; // Update quantity in system
							System.out.println("Order placed");
						}
					} else {
						System.out.println("Let us know about your future requirements !");
					}
				}
				break;
			}
			}
		}

		void blood() {
			System.out.println("Enter the Blood Group required");
			String hosp_blood = sc.nextLine();
			switch (hosp_blood) {
			case "A+":
				System.out.println("Enter the quantity required in ml");
				int Aq = scan.nextInt(); // Requirement of blood
				if (Aq <= A) {
					A = A - Aq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + A
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (A != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = Aq - A;
							A = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement
								System.out.println(n);
								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								}
								if (n < o) {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and A+ blood
									System.out.println("Requirement is fulfilled. ");// + A + "ml A+ blood and" + n + "ml
																						// O- blood");
									o = o - n;
								}
								System.out.println("Order placed !");
							} else {
								System.out.println("Let us know about any further requirement !");
							}
						}
					}
				}
				break;
			case "A-":
				System.out.println("Enter the quantity required in ml");
				int aq = scan.nextInt(); // Requirement of blood
				if (aq <= a) {
					a = a - aq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + a
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (a != 0) {
						System.out.println("Press 1 if you want to proceed");
						flag = scan.nextInt();
						if (flag == 1) {
							float n = aq - a;
							a = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + " ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and A- blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "B+":
				System.out.println("Enter the quantity required in ml");
				int Bq = scan.nextInt(); // Requirement of blood
				if (Bq <= B) {
					B = B - Bq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + B
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (B != 0) {
						System.out.println("Press 1 if you want to proceed ");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = Bq - B;
							B = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and B+ blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "B-":
				System.out.println("Enter the quantity required in ml");
				int bq = scan.nextInt(); // Requirement of blood
				if (bq <= b) {
					b = b - bq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + b
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (b != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = bq - b;
							b = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and B- blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}

						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "O+":
				System.out.println("Enter the quantity required in ml");
				int Oq = scan.nextInt(); // Requirement of blood
				if (Oq <= O) {
					O = O - Oq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + O
							+ " amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (O != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = Oq - O;
							O = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + " quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and O+ blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "O-":
				System.out.println("Enter the quantity required in ml");
				int oq = scan.nextInt(); // Requirement of blood
				if (oq < o) {
					o = o - oq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have" + o + " amount of blood available.");
					if (o != 0) {
						System.out.println("Press 1 if you want to proceed with this order.");
						int flag = scan.nextInt();
						if (flag == 1) {
							o = 0;
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "AB+":
				System.out.println("Enter the quantity required in ml");
				int ABq = scan.nextInt(); // Requirement of blood
				if (ABq <= AB) {
					AB = AB - ABq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have " + AB
							+ " ml amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (AB != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = ABq - AB;
							AB = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + "ml quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and AB+ blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			case "AB-":
				System.out.println("Enter the quantity required in ml");
				int abq = scan.nextInt(); // Requirement of blood
				if (abq <= ab) {
					ab = ab - abq; // Giving hospital required blood and updating blood quantity in system
				} else {
					// Giving hospital as much quantity we have, if we can't fulfill the requirement
					System.out.println("Sufficient blood not available");
					System.out.println("We have" + ab
							+ " amount of blood available. We can Check if any quantity of O- blood group is availabe as O- is a Universal Donor group.");
					if (ab != 0) {
						System.out.println("Press 1 if you want to proceed");
						int flag = scan.nextInt();
						if (flag == 1) {
							float n = abq - ab;
							ab = 0;
							if (o != 0) {
								// To check how much O- blood is needed to fulfill the requirement

								// O- is universal donor so we try to fulfill requirement by O- blood
								System.out.println("We have " + o + " quantity of O- which is universal donor");
								System.out.println(n + "ml of O- blood is included in order");
								if (n >= o) {
									// if requirement is more than O- blood quantity then give all the O- blood
									System.out.println(o + "ml of O- blood is included in order");
									o = 0;
								} else {
									// If requirement is less than O- blood then its fulfilled by combination of O-
									// blood and AB- blood
									System.out.println("Requirement is fulfilled.");
									o = o - n;
								}
							}
						}
					} else {
						System.out.println("Let us know about any further requirement !");
					}
				}
				break;
			}
			total_blood = A + a + B + b + O + o + AB + ab; // updating the total blood amount
		}

		void display() {
			// Donor certificate display
			ptr = head;
			System.out.println("Please Enter the following Details to get your certificate:");
			System.out.println("Enter your name");
			String donor_name = sc.nextLine();
			System.out.println("Enter your number");
			donor_no = scan.nextInt();
			int flag = 0;
			while (ptr != null) {
				if (ptr.name.equals(donor_name) && ptr.count == donor_no) {
					System.out.println("--------CERTIFICATE OF BLOOD DONATION--------");
					System.out.println("THANK YOU " + ptr.name + " FOR YOUR BLOOD DONATION");
					System.out.println("Donor Name:" + ptr.name);
					System.out.println("Donor Age:" + ptr.age);
					System.out.println("Donor Blood Group:" + ptr.blood_grp);
					flag = 1;
				}
				ptr = ptr.next;
			}
			if (flag == 0) {
				System.out.println("Data of Donation not Found.");
			}
		}

		void display1() {
			System.out.println("Number of Donors that came in Today: " + count);
			System.out.println("The Quantity of Blood in the Blood Bank:");
			Formatter fmt = new Formatter();
			System.out.println("The Quantity of Blood Available according to the blood groups is:");
			System.out.printf("%15s %15s %15s %15s %15s %15s %15s %15s\n", "A+", "A-", "B+", "B-", "O+", "O-", "AB+",
					"AB-");
			fmt.format("%15s %15s %15s %15s %15s %15s %15s %15s\n", A + " ml", a + " ml", B + " ml", b + " ml", O + " ml",
					o + " ml", AB + " ml", ab + "ml");
			System.out.println(fmt);
		}

		void display2() {
			System.out.println("List of Donors who Donated Blood Today:");
			ptr = head;
			while (ptr != null) {
				System.out.println(ptr.name);
				ptr = ptr.next;
			}
		}

		

	}

