

public class VendingMachine {
	//A nickel weighs 5 grams exactly
	public final double NICKELWEIGHT = 5.0;
	//A nickel measures 22.21 milimeters diameter;
	public static final double NICKELSIZE = 21.21;
	// A dime weighs 2.68 grams;
	public static final double DIMEWEIGHT = 2.268;
	//A dime measures 17.91 millimeters diameter
	public static final double DIMESIZE = 17.91;
	//A quarter weighs 5.670 grams;
	public static final double QUARTERWEIGHT = 5.670;
	//A quarter's diameter is 24.26 millimeters;
	public static final double QUARTERSIZE = 24.26;
	
	//Chips cost $0.50 which is 50 pennies
	public static final int CHIPSCOST = 50;
	//Sodas cost $1.00 which is 100 pennies
	public static final int SODACOST = 100;
	//Candy costs $0.65 which is 65 pennies
	public static final int CANDYCOST = 65;
	

	
	//the number of pennies
	private int total;
	//the number of chips in the machine
	private int Chips;
	//the number of Sodas in the machine
	private int Soda;
	//the number of candys in the machine
	private int Candy;
	//the number of quarters in the machine
	private int Quarters;
	//The number of Dimes in the machine
	private int Dimes;
	//The number of Nickels in the machine
	private int Nickels;
	//The Display the customer sees
	private String Display;
	//Default Message, changed if exact change is needed
	private String Message = "INSERT COIN";
	
	//All the setters and getters needed although some will not be used in the program
	public String getMessage() {return Message;}
	public void setTotal(int total) {this.total=total;}
	public int getTotal() {return total;}
	public void setQuarters(int Quarters){this.Quarters=Quarters;}
	public int getQuarters(){return Quarters;}
	public void setDimes(int Dimes){this.Dimes=Dimes;}
	public int getDimes(){return Dimes;}
	public void setNickels(int Nickels){this.Nickels=Nickels;}
	public int getNickels(){return Nickels;}
	public void setChips(int Chips){this.Chips=Chips;}
	public int getChips(){return Chips;}
	public void setSoda(int Soda){ this.Soda=Soda;}
	public int getSoda(){return Soda;}
	public void setCandy(int Candy){this.Candy=Candy;}
	public int getCandy(){return Candy;}
	public String getDisplay(){return Display;}
	
	
	//The message will need to be changed every time the machine needs exact change
	public void updateMessage(){
		//If we have 4 nickels, 3 nickels and a dime, or 1 Nickel and 2 dimes, we will have enough change for any amount inserted
		// *NOTE* This is only because this machine does not accept dollar bills, It is impossible for the user to have inserted money that there is not change for
		// if the above conditions are met
		if (Nickels >= 4 || (Nickels>=3 && Dimes>=1) || (Nickels>=1 && Dimes >=2))
			Message = "INSERT COIN";
		else 
			Message = "EXACT CHANGE ONLY";
	}
	
	//A method to convert given weight to corresponding Value
		//if the method has a corresponding coinvalue will also increase the number of that coin in the machine
	private int convertWeightandSize(double weight, double size){
		if (weight==NICKELWEIGHT & size==NICKELSIZE){
			Nickels++;
			return 5;
		}
		else if (weight==DIMEWEIGHT & size==DIMESIZE){
			Dimes++;
			return 10;
		}
		else if (weight==QUARTERWEIGHT & size==QUARTERSIZE){
			Quarters++;
			return 25;
		}
		else 
			return 0;
	}

	
	//This constructor will be passsed the total amount in the machine, the number of chips it has inside, number of Sodas, and the amount of Candy
	//Both constructors will preset their coin amounts to all be 10
	public VendingMachine(int total, int Chips, int Sodas, int Candy) {
		this.total=total;
		this.Chips=Chips;
		this.Soda = Sodas;
		this.Candy = Candy;
		
		Quarters=10;
		Nickels=10;
		Dimes=10;
		Display= Message;

	}

	//The base  Vending Machine Consturctor 
	public VendingMachine(){
		total = 0;
		Chips = 10;
		Soda = 10;
		Candy = 10;
		Quarters=10; 
		Dimes=10;
		Nickels=10;
		Display=Message;
	}
	
	//This is the String to convert our total for instance 150, into its readble string (ie $1.50)
	public String getPrintableAmount(int amount){
		//this checks how many cents are left after all the dollars are taken out
		int CentsLeft = amount % 100;
		String ourAmount;  //Eventually machine will pass out a readable ourAmount after our calculations
		
		//if there is less than 10 cents left  this method insures that a '0' is put in front, so that 
		//someone with 0.05 cents left does not think they have $0.50 (without this 0.5 would be displayed)
		if (CentsLeft<10)
		ourAmount = "$" + amount/100 + ".0" + amount%100;
		//Machine takes amount/100 to get the dollars inserted, and amount%100 to get the pennies that are left
		else
		ourAmount = "$" + amount/100 + "." + amount%100;
		
		return ourAmount;
	}

//This method takes in the weight and measurement of a coin, and after determining it's value, adds it to the total, and updates Display
	public void insert(double CoinWeight, double CoinSize) {
		
		total+= convertWeightandSize(CoinWeight, CoinSize);
		if (total!=0)
			Display=getPrintableAmount(total);
		else Display = Message;

		
	}

	//This method is for the customer to make change without a purchase
	
	//This function makes change from the remaining total either by Customer request or by Transaction
	public void makeChange(){
		//start giving the customer as many quarters change as possible
		while (total/25>=1){
			if (Quarters<=0)
			break;
			Quarters--;
			total-=25;
		}
		
		//after no more Quarters can be taken, start giving as many dimes as possible
		while (total/10>=1){
			if (Dimes<=0)
			break;
			Dimes--;
			total-=10;
		}
		//after dimes, start giving the customer as many Nickels as possibe
		while (total/5>=1){
			if (Nickels <=0)
			break;
			Nickels--;
			total-=5;
		}
		//It's important before returning new total in machine, to update whether or not vending machine can make change, (Message is either EXACT CHANGE ONLY or INSERT COIN)
		updateMessage();
	
		//the customer has no money in the machine and we can safely disply message
		if (total==0)
			Display=Message;
		else
			//The customer still has money in the machine and the machine will show them that amount
			Display="$ " + getPrintableAmount(total);
	
		
	}
	
	
	//This method takes in a String to determine what item the customer wants, and purchase that item if they have the available funds
	public void purchase(String ITEMCHOICE) {
		
		//Customer Chose Chips
		if (ITEMCHOICE == "Chips"){
			//Machine is out of Chips
			if (Chips<=0){
				Display="SOLD OUT";
				return;
				}
				
			//Enough Money for Chips, so the Machine subtracts their cost from the Customers total, and decrease our chip inventory
			if (total>=CHIPSCOST){
				Chips--;
				total-=CHIPSCOST;
				makeChange();
				Display = "THANK YOU";
				return ;
			}
			//The customer did not put enough money in for Chips, so it shows them the Chips price
			Display = "Price " + getPrintableAmount(CHIPSCOST);
			return ;
	}
		//Customer Chose Candy
		if (ITEMCHOICE == "Candy"){
			if (Candy<=0){
				
				Display="SOLD OUT";
				
				return ;
			}
			if (total>=CANDYCOST){
				Candy--;
				total-=CANDYCOST;
				makeChange();
				
				Display = "THANK YOU";
				return;
			}
			Display = "Price " + getPrintableAmount(CANDYCOST);
			return;
		}
		//Customer Chose Soda
		if (ITEMCHOICE == "Soda"){
			if (Soda<=0){
				
				Display="SOLD OUT";
				
				
				return ;
				
			}
			if (total>=SODACOST){
				
				Soda--;
				total-= SODACOST;
				makeChange();
				Display="THANK YOU";
				return;
			}
			Display = "Price " + getPrintableAmount(SODACOST);
			return;
		}
		//edge case we should never get to from an invalid choice of product
		Display="Invalid Choice";
	

	}
	//when checking the display, we will display money if the user has money in the machine, or our Message(INSERT COIN or EXACT CHANGE ONLY) if there is no money in the machine
	//helps the user check how much money is in the machine
	public void checkDisplay(){
		if (total >0)
			Display=getPrintableAmount(total);
		else
			Display=Message;
	}
	

	

}
