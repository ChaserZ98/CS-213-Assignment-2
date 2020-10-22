package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Bishop extends CommonPiece {


    public Bishop(String newPosition, String newColor) {
        this.CurrentPosition = newPosition;
        this.Color = newColor;
    }

    public String toString(){
        if (this.Color.equals("white")) return "wB";
        else return "bB";
    }

    /**
     * Bishop moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }
}
