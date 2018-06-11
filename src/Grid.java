public class Grid implements Runnable{

	private Cell[][] cells;
	public int rows;
	private boolean running;
	private int time;
	public static int nbIterations = 0;
	public static int nbIterationsMax = 0;
	public static int IterationMaxAtteint = 0;

	//pour la limite max d'itérations
	public static boolean boolRun = true;

	public Grid(Cell[][] cells, int rows) {
		this.cells = cells;
		this.rows = rows;
		initNeighbors(rows);
	}

	public int getNbIterations() { return nbIterations; }

	public void setNbIterations(int nb) {
		nbIterations = nb;
		MainGUI.showIterationsInLabel(String.valueOf(nb));
	}

	public int getNbIterationsMax() { return nbIterationsMax; }

	public void setNbIterationsMax(int nb) { nbIterationsMax = nb; }

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
				for (int k = i-1; k < i+2 && k < rows ; k++) {
					if((k < 0 || k > rows)){
						continue;
					}
					for (int l = j-1; l < j+2 && l < rows; l++) {
						if((l < 0 || l > rows)){
							continue;
						}
						if ((k != i || l != j) ) {
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
				if(nbIterations >= nbIterationsMax)
				{
					System.out.println("ici");
					running = false;
					IterationMaxAtteint = IterationMaxAtteint+1;
					setNbIterationsMax(getNbIterationsMax()*2);
					MainGUI.setBtnStart("Stop");
				}
				else
				{
                    for (int i = 0; i < this.rows; i++) {
                        for (int j = 0; j < this.rows; j++) {
                            cells[i][j].nextState();
                        }
                    }
                    for (int i = 0; i < this.rows; i++) {
                        for (int j = 0; j < this.rows; j++) {
                            cells[i][j].display(cells[i][j].getNextState());
                            cells[i][j].applyNextState();
                        }
                    }
					setNbIterations(getNbIterations()+1);
				}
			}
		}
	}

	public void pause() {
		running = false;
	}

	public void resume(String nbIterationsMax) {
		int intNbIterationsMax = Integer.parseInt(nbIterationsMax);
		if(intNbIterationsMax > 0) {
			setNbIterationsMax(getNbIterations()+intNbIterationsMax);
		}
		else {
			setNbIterationsMax(5000);
		}
		running = true;
	}

}