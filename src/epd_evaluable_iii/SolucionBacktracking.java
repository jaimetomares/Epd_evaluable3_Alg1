package epd_evaluable_iii;

public class SolucionBacktracking {
int camino[];
double matrizdist[][];
double smejor;
public int pasosR=0;
public int ciudadini;

//La ciudad inicial es un numero de 1 a N ciudades pero internamente las ciudades van de 0 a N-1
public SolucionBacktracking(double matrizdist[][], int ciudadini) {
	this.ciudadini=ciudadini;
	camino = new int [matrizdist.length]; 
	camino[0]=ciudadini-1;
	this.matrizdist = matrizdist;
}

public double getSolucion() {
	Cities c = new Cities();
	Cities cities[][] = c.sortCitiesByDistance(matrizdist);
	int caminocpy[] = this.camino;
	smejor= caminoDeDistMinima(cities,caminocpy,caminocpy[0],1,0,0);
	return smejor;
}


public double caminoDeDistMinima(Cities cities[][],int camino[],int ciudadAct,int visitas,double Smejor, double Sparcial) {
	Cities opcion[] = cities[ciudadAct];//vector con distancias ordenadas
	pasosR++;
	int opt=1;
   if(pasosR%1000000==0)System.out.println("						current recursion steps: "+pasosR);
	while(opt<=opcion.length-1 && pasosR<10000000) {
		camino[visitas]=opcion[opt].city;
		Sparcial+=opcion[opt].distance;
		/*Una opción de camino es válida si:
		 * - La solución parcial generada por dicho camino no empeora la actual mejor solución
		 * - La ciudad elegida no está en el camino actual
		 * */ 
		//Si por lo pronto una de las opciones tomadas sea o no válida empeora el camino, se dejará de probar el resto de opciones
		if(!esSolParcial(Smejor,Sparcial)) {
			camino[visitas]=0;
		    Sparcial-=opcion[opt].distance;
			break;
		}
		/*Si la opción es válida, se determina la completitud del camino
		*	- Si es un camino completo y válido, se actualiza la solución óptima
		*	- Si el camino aún no esta completo, llamaremos de nuevo a la función
		*/
		if(opcionValida(opcion[opt],Smejor,Sparcial,camino)) {
					visitas++;
					if(visitas==camino.length) {
						Smejor = Sparcial+cities[opcion[opt].city][camino[0]].distance;
						System.out.println("Nueva distancia mejorada: "+Smejor);
						pasosR=0;
					}else {
					Smejor = caminoDeDistMinima(cities,camino,opcion[opt].city,visitas,Smejor,Sparcial);
					}
			     	visitas--;
		}
		//si no es válida deshacer camino y escoger otra
		camino[visitas]=0;
		Sparcial-=opcion[opt].distance;
		opt++;
	}
	return Smejor;
}
//Una Opcion es válida si la ciudad no se ha visitado y si no empeora la solución óptima
private boolean opcionValida(Cities cities, double smejor, double sparcial, int[] camino) {
	boolean b = true;
	if(visitado(camino,cities.city) || !esSolParcial(smejor,sparcial))
		b = false;
return b;
}
//Es una solución parcial si la solución mejor inicial es 0 o si la solución parcial mejora la solución óptima
private boolean esSolParcial(double smejor, double sparcial) {
	return smejor==0 || sparcial<smejor;
}

//True en caso de que una ciudad ya esté en el camino emprendido, false si no está
private boolean visitado(int[] camino, int city) {
	boolean b=false;
	int c=0;
	for(int i=0;i<camino.length;i++) {
		if(camino[i]==city)
			c++;
		if(c>1) {
			b=true;
			break;
		}
	}
	return b;
}


}
