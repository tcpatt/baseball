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

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * This class defines Player objects by providing storage for player data and
 * methods for player data assignment and retrieval.
 * 
 * @author Taylor Patterson
 */
public class Player {

	/**
	 * Player ID number
	 */
	private Integer Id;

	/**
	 * List of games for this player
	 */
	private List<Game> games;

	/**
	 * Player's full name
	 */
	private String name;

	/**
	 * The URL for an stock photo of the player
	 */
	private URL imagePath;

	/**
	 * The constructor
	 */
	public Player() {
		setId(null);
		setGames(null);
		setName(null);
		setImagePath(null);
	}

	/**
	 * Player constructor that gets data from a .json file
	 * 
	 * @param playerJSON
	 *            The .json file containing player data with which to construct
	 *            an object of type Player.
	 * @throws IOException
	 */
	public Player(File playerJSON) {
	}

	/**
	 * Retrieve the player ID
	 * 
	 * @return The Integer ID for this Player
	 */
	public int getId() {
		return Id;
	}

	/**
	 * Set the player ID
	 * 
	 * @param id
	 *            The Integer to set this Player's ID to
	 */
	public void setId(Integer id) {
		Id = id;
	}

	/**
	 * Retrieve this player's games
	 * 
	 * @return The List of Game objects for this Player
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * Set this player's games data
	 * 
	 * @param games
	 *            The List of Game objects to set for this Player
	 */
	public void setGames(List<Game> games) {
		this.games = games;
	}

	/**
	 * Retrieve this player's name
	 * 
	 * @return The String for this Player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set this player's name
	 * 
	 * @param name
	 *            The String to set this Player's name to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the path to this player's photo
	 * 
	 * @return The URL that leads o photo of this Player
	 */
	public URL getImagePath() {
		return imagePath;
	}

	/**
	 * Set the URL for a photo of this player
	 * 
	 * @param imagePath
	 *            The URL to a photo to set this Player
	 */
	public void setImagePath(URL imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * Generate an HTML file containing the Player's data in order to display
	 * this information in a web browser
	 * 
	 * @param fileToWrite
	 *            The .html file where the player data will be written
	 * @throws IOException
	 */
	public void writeToHTML(File fileToWrite) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(fileToWrite.toPath(),
				Charset.forName("UTF-8"));
		writer.write(fileToWrite.getName());
	}

}
