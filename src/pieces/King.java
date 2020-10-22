package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class King extends pieces.CommonPiece {


    public King(String newPosition, String newColor) {
        this.CurrentPosition = newPosition;
        this.Color = newColor;
    }

    public String toString(){
        if (this.Color.equals("white")) return "wK";
        else return "bK";
    }

    /**
     * King moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }
}
