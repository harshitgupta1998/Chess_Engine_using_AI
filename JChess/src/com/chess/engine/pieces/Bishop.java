package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Board;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9};

    Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffsets : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if(isFirstColumnExlusion(this.piecePosition, candidateDestinationCoordinate )||isEighthColumnExclusion(this.piecePosition, candidateDestinationCoordinate))
                {
                    break;
                }


                candidateDestinationCoordinate += candidateCoordinateOffsets;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(Board, this, candidateDestinationCoordinate));

                    } else {
                        final Piece pieceAtDestinantion = candidateDestinationTile.getPieces();
                        final Alliance pieceAlliance = pieceAtDestinantion.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.attackMove(Board, this, candidateDestinationCoordinate));
                        }
                        break;


                    }
                }

            }
        }

        return Collections.unmodifiableMap(legalMoves);

    }
    public static boolean isFirstColumnExlusion(final int currentPosition,final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] &&((candidateOffset==-9) || (candidateOffset ==-10) || (candidateOffset ==7) || (candidateOffset ==15));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset)==-7 || candidateOffset ==9));
    }
}
