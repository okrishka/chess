package chess.pieces;

import chess.Board;
import chess.BoardSpot;

public abstract class Piece {

	private ChessPiece ChessEnumType;
	private BoardSpot boardPosition;

	public BoardSpot getBoardPosition() {
		return boardPosition;
	}

	public void setBoardPosition(BoardSpot boardPosition) {
		this.boardPosition = boardPosition;
	}

	public ChessPiece getChessEnumType() {
		return ChessEnumType;
	}

	public void setChessEnumType(ChessPiece chessEnumType) {
		ChessEnumType = chessEnumType;
	}

	public Piece(int x, int y, ChessPiece type) {
		setBoardPosition(new BoardSpot(x, y));
		setChessEnumType(type);
	}

	public void markAttackedSpots(Board board) {

		for (int i = 0; i < board.getAttackedSportsArr().length; i++) {
			for (int j = 0; j < board.getAttackedSportsArr().length; j++) {
				if (board.getAttackedSportsArr()[i][j])
					continue;
				board.getAttackedSportsArr()[i][j] = isAttackedSpot(this.getBoardPosition().getX(),
						this.getBoardPosition().getY(), i, j);
			}
		}
	}

	public abstract boolean isAttackedSpot(int fromX, int fromY, int toX, int toY);

	@Override
	public String toString() {
		return ChessEnumType.getsVal();
	}

}
