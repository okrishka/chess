package chess.pieces;

public class Bishop extends Piece {

	public Bishop(int x, int y) {
		super(x, y, ChessPiece.BISHOP);
	}

	@Override
	public boolean isAttackedSpot(int fromX, int fromY, int toX, int toY) {
		if (Math.abs(toX - fromX) == Math.abs(toY - fromY))
			return true;

		return false;
	}

}
