package com.tio.chess.model;

import com.tio.boardGame.ChessBoard;

public class ChessMatch {

    private ChessBoard board;

    public ChessMatch(){
        board = new ChessBoard(8,8);
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
}
