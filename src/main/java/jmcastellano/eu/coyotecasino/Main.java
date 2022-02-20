package jmcastellano.eu.coyotecasino;

import java.util.ArrayList;

public class Main {
		
	//el programa no tendrá muchas clases, creaeremos una clase especifca para tramitar la partida
	public static void main(String[] args) {
		int numpartida = 1;
		int numpartidastotales = 0;
		boolean gastarGemas = false;
		ArrayList<Partida> aprocesar = null;
		ArrayList<Partida> anteriores= null;
		int totalRondas = 0;
		int totalCoyotes = 0;
		float ratiocoyote = 0.0f;
		float porcentajecoyote = 0.0f;
		int totalgemas = 0;
		if(args.length < 2) {
			System.out.println("Deben incluirse dos parametros. El primero de ellos indica el número de partidas. El segundo de ellos indica si debe gastarse gemas para continuar");
			System.exit(-1);
		}
		try {
			numpartidastotales = Integer.parseInt(args[0]);
		}
		catch(NumberFormatException e) {
			System.out.println("El primer parametro debe ser númerico");
			System.exit(-1);
		}
		
		if(!args[1].toLowerCase().equals("true") && !args[1].toLowerCase().equals("false")) {
			System.out.println("El segundo parametro debe valer o true o false");
			System.exit(-1);
		}
		
		if(args[1].toLowerCase().equals("true")) {
			gastarGemas = true;
		}
	
		//para no saturar procesaremos paquetes de hasta 10 partidas y tendremos 2 listas que iremos intercambiando
		System.out.println("NumPartida RondaFinal NumCoyotes RatioRondasCoyote PerCoyote GemasGastadas");
		while(numpartida <= numpartidastotales) {
			aprocesar = new ArrayList<>();
			for(int indice = 0; indice < 10 && numpartida <= numpartidastotales; indice++) {
				Partida p = new Partida(numpartida,gastarGemas);
				aprocesar.add(p);
				p.start();
				numpartida++;
			}
			//una vez que han iniciado todas las hebras nos ponemos a procesar las anteriores si las hubiera
			if(anteriores!=null && anteriores.size()>0) {
				for(int indice = 0; indice < anteriores.size(); indice++) {
					System.out.println(anteriores.get(indice));
					//ahora tenemos que procesar las medias
					totalRondas+=anteriores.get(indice).getRondafinal();
					totalCoyotes+=anteriores.get(indice).getNumcoyotes();
					ratiocoyote+= anteriores.get(indice).getRadioRondasCoyote();
					porcentajecoyote+= anteriores.get(indice).getPorcentajeCoyote();
					totalgemas+= anteriores.get(indice).getGemasgastadas();
				}
			}
			anteriores = aprocesar;
			//tenemos que esperar a que las hebras anteriores hayan terminado
			for(int indice = 0; indice < anteriores.size(); indice++) {
				//esto es arriesgado, pero el programa no deberia quedarse atascado aqui
				if(anteriores.get(indice).isAlive()) {
					try { Thread.sleep(100); } catch(InterruptedException e) {}
				}
			}
			
			aprocesar = null;
		}
		
		//despues del bucle solo quedará la ultima ronda
		if(anteriores!=null && anteriores.size()>0) {
			for(int indice = 0; indice < anteriores.size(); indice++) {
				System.out.println(anteriores.get(indice));
				//ahora tenemos que procesar las medias
				totalRondas+=anteriores.get(indice).getRondafinal();
				totalCoyotes+=anteriores.get(indice).getNumcoyotes();
				ratiocoyote+= anteriores.get(indice).getRadioRondasCoyote();
				porcentajecoyote+= anteriores.get(indice).getPorcentajeCoyote();
				totalgemas+= anteriores.get(indice).getGemasgastadas();
			}
		}
		
		//ok hora de imprimir los resultados finales
		System.out.println("TotalPartidas:" + numpartidastotales);
		System.out.println("TotalRondas:" + totalRondas);
		System.out.println("NumCoyotes:" + totalCoyotes);
		double ratioscoyote = Math.round((ratiocoyote/numpartidastotales) * 100.0) / 100.0;
		System.out.println("RatioRondasCoyote:" + ratioscoyote );
		double percoyote =  Math.round((porcentajecoyote/numpartidastotales) * 100.0) / 100.0;
		System.out.println("PorcentajeCoyote:" + (percoyote * 100) );
		System.out.println("Gemas Gastadas:" + totalgemas);
	}

}
