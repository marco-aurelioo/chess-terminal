package com.tio.chess.model;

import com.tio.boardGame.BoardException;
import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
import com.tio.boardGame.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private ChessBoard board;
    private List<ChessPiece> capturedPieces= new ArrayList<>();
    private List<ChessPiece> boardPieces = new ArrayList<>();

    private int turn;
    private COLOR currentPlayer;
    private boolean check;
    private boolean checkMate;

    public ChessMatch(){
        board = new ChessBoard(8,8);
        initialSetup();
        this.turn = 1;
        this.currentPlayer = COLOR.WHITE;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public boolean isCheck() {
        return check;
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

        if(testCheck(currentPlayer)){
            undoMove(source,target,(ChessPiece)capturePiece);
            throw new ChessException("você não pode entrar em check");
        }


        check = testCheck(oponent(currentPlayer)) ? true: false;
        if(testCheckMate(oponent(currentPlayer))){
            checkMate =true;
        }

        nextTurn();
        return (ChessPiece) capturePiece;
    }

    public void undoMove(Position source, Position target, ChessPiece capturePiece){
        Piece piece = board.removePiece(target);
        board.placePiece(piece,source);
        ((ChessPiece)piece).decreaseMoveCount();
        if(capturePiece != null){
            board.placePiece(capturePiece,target);
            capturedPieces.remove(capturePiece);
            boardPieces.add(capturePiece);
        }
        if(((ChessPiece) piece).getMoveCount() == 0) {
            if (piece instanceof King) {
                if (Math.abs(source.getCol() - target.getCol()) == 2) {
                    //foi um roque
                    if((source.getCol() - target.getCol()) < 2){
                        Position rookTargetPos = new Position(source.getCol() + 1 ,source.getRow());
                        Position rookSourcePos = new Position(7,source.getRow());
                        Piece rook = board.removePiece(rookTargetPos);
                        board.placePiece(rook,rookSourcePos);
                        ((ChessPiece)rook).increaseMoveCount();
                    }else{
                        Position rookSourcePos = new Position(0,source.getRow());
                        Position rookTargetPos = new Position(source.getCol() - 1 ,source.getRow());
                        Piece rook = board.removePiece(rookTargetPos);
                        board.placePiece(rook,rookSourcePos);
                        ((ChessPiece)rook).increaseMoveCount();
                    }
                }
            }
        }
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

    public Piece makeMove(Position source, Position target) {
        Piece piece =board.removePiece(source);
        Piece capturedPiece =  board.removePiece(target);
        ((ChessPiece)piece).increaseMoveCount();
        board.placePiece(piece,target);
        if(((ChessPiece) piece).getMoveCount() == 1) {
            if (piece instanceof King) {
                if (Math.abs(source.getCol() - target.getCol()) == 2) {
                    //foi um roque
                    if((source.getCol() - target.getCol()) < 2){
                        Position rookSourcePos = new Position(7,source.getRow());
                        Piece rook = board.removePiece(rookSourcePos);
                        Position rookTargetPos = new Position(source.getCol() + 1 ,source.getRow());
                        board.placePiece(rook,rookTargetPos);
                        ((ChessPiece)rook).increaseMoveCount();
                    }else{
                        Position rookSourcePos = new Position(0,source.getRow());
                        Piece rook = board.removePiece(rookSourcePos);
                        Position rookTargetPos = new Position(source.getCol() - 1 ,source.getRow());
                        board.placePiece(rook,rookTargetPos);
                        ((ChessPiece)rook).increaseMoveCount();
                    }
                }
            }
        }
        return capturedPiece;
    }

    public COLOR oponent(COLOR color){
        return color == COLOR.WHITE? COLOR.BLACK: COLOR.WHITE;
    }

    public ChessPiece getKing(COLOR color){
        List<Piece> lista = boardPieces.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : lista) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new ChessException("não encontrado o rei "+color);
    }

    public boolean testCheck(COLOR color){
        Position positionKing = getKing(color).getChessPosition().toPosition();
        COLOR oponentColor = oponent(color);
        List<Piece> oponentPieces = boardPieces.stream().filter(p -> p.getColor()  == oponentColor).collect(Collectors.toList());
        for(Piece p : oponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[positionKing.getRow()][positionKing.getCol()]){
                return true;
            }
        }
        return false;
    }

    public boolean testCheckMate(COLOR color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> lista = boardPieces.stream().filter(p -> p.getColor() == color).collect(Collectors.toList());
        for (Piece p : lista) {
            boolean[][] possivelMoves = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCols(); j++) {
                    if(possivelMoves[i][j]) {
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capture = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, (ChessPiece) capture);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    private void placeNewPiece(char col, int row, ChessPiece piece){
        board.placePiece(piece,new ChessPosition(col,row).toPosition());
        boardPieces.add(piece);
    }

    private void initialSetup(){

        placeNewPiece('a',7,new Pawn(board,COLOR.BLACK));
        placeNewPiece('b',7,new Pawn(board,COLOR.BLACK));
        placeNewPiece('c',7,new Pawn(board,COLOR.BLACK));
        placeNewPiece('d',7,new Pawn(board,COLOR.BLACK));
        placeNewPiece('e',7,new Pawn(board,COLOR.BLACK));
        placeNewPiece('f',7,new Pawn(board,COLOR.BLACK));
        placeNewPiece('g',2,new Pawn(board,COLOR.BLACK));
        placeNewPiece('h',7,new Pawn(board,COLOR.BLACK));

        placeNewPiece('e',8,new King(board,COLOR.BLACK,this));
        placeNewPiece('a',8,new Rook(board,COLOR.BLACK));
        placeNewPiece('h',8,new Rook(board,COLOR.BLACK));
        //placeNewPiece('b',8,new Knight(board,COLOR.BLACK));
        //placeNewPiece('d',8,new Queen(board,COLOR.BLACK));
        //placeNewPiece('f',8,new Bishop(board,COLOR.BLACK));
        //placeNewPiece('c',8,new Bishop(board,COLOR.BLACK));
        //placeNewPiece('g',8,new Knight(board,COLOR.BLACK));

        placeNewPiece('a',2,new Pawn(board,COLOR.WHITE));
        placeNewPiece('b',2,new Pawn(board,COLOR.WHITE));
        placeNewPiece('c',2,new Pawn(board,COLOR.WHITE));
        placeNewPiece('d',2,new Pawn(board,COLOR.WHITE));
        placeNewPiece('e',2,new Pawn(board,COLOR.WHITE));
        placeNewPiece('f',2,new Pawn(board,COLOR.WHITE));
      //  placeNewPiece('g',2,new Pawn(board,COLOR.WHITE));
        placeNewPiece('h',2,new Pawn(board,COLOR.WHITE));

        placeNewPiece('a',1,new Rook(board,COLOR.WHITE));
        placeNewPiece('h',1,new Rook(board,COLOR.WHITE));
        placeNewPiece('e',1,new King(board,COLOR.WHITE,this));
        //placeNewPiece('b',1,new Knight(board,COLOR.WHITE));
        //placeNewPiece('c',1,new Bishop(board,COLOR.WHITE));
        //placeNewPiece('d',1,new Queen(board,COLOR.WHITE));
        //placeNewPiece('f',1,new Bishop(board,COLOR.WHITE));
        //placeNewPiece('g',1,new Knight(board,COLOR.WHITE));







    }

}
