package pieces;

import control.control;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Bishop extends CommonPiece {


    public Bishop(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = control.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = control.letterCoordinateToIntCoordinate(destination);
        return !destination.equals(this.currentPosition) && (Math.abs(intCurrentCoordinate[0] - intDestination[0]) == Math.abs(intCurrentCoordinate[1] - intDestination[1]));
    }

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wB";
        else return "bB";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
