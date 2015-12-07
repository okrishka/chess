package chess;

import java.util.Set;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class PieceFactory {
	static Piece createPiece(String sPiece, int nHorizCoordinate, int nVertCoordinate) {
		if (sPiece.equals("P")) {
			// create pawn
			return new Pawn(nHorizCoordinate, nVertCoordinate);
		}
		if (sPiece.equals("K")) {
			// create king
			return new King(nHorizCoordinate, nVertCoordinate);
		}
		if (sPiece.equals("B")) {
			// create bishop
			return new Bishop(nHorizCoordinate, nVertCoordinate);
		}
		if (sPiece.equals("Q")) {
			// create queen
			return new Queen(nHorizCoordinate, nVertCoordinate);
		}
		if (sPiece.equals("R")) {
			// create rook
			return new Rook(nHorizCoordinate, nVertCoordinate);
		}
		if (sPiece.equals("N")) {
			// create knight
			return new Knight(nHorizCoordinate, nVertCoordinate);
		}
		return null;
	}

	public static Piece createPiece(String sPiece, BoardSpot boardSpot) {
		return createPiece(sPiece, boardSpot.getX(), boardSpot.getY());

	}

	public static Piece createPiece(String sPiece, Board board) {
		for (BoardSpot unchargedBoardSpot : board.getUnattackedSpotsSet()) {
			Piece potentialPiece = createPiece(sPiece, unchargedBoardSpot.getX(), unchargedBoardSpot.getY());
			if (!isPotentialPiceAttackingExistingPieces(board.getPiecesMap().keySet(), potentialPiece))
				return potentialPiece;
		}
		return null;
	}
	
	public static boolean isPotentialPiceAttackingExistingPieces(Set<BoardSpot> takenSpots, Piece potentialPiece){
		boolean bPotentialPieceAttacksExistingPiece = false;
		for (BoardSpot takenSpot : takenSpots) {
			bPotentialPieceAttacksExistingPiece = potentialPiece.isAttackedSpot(
					potentialPiece.getBoardPosition().getX(), potentialPiece.getBoardPosition().getY(),
					takenSpot.getX(), takenSpot.getY());
			if (bPotentialPieceAttacksExistingPiece)
				break;
		}
		return bPotentialPieceAttacksExistingPiece;
	}

}
