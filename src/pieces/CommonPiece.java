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

    public String CurrentPosition;
    public String Color;

    public CommonPiece(String Position, String Col){
        this.CurrentPosition = Position;
        this.Color = Col;
    }

    public boolean checkValid(String dest){
        return false;
    }

    public void setCurrentPosition(String position){
        this.CurrentPosition = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommonPiece that = (CommonPiece) o;
        return Objects.equals(CurrentPosition, that.CurrentPosition) &&
                Objects.equals(Color, that.Color);
    }

}



