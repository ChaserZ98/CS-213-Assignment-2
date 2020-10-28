package model;

import pieces.CommonPiece;
import pieces.King;

import java.util.Scanner;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 */

public class ChessGame {
    final String whitePlayer = "White";
    final String blackPlayer = "Black";
    Board chessBoard;
    public ChessGame(){
        this.chessBoard = new Board();
    }
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

            // TODO: 2020/10/26 isCheckmate method
            if(updateKingCheckmateStatus(chessBoard)) {
                System.out.println("checkmate");
                break;
            }

            if(isMoveLegal){
                chessBoard.clearEnPassant(currentPlayer.equals(blackPlayer)? whitePlayer.toLowerCase() : blackPlayer.toLowerCase());
                currentPlayer = currentPlayer.equals(blackPlayer)? whitePlayer : blackPlayer;
            }
        }
        input.close();
    }
    public boolean resign(String currentPlayer){
        if(currentPlayer.equals(blackPlayer)){
            System.out.println(whitePlayer + " wins");
        }
        else{
            System.out.println(blackPlayer + " wins");
        }
        return true;
    }
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

    public boolean updateKingCheckmateStatus(Board chessBoard){
        CommonPiece whiteKing = null;
        CommonPiece blackKing = null;


        int[] intCoordinate = new int[2];
        int[] tempCoordinate = new int[2];
        String newPosition;
        boolean[] isChecked = new boolean[4];
        for(int i=0; i<4; i++){
            isChecked[i] = false;
        }


        for(CommonPiece piece : chessBoard.pieces){
            if(piece instanceof King && piece.color.equals(whitePlayer.toLowerCase())){
                whiteKing = (King) piece;
                intCoordinate = util.util.letterCoordinateToIntCoordinate(piece.getName());

                //Coordinate X-1
                if((intCoordinate[1]<8)&&(intCoordinate[1]>0)){
                    tempCoordinate[1]=intCoordinate[1]-1;
                    tempCoordinate[0]=intCoordinate[0];
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, whiteKing.color))
                        isChecked[0]= true;

                //Coordinate X+1
                if((intCoordinate[1]<7)&&(intCoordinate[1]>=0)){
                    tempCoordinate[1]=intCoordinate[1]+1;
                    tempCoordinate[0]=intCoordinate[0];
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, whiteKing.color))
                        isChecked[1]= true;

                //Coordinate Y-1
                if((intCoordinate[0]<8)&&(intCoordinate[1]>0)){
                    tempCoordinate[1]=intCoordinate[1];
                    tempCoordinate[0]=intCoordinate[0]-1;
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, whiteKing.color))
                        isChecked[2]= true;

                //Coordinate Y+1
                if((intCoordinate[0]<7)&&(intCoordinate[1]>=0)){
                    tempCoordinate[1]=intCoordinate[1];
                    tempCoordinate[0]=intCoordinate[0]+1;
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, whiteKing.color))
                        isChecked[3]= true;


            }
            else if(piece instanceof King && piece.color.equals(blackPlayer.toLowerCase())){
                blackKing = (King) piece;
                intCoordinate = util.util.letterCoordinateToIntCoordinate(piece.getName());

                //Coordinate X-1
                if((intCoordinate[1]<8)&&(intCoordinate[1]>0)){
                    tempCoordinate[1]=intCoordinate[1]-1;
                    tempCoordinate[0]=intCoordinate[0];
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, blackKing.color))
                        isChecked[0]= true;

                //Coordinate X+1
                if((intCoordinate[1]<7)&&(intCoordinate[1]>=0)){
                    tempCoordinate[1]=intCoordinate[1]+1;
                    tempCoordinate[0]=intCoordinate[0];
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, blackKing.color))
                        isChecked[1]= true;

                //Coordinate Y-1
                if((intCoordinate[0]<8)&&(intCoordinate[1]>0)){
                    tempCoordinate[1]=intCoordinate[1];
                    tempCoordinate[0]=intCoordinate[0]-1;
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, blackKing.color))
                        isChecked[2]= true;

                //Coordinate Y+1
                if((intCoordinate[0]<7)&&(intCoordinate[1]>=0)){
                    tempCoordinate[1]=intCoordinate[1];
                    tempCoordinate[0]=intCoordinate[0]+1;
                }
                newPosition = util.util.intCoordinateToLetterCoordinate(tempCoordinate);
                if(whiteKing.checkMoveRange(newPosition))
                    if(chessBoard.isPositionUnderCapture(newPosition, blackKing.color))
                        isChecked[3]= true;

            }
        }

        return isChecked[0]&&isChecked[1]&&isChecked[2]&&isChecked[3];

    }
}
