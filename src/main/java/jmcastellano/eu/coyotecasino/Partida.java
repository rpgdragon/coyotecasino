package jmcastellano.eu.coyotecasino;

import java.util.Random;

public class Partida extends Thread {
	private final int numpartida;
	private int rondafinal;
	private int rondasjugadas;
	private int numcoyotes;
	private final boolean gastarGemas;
	private int gemasgastadas;
	private final Random cartaelegida;
	private final Random cartamala;
	
	public Partida(int numpartida, boolean gastarGemas) {
		this.numpartida = numpartida;
		this.gastarGemas = gastarGemas;
		this.rondafinal = 1;
		this.numcoyotes = 0;
		cartaelegida = new Random();
		cartamala = new Random();
	}
	
	@Override
	public void run() {
		while(this.rondafinal<40) {
			if(this.rondafinal==1 || this.rondafinal%5==0) {
				//si es la primera ronda o es multiple de 5 no cuenta, solo añadimos una ronda más al final
				this.rondafinal++;
				continue;
			}
			this.rondasjugadas++;
			int mala = cartamala.nextInt(4);
			int elegida = cartaelegida.nextInt(4);
			if(mala == elegida) {
				//ohhh Coyote :(
				this.numcoyotes++;
				//comprobamos si es fin de partida o seguimos
				if(this.gastarGemas) {
					int costegemas = dimeGemasActuales(this.numcoyotes);
					this.gemasgastadas+=costegemas;
					this.rondafinal++;
				}
				else {
					//no se quiere gastar gemas, terminamos el bucle
					break;
				}
			}
			else {
				//carta correcta, pasams a la siguiente ronda
				this.rondafinal++;
			}
		}
		
	}

	public int getNumpartida() {
		return numpartida;
	}

	public int getRondafinal() {
		return rondafinal;
	}

	public void setRondafinal(int rondafinal) {
		this.rondafinal = rondafinal;
	}

	public int getNumcoyotes() {
		return numcoyotes;
	}

	public void setNumcoyotes(int numcoyotes) {
		this.numcoyotes = numcoyotes;
	}

	public boolean isGastarGemas() {
		return gastarGemas;
	}
	
	public int getGemasgastadas() {
		return gemasgastadas;
	}

	public void setGemasgastadas(int gemasgastadas) {
		this.gemasgastadas = gemasgastadas;
	}

	public float getRadioRondasCoyote() {
		if(this.numcoyotes==0) {
			//aqui devolvemos 40.0, la razón es que sino este numero daría infinito y romperia los calclos
			return 40.0f;
		}
		return (float)this.rondafinal/this.numcoyotes;
	}
	
	public float getPorcentajeCoyote() {
		return (float)this.numcoyotes/this.rondasjugadas;
	}
	
	private int dimeGemasActuales(int numCoyote) {
		if(numCoyote==1) {
			return 50;
		}
		return dimeGemasActuales(numCoyote-1)*2;
	}

	@Override
	public String toString() {
		return this.numpartida + " " + this.rondafinal + " " + this.numcoyotes + " " + this.getRadioRondasCoyote() + " " + this.getPorcentajeCoyote() + " " + this.rondasjugadas + " " +  this.gemasgastadas;
	}
	
	
	
	

}
