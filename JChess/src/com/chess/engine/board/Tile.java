package com.chess.engine.board;
import com.chess.engine.pieces.Piece; //immutablity is important try and provide static factories to reuse the instances

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileCoordinate;

    private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap= new HashMap<>();
        for(int i=0;i<64;i++)
        {
            emptyTileMap.put(i,new EmptyTile(i));
        }
        //return ImmutableMap.copyOf(emptyTileMap); //download guava'
        return Collections.unmodifiableMap(emptyTileMap);
        //return emptyTileMap;
    }
    public static Tile createTile(final int tileCoordinate, final Piece piece)
    {
        return piece!= null? new OccupiedTile(tileCoordinate,piece): EMPTY_TILES_CACHE.get(tileCoordinate);
    }


    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPieces();

    public static final class EmptyTile extends Tile {
        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPieces() {
            return null;
        }
    }
    public static final class OccupiedTile extends Tile {
    private Piece pieceOnTile; // keeping the class immutable
    private OccupiedTile(int tileCoordinate,Piece pieceOnTile)
    {
        super(tileCoordinate);
        this.pieceOnTile=pieceOnTile;
    }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPieces() {
            return this.pieceOnTile;
        }
    }
}
