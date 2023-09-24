package epd_evaluable_iii;

import java.io.*;
/*Authors
 * - Jaime Baquerizo Delgado
 * - Víctor Jesús Reina López
 */
public class EPD_evaluable_III {

	public static void main(String[] args) {
		
		File data = new File("C:\\Users\\Jaime\\OneDrive\\Escritorio\\data\\");
		String archivos[] = data.list();
		LecturaDeDatos lec = new LecturaDeDatos();
                long start, end, tiempo;
	
		for(int i=0;i<archivos.length;i++) {
		System.out.println("------------------ARCHIVO: "+archivos[i]+" ------------------------------");
		double [][] matrizdist = lec.obtenerMatrizDistancias("C:\\Users\\Jaime\\OneDrive\\Escritorio\\data\\"+ archivos[i]); 
		SolucionBacktracking s = new SolucionBacktracking(matrizdist,1); // Distancia del camino desde la ciudad 1
                start = System.nanoTime();
		double res=s.getSolucion();
                end = System.nanoTime();
                tiempo= (start-end)/10^-9;
                System.out.println("El tiempo en calcular la distancia mediante backtracking del archivo:"+ archivos[i]+"es  " + tiempo);
		System.out.println("\nTras haber recurrido a la función "+s.pasosR+" veces sin obtener mejora\nse decidió parar la ejecución. Resultado obtenido: "+res+"\n");
		}
		//Nótese que en los archivos de más de 1000 ciudades la ejecución tardará más debido al aumento en el numero de opciones
		//Mientras más ciudades, el proceso es más costoso
	}

}
