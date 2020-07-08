package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//position based on the tile number

public class Knight extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATES={-17,-15,-10,-6,6,10,15,17};
    Knight(final int piecePosition, final Alliance pieceAlliance)
    {
        super(piecePosition,pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        //loop through candidate posn legal moves and offset.
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentCoordinate: CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinatioCoordinate = this.piecePosition + currentCoordinate;
            //declare currentCandidateOffset
            if (BoardUtils.isValidTileCoordinate(candidateDestinatioCoordinate))
            {
                if(isFirstColumnExlusion(this.piecePosition,currentCandidateOffset)
                        || isSecondColumnExclusion(this.piecePosition,currentCandidateOffset)
                || isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinatioCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestinantion = candidateDestinationTile.getPieces();
                    final Alliance pieceAlliance = pieceAtDestinantion.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
                //if not outoff bound
            }
        }
        return Collections.unmodifiableMap(legalMoves);

    }
    public static boolean isFirstColumnExlusion(final int currentPosition,final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] &&((candidateOffset==-17) || (candidateOffset ==-10) || (candidateOffset ==6) || (candidateOffset ==15));
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset)==-10 || candidateOffset ==6));

    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset)==10 || candidateOffset ==-6));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset)==10 || candidateOffset ==-6));
    }

}
