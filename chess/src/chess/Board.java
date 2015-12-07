package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import chess.pieces.Piece;

public class Board {
	private int nHorizDim;
	private int nVertDim;
	private Map<BoardSpot, Piece> piecesMap = null;
	private SortedSet<BoardSpot> unattackedSpotsSet = null;
	private boolean[][] attackedSpotsArr = null;

	public Board(int nHorizDim, int nVertDim) {
		this.setnHorizDim(nHorizDim);
		this.setnVertDim(nVertDim);
		attackedSpotsArr = new boolean[nHorizDim][nVertDim];
		piecesMap = new HashMap<BoardSpot, Piece>();
		updateUnattackedSpotsSet();
	}

	public Map<BoardSpot, Piece> getPiecesMap() {
		return piecesMap;
	}

	public void setPiecesMap(Map<BoardSpot, Piece> piecesMap) {
		attackedSpotsArr = new boolean[nHorizDim][nVertDim];
		this.piecesMap = piecesMap;
		markAttackedSpots();
	}

	public SortedSet<BoardSpot> getUnattackedSpotsSet() {
		return unattackedSpotsSet;
	}

	public void setUnattackedSpotsSet(SortedSet<BoardSpot> unchargedSpotsSet) {
		this.unattackedSpotsSet = unchargedSpotsSet;
	}

	public boolean[][] getAttackedSportsArr() {
		return attackedSpotsArr;
	}

	public void setAttackedSportsArr(boolean[][] chargedSportsArr) {
		this.attackedSpotsArr = chargedSportsArr;
	}

	public int getnHorizDim() {
		return nHorizDim;
	}

	public int getnVertDim() {
		return nVertDim;
	}

	public void setnVertDim(int nVertDim) {
		this.nVertDim = nVertDim;
	}

	public void setnHorizDim(int nHorizDim) {
		this.nHorizDim = nHorizDim;
	}

	public void markAttackedSpots() {
		for (Piece piece : piecesMap.values()) {
			piece.markAttackedSpots(this);
		}
		updateUnattackedSpotsSet();
	}

	private void updateUnattackedSpotsSet() {
		this.unattackedSpotsSet = new TreeSet<BoardSpot>();
		for (int i = 0; i < nHorizDim; i++) {
			for (int j = 0; j < nVertDim; j++) {
				if (!attackedSpotsArr[i][j])
					this.unattackedSpotsSet.add(new BoardSpot(i, j));
			}
		}
	}

	public void printBoard() {
		for (int i = nHorizDim - 1; i > -1; i--) {
			for (int j = 0; j < nVertDim; j++) {
				BoardSpot boardSpot = new BoardSpot(i, j);
				if (piecesMap.containsKey(boardSpot))
					System.out.print(piecesMap.get(boardSpot).toString() + " ");
				else if (attackedSpotsArr[i][j])
					System.out.print("0 ");
				else
					System.out.print("* ");
			}
			System.out.println();
		}
	}

}
