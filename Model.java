import java.util.ArrayList; import javax.swing.event.*;
public class Model{
    private int currentBoard[]; //0-5 are player A's pits (A1-A6), 6 is player A's mancala, 7-12 are player B's pits (B1-B6), 13 is player B's mancala
    private int previousBoard[];
    private ArrayList<ChangeListener> listeners;
    private char currentPlayer;
    private char previousPlayer;
    private int undoCounter;
    private int moveCounter; //number of moves made since start of the game/last undo
    private char endState; //if game ends, will contain which player won (would be C if game tied); else has null char

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

    public int getCurrentBoard(int index){
        return currentBoard[index];
    }

    public void addChangeListener(ChangeListener listener)
   {
      listeners.add(listener);
   }

    public int getPreviousBoard(int index){
        return previousBoard[index];
    }

    public char getCurrentPlayer(){
        return currentPlayer;
    }

    public char getPreviousPlayer(){
        return previousPlayer;
    }

    public char getEndState(){
        return endState;
    }

    public int getUndoCounter(){
        return undoCounter;
    }

    public int getMoveCounter(){
        return moveCounter;
    }

    public void setUndoCounter(int num){
        undoCounter = num;
    }

    public void setMoveCounter(int num){
        moveCounter = num;
    }

    public void setCurrentPlayer(char c){
        currentPlayer = c;
    }

    public void setPreviousPlayer(char c){
        previousPlayer = c;
    }

    public int getYourMancala(){
        if(currentPlayer == 'A'){
            return 6;
        }
        else{
            return 13;
        }
    }

    public int getOthersMancala(){
        if(currentPlayer == 'B'){
            return 6;
        }
        else{
            return 13;
        }
    }

    public void printBoard(){
        System.out.println("Current Board:");
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


    public boolean undo(){
        if(moveCounter == 0 || undoCounter == 3){ //illegal undo, either game has just started, or player hasn't made a move since they last pressed undo, or player has made 3 undos in a row
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

    public void switchPlayer(){
        if(currentPlayer == 'A'){
            currentPlayer = 'B';
        }
        else{
            currentPlayer = 'A';
        }
         
    }

    public boolean distributeStones(int index){//returns whether move was valid or not, takes care of updating board according to move that was made if move was valid
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
            //System.out.print("Not Your Mancala, current player is " + currentPlayer + ", current pit is " + currentPit);
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

    public boolean checkOppositePit(int currentPit){
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

    public void endGame(){//to check if game has ended and update endState and board if it has
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