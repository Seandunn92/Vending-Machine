import static org.junit.Assert.*;

import org.junit.Test;


public class VendingMachineTest {


	//verifies an empty machine will have a quarter in the total position (25 for 25 pennies) if we put a quarter in (A coin which weighs as much as a quart
	//and is the size of a quarter is a quarter for our program.
	@Test
	public void whenEmptyVendingMachineIsPassedAQuarterItReturnsAQuarter() {
		VendingMachine vender = new VendingMachine();
		vender.insert(VendingMachine.QUARTERWEIGHT, VendingMachine.QUARTERSIZE);
		assertEquals(25,vender.getTotal() );
	}
	//verifies an empty machine will have a total of 5, when a Nickel is Put in
	@Test
	public void whenEmptyVendingMachineIsPassedANickelItReturnsANickel(){
		VendingMachine vender = new VendingMachine();
		vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE);
		assertEquals(5,vender.getTotal());
	}
	//verifies when an empty machine is passed a dime, it will have a total of 10
	public void whenEmptyVendingMachineIsPassedADimeItReturnsADime(){
		VendingMachine vender = new VendingMachine();
		vender.insert(VendingMachine.DIMEWEIGHT, VendingMachine.DIMESIZE);
		assertEquals(10, vender.getTotal());
	}
	@Test
	//Verifies when user puts money in a machine that already has money, the machine will correctly adjust the total
	public void whenVendingMachineWithAMoneyIsGivenMoreMoenyItsTotalUpdatesCorrecty(){
		//initialize to start with a dollar in the machine
		VendingMachine vender = new VendingMachine(100, 10, 10, 10);
		//Add a Quarter and Expect 125($1.25) is in the machine
		vender.insert(VendingMachine.QUARTERWEIGHT, VendingMachine.QUARTERSIZE);
		assertEquals(125, vender.getTotal() );
		//Add a dime to our 125, and we expect 135
		vender.insert(VendingMachine.DIMEWEIGHT, VendingMachine.DIMESIZE);
		assertEquals(135, vender.getTotal());
		//Add a nickel to our 135 and we expect 140
		vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE);
		assertEquals(140, vender.getTotal());
	}
	
	//This method makes sure chips are given to customer when selected with enough money in the machine and Thank You is displayed
	@Test
	public void whenEnoughMoneyForChipsChipsAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(50, 10, 10, 10);
		vender.purchase("Chips");
		assertEquals("THANK YOU",vender.getDisplay());
		//Vending Machine had 10 Chips before Transaction, so should only have 9 now
		assertEquals(9, vender.getChips());

	}
	
	//This method assures that if not enough money and Chips Are Selected, the Price of Chips is Displayed
	@Test
	public void whenNotEnoughMoneyForChipsChipsAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(35, 10, 10, 10);
		vender.purchase("Chips");
		assertEquals("Price $0.50", vender.getDisplay());
			}
	
	//This method assures that Candy is Properly Dispensed when enough money is Given
	@Test
	public void whenEnoughMoneyForCandyCandyAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(65, 10, 10, 10);
		vender.purchase("Candy");
		assertEquals("THANK YOU", vender.getDisplay());
		//We have 1 less than our starting 10 Candys
		assertEquals(9, vender.getCandy());

	}
	//This method assures that Candy is not given and the Price of Candy is shown, when Candy is selected without enough money
	@Test
	public void whenNotEnoughMoneyForCandyCandyAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(35, 10, 10, 10);
		vender.purchase("Candy");
		assertEquals("Price $0.65", vender.getDisplay());
	}
	//This method tests to make sure that when Soda is Selected and there is enough money in the Machine, Soda is given and Thank You is Displayeed
	@Test
	public void whenEnoughMoneyForSodaSodaAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(100, 10, 10, 10);
		vender.purchase("Soda");
		assertEquals("THANK YOU", vender.getDisplay());
		//We have 1 less soda than we started with
		assertEquals(9, vender.getSoda());

	}
	@Test
	//This method makes sure that when Soda is Selected but not enough money is in the machine, The price of Soda will appear
	public void whenNotEnoughMoneyForSodaSodaAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(35, 10, 10, 10);
		vender.purchase("Soda");
		assertEquals("Price $1.00", vender.getDisplay());
	}
	
	//This test makes sure the vending machine is emptied out after each purchase, meaning it made Change for the customrer
	// This test does not account for the edge case where the customer inserted money without verifying the machine had change
	@Test
	public void VerifyEmptyVendingMachinewhenPurchaseMade(){
		
		//Chips are purchsed, Vending Machine Total should be 0 when finished
		VendingMachine vender = new VendingMachine();
		vender.setTotal(200);
		vender.purchase("Chips");
		assertEquals(0, vender.getTotal());
		
		//Candy is purhcased, Vending Machine Total should be 0 when finished
		vender = new VendingMachine();
		vender.setTotal(200);
		vender.purchase("Candy");
		assertEquals(0, vender.getTotal());
		
		//Soda is purchased, Vending Machine Total should be 0 when finished
		vender = new VendingMachine();
		vender.setTotal(200);
		vender.purchase("Soda");
		assertEquals(0, vender.getTotal());
	} 
	
	
	@Test
	//This method verifies that MakeChange is working correctly, and not giving away more change then we have
	public void VerifyMakeChange(){
		VendingMachine vender = new VendingMachine();
		
		//set Quarters to 10 and test to make sure we stop when no quarters are left since machine should not be able to provide $6.00 worth of quarters
		vender.setQuarters(10);
		vender.setTotal(600);
		vender.makeChange();
		//assert the machine did not allow quarters to take on a negative value
		assertTrue(vender.getQuarters()>=0);
		
		
		//setQuarters to 10 and test making change for 1 dollar, we should have 6 quarters left
		vender.setQuarters(10);
		vender.setTotal(100);
		vender.makeChange();
		assertEquals(6, vender.getQuarters());
		
		// Now we test for dimes, start by setting quarters equal to 0
		vender.setQuarters(0);
		//if we have 10 dimes and need to give 1.10 back, need to make sure vender stops giving dimes when dimes gets to 0
		vender.setDimes(10);
		vender.setTotal(190);
		vender.makeChange();
		assertTrue(vender.getDimes()>=0);
		
		//set dimes to 15 and make change for 1 dollar, should have 5 dimes left (Quarters is still set to 0)
		vender.setDimes(15);
		vender.setTotal(100);
		vender.makeChange();
		assertEquals(5, vender.getDimes());
		
		//Now we 0 out dimes and make sure Nickels is Working (Quarters are still set to 0 as well)
		vender.setDimes(0);
		vender.setNickels(10);
		vender.setTotal(55);
		vender.makeChange();
		assertTrue(vender.getNickels()>=0);
		
		//Test for if when we have 15 nickels, 0 dimes, and 0 quarters, 50 cents needs to be refunded, we should have 5 nickels left
		vender.setNickels(15);
		vender.setTotal(50);
		vender.makeChange();
		assertEquals(5, vender.getNickels());
	}
	@Test
	public void VerifySoldOutFeature(){
		//set there to be enough money to purchase any item but all the items to be sold out
		VendingMachine vender = new VendingMachine(100, 0, 0, 0);
		vender.purchase("Chips");
		assertEquals("SOLD OUT", vender.getDisplay());
		vender.purchase("Candy");
		assertEquals("SOLD OUT", vender.getDisplay());
		vender.purchase("Soda");
		assertEquals("SOLD OUT", vender.getDisplay());
	}
	@Test
	//verifies the machine will let user know when exact change is needed. This one requires some thought
	//Since this machine only accepts Nickels, Dimes, And Quarters. The machine needs to have either 4 nickels, or 1 nickel and 2 dimes, in order to be able to provide change
	//for any amount of money. 
	public void VerifyExactChangeFeature(){
		VendingMachine vender = new VendingMachine();
		vender.setDimes(0);
		vender.setNickels(0);
		vender.setQuarters(0);
		vender.updateInsertOrExact();
		assertEquals("EXACT CHANGE ONLY", vender.getInsertOrExact());
		
	}
	
	@Test
	//made a test for adding 3 nickels because software wasn't running correctly when 3 were inserted
	public void TestFor3Nickels(){
		VendingMachine vender = new VendingMachine();
		vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE);
		vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE);
		vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE);
		assertEquals(15, vender.getTotal(), 0);
	}
	@Test
	//Test that Check Display is properly displaying the customers money, or the appropriate message if no money is in the machine
	public void TestCheckDisplay(){
		VendingMachine vender = new VendingMachine();
		vender.checkDisplay();
		assertEquals(vender.getInsertOrExact(), vender.getDisplay());
		vender.insert(VendingMachine.NICKELWEIGHT, VendingMachine.NICKELSIZE);
		vender.checkDisplay();
		assertEquals("$0.05", vender.getDisplay());
	}
	@Test
	public void TestMarginOfError(){
		double ourWeight = VendingMachine.NICKELWEIGHT -.08;
		VendingMachine vender= new VendingMachine();
		assertTrue(vender.WithinMarginOfError(VendingMachine.NICKELWEIGHT, ourWeight));
		ourWeight = VendingMachine.NICKELWEIGHT + .08;
		assertTrue(vender.WithinMarginOfError(VendingMachine.NICKELWEIGHT, ourWeight));
	}
	@Test
	public void TestMarginOfErrorWhenInsertingCoins(){
		
		//Add a nickel within margin of Error, Should have 5 cents in machine
		double ourWeight = VendingMachine.NICKELWEIGHT -.08;
		double ourSize = VendingMachine.NICKELSIZE + .08;
		VendingMachine vender = new VendingMachine();
		vender.insert(ourWeight, ourSize);
		assertEquals(5, vender.getTotal());
	
		//Add a dime within margin of Error, Should have 15 cents including previous nickel
		ourWeight = VendingMachine.DIMEWEIGHT + .08;
		ourSize = VendingMachine.DIMESIZE - .08;
		vender.insert(ourWeight, ourSize);
		assertEquals(15, vender.getTotal());
		
		//Add a Quarter with Margin of Error, Should have 40 cents, including previous Nickel and Dime
		ourWeight = VendingMachine.QUARTERWEIGHT -.08;
		ourSize = VendingMachine.QUARTERSIZE + .08;
		vender.insert(ourWeight, ourSize);
		assertEquals(40, vender.getTotal());
	}
	
}
