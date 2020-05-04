package com.tio.app;

import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;
import com.tio.chess.model.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    public static void clearScreen(){
        System.out.print("\033[H\033[2j");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scanner){
        try {
            String lance = scanner.nextLine();
            char column = lance.charAt(0);
            int row = Integer.parseInt(lance.substring(1));
            return new ChessPosition(column, row);
        }catch(RuntimeException ex){
            throw  new InputMismatchException("Erro ao digitar position");
        }
    }

    public static void printBoard(ChessPiece[][] pieces){
        for(int i = 0; i < pieces.length; i++){
            System.out.print((8 - i) + " ");
            for(int j = 0; j < pieces.length; j++){
                System.out.print(printPiece(pieces[i][j],false));
            }
            System.out.println(" ");
        }
        System.out.println(" a b c d e f g");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possiblesMoves) {
        for(int i = 0; i < pieces.length; i++){
            System.out.print((8 - i ) + " ");
            for(int j = 0; j < pieces.length; j++){
                System.out.print(printPiece(pieces[i][j],possiblesMoves[i][j]));
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println(" a b c d e f g h");
    }

    private static String printPiece(ChessPiece piece,boolean setBackGround){
        if(setBackGround ){
            System.out.print(ConsoleColors.GREEN_BACKGROUND);
        }
        if(piece == null){
            return "-"+ ConsoleColors.RESET;
        }else {
            if(piece.getColor() == COLOR.WHITE) {
                return ConsoleColors.YELLOW+ piece.toString() + ConsoleColors.RESET;
            }else{
                return ConsoleColors.BLUE+ piece.toString() + ConsoleColors.RESET;
            }
        }
    }


}
