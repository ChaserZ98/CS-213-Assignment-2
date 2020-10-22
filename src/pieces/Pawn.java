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

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wp";
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
