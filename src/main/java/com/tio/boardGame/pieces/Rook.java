package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
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

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizBoard = new boolean[ getBoard().getRows()][getBoard().getCols()];

        //acima
        Position ref = new Position(position.getCol() , position.getRow() -1);
        while(getBoard().positionExists(ref) && !getBoard().thereIsAPiece(ref)) {
            matrizBoard[ref.getRow()][ref.getCol()] = true;
            ref.setRow(ref.getRow() -1 );
        }
        if(getBoard().positionExists(ref) && isThereOponetPiece(ref)){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        //abaixo
        ref = new Position(position.getCol() , position.getRow() +1);
        while(getBoard().positionExists(ref) && !getBoard().thereIsAPiece(ref)) {
            matrizBoard[ref.getRow()][ref.getCol()] = true;
            ref.setRow(ref.getRow() +1 );
        }
        if(getBoard().positionExists(ref) && isThereOponetPiece(ref)){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        //direita
        ref = new Position(position.getCol() - 1, position.getRow());
        while(getBoard().positionExists(ref) && !getBoard().thereIsAPiece(ref)) {
            matrizBoard[ref.getRow()][ref.getCol()] = true;
            ref.setCol(ref.getCol() - 1 );
        }
        if(getBoard().positionExists(ref) && isThereOponetPiece(ref)){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        //esquerda
        ref = new Position(position.getCol() + 1, position.getRow());
        while(getBoard().positionExists(ref) && !getBoard().thereIsAPiece(ref)) {
            matrizBoard[ref.getRow()][ref.getCol()] = true;
            ref.setCol(ref.getCol() + 1 );
        }
        if(getBoard().positionExists(ref) && isThereOponetPiece(ref)){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }
        return matrizBoard;
    }



}
