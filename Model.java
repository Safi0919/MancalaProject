/**
 * Model to represent Mancala Board Game using MVC pattern
 * @author Sneha Nalla
 * @version 1.0 12/4/2024
 */

import java.util.ArrayList; import javax.swing.event.*;

/**
 * A model of the board for Mancala board game, keeping track of the current player, the number of stones in each pit, and allowing a player to undo their move up to 3 times. Follows MVC Pattern, notifying its listeners when the Model's state has changed after a move has been made/undone.
 */
public class Model{
    private int currentBoard[]; //0-5 are player A's pits (A1-A6), 6 is player A's mancala, 7-12 are player B's pits (B1-B6), 13 is player B's mancala
    private int previousBoard[];
    private ArrayList<ChangeListener> listeners;
    private char currentPlayer;
    private char previousPlayer;
    private int undoCounter;
    private int moveCounter; //number of moves made since start of the game/last undo
    private char endState; //if game ends, will contain which player won (would be C if game tied); else has null char

    /**
     * Constructs a Mancala board with the specified number of stones to start with in each pit.
     * @param num - initial number of stones in each pit (excludes manacalas)
     * precondition: num should be positive, and it should be either 3 or 4 according to project description.
     */
    public Model(int num){
        currentBoard = new int[14];
        previousBoard = new int[14];
        for(int i = 0; i < 6; i++){
            currentBoard[i] = num;
            currentBoard[i+7] = num;
            previousBoard[i] = num;
            previousBoard[i+7] = num;
        }
        currentPlayer = 'A';
        previousPlayer = 0;
        undoCounter = 0;
        moveCounter = 0;
        endState = 0;
        listeners = new ArrayList<ChangeListener>();

    }

    /**
     * Return the number of stones in a particular pit of the current board.
     * @param index - index of particular pit
     * @return number of stones in particular pit of current board
     * precondition: index should be 0-13
     */
    public int getCurrentBoard(int index){
        return currentBoard[index];
    }

    /**
     * Add a View object to the list of Views to notify in accordance with MVC pattern.
     * @param listener - listener of View object to be added
     */
    public void addChangeListener(ChangeListener listener){
      listeners.add(listener);
   }

   /**
     * Return the number of stones in a particular pit of the previous state of the board.
     * @param index - index of particular pit
     * @return number of stones in particular pit of the previous state of the board
     * precondition: index should be 0-13
     */
    public int getPreviousBoard(int index){
        return previousBoard[index];
    }

    /**
     * Return whose turn it is.
     * @return character representing player whose turn it is (either 'A' or 'B')
     */
    public char getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Return whose turn it was before last move was made.
     * @return character representing player whose turn it was before last move was made (either 'A' or 'B')
     */
    public char getPreviousPlayer(){
        return previousPlayer;
    }

    /**
     * Returns whether game has ended or not, and if it ended, which player won (returns null if game hasn't ended yet, 'C' if it was a tie)
     * @return endState (is either null, 'A', 'B', or 'C')
     */
    public char getEndState(){
        return endState;
    }

    /**
     * Returns how many undoes have been made in a row.
     * @return number of undoes made in a row
     */
    public int getUndoCounter(){
        return undoCounter;
    }

    /**
     * Returns how many moves have been made since the last undo.
     * @return number of moves made since the last undo
     */
    public int getMoveCounter(){
        return moveCounter;
    }

    /**
     * Sets undoCounter to num.
     * @param num - number to set undoCounter to
     * precondition: num should be nonnegative
     */
    public void setUndoCounter(int num){
        undoCounter = num;
    }

    /**
     * Sets moveCounter to num.
     * @param num - number to set moveCounter to
     * precondition: num should be nonnegative
     */
    public void setMoveCounter(int num){
        moveCounter = num;
    }

    /**
     * Returns the index of the mancala of the player whose turn it currently is.
     * @return index of mancala of current player
     */
    public int getYourMancala(){
        if(currentPlayer == 'A'){
            return 6;
        }
        else{
            return 13;
        }
    }

    /**
     * Returns the index of the mancala of the player whose turn it currently isn't.
     * @return index of mancala of non-current player
     */
    public int getOthersMancala(){
        if(currentPlayer == 'B'){
            return 6;
        }
        else{
            return 13;
        }
    }

  /**
   * Prints current state of board in console, including whose turn it currently is, number of undoes that have been made in a row, and number of moves made since last undo.
   */
    public void printBoard(){
        System.out.println("Current Board: (Undo Count = " + undoCounter + " Move Count = " + moveCounter + ")");
        System.out.println("Player " + currentPlayer + "'s Turn");
        System.out.print("  ");
        for(int i = 12; i >= 7; i--){
            System.out.print(currentBoard[i] + " ");
        }
        System.out.println();
        System.out.println(currentBoard[13] + "                 " + currentBoard[6]);
        System.out.print("  ");
        for(int i = 0; i < 6; i++){
            System.out.print(currentBoard[i] + " ");
        }
        System.out.println();
    }

    /**
   * Prints previous state of board before last move was made in console, including whose turn it was during that last move.
   */
    public void printPreviousBoard(){
        System.out.println("Previous Board:");
        System.out.println("Player " + previousPlayer + "'s Turn");
        System.out.print("  ");
        for(int i = 12; i >= 7; i--){
            System.out.print(previousBoard[i] + " ");
        }
        System.out.println();
        System.out.println(previousBoard[13] + "                 " + previousBoard[6]);
        System.out.print("  ");
        for(int i = 0; i < 6; i++){
            System.out.print(previousBoard[i] + " ");
        }
        System.out.println();
    }

/**
 * Performs undo (if it is legal to do) to return board to the state it was in before last move was made.
 * @return - whether undo was legal
 */
    public boolean undo(){
        if(moveCounter == 0 || undoCounter == 3){ //illegal undo, either game has just started, or player hasn't made a move since they last pressed undo, or player has made 3 undos in a row
            // if(undoCounter == 3 && moveCounter == 1){
            //     undoCounter = 0;
            // }
            return false;
        }
        else{
            for(int i = 0; i < 14; i++){
                currentBoard[i] = previousBoard[i];
            }
            currentPlayer = previousPlayer;
            if(moveCounter != 1){ //if player's last move wasn't undone, undoCounter gets reset
                undoCounter = 0;
            }
            moveCounter = 0;
            undoCounter++;

             //notify listeners
            ChangeEvent event = new ChangeEvent(this);
            for(ChangeListener listener : listeners){
                listener.stateChanged(event);
            }

            return true;
        }
       
        
    }

    /**
     * Returns whether index references a pit that belongs to current player.
     * @param index - index of pit in board you want to check
     * @return whether index references a pit belonging to current player
     * precondition: index should be 0-13
     */
    public boolean isYourPit(int index){
        if(index <= 6 && currentPlayer == 'A'){
            return true;
        }
        else if(index >= 7 && currentPlayer == 'B'){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Switches current player to other player.
     */
    private void switchPlayer(){
        if(currentPlayer == 'A'){
            currentPlayer = 'B';
        }
        else{
            currentPlayer = 'A';
        }
         
    }

    /**
     * When a move has been made, determines whether move is legal or not, and redistributes stones based on the move if it was legal, updating state of Model accordingly.
     * @param index - pit that has been clicked/move has been made on
     * @return whether move made was legal or not
     * precondition: index should be 0-13
     */
    public boolean distributeStones(int index){
        if(index == 6 || index == 13){ //if a mancala was picked, not a valid move
            return false;
        }
        else if(currentBoard[index] == 0){ //if empty pit was picked, not a valid move
            return false;
        }
        else if(currentPlayer == 'A' && index >= 7){//if other player's pit was picked, not a valid move
            return false;
        }
        else if(currentPlayer == 'B' && index <= 5){//if other player's pit was picked, not a valid move
            return false;
        }
        for(int i = 0; i < 14; i++){//update previousBoard
            previousBoard[i] = currentBoard[i];
        }
        moveCounter++;
        if(moveCounter != 1){ //if player's last move wasn't undone, undoCounter gets reset
                undoCounter = 0;
        }
        int currentPit = index; int numStones = currentBoard[index];
        while(numStones > 0){
            currentPit++;
            currentPit%=14;
            currentBoard[currentPit]++;
            numStones--;
        }
        currentBoard[index] = numStones;

        checkOppositePit(currentPit);
        previousPlayer = currentPlayer;
        if(!isYourMancala(currentPit)){
            switchPlayer();
        }
        endGame();
        
        //notify listeners
        ChangeEvent event = new ChangeEvent(this);
        for(ChangeListener listener : listeners){
            listener.stateChanged(event);
        }
        return true;

    }

    /**
     * Returns whether index references current player's mancala.
     * @param index - pit to be checked
     * @return whether index references current player's mancala
     * precondition: index should be 0-13
     */
    public boolean isYourMancala(int index){
        if(currentPlayer == 'A' && index == 6){
            return true;
        }
        else if(currentPlayer == 'B' && index == 13){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks whether or not last stone landed in such a way that opponent's pit's stones opposite current player's pit go into current player's mancala. Carries this operation out if it did.
     * @param currentPit - index of pit that last stone landed in
     * @return whether operation was carried out/board was changed
     * precondition: currentPit should be 0-13
     */
    private boolean checkOppositePit(int currentPit){
        if(currentPit == 6 || currentPit == 13){
            return false;
        }
        else if(currentBoard[currentPit] != 1){
            return false;
        }
        else{
            int oppositePit = getOppositePit(currentPit);
            if(currentPlayer == 'A' && currentPit < 6){
                if(currentBoard[oppositePit] != 0){
                    int addStones = currentBoard[oppositePit] + 1;
                    currentBoard[oppositePit] = 0;
                    currentBoard[currentPit] = 0;
                    currentBoard[6] += addStones;
                    return true;

                }
            }
            else if(currentPlayer == 'B' && currentPit >= 7){
                if(currentBoard[oppositePit] != 0){
                    int addStones = currentBoard[oppositePit] + 1;
                    currentBoard[oppositePit] = 0;
                    currentBoard[currentPit] = 0;
                    currentBoard[13] += addStones;
                    return true;

                }
            }
            return false;
        }
    }

    /**
     * Returns pit opposite of the pit that index references.
     * @param index - pit that you want to get opposite pit of
     * @return index of pit that is opposite pit that index references
     * precondition: index should be 0-13
     */
    public int getOppositePit(int index){//returns opposite pit on board to yours
        if(index == 0){return 12;}
        if(index == 1){return 11;}
        if(index == 2){return 10;}
        if(index == 3){return 9;}
        if(index == 4){return 8;}
        if(index == 5){return 7;}
        if(index == 12){return 0;}
        if(index == 11){return 1;}
        if(index == 10){return 2;}
        if(index == 9){return 3;}
        if(index == 8){return 4;}
        if(index == 7){return 5;}
        else if(index == 6){return 13;}
        else{return 6;}
    }

    /**
     * Checks if game has ended (if all non-mancala pits on one side have been emptied) and update number of stones in mancalas and endState if it has (endState becomes player who has greater number of stones in their mancala, or becomes 'C' if there was a tie).
     */
    private void endGame(){
        boolean isEmptyA = true; boolean isEmptyB = true; int addA = 0; int addB = 0;
        for(int i = 0; i < 6; i++){
            if(currentBoard[i] != 0){
                isEmptyA = false;
                addA += currentBoard[i];
            }
            if(currentBoard[i+7] != 0){
                isEmptyB = false;
                addB += currentBoard[i+7];
            }
        }
        
        if(isEmptyA || isEmptyB){
            currentBoard[6] += addA;
            currentBoard[13] += addB;
            for(int i = 0; i < 6; i++){
                currentBoard[i] = 0;
                currentBoard[i+7] = 0;
            }
            if(currentBoard[6] > currentBoard[13]){
                endState = 'A';
            }
            else if(currentBoard[6] < currentBoard[13]){
                endState = 'B';
            }
            else{
                endState = 'C'; //tie game
            }
        }
       
    }

}