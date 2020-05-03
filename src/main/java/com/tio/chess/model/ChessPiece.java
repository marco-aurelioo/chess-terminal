package com.tio.chess.model;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;

public abstract class ChessPiece extends Piece {
    private COLOR color;

    public ChessPiece(ChessBoard board, COLOR color){
        super(board);
        this.color = color;
    }

    public COLOR getColor(){
        return this.color;
    }

    protected boolean isThereOponetPiece(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && !p.getColor().equals(this.getColor());
    }

}
