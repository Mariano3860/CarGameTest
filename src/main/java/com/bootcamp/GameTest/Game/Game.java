package com.bootcamp.GameTest.Game;


import java.util.Arrays;

public class Game {
    private final Tablero tablero;

    private int[][] mapeado;
    private int currentCol;
    private int currentRow;
    private int life;
    private int tableroRow;
    private int tableroCol;

    public Game() {
        tableroRow = 8;
        tableroCol = 8;
        life = 10;
        tablero = new Tablero(tableroRow, tableroCol, 10, 5);
        mapeado = new int[tableroRow][tableroCol];

        //Entregar posiciones iniciales vacias
        currentRow = (int) (Math.random() * tableroRow);
        currentCol = (int) (Math.random() * tableroCol);
        while (!tablero.availablePosition(currentRow, currentCol)) {
            currentRow = (int) (Math.random() * tableroRow);
            currentCol = (int) (Math.random() * tableroCol);
        }
        //Renderizar el tablero+auto inicial
        tablero.renderTablero(currentRow, currentCol);

        //Loop mover auto, incluye muerte, y entrega resultados
        var car = new Car(life);
        while(car.getLife()>0){
            int lastRow = currentRow;
            int lastCol = currentCol;
            int pos;

            //Chequear si hay vecinos (der, iz, arr, abajo) con "2" y anotarlo en mapeado
            //Cambiar mapa de badpositions por la matriz mapeado
            //Pedir random move y chequear que no haya "2" mapeados, si hay "2", pedir nuevas posiciones
            //Mapeado es igual al tablero real? => descubrio el mapa entero
            //Despueas de 999 movimientos mapeado!=tablero => No pudo descubrir el mapa entero
            //Life<0 => Murio el auto

            this.moveCar(currentRow,currentCol);
            pos = tablero.getTablero()[currentRow][currentCol];
            switch (pos){
                case 0:
                    tablero.getTablero()[currentRow][currentCol] = 7;
                    break;
                case 1:
                    car.addBadPosition(currentRow, currentCol);
                    currentRow = lastRow;
                    currentCol = lastCol;
                    break;
                case 2:
                    car.death();
            }

        }
    }

    void moveCar(int row,int col){
        int rnd = (int)(Math.random() * 4);
        switch (rnd) {
            //izquierda
            case 0:
                row--;
                if (row<0){
                    row = tablero.getTablero().length - 1;
                }
                break;
            //arriba
            case 1:
                col--;
                if (col<0){
                    col = tablero.getTablero()[row].length - 1;
                }
                break;
            //derecha
            case 2:
                row++;
                if (row>(tablero.getTablero().length - 1)){
                    row = 0;
                }
                break;
            case 3:
                col++;
                if (col>(tablero.getTablero()[row].length - 1)){
                    col = 0;
                }
        }
        this.currentRow = row;
        this.currentCol = col;
    }
}
