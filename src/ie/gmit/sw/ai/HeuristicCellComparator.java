package ie.gmit.sw.ai;

import java.util.Comparator;

import ie.gmit.sw.maze.Cell;

public class HeuristicCellComparator implements Comparator<Cell> {
	private static int targetRow = 0;
	private static int targetColumn = 0;
	
	@Override
	public int compare(Cell cell1, Cell cell2) {
		if(cell1.getDistanceToCell(targetRow, targetColumn) > cell2.getDistanceToCell(targetRow, targetColumn)){
			return -1;
		}else if(cell1.getDistanceToCell(targetRow, targetColumn) < cell2.getDistanceToCell(targetRow, targetColumn)){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public static int getTargetRow() {
		return targetRow;
	}
	public static void setTargetRow(int row) {
		targetRow = row;
	}
	public static int getTargetColumn() {
		return targetColumn;
	}
	public static void setTargetColumn(int column) {
		targetColumn = column;
	}

}
