package okri.chess.pieces;

public class Queen extends Piece {

	public Queen(int x, int y) {
		super(x, y, ChessPiece.QUEEN);
	}

	@Override
	public boolean isAttackedSpot(int fromX, int fromY, int toX, int toY) {
		if (Math.abs(toX - fromX) == Math.abs(toY - fromY))
			return true;
		if (toX == fromX)
			return true;
		if (toY == fromY)
			return true;
		return false;
	}

}
