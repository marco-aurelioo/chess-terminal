package com.tio.app;


import com.tio.chess.model.ChessMatch;
import com.tio.chess.model.ChessPiece;
import com.tio.chess.model.ChessPosition;


import java.util.Scanner;

public class ChessApp {
    public static void main(String[] args) {
        System.out.println("Start");

        Scanner sc = new Scanner(System.in);

        ChessMatch match = new ChessMatch();
        while(true) {
            UI.printBoard(match.getPieces());
            System.out.println("Source");
            System.out.println("");
            ChessPosition source =  UI.readChessPosition(sc);;

            System.out.println("target");
            ChessPosition target = UI.readChessPosition(sc);
            ChessPiece captured = match.performChessMove(source,target);       }

    }
}
