package pieces;

import util.util;

/**
 * It is an inherited class of CommonPiece
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */
public class Rook extends CommonPiece {

    /**
     * give a variable check if it can castling.
     *
     */
    public boolean canCastling = true;
    /**
     *
     * The constructor
     *
     * @param Position get the position
     * @param Col get the color
     */
    public Rook(String Position, String Col) {
        super(Position, Col);
    }

    /**
     *
     * @param destination  get target coordinates
     * @return
     */
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


    /**
     * get the name of current piece
     * @return  the name
     */
    @Override
    public String getName() {
        if (this.color.equals("white")) return "wR";
        else return "bR";
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
