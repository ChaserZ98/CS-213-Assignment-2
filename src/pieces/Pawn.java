package pieces;

import util.util;

/**
 * It is an inherited class of CommonPiece
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */
public class Pawn extends CommonPiece {

    /**
     * give a variable check if it can be enpassant.
     *
     */
    public boolean canBeEnPassant = false;
    /**
     *
     * The constructor
     * @param Position get the position
     * @param Col get the color
     */
    public Pawn(String Position, String Col) {
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


    /**
     * get the name of current piece
     * @return  the name
     */
    @Override
    public String getName() {
        if (this.color.equals("white")) return "wp";
        else return "bp";
    }


    /**
     * Check if the piece is the same as itself
     * @param obj  position
     * @return if it is equal
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
