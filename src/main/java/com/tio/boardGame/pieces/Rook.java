package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class Rook extends ChessPiece {

    public Rook(ChessBoard board, COLOR color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

}
