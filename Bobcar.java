import java.util.Scanner;
/**
 * 
 * 10/15/14 CSE020 UCM Project 1
 * @author Huimin Zhang
 * @author Johnson Mei
 * @version 1.1
 *
 */
public class Bobcar {
	static Scanner input = new Scanner(System.in);

	//Arrays for easy access to car names and prices
	static String[] carList = {" ", Economy", "Compact", "Standard"};
	static int[] costList = {25, 55, 100};

	//This variable can be easily modified to change currency (don't forget to change cost)
	static char currency = '$';

	//Variables to store user choices
	static int carChosen;
	static int rentalDays;
	static boolean isMember;
	static boolean wantsPackage;

	//The price variable is changed after each stage of the calculation
	static int price;

	public static void main(String[] args) {
		carChosen = askCar();
		rentalDays = askDuration();
		isMember = askMember();
		if (isMember == true)
			wantsPackage = askPackage();
		price = calculatePrice();
	}

	public static int calculatePrice(){
		/*
		 * Base price calculation
		 */
		
		//Show user the chosen car and its price
		System.out.println("Cost of chosen car, " + carList[carChosen] + ", " +
				"is" + ' ' + currency + costList[carChosen] + " per day. ");

		//Show user the rental period
		System.out.println("Rental period is " + rentalDays);

		//Handle plurality
		if (rentalDays == 1)
			System.out.println(" day.");
		else
			System.out.println(" days.");

		//Set price to the base price and print
		price = costList[carChosen] * rentalDays;
		System.out.println("Base price is " + currency + price);
		
		/*
		 * Club discount and premium package cost
		 */
		
		//Apply club discount if applicable
		if (isMember == true) {
			price -= costList[carChosen] * rentalDays / 5;
			System.out.println("Club discount is " + currency + costList[carChosen] * rentalDays / 5);

		}

		//Add package price if chosen
		if (wantsPackage == true) {
			price *= 1.2;
			System.out.println("Package cost is " + currency + price * 0.2);
		}

		//Final price
		System.out.println("Final price is " + currency + price);
		return price;
	}

	public static int askCar() {
		//Print choices
		System.out.println("Select from the following rental cars: ");
		//Iterate through array of cars available and list them
		for (int i=0;i<carList.length;i++) {
			System.out.println("Press" + ' ' + i + ' ' + "for" + ' ' + carList[i]);
		}

		//Take input
		int tempCarChosen = input.nextInt();

		//Check input validity
		if (tempCarChosen >= 0 && tempCarChosen < carList.length)
			return tempCarChosen;
		else{
			System.out.println("Invalid input!");
			return askCar();
		}
	}

	public static int askDuration() {
		System.out.println("Enter duration of rental in days: ");
		return input.nextInt();
	}

	public static boolean askMember() {
		//Prompt user
		System.out.println("Are you a club member?");
		System.out.println("Press 1 for yes");
		System.out.println("Press 0 for no");

		//Take input
		int tempIsMember = input.nextInt();

		//Parse input and check validity
		if (tempIsMember == 1)
			return true;
		else if (tempIsMember == 0)
			return false;
		else {
			System.out.println("Invalid Input!");
			return askMember();
		}
	}

	public static boolean askPackage() {
		//Prompt user
		System.out.println("Do you want the Platinum Exclusive Package?");
		System.out.println("Press 1 for yes");
		System.out.println("Press 0 for no");

		//Take input
		int tempWantsPackage = input.nextInt();

		//Parse input and check validity
		if (tempWantsPackage == 1)
			return true;
		else if (tempWantsPackage == 0)
			return false;
		else {
			System.out.println("Invalid Input!");
			return askPackage();
		}
	}
}
