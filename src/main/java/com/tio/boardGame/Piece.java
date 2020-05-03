package com.tio.boardGame;

public abstract class Piece {

    private ChessBoard board;
    protected Position position;

    public Piece(ChessBoard board) {
        this.board = board;
    }

    public abstract  boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getCol()][position.getRow()];
    }

    public boolean isThereAnyPossibleMoves(){
        boolean[][] mat = possibleMoves();
        for( int i = 0; i < mat.length; i++ ){
            for(int j = 0; j < mat[i].length; j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    protected ChessBoard getBoard(){
        return this.board;
    }

}
