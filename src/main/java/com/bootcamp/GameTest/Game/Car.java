package com.bootcamp.GameTest.Game;

import java.util.HashMap;
import java.util.Map;

public class Car {
    private int life;

    public Car(int life) {
        this.life = life;
    }

    public void decreaseLife(){
        life--;
    }

    public void death(){
        life=0;
        System.out.println("You are fucking dead");
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}