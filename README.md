Vending Machine README

Synopsis 

This Vending Machine provides Chips, Sodas, and Candy, for varying prices. The user can insert Nickels, Dimes, or Quarters and make Selections. If there is enough money in the machine 
the item is dispensed, and change is given if necessary. The user can also check the Display and see their current total or a prompt to insert Money if no Money is in the Machine

Code Example

Lets say the User wants to Insert a Nickel.
From the driver program, all the user is doing is hitting 1
The code behind that though is this
VendingMachine vender = new VendingMachine();   (This is our vending machine)

vender.insert(vender.NICKELWEIGHT, vender.NICKELSIZE); (The vending machine is passed a coin that has a Nickel's weight, and a Nickels Size, 
														and from that can determine a nickel has been inserted)

After we break out of the switch statement we get to this

System.out.println(vender.getDisplay());     (This will let the user know their nickel has been deposited)


Motivation

This is a project Sean Dunn started for the initial interview process at Pillar Technologies. The primary purpose of this project was to properly learn Test Driven Development and use it
to program 1 of 4 sample projects.

Installation

Everything initially programmed was done on Eclipse which is a Java platform. To develop this program, start a  Java project and copy "Driver.java", "VendingMachine.java", 
and "Driver.java" into your source folder

Tests

VendingMachineTest.Java has all the Tests written for this project. You can go there to either edit Tests and see if they work as desired, or write new ones to Test yourself
For Instance here is a snippet from VerifyMakeChange()

vender.setQuarters(10);
		vender.setTotal(100);
		vender.makeChange();
		assertEquals(6, vender.getQuarters());
		
If we change vender.setTotal(100); to vender.setTotal(200) we would expect 2 quarters to be left after $2.00 of quarters are given to customer, so it should look like this
vender.setQuarters(10);
		vender.setTotal(200);
		vender.makeChange();
		assertEquals(2, vender.getQuarters());
		
Contributors

@Sean Dunn -seandunn92@gmail.com





