package com.tio.chess.model;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;

public class ChessPiece extends Piece {
    private COLOR color;

    public ChessPiece(ChessBoard board, COLOR color){
        super(board);
        this.color = color;
    }

    public COLOR getColor(){
        return this.color;
    }

}
