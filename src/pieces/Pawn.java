package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Pawn extends CommonPiece {


    public Pawn(String Position, String Col) {
        super(Position, Col);
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
