package com.bootcamp.GameTest;

import com.bootcamp.GameTest.Game.Tablero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GameTestApplicationTests {

	@Test
	void buildTablero_ReturnANotNullTablero_WhenSuccess() {
		var matrix = new int[][] {{},{}};
		var tablero = Tablero.buildTablero(matrix);

		assertNotNull(tablero);
		assertNotNull(tablero.getTablero());
	}

	@Test
	void buildTablero_ReturnATableroWithA2X2Matrix_WhenSuccess() {
		var matrix = new int[][] {{0,1},{0,1}};
		var tablero = Tablero.buildTablero(matrix);

		assertTrue(tablero.getTablero().length == 2);
		assertTrue(tablero.getTablero()[0].length == 2);
		assertTrue(tablero.getTablero()[1].length == 2);
	}
}
