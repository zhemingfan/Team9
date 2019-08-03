import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTestGUIVERSION {
	
	//Testing constructors
	@Test
	public void test_Constructor_AllZero() {
		Player testPlayer = new Player(0,0);
		assertEquals("Created player with 0 HP and starting currency - testing HP", 20, testPlayer.getHealth());
		assertEquals("Created player with 0 HP and starting currency - testing Money", 20, testPlayer.getMoney());
	}
	
	@Test
	public void test_Constructor_ZeroHP() {
		Player testPlayer = new Player(0, 50);
		assertEquals("Created player with 0 HP and 50 starting currency - testing HP", 20, testPlayer.getHealth());
		assertEquals("Created player with 0 HP and 50 starting currency - testing Money", 50, testPlayer.getMoney());
	}

	@Test
	public void test_Constructor_ZeroMoney() {
		Player testPlayer = new Player(50, 0);
		assertEquals("Created player with 50 HP and 0 starting currency - testing HP", 50, testPlayer.getHealth());
		assertEquals("Created player with 50 HP and 0 starting currency - testing Money", 20, testPlayer.getMoney());
	}

	@Test
	public void test_Constructor_AllNonZeroInvalid() {
		Player testPlayer = new Player (10, 10);
		assertEquals("Created player with 10 HP and 10 starting currency - testing HP", 20, testPlayer.getHealth());
		assertEquals("Created player with 10 HP and 10 starting currency - testing Money", 20, testPlayer.getMoney());
	}

	@Test
	public void test_Constructor_MinimumValue() {
		Player testPlayer = new Player(20,20);
		assertEquals("Created player with 20 HP and 20 starting currency - testing HP", 20, testPlayer.getHealth());
		assertEquals("Created player with 20 HP and 20 starting currency - testing Money", 20, testPlayer.getMoney());
	}

	@Test
	public void test_Constructor_VeryHighHP() {
		Player testPlayer = new Player(20000, 20);
		assertEquals("Created player with 20000 HP and 20 starting currency - testing HP", 20000, testPlayer.getHealth());
		assertEquals("Created player with 20000 HP and 20 starting currency - testing Money", 20, testPlayer.getMoney());
	}

	@Test
	public void test_Constructor_VeryHighMoney() {
		Player testPlayer = new Player(20, 20000);
		assertEquals("Created player with 20 HP and 20000 starting currency - testing HP", 20, testPlayer.getHealth());
		assertEquals("Created player with 20 HP and 20000 starting currency - testing Money", 20000, testPlayer.getMoney());
	}

	@Test
	public void test_Constructor_AllVeryHigh() {
		Player testPlayer = new Player (200000, 200000);
		assertEquals("Created player with 20000 HP and 20000 starting currency - testing HP", 200000, testPlayer.getHealth());
		assertEquals("Created player with 20000 HP and 20000 starting currency - testing Money", 200000, testPlayer.getMoney());
	}
	
	//Testing setters and getters
	@Test
	public void test_setter_and_getter_HP_CorrectValue() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setHealth(100);
		assertEquals("Changed HP to 100", 100, testPlayer.getHealth());
	}
	
	@Test
	public void test_setter_and_getter_HP_NegativeValue() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setHealth(-100);
		assertEquals("Set HP to a negative value -100 and will default to 20", 20, testPlayer.getHealth());
	}
	
	@Test
	public void test_setter_and_getter_HP_SameValue() {
		Player testPlayer = new Player(40, 40);
		testPlayer.setHealth(40);
		assertEquals("Set HP to the same value, 40", 40, testPlayer.getHealth());
	}
	
	@Test
	public void test_setter_and_getter_HP_InvalidNonZeroValue() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setHealth(10);
		assertEquals("Set HP to an invalid value 10 and will default to 20", 20, testPlayer.getHealth());
	}
	
	@Test
	public void test_setter_and_getter_HP_Zero() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setHealth(0);
		assertEquals("Set HP to an invalid value 0 and will default to 20", 20, testPlayer.getHealth());
	}
	
	@Test
	public void test_setter_and_getter_Money_CorrectValue() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setMoney(100);
		assertEquals("Set starting Money to a valid value 100", 100, testPlayer.getMoney());
	}
	
	@Test
	public void test_setter_and_getter_Money_NegativeValue() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setMoney(-1);
		assertEquals("Set starting Money to a negative value -1 and will default to 20", 20, testPlayer.getMoney());
	}
	
	@Test
	public void test_setter_and_getter_Money_SameValue() {
		Player testPlayer = new Player(40, 40);
		testPlayer.setMoney(40);
		assertEquals("Set starting Money to the same value, 40", 40, testPlayer.getMoney());
	}
	
	@Test
	public void test_setter_and_getter_Money_InvalidNonZeroValue() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setMoney(10);
		assertEquals("Set starting Money to an invalid value 10 and will default to 20", 20, testPlayer.getMoney());
	}
	
	@Test
	public void test_setter_and_getter_Money_Zero() {
		Player testPlayer = new Player(20, 20);
		testPlayer.setMoney(0);
		assertEquals("Set starting Money to zero and will default to 20", 20, testPlayer.getMoney());
	}
	
	//Testing isKilled
	@Test
	public void test_isKilled_NotDead() {
		Player testPlayer = new Player(20, 20);
		testPlayer.takeDamage(19);
		assertEquals("Player with 20HP takes 19 damage; not dead", false, testPlayer.isKilled());
	}
	
	
	@Test
	public void test_isKilled_ExactDamageDeath() {
		Player testPlayer = new Player(20, 20);
		testPlayer.takeDamage(20);
		assertEquals("Player with 20HP takes 20 damage; dead", true, testPlayer.isKilled());
	}
	
	public void test_isKilled_ExcessiveDamageDeath() {
		Player testPlayer = new Player(20, 20);
		testPlayer.takeDamage(100);
		assertEquals("Player with 20HP takes 100 damage; dead", true, testPlayer.isKilled());
	}
	
	//Testing Damage
	@Test
	public void test_takeDamage_ValidDamage() {
		Player testPlayer = new Player(20,20);
		testPlayer.takeDamage(5);
		assertEquals("Player with 20 HP takes 5 damage - checking HP", 15, testPlayer.getHealth());
	}
	
	@Test
	public void test_takeDamage_NegativeDamage() {
		Player testPlayer = new Player(20,20);
		testPlayer.takeDamage(-5);
		assertEquals("Player with 20 HP takes a negative damage, HP will be unchanged", 20, testPlayer.getHealth());
	}
	
	@Test
	public void test_takeDamage_ExactKillingBlow() {
		Player testPlayer = new Player(20,20);
		testPlayer.takeDamage(20);
		assertEquals("Player with 20 HP takes 20 damage - checking HP", 0, testPlayer.getHealth());
	}
	
	@Test
	public void test_takeDamage_ExcessiveKillingBlow() {
		Player testPlayer = new Player(20,20);
		testPlayer.takeDamage(200);
		assertEquals("Player with 20 HP takes 200 damage - checking HP", -180, testPlayer.getHealth());
	}
	
	//Testing hasEnoughFunds
	@Test
	public void test_hasEnoughFunds_ExactAmount() {
		Player testPlayer = new Player(20,20);
		assertEquals("Player has 20 money, checking if 20 price is affordable", true, testPlayer.enoughFunds(20));
	}
	
	@Test
	public void test_hasEnoughFunds_MoreThanEnough() {
		Player testPlayer = new Player(20,20);
		assertEquals("Player has 20 money, checking if 5 price is affordable", true, testPlayer.enoughFunds(5));
	}
	
	@Test
	public void test_hasEnoughFunds_NotEnough() {
		Player testPlayer = new Player(20,20);
		assertEquals("Player has 20 money, checking if 50 price is affordable", false, testPlayer.enoughFunds(50));
	}
	
	//Testing buyTower
	@Test
	public void test_buyTower_EnoughFunds() {
		Player testPlayer = new Player(20, 20);
		testPlayer.buyDefense(5);
		assertEquals("Player has 20 money, buys tower costing 5 price - checking money", 15, testPlayer.getMoney());
	}
	
	@Test
	public void test_buyTower_NotEnoughFunds() {
		Player testPlayer = new Player(20, 20);
		testPlayer.buyDefense(25);
		assertEquals("Player has 20 money, tries to buy tower costing 25 price - checking money", 20, testPlayer.getMoney());
	}
	
	@Test
	public void test_buyTower_ExactFunds() {
		Player testPlayer = new Player(20, 20);
		testPlayer.buyDefense(20);
		assertEquals("Player has 20 money, buys tower costing 20 price - checking money", 0, testPlayer.getMoney());
	}
	
	@Test
	public void test_buyTower_InvalidPrice() {
		Player testPlayer = new Player(20, 20);
		testPlayer.buyDefense(-20);
		assertEquals("Player has 20 money, tries to buy tower with an invalid price - checking money", 20, testPlayer.getMoney());
	}
	
	//Testing gainMoney
	@Test
	public void test_gainMoney_validAmount() {
		Player testPlayer = new Player(20, 20);
		testPlayer.gainMoney(30);
		assertEquals("Player with 20 money gains 30 money - checking money", 50, testPlayer.getMoney());
	}
	
	@Test
	public void test_gainMoney_ZeroBounty() {
		Player testPlayer = new Player(20, 20);
		testPlayer.gainMoney(0);
		assertEquals("Player with 20 money gains nothing - checking money", 20, testPlayer.getMoney());
	}
	
	@Test
	public void test_gainMoney_NegativeBounty() {
		Player testPlayer = new Player(20,20);
		testPlayer.gainMoney(-50);
		assertEquals("Player with 20 money gains a negative value -50 - checking money", 20, testPlayer.getMoney());
	}
}
