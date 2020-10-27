package model;

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

                //check check

                //check checkmate

                if(isMoveLegal){
                    chessBoard.clearEnPassant(currentPlayer.equals(blackPlayer)? whitePlayer.toLowerCase() : blackPlayer.toLowerCase());
                    currentPlayer = currentPlayer.equals(blackPlayer)? whitePlayer : blackPlayer;
                }
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
}
