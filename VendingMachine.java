

public class VendingMachine {
	//A nickel weighs 5 grams exactly
	public static final double NICKELWEIGHT = 5.0;
	// A dime weighs 2.68 grams;
	public static final double DIMEWEIGHT = 2.268;
	//A quarter weighs 5.670 grams;
	public static final double QUARTERWEIGHT = 5.670;
	public static final double CHIPSCOST = 0.50;
	public static final double SODACOST = 1.00;
	public static final double CANDYCOST = 0.65;

	private double total;
	private int Chips;
	private int Sodas;
	private int Candy;
	//A method to convert given weight to corresponding Value
	private double convertWeight(double d){
		if (d==NICKELWEIGHT)
			return 0.05;
		else if (d==DIMEWEIGHT)
			return 0.10;
		else if (d==QUARTERWEIGHT)
			return 0.25;
		else 
			return 0.00;
	}

	public VendingMachine(double total, int Chips, int Sodas, int Candy) {
		this.total=total;
		this.Chips=Chips;
		this.Sodas = Sodas;
		this.Candy = Candy;

	}

	public VendingMachine(){
		total = 0.00;
		Chips = 10;
		Sodas = 10;
		Candy = 10;
	}

	public double insert(double CoinWeight) {
		// TODO Auto-generated method stub
		total+= convertWeight(CoinWeight);

		return total;
	}

	public String purchase(String ITEMCHOICE) {
		// TODO Auto-generated method stub
		if (ITEMCHOICE == "Chips"){
			if (total>=CHIPSCOST){
				Chips--;
				return "THANK YOU";
			}
			//trick from onlineto make 2 decimal places
			return "Price " + String.format("%.2f", CHIPSCOST);
		}
		if (ITEMCHOICE == "Candy"){
			if (total>=CANDYCOST){
				Candy--;
				return "THANK YOU";
			}
			return "Price " + String.format("%.2f", CANDYCOST);
		}
		
		if (ITEMCHOICE == "Soda"){
			if (total>=SODACOST){
				Sodas--;
				return "THANK YOU";
			}
			return "Price " + String.format("%.2f", SODACOST);
		}
		
		return null;

	}

}
