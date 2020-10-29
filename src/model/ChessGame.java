package model;

import pieces.CommonPiece;
import pieces.King;
import util.util;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The entity of a chess game
 * @author Feiyu Zheng
 * @author Ying Yu
 */
public class ChessGame {
    /**
     * The string value of white player
     */
    final String whitePlayer = "White";
    /**
     * The string value of black player
     */
    final String blackPlayer = "Black";
    /**
     * The entity of chess board
     */
    Board chessBoard;

    /**
     *The default constructor
     */
    public ChessGame(){
        this.chessBoard = new Board();
    }

    /**
     * method to start the game
     */
    public void play(){
        String currentPlayer = whitePlayer;
        chessBoard = new Board();

        boolean isGameOver = false;
        boolean drawRequest = false;

        Scanner input = new Scanner(System.in);

        while(!isGameOver){
            boolean isMoveLegal = false;

            System.out.println();
            System.out.println(chessBoard);
            System.out.println();

            while(!isMoveLegal){
                System.out.print(currentPlayer + "'s Move: ");
                String command = input.nextLine();

                //resign
                if(command.equals("resign")){
                    isMoveLegal = true;
                    isGameOver = resign(currentPlayer);
                }
                else if(command.equals("draw") && drawRequest){
                    isMoveLegal = true;
                    isGameOver = true;
                }
                else{
                    String[] parameter = command.split(" ");

                    //regular move w/o appended option
                    if(parameter.length == 2){
//                    System.out.println("Input: " + parameter[0] + " " + parameter[1]);
                        isMoveLegal = chessBoard.movePiece(currentPlayer, parameter[0], parameter[1]);
                    }

                    //regular move with appended option
                    else if(parameter.length == 3 || parameter.length == 4){

                        if(parameter[parameter.length - 1].equals("draw?")){
//                            System.out.println("draw request");
                            drawRequest = true;
                            isMoveLegal = true;
                        }
                        else if(parameter[2].equals("B") || parameter[2].equals("N") || parameter[2].equals("Q") || parameter[2].equals("R")){
                            isMoveLegal = chessBoard.movePiece(currentPlayer, parameter[0], parameter[1], parameter[2]);
                        }
                        else{
                            System.out.println("Illegal move, try again");
                        }
                    }

                    //other illegal command
                    else{
                        System.out.println("Illegal move, try again");
                    }
                }
            }

            updateKingCheckStatus(chessBoard);

            if(isCheckMate(chessBoard, currentPlayer.equals(whitePlayer) ? blackPlayer.toLowerCase() : whitePlayer.toLowerCase())) {
                System.out.println("Checkmate");
                System.out.println(currentPlayer.equals(blackPlayer)? blackPlayer + " wins" : whitePlayer + "wins");
                isGameOver = true;
            }

            if(isMoveLegal){
                chessBoard.clearEnPassant(currentPlayer.equals(blackPlayer)? whitePlayer.toLowerCase() : blackPlayer.toLowerCase());
                currentPlayer = currentPlayer.equals(blackPlayer)? whitePlayer : blackPlayer;
            }
        }
        input.close();
    }

    /**
     * Execute if a player choose to resign
     * @param currentPlayer The String value of current player
     * @return a always true value to set the isGameOver flag to true
     */
    public boolean resign(String currentPlayer){
        if(currentPlayer.equals(blackPlayer)){
            System.out.println(whitePlayer + " wins");
        }
        else{
            System.out.println(blackPlayer + " wins");
        }
        return true;
    }

    /**
     * update the check status of each king
     * @param chessBoard the chess board entity
     */
    public void updateKingCheckStatus(Board chessBoard){
        CommonPiece whiteKing = null;
        CommonPiece blackKing = null;
        for(CommonPiece piece : chessBoard.pieces){
            if(piece instanceof King && piece.color.equals(whitePlayer.toLowerCase())){
                whiteKing = (King) piece;
            }
            else if(piece instanceof King && piece.color.equals(blackPlayer.toLowerCase())){
                blackKing = (King) piece;
            }
        }
        ((King) whiteKing).isChecked = chessBoard.isPositionUnderCapture(whiteKing.currentPosition, whiteKing.color);
        ((King) blackKing).isChecked = chessBoard.isPositionUnderCapture(blackKing.currentPosition, blackKing.color);
        if(((King) whiteKing).isChecked){
            System.out.println("Check");
        }
        if(((King) blackKing).isChecked){
            System.out.println("Check");
        }
    }

    /**
     * Check if checkmate occurs
     * @param chessBoard the chess board entity
     * @param color the color of the king you want to check
     * @return true if checkmate occurs
     */
    public boolean isCheckMate(Board chessBoard, String color){
        boolean result = true;
        CommonPiece king = null;
        for(CommonPiece piece : chessBoard.pieces){
            if(piece instanceof King && piece.color.equals(color)){
                king = (King) piece;
                break;
            }
        }
        if(!((King) king).isChecked){
            return false;
        }
        int[] intKingCoordinate = util.letterCoordinateToIntCoordinate(king.currentPosition);
        int[][] nearbyIntCoordinate = {
                {intKingCoordinate[0] + 1, intKingCoordinate[1] - 1}, {intKingCoordinate[0] + 1, intKingCoordinate[1]}, {intKingCoordinate[0] + 1, intKingCoordinate[1] + 1},
                {intKingCoordinate[0], intKingCoordinate[1] - 1}, {intKingCoordinate[0], intKingCoordinate[1] + 1},
                {intKingCoordinate[0] - 1, intKingCoordinate[1] - 1}, {intKingCoordinate[0] - 1, intKingCoordinate[1]}, {intKingCoordinate[0] - 1, intKingCoordinate[1] + 1}
        };
        ArrayList<String> legalMove = new ArrayList<>();
        for(int[] intCoordinate : nearbyIntCoordinate){
            String letterCoordinate = util.intCoordinateToLetterCoordinate(intCoordinate);
            if(util.isLetterCoordinate(letterCoordinate)){
                legalMove.add(letterCoordinate);
            }
        }

        for(String position : legalMove){
            CommonPiece pieceOnPosition = chessBoard.getPieceByPosition(position);
            if(chessBoard.isMoveReachable(king, position, "")){
                if(!chessBoard.isPositionUnderCapture(position, king.color)){
                    result = false;
                }
            }
        }
        return result;
    }


}
