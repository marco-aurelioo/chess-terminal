package com.tio.app;

import com.tio.boardGame.Piece;
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
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pieces.length; i++){
            sb.append(pieces.length - i).append(" ");
            for(int j = 0; j <pieces[i].length; j++){
                sb.append(printPiece(pieces[i][j])).append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }
        sb.append(" a b c d e f g");
        System.out.println(sb.toString());
    }

    private static String printPiece(ChessPiece piece){
        if(piece == null){
            return "-";
        }else {
            if(piece.getColor() == COLOR.WHITE) {
                return ConsoleColors.YELLOW+ piece.toString() + ConsoleColors.RESET;
            }else{
                return ConsoleColors.BLUE+ piece.toString() + ConsoleColors.RESET;
            }
        }
    }
}
