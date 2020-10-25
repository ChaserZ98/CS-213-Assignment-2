package pieces;

import java.util.Objects;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public abstract class CommonPiece {

    public String currentPosition;
    public String color;

    public CommonPiece(String Position, String Col){
        this.currentPosition = Position;
        this.color = Col;
    }

    public abstract boolean checkMoveRange(String destination);

    public void setCurrentPosition(String position){
        this.currentPosition = position;
    }
    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonPiece that = (CommonPiece) o;
        return Objects.equals(currentPosition, that.currentPosition) &&
                Objects.equals(color, that.color);
    }

}



