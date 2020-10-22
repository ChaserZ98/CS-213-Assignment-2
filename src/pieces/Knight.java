package pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Knight extends CommonPiece {


    public Knight(String Position, String Col) {
        super(Position, Col);
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
