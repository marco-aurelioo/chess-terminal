package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Position;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

import java.awt.*;

public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, COLOR color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];
        Position p = new Position(position.getCol(),position.getRow());
        int direcao =( getColor() == COLOR.BLACK )? 1 : -1;

        p.setValues(position.getCol()  , p.getRow() +( 1 * direcao));
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.getRow()][p.getCol()] = true;
            if(getMoveCount() == 0){
                p.setValues(p.getCol() , p.getRow() +( 1 * direcao));
                if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                    mat[p.getRow()][p.getCol()] = true;
                }
            }
        }
        p.setValues(position.getCol() + 1 , position.getRow() +( 1 * direcao));
        if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        p.setValues(position.getCol() - 1 , position.getRow() +( 1 * direcao));
        if (getBoard().positionExists(p) && isThereOponetPiece(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        return mat;

    }

    @Override
    public String toString() {
        return "P";
    }
}
