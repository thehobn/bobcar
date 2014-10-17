import java.util.Scanner;
/**
 * 
 * 10/15/14 CSE020 UCM Project 1
 * @author Huimin Zhang
 * @author Johnson Mei
 * @version 1.2
 *
 */
public class Bobcar {
	static Scanner input = new Scanner(System.in);

	//Arrays for easy access to car names and prices
	static String[] carList = {"Economy", "Compact", "Standard"};
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
		System.out.print("Rental period is " + rentalDays);

		//Handle plurality
		if (rentalDays == 1)
			System.out.println(" day.");
		else
			System.out.println(" days.");

		//Set price to the base price and print
		int basePrice = costList[carChosen] * rentalDays;
		System.out.println("Base price is " + "\t\t " + currency + basePrice);
		price = basePrice;
		
		/*
		 * Club discount and premium package cost
		 */

		//Apply club discount if applicable
		if (isMember == true) {
			int clubDiscountAmount = costList[carChosen] * (rentalDays / 5);
			System.out.println("Club discount is " + "\t-" + currency + clubDiscountAmount);
			price -= clubDiscountAmount;
		}

		//Add package price if chosen
		if (wantsPackage == true) {
			int additionalPackageCost = (int) (basePrice * 0.2);
			System.out.println("Package cost is " + "\t+" + currency + additionalPackageCost);
			price += additionalPackageCost;
		}

		//Final price
		System.out.println("Final price is " + "\t\t " + currency + price);
		return price;
	}

	public static int askCar() {
		//Print choices
		System.out.println("Select from the following rental cars: ");
		//Iterate through array of cars available and list them
		for (int i=0;i<carList.length;i++) {
			System.out.println("Press" + ' ' + (i+1) + ' ' + "for" + ' ' + carList[i]);
		}

		//Take input
		int carChosenIndex = input.nextInt() - 1;

		//Check input validity
		if (carChosenIndex >= 0 && carChosenIndex < carList.length)
			return carChosenIndex;
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
		int isMemberInput = input.nextInt();

		//Parse input and check validity
		if (isMemberInput == 1)
			return true;
		else if (isMemberInput == 0)
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
		int wantsPackageInput = input.nextInt();

		//Parse input and check validity
		if (wantsPackageInput == 1)
			return true;
		else if (wantsPackageInput == 0)
			return false;
		else {
			System.out.println("Invalid Input!");
			return askPackage();
		}
	}
}
