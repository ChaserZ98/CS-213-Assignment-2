package pieces;

import util.util;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Rook extends CommonPiece {

    public boolean canCastling = true;

    public Rook(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);
        if(destination.equals(this.currentPosition)){
            return false;
        }
        if(intCurrentCoordinate[0] == intDestination[0] && Math.abs(intCurrentCoordinate[1] - intDestination[1]) > 0){
            return true;
        }
        else if(intCurrentCoordinate[1] == intDestination[1] && Math.abs(intCurrentCoordinate[0] - intDestination[0]) > 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wR";
        else return "bR";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
