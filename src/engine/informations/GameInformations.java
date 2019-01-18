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

    /**
      * The number of rounds 
    **/
    private int rounds;

    /**
      * Boolean used to know if the white player is checked 
    **/
    private boolean isWhitePlayerChecked;

    /**
      * Boolean used to know if the black player is checked 
    **/
    private boolean isBlackPlayerChecked;

    public GameInformations() {
        //default values
        this.isEndOfGame = false;

        this.isWhitePlayerChecked = false;
        this.isBlackPlayerChecked = false;

        this.rounds = 1;
    }

    public GameInformations(GameInformations informations) {
        this.isEndOfGame = informations.isEndOfGame;
        this.rounds = informations.rounds;
        this.isWhitePlayerChecked = informations.isWhitePlayerChecked;
        this.isBlackPlayerChecked = informations.isBlackPlayerChecked;
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
      * Get the state of checked for the white player
      * @return The state of checked for the white player 
    **/
    public boolean isWhitePlayerChecked() {
        return this.isWhitePlayerChecked;
    }

    /**
      * Get the state of checked for the black player
      * @return The state of checked for the black player
    **/
    public boolean isBlackPlayerChecked() {
        return this.isBlackPlayerChecked;
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

    /**
      * Check if the current player is checked
      * @return True if the current player is checked, else false 
    **/
    public boolean isCurrentPlayerChecked() {
        return (this.isBlackPlayerPlaying()) ? this.isBlackPlayerChecked : this.isWhitePlayerChecked; 
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
      * Set a new value for the rounds 
      * @param rounds The new value
    **/
    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    /**
      * Set a new state for the end of the game
      * @param isEndOfGame The new state 
    **/
    public void setIsEndOfGame(boolean isEndOfGame) {
        this.isEndOfGame = isEndOfGame;
    }

    /**
      * Toggle the black checked state 
    **/
    public void toggleIsBlackPlayerChecked() {
        this.isBlackPlayerChecked = !this.isBlackPlayerChecked;
    }

    /**
      * Toggle the white checked state 
    **/
    public void toggleIsWhitePlayerChecked() {
        this.isWhitePlayerChecked = !this.isWhitePlayerChecked;
    }

    /**
      * Set the white checked state
      * @param isWhitePlayerChecked The new state 
    **/
    public void setIsWhitePlayerChecked(boolean isWhitePlayerChecked) {
        this.isWhitePlayerChecked = isWhitePlayerChecked;
    }

    /**
      * Set the black checked state
      * @param isBlackPlayerChecked The new state
    **/
    public void setIsBlackPlayerChecked(boolean isBlackPlayerChecked) {
        this.isBlackPlayerChecked = isBlackPlayerChecked;
    } 
}