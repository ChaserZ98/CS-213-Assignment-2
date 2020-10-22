package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Knight extends pieces.CommonPiece {


    public Knight(String newPosition, String newColor) {
        this.CurrentPosition = newPosition;
        this.Color = newColor;
    }

    public String toString(){
        if (this.Color.equals("white")) return "wN";
        else return "bN";
    }

    /**
     * Knight moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }
}
