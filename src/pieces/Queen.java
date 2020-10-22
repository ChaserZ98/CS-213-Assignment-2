package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Queen extends pieces.CommonPiece {


    public Queen(String newPosition, String newColor) {
        this.CurrentPosition = newPosition;
        this.Color = newColor;
    }

    public String toString(){
        if (this.Color.equals("white")) return "wQ";
        else return "bQ";
    }

    /**
     * Queen moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }
}
