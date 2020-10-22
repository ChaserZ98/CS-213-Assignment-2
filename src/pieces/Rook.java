package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Rook extends pieces.CommonPiece {


    public Rook(String newPosition, String newColor) {
        this.CurrentPosition = newPosition;
        this.Color = newColor;
    }

    public String toString(){
        if (this.Color.equals("white")) return "wR";
        else return "bR";
    }

    /**
     * Rook moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }
}
