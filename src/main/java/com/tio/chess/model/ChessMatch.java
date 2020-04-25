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

    private void initialSetup(){
        this.board.placePiece(new King(board,COLOR.WHITE),new Position(0,4));
        this.board.placePiece(new King(board,COLOR.BLACK),new Position(7,4));
        this.board.placePiece(new Rook(board,COLOR.WHITE),new Position(7,2));
    }
}
