package com.tio.boardGame;

public abstract class Piece {

    private ChessBoard board;
    protected Position position;

    public Piece(ChessBoard board) {
        this.board = board;
    }

}
