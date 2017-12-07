package okri.chess.pieces;

public class Knight extends Piece {

	public Knight(int x, int y) {
		super(x, y, ChessPiece.KNIGHT);
	}

	@Override
	public boolean isAttackedSpot(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY)
			return true;
		if (toX == fromX + 2 && toY == fromY + 1)
			return true;
		if (toX == fromX + 2 && toY == fromY - 1)
			return true;
		if (toX == fromX + 1 && toY == fromY + 2)
			return true;
		if (toX == fromX + 1 && toY == fromY - 2)
			return true;
		if (toX == fromX - 1 && toY == fromY + 2)
			return true;
		if (toX == fromX - 1 && toY == fromY - 2)
			return true;
		if (toX == fromX - 2 && toY == fromY + 1)
			return true;
		if (toX == fromX - 2 && toY == fromY - 1)
			return true;
		return false;
	}

}
