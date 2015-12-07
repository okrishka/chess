package chess.pieces;

public enum ChessPiece {
	PAWN("P"), KNIGHT("N"), KING("K"), BISHOP("B"), ROOK("R"), QUEEN("Q");

	private String sVal;
	private int nFootprintValue;

	ChessPiece(String s) {
		sVal = s;
		if (s.equals("P"))
			setnFootprintValue(1);
		if (s.equals("N"))
			setnFootprintValue(2);
		if (s.equals("K"))
			setnFootprintValue(3);
		if (s.equals("B"))
			setnFootprintValue(4);
		if (s.equals("R"))
			setnFootprintValue(5);
		if (s.equals("Q"))
			setnFootprintValue(6);
	}

	public String getsVal() {
		return sVal;
	}

	public int getnFootprintValue() {
		return nFootprintValue;
	}

	public void setnFootprintValue(int nFootprintValue) {
		this.nFootprintValue = nFootprintValue;
	}

	public static ChessPiece getByValue(String sVal) {
		for (ChessPiece chp : values()) {
			if (chp.getsVal().equals(sVal)) {
				return chp;
			}
		}
		throw new IllegalArgumentException("Illegal argument " + sVal);
	}

}
