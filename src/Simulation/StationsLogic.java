package Simulation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class StationsLogic {
    private Hashtable<Character, Station> stations;

    private Hashtable <String, Hashtable < Station, Hashtable < Character, Integer > > > diffrenceOfTime;
    private Hashtable <String, Hashtable< String, ArrayList<Integer> >> travelingTimePerStation;
    private Hashtable <Character, ArrayList<Trip> > pendingTripsPerStation;

    //Muy pediente de cambios - Óscar a cambio de arreglar el problema que causo Ok :(
    void reservarTiempo(String pRouteId, int pSecondDeparture){
        Hashtable < Station, Hashtable < Character, Integer >  > stationsToUpdate =  this.diffrenceOfTime.get(pRouteId); //Hash con las estaciones

        for (Station conflictiveStation : stationsToUpdate.keySet()){ 				                //Por cada estación que causa conflictos
            Hashtable < Character, Integer > routesInterference = stationsToUpdate.get(conflictiveStation); 	//Hash con las rutas que interfieren con la actual y en cual tiempo lo hacen

            Hashtable <Character, ArrayList <Integer> > departureTimeToUpdate = conflictiveStation.getDepartureTime(); //Tiempo para utilizar
            for (char conflictiveDestiny: routesInterference.keySet()){ //Cada destino de la estacion que se revisa

                List<Integer> departureTimeToAStation = departureTimeToUpdate.get(conflictiveDestiny);   	//Tiempos de salidas que de dicha estacion a dicho destino

                departureTimeToAStation.remove (pSecondDeparture);		//Elimina los tiempos de salida que causarian choques
                departureTimeToUpdate.put(conflictiveDestiny, (ArrayList<Integer>) departureTimeToAStation);
                //Se guarda la version modificada
            }
        }
    }



}
