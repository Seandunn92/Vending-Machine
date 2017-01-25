import java.util.Scanner;

public class Driver {


	public static void main(String[] args){
		VendingMachine vender= new VendingMachine();
		Scanner VendingScanner = new Scanner(System.in);
		int input;
		System.out.println("Welcome to Vending Machine here are your options");
		System.out.println("1: Insert Nickel");
		System.out.println("2: Insert Dime");
		System.out.println("3: Insert Quarter");
		System.out.println("4: Purchase Chips");
		System.out.println("5: Purhcase Candy");
		System.out.println("6: Purchase Soda");
		System.out.println("7: Return Coins");
		System.out.println("0: Quit");

		System.out.println();
		System.out.println(vender.getDisplay());

		do{

			input=VendingScanner.nextInt();

			switch(input){
			case 1: vender.insert(vender.NICKELWEIGHT, vender.NICKELSIZE); break;
			case 2: vender.insert(vender.DIMEWEIGHT, vender.DIMESIZE); break;
			case 3: vender.insert(vender.QUARTERWEIGHT, vender.QUARTERSIZE); break;
			case 4: vender.purchase("Chips"); break;
			case 5: vender.purchase("Candy"); break;
			case 6: vender.purchase("Soda"); break;
			case 7: vender.makeChange(); break;
			}

			System.out.println(vender.getDisplay());
			System.out.println(vender.getTotal());

		}while (input!=0);


	}

}
