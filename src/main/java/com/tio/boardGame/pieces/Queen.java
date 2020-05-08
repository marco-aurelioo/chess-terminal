package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Position;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class Queen extends ChessPiece {
    public Queen(ChessBoard board, COLOR color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizBoard = new boolean[ getBoard().getRows()][getBoard().getCols()];
        validaMovimento(matrizBoard,1,0);
        validaMovimento(matrizBoard,-1,0);
        validaMovimento(matrizBoard,0,1);
        validaMovimento(matrizBoard,0,-1);
        validaMovimento(matrizBoard,1,1);
        validaMovimento(matrizBoard,1,-1);
        validaMovimento(matrizBoard,-1,1);
        validaMovimento(matrizBoard,-1,-1);
        return matrizBoard;
    }

    @Override
    public String toString() {
        return "Q";
    }

    private void validaMovimento(boolean[][] matrizBoard, int direcaoCol, int direcaoRow) {
        Position ref = new Position(this.position.getCol() + (1*direcaoCol) , this.position.getRow() +(1*direcaoRow));
        while(getBoard().positionExists(ref) && !getBoard().thereIsAPiece(ref)) {
            matrizBoard[ref.getRow()][ref.getCol()] = true;
            ref.setValues(ref.getCol() + (1*direcaoCol) , ref.getRow() +(1*direcaoRow));
        }
        if(getBoard().positionExists(ref) && isThereOponetPiece(ref)){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }
    }
}
