package pieces;

import control.control;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Queen extends CommonPiece {


    public Queen(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = control.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = control.letterCoordinateToIntCoordinate(destination);
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

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wQ";
        else return "bQ";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
