package control;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class control {

    /**
     * Tranfer from Integer to Character for ASCII
     *
     */
    public static char intToChar(int i){
        return (char) (i+97);
    }

    /**
     *
     * @param letterCoordinate position of piece represented by letter coordinate
     * @return intCoordinate
     */
    public static int[] letterCoordinateToIntCoordinate(String letterCoordinate){
        int[] intCoordinate = new int[2];
        intCoordinate[1] = letterCoordinate.charAt(0) - 'a';
        intCoordinate[0] = 8 - (letterCoordinate.charAt(1) - '0');
        return intCoordinate;
    }
}
