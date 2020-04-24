package com.tio.chess.app;

import com.tio.chess.model.ChessBoard;

public class ChessApp {
    public static void main(String[] args) {
        System.out.println("Start");
        ChessBoard b = new ChessBoard();
        b.printBoard();
    }
}
