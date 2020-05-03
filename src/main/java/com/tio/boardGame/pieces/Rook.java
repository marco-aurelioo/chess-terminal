package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class Rook extends ChessPiece {

    public boolean[][] matrizBoard;

    public Rook(ChessBoard board, COLOR color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        matrizBoard = new boolean[ getBoard().getCols()][getBoard().getRows()];

        Position p = new Position(position.getCol(),position.getRow());
        //acima
        p.setValues(position.getCol(), position.getRow() -1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
            p.setRow(p.getRow() - 1);
        }
        if(getBoard().positionExists(p) && isThereOponetPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
        }

        //abaixo
        p.setValues(position.getCol(), position.getRow() +1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
            p.setRow(p.getRow() + 1);
        }
        if(getBoard().positionExists(p) && isThereOponetPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
        }

        //esquerda
        p.setValues(position.getCol() - 1, position.getRow());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
            p.setCol(p.getCol() - 1);
        }
        if(getBoard().positionExists(p) && isThereOponetPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
        }

        //direita
        p.setValues(position.getCol() + 1, position.getRow());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
            p.setCol(p.getCol() + 1);
        }
        if(getBoard().positionExists(p) && isThereOponetPiece(p)){
            matrizBoard[p.getCol()][p.getRow()] = true;
        }


        return matrizBoard;
    }



}
