package pieces;

import util.util;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Knight extends CommonPiece {


    public Knight(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);
        return !destination.equals(this.currentPosition) && Math.abs(intCurrentCoordinate[0] - intDestination[0]) + Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 3;
    }

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wN";
        else return "bN";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
