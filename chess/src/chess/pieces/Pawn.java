package chess.pieces;

public class Pawn extends Piece {

	public Pawn(int x, int y) {
		super(x, y, ChessPiece.PAWN);
	}

	@Override
	public boolean isAttackedSpot(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY)
			return true;
		if (toX == fromX + 1 && toY == fromY + 1)
			return true;
		if (toX == fromX + 1 && toY == fromY - 1)
			return true;
		return false;
	}
}
