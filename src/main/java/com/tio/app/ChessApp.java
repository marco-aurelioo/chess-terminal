package com.tio.app;


import com.tio.chess.model.ChessException;
import com.tio.chess.model.ChessMatch;
import com.tio.chess.model.ChessPiece;
import com.tio.chess.model.ChessPosition;


import java.util.InputMismatchException;
import java.util.Scanner;

public class ChessApp {
    public static void main(String[] args) {
        System.out.println("Start");

        Scanner sc = new Scanner(System.in);

        ChessMatch match = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(match.getPieces());
                System.out.println("Source");
                ChessPosition source = UI.readChessPosition(sc);

                UI.clearScreen();
                boolean[][] possiblesMoves = match.possiblesMoves(source);
                UI.printBoard(match.getPieces(),possiblesMoves);

                System.out.println("target");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece captured = match.performChessMove(source, target);
            }catch(ChessException e){
                System.out.println("Erro:"+e.getMessage());
                sc.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Erro:"+e.getMessage());
                sc.nextLine();
            }
        }
    }
}
