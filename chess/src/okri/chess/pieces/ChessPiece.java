package okri.chess.pieces;

public enum ChessPiece {
	PAWN("P"), KNIGHT("N"), KING("K"), BISHOP("B"), ROOK("R"), QUEEN("Q");

	private String sVal;
	private int nFootprintValue;

	ChessPiece(String s) {
		sVal = s;
		if (s.equals("P"))
			setFootprintValue(1);
		if (s.equals("N"))
			setFootprintValue(2);
		if (s.equals("K"))
			setFootprintValue(3);
		if (s.equals("B"))
			setFootprintValue(4);
		if (s.equals("R"))
			setFootprintValue(5);
		if (s.equals("Q"))
			setFootprintValue(6);
	}

	public String getVal() {
		return sVal;
	}

	public int getFootprintValue() {
		return nFootprintValue;
	}

	private void setFootprintValue(int nFootprintValue) {
		this.nFootprintValue = nFootprintValue;
	}

	public static ChessPiece getByValue(String sVal) {
		for (ChessPiece chp : values()) {
			if (chp.getVal().equals(sVal)) {
				return chp;
			}
		}
		throw new IllegalArgumentException("Illegal argument " + sVal);
	}

}
