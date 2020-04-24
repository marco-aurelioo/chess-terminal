package com.tio.chess.model;

public class ChessBoard {
        int rows = 8;
        int cols = 8;
        Piece[][] cells = new Piece[8][8];

        public Piece piece(int col,int row){
            return null;
        }

        public Piece piece(Position position){
            return null;
        }

        public void placePiece(Piece piece,Position position){

        }

        public Piece removePiece(Position position){
            return null;
        }

        public boolean positionExists(Position position){
            return false;
        }

        public boolean thereIsAPiece(Position p){
            return false;
        }


        public void printBoard(){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < rows; i++){
                sb.append(rows-i).append(" ");
                for(int j = 0; j <cols; j++){
                    sb.append(printPiece(i,j));
                }
                sb.append(System.getProperty("line.separator"));
            }
            sb.append("  abcdefg");
            System.out.println(sb.toString());
        }

        public char printPiece(int r,int c){
            Piece p = cells[r][c];
            if(p == null){
                return '-';
            }else {
                return p.getPieceName();
            }
        }
}
