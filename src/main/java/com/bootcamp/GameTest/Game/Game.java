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
        //Inicializar Mapeado con todas las posiciones distintas a 0,1,2 para saber por donde va pasando el auto
        //A medida que lo recorre, lo transforma en 0, 1 y 2.
        //Por ahora lo inicializo en -1 exepto que haya algo mas eficiente
        for(int i=0;i<mapeado.length;i++){
            for(int j=0;j<mapeado[i].length;j++) {
                mapeado[i][j]= -1;
            }
        }

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

            //Chequear los 4 lugares a los costados (der, iz, arr, abajo), si ">0", buscar en Tablero si es "2", si es "2" anotarlo en mapeado
            //Cambiar mapa de badpositions por la matriz mapeado
            //Pedir random move y chequear que no haya "1" o "2" mapeados, si hay, pedir nuevas posiciones
            //Mapeado es igual al tablero real? => "Descubrio el mapa entero"
            //Despues de 999 movimientos si mapeado!=tablero => No pudo descubrir el mapa entero
            //Life<0 => Murio el auto
            //Opcional: Buscar una funcion mas eficiente para chequear si mapeado!=tablero

            this.moveCar(currentRow,currentCol);
            pos = tablero.getTablero()[currentRow][currentCol];
            switch (pos){
                case 0:
                    tablero.getTablero()[currentRow][currentCol] = 7;
                    break;
                case 1:
                    car.addBadPosition(currentRow, currentCol);
                    car.decreaseLife();
                    //Agregar a la matriz mapear la posicion con 1
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
