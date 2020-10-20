package Pieces;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Piece {
    public String CurrentPosition;
    public String Color;

    public boolean CheckValid(String dest){
        return false;
    }

    public void SetCurrentPosition(String position){
        CurrentPosition = position;
    }

    public void SetColor(String color){
        this.Color= color;
    }

}
