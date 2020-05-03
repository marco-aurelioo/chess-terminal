package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class King extends ChessPiece {

    public boolean[][] matrizBoard;

    public King(ChessBoard board, COLOR color) {
        super(board, color);
        matrizBoard = new boolean[board.getCols()][board.getRows()];
    }

    @Override
    public String toString() {
        return "K";
    }


    @Override
    public boolean[][] possibleMoves() {
        matrizBoard = new boolean[ getBoard().getCols()][getBoard().getRows()];
        return matrizBoard;
    }
}
