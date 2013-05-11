package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private Player player;
	private int SHealth;

	@Before 
	public void setUp() {
		SHealth = 50;
		player = new Player("test");
		player.getHealth();
		player.getPlayerName();
		player.setHealth(SHealth);
	}

	@Test
	public void testName() {
		assertEquals(player.getPlayerName(), "test");
	}

	@Test
	public void testGetHealth() {
		assertEquals(player.getHealth(), 50);
	}

	@Test
    public void testSetHealth( )
    {
       assertEquals(this.SHealth, 50);
    }


}
