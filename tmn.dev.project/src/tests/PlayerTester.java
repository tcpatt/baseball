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
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tmn.dev.project.Game;
import tmn.dev.project.Player;

/**
 * This class defines the JUnit tests for {@link Player} objects.
 * 
 * @author Taylor Patterson
 */
public class PlayerTester {

	/**
	 * The {@link Player} object to be used by all of the tests
	 */
	private Player testPlayer;

	/**
	 * Initialize a {@link Player} object for the tests to use.
	 */
	@Before
	public void initializePlayer() {
		testPlayer = new Player();
	}

	/**
	 * Test method for {@link Player#setId(int)} and {@link Player#getId()}.
	 */
	@Test
	public void testSetAndGetId() {
		assert (-1 == testPlayer.getId());
		int testId = 99;
		testPlayer.setId(testId);
		assert (testId == testPlayer.getId());
	}

	/**
	 * Test method for {@link Player#setGames(java.util.List)} and
	 * {@link Player#getGames()}.
	 */
	@Test
	public void testSetAndGetGames() {
		assertNull(testPlayer.getGames());
		List<Game> testGames = new ArrayList<Game>();
		testPlayer.setGames(testGames);
		assertNotNull(testPlayer.getGames());
		assertEquals(testGames, testPlayer.getGames());
		assertTrue(testPlayer.getGames().isEmpty());
		Game game1 = new Game();
		Game game2 = new Game();
		testGames.add(game1);
		testGames.add(game2);
		assertFalse(testPlayer.getGames().isEmpty());
		assertEquals(testGames, testPlayer.getGames());
	}

	/**
	 * Test method for {@link Player#setName(String)} and
	 * {@link Player#getName()}.
	 */
	@Test
	public void testSetAndGetName() {
		assertNull(testPlayer.getName());
		String testName = "Ricky Vaughn";
		testPlayer.setName(testName);
		assertEquals(testName, testPlayer.getName());
	}

	/**
	 * Test method for {@link Player#setImagePath(String)} and
	 * {@link Player#getImagePath()}.
	 */
	@Test
	public void testSetAndGetImagePath() {
		assertNull(testPlayer.getImagePath());
		String testPath = "https://fake.url/wild_thing";
		testPlayer.setImagePath(testPath);
		assertEquals(testPath, testPlayer.getImagePath());
	}

}
