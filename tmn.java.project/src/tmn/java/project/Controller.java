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

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This class contains the main method for this project and acts as a control
 * handler for the project.
 * 
 * @author Taylor Patterson
 */
public class Controller {

	/**
	 * This method presents the user with a dialog to select files with which to
	 * create {@link Player} objects and HTML files.
	 */
	private static void presentFileChooser() {
		// Construct a dialog for the user to select player data files to use
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select player data files");
		fileChooser.setMultiSelectionEnabled(true);

		// Present the dialog to the user
		int openFileReturn = fileChooser.showOpenDialog(null);

		// Continue if the user selected one or more files
		if (JFileChooser.APPROVE_OPTION == openFileReturn) {
			// Collect the user's file selection and attempt to create HTML
			// files for the player data
			List<File> files = Arrays.asList(fileChooser.getSelectedFiles());
			createHTMLFiles(files);
		} else {
			// No files selected
			// Notify the user that the application will terminate
			JOptionPane.showMessageDialog(null,
					"No files were selected. The application will now terminate.",
					"No selection", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method loops over the list of input files and attempts to create
	 * {@link Player} objects for the input and then write HTML files for the
	 * player data.
	 * 
	 * @param files
	 *            The list of files to use to try to create {@link Player}
	 *            objects and HTML files
	 */
	private static void createHTMLFiles(List<File> files) {
		for (File file : files) {
			Player player = new Player(file);
			player.writeToHTML("path/to/working/directory/html/filename.html");
		}
	}

	/*
	 * The main method for this project.
	 */
	public static void main(String[] args) {
		// Present a file selection dialog to the user.
		// Control is handled elsewhere from here.
		presentFileChooser();
	}

}
