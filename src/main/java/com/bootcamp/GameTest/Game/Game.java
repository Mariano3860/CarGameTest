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
        tablero = new Tablero(tableroRow, tableroCol, 12, 10);
        mapeado = new int[tableroRow][tableroCol];
        //Inicializar Mapeado con todas las posiciones distintas a 0,1,2 para saber por donde va pasando el auto
        //A medida que lo recorre, lo transforma en 0, 1 y 2.
        //Por ahora lo inicializo en -1 exepto que haya algo mas eficiente
        for (int i = 0; i < mapeado.length; i++) {
            for (int j = 0; j < mapeado[i].length; j++) {
                mapeado[i][j] = -1;
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
        int count = 0;
        int count2 = 0;
        int maxcount = 2000;
        while (car.getLife() > 0 || count < maxcount) {
            count++;
            int lastRow = currentRow;
            int lastCol = currentCol;
            int pos;

            //Chequear los 4 lugares a los costados (der, iz, arr, abajo), si es "2" anotarlo en mapeado
            this.MapearCostados(currentRow, currentCol);
            //Pedir random move y chequear que no haya "1" o "2" mapeados, si hay, pedir nuevas posiciones
            boolean good = false;
            while (!good || count2 < maxcount) {
                count2++;
                int origRow = currentRow;
                int origCol = currentCol;
                this.moveCar(currentRow, currentCol, "rnd");
                pos = mapeado[currentRow][currentCol];
                switch (pos) {
                    case -1:
                    case 0:
                        good = true;
                        break;
                    case 1:
                    case 2:
                        currentRow = origRow;
                        currentCol = origCol;
                        break;
                }
            }
            //Con la nueva posicion valida, mapeame donde esta
            pos = tablero.getTablero()[currentRow][currentCol];
            switch (pos) {
                case 0:
                    mapeado[currentRow][currentCol] = 0;
                    tablero.getTablero()[currentRow][currentCol] = 7;
                    break;
                case 1:
                    mapeado[currentRow][currentCol] = 1;
                    car.decreaseLife();
                    currentRow = lastRow;
                    currentCol = lastCol;
                    System.out.println();
                    this.renderMapeado(currentRow, currentCol);
                    break;
                case 2:
                    car.death();
                    this.renderMapeado(currentRow, currentCol);
                    System.out.println(count);
            }

            //Mapeado es igual al tablero real? => "Descubrio el mapa entero"
            boolean descubrio = false;
            for (int i = 0; i < mapeado.length; i++) {
                for (int j = 0; j < mapeado[i].length; j++) {
                    if (mapeado[i][j] == tablero.getTablero()[i][j]) {
                        descubrio = true;
                    } else if (mapeado[i][j] == 7 || tablero.getTablero()[i][j] == 7) {
                        descubrio = true;
                    } else {
                        descubrio = false;
                        break;
                    }
                }
                if (!descubrio){
                    break;
                }
            }
            if (descubrio) {
                System.out.println();
                this.renderMapeado(currentRow, currentCol);
                System.out.println("Vida: " + car.getLife());
                System.out.println("Saltos dados: " + count);
                System.out.println("Descubrio todo el tablero");
                break;
            }

            //Despues de 999 movimientos si mapeado!=tablero => No pudo descubrir el mapa entero
            if (count == maxcount) {
                System.out.println();
                this.renderMapeado(currentRow, currentCol);
                System.out.println("Vida: " + car.getLife());
                System.out.println("Saltos dados: " + count);
                System.out.println("No pudo descubrir todo el tablero");
                break;
            }
            //Life<0 => Murio el auto
            if (car.getLife() == 0) {
                System.out.println();
                this.renderMapeado(currentRow, currentCol);
                System.out.println("Vida: " + car.getLife());
                System.out.println("Saltos dados: " + count);
                System.out.println("El auto se rompió");
                break;
        }

        }

    }

    void moveCar(int row, int col, String dir) {
        int direccion = 10;
        if (dir.equals("rnd")) {
            direccion = (int) (Math.random() * 4);
        }
        if (dir.equals("izq")) {
            direccion = 0;
        }
        if (dir.equals("arr")) {
            direccion = 1;
        }
        if (dir.equals("der")) {
            direccion = 2;
        }
        if (dir.equals("abj")) {
            direccion = 3;
        }
        switch (direccion) {
            //izquierda
            case 0:
                row--;
                if (row < 0) {
                    row = tablero.getTablero().length - 1;
                }
                break;
            //arriba
            case 1:
                col--;
                if (col < 0) {
                    col = tablero.getTablero()[row].length - 1;
                }
                break;
            //derecha
            case 2:
                row++;
                if (row > (tablero.getTablero().length - 1)) {
                    row = 0;
                }
                break;
            case 3:
                col++;
                if (col > (tablero.getTablero()[row].length - 1)) {
                    col = 0;
                }
        }
        this.currentRow = row;
        this.currentCol = col;
    }

    void renderMapeado(int rowCar, int colCar) {
        for (int i = 0; i < mapeado.length; i++) {
            System.out.print("|");
            for (int j = 0; j < mapeado[i].length; j++) {
                if (i == rowCar && j == colCar) {
                    System.out.print(7);
                } else {
                    System.out.print(mapeado[i][j]);
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }

    void MapearCostados(int row, int col) {
        //Solo uso moveCar para saber los costados, por eso guardo la posicion original para devolderla al final.
        int origRow = currentRow;
        int origCol = currentCol;
        this.moveCar(row, col, "izq");
        if (tablero.getTablero()[currentRow][currentCol] == 2) {
            mapeado[currentRow][currentCol] = 2;
            currentRow = origRow;
            currentCol = origCol;
        }
        this.moveCar(row, col, "arr");
        if (tablero.getTablero()[currentRow][currentCol] == 2) {
            mapeado[currentRow][currentCol] = 2;
            currentRow = origRow;
            currentCol = origCol;
        }
        this.moveCar(row, col, "abj");
        if (tablero.getTablero()[currentRow][currentCol] == 2) {
            mapeado[currentRow][currentCol] = 2;
            currentRow = origRow;
            currentCol = origCol;
        }
        this.moveCar(row, col, "der");
        if (tablero.getTablero()[currentRow][currentCol] == 2) {
            mapeado[currentRow][currentCol] = 2;
            currentRow = origRow;
            currentCol = origCol;
        }
        this.currentRow = origRow;
        this.currentCol = origCol;
    }

}
