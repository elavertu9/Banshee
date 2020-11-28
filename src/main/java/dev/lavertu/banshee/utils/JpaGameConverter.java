package dev.lavertu.banshee.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lavertu.banshee.exception.api.GameMappingException;
import dev.lavertu.banshee.game.Color;
import dev.lavertu.banshee.game.Coordinate;
import dev.lavertu.banshee.game.GameBoard;
import dev.lavertu.banshee.game.pieces.iPiece;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

@Converter
public class JpaGameConverter implements AttributeConverter<GameBoard, String> {

//    private static final Logger LOGGER = LogManager.getLogger(JpaGameConverter.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(JpaGameConverter.class);

    private static final int COLS = 8;
    private static final int ROWS = 4;

    @Override
    public String convertToDatabaseColumn(GameBoard gameBoard) {
        return convertGameBoardToJson(gameBoard).toString();
    }

    @Override
    public GameBoard convertToEntityAttribute(String gameBoardJson) {
        return convertJsonToGameBoard(new JSONObject(gameBoardJson));
    }

    public JSONObject convertGameBoardToJson(GameBoard gameBoard) {
        JSONArray jsonGameBoard = new JSONArray();
        for(int row = 0; row < gameBoard.getRows(); row++){
            JSONArray jsonRow = new JSONArray();
            for(int col = 0; col < gameBoard.getCols(); col++){
                iPiece piece = gameBoard.pieceAt(new Coordinate(row, col));
                JSONObject jsonCell = new JSONObject();
                jsonCell.put("faceUp", piece.getIsFaceUp());
                jsonCell.put("color", piece.getColor().toString());
                jsonCell.put("class", piece.getClass().getCanonicalName());
                jsonCell.put("rank", piece.getRank());
                jsonRow.put(jsonCell);

            }
            jsonGameBoard.put(jsonRow);
        }
        JSONObject jsonGame = new JSONObject();
        jsonGame.put("game", jsonGameBoard);
        return jsonGame;
    }

    public GameBoard convertJsonToGameBoard(JSONObject jsonGame) {
        iPiece[][] gameBoard = new iPiece[ROWS][COLS];
        JSONArray jsonGameBoard = jsonGame.getJSONArray("game");

        for(int row = 0; row < jsonGameBoard.length(); row++) {
            JSONArray jsonRow = jsonGameBoard.getJSONArray(row);

            for(int col = 0; col < jsonRow.length(); col++) {
                JSONObject jsonCell = jsonRow.getJSONObject(col);
                String pieceName = jsonCell.getString("class");
                String pieceColor = jsonCell.getString("color");
                boolean pieceFaceUp = jsonCell.getBoolean("faceUp");

                try {
                    Coordinate position = new Coordinate(row, col);
                    iPiece piece = (iPiece) Class.forName(pieceName).getConstructor(Color.class).newInstance(Color.valueOf(pieceColor));;
                    if (pieceFaceUp) {
                        piece.flipPiece();
                    }
                    gameBoard[position.getRow()][position.getColumn()] = piece;
                }
                catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    LOGGER.error(ex.getMessage());
                    // throw new GameMappingException("Could not find map JSON piece representation for iPiece class: " + pieceName);
                    // TODO - We can't throw exception here since AttributeConverter extends an interface - add better exception handling so we don't create a new game board
                    return new GameBoard();
                }
            }
        }
        return new GameBoard(gameBoard);
    }

}
