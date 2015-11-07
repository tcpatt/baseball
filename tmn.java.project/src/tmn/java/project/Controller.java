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

/**
 * This class contains the main method for this project and acts as a control
 * handler for the project.
 * 
 * @author Taylor Patterson
 */
public class Controller {

	/*
	 * The main method for this project.
	 */
	public static void main(String[] args) {

		// Present a dialog to the user to select player data files to use
		final JFileChooser fileChooser = new JFileChooser();

		// Allow for multiple file selection and present the dialog to the user
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setVisible(true);

		// Collect the user's file selection
		List<File> files = Arrays.asList(fileChooser.getSelectedFiles());

		// Loop over the list of selected files, create a Player objects for
		// each file, and generate an HTML file for each Player
		for (File file : files) {
			Player player = new Player(file);
			player.writeToHTML("path/to/working/directory/html/filename.html");
		}
	}

}
