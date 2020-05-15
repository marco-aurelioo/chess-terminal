package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessMatch;
import com.tio.chess.model.ChessPiece;
import com.tio.chess.model.ChessPosition;

public class King extends ChessPiece {

    private ChessMatch match;

    public King(ChessBoard board, COLOR color, ChessMatch match) {
        super(board, color);
        this.match = match;
    }

    @Override
    public String toString() {
        return "K";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizBoard = new boolean[ getBoard().getCols()][getBoard().getRows()];

        validaMovimento(matrizBoard,1,1);
        validaMovimento(matrizBoard,1,-1);
        validaMovimento(matrizBoard,-1,1);
        validaMovimento(matrizBoard,-1,-1);
        validaMovimento(matrizBoard,1,1);
        validaMovimento(matrizBoard,1,-1);
        validaMovimento(matrizBoard,-1,1);
        validaMovimento(matrizBoard,-1,-1);
        movimentoRoque(matrizBoard);
        return matrizBoard;
    }

    private void movimentoRoque(boolean[][] matrizBoard){
        if(getMoveCount() == 0){
            Position  posref1 = new Position(position.getCol() + 1 ,position.getRow());
            Position  posref2 = new Position(position.getCol() + 2 ,position.getRow());
            Position  posref3 = new Position(position.getCol() + 3 ,position.getRow());
            ChessPiece rook = (ChessPiece) getBoard().piece(posref3);
            if(!(rook instanceof Rook) || rook.getMoveCount() > 0){
                return;
            }else {
                ChessPiece piece1 = (ChessPiece) getBoard().piece(posref1);
                ChessPiece piece2 = (ChessPiece) getBoard().piece(posref2);
                if( piece1 == null && piece2 == null  ){
                    matrizBoard[posref1.getRow()][posref1.getCol()] = true;
                    matrizBoard[posref2.getRow()][posref2.getCol()] = true;
                }
            }
        }
    }

    private void validaMovimento(boolean[][] matrizBoard,int colAdd, int rowAdd){
        Position ref = new Position(position.getCol() + colAdd ,position.getRow() + rowAdd );
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }
    }



}
