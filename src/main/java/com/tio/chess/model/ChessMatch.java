package com.tio.chess.model;

import com.tio.boardGame.BoardException;
import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
import com.tio.boardGame.pieces.King;
import com.tio.boardGame.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private ChessBoard board;
    private List<ChessPiece> capturedPieces= new ArrayList<>();
    private List<ChessPiece> boardPieces = new ArrayList<>();

    private int turn;
    private COLOR currentPlayer;

    public ChessMatch(){
        board = new ChessBoard(8,8);
        initialSetup();
        this.turn = 1;
        this.currentPlayer = COLOR.WHITE;
    }

    public List<ChessPiece> getCapturedPieces(){
        return this.capturedPieces;
    }

    public int getTurn(){
        return turn;
    }

    public COLOR getCurrentPlayer(){
        return this.currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getCols()][board.getRows()];
        for (int i = 0; i < board.getCols(); i++){
            for (int j = 0; j < board.getRows(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }


    public boolean[][] possiblesMoves(ChessPosition source) {
        Position position = source.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturePiece = makeMove(source,target);
        if(capturePiece != null){
            this.capturedPieces.add((ChessPiece)capturePiece);
            this.boardPieces.remove(capturePiece);
        }
        nextTurn();
        return (ChessPiece) capturePiece;
    }

    private void nextTurn(){
        turn++;
        this.currentPlayer = (this.currentPlayer == COLOR.WHITE )?  COLOR.BLACK : COLOR.WHITE;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("posição invalida");
        }
        if(((ChessPiece)board.piece(position)).getColor() != currentPlayer){
            throw new ChessException("Essa peça é do adversario!");
        }
        if(!board.piece(position).isThereAnyPossibleMoves()){
            throw new ChessException("Não ha movimentos possiveis");
        }
    }

    private void validateTargetPosition(Position source, Position target ){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("Movimento invalido");
        }
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece =board.removePiece(source);
        Piece capturedPiece =  board.removePiece(target);
        board.placePiece(piece,target);
        return capturedPiece;
    }

    private void placeNewPiece(char col, int row, ChessPiece piece){
        board.placePiece(piece,new ChessPosition(col,row).toPosition());
        boardPieces.add(piece);
    }

    private void initialSetup(){

        placeNewPiece('e',8,new King(board,COLOR.BLACK));
        placeNewPiece('a',8,new Rook(board,COLOR.BLACK));
        placeNewPiece('h',8,new Rook(board,COLOR.BLACK));
        placeNewPiece('e',1,new King(board,COLOR.WHITE));
        placeNewPiece('a',1,new Rook(board,COLOR.WHITE));
        placeNewPiece('h',1,new Rook(board,COLOR.WHITE));



    }

}
