
package chess;

import model.Board;

import java.util.Scanner;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Chess {

    public static void main(String[] args) {
        final String whitePlayer = "White";
        final String blackPlayer = "Black";
        String currentPlayer = whitePlayer;

        Board chessBoard = new Board();
        boolean isEnd  = false;

        Scanner input = new Scanner(System.in);

        while(!isEnd){
            System.out.println("");
            System.out.println(chessBoard);
            System.out.println("");
            System.out.print(currentPlayer + "'s Move: ");
            String command = input.nextLine();

            /*
            resign
             */
            if(command.equals("resign")){
                if(currentPlayer.equals(blackPlayer)){
                    System.out.println(whitePlayer + " wins");
                }
                else{
                    System.out.println(blackPlayer + " wins");
                }
                isEnd = true;
            }
            else{
                String[] parameter = command.split(" ");
                /*
                regular move
                 */
                if(parameter.length == 2){
                    System.out.println("Input: " + parameter[0] + " " + parameter[1]);
                }
                /*
                regular move with appended option
                 */
                else if(parameter.length == 3){
                    if(parameter[2].equals("N")){
                        System.out.println("Pawn promotion");
                    }
                    else if(parameter[2].equals("draw?")){
                        System.out.println("draw request");
                    }
                    else{
                        System.out.println("Illegal command.");
                    }
                }
                /*
                other illegal commands
                 */
                else{
                    System.out.println("Illegal command.");
                }
            }

            currentPlayer = currentPlayer.equals(blackPlayer)? whitePlayer : blackPlayer;
        }
        input.close();


    }

}
