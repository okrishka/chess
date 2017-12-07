package okri.chess;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import okri.chess.pieces.ChessPiece;
import okri.chess.pieces.Piece;

import java.util.EnumSet;

public class Dialog {

	private static BoardSpot askForPiecePosition(Scanner reader, Board board) {
		int nHorizCoordinate = 0;
		int nVertCoordinate = 0;
		do {
			System.out.println("Enter horizontal coordinate (0 - " + (board.getHorizDim() - 1) + "):");
			nHorizCoordinate = reader.nextInt();
		} while (nHorizCoordinate < 0 || nHorizCoordinate > (board.getHorizDim() - 1));
		do {
			System.out.println("Enter vertical coordinate (0 - " + (board.getVertDim() - 1) + "):");
			nVertCoordinate = reader.nextInt();
		} while (nVertCoordinate < 0 || nVertCoordinate > (board.getVertDim() - 1));
		return new BoardSpot(nHorizCoordinate, nVertCoordinate);

	}

	private static Board askForBoardDimentions(Scanner reader) {
		int nHorizDim = 0;
		int nVertDim = 0;
		do {
			System.out.println("Enter horizontal dimention:");
			nHorizDim = reader.nextInt();
		} while (nHorizDim < 0);
		do {
			System.out.println("Enter vertical dimention:");
			nVertDim = reader.nextInt();
		} while (nVertDim < 0);
		return new Board(nHorizDim, nVertDim);

	}

	public static void main(String[] args) throws InterruptedException {
		Board board = null;

		EnumSet<ChessPiece> chessPiecesEnumSet = EnumSet.allOf(ChessPiece.class);
		Set<String> allowedPieceEntrySet = new HashSet<String>();
		Scanner reader = new Scanner(System.in); // Reading from System.in
		int nPieces = 0;
		BoardSpot firstPiecePosition = null;
		for (ChessPiece chp : chessPiecesEnumSet) {
			allowedPieceEntrySet.add(chp.getVal());
		}
		try {
			for (;; nPieces++) {
				String sPiece = null;
				Piece newPiece = null;

				if (nPieces == 0) {
					board = askForBoardDimentions(reader);
					firstPiecePosition = askForPiecePosition(reader, board);

				}
				do {
					System.out.println("Enter piece number " + (nPieces + 1)
							+ " (P - pawn, B - bishop, K - king, Q - queen, N - knight, R - rook)");
					sPiece = reader.next();
					sPiece = new String(sPiece.toUpperCase());
				} while (!allowedPieceEntrySet.contains(sPiece));
				if (nPieces > 0) {
					newPiece = PieceFactory.createPiece(sPiece, board);
					if (newPiece == null) {

						System.out.println("All spots are attacked. New Piece could not be placed on the board");
						board = AccomodatePieces.accomodatePieces(board, sPiece);
						if (board == null) {
							System.out.println("Can't accomodate " + (nPieces + 1) + "th piece. Quit");
							return;
						} else {
							System.out.println("Piece has been accomodated successfully");
							continue;
						}

					}
				} else {
					newPiece = PieceFactory.createPiece(sPiece, firstPiecePosition);
				}
				board.getPiecesMap().put(newPiece.getBoardPosition(), newPiece);
				board.markAttackedSpots();
				board.printBoard();
			}
		} finally {
			reader.close();
		}
	}

}
