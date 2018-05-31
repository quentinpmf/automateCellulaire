public class Grid implements Runnable{

	private Cell[][] cells;
	private boolean running;
	private int time;

	public Grid(Cell[][] cells) {
		this.cells = cells;
		initNeighboors();
	}
	
	public void setSpeed(int time) {
		this.time = time;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	///////////////////////////
	//Début Méthode à coder  //
	///////////////////////////


	private void initNeighboors() {
		for (int i = 0; i < MainGUI.CELL_ROWS; i++) {
			for (int j = 0; j < MainGUI.CELL_COLS; j++) {
				Cell c = cells[i][j];
				for (int k = i-1; k < i+2 && k < MainGUI.CELL_ROWS && k > 0; k++) {
					for (int l = j-1; l < j+2 && l > 0 && l < MainGUI.CELL_COLS; l++) {
						if ((k != i || l != j) ) {
							c.addNeighboor(cells[k][l]);
						}
					}
				}

			}
		}
	}
	
	///////////////////////////
	//Fin Méthode à coder    //
	///////////////////////////

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
				
				for (int i = 0; i < MainGUI.CELL_ROWS; i++) {
					for (int j = 0; j < MainGUI.CELL_COLS; j++) {
						cells[i][j].nextState();
					}
				}
				
				for (int i = 0; i < MainGUI.CELL_ROWS; i++) {
					for (int j = 0; j < MainGUI.CELL_COLS; j++) {
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
