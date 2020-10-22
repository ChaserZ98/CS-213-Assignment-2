package model;

import pieces.*;
import control.control;

import java.util.ArrayList;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Board {

    final String[] rowLetterCoordinate = {" a", " b", " c", " d", " e", " f", " g", " h"};
    final String[] columnLetterCoordinate = {" 8", " 7", " 6", " 5", " 4", " 3", " 2", " 1"};
    ArrayList<CommonPiece> pieces = new ArrayList<>();
    String[][] boardLayout = {
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
    };

    public Board() {
        initializePieces();
        fillBroad();
    }
    public boolean movePiece(String currentCoordinate, String destination){
        return true;
    }
    public void fillBroad(){
        for (CommonPiece piece : this.pieces) {
            int xIntCoordinate = piece.currentPosition.charAt(0) - 'a';
            int yIntCoordinate = 8 - Integer.parseInt(String.valueOf(piece.currentPosition.charAt(1)));
            boardLayout[yIntCoordinate][xIntCoordinate] = piece.getName();
        }
    }
    public void initializePieces(){
        this.pieces.add(new Rook("a8", "black"));
        this.pieces.add(new Knight("b8", "black"));
        this.pieces.add(new Bishop("c8", "black"));
        this.pieces.add(new Queen("d8", "black"));
        this.pieces.add(new King("e8", "black"));
        this.pieces.add(new Bishop("f8", "black"));
        this.pieces.add(new Knight("g8", "black"));
        this.pieces.add(new Rook("h8", "black"));
        for(int i = 0; i < columnLetterCoordinate.length; i++){
            this.pieces.add(new Pawn(control.intToChar(i) + "7", "black"));
        }

        this.pieces.add(new Rook("a1", "white"));
        this.pieces.add(new Knight("b1", "white"));
        this.pieces.add(new Bishop("c1", "white"));
        this.pieces.add(new Queen("d1", "white"));
        this.pieces.add(new King("e1", "white"));
        this.pieces.add(new Bishop("f1", "white"));
        this.pieces.add(new Knight("g1", "white"));
        this.pieces.add(new Rook("h1", "white"));
        for(int i = 0; i < columnLetterCoordinate.length; i++){
            this.pieces.add(new Pawn(control.intToChar(i) + "2", "white"));
        }

    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < rowLetterCoordinate.length; i++){
            for(int j = 0; j < columnLetterCoordinate.length; j++){
                out.append(this.boardLayout[i][j]);
                out.append(" ");
            }
            out.append(this.rowLetterCoordinate[i]);
            out.append("\n");
        }
        for(int i = 0; i < columnLetterCoordinate.length; i++){
            out.append(this.rowLetterCoordinate[i]);
            if(i != columnLetterCoordinate.length - 1){
                out.append(" ");
            }
        }
        return out.toString();
    }
}

