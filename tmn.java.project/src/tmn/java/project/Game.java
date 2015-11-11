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
	private int RBI;

	/**
	 * The AB recorded for this game
	 */
	private int AB;

	/**
	 * The path to the opposing team's logo
	 */
	private String oppImage;

	/**
	 * The opposing team's three letter ID
	 */
	private String opp;

	/**
	 * The HR recorded for this game
	 */
	private int HR;

	/**
	 * The player's team's three letter ID
	 */
	private String team;

	/**
	 * The H recorded for this game
	 */
	private int H;

	/**
	 * The path to the player's team's logo
	 */
	private String teamImage;

	/**
	 * The date for when this game took place
	 */
	private String date;

	/**
	 * Retrieve the player's RBI total for this game
	 * 
	 * @return The total number of RBI recorded for the Player in this Game
	 */
	public int getRbi() {
		return RBI;
	}

	/**
	 * Set the player's RBI total for this game
	 * 
	 * @param rbi
	 *            The value to set for the RBI count
	 */
	public void setRbi(int rbi) {
		this.RBI = rbi;
	}

	/**
	 * Retrieve the player's AB total for this game
	 * 
	 * @return The total number of AB recorded for the Player for this Game
	 */
	public int getAtBats() {
		return AB;
	}

	/**
	 * Set the player's AB total for this game
	 * 
	 * @param atBats
	 *            The value to set for the AB count
	 */
	public void setAtBats(int atBats) {
		this.AB = atBats;
	}

	/**
	 * Retrieve the player's H total for this game
	 * 
	 * @return The total number of H recorded for the Player for this Game
	 */
	public int getHits() {
		return H;
	}

	/**
	 * Set the player's H total for this game
	 * 
	 * @param hits
	 *            The value to set for the H count
	 */
	public void setHits(int hits) {
		this.H = hits;
	}

	/**
	 * Retrieve the String representation of the URL for the image of the logo
	 * for the opponent in this game
	 * 
	 * @return The String of the URL for the image of the logo for the opponent
	 *         in this game
	 */
	public String getOpponentImg() {
		return oppImage;
	}

	/**
	 * Set the String representation of the URL for the image of the logo for
	 * the opponent in this game
	 * 
	 * @param opponentImg
	 *            The String of the URL to set as the path to the image of the
	 *            logo for the opponent in this game
	 */
	public void setOpponentImg(String opponentImg) {
		this.oppImage = opponentImg;
	}

	/**
	 * Retrieve the three letter ID for the opponent in this game
	 * 
	 * @return The three letter ID for the opponent in this game
	 */
	public String getOpponentId() {
		return opp;
	}

	/**
	 * Set the three letter ID for the opponent in this game
	 * 
	 * @param opponentId
	 *            The value to set the opponent's three letter ID to
	 */
	public void setOpponentId(String opponentId) {
		this.opp = opponentId;
	}

	/**
	 * Retrieve the player's HR total for this game
	 * 
	 * @return The total number of HR recorded for the Player in this Game
	 */
	public int getHomeRuns() {
		return HR;
	}

	/**
	 * Set the player's HR total for this game
	 * 
	 * @param homeRuns
	 *            The value to set for the HR count
	 */
	public void setHomeRuns(int homeRuns) {
		this.HR = homeRuns;
	}

	/**
	 * Retrieve the three letter ID for the player's team
	 * 
	 * @return The three letter ID for the Player's team
	 */
	public String getPlayerTeamId() {
		return team;
	}

	/**
	 * Set the three letter ID for the player's team
	 * 
	 * @param playerTeamId
	 *            The value to set for the player's team ID
	 */
	public void setPlayerTeamId(String playerTeamId) {
		this.team = playerTeamId;
	}

	/**
	 * Retrieve the URL for the image of the player's team's logo
	 * 
	 * @return The String representation of the URL for the image of the
	 *         player's team's logo
	 */
	public String getPlayerTeamImg() {
		return teamImage;
	}

	/**
	 * Set the URL for the image of the player's team's logo
	 * 
	 * @param playerTeamImg
	 *            The String representation of the URL for the image of the
	 *            player's team's logo
	 */
	public void setPlayerTeamImg(String playerTeamImg) {
		this.teamImage = playerTeamImg;
	}

	/**
	 * Retrieve the date for this game
	 * 
	 * @return The date for this game
	 */
	public String getGameDate() {
		return date;
	}

	/**
	 * Set the date for this game
	 * 
	 * @param gameDate
	 *            The value to set for the date of this game
	 */
	public void setGameDate(String gameDate) {
		this.date = gameDate;
	}
}
