package com.tio.chess.model;

import com.tio.boardGame.BoardException;
import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
import com.tio.boardGame.pieces.King;
import com.tio.boardGame.pieces.Rook;

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
        if(capturePiece != null){
            board.placePiece(capturePiece,target);
            capturedPieces.remove(capturePiece);
            boardPieces.add(capturePiece);
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

    private Piece makeMove(Position source, Position target) {
        Piece piece =board.removePiece(source);
        Piece capturedPiece =  board.removePiece(target);
        board.placePiece(piece,target);
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
                        if (testCheck) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private void placeNewPiece(char col, int row, ChessPiece piece){
        board.placePiece(piece,new ChessPosition(col,row).toPosition());
        boardPieces.add(piece);
    }

    private void initialSetup(){

        placeNewPiece('a',8,new King(board,COLOR.BLACK));
        placeNewPiece('b',8,new Rook(board,COLOR.BLACK));

        placeNewPiece('e',1,new King(board,COLOR.WHITE));
        placeNewPiece('b',1,new Rook(board,COLOR.WHITE));
        placeNewPiece('h',7,new Rook(board,COLOR.WHITE));



    }

}
