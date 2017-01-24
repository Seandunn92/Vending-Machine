import static org.junit.Assert.*;

import org.junit.Test;


public class VendingMachineTest {


	
	@Test
	public void whenEmptyVendingMachineIsPassedAQuarterItReturnsAQuarter() {
		VendingMachine vender = new VendingMachine();
		assertEquals(0.25, vender.insert(vender.QUARTERWEIGHT), 0.01);
	}
	@Test
	public void whenEmptyVendingMachineIsPassedANickelItReturnsANickel(){
		VendingMachine vender = new VendingMachine();
		assertEquals(0.05,vender.insert(vender.NICKELWEIGHT), 0.01);
	}
	public void whenEmptyVendingMachineIsPassedADimeItReturnsADime(){
		VendingMachine vender = new VendingMachine();
		assertEquals(0.10,vender.insert(vender.DIMEWEIGHT), 0.01);
	}
	@Test
	public void whenVendingMachineWithADollarIsGivenAQuarterItReturnsDollarAndQuarter(){
		VendingMachine vender = new VendingMachine(1.00, 10, 10, 10);
		assertEquals(1.25, vender.insert(vender.QUARTERWEIGHT), 0.01);
	}
	@Test
	public void whenEnoughMoneyForChipsChipsAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.50, 10, 10, 10);
		assertEquals("THANK YOU", vender.purchase("Chips"));
	}
	@Test
	public void whenNotEnoughMoneyForChipsChipsAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.35, 10, 10, 10);
		assertEquals("Price 0.50", vender.purchase("Chips"));
	}
	
	@Test
	public void whenEnoughMoneyForCandyCandyAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.65, 10, 10, 10);
		assertEquals("THANK YOU", vender.purchase("Candy"));
	}
	@Test
	public void whenNotEnoughMoneyForCandyCandyAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.35, 10, 10, 10);
		assertEquals("Price 0.65", vender.purchase("Candy"));
	}
	@Test
	public void whenEnoughMoneyForSodaSodaAreDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(1.00, 10, 10, 10);
		assertEquals("THANK YOU", vender.purchase("Soda"));
	}
	@Test
	public void whenNotEnoughMoneyForSodaSodaAreNotDispensedWhenSelected(){
		VendingMachine vender = new VendingMachine(0.35, 10, 10, 10);
		assertEquals("Price 1.00", vender.purchase("Soda"));
	}
	
	
	
}
