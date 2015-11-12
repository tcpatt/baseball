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
package tmn.dev.project;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

/**
 * This class contains the main method for this project and acts as a control
 * handler for the project.
 * 
 * @author Taylor Patterson
 */
public class Controller {

	/**
	 * This method presents the user with a dialog to select files.
	 */
	private static List<File> getFileSelection() {
		// Construct a dialog for the user to select player data files to use
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select player data files");
		fileChooser.setMultiSelectionEnabled(true);

		// Present the dialog to the user
		int openFileReturn = fileChooser.showOpenDialog(null);

		// Collect the selected files
		List<File> files = null;
		if (JFileChooser.APPROVE_OPTION == openFileReturn) {
			files = Arrays.asList(fileChooser.getSelectedFiles());
		}
		return files;
	}

	/**
	 * This method attempts to create a list of {@link Player} objects for the
	 * list of input files.
	 * 
	 * @param files
	 *            The list of files from which to try to create {@link Player}
	 *            objects
	 * @return The list of {@link Player} objects created by this method
	 * @throws IOException
	 */
	private static List<Player> createPlayers(List<File> files)
			throws IOException {
		// Initialize a list of Player objects to return
		List<Player> players = new ArrayList<Player>();

		for (File file : files) {
			// Read the file and initialize a Player object from the contents
			Reader reader = Files.newBufferedReader(file.toPath());
			Gson gson = new Gson();
			Player player = gson.fromJson(reader, Player.class);
			// Add the Player to the list to be returned
			players.add(player);
		}
		return players;
	}

	/**
	 * This method loops over the list of input {@link Player} objects and
	 * attempts to write HTML files for the player data.
	 * 
	 * @param players
	 *            The list of {@link Player} objects to use to create HTML files
	 * @return The list of Strings for the path to the created files
	 * @throws IOException
	 */
	private static List<String> createHTMLFiles(List<Player> players)
			throws IOException {
		// Initialize a list of files to return
		List<String> files = new ArrayList<String>();

		// Create the String representation of the /html directory
		String htmlDirStr = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "html";

		// If the directory doesn't already exist, create it
		File htmlDir = new File(htmlDirStr);
		if (!htmlDir.exists() && !htmlDir.isDirectory())
			htmlDir.mkdir();

		// Create the player data HTML files
		for (Player player : players) {
			// Initialize a file
			String fileName = htmlDirStr + System.getProperty("file.separator")
					+ player.getId() + ".html";
			File fileToWrite = new File(fileName);
			// If the file already exists, append a number in parentheses
			int index = 0;
			while (!fileToWrite.createNewFile()) {
				// Append (<index>).html after the Player ID
				fileName = htmlDirStr + System.getProperty("file.separator")
						+ player.getId() + "(" + ++index + ")" + ".html";
				// Try to create a file with this name
				fileToWrite = new File(fileName);
			}

			// Write the player data to ./html/<player-id>.html
			player.writeToHTML(fileToWrite);

			// Add the filename to the return list
			files.add(fileToWrite.getAbsolutePath());
		}
		return files;
	}

	/*
	 * The main method for this project.
	 */
	public static void main(String[] args) {
		// Present a file selection dialog to the user.
		List<File> selectedFiles = getFileSelection();

		// Try to create Player(s) from the selected file(s)
		if (!selectedFiles.isEmpty()) {
			List<Player> players = null;
			try {
				players = createPlayers(selectedFiles);
			} catch (IOException e1) {
				// Notify the user that HTML files couldn't be created
				JOptionPane.showMessageDialog(null,
						"The selected file(s) could not be read as player "
								+ "data. The application will now terminate.",
						"Cannot Read File(s)", JOptionPane.ERROR_MESSAGE);
			}

			// Try to create the HTML file(s) for the Player(s)
			if (players != null && !players.isEmpty()) {
				List<String> htmlFileNames = null;
				try {
					htmlFileNames = createHTMLFiles(players);
					String fileListStr = "";
					for (String fileName : htmlFileNames)
						fileListStr += fileName + "\n";
					JOptionPane.showMessageDialog(null,
							"The following files were created:\n" + fileListStr,
							"Files Created", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					// Notify the user that HTML files couldn't be created
					JOptionPane.showMessageDialog(null,
							"Could not create HTML file(s) for the selected "
									+ "Player(s). The application will now "
									+ "terminate.",
							"HTML Creation Failure", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// No Player(s) created
				// Notify the user that the application will terminate
				JOptionPane.showMessageDialog(null,
						"Could not create Players from the selected file(s). "
								+ "The application will now terminate.",
						"Player Creation Failure", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// No files selected
			// Notify the user that the application will terminate
			JOptionPane.showMessageDialog(null,
					"No files were selected. The application will now "
							+ "terminate.",
					"No Selection", JOptionPane.ERROR_MESSAGE);
		}
	}
}