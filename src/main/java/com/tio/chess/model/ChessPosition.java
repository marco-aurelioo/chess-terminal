package com.tio.chess.model;

import com.tio.boardGame.Position;

public class ChessPosition {

    private char column;
    private int row;

    public ChessPosition(char col,int row){
        if(row > 8 && row < 0 || col < 'a' && col > 'h'){
            throw new ChessException("Posição invalidade deve estar contido entre a1 e h8");
        }
        this.column = col;
        this.row = row;
    }

    public ChessPosition getChessPosition(Position position){
        return fromPosition(position);
    }

    protected Position toPosition(){
        return new Position( column - 'a', 8 - row );
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)(position.getCol()+'a'),8-position.getRow());
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return ""+column+row;
    }
}
