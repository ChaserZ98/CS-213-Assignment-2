package pieces;

import util.util;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class King extends CommonPiece {
    public boolean canCastling = true;
    public boolean isChecked = false;

    public King(String Position, String Col) {
        super(Position, Col);
    }

    @Override
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
    public String getName() {
        if (this.color.equals("white")) return "wK";
        else return "bK";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
