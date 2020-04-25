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

    public Piece piece(int row, int col){
        return pieces[row][col];
    }

        public Piece piece(Position position){
            return null;
        }

        public void placePiece(Piece piece,Position position){
            this.pieces[position.getRow()][position.getCol()] = piece;
            piece.position = position;
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
}
