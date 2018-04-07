public class Viaje {

    private Drone drone;
    private Estacion puntoA;
    private Estacion puntoB;

    public Viaje(Drone drone, Estacion puntoA, Estacion puntoB) {
        this.drone = drone;
        this.puntoA = puntoA;
        this.puntoB = puntoB;
    }

    public Drone getDrone() {
        return drone;
    }

    public Estacion getPuntoA() {
        return puntoA;
    }

    public Estacion getPuntoB() {
        return puntoB;
    }
}
