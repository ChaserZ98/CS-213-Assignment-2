package pieces;

import java.util.Objects;

/**
 * Group 13
 *
 *This is the base class of all chess pieces, an abstract class
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */


public abstract class CommonPiece {

    /**
     *The coordinate position of the current chess piece
     *
     */
    public String currentPosition;
    /**
     * The color of the current piece
     *
     */
    public String color;

    /**
     *
     * The constructor
     * @param Position get the position
     * @param Col get the color
     */
    public CommonPiece(String Position, String Col){
        this.currentPosition = Position;
        this.color = Col;
    }

    /**
     * Check the movable range of the chess pieces
     * @param destination  get target coordinates
     * @return position
     */
    public abstract boolean checkMoveRange(String destination);


    /**
     *  set current position of current piece
     * @param position current position
     */
    public void setCurrentPosition(String position){
        this.currentPosition = position;
    }

    /**
     * get the name of current piece
     * @return name
     */
    public abstract String getName();


    /**
     * Check if the piece is the same as itself
     * @param o position
     * @return if it is equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonPiece that = (CommonPiece) o;
        return Objects.equals(currentPosition, that.currentPosition) &&
                Objects.equals(color, that.color);
    }

}



