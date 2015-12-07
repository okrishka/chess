package chess.pieces;

public class Rook extends Piece {

	public Rook(int x, int y) {
		super(x, y, ChessPiece.ROOK);
	}

	@Override
	public boolean isAttackedSpot(int fromX, int fromY, int toX, int toY) {
		if (toX == fromX)
			return true;
		if (toY == fromY)
			return true;
		return false;
	}

}
