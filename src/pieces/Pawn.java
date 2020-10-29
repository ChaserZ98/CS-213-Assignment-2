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
public class Pawn extends CommonPiece {

    /**
     * give a variable check if it can be enpassant.
     *
     */
    public boolean canBeEnPassant = false;
    /**
     * The constructor
     *
     */
    public Pawn(String Position, String Col) {
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
        /**
         * get the name of current piece
         *
         */
        if (this.color.equals("white")) return "wp";
        else return "bp";
    }

    @Override
    /**
     * Check if the piece is the same as itself
     *
     */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
