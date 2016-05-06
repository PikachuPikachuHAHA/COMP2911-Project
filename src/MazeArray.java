import java.util.ArrayList;

/**
 * Maze storages, holds all the cells and provides functions for maze generation
 * @author ruby
 *
 */
public class MazeArray {
	private ArrayList<ArrayList<Cell>> cells;
	private int size;
	/**
	 * Generates empty maze grid based on size provided
	 * cells are all disconnected
	 * @param size Width and height of maze
	 */
	public MazeArray(int size){
		this.size = size;
		this.cells = new ArrayList<ArrayList<Cell>>();
		for (int row = 0; row < size; row++){
			ArrayList<Cell> temp = new ArrayList<Cell>();
			for (int col = 0; col < size; col++){
				temp.add(new Cell(row, col));
			}
			this.cells.add(temp);
		}
	}
	/**
	 * Returns cell object at specific location
	 * @param row row number of cell (0 index)
	 * @param col column number of cell (0 index)
	 * @return Cell object at that location, null if invalid
	 */
	public Cell getCell(int row, int col){
		if ((row < 0 || row >= size)||(col < 0 || col >= size)){
			return null;
		}
		return this.cells.get(row).get(col);
	}
	
	/**
	 * Gets all nighbours of cell that has not been visited by maze gen algorithm
	 * @param cell
	 * @return
	 */
	public ArrayList<Cell> getAllUnvisitedNeighbours(Cell cell){
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		int row = cell.getRow();
		int col = cell.getColumn();
		//add north cell
		if (row > 0){
			if (!getCell(row-1, col).hasBeenVisited()){
				neighbours.add(getCell(row-1, col));
			}
		}
		//add south cell
		if (row < this.size-1){
			if (!getCell(row+1, col).hasBeenVisited()){
				neighbours.add(getCell(row+1, col));
			}
		}
		//add west cell
		if (col > 0){
			if (!getCell(row, col-1).hasBeenVisited()){
				neighbours.add(getCell(row, col-1));
			}
		}
		//add east cell
		if (col < this.size-1){
			if (!getCell(row, col+1).hasBeenVisited()){
				neighbours.add(getCell(row, col+1));
			}
		}
		
		return neighbours;
	}
	
	/**
	 * Create path between 2 adjacent cells
	 * @param cell1 Cell object 1
	 * @param cell2 Cell object 2
	 */
	public void carvePath(Cell cell1, Cell cell2){
		// if they are in the same row
		if (cell1.getRow()==cell2.getRow()){
			// if cell 1 is to the right of cell 2
			if (cell1.getColumn()> cell2.getColumn()){
				cell1.setWest(cell2);
				cell2.setEast(cell1);
			}
			// if cell 1 is to the left of cell 2
			else{
				cell1.setEast(cell2);
				cell2.setWest(cell1);
			}
		}
		else {
			// if cell 1 is below cell 2
			if (cell1.getRow() > cell2.getRow()){
				cell1.setNorth(cell2);
				cell2.setSouth(cell1);
			}
			else{
				cell1.setSouth(cell2);
				cell2.setNorth(cell1);
			}
		}
	}
	
	public void printMaze(){
		//print the top wall
		for (int i = 0; i < this.size; i++){
			System.out.print("**");
		}
		System.out.println("*");
		//for every line
		for (int row = 0; row < this.size; row++){
			//print the first wall
			String curLine = "*";
			String botLine = "*";
			//print each thingy
			for (int col = 0; col < this.size; col++){
				curLine += " ";
				if (getCell(row, col).getEast() == null){
					curLine += "*";
				}
				else {
					curLine += " ";
				}
				if (getCell(row, col).getSouth() == null){
					botLine += '*';
				}
				else {
					botLine += " ";
				}
				botLine += "*";
			}
			System.out.println(curLine);
			System.out.println(botLine);
		}
	}
}
