package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.Map.Entry;

import chess.pieces.ChessPiece;
import chess.pieces.Piece;

public class AccomodatePieces {
	public static Board accomodatePieces(Board oldBoard, String sPiece) throws InterruptedException {
		Board newBoard = new Board(oldBoard.getnHorizDim(), oldBoard.getnVertDim());
		
		System.out.println("Trying to accomodate this piece");

		List<ChessPiece> piecesList = createListOfPieces(oldBoard.getPiecesMap().values(), sPiece);

		// sort list by potential footprint: Queen highest, Pawn - smallest
		Collections.sort(piecesList, new Comparator<ChessPiece>() {
			@Override
			public int compare(ChessPiece one, ChessPiece two) {
				return two.getnFootprintValue() - one.getnFootprintValue();
			}
		});

		for (ChessPiece piece : piecesList) {
			BoardSpot spot = findFirstSpotWithLeastFootprint(piece, newBoard);
			if (spot == null)
				return null;
			Piece newPiece = PieceFactory.createPiece(piece.getsVal(), spot.getX(), spot.getY());
			newBoard.getPiecesMap().put(newPiece.getBoardPosition(), newPiece);
			newBoard.markAttackedSpots();
			newBoard.printBoard();
			System.out.println();
			Thread.sleep(1000);
		}
		return newBoard;

	}

	private static BoardSpot findFirstSpotWithLeastFootprint(ChessPiece piece, Board board) {
		TreeMap<Integer, BoardSpot> footprintsMap = new TreeMap<Integer, BoardSpot>();
		for (BoardSpot potentialSpot : board.getUnattackedSpotsSet()) {
			Piece potentialPiece = PieceFactory.createPiece(piece.getsVal(), potentialSpot.getX(),
					potentialSpot.getY());
			if (PieceFactory.isPotentialPiceAttackingExistingPieces(board.getPiecesMap().keySet(), potentialPiece))
				continue;
			int nCount = countFootprintOnUnattackedSpots(board.getUnattackedSpotsSet(), potentialPiece);
			if (!footprintsMap.containsKey(nCount)){
				
				footprintsMap.put(nCount, potentialSpot);
			}
			//System.out.println(nCount + " " + potentialSpot.toString());
		}
		Entry<Integer, BoardSpot> ent = footprintsMap.firstEntry();
		return (ent == null ? null : ent.getValue());
	}

	private static int countFootprintOnUnattackedSpots(SortedSet<BoardSpot> unattackedSpotsSet, Piece potentialPiece) {
		int nCount = 0;
		for (BoardSpot checkSpot : unattackedSpotsSet) {
			if (potentialPiece.isAttackedSpot(potentialPiece.getBoardPosition().getX(),
					potentialPiece.getBoardPosition().getY(), checkSpot.getX(), checkSpot.getY()))
				nCount++;
		}
		return nCount;
	}

	private static List<ChessPiece> createListOfPieces(Collection<Piece> values, String sPiece) {
		List<ChessPiece> piecesList = new ArrayList<ChessPiece>();
		for (Piece pieceOnTheBoard : values) {
			piecesList.add(pieceOnTheBoard.getChessEnumType());
		}
		piecesList.add(ChessPiece.getByValue(sPiece));
		return piecesList;
	}

}
