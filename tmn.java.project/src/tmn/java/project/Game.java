/*******************************************************************************
 * Copyright (c) 2015 Taylor Patterson
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Taylor Patterson - Initial implementation and documentation
 *******************************************************************************/
package tmn.java.project;

import java.net.URL;
import java.util.Date;

/**
 * This class defines Game objects by providing storage for game data and
 * methods for game data assignment and retrieval.
 * 
 * @author Taylor Patterson
 */
public class Game {

	/**
	 * The RBI recorded for this game
	 */
	private int rbi;

	/**
	 * The AB recorded for this game
	 */
	private int atBats;

	/**
	 * The H recorded for this game
	 */
	private int hits;

	/**
	 * The path to the opposing team's logo
	 */
	private URL opponentImg;

	/**
	 * The opposing team's three letter ID
	 */
	private String opponentId;

	/**
	 * The HR recorded for this game
	 */
	private int homeRuns;

	/**
	 * The player's team's three letter ID
	 */
	private String playerTeamId;

	/**
	 * The path to the player's team's logo
	 */
	private URL playerTeamImg;

	/**
	 * The date for when this game took place
	 */
	private Date gameDate;

	/**
	 * Retrieve the player's RBI total for this game
	 * 
	 * @return The total number of RBI recorded for the Player in this Game
	 */
	public int getRbi() {
		return rbi;
	}

	/**
	 * Set the player's RBI total for this game
	 * 
	 * @param rbi
	 *            The value to set for the RBI count
	 */
	public void setRbi(int rbi) {
		this.rbi = rbi;
	}

	/**
	 * Retrieve the player's AB total for this game
	 * 
	 * @return The total number of AB recorded for the Player for this Game
	 */
	public int getAtBats() {
		return atBats;
	}

	/**
	 * Set the player's AB total for this game
	 * 
	 * @param atBats
	 *            The value to set for the AB count
	 */
	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}

	/**
	 * Retrieve the player's H total for this game
	 * 
	 * @return The total number of H recorded for the Player for this Game
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * Set the player's H total for this game
	 * 
	 * @param hits
	 *            The value to set for the H count
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	/**
	 * Retrieve the URL for the image of the logo for the opponent in this game
	 * 
	 * @return The URL for the image of the logo for the opponent in this game
	 */
	public URL getOpponentImg() {
		return opponentImg;
	}

	/**
	 * Set the URL for the image of the logo for the opponent in this game
	 * 
	 * @param opponentImg
	 *            The URL to set as the path to the image of the logo for the
	 *            opponent in this game
	 */
	public void setOpponentImg(URL opponentImg) {
		this.opponentImg = opponentImg;
	}

	/**
	 * Retrieve the three letter ID for the opponent in this game
	 * 
	 * @return The three letter ID for the opponent in this game
	 */
	public String getOpponentId() {
		return opponentId;
	}

	/**
	 * Set the three letter ID for the opponent in this game
	 * 
	 * @param opponentId
	 *            The value to set the opponent's three letter ID to
	 */
	public void setOpponentId(String opponentId) {
		this.opponentId = opponentId;
	}

	/**
	 * Retrieve the player's HR total for this game
	 * 
	 * @return The total number of HR recorded for the Player in this Game
	 */
	public int getHomeRuns() {
		return homeRuns;
	}

	/**
	 * Set the player's HR total for this game
	 * 
	 * @param homeRuns
	 *            The value to set for the HR count
	 */
	public void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
	}

	/**
	 * Retrieve the three letter ID for the player's team
	 * 
	 * @return The three letter ID for the Player's team
	 */
	public String getPlayerTeamId() {
		return playerTeamId;
	}

	/**
	 * Set the three letter ID for the player's team
	 * 
	 * @param playerTeamId
	 *            The value to set for the player's team ID
	 */
	public void setPlayerTeamId(String playerTeamId) {
		this.playerTeamId = playerTeamId;
	}

	/**
	 * Retrieve the URL for the image of the player's team's logo
	 * 
	 * @return The URL for the image of the player's team's logo
	 */
	public URL getPlayerTeamImg() {
		return playerTeamImg;
	}

	/**
	 * Set the URL for the image of the player's team's logo
	 * 
	 * @param playerTeamImg
	 *            The URL for the image of the player's team's logo
	 */
	public void setPlayerTeamImg(URL playerTeamImg) {
		this.playerTeamImg = playerTeamImg;
	}

	/**
	 * Retrieve the date for this game
	 * 
	 * @return The date for this game
	 */
	public Date getGameDate() {
		return gameDate;
	}

	/**
	 * Set the date for this game
	 * 
	 * @param gameDate
	 *            The value to set for the date of this game
	 */
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
}
