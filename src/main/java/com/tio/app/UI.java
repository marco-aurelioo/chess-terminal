package com.tio.app;

import com.tio.boardGame.Piece;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessPiece;

public class UI {

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
