package com.staxter.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

import com.ginsberg.junit.exit.ExpectSystemExit;
import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

/**
 * Unit test class for the Game program
 * 
 * @author Veena Anil Kumar
 *
 */
public class GameTest {

	private Player player1;
	private Player player2;
	private List<Player> players = new ArrayList<>();

	/**
	 * Create two players, initiator and player2
	 */
	@Before
	public void initializePlayers() {
		player1 = new Player("player1");
		player2 = new Player("player2");
		players.add(player1);
		players.add(player2);

	}
/*
	@Test
	@ExpectSystemExitWithStatus(1)
	public void checkIfProgramTerminatedAfter_10Messages() {

		for (int i = 0; i < 15; i++) {
			player1.sendMessage("Hi", players);

		}

	}
*/
}
