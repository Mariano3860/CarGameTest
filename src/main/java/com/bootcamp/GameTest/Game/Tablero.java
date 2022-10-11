package com.bootcamp.GameTest.Game;


public class Tablero {

    private int[][] tablero;

    public Tablero(int row, int col,int perRocks, int perDeath) {
        tablero = new int[row][col];
        this.init(perRocks,perDeath);
    }

    private Tablero(int[][] matrix) {
        this.tablero = matrix;
    }

    public static Tablero buildTablero(int[][] matrix) {
        return new Tablero(matrix);
    }

    public void init(double perRocks,double perDeath){
        double rocks = perRocks/100;
        double death = rocks + (perDeath/100);
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[i].length;j++){
                double rnd = Math.random();
                if (rnd < rocks){
                    tablero[i][j] = 1;
                } else if (rnd < death) {
                    tablero[i][j] = 2;
                } else {
                    tablero[i][j] = 0;
                }
            }
        }
    }

    public boolean availablePosition(int row, int col){
        if (row-1 <0){
            row = tablero.length-1;
        }
        if (col-1 <0){
            col = tablero[row].length-1;
        }
        if (tablero[row][col]==1){
            return false;
        } else if (tablero[row][col]==2){
            return false;
        } else if (tablero[row-1][col]==2 && tablero[row][col-1]==2 && tablero[row][col+1]==2 && tablero[row+1][col]==2) {
            return false;
        }
        return true;
    }

    public void renderTablero(int rowCar, int colCar){
        for(int i=0;i<tablero.length;i++){
            System.out.print("|");
            for(int j=0;j<tablero[i].length;j++) {
                if (i==rowCar && j==colCar){
                    System.out.print(7);
                } else {
                    System.out.print(tablero[i][j]);
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
}
