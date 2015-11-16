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

import org.junit.Before;
import org.junit.Test;

import tmn.dev.project.Game;

/**
 * This class defines the JUnit tests for {@link Game} objects.
 * 
 * @author Taylor Patterson
 */
public class GameTester {

	/**
	 * The {@link Game} object to be used by all of the tests
	 */
	private Game testGame;

	/**
	 * Initialize a {@link Game} object for the tests to use.
	 */
	@Before
	public void initializeGame() {
		testGame = new Game();
	}

	/**
	 * Test method for {@link Game#setRbi(int)} and {@link Game#getRbi()}.
	 */
	@Test
	public void testSetAndGetRbi() {
		assert(-1 == testGame.getRbi());
		int testRbi = 1;
		testGame.setRbi(testRbi);
		assert(testRbi == testGame.getRbi());
	}

	/**
	 * Test method for {@link Game#setAtBats(int)} and {@link Game#getAtBats()}.
	 */
	@Test
	public void testSetAndGetAtBats() {
		assert(-1 == testGame.getAtBats());
		int testAb = 2;
		testGame.setAtBats(testAb);
		assert(testAb == testGame.getAtBats());
	}

	/**
	 * Test method for {@link Game#setHits(int)} and {@link Game#getHits()}.
	 */
	@Test
	public void testSetAndGetHits() {
		assert(-1 == testGame.getHits());
		int testH = 3;
		testGame.setHits(testH);
		assert(testH == testGame.getHits());
	}

	/**
	 * Test method for {@link Game#setOpponentImg(String)} and
	 * {@link Game#getOpponentImg()}.
	 */
	@Test
	public void testSetAndGetOpponentImg() {
		assertNull(testGame.getOpponentImg());
		String testPath = "https://fake.url/det.png";
		testGame.setOpponentImg(testPath);
		assertEquals(testPath, testGame.getOpponentImg());
	}

	/**
	 * Test method for {@link Game#setOpponentId(String)} and
	 * {@link Game#getOpponentId()}.
	 */
	@Test
	public void testSetAndGetOpponentId() {
		assertNull(testGame.getOpponentId());
		String testId = "DET";
		testGame.setOpponentId(testId);
		assertEquals(testId, testGame.getOpponentId());
	}

	/**
	 * Test method for {@link Game#setHomeRuns(int)} and
	 * {@link Game#getHomeRuns()}.
	 */
	@Test
	public void testSetAndGetHomeRuns() {
		assert(-1 == testGame.getHomeRuns());
		int testHr = 4;
		testGame.setHomeRuns(testHr);
		assert(testHr == testGame.getHomeRuns());
	}

	/**
	 * Test method for {@link Game#setPlayerTeamId(String)} and
	 * {@link Game#getPlayerTeamId()}.
	 */
	@Test
	public void testSetAndGetPlayerTeamId() {
		assertNull(testGame.getPlayerTeamId());
		String testId = "CLE";
		testGame.setPlayerTeamId(testId);
		assertEquals(testId, testGame.getPlayerTeamId());
	}

	/**
	 * Test method for {@link Game#setPlayerTeamImg(String)} and
	 * {@link Game#getPlayerTeamImg()}.
	 */
	@Test
	public void testSetAndGetPlayerTeamImg() {
		assertNull(testGame.getPlayerTeamImg());
		String testPath = "https://fake.url/cle.png";
		testGame.setPlayerTeamImg(testPath);
		assertEquals(testPath, testGame.getPlayerTeamImg());
	}

	/**
	 * Test method for {@link Game#setGameDate(String)} and
	 * {@link Game#getGameDate()}.
	 */
	@Test
	public void testSetAndGetGameDate() {
		assertNull(testGame.getGameDate());
		String testDate = "08-02-1989";
		testGame.setGameDate(testDate);
		assertEquals(testDate, testGame.getGameDate());
	}

}
