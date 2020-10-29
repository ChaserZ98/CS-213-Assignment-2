package pieces;

import java.util.Objects;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */


/**
 * This is the base class of all chess pieces, an abstract class
 *
 */
public abstract class CommonPiece {

    /**
     * The coordinate position of the current chess piece
     *
     */
    public String currentPosition;
    /**
     * The color of the current piece
     *
     */
    public String color;

    /**
     * The constructor
     *
     */
    public CommonPiece(String Position, String Col){
        this.currentPosition = Position;
        this.color = Col;
    }

    /**
     * Check the movable range of the chess pieces
     *
     */
    public abstract boolean checkMoveRange(String destination);


    /**
     * set current position of current piece
     *
     */
    public void setCurrentPosition(String position){
        this.currentPosition = position;
    }
    /**
     * get the name of current piece
     *
     */
    public abstract String getName();

    @Override

    /**
     * Check if the piece is the same as itself
     *
     */

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonPiece that = (CommonPiece) o;
        return Objects.equals(currentPosition, that.currentPosition) &&
                Objects.equals(color, that.color);
    }

}



