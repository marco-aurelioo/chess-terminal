package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Position;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class Knight extends ChessPiece {
    public Knight(ChessBoard board, COLOR color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {


        boolean[][] matrizBoard = new boolean[ getBoard().getCols()][getBoard().getRows()];

        validaMovimento(matrizBoard,2,1);
        validaMovimento(matrizBoard,2,-1);
        validaMovimento(matrizBoard,-2,1);
        validaMovimento(matrizBoard,-2,-1);
        validaMovimento(matrizBoard,1,2);
        validaMovimento(matrizBoard,1,-2);
        validaMovimento(matrizBoard,-1,2);
        validaMovimento(matrizBoard,-1,-2);

        return matrizBoard;
    }

    private void validaMovimento(boolean[][] matrizBoard,int colAdd, int rowAdd){
        Position ref = new Position(position.getCol() + colAdd ,position.getRow() + rowAdd );
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }
    }


    @Override
    public String toString() {
        return "N";
    }
}
