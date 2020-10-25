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

    @Override
    public boolean checkMoveRange(String destination) {
        return false;
    }

    @Override
    public String getName() {
        if (this.color.equals("white")) return "wN";
        else return "bN";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
