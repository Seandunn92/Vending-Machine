

public class VendingMachine {
	//A nickel weighs 5 grams exactly
	public static final double NICKELWEIGHT = 5.0;
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
	
	//Chips cost $0.50
	public static final double CHIPSCOST = 0.50;
	//Sodas cost $1.00
	public static final double SODACOST = 1.00;
	//Candy costs $0.65
	public static final double CANDYCOST = 0.65;

	private double total;
	private int Chips;
	private int Sodas;
	private int Candy;
	private int Quarters;
	private int Dimes;
	private int Nickels;
	
	public void setTotal(double total) {this.total=total;}
	public double getTotal() {return total;}
	public void setQuarters(int Quarters){this.Quarters=Quarters;}
	public int getQuarters(){return Quarters;}
	public void setDimes(int Dimes){this.Dimes=Dimes;}
	public int getDimes(){return Dimes;}
	public void setNickels(int Nickels){this.Nickels=Nickels;}
	public int getNickels(){return Nickels;}
	
	//A method to convert given weight to corresponding Value
		//if the method has a corresponding coinvalue will also increase the number of that coin in the machine
	private double convertWeightandSize(double weight, double size){
		if (weight==NICKELWEIGHT & size==NICKELSIZE){
			Nickels++;
			return 0.05;
		}
		else if (weight==DIMEWEIGHT & size==DIMESIZE){
			Dimes++;
			return 0.10;
		}
		else if (weight==QUARTERWEIGHT & size==QUARTERSIZE){
			Quarters++;
			return 0.25;
		}
		else 
			return 0.00;
	}

	public VendingMachine(double total, int Chips, int Sodas, int Candy) {
		this.total=total;
		this.Chips=Chips;
		this.Sodas = Sodas;
		this.Candy = Candy;
		
		Quarters=10;
		Nickels=10;
		Dimes=10;

	}

	public VendingMachine(){
		total = 0.00;
		Chips = 10;
		Sodas = 10;
		Candy = 10;
		Quarters=10; 
		Dimes=10;
		Nickels=10;
	}


	public double insert(double CoinWeight, double CoinSize) {
		// TODO Auto-generated method stub
		total+= convertWeightandSize(CoinWeight, CoinSize);

		return total;
	}

	//This function makes change from the remaining total either by Customer request or by Transaction
	public double makeChange(double total){
		//start taking away as many Quarters as possible
		while (total/0.25>=1){
			if (Quarters<=0)
			break;
			Quarters--;
			total-=0.25;
		}
		while (total/0.10>=1){
			if (Dimes<=0)
			break;
			Dimes--;
			total-=0.10;
		}
		while (total/0.05>=1){
			if (Nickels <=0)
			break;
			Nickels--;
			total-=0.05;
		}
		
		return total;
		
	}
	
	public String purchase(String ITEMCHOICE) {
		// TODO Auto-generated method stub
		if (ITEMCHOICE == "Chips"){
			if (total>=CHIPSCOST){
				Chips--;
				makeChange(total-CHIPSCOST);
				return "THANK YOU";
			}
			//trick from onlineto make 2 decimal places
			return "Price " + String.format("%.2f", CHIPSCOST);
		}
		if (ITEMCHOICE == "Candy"){
			if (total>=CANDYCOST){
				Candy--;
				makeChange(total-CANDYCOST);
				return "THANK YOU";
			}
			return "Price " + String.format("%.2f", CANDYCOST);
		}
		
		if (ITEMCHOICE == "Soda"){
			if (total>=SODACOST){
				
				Sodas--;
				makeChange(total-SODACOST);
				return "THANK YOU";
			}
			return "Price " + String.format("%.2f", SODACOST);
		}
		
		return null;

	}

	

}
