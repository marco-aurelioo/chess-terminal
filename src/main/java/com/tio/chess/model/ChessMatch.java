package com.tio.chess.model;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Position;
import com.tio.boardGame.pieces.King;
import com.tio.boardGame.pieces.Rook;

public class ChessMatch {

    private ChessBoard board;

    public ChessMatch(){
        board = new ChessBoard(8,8);
        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getCols()];
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getCols(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char col, int row, ChessPiece piece){
        board.placePiece(piece,new ChessPosition(col,row).toPosition());
    }

    private void initialSetup(){
        placeNewPiece('e',1,new King(board,COLOR.WHITE));
        placeNewPiece('e',8,new King(board,COLOR.BLACK));
        placeNewPiece('c',3,new Rook(board,COLOR.WHITE));
    }
}
