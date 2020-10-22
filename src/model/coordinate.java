package model;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class coordinate {

    public int row;
    public int column;

    public coordinate(int x, int y){
        this.row = x;
        this.column = y;
    }

    /**
     * Check if input position is inside the display board--[0][0] to [7][7]
     *
     */
    public boolean pieceInsideBoard(String letterCoordinate){

        int[] intCoordinate = PositionToCoordinate(letterCoordinate);

        return intCoordinate[0] >= 0 && intCoordinate[0] <= 7 && intCoordinate[1] >= 0 && intCoordinate[1] <= 7;
    }

    /**
     * Transfer input position string to coordinate
     *
     */
    public int[] PositionToCoordinate(String letterCoordinate){
        int[] intCoordinate = new int[2];
        intCoordinate[0] = -1;
        intCoordinate[1] = -1;

        letterCoordinate = letterCoordinate.toLowerCase();

        if (letterCoordinate.length() != 2) return intCoordinate;

        int xCoordinate = letterCoordinate.charAt(0)-'a'+1;
        intCoordinate[0] = xCoordinate;
        intCoordinate[1] = Integer.parseInt(letterCoordinate.charAt(1)+"");

        return intCoordinate;
    }
}
