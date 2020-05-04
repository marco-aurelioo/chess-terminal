package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Position;
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


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizBoard = new boolean[ getBoard().getCols()][getBoard().getRows()];

        Position ref = new Position(position.getCol() -1 ,position.getRow() );
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
            isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol()  - 1 ,position.getRow() - 1);
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol()  ,position.getRow() - 1);
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol() + 1 ,position.getRow() - 1);
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol() + 1 ,position.getRow() );
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol() + 1 ,position.getRow() +1);
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol()  ,position.getRow() + 1);
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }

        ref = new Position(position.getCol() - 1  ,position.getRow() + 1);
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }
        return matrizBoard;
    }
}
