package com.tio.boardGame;

public class ChessBoard {
    int rows;
    int cols;

    public Piece[][] pieces;

    public ChessBoard(int c, int r) {
        if(r < 0 && c < 0){
            throw new BoardException("numero de colunas/linhas invalido");
        }
        this.rows = r;
        this.cols = c;
        this.pieces = new Piece[r][c];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Piece piece(int row, int col) {
        return pieces[row][col];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getCol()];
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)){
            throw new BoardException("Ja existe uma pça neste local");
        }
        this.pieces[position.getRow()][position.getCol()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        return null;
    }

    public boolean positionExists(Position position) {
        try {
            Piece p = pieces[position.getRow()][position.getCol()];
            return true;
        } catch (IndexOutOfBoundsException iof) {
            return false;
        }
    }

    public boolean thereIsAPiece(Position position){
        Piece p = piece(position);
        if(p == null) {
            return false;
        }else{
            return true;
        }
    }

}
