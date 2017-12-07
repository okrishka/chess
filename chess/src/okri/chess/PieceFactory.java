package okri.chess;

import java.util.Set;

import okri.chess.pieces.Bishop;
import okri.chess.pieces.King;
import okri.chess.pieces.Knight;
import okri.chess.pieces.Pawn;
import okri.chess.pieces.Piece;
import okri.chess.pieces.Queen;
import okri.chess.pieces.Rook;

public class PieceFactory {
	public static Piece createPiece(String sPiece, int nHorizCoordinate, int nVertCoordinate) {
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
		for (BoardSpot unattackedBoardSpot : board.getUnattackedSpotSet()) {
			Piece potentialPiece = createPiece(sPiece, unattackedBoardSpot.getX(), unattackedBoardSpot.getY());
			if (!isPotentialPieceAttackingExistingPieces(board.getPiecesMap().keySet(), potentialPiece))
				return potentialPiece;
		}
		return null;
	}
	
	public static boolean isPotentialPieceAttackingExistingPieces(Set<BoardSpot> takenSpots, Piece potentialPiece){
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
