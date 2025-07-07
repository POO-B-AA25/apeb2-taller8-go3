import java.util.ArrayList;

public class Problema4_MonitoreoClimatico {
    public static void main(String[] args) {
        ArrayList<Dispositivo> dispositivos = new ArrayList<>();

        dispositivos.add(new DispositivoCosta("D001", "Guayaquil", 34.0, 100.0, 92.0, 4.2));
        dispositivos.add(new DispositivoSierra("D002", "Quito", 7.0, 180.0, 2800.0, 2.5));
        dispositivos.add(new DispositivoOriente("D003", "Puyo", 31.0, 450.0, 91.0, 85.0, 1.8));

        System.out.println("--- REPORTES INDIVIDUALES POR DISPOSITIVO ---");
        for (Dispositivo d : dispositivos) {
            d.procesarDatos();
            d.detectarRiesgos();
            System.out.println(d);
        }

        System.out.println("\n--- REPORTE GENERAL CONSOLIDADO ---");
        ReporteAmbiental reporte = new ReporteAmbiental(dispositivos);
        System.out.println(reporte);
        
        System.out.println("Riesgos detectados en la Sierra: " + reporte.riesgosRegion("Sierra"));
    }
}

abstract class Dispositivo {
    public String id;
    public String ubicacion;
    public double temperatura;
    public double precipitacion;
    public String estado;
    public ArrayList<String> riesgosDetectados = new ArrayList<>();

    public Dispositivo(String id, String ubicacion, double temperatura, double precipitacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.temperatura = temperatura;
        this.precipitacion = precipitacion;
        this.estado = "Normal";
    }

    public abstract void procesarDatos();
    public abstract void detectarRiesgos();

    @Override
    public String toString() {
        return "Dispositivo [id=" + id + ", ubicacion=" + ubicacion + ", estado=" + estado + ", riesgos=" + riesgosDetectados + "]";
    }
}

class DispositivoCosta extends Dispositivo {
    public double humedad;
    public double salinidad;

    public DispositivoCosta(String id, String ubicacion, double temperatura, double precipitacion, double humedad, double salinidad) {
        super(id, ubicacion, temperatura, precipitacion);
        this.humedad = humedad;
        this.salinidad = salinidad;
    }

    @Override
    public void procesarDatos() {
        if (temperatura > 33) {
            estado = "Temperatura Alta";
        } else if (humedad > 90) {
            estado = "Humedad Critica";
        } else {
            estado = "Normal";
        }
    }

    @Override
    public void detectarRiesgos() {
        riesgosDetectados.clear();
        if (temperatura > 33) riesgosDetectados.add("Ola de calor extrema");
        if (precipitacion > 250) riesgosDetectados.add("Inundacion costera");
        if (humedad > 90) riesgosDetectados.add("Humedad critica");
        if (salinidad > 4) riesgosDetectados.add("Salinizacion del suelo");
    }

    @Override
    public String toString() {
        return "Costa -> " + super.toString();
    }
}

class DispositivoSierra extends Dispositivo {
    public double altitud;
    public double estabilidadSuelo;

    public DispositivoSierra(String id, String ubicacion, double temperatura, double precipitacion, double altitud, double estabilidadSuelo) {
        super(id, ubicacion, temperatura, precipitacion);
        this.altitud = altitud;
        this.estabilidadSuelo = estabilidadSuelo;
    }

    @Override
    public void procesarDatos() {
        if (temperatura < 8) {
            estado = "Frio extremo";
        } else if (estabilidadSuelo < 3) {
            estado = "Suelo inestable";
        } else {
            estado = "Normal";
        }
    }

    @Override
    public void detectarRiesgos() {
        riesgosDetectados.clear();
        if (temperatura < 5) riesgosDetectados.add("Riesgo de heladas");
        if (precipitacion > 150 && estabilidadSuelo < 4) riesgosDetectados.add("Riesgo de deslizamiento");
        if (estabilidadSuelo < 2) riesgosDetectados.add("Suelo extremadamente inestable");
    }

    @Override
    public String toString() {
        return "Sierra -> " + super.toString();
    }
}

class DispositivoOriente extends Dispositivo {
    public double humedad;
    public double calidadAire;
    public double biodiversidad;

    public DispositivoOriente(String id, String ubicacion, double temperatura, double precipitacion, double humedad, double calidadAire, double biodiversidad) {
        super(id, ubicacion, temperatura, precipitacion);
        this.humedad = humedad;
        this.calidadAire = calidadAire;
        this.biodiversidad = biodiversidad;
    }

    @Override
    public void procesarDatos() {
        if (calidadAire > 80) {
            estado = "Aire contaminado";
        } else if (biodiversidad < 3) {
            estado = "Biodiversidad baja";
        } else {
            estado = "Normal";
        }
    }

    @Override
    public void detectarRiesgos() {
        riesgosDetectados.clear();
        if (calidadAire > 75) riesgosDetectados.add("Contaminacion del aire");
        if (precipitacion > 400) riesgosDetectados.add("Inundacion amazonica");
        if (biodiversidad < 2) riesgosDetectados.add("Perdida critica de biodiversidad");
    }

    @Override
    public String toString() {
        return "Oriente -> " + super.toString();
    }
}

class ReporteAmbiental {
    public ArrayList<Dispositivo> dispositivos;
    public int totalRiesgos;

    public ReporteAmbiental(ArrayList<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
        this.calcularTotalRiesgos();
    }

    public void calcularTotalRiesgos() {
        totalRiesgos = dispositivos.stream().mapToInt(d -> d.riesgosDetectados.size()).sum();
    }

    public ArrayList<String> riesgosRegion(String region) {
        ArrayList<String> riesgos = new ArrayList<>();
        for (Dispositivo d : dispositivos) {
            if (region.equalsIgnoreCase("Costa") && d instanceof DispositivoCosta && !d.riesgosDetectados.isEmpty()) {
                riesgos.addAll(d.riesgosDetectados);
            } else if (region.equalsIgnoreCase("Sierra") && d instanceof DispositivoSierra && !d.riesgosDetectados.isEmpty()) {
                riesgos.addAll(d.riesgosDetectados);
            } else if (region.equalsIgnoreCase("Oriente") && d instanceof DispositivoOriente && !d.riesgosDetectados.isEmpty()) {
                riesgos.addAll(d.riesgosDetectados);
            }
        }
        return riesgos;
    }

    @Override
    public String toString() {
        return "ReporteAmbiental [dispositivos=" + dispositivos.size() + ", totalRiesgos=" + totalRiesgos + "]";
    }
}