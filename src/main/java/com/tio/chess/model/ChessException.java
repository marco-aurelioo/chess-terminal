package com.tio.chess.model;

import com.tio.boardGame.BoardException;

public class ChessException extends BoardException {
    public ChessException(String msg){
        super(msg);
    }
}
