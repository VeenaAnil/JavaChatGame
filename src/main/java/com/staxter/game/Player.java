package com.staxter.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class Player {

	private static final Logger logger = Logger.getLogger(Player.class);
	/**
	 * This field saves the Player name
	 */
	public final String name;
	/**
	 * Map track the message counter for each players
	 */
	public static Map<Player, Integer> counter = new HashMap<Player, Integer>();

	/**
	 * Create new Player instance with the player name.
	 * 
	 * @param playerName
	 */
	public Player(String playerName) {
		this.name = playerName;

	}

	/**
	 * isInitiator message field is used to identify the initial message to
	 * initialize the players message counter
	 */
	private boolean isInitiatorMessage = true;

	/**
	 * This method send the message to another player
	 * 
	 * @param message the message send by other player
	 * @param players List of all players in the chat game
	 */
	public void sendMessage(String message, List<Player> players) {
		if (isInitiatorMessage) {
			initializeMessageCounter(players);
		}
		Player nextPlayer = getNextPlayer(this, players);

		if (logger.isDebugEnabled()) {

			logger.debug(nextPlayer.name + " receiving message from  " + this.name + " and message is >>>" + message);
		}

		nextPlayer.receiveMessage(message, players);

	}

	/**
	 * This method receives message from a player and send new message to the sender
	 * . if the number of messages received by the initiator player is 10 exit the
	 * program
	 * 
	 * @param receivedMessage -The message received from another player
	 * @param players         -list of players in the chat game
	 */
	public void receiveMessage(String receivedMessage, List<Player> players) {

		int count = counter.get(this);
		if (count <= 10) {
			String newMessage = receivedMessage + count;
			if (logger.isDebugEnabled()) {

				logger.debug(this.name + " sending '" + counter.get(this) + "' message >>> " + newMessage);
			}
			counter.put(this, count + 1);
			this.sendMessage(newMessage, players);
		}else {
			System.exit(1);
		}
		
	}

	/**
	 * This method returns the nextPlayer who has to receive the message
	 * 
	 * @param currentPlayer - current player who is going to send the message
	 * @param players       - List of players
	 * @return Player
	 */
	public Player getNextPlayer(Player currentPlayer, List<Player> players) {

		int index = players.indexOf(currentPlayer);
		Player nextPlayer = (index < players.size() - 1) ? players.get(index + 1) : players.get(0);
		return nextPlayer;

	}

	/**
	 * This method initialize the message counter for players and update the
	 * initiator Player message count for the first time initiator player sends
	 * message
	 * 
	 * @param players
	 */
	public void initializeMessageCounter(List<Player> players) {

		players.stream().forEach((player) -> {
			counter.put(player, 1);
			player.isInitiatorMessage = false;
		});
		counter.put(this, 2);

	}
}