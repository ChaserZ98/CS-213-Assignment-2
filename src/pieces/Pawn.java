package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Pawn extends pieces.CommonPiece {


    public Pawn(String newPosition, String newColor) {
        this.CurrentPosition = newPosition;
        this.Color = newColor;
    }

    public String toString(){
        if (this.Color.equals("white")) return "wp";
        else return "bp";
    }

    /**
     * Pawn moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }
}
