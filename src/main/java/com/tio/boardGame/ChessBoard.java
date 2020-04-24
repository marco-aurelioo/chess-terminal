package com.tio.boardGame;

public class ChessBoard {
        int rows;
        int cols;

        public Piece[][] pieces;

        public ChessBoard(int r, int c){
            this.rows =r;
            this.cols =c;
            this.pieces = new Piece[r][c];
        }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Piece piece(int col, int row){
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

            System.out.println(sb.toString());
        }

        public char printPiece(int r,int c){
            Piece p = pieces[r][c];
            if(p == null){
                return '-';
            }else {
                return p.getPieceName();
            }
        }
}
