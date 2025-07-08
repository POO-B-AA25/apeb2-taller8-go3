import java.util.ArrayList;
import java.util.Random;
public class Problema6_SimuladorConflictos {
    public static void main(String[] args) {
        ArrayList<Nacion> naciones = new ArrayList<>();
        int conflictosSimulados = 0;

        naciones.add(new NacionDesarrollada("USA", 331_000_000, 700_000, 95, true, true));
        naciones.add(new NacionEnDesarrollo("Ecuador", 18_000_000, 40_000, 50, true));
        naciones.add(new NacionDesarrollada("Alemania", 83_000_000, 500_000, 85, true, false));
        naciones.add(new NacionEnDesarrollo("Bolivia", 11_000_000, 30_000, 40, false));

        Random rand = new Random();

        for (int i = 0; i < 3; i++) {
            int idx1 = rand.nextInt(naciones.size());
            int idx2;
            do {
                idx2 = rand.nextInt(naciones.size());
            } while (idx2 == idx1);

            Nacion n1 = naciones.get(idx1);
            Nacion n2 = naciones.get(idx2);

            System.out.println("\n>>> Conflicto entre " + n1.getNombre() + " y " + n2.getNombre());

            n1.declararConflicto();
            n2.declararConflicto();

            double impacto1 = n1.calcularImpacto();
            double impacto2 = n2.calcularImpacto();

            if (impacto1 > impacto2) {
                n1.ganarConflicto(n2);
                n2.perderConflicto();
                System.out.println(n1.getNombre() + " gana el conflicto.");
            } else if (impacto2 > impacto1) {
                n2.ganarConflicto(n1);
                n1.perderConflicto();
                System.out.println(n2.getNombre() + " gana el conflicto.");
            } else {
                n1.empatarConflicto();
                n2.empatarConflicto();
                System.out.println("Empate entre ambas naciones.");
            }

            conflictosSimulados++;
        }

        System.out.println("\n=========== REPORTE FINAL ===========");
        for (Nacion n : naciones) {
            n.reporte();
            System.out.println("-------------------------------------");
        }

        System.out.println("Total de conflictos simulados: " + conflictosSimulados);
    }
}

abstract class Nacion {
    public String nombre;
    public int poblacion;
    public double recursos;
    public int poderMilitar;
    public boolean enConflicto;
    public boolean tieneAliados;

    public Nacion(String nombre, int poblacion, double recursos, int poderMilitar, boolean tieneAliados) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.recursos = recursos;
        this.poderMilitar = poderMilitar;
        this.tieneAliados = tieneAliados;
        this.enConflicto = false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public double getRecursos() {
        return recursos;
    }

    public int getPoderMilitar() {
        return poderMilitar;
    }

    public boolean estaEnConflicto() {
        return enConflicto;
    }

    public void declararConflicto() {
        enConflicto = true;
    }

    public abstract double calcularImpacto();

    public void ganarConflicto(Nacion perdedor) {
        int diferencia = Math.abs(this.poderMilitar - perdedor.poderMilitar);
        int perdidaPoblacion = (int)(perdedor.poblacion * (diferencia * 0.05));
        perdedor.poblacion -= perdidaPoblacion;
        perdedor.recursos -= perdedor.recursos * 0.10;
        perdedor.enConflicto = false;
        this.enConflicto = false;
    }

    public void perderConflicto() {
        enConflicto = false;
    }

    public void empatarConflicto() {
        recursos -= recursos * 0.05;
        enConflicto = false;
    }

    public void reporte() {
        System.out.println("Nacion: " + nombre);
        System.out.println("Poblacion: " + poblacion);
        System.out.println("Recursos: $" + recursos);
        System.out.println("Poder militar: " + poderMilitar);
        System.out.println("En conflicto: " + (enConflicto ? "Si" : "No"));
    }

    protected boolean tieneAliados() {
        return tieneAliados;
    }
}

class NacionDesarrollada extends Nacion {
    public boolean tecnologiaAvanzada;

    public NacionDesarrollada(String nombre, int poblacion, double recursos, int poderMilitar, boolean tecnologiaAvanzada, boolean tieneAliados) {
        super(nombre, poblacion, recursos, poderMilitar, tieneAliados);
        this.tecnologiaAvanzada = tecnologiaAvanzada;
    }

    public double calcularImpacto() {
        double bono = tecnologiaAvanzada ? 1.2 : 1.0;
        double aliado = tieneAliados() ? 1.1 : 1.0;
        double total = getPoderMilitar() * bono * aliado;
        return Math.min(total, 100);
    }
}

class NacionEnDesarrollo extends Nacion {
    public NacionEnDesarrollo(String nombre, int poblacion, double recursos, int poderMilitar, boolean tieneAliados) {
        super(nombre, poblacion, recursos, poderMilitar, tieneAliados);
    }

    public double calcularImpacto() {
        double recursosPorPersona = (getRecursos() + getPoderMilitar()) / (double)getPoblacion();
        double limitador = 1 - Math.min(0.5, recursosPorPersona);
        double aliado = tieneAliados() ? 1.1 : 1.0;
        double total = getPoderMilitar() * limitador * aliado;
        return Math.max(total, 1);
    }
}