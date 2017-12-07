package okri.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import okri.chess.pieces.Piece;

public class Board {
	private int nHorizDim;
	private int nVertDim;
	private Map<BoardSpot, Piece> piecesMap = null;
	private SortedSet<BoardSpot> unattackedSpotSet = null;
	private boolean[][] attackedSpotArr = null;

	public Board(int nHorizDim, int nVertDim) {
		this.setHorizDim(nHorizDim);
		this.setVertDim(nVertDim);
		attackedSpotArr = new boolean[nHorizDim][nVertDim];
		piecesMap = new HashMap<BoardSpot, Piece>();
		updateUnattackedSpotSet();
	}

	public Map<BoardSpot, Piece> getPiecesMap() {
		return piecesMap;
	}

	public void setPiecesMap(Map<BoardSpot, Piece> piecesMap) {
		attackedSpotArr = new boolean[nHorizDim][nVertDim];
		this.piecesMap = piecesMap;
		markAttackedSpots();
	}

	public SortedSet<BoardSpot> getUnattackedSpotSet() {
		return unattackedSpotSet;
	}

	public void setUnattackedSpotsSet(SortedSet<BoardSpot> unchargedSpotsSet) {
		this.unattackedSpotSet = unchargedSpotsSet;
	}

	public boolean[][] getAttackedSpotArr() {
		return attackedSpotArr;
	}

	public void setAttackedSpotArr(boolean[][] chargedSpotArr) {
		this.attackedSpotArr = chargedSpotArr;
	}

	public int getHorizDim() {
		return nHorizDim;
	}

	public int getVertDim() {
		return nVertDim;
	}

	public void setVertDim(int nVertDim) {
		this.nVertDim = nVertDim;
	}

	public void setHorizDim(int nHorizDim) {
		this.nHorizDim = nHorizDim;
	}

	public void markAttackedSpots() {
		for (Piece piece : piecesMap.values()) {
			markAttackedSpots(piece);
		}
		updateUnattackedSpotSet();
	}

	private void updateUnattackedSpotSet() {
		this.unattackedSpotSet = new TreeSet<BoardSpot>();
		for (int i = 0; i < nHorizDim; i++) {
			for (int j = 0; j < nVertDim; j++) {
				if (!attackedSpotArr[i][j])
					this.unattackedSpotSet.add(new BoardSpot(i, j));
			}
		}
	}

	private void markAttackedSpots(Piece piece) {

		for (int i = 0; i < this.getAttackedSpotArr().length; i++) {
			for (int j = 0; j < this.getAttackedSpotArr().length; j++) {
				if (this.getAttackedSpotArr()[i][j])
					continue;
				this.getAttackedSpotArr()[i][j] = piece.isAttackedSpot(piece.getBoardPosition().getX(),
						piece.getBoardPosition().getY(), i, j);
			}
		}
	}
	
	public void printBoard() {
		for (int i = nHorizDim - 1; i > -1; i--) {
			for (int j = 0; j < nVertDim; j++) {
				BoardSpot boardSpot = new BoardSpot(i, j);
				if (piecesMap.containsKey(boardSpot))
					System.out.print(piecesMap.get(boardSpot).toString() + " ");
				else if (attackedSpotArr[i][j])
					System.out.print("0 ");
				else
					System.out.print("* ");
			}
			System.out.println();
		}
	}

}
