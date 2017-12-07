package okri.chess.pieces;

public class King extends Piece {

	public King(int x, int y) {
		super(x, y, ChessPiece.KING);
	}

	@Override
	public boolean isAttackedSpot(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY)
			return true;
		double nDist = Math.pow((toX - fromX), 2) + Math.pow((toY - fromY), 2);
		return nDist == 1 || nDist == 2;
	}

}
