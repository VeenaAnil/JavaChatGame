package com.staxter.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.log4j.Logger;

import java.util.stream.Collectors;
import java.util.ArrayList;

public class Game {

	private static final Logger logger = Logger.getLogger(Game.class);
	private static Map<String, Player> chatPlayers = new HashMap<>();
	private static List<Player> playersList = new ArrayList<>();
	private static String initiatorPlayer;
	private static String startMessage;

	public static void main(String[] args) {

		if (logger.isDebugEnabled()) {
			logger.debug("----------------------------Staxter Chat Game--------------------------------\n");
		}
		try {

			initializeGame(args);
			startGame();
		} catch (Exception e) {
			logger.error("Exception occured while starting chat game" + e.getMessage());
		}
	}

	/**
	 * The initiator player staring the game by sending the message to other player
	 */
	private static void startGame() {

		Player.counter.put(chatPlayers.get(initiatorPlayer), 1);

		if (logger.isDebugEnabled()) {
		
			logger.debug(initiatorPlayer + " sending inital ('1') message >>> " + startMessage);
		}
		chatPlayers.get(initiatorPlayer).sendMessage(startMessage, playersList);

	}

	/**
	 * Initializing the chat game by settingUp players, initiator player and message
	 * reading from the command line arguments
	 * 
	 * @param args
	 */
	private static void initializeGame(String... args) {

		if (logger.isDebugEnabled()) {

			logger.debug("Initializing the Game.......\n");
		}

		CommandLine commandLine;
		try {
			commandLine = new DefaultParser().parse(new Options(), args);

		} catch (ParseException e) {
			logger.error("Exception occured while parsing Command line arguments" + e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}

		if (commandLine.getArgs().length < 3) {
			logger.error("Please provide the necessary input parameters for  the chat game");
			throw new IllegalArgumentException("please provide the necessary input parameters for  the chat game");
		}

		setUpPlayers(commandLine.getArgs()[0].split(","));
		setUpInitiator(commandLine.getArgs()[1]);
		startMessage = commandLine.getArgs()[2];

	}

	/**
	 * Setting up the players by creating Player object for each players and adding
	 * players to the list
	 * 
	 * @param players
	 */
	private static void setUpPlayers(String... players) {
		playersList = Arrays.stream(players).map(Player::new).collect(Collectors.toList());
		playersList.forEach(player -> {
			chatPlayers.put(player.name, player);
			if (logger.isDebugEnabled()) {

				logger.debug(String.format("Player " + player.name + " has  joined the chat game"));
			}

		});
	}

	/**
	 * Setting up the initiator player
	 * 
	 * @param initiator
	 */
	private static void setUpInitiator(String initiator) {

		if (!chatPlayers.containsKey(initiatorPlayer = initiator)) {
			logger.error("Initiator Player " + initiatorPlayer + " not found in the players list");
			throw new IllegalArgumentException(
					String.format("Initiator Player {%s} not found in the players list", initiatorPlayer));
		}
		initiatorPlayer = initiator;
		if (logger.isDebugEnabled()) {

			logger.debug("Initiator Player for this chat game is " + initiatorPlayer+"\n");
		}

	}

}
