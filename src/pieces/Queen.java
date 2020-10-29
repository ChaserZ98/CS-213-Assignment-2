package pieces;

import util.util;

/**
 * It is an inherited class of CommonPiece
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */
public class Queen extends CommonPiece {

    /**
     *
     * The constructor
     * @param Position get the position
     * @param Col get the color
     */
    public Queen(String Position, String Col) {
        super(Position, Col);
    }


    /**
     * Check the movable range of the chess pieces
     * @param destination  get target coordinates
     * @return position
     */
    @Override
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);
        if(destination.equals(this.currentPosition)){
            return false;
        }
        if(Math.abs(intCurrentCoordinate[0] - intDestination[0]) == Math.abs(intCurrentCoordinate[1] - intDestination[1])){
            return true;
        }
        else if(intCurrentCoordinate[0] == intDestination[0] && Math.abs(intCurrentCoordinate[1] - intDestination[1]) > 0){
            return true;
        }
        else if(intCurrentCoordinate[1] == intDestination[1] && Math.abs(intCurrentCoordinate[0] - intDestination[0]) > 0){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * get the name of current piece
     * @return  the name
     */
    @Override
    public String getName() {
        if (this.color.equals("white")) return "wQ";
        else return "bQ";
    }

    /**
     * Check if the piece is the same as itself
     * @param o  position
     * @return if it is equal
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
