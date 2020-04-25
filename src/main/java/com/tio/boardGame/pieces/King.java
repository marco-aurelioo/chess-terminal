package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class King extends ChessPiece {

    public King(ChessBoard board, COLOR color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }
}
