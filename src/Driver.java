import java.util.Scanner;


public class Driver {


	public static void main(String[] args){
		//The Vending Machine the main app will use
		VendingMachine vender= new VendingMachine();
		//How the Machine reads input from the customer
		Scanner VendingScanner = new Scanner(System.in);
		//placeholder for user input
		int input;
		System.out.println("Welcome to Vending Machine here are your options");
		System.out.println("1: Insert Nickel");
		System.out.println("2: Insert Dime");
		System.out.println("3: Insert Quarter");
		System.out.println("4: Purchase Chips");
		System.out.println("5: Purhcase Candy");
		System.out.println("6: Purchase Soda");
		System.out.println("7: Return Coins");
		System.out.println("8: Check Display");
		System.out.println("0: Quit");
	
		System.out.println();
		//Initial Vending Machine Display, should say "INSERT COIN" since machine shouldln't need exact change yet
		System.out.println(vender.getDisplay());
		
		//keep looping through user choices until 0 is chosen to quit.
		do{

			input=VendingScanner.nextInt();

			switch(input){
			case 1: vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE); break;
			case 2: vender.insert(VendingMachine.DIMEWEIGHT, VendingMachine.DIMESIZE); break;
			case 3: vender.insert(VendingMachine.QUARTERWEIGHT, VendingMachine.QUARTERSIZE); break;
			case 4: vender.purchase("Chips"); break;
			case 5: vender.purchase("Candy"); break;
			case 6: vender.purchase("Soda"); break;
			case 7: vender.makeChange(); break;
			case 8: vender.checkDisplay();
			}

			System.out.println(vender.getDisplay());
			

		}while (input!=0);


	}

}
