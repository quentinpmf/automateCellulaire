public class Grid implements Runnable{

	private Cell[][] cells;
	public static int rows;
	private boolean running;
	private int time;

	public Grid(Cell[][] cells, int rows) {
		this.cells = cells;
		this.rows = rows;
		initNeighbors(rows);
	}
	
	public void setSpeed(int time) {
		this.time = time;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}

	/**
	 * Intialize neighbors of a cell in the grid
	 * @param rows
	 */
	private void initNeighbors(int rows) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				Cell c = cells[i][j];
				//System.out.println(cells[i][j]);

                for (int k = i-1; k < i+2 && k < rows && k > 0; k++) {
                    for (int l = j-1; l < j+2 && l > 0 && l < rows; l++) {
                        System.out.println(cells[i][j]);
                        if ((k != i || l != j) ) {
                            System.out.println(cells[i][j]);
                            System.out.println(cells[k][l]);
                            c.addNeighbor(cells[k][l]);
                        }
                    }
                }

			}
		}
	}

	@Override
	public void run() {
		
		while (true) {
			
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				System.err.println("Thread exception - fatal error");
				System.exit(-1);
			}
			
			if (running) {
				for (int i = 0; i < this.rows; i++) {
					for (int j = 0; j < this.rows; j++) {
						cells[i][j].nextState();
					}
				}
				for (int i = 0; i < this.rows; i++) {
					for (int j = 0; j < this.rows; j++) {
						cells[i][j].display();
					}
				}


			}
		}
	}

	public void pause() {
		running = false;
	}

	public void resume() {
		running = true;
	}

}
