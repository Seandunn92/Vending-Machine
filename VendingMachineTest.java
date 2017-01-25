import static org.junit.Assert.*;

import org.junit.Test;


public class VendingMachineTest {


	
	@Test
	public void whenEmptyVendingMachineIsPassedAQuarterItReturnsAQuarter() {
		VendingMachine vender = new VendingMachine();
		vender.insert(vender.QUARTERWEIGHT, vender.QUARTERSIZE);
		assertEquals(0.25,vender.getTotal() , 0.01);
	}
	@Test
	public void whenEmptyVendingMachineIsPassedANickelItReturnsANickel(){
		VendingMachine vender = new VendingMachine();
		vender.insert(vender.NICKELWEIGHT, vender.NICKELSIZE);
		assertEquals(0.05,vender.getTotal(), 0.01);
	}
	public void whenEmptyVendingMachineIsPassedADimeItReturnsADime(){
		VendingMachine vender = new VendingMachine();
		vender.insert(vender.DIMEWEIGHT, vender.DIMESIZE);
		assertEquals(0.10, vender.getTotal(), 0.01);
	}
	@Test
	public void whenVendingMachineWithADollarIsGivenAQuarterItReturnsDollarAndQuarter(){
		VendingMachine vender = new VendingMachine(1.00, 10, 10, 10);
		vender.insert(vender.QUARTERWEIGHT, vender.QUARTERSIZE);
		assertEquals(1.25, vender.getTotal(), 0.01);
	}
	@Test
	public void whenEnoughMoneyForChipsChipsAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.50, 10, 10, 10);
		vender.purchase("Chips");
		assertEquals("THANK YOU",vender.getDisplay());
	}
	@Test
	public void whenNotEnoughMoneyForChipsChipsAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.35, 10, 10, 10);
		vender.purchase("Chips");
		assertEquals("Price 0.50", vender.getDisplay());
	}
	
	@Test
	public void whenEnoughMoneyForCandyCandyAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.65, 10, 10, 10);
		vender.purchase("Candy");
		assertEquals("THANK YOU", vender.getDisplay());
	}
	@Test
	public void whenNotEnoughMoneyForCandyCandyAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.35, 10, 10, 10);
		vender.purchase("Candy");
		assertEquals("Price 0.65", vender.getDisplay());
	}
	@Test
	public void whenEnoughMoneyForSodaSodaAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(1.00, 10, 10, 10);
		vender.purchase("Soda");
		assertEquals("THANK YOU", vender.getDisplay());
	}
	@Test
	public void whenNotEnoughMoneyForSodaSodaAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.35, 10, 10, 10);
		vender.purchase("Soda");
		assertEquals("Price 1.00", vender.getDisplay());
	}
	/* This test does not account for the edge case where the customer inserted money without verifying the machine had change
	@Test
	public void VerifyEmptyVendingMachinewhenPurchaseMade(){
		VendingMachine vender = new VendingMachine(2.00, 10, 10, 10);
		vender.purchase("Chips");
		assertEquals(0.00, vender.getTotal(), 0.01);
		vender.setTotal(2.00);
		
		vender.purchase("Candy");
		assertEquals(0.00, vender.getTotal(), 0.01);
		vender.setTotal(2.00);
		
		vender.purchase("Soda");
		assertEquals(0.00, vender.getTotal(), 0.01);
	} 
	*/
	
	@Test
	public void VerifyMakeChange(){
		VendingMachine vender = new VendingMachine();
		
		//set Quarters to 10 and test to make sure we stop when no quarters are left since machine should not be able to provide $6.00 worth of quarters
		vender.setQuarters(10);
		vender.setTotal(6.00);
		vender.makeChange();
		assertTrue(vender.getQuarters()>=0);
		
		
		//setQuarters to 10 and test making change for 1 dollar, we should have 6 quarters left
		vender.setQuarters(10);
		vender.setTotal(1.00);
		vender.makeChange();
		assertEquals(6, vender.getQuarters());
		
		// Now we test for dimes, start by setting quarters equal to 0
		vender.setQuarters(0);
		//if we have 10 dimes and need to give 1.10 back, need to make sure vender stops giving dimes when dimes gets to 0
		vender.setDimes(10);
		vender.setTotal(1.90);
		vender.makeChange();
		assertTrue(vender.getDimes()>=0);
		
		//set dimes to 15 and make change for 1 dollar, should have 5 dimes left (Quarters is still set to 0)
		vender.setDimes(15);
		vender.setTotal(1.00);
		vender.makeChange();
		assertEquals(5, vender.getDimes());
		
		//Now we 0 out dimes and make sure Nickels is Working
		vender.setDimes(0);
		vender.setNickels(10);
		vender.setTotal(0.55);
		vender.makeChange();
		assertTrue(vender.getNickels()>=0);
		
		//Test for if when we have 15 nickels, 0 dimes, and 0 quarters, 50 cents needs to be refunded, we should have 5 nickels left
		vender.setNickels(15);
		vender.setTotal(0.50);
		vender.makeChange();
		System.out.println(vender.getNickels());
		assertEquals(5, vender.getNickels());
	}
	@Test
	public void VerifySoldOutFeature(){
		//set there to be enough money to purchase any item but all the items to be sold out
		VendingMachine vender = new VendingMachine(1.00, 0, 0, 0);
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
		vender.updateMessage();
		assertEquals("EXACT CHANGE ONLY", vender.getMessage());
		
	}
	
	@Test
	public void TestFor3Nickels(){
		VendingMachine vender = new VendingMachine();
		vender.insert(vender.NICKELWEIGHT, vender.NICKELSIZE);
		vender.insert(vender.NICKELWEIGHT, vender.NICKELSIZE);
		vender.insert(vender.NICKELWEIGHT, vender.NICKELSIZE);
		assertEquals(0.15, vender.getTotal(), 0);
	}
	
}
