package okri.chess;

public class BoardSpot implements Comparable<BoardSpot> {
	@Override
	public String toString() {
		return "BoardSpot [x=" + x + ", y=" + y + "]";
	}

	private int x;
	private int y;

	public BoardSpot(int x2, int y2) {
		x = x2;
		y = y2;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardSpot other = (BoardSpot) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(BoardSpot anotherSpot) {
		return (this.y * 10 + this.x) - (anotherSpot.y * 10 + anotherSpot.x);
	}
}
