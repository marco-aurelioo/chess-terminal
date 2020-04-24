package com.tio.app;

import com.tio.boardGame.ChessBoard;
import com.tio.chess.model.ChessMatch;

public class ChessApp {
    public static void main(String[] args) {
        System.out.println("Start");
        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieces());
    }
}
