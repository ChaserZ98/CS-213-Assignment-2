package pieces;

import util.util;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

/**
 * It is an inherited class of CommonPiece
 *
 */

public class King extends CommonPiece {

    /**
     * give a variable check if it can castling.
     *
     */
    public boolean canCastling = true;
    /**
     * give a variable check if it is already checked.
     *
     */
    public boolean isChecked = false;


    /**
     * The constructor
     *
     */
    public King(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    /**
     * Check the movable range of the chess pieces
     *
     */
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);
        if(destination.equals(this.currentPosition)){
            return false;
        }
        else if(Math.abs(intCurrentCoordinate[0] - intDestination[0]) + Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1){
            return true;
        }
        else if(intCurrentCoordinate[0] == intDestination[0] && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 2){
            return true;
        }
        else if(Math.abs(intCurrentCoordinate[0] - intDestination[0]) == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1){
            return true;
        }
        else{
            return false;
        }
//        return !destination.equals(this.currentPosition) && (Math.abs(intCurrentCoordinate[0] - intDestination[0]) + Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1 || (intCurrentCoordinate[0] == intDestination[0] && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 2) || (Math.abs(intCurrentCoordinate[0] - intDestination[0]) == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1));
    }

    @Override
    /**
     * get the name of current piece
     *
     */
    public String getName() {

        if (this.color.equals("white")) return "wK";
        else return "bK";
    }


    @Override
    /**
     * Check if the piece is the same as itself
     *
     */
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
