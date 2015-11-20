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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Taylor Patterson
 */
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a Firefox driver
		WebDriver driver = new FirefoxDriver();

		// Navigate to github.com
		driver.get("https://github.com");

		// Get the search field
		WebElement element = driver.findElement(By.name("q"));

		// Search for the repo containing this code
		element.sendKeys("tmn");
		element.submit();

		// Click the link to the desired repository
		driver.findElement(By.linkText("tcpatt/tmn")).click();

		// Simple search for TruMedia
		element = driver.findElement(By.name("q"));
		element.sendKeys("TruMedia");
		element.submit();

		// Advanced search for TruMedia
		driver.findElement(By.linkText("advanced search")).click();
		element = driver.findElement(By.id("search_repos"));
		element.sendKeys("tcpatt/tmn");
		Select dropdown = new Select(
				driver.findElement(By.id("search_language")));
		dropdown.selectByValue("Java");
		element.submit();

		// Navigate to the test file
		// driver.findElement(By.id("d4728e20f195aa3992a6172487f5f91f-abf49617197"
		// + "196df17708d1c9fe79bbed69862ec")).click();
		// element = driver.findElement(By.className("file"));
		// System.out.println(element.getText());

		driver.get("http://www.trumedianetworks.com");
		driver.findElement(By.id("email")).click();
		element = driver.findElement(By.name("fname"));
		element.sendKeys("Taylor");
		element = driver.findElement(By.name("lname"));
		element.sendKeys("Patterson");
		element = driver.findElement(By.id("email2-field"));
		element.sendKeys("tcpatt@gmail.com");
		element = driver.findElement(By.id("textarea1-field"));
		element.sendKeys("Test message");

		// Close the browser
		driver.quit();
	}

}
