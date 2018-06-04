import sun.applet.Main;

import javax.swing.*;

public class Grid implements Runnable{

	private Cell[][] cells;
	public static int rows;
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
	
	///////////////////////////
	//Début Méthode à coder  //
	///////////////////////////


	private void initNeighbors(int rows) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				Cell c = cells[i][j];
				for (int k = i-1; k < i+2 && k < rows && k > 0; k++) {
					for (int l = j-1; l < j+2 && l > 0 && l < rows; l++) {
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
							cells[i][j].display();
						}
					}
					setNbIterations(getNbIterations()+1);
					System.out.println("nb d'iterations : "+getNbIterations());
					System.out.println("nbIterationsMax : "+getNbIterationsMax());

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
			setNbIterationsMax((IterationMaxAtteint*intNbIterationsMax)+intNbIterationsMax);
		}
		else {
			setNbIterationsMax(999);
		}
		running = true;
	}

}
