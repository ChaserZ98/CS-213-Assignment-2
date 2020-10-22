package model;

import chess.Chess;
import pieces.*;
import control.control;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Board {

    private static CommonPiece[][] boardLayout;

    public Board() {

        boardLayout = new CommonPiece[8][8];

        boardLayoutFill();
    }

    /**
     *
     * bR bN bB bQ bK bB bN bR 8
     * bp bp bp bp bp bp bp bp 7
     *    ##    ##    ##    ## 6
     * ##    ##    ##    ##    5
     *    ##    ##    ##    ## 4
     * ##    ##    ##    ##    3
     * wp wp wp wp wp wp wp wp 2
     * wR wN wB wQ wK wB wN wR 1
     *  a  b  c  d  e  f  g  h
     *
     * Fill boardLayout for the first time:  8x8 (index 0 to 7)
     */

    public void boardLayoutFill() {

        /*
          Firstly set 8x8 pieces to null

         */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardLayout[i][j] = null;
            }
        }
        /*
          set all black pieces

         */
        boardLayout[0][0] = new Rook("a8", "black");
        boardLayout[0][1] = new Knight("b8", "black");
        boardLayout[0][2] = new Bishop("c8", "black");
        boardLayout[0][3] = new Queen("d8", "black");
        boardLayout[0][4] = new King("e8", "black");
        boardLayout[0][7] = new Rook("h8", "black");
        boardLayout[0][5] = new Bishop("f8", "black");
        boardLayout[0][6] = new Knight("g8", "black");
        for (int i = 0; i < 8; i++)
            boardLayout[1][i] = new Pawn(control.intToChar(i) + "7", "black");
        /*
          set all white pieces

         */
        boardLayout[7][0] = new Rook("a1", "white");
        boardLayout[7][1] = new Knight("b1", "white");
        boardLayout[7][2] = new Bishop("c1", "white");
        boardLayout[7][3] = new Queen("d1", "white");
        boardLayout[7][4] = new King("e1", "white");
        boardLayout[7][5] = new Bishop("f1", "white");
        boardLayout[7][6] = new Knight("g1", "white");
        boardLayout[7][7] = new Rook("h1", "white");
        for (int i = 0; i < 8; i++)
            boardLayout[6][i] = new Pawn(control.intToChar(i) + "2", "white");

    }

    /**
     * Draw board layout for output
     *
     */
    public static void drawBoardLayout() {

        String[][] tempBoard = new String[9][9];
        /*
          Fill board from [0][0] to [7][7] for pieces

         */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardLayout[i][j] != null) {
                    tempBoard[i][j] = boardLayout[i][j].toString();
                }
                else {
                    if ((i % 2 == 1 && j % 2 == 0) || (i % 2 == 0 && j % 2 == 1)) {
                        tempBoard[i][j] = "##";
                    }
                    else {
                        tempBoard[i][j] = "  ";
                    }
                }
            }
        }

        /*
          Draw 8 to 1 from [0][8] to [7][8]
         Draw a to h from [8][0] to [8][7]
          [8][8] is space
         */
        for (int i = 0; i < 8; i++)
            tempBoard[i][8] = Integer.toString(8 - i);
        for (int i = 0; i < 8; i++)
            tempBoard[8][i] = " ".concat(Character.toString(control.intToChar(i)));

        tempBoard[8][8] = "  ";

        /*
          Print for output from [0][0] to [8][8]

         */
        for (int i = 0; i < 9; i++) {
            String eachBoardLine = "";
            for (int j = 0; j < 9; j++) {
                eachBoardLine = eachBoardLine.concat(" ");
                eachBoardLine = eachBoardLine.concat(tempBoard[i][j]);
            }
            System.out.println(eachBoardLine);
        }
    }
}

