package ie.gmit.sw.ai;

import java.util.Comparator;

import ie.gmit.sw.maze.Node;

public class EnemyHeuristicCellComparator implements Comparator<Node> {
	private static int targetRow = 0;
	private static int targetColumn = 0;
	
	@Override
	public int compare(Node cell1, Node cell2) {
		if(cell1.getCell().getDistanceToCell(targetRow, targetColumn) > cell2.getCell().getDistanceToCell(targetRow, targetColumn)){
			return -1;
		}else if(cell1.getCell().getDistanceToCell(targetRow, targetColumn) < cell2.getCell().getDistanceToCell(targetRow, targetColumn)){
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
