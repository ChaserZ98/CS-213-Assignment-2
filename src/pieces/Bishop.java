package pieces;

import util.util;

/**
 * It is an inherited class of CommonPiece
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */
public class Bishop extends CommonPiece {

    /**
     *
     * The constructor
     * @param Position get the position
     * @param Col get the color
     */
    public Bishop(String Position, String Col) {
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
        return !destination.equals(this.currentPosition) && (Math.abs(intCurrentCoordinate[0] - intDestination[0]) == Math.abs(intCurrentCoordinate[1] - intDestination[1]));
    }


    /**
     * get the name of current piece
     * @return  the name
     */
    @Override
    public String getName() {
        if (this.color.equals("white")) return "wB";
        else return "bB";
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
