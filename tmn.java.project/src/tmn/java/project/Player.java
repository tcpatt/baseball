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
	private int id;

	/**
	 * List of games for this player
	 */
	private List<Game> games;

	/**
	 * Player's full name
	 */
	private String name;

	/**
	 * The String representation of the URL for a stock photo of the player
	 */
	private String image;

	/**
	 * The constructor
	 */
	public Player() {
		setId(-1);
		setGames(null);
		setName(null);
		setImagePath(null);
	}

	/**
	 * Retrieve the player ID
	 * 
	 * @return The ID for this Player
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the player ID
	 * 
	 * @param id
	 *            The value to set this Player's ID to
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieve this player's games
	 * 
	 * @return The List of {@link Game} objects for this Player
	 */
	public List<Game> getGames() {
		return games;
	}

	/**
	 * Set this player's games data
	 * 
	 * @param games
	 *            The List of {@link Game} objects to set for this Player
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
	 * @return The String representation of the URL that leads o photo of this
	 *         player
	 */
	public String getImagePath() {
		return image;
	}

	/**
	 * Set the URL for a photo of this player
	 * 
	 * @param imagePath
	 *            The String representation of the URL for a photo to set for
	 *            this player
	 */
	public void setImagePath(String imagePath) {
		this.image = imagePath;
	}

	/**
	 * Generate an HTML file containing the player's data in order to display
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
		writer.close();
	}
}
