package com.tio.boardGame;

public class Position {
    private int col;
    private int row;

    public Position(int c, int r ){
        this.row = r;
        this.col = c;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return row+" "+col;
    }
}
