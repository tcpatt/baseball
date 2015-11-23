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
package tmn.qa.project;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * This class provides a simple demonstration of Selenium {@link WebDriver}
 * using a main() method as the entry point. It demonstrates some
 * {@link FirefoxDriver} page navigation and form manipulation. Logging is
 * provided by log4j.
 * 
 * @author Taylor Patterson
 */
public class GitHubUsingMain {

	/**
	 * The log4j logger
	 */
	static Logger log = Logger.getLogger(GitHubUsingMain.class.getName());

	/**
	 * The {@link WebDriver} used for these tests
	 */
	private static WebDriver driver;

	/**
	 * The number of files found via simple search
	 */
	private static String simpleSearchCount;

	/**
	 * Test the browsers ability to navigate to GitHub and log some verification
	 * checks
	 */
	private static void goToGitHub() {
		// Navigate to github.com
		driver.get("https://github.com");
		Assert.assertEquals(driver.getTitle(),
				"GitHub · Where software is built");
		log.info("Accessed 'https://github.com'. Page title is: '"
				+ driver.getTitle() + "'.");
	}

	/**
	 * Use the main search field to find repositories containing the string
	 * "tmn"
	 */
	private static void repoSearch() {
		// Get the search field
		WebElement element = driver.findElement(By.name("q"));
		Assert.assertNotNull(element);
		Assert.assertEquals(element.getAttribute("placeholder"),
				"Search GitHub");
		log.info("Accessed the site search field. Placeholder text is: '"
				+ element.getAttribute("placeholder") + "'.");

		// Search for the repository containing this code
		element.sendKeys("tmn");
		element.submit();
		log.info("Searched the site for 'tmn'");
	}

	/**
	 * Find the search result for the repository "tcpatt/tmn" and click on the
	 * link
	 */
	private static void resultsSelect() {
		// Find the link
		WebElement element = driver.findElement(By.linkText("tcpatt/tmn"));
		Assert.assertNotNull(element);
		Assert.assertEquals(element.getAttribute("href"),
				"https://github.com/tcpatt/tmn");
		log.info("Found the link to '" + element.getText() + "'.");
		// Click the link to the desired repository
		element.click();
		log.info("Clicked on the link to 'tcpatt/tmn'");
	}

	/**
	 * Search the tcpatt/tmn repository for occurrences of "TruMedia" and make
	 * some assertions about the results
	 */
	private static void simpleSearch() {
		// Make sure we are on the correct page
		Assert.assertEquals(driver.getTitle(), "tcpatt/tmn · GitHub");
		log.info("Active page title: '" + driver.getTitle() + "'.");
		// Get the search field
		WebElement element = driver.findElement(By.name("q"));
		Assert.assertNotNull(element);
		Assert.assertEquals(element.getAttribute("placeholder"), "Search");
		Assert.assertEquals(element.getAttribute("aria-label"),
				"Search this repository");
		// Send the search term
		element.sendKeys("TruMedia");
		element.submit();
		log.info("Executed a simple search on the current repository "
				+ "(tcpatt/tmn) for 'TruMedia'");
		// Check the count of files containing "TruMedia" in this repository
		element = driver.findElement(By.className("counter"));
		simpleSearchCount = element.getText();
		log.info("Found " + simpleSearchCount + " files containing "
				+ "'TruMedia'.");
	}

	/**
	 * Expand the previous search for "TruMedia" to all of GitHub and make some
	 * assertions about the results
	 */
	private static void expandSearch() {
		// Make sure we are on the correct page
		Assert.assertEquals(driver.getTitle(), "Search Results · GitHub");
		log.info("Active page title: '" + driver.getTitle() + "'.");
		// Locate and click the link to expand the search to all of GitHub
		WebElement element = driver
				.findElement(By.linkText("Search all of GitHub"));
		Assert.assertNotNull(element);
		element.click();
		log.info("Clicked on 'Search all of GitHub'");
	}

	/**
	 * Navigate to the advanced search page and enter some values into the
	 * fields. Conduct the advanced search.
	 */
	private static void advancedSearch() {
		// Make sure we are on the correct page
		Assert.assertEquals(driver.getTitle(), "Search · TruMedia · GitHub");
		log.info("Active page title: '" + driver.getTitle() + "'.");
		// Find the advanced search link
		WebElement element = driver.findElement(By.linkText("Advanced search"));
		Assert.assertNotNull(element);
		element.click();
		log.info("Clicked on 'Advanced search'");
		// Edit the advanced search form
		element = driver.findElement(By.id("search_repos"));
		Assert.assertNotNull(element);
		element.sendKeys("tcpatt/tmn");
		log.info("Entered 'tcpatt/tmn' into the text field for 'In these "
				+ "repositories'");
		Select dropdown = new Select(
				driver.findElement(By.id("search_language")));
		Assert.assertNotNull(dropdown);
		Assert.assertTrue(!dropdown.getOptions().isEmpty());
		dropdown.selectByValue("Java");
		log.info("Selected 'Java' in the 'Written in this language' drop down");
		element.submit();
		log.info("Searching based on the form values entered.");
		// Check the count of files containing "TruMedia" in this repository
		element = driver.findElement(By.className("counter"));
		Assert.assertEquals(element.getText(), simpleSearchCount);
		log.info("Simple search and advanced search both return "
				+ simpleSearchCount + " files containing 'TruMedia'.");
	}

	/**
	 * Navigate to TruMedia Networks' web site and send them the assertion log
	 * 
	 * @throws IOException
	 */
	private static void distributeLog() throws IOException {
		// Direct the browser to trumedianetworks.com
		driver.get("http://www.trumedianetworks.com");

		// Click on the mail icon
		driver.findElement(By.id("email")).click();

		// Complete the form to contact TruMedia Networks
		WebElement element = driver.findElement(By.name("fname"));
		element.sendKeys("Taylor");
		element = driver.findElement(By.name("lname"));
		element.sendKeys("Patterson");
		element = driver.findElement(By.id("email2-field"));
		element.sendKeys("tcpatt@gmail.com");

		// Write the log to the message text area
		element = driver.findElement(By.id("textarea1-field"));
		// Message header
		element.sendKeys("Here are the contents of the log for the test of "
				+ "github.com:\n");
		// Duplicate the log
		String logFilePath = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "logs"
				+ System.getProperty("file.separator") + "GitHubTest.log";
		BufferedReader logReader = new BufferedReader(
				new java.io.FileReader(logFilePath));
		String line = null;
		while ((line = logReader.readLine()) != null) {
			element.sendKeys("\n" + line);
		}
		logReader.close();

		// Send the message
		driver.findElement(By.className("button"));
	}

	/**
	 * The main method for this Java project
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Set the logger configuration
		DOMConfigurator.configure("log4j.xml");

		// Create a Firefox driver
		driver = new FirefoxDriver();

		try {
			// Interact with github.com
			goToGitHub();
			repoSearch();
			resultsSelect();
			simpleSearch();
			expandSearch();
			advancedSearch();
			// Share the logger data
			distributeLog();
		} catch (NoSuchElementException e) {
			log.error("Could not find element.\n" + e.getMessage());
		} catch (IOException e) {
			log.error("Log file not found.\n" + e.getMessage());
		}

		// Close the browser
		driver.quit();

		// Notify the user of completion
		System.out.println("\n\n *** Test complete. ***\n Log written to "
				+ System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "logs"
				+ System.getProperty("file.separator") + "GitHubTest.log");
	}

}
