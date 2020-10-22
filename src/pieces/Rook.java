package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Rook extends CommonPiece {


    public Rook(String Position, String Col) {
        super(Position, Col);
    }

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wR";
        else return "bR";
    }

    /**
     * Rook moving path valid check
     *
     */
    public boolean CheckValid(String dest){

        return false;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
