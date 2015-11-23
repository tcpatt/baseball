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

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

/**
 * This class provides a simple demonstration of Selenium {@link WebDriver}
 * using the TestNG framework. It demonstrates some {@link FirefoxDriver} page
 * navigation and form manipulation. Logging is provided by log4j in addition to
 * the reporting provided by TestNG.
 * 
 * @author Taylor Patterson
 */
public class GitHubTestNG {

	/**
	 * The log4j logger
	 */
	static Logger log = Logger.getLogger(GitHubUsingMain.class.getName());

	/**
	 * The {@link WebDriver} used for these tests
	 */
	WebDriver driver;

	/**
	 * Initialize the {@link WebDriver} instance and set the logger
	 * configuration
	 */
	@BeforeTest
	public void setup() {
		// Set the logger configuration
		DOMConfigurator.configure("log4j.xml");

		// Create a Firefox driver
		driver = new FirefoxDriver();
	}

	/**
	 * Test the browsers ability to navigate to GitHub and log some verification
	 * checks
	 */
	@Test(priority = 0)
	public void goToGitHubTest() {
		// Navigate to github.com
		driver.get("https://github.com");
		Assert.assertEquals(driver.getTitle(),
				"GitHub · Where software is built");
		log.info("Accessed 'https://github.com'");
	}

	/**
	 * Use the main search field to find repositories containing the string
	 * "tmn"
	 */
	@Test(priority = 1)
	public void repoSearchTest() {
		// Get the search field
		WebElement element = driver.findElement(By.name("q"));

		// Search for the repo containing this code
		element.sendKeys("tmn");
		element.submit();
		log.info("Searched for 'tmn'");
	}

	/**
	 * Find the search result for the repository "tcpatt/tmn" and click on the
	 * link
	 */
	@Test(priority = 2)
	public void resultsSelectTest() {
		// Click the link to the desired repository
		driver.findElement(By.linkText("tcpatt/tmn")).click();
		log.info("Clicked on 'tcpatt/tmn'");
	}

	/**
	 * Search the tcpatt/tmn repository for occurrences of "TruMedia" and make
	 * some assertions about the results
	 */
	@Test(priority = 3)
	public void simpleSearchTest() {
		// Simple search for "TruMedia"
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("TruMedia");
		element.submit();
		log.info("Executed a simple search on the current repository "
				+ "(tcpatt/tmn) for 'TruMedia'");
	}

	/**
	 * Expand the previous search for "TruMedia" to all of GitHub and make some
	 * assertions about the results
	 */
	@Test(priority = 4)
	public void expandSearchTest() {
		// Search for "TruMedia" on all of GitHub
		driver.findElement(By.linkText("Search all of GitHub")).click();
		log.info("Clicked on 'Search all of GitHub'");
	}

	/**
	 * Navigate to the advanced search page and enter some values into the
	 * fields. Conduct the advanced search.
	 */
	@Test(priority = 5)
	public void advancedSearchTest() {
		// Advanced search for "TruMedia" in only the repo for this project
		driver.findElement(By.linkText("Advanced search")).click();
		log.info("Clicked on 'Advanced search'");
		WebElement element = driver.findElement(By.id("search_repos"));
		element.sendKeys("tcpatt/tmn");
		log.info("Entered 'tcpatt/tmn' into the text field for 'In these "
				+ "repositories'");
		Select dropdown = new Select(
				driver.findElement(By.id("search_language")));
		dropdown.selectByValue("Java");
		log.info("Selected 'Java' in the 'Written in this language' dropdown");
		element.submit();
	}

	/**
	 * Navigate to TruMedia Networks' web site and send them the assertion log
	 * 
	 * @throws IOException
	 */
	public void distributeLog() throws IOException {
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
	 * Distribute the log file and close the Firefox test browser
	 * 
	 * @throws IOException
	 */
	@AfterTest
	public void tearDown() {
		// Distribute the log
		// try {
		// distributeLog();
		// } catch (IOException e) {
		// log.error("Log file not found.\n" + e.getMessage());
		// }
		// Close Firefox driver
		driver.close();
	}

}
