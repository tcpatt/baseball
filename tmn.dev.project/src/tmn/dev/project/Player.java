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

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
	 * The running total of RBI
	 */
	private int[] rbiRunTot;

	/**
	 * The running total of AB
	 */
	private int[] abRunTot;

	/**
	 * The running total of H
	 */
	private int[] hRunTot;

	/**
	 * The running total of HR
	 */
	private int[] hrRunTot;

	/**
	 * The computed batting average following each game. The x values in the
	 * series represent the game number, and the y values represent the player's
	 * batting average following that game.
	 */
	private XYSeries baGame;

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
	 * Loop over this Player's list of {@link Game} objects and compute the
	 * running total of H, AB, HR, and RBI. Also compute the running batting
	 * average (H/AB) at the completion of each game.
	 */
	private void computeTotalsAndAvg() {
		// There is nothing to do with no games
		if (games.isEmpty())
			return;

		// Initialize the running total variables
		rbiRunTot = new int[games.size()];
		abRunTot = new int[games.size()];
		hRunTot = new int[games.size()];
		hrRunTot = new int[games.size()];
		baGame = new XYSeries("Batting Average");

		// Set the value for the first game
		rbiRunTot[0] = games.get(0).getRbi();
		abRunTot[0] = games.get(0).getAtBats();
		hRunTot[0] = games.get(0).getHits();
		hrRunTot[0] = games.get(0).getHomeRuns();
		baGame.add(1, (float) hRunTot[0] / abRunTot[0]);

		// Loop over the games to add to the running total vectors
		for (int i = 1; i < games.size(); i++) {
			rbiRunTot[i] = rbiRunTot[i - 1] + games.get(i).getRbi();
			abRunTot[i] = abRunTot[i - 1] + games.get(i).getAtBats();
			hRunTot[i] = hRunTot[i - 1] + games.get(i).getHits();
			hrRunTot[i] = hrRunTot[i - 1] + games.get(i).getHomeRuns();
			baGame.add(i + 1, (float) hRunTot[i] / abRunTot[i]);
		}
	}

	/**
	 * Generate a plot of batting average over the span of a season and write
	 * the plot to a PNG file.
	 * 
	 * @return The String representation of the path to the PNG file containing
	 *         the plot.
	 */
	private String createBAPlot() {

		JFrame frame = new JFrame("Plot Application");

		JFreeChart chart = ChartFactory.createScatterPlot(
				"Batting Average Throughout the Season", "Game Number",
				"Batting Average", new XYSeriesCollection(baGame),
				PlotOrientation.VERTICAL, false, false, false);
		XYPlot plot = chart.getXYPlot();
		NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
		xAxis.setRange(baGame.getMinX(), baGame.getMaxX());
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setRange(baGame.getMinY() - .02, baGame.getMaxY() + .02);
		yAxis.setNumberFormatOverride(new DecimalFormat(".000"));

		ChartPanel panel = new ChartPanel(chart, true);
		panel.setPreferredSize(new java.awt.Dimension(800, 450));
		panel.setMinimumDrawHeight(10);
		panel.setMaximumDrawHeight(2000);
		panel.setMinimumDrawWidth(20);
		panel.setMaximumDrawWidth(2000);

		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);

		return null;
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
		// Compute the values we will need
		computeTotalsAndAvg();

		// Create the batting average over time plot
		String baPlotPath = createBAPlot();

		// Initialize the file writer
		BufferedWriter writer = Files.newBufferedWriter(fileToWrite.toPath(),
				Charset.forName("UTF-8"));

		// Write the HTML file header
		writeHeader(writer);

		// Write the player data to HTML
		writeStatBoxes(writer);

		// Write the batting average plot
		writeBAPlot(writer, baPlotPath);

		// Write the NTML file footer
		writeFooter(writer);

		// Close the file
		writer.close();
	}

	/**
	 * Write the HTML file header for the file created by the writeToHTML(File)
	 * method.
	 * 
	 * @param writer
	 *            The Writer that is writing the HTML file
	 * @throws IOException
	 */
	private void writeHeader(BufferedWriter writer) throws IOException {
		writer.write("<!DOCTYPE html>\n<html>\n<head>\n<title>" + name
				+ "</title>\n<link rel=\"stylesheet\" type=\"text/css\" "
				+ "href=\"../css/playerCard.css\"></head>\n<body>");
	}

	/**
	 * Write the statistics to the HTML file.
	 * 
	 * @param writer
	 *            The Writer that is writing the HTML file
	 * @throws IOException
	 */
	private void writeStatBoxes(BufferedWriter writer) throws IOException {
		writer.write("<div class=\"banner\"><h1 id=\"name\">" + name
				+ "</h1></div>");

		writer.write("<div class=\"row\">");

		writer.write("<div class=\"col-qrtr stat\"><h2 id=\"hits\">H<br />"
				+ hRunTot[hRunTot.length - 1] + "</h2></div>");
		writer.write("<div class=\"col-qrtr stat\"><h2 id=\"atBats\">AB<br />"
				+ abRunTot[abRunTot.length - 1] + "</h2></div>");
		writer.write("<div class=\"col-qrtr stat\"><h2 id=\"rbi\">RBI<br />"
				+ rbiRunTot[rbiRunTot.length - 1] + "</h2></div>");
		writer.write("<div class=\"col-qrtr stat\"><h2 id=\"homeRuns\">HR<br />"
				+ hrRunTot[hrRunTot.length - 1] + "</h2></div>");

		writer.write("</div>");

		// Format the final batting average to the typical .xxx format
		String BA = String.format("%.3g%n",
				baGame.getY(baGame.getItemCount() - 1));
		BA = BA.substring(1);

		writer.write("<div class=\"col-full stat\"><h2 id=\"avg\">BA<br />" + BA
				+ "</h2></div>");
	}

	/**
	 * Write the batting average plot information in the HTML file.
	 * 
	 * @param writer
	 *            The Writer that is writing the HTML file
	 * @param baPlotPath
	 *            The String representation of the path to the file containing
	 *            the plot of batting average over the span of a season
	 */
	private void writeBAPlot(BufferedWriter writer, String baPlotPath) {
	}

	/**
	 * Write the HTML file footer for the file created by the writeToHTML(File)
	 * method.
	 * 
	 * @param writer
	 *            The Writer that is writing the HTML file
	 * @throws IOException
	 */
	private void writeFooter(BufferedWriter writer) throws IOException {
		writer.write("</body>\n</html>");
	}

}
