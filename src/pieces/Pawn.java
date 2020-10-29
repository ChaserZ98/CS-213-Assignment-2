package pieces;

import util.util;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Pawn extends CommonPiece {

    public boolean canBeEnPassant = false;

    public Pawn(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    public boolean checkMoveRange(String destination) {
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(this.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);
        if(destination.equals(this.currentPosition)){
            return false;
        }
        if(this.color.equals("white")){
            if(intCurrentCoordinate[0] == 6){
                return (intCurrentCoordinate[0] - intDestination[0] <=2 && intCurrentCoordinate[0] - intDestination[0] > 0 && intCurrentCoordinate[1] == intDestination[1]) || (intCurrentCoordinate[0] - intDestination[0] == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1);
            }
            else{
                return (intCurrentCoordinate[0] - intDestination[0] ==1  && intCurrentCoordinate[1] == intDestination[1]) || (intCurrentCoordinate[0] - intDestination[0] == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1);
            }
        }
        else{
            if(intCurrentCoordinate[0] == 1){
                return (intDestination[0] - intCurrentCoordinate[0] <=2 && intDestination[0] - intCurrentCoordinate[0] > 0 && intCurrentCoordinate[1] == intDestination[1]) || (intDestination[0] - intCurrentCoordinate[0] == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1);
            }
            else{
                return (intDestination[0] - intCurrentCoordinate[0] == 1  && intCurrentCoordinate[1] == intDestination[1]) || (intDestination[0] - intCurrentCoordinate[0] == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1);
            }
        }
    }

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wp";
        else return "bp";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
