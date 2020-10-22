package pieces;

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

    public CommonPiece(){
        this.CurrentPosition = null;
        this.Color = null;
    }

    public CommonPiece(String Position, String Col){
        this.CurrentPosition = Position;
        this.Color = Col;
    }

    public boolean CheckValid(String dest){
        return false;
    }

    public void SetCurrentPosition(String position){
        this.CurrentPosition = position;
    }

    public void SetColor(String color){
        this.Color= color;
    }

}



