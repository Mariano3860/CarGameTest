package com.bootcamp.GameTest;

import com.bootcamp.GameTest.Game.Game;
import com.bootcamp.GameTest.Game.Tablero;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameTestApplication.class);
		Game game = new Game();
	}

}
