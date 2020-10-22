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
    public boolean pieceInsideBoard(String position){

        int[] positionCoordinate = PositionToCoordinate(position);

        if(positionCoordinate[0] >= 0 && positionCoordinate[0] <= 7 && positionCoordinate[1] >= 0 && positionCoordinate[1] <= 7)
            return true;
        else
            return false;
    }

    /**
     * Transfer input position string to coordinate
     *
     */
    public int[] PositionToCoordinate(String position){
        int[] positionCoordinate = new int[2];
        positionCoordinate[0] = -1;
        positionCoordinate[1] = -1;

        position = position.toLowerCase();

        if (position.length() != 2) return positionCoordinate;

        int letterToInteger = position.charAt(0)-'a'+1;
        positionCoordinate[0] = letterToInteger;
        positionCoordinate[1] = Integer.parseInt(position.charAt(1)+"");

        return positionCoordinate;
    }
}
