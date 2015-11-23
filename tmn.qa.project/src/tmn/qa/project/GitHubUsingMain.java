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
	 * Variable declaration for the logger
	 */
	static Logger log = Logger.getLogger(GitHubUsingMain.class.getName());

	/**
	 * Traverse some pages starting from github.com
	 * 
	 * @param driver
	 *            The {@link WebDriver} object used to control browser
	 *            interactions
	 */
	public static void navigateGitHub(WebDriver driver) {
		// Navigate to github.com
		driver.get("https://github.com");
		log.info("Accessed 'https://github.com'");
		Assert.assertEquals(driver.getTitle(),
				"GitHub - Where software is " + "built");

		// Get the search field
		WebElement element = driver.findElement(By.name("q"));

		// Search for the repo containing this code
		element.sendKeys("tmn");
		element.submit();
		log.info("Searched for 'tmn'");

		// Click the link to the desired repository
		driver.findElement(By.linkText("tcpatt/tmn")).click();
		log.info("Clicked on 'tcpatt/tmn'");

		// Simple search for "TruMedia"
		element = driver.findElement(By.name("q"));
		element.sendKeys("TruMedia");
		element.submit();
		log.info("Executed a simple search on the current repository "
				+ "(tcpatt/tmn) for 'TruMedia'");

		// Search for "TruMedia" on all of GitHub
		driver.findElement(By.linkText("Search all of GitHub")).click();
		log.info("Clicked on 'Search all of GitHub'");

		// Advanced search for "TruMedia" in only the repo for this project
		driver.findElement(By.linkText("Advanced search")).click();
		log.info("Clicked on 'Advanced search'");
		element = driver.findElement(By.id("search_repos"));
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
	 * @param driver
	 *            The {@link WebDriver} object used to control browser
	 *            interactions
	 * @throws IOException
	 */
	public static void distributeLog(WebDriver driver) throws IOException {
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
		WebDriver driver = new FirefoxDriver();

		try {
			// Interact with github.com
			navigateGitHub(driver);
			// Share the logger data
			distributeLog(driver);
		} catch (NoSuchElementException e) {
			log.error("Could not find element.\n" + e.getMessage());
		} catch (IOException e) {
			log.error("Log file not found.\n" + e.getMessage());
		}

		// Close the browser
		driver.quit();
	}

}
