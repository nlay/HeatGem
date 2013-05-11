package edu.ycp.cs320.heatgem.shared;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScoreTest {
	private Score PlayerScore;
	
	private double FinalScore;
	private Player player1;
	private int health;
	private int gameTimer;
	private double DELTA = .00000001;
	
	@Before
	public void setUp() {
		PlayerScore = new Score();
		player1 = new Player("Test");
		player1.setHealth(100);
		gameTimer = 300;
		FinalScore = PlayerScore.getScore(gameTimer, player1);
	}
	
	@Test
	public void testGetScore() { 
		assertEquals(FinalScore, 300.00, 10444.0);
	}
}