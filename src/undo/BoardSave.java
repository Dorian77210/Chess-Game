package undo;

import helper.collections.PieceCollection;

import enums.PieceType;

import engine.informations.GameInformations;

import json.JSONParser;

import org.json.JSONObject;

/**
  * The class <code>BoardSave</code> saves the crucial informations of the board
  * @version 1.0
  * @author Dorian Terbah
**/

public class BoardSave {

    /**
      * The representation of save of the game in JSON 
    **/
    private JSONObject json;

    public BoardSave(PieceCollection allPieces, GameInformations informations) {
        this.json = new JSONObject()
                        .put(JSONParser.JSON_BOARD_SAVE_PIECES, JSONParser.piecesToJSON(allPieces))
                        .put(JSONParser.JSON_BOARD_SAVE_INFORMATIONS, JSONParser.informationsToJSON(informations));
    }

    //construct a board save with its json representation
    public BoardSave(JSONObject json) {
        this.json = json;
    }

    /***************************** 
    ***********GETTER************* 
    ******************************/
    /**
      * Get all of the pieces
      * @return All of the pieces 
    **/
    public PieceCollection getAllPieces() {
        return JSONParser.jsonToPlayers(this.json.getJSONObject(JSONParser.JSON_BOARD_SAVE_PIECES));
    }

    /**
      * Get the informations of the game
      * @return The informations of the game
    **/
    public GameInformations getGameInformations() {
        return JSONParser.jsonToInformations(this.json.getJSONObject(JSONParser.JSON_BOARD_SAVE_INFORMATIONS));
    }

    /**
      * Get the json's representation of the save
      * @return The save in JSON format
    **/
    public JSONObject toJSONFormat() {
        return this.json;
    }
}