package model;

import pieces.*;
import util.util;

import java.util.ArrayList;

/**
 * Group 13
 * @author Feiyu Zheng
 * @author Ying Yu
 *
 *
 */

public class Board {

    final String[] columnLetterCoordinate = {" a", " b", " c", " d", " e", " f", " g", " h"};
    final String[] rowLetterCoordinate = {" 8", " 7", " 6", " 5", " 4", " 3", " 2", " 1"};
    ArrayList<CommonPiece> pieces = new ArrayList<>();
    String[][] boardLayout = {
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
            {"  ", "##", "  ", "##", "  ", "##", "  ", "##"},
            {"##", "  ", "##", "  ", "##", "  ", "##", "  "},
    };

    public Board() {
        initializePieces();
        fillBoard();
    }

    public void clearEnPassant(String color){
        for(CommonPiece piece : this.pieces){
            if(piece instanceof Pawn && piece.color.equals(color)){
                ((Pawn) piece).canBeEnPassant = false;
            }
        }
    }

    public CommonPiece getPieceByPosition(String letterCoordinate){
        for(CommonPiece piece : this.pieces){
            if(piece.currentPosition.equals(letterCoordinate)){
                return piece;
            }
        }
        return null;

    }

    //check if the position is safe
    public boolean isPositionUnderCapture(String position, String currentPlayer){
        boolean result = false;
        CommonPiece destPiece = getPieceByPosition(position);
        CommonPiece tempPiece = new Queen(position, currentPlayer);
        if(destPiece == null){
            this.pieces.add(tempPiece);
        }
        this.pieces.remove(destPiece);

        for(CommonPiece piece : this.pieces){
            if(!piece.color.equals(currentPlayer)){

                if(isMoveReachable(piece, position, "")){
                    result = true;
                    break;
                }
            }
        }
        if(destPiece != null){
            this.pieces.add(destPiece);
        }
        this.pieces.remove(tempPiece);
        return result;
    }

    //check if the king will be checked after making a certain move
    public boolean willOwnKingBeChecked(CommonPiece piece, String destination){
        String originalPosition = piece.currentPosition;
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(piece.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);

        King king = null;
        if(piece instanceof King){
            king = (King) piece;
        }
        else{
            for(CommonPiece commonPiece : this.pieces){
                if(commonPiece instanceof King && commonPiece.color.equals(piece.color)){
                    king = (King) commonPiece;
                    break;
                }
            }
        }

        //check castling law
        if(piece instanceof King){
            if(intCurrentCoordinate[0] == intDestination[0] && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 2){
                int horizontalIncrement = (intDestination[1] - intCurrentCoordinate[1]) / 2;
                piece.setCurrentPosition(util.intCoordinateToLetterCoordinate(new int[]{intCurrentCoordinate[0], intDestination[1] + horizontalIncrement}));
                if(isPositionUnderCapture(piece.currentPosition, piece.color)){
                    piece.setCurrentPosition(originalPosition);
                    return true;
                }
                else{
                    piece.setCurrentPosition(util.intCoordinateToLetterCoordinate(new int[]{intCurrentCoordinate[0], intDestination[1] + 2 * horizontalIncrement}));
                    if(isPositionUnderCapture(piece.currentPosition, piece.color)){
                        piece.setCurrentPosition(originalPosition);
                        return true;
                    }
                    else{
                        piece.setCurrentPosition(originalPosition);
                        return false;
                    }
                }
            }
        }

        piece.setCurrentPosition(destination);
        if(isPositionUnderCapture(king.currentPosition, king.color)){
            piece.setCurrentPosition(originalPosition);
            return true;
        }
        else{
            piece.setCurrentPosition(originalPosition);
            return false;
        }
    }

    //check if the piece can reach the destination
    public boolean isMoveReachable(CommonPiece piece, String destination, String option){
        CommonPiece destPiece = getPieceByPosition(destination);
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(piece.currentPosition);
        int[] intDestination = util.letterCoordinateToIntCoordinate(destination);

        if(piece instanceof Bishop){
            if(piece.checkMoveRange(destination)){

                int verticalIncrement = intDestination[0] < intCurrentCoordinate[0] ? -1 : 1;
                int horizontalIncrement = intDestination[1] < intCurrentCoordinate[1] ? -1 : 1;

                //diagonal move
                int[] intBetweenCoordinate = {intCurrentCoordinate[0] + verticalIncrement, intCurrentCoordinate[1] + horizontalIncrement};
                while(intBetweenCoordinate[0] != intDestination[0] && intBetweenCoordinate[1] != intDestination[1]){
                    CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(intBetweenCoordinate));
                    if(betweenPiece != null){
                        return false;
                    }
                    intBetweenCoordinate[0] += verticalIncrement;
                    intBetweenCoordinate[1] += horizontalIncrement;
                }

                if(destPiece == null){
                    return true;
                }
                else{
                    return !destPiece.color.equals(piece.color);
                }
            }
            else{
                return false;
            }
        }
        else if(piece instanceof King){
            if(piece.checkMoveRange(destination)){
                if(Math.abs(intCurrentCoordinate[0] - intDestination[0]) + Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1 || (Math.abs(intCurrentCoordinate[0] - intDestination[0]) == 1 && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1)){
                    if(destPiece == null){
                        return true;
                    }
                    else{
                        return !piece.color.equals(destPiece.color);
                    }
                }
                //castling
                else if(intCurrentCoordinate[0] == intDestination[0] && Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 2 && ((King) piece).canCastling && !((King) piece).isChecked){
                    int horizontalIncrement = (intDestination[1] - intCurrentCoordinate[1]) / 2;
                    CommonPiece[] betweenPiece = {getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{intCurrentCoordinate[0], intCurrentCoordinate[1] + horizontalIncrement})), getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{intCurrentCoordinate[0], intCurrentCoordinate[1] + 2 * horizontalIncrement}))};
                    CommonPiece castlingRook = getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{intCurrentCoordinate[0], intCurrentCoordinate[1] + 3 * horizontalIncrement}));
                    if(betweenPiece[0] == null && betweenPiece[1] == null && castlingRook instanceof Rook){
                        return ((Rook) castlingRook).canCastling;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else if(piece instanceof Knight){
            if(piece.checkMoveRange(destination)){
                if(destPiece == null){
                    return true;
                }
                else{
                    return !destPiece.color.equals(piece.color);
                }
            }
            else{
                return false;
            }
        }
        else if(piece instanceof Pawn){
            if(piece.checkMoveRange(destination)){
                //vertical move
                if(intCurrentCoordinate[1] == intDestination[1]){
                    //move 1 square
                    if(Math.abs(intDestination[0] - intCurrentCoordinate[0]) == 1 && destPiece == null){
                        return true;
                    }
                    //move 2 square
                    else if(Math.abs(intDestination[0] - intCurrentCoordinate[0]) == 2 && destPiece == null){
                        CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{piece.color.equals("white")? intDestination[0] + 1 : intDestination[0] - 1, intDestination[1]}));
                        if(betweenPiece == null){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
                //diagonal move (capture)
                else if(Math.abs(intCurrentCoordinate[1] - intDestination[1]) == 1 && Math.abs(intCurrentCoordinate[0] - intDestination[0]) == 1){
                    CommonPiece enPassantPawn = getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{piece.color.equals("white")? intDestination[0] + 1 : intDestination[0] - 1, intDestination[1]}));
                    //en passant
                    if(enPassantPawn instanceof Pawn){
                        if(((Pawn) enPassantPawn).canBeEnPassant){
                            return true;
                        }
                    }
                    if(destPiece == null){
                        return false;
                    }
                    //regular capture
                    else{
                        return !destPiece.color.equals(piece.color);
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else if(piece instanceof Queen){
            if(piece.checkMoveRange(destination)){

                int verticalIncrement = intDestination[0] < intCurrentCoordinate[0] ? -1 : 1;
                int horizontalIncrement = intDestination[1] < intCurrentCoordinate[1] ? -1 : 1;

                //horizontal move
                if(intCurrentCoordinate[0] == intDestination[0]){
                    int[] intBetweenCoordinate = {intCurrentCoordinate[0], intCurrentCoordinate[1] + horizontalIncrement};
                    while(intBetweenCoordinate[1] != intDestination[1]){
                        CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(intBetweenCoordinate));
                        if(betweenPiece != null){
                            return false;
                        }
                        intBetweenCoordinate[1] += horizontalIncrement;
                    }
                    if(destPiece == null){
                        return true;
                    }
                    else{
                        return !destPiece.color.equals(piece.color);
                    }
                }
                //vertical move
                else if(intCurrentCoordinate[1] == intDestination[1]){
                    int[] intBetweenCoordinate = {intCurrentCoordinate[0] + verticalIncrement, intCurrentCoordinate[1]};
                    while(intBetweenCoordinate[0] != intDestination[0]){
                        CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(intBetweenCoordinate));
                        if(betweenPiece != null){
                            return false;
                        }
                        intBetweenCoordinate[0] += verticalIncrement;
                    }
                    if(destPiece == null){
                        return true;
                    }
                    else{
                        return !destPiece.color.equals(piece.color);
                    }
                }
                //diagonal move
                else{
                    int[] intBetweenCoordinate = {intCurrentCoordinate[0] + verticalIncrement, intCurrentCoordinate[1] + horizontalIncrement};
                    while(intBetweenCoordinate[0] != intDestination[0] && intBetweenCoordinate[1] != intDestination[1]){
                        CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(intBetweenCoordinate));
                        if(betweenPiece != null){
                            return false;
                        }
                        intBetweenCoordinate[0] += verticalIncrement;
                        intBetweenCoordinate[1] += horizontalIncrement;
                    }
                    if(destPiece == null){
                        return true;
                    }
                    else{
                        return !destPiece.color.equals(piece.color);
                    }
                }
            }
            else{
                return false;
            }
        }
        else if(piece instanceof Rook){
            if(piece.checkMoveRange(destination)){

                int verticalIncrement = intDestination[0] < intCurrentCoordinate[0] ? -1 : 1;
                int horizontalIncrement = intDestination[1] < intCurrentCoordinate[1] ? -1 : 1;

                //horizontal move
                if(intCurrentCoordinate[0] == intDestination[0]){
                    int[] intBetweenCoordinate = {intCurrentCoordinate[0], intCurrentCoordinate[1] + horizontalIncrement};
                    while(intBetweenCoordinate[1] != intDestination[1]){
                        CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(intBetweenCoordinate));
                        if(betweenPiece != null){
                            return false;
                        }
                        intBetweenCoordinate[1] += horizontalIncrement;
                    }
                    if(destPiece == null){

                        return true;
                    }
                    else{
                        return !destPiece.color.equals(piece.color);
                    }
                }
                //vertical move
                else if(intCurrentCoordinate[1] == intDestination[1]){
                    int[] intBetweenCoordinate = {intCurrentCoordinate[0] + verticalIncrement, intCurrentCoordinate[1]};
                    while(intBetweenCoordinate[0] != intDestination[0]){
                        CommonPiece betweenPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(intBetweenCoordinate));
                        if(betweenPiece != null){
                            return false;
                        }
                        intBetweenCoordinate[0] += verticalIncrement;
                    }
                    if(destPiece == null){
                        return true;
                    }
                    else{
                        return !destPiece.color.equals(piece.color);
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public CommonPiece promotion(CommonPiece piece, String destination, String option){
        switch (option) {
            case "B" -> {
                this.pieces.remove(piece);
                piece = new Bishop(destination, piece.color);
                this.pieces.add(piece);
            }
            case "N" -> {
                this.pieces.remove(piece);
                piece = new Knight(destination, piece.color);
                this.pieces.add(piece);
            }
            case "Q" -> {
                this.pieces.remove(piece);
                piece = new Queen(destination, piece.color);
                this.pieces.add(piece);
            }
            case "R" -> {
                this.pieces.remove(piece);
                piece = new Rook(destination, piece.color);
                this.pieces.add(piece);
            }
            default -> {
                this.pieces.remove(piece);
                piece = new Queen(destination, piece.color);
                this.pieces.add(piece);
            }
        }
        return piece;
    }

    public void enPassant(CommonPiece piece, CommonPiece enPassantPawn, String destination){
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(piece.currentPosition);
        int[] intDestinationCoordinate = util.letterCoordinateToIntCoordinate(destination);

        if(((Pawn) enPassantPawn).canBeEnPassant){
            this.pieces.remove(enPassantPawn);
            boardLayout[intCurrentCoordinate[0]][intDestinationCoordinate[1]] = (intCurrentCoordinate[0] + intDestinationCoordinate[1]) % 2 == 1? "##": "  ";
        }
    }

    public void castling(CommonPiece piece, String destination){
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(piece.currentPosition);
        int[] intDestinationCoordinate = util.letterCoordinateToIntCoordinate(destination);
        int horizontalIncrement = (intDestinationCoordinate[1] - intCurrentCoordinate[1]) / 2;

        CommonPiece castlingRook = getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{intCurrentCoordinate[0], intCurrentCoordinate[1] + 3 * horizontalIncrement}));
        ((Rook) castlingRook).canCastling = false;
        castlingRook.setCurrentPosition(util.intCoordinateToLetterCoordinate(new int[]{intDestinationCoordinate[0], intDestinationCoordinate[1] - horizontalIncrement}));
        boardLayout[intDestinationCoordinate[0]][intDestinationCoordinate[1] - horizontalIncrement] = castlingRook.getName();
        boardLayout[intCurrentCoordinate[0]][intCurrentCoordinate[1] + 3 * horizontalIncrement] = (intCurrentCoordinate[0] + (intCurrentCoordinate[1] + 3 * horizontalIncrement)) % 2 == 1 ? "##": "  ";
    }

    public void Capture(CommonPiece piece, CommonPiece destPiece, String destination, String option){
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(piece.currentPosition);
        int[] intDestinationCoordinate = util.letterCoordinateToIntCoordinate(destination);
        this.pieces.remove(destPiece);
        piece.currentPosition = destination;

        if(piece instanceof Pawn && intDestinationCoordinate[0] == (piece.color.equals("white")? 0 : 7)){
            piece = promotion(piece, destination, option);
        }

        boardLayout[intDestinationCoordinate[0]][intDestinationCoordinate[1]] = piece.getName();
        boardLayout[intCurrentCoordinate[0]][intCurrentCoordinate[1]] = (intCurrentCoordinate[0] + intCurrentCoordinate[1]) % 2 == 1 ? "##": "  ";
    }

    public void Move(CommonPiece piece, String destination, String option){
        int[] intCurrentCoordinate = util.letterCoordinateToIntCoordinate(piece.currentPosition);
        int[] intDestinationCoordinate = util.letterCoordinateToIntCoordinate(destination);

        CommonPiece adjacentPiece = getPieceByPosition(util.intCoordinateToLetterCoordinate(new int[]{piece.color.equals("white")? intDestinationCoordinate[0] + 1 : intDestinationCoordinate[0] - 1, intDestinationCoordinate[1]}));
        if(adjacentPiece instanceof Pawn){
            enPassant(piece, adjacentPiece, destination);
        }
        if(piece instanceof Pawn){
            //en passant
            if(Math.abs(intCurrentCoordinate[0] - intDestinationCoordinate[0]) == 2){
                ((Pawn) piece).canBeEnPassant = true;
            }
        }
        else if(piece instanceof Rook){
            ((Rook) piece).canCastling = false;
        }
        else if(piece instanceof King){
            ((King) piece).canCastling = false;
            //castling
            if(intCurrentCoordinate[0] == intDestinationCoordinate[0] && Math.abs(intCurrentCoordinate[1] - intDestinationCoordinate[1]) == 2){
                castling(piece, destination);
            }
        }

        piece.currentPosition = destination;

        //promotion
        if(piece instanceof Pawn && intDestinationCoordinate[0] == (piece.color.equals("white")? 0 : 7)){
            piece = promotion(piece, destination, option);
        }

        boardLayout[intDestinationCoordinate[0]][intDestinationCoordinate[1]] = piece.getName();
        boardLayout[intCurrentCoordinate[0]][intCurrentCoordinate[1]] = (intCurrentCoordinate[0] + intCurrentCoordinate[1]) % 2 == 1 ? "##": "  ";
    }

    public boolean movePiece(String currentPlayer, String currentCoodinate, String destination){
        return movePiece(currentPlayer, currentCoodinate, destination, "");
    }

    public boolean movePiece(String currentPlayer, String currentCoordinate, String destination, String option){
        if(!util.isLetterCoordinate(currentCoordinate) || !util.isLetterCoordinate(destination)){
            System.out.println("Illegal move, try again");
            return false;
        }

        CommonPiece piece = getPieceByPosition(currentCoordinate);
        if(piece == null || !piece.color.equals(currentPlayer.toLowerCase())){
            System.out.println("Illegal move, try again");
            return false;
        }

//        boolean bool1 = isMoveReachable(piece, destination, option);
//        boolean bool2 = !willOwnKingBeChecked(piece, destination);
//        boolean moveResult = bool1 && bool2;

        boolean moveResult = (isMoveReachable(piece, destination, option) && !willOwnKingBeChecked(piece, destination));
        if(moveResult){
            CommonPiece destPiece = getPieceByPosition(destination);
            if(destPiece == null){
                Move(piece, destination, option);
            }
            else{
                Capture(piece, destPiece, destination, option);
            }
            return true;
        }
        else{
            System.out.println("Illegal move, try again");
            System.out.println(isMoveReachable(piece, destination, option) + " " + (willOwnKingBeChecked(piece, destination)));
//            System.out.println(bool1 + " " + bool2);
            return false;
        }
    }

    public void fillBoard(){
        for (CommonPiece piece : this.pieces) {
            int xIntCoordinate = piece.currentPosition.charAt(0) - 'a';
            int yIntCoordinate = 8 - Integer.parseInt(String.valueOf(piece.currentPosition.charAt(1)));
            boardLayout[yIntCoordinate][xIntCoordinate] = piece.getName();
        }
    }
    public void initializePieces(){
        this.pieces.add(new Rook("a8", "black"));
        this.pieces.add(new Knight("b8", "black"));
        this.pieces.add(new Bishop("c8", "black"));
        this.pieces.add(new Queen("d8", "black"));
        this.pieces.add(new King("e8", "black"));
        this.pieces.add(new Bishop("f8", "black"));
        this.pieces.add(new Knight("g8", "black"));
        this.pieces.add(new Rook("h8", "black"));
        for(int i = 0; i < columnLetterCoordinate.length; i++){
            this.pieces.add(new Pawn(util.intToChar(i) + "7", "black"));
        }

        this.pieces.add(new Rook("a1", "white"));
        this.pieces.add(new Knight("b1", "white"));
        this.pieces.add(new Bishop("c1", "white"));
        this.pieces.add(new Queen("d1", "white"));
        this.pieces.add(new King("e1", "white"));
        this.pieces.add(new Bishop("f1", "white"));
        this.pieces.add(new Knight("g1", "white"));
        this.pieces.add(new Rook("h1", "white"));
        for(int i = 0; i < columnLetterCoordinate.length; i++){
            this.pieces.add(new Pawn(util.intToChar(i) + "2", "white"));
        }

    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < rowLetterCoordinate.length; i++){
            for(int j = 0; j < columnLetterCoordinate.length; j++){
                out.append(this.boardLayout[i][j]);
                out.append(" ");
            }
            out.append(this.rowLetterCoordinate[i]);
            out.append("\n");
        }
        for(int i = 0; i < columnLetterCoordinate.length; i++){
            out.append(this.columnLetterCoordinate[i]);
            if(i != columnLetterCoordinate.length - 1){
                out.append(" ");
            }
        }
        return out.toString();
    }
}

