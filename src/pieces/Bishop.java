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
public class Bishop extends CommonPiece {

    /**
     * The constructor
     *
     */
    public Bishop(String Position, String Col) {
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
        return !destination.equals(this.currentPosition) && (Math.abs(intCurrentCoordinate[0] - intDestination[0]) == Math.abs(intCurrentCoordinate[1] - intDestination[1]));
    }

    @Override

    public String getName() {
        /**
         * get the name of current piece
         *
         */
        if (this.color.equals("white")) return "wB";
        else return "bB";
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
