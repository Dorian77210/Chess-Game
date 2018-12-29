package engine.informations;

/**
  * The class <code>GameInformations</code> stores all informations about the game
  * @version 1.0
  * @author Dorian Terbah 
**/

public class GameInformations {

    /**
      * Represents the end of the game 
    **/
    private boolean isEndOfGame;

    private int rounds;

    public GameInformations() {
        //default values
        this.isEndOfGame = false;

        this.rounds = 1;
    }

    /***************************** 
    *************GETTER*********** 
    
    *****************************/
    /**
      * Get the begin state of the game
      * @return True if it is the begin if the game, else false 
    **/
    public boolean isBeginOfGame() {
        return (this.rounds == 1);
    }

    /**
      * Get the end state of the game
      * @return true if the game is finished, else false 
    **/
    public boolean isEndOfGame() {
        return this.isEndOfGame;
    }


    /**
      * Check if it is to the black player to play
      * @return True if it is, else false 
    **/
    public boolean isBlackPlayerPlaying() {
        return ((this.rounds % 2) == 0);
    }

    /**
      * Get the number of rounds of the game
      * @return The number of tounds of the game
    **/
    public int getRounds() {
        return this.rounds;
    }

    /***************************** 
    *************SETTER*********** 
    *****************************/

    /**
      * Increments the number of rounds 
    **/
    public void incrementRounds() {
        this.rounds++;
    }

    /**
      * Set a new state for the end of the game
      * @param isEndOfGame The new state 
    **/
    public void setIsEndOfGame(boolean isEndOfGame) {
        this.isEndOfGame = isEndOfGame;
    }
}