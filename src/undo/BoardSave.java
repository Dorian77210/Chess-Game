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
      * All of the pieces of the two players in json format
    **/
    private JSONObject piecesJSON;

    /**
      * The informations of the game int json format
    **/
    private JSONObject informationsJSON;

    public BoardSave(PieceCollection allPieces, GameInformations informations) {
        this.piecesJSON = JSONParser.piecesToJSON(allPieces);
        this.informationsJSON = JSONParser.informationsToJSON(informations);
    }

    //construct a board save with its json representation
    public BoardSave(JSONObject json) {
        this.piecesJSON = json.getJSONObject(JSONParser.JSON_BOARD_SAVE_PIECES);
        this.informationsJSON = json.getJSONObject(JSONParser.JSON_BOARD_SAVE_INFORMATIONS);
    }

    /***************************** 
    ***********GETTER************* 
    ******************************/
    /**
      * Get all of the pieces
      * @return All of the pieces 
    **/
    public PieceCollection getAllPieces() {
        return JSONParser.jsonToPlayers(this.piecesJSON);
    }

    /**
      * Get the informations of the game
      * @return The informations of the game
    **/
    public GameInformations getGameInformations() {
        return JSONParser.jsonToInformations(this.informationsJSON);
    }

    /**
      * Get the json's representation of the save
      * @return The save in JSON format
    **/
    public JSONObject toJSONFormat() {
        return new JSONObject()
                  .put(JSONParser.JSON_BOARD_SAVE_INFORMATIONS, this.informationsJSON)
                  .put(JSONParser.JSON_BOARD_SAVE_PIECES, this.piecesJSON);
    }
}