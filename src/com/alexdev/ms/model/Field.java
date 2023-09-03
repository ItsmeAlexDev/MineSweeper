package com.alexdev.ms.model;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

import com.alexdev.ms.exception.ExplosionException;

public class Field {
	
	private final int row_X;
	private final int col_Y;
	
	private boolean mined;
	private boolean open;
	private boolean marked;
	
	private List<Field> neighbors = new ArrayList<>();
	
	Field(int x, int y){
		this.row_X = x;
		this.col_Y = y;
	}
	
	boolean addNeighbor(Field neighbor) {
		boolean isDiagonal = row_X != neighbor.row_X && col_Y != neighbor.col_Y;
		int delta = abs(row_X - neighbor.row_X) + abs(col_Y - neighbor.col_Y);
		
		if (delta == 1 && !isDiagonal) {
			neighbors.add(neighbor);
			return true;
		} else if (delta == 2 && isDiagonal) {
			neighbors.add(neighbor);
			return true;
		} else 
			return false;
	}
	
	void toggleMarkup() {
		if (!open)
			marked = !marked;
	}
	
	void mark() {
		marked = true;
	}
	
	void markOff() {
		marked = false;
	}
	
	void mine() {
		mined = true;
	}
	
	boolean open() {
		if (!open && !marked) {
			open = true;
			
			if (mined)
				throw new ExplosionException();
			
			if (safeNeighborhood())
				neighbors.forEach(neighbor -> neighbor.open());
			
			return true;
		} else
			return false;
	}
	
	
	boolean safeNeighborhood() {
		return neighbors.stream().noneMatch(neighbor -> neighbor.isMined());
	}
	
	public boolean isMined() {
		return mined;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public boolean isLocked() {
		return !open;
	}

	public int getRow_X() {
		return row_X;
	}

	public int getCol_Y() {
		return col_Y;
	}
	
	boolean goalAchieved() {
		return (!mined && open) || (mined && marked);
	}
	
	long minesInNeighborhood() {
		return neighbors.stream().filter(neighbor -> neighbor.mined).count();
	}
	
	void reiniciar() {
		open = false;
		mined = false;
		marked = false;
	}
	
	@Override
	public String toString() {
		if (marked)
			return "x";
		if (open && mined)
			return "*";
		if (open && minesInNeighborhood() > 0)
			return Long.toString(minesInNeighborhood());
		if (open)
			return " ";
		return "?";
	}
}
