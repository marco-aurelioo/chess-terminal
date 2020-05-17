package com.tio.boardGame.pieces;

import com.tio.boardGame.ChessBoard;
import com.tio.boardGame.Piece;
import com.tio.boardGame.Position;
import com.tio.chess.model.COLOR;
import com.tio.chess.model.ChessMatch;
import com.tio.chess.model.ChessPiece;
import com.tio.chess.model.ChessPosition;

public class King extends ChessPiece {

    private ChessMatch match;

    public King(ChessBoard board, COLOR color, ChessMatch match) {
        super(board, color);
        this.match = match;
    }

    @Override
    public String toString() {
        return "K";
    }


    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrizBoard = new boolean[ getBoard().getCols()][getBoard().getRows()];

        validaMovimento(matrizBoard,-1,-1);
        validaMovimento(matrizBoard,-1,0);
        validaMovimento(matrizBoard,-1,1);
        validaMovimento(matrizBoard,0,-1);
        validaMovimento(matrizBoard,0,1);
        validaMovimento(matrizBoard,1,-1);
        validaMovimento(matrizBoard,1,0);
        validaMovimento(matrizBoard,1,1);
        movimentoRoque(matrizBoard);
        return matrizBoard;
    }

    private void movimentoRoque(boolean[][] matrizBoard){
        if(getMoveCount() == 0 && !match.isCheck()){
            validaRoquePequeno(matrizBoard);
            validaRoqueGrande(matrizBoard);
        }
    }

    private void validaRoquePequeno(boolean[][] matrizBoard) {
        Position kingRef = new Position(position.getCol(),position.getRow());
        for(int i = 1; i < 3; i++){
            Position ref = new Position(kingRef.getCol() + i ,kingRef.getRow());
            Piece piece = getBoard().piece(ref);
            if(piece != null){
                return;
            }
        }
        ChessPiece rook = (ChessPiece) getBoard().piece( new Position(kingRef.getCol() + 3 ,position.getRow()));
        if(!(rook instanceof Rook) || rook.getMoveCount() > 0){
            return;
        }
        //valida peças passando por check
        for(int i = 1; i < 3; i++){
            Position ref = new Position(kingRef.getCol() + i ,kingRef.getRow());
            Piece piece = match.makeMove(kingRef,ref);
            if(match.testCheck(this.getColor())){
                match.undoMove(kingRef,ref,(ChessPiece)piece);
                return;
            }
            match.undoMove(kingRef,ref,(ChessPiece)piece);
        }
        for(int i = 1; i < 3; i++){
            validaMovimento(matrizBoard,i,0);
        }
    }

    private void validaRoqueGrande(boolean[][] matrizBoard) {
        Position kingRef = new Position(position.getCol(),position.getRow());
        for(int i = 1; i < 4; i++){
            Position ref = new Position(kingRef.getCol() + (i * -1) ,kingRef.getRow());
            Piece piece = getBoard().piece(ref);
            if(piece != null){
                return;
            }
        }
        ChessPiece rook = (ChessPiece) getBoard().piece( new Position(kingRef.getCol() - 4 ,kingRef.getRow()));
        if(!(rook instanceof Rook) || rook.getMoveCount() > 0){
            return;
        }
        for(int i = 1; i < 3; i++){
            Position ref = new Position(kingRef.getCol() + (i * -1) ,kingRef.getRow());
            Piece piece = match.makeMove(kingRef,ref);
            if(match.testCheck(this.getColor())){
                match.undoMove(kingRef,ref,(ChessPiece)piece);
                return;
            }
            match.undoMove(kingRef,ref,(ChessPiece)piece);
        }
        for(int i = 1; i < 3; i++){
            validaMovimento(matrizBoard,(i*-1),0);
        }
    }

    private void validaMovimento(boolean[][] matrizBoard,int colAdd, int rowAdd){
        Position ref = new Position(position.getCol() + colAdd ,position.getRow() + rowAdd );
        if( getBoard().positionExists(ref) && (!getBoard().thereIsAPiece(ref) ||
                isThereOponetPiece(ref))){
            matrizBoard[ref.getRow()][ref.getCol()] = true;
        }
    }



}
