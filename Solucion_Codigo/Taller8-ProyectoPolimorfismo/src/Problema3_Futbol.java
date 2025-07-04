import java.util.ArrayList;
public class Problema3_Futbol {
    public static void main(String[] args) {
        ArrayList<Jugador> equipo = new ArrayList<>();
        equipo.add(new Atacante("Tiago pzk", 9, "12.345.678-9", 2, 15, 4));
        equipo.add(new Defensor("Lit Killah", 4, "11.222.333-4", 0, 10, 6));
        equipo.add(new Portero("C.r.o", 1, "10.111.222-3", 0, 7));

        for (Jugador jugador : equipo) {
            System.out.println("======================");
            jugador.mostrarInfo();
        }
    }
}

abstract class Jugador {
    public String nombre;
    public int dorsal;
    public String rut;
    public int goles;

    public Jugador(String nombre, int dorsal, String rut, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = goles;
    }

    public abstract int calcularValoracion();
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Dorsal: " + dorsal);
        System.out.println("RUT: " + rut);
        System.out.println("Goles: " + goles);
        System.out.println("Valoracion: " + calcularValoracion());
    }
}

class Atacante extends Jugador {
    public int pasesExitosos;
    public int recuperaciones;

    public Atacante(String nombre, int dorsal, String rut, int goles, int pasesExitosos, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pasesExitosos = pasesExitosos;
        this.recuperaciones = recuperaciones;
    }
    public int calcularValoracion() {
        return goles * 30 + recuperaciones * 3;
    }
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Pases exitosos: " + pasesExitosos);
        System.out.println("Recuperaciones: " + recuperaciones);
    }
}

class Defensor extends Jugador {
    public int pasesExitosos;
    public int recuperaciones;
    
    public Defensor(String nombre, int dorsal, String rut, int goles, int pasesExitosos, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.pasesExitosos = pasesExitosos;
        this.recuperaciones = recuperaciones;
    }
    public int calcularValoracion() {
        return goles * 30 + recuperaciones * 4;
    }
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Pases exitosos: " + pasesExitosos);
        System.out.println("Recuperaciones: " + recuperaciones);
    }
}
class Portero extends Jugador {
    public int atajadas;
    
    public Portero(String nombre, int dorsal, String rut, int goles, int atajadas) {
        super(nombre, dorsal, rut, goles);
        this.atajadas = atajadas;
    }
    public int calcularValoracion() {
        return goles * 30 + atajadas * 5;
    }
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Atajadas: " + atajadas);
    }
}