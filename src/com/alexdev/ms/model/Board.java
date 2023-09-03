package com.alexdev.ms.model;

import static java.lang.Math.random;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import com.alexdev.ms.exception.ExplosionException;

public class Board {
	
	private int rows;
	private int cols;
	private int mines;
	
	private final List<Field> fields = new ArrayList<>();

	public Board(int rows, int cols, int mines) {
		this.rows = rows;
		this.cols = cols;
		this.mines = mines;
		
		generateFields();
		associateNeighbors();
		mineFields();
	}
	
	public void open(int row, int col) {
		try {
			fields.stream()
				.filter(field -> field.getRow_X() == row &&
				     	         field.getCol_Y() == col)
				.findFirst()
				.ifPresent(field -> field.open());
		} catch (ExplosionException e) {
			
			fields.forEach(field -> field.setOpen(true));
			
			throw e;
		}
	}
	
	public void toggleMarkup(int row, int col) {
		fields.stream()
			.filter(field -> field.getRow_X() == row &&
			     	         field.getCol_Y() == col)
			.findFirst()
			.ifPresent(field -> field.toggleMarkup());
	}

	private void generateFields() {
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < rows; col++)
				fields.add(new Field(row, col));
	}

	private void associateNeighbors() {
		for (Field field_1 : fields)
			for (Field field_2 : fields)
				field_1.addNeighbor(field_2);
	}
	
	private void mineFields() {
		long minedFields = 0;
		
		while (minedFields < mines) {
			int rnd = (int) (random() * fields.size());
			fields.get(rnd).mine();
			minedFields = fields.stream().filter(field -> field.isMined()).count();
		}
	}

	public boolean goalAchieved() {
		return fields.stream().allMatch(field -> field.goalAchieved());
	}
	
	public void restartGame() {
		fields.stream().forEach(field -> field.reiniciar());
		mineFields();
	}
	
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getMines() {
		return mines;
	}
	
	public List<Field> getFields() {
		return unmodifiableList(fields);
	}

	public int getFieldsCount() {
		return fields.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		
		stringbuilder.append("   ");
		
		for (int col_index = 0; col_index < cols; col_index++)
			stringbuilder.append(col_index + "  ");
		
		stringbuilder.append("\n");
		
		int fieldsIndex = 0;

		for (int row = 0; row < rows; row++) {
			stringbuilder.append(row + " ");
			
			for (int col = 0; col < cols; col++) {
				stringbuilder.append(" " + fields.get(fieldsIndex) + " ");
				fieldsIndex++;
			}
			stringbuilder.append("\n");
		}
		return stringbuilder.toString();
	}
}
