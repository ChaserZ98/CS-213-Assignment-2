package util;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class util {

    /**
     * Transfer from Integer to Character for ASCII
     * @param i integer value
     * @return char value
     */
    public static char intToChar(int i){
        return (char) (i+97);
    }

    /**
     *
     * @param str input string
     * @return true if the input string is a letter coordinate
     */
    public static boolean isLetterCoordinate(String str){
        if(str.length()==2){
            return str.charAt(0) >= 'a' && str.charAt(0) <= 'h' && str.charAt(1) >= '1' && str.charAt(1) <= '8';
        }
        return false;
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

    /**
     *
     * @param intCoordinate position of piece represented by int coordinate
     * @return letterCoordinate
     */
    public static String intCoordinateToLetterCoordinate(int[] intCoordinate){
        return Character.toString('a' + intCoordinate[1]) + Character.toString('8' - intCoordinate[0]);
    }

}
