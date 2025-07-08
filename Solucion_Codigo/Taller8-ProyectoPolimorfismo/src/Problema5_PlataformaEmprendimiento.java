import java.util.ArrayList;

public class Problema5_PlataformaEmprendimiento {
    public static void main(String[] args) {
        EmprendimientoTecnologico tech = new EmprendimientoTecnologico(
            "TecLoja", 
            "Carlos Perez", 
            "carlos@mail.com", 
            "Innovar Loja con tecnologia", 
            "IA avanzada", 
            true
        );
        tech.productos.add("App de comercio local");
        tech.productos.add("Sistema de inventario");
        tech.mentores.add(new Mentor("Ana Torres", "Desarrollo de Software", 5));

        EmprendimientoGastronomico food = new EmprendimientoGastronomico(
            "Sabores Lojanos", 
            "Mariana Rios", 
            "mariana@mail.com", 
            "Difundir gastronomia local", 
            10, 
            "Comida tradicional", 
            true
        );
        food.productos.add("Tamales Lojanos");
        food.productos.add("Repe Lojano");
        
        System.out.println("--- ESTADO INICIAL ---");
        System.out.println(tech);
        System.out.println("--------------------");
        System.out.println(food);
        System.out.println("\n--- ACCIONES ---");

        tech.participarEnFeria("Feria de Innovacion 2025");
        food.participarEnFeria("Feria Cultural Lojana");

        tech.evolucionar();
        food.evolucionar();

        System.out.println("\n--- ESTADO FINAL ---");
        System.out.println(tech);
        System.out.println("--------------------");
        System.out.println(food);
    }
}

abstract class Emprendimiento {
    public String nombre;
    public String propietario;
    public String contacto;
    public String mision;
    public ArrayList<String> productos = new ArrayList<>();
    public ArrayList<Mentor> mentores = new ArrayList<>();

    public Emprendimiento(String nombre, String propietario, String contacto, String mision) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.contacto = contacto;
        this.mision = mision;
    }

    public abstract void participarEnFeria(String tipoFeria);
    public abstract void evolucionar();
    public abstract boolean requiereMentor();

    public String toString() {
        return "Emprendimiento: " + nombre + "\nPropietario: " + propietario + " | Contacto: " + contacto +
               "\nMision: " + mision +
               "\nProductos: " + productos +
               "\nMentores: " + mentores;
    }
}

class Mentor {
    public String nombre;
    public String especialidad;
    public int experiencia;

    public Mentor(String nombre, String especialidad, int experiencia) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.experiencia = experiencia;
    }

    public void brindarAsesoria() {
        System.out.println(nombre + " brinda asesoria en " + especialidad);
    }

    public String toString() {
        return nombre + " - " + especialidad + " (" + experiencia + " anios)";
    }
}

class EmprendimientoTecnologico extends Emprendimiento {
    public String tecnologia;
    public boolean innovacion;

    public EmprendimientoTecnologico(String nombre, String propietario, String contacto, String mision, String tecnologia, boolean innovacion) {
        super(nombre, propietario, contacto, mision);
        this.tecnologia = tecnologia;
        this.innovacion = innovacion;
    }

    @Override
    public void participarEnFeria(String tipoFeria) {
        System.out.println(nombre + " participa en feria tecnologica: " + tipoFeria);
    }

    @Override
    public void evolucionar() {
        System.out.println(nombre + " ha evolucionado incorporando nueva tecnologia: " + tecnologia);
        this.desarrolloSoftware();
    }

    public void desarrolloSoftware() {
        this.productos.add("Plataforma web avanzada");
    }

    @Override
    public boolean requiereMentor() {
        return true;
    }

    public String toString() {
        return super.toString() + "\nTipo: Tecnologico | Tecnologia: " + tecnologia + " | Innovacion: " + innovacion;
    }
}

class EmprendimientoArtesanal extends Emprendimiento {
    public String materialPrincipal;
    public String tipoArtesania;

    public EmprendimientoArtesanal(String nombre, String propietario, String contacto, String mision, String materialPrincipal, String tipoArtesania) {
        super(nombre, propietario, contacto, mision);
        this.materialPrincipal = materialPrincipal;
        this.tipoArtesania = tipoArtesania;
    }

    @Override
    public void participarEnFeria(String tipoFeria) {
        System.out.println(nombre + " exhibe " + tipoArtesania + " en feria de arte: " + tipoFeria);
    }

    @Override
    public void evolucionar() {
        this.productos.add("Linea premium artesanal");
    }

    @Override
    public boolean requiereMentor() {
        return false;
    }

    public String toString() {
        return super.toString() + "\nTipo: Artesanal | Material: " + materialPrincipal + " | Tipo: " + tipoArtesania;
    }
}

class EmprendimientoAgricola extends Emprendimiento {
    public String cultivo;
    public boolean esOrganico;

    public EmprendimientoAgricola(String nombre, String propietario, String contacto, String mision, String cultivo, boolean esOrganico) {
        super(nombre, propietario, contacto, mision);
        this.cultivo = cultivo;
        this.esOrganico = esOrganico;
    }

    @Override
    public void participarEnFeria(String tipoFeria) {
        System.out.println(nombre + " presenta " + cultivo + " en feria agricola: " + tipoFeria);
    }

    @Override
    public void evolucionar() {
        this.cosechar();
    }

    public void cosechar() {
        this.productos.add("Cosecha de " + cultivo);
    }

    @Override
    public boolean requiereMentor() {
        return true;
    }

    public String toString() {
        return super.toString() + "\nTipo: Agricola | Cultivo: " + cultivo + " | Organico: " + esOrganico;
    }
}

class EmprendimientoGastronomico extends Emprendimiento {
    public int platosMenu;
    public String especialidad;
    public boolean servicioDelivery;

    public EmprendimientoGastronomico(String nombre, String propietario, String contacto, String mision, int platosMenu, String especialidad, boolean servicioDelivery) {
        super(nombre, propietario, contacto, mision);
        this.platosMenu = platosMenu;
        this.especialidad = especialidad;
        this.servicioDelivery = servicioDelivery;
    }

    @Override
    public void participarEnFeria(String tipoFeria) {
        System.out.println(nombre + " ofrece degustacion de " + especialidad + " en feria gastronomica: " + tipoFeria);
    }

    @Override
    public void evolucionar() {
        this.prepararMenu();
    }

    public void prepararMenu() {
        this.productos.add("Nuevo plato: " + this.especialidad + " gourmet");
        this.platosMenu++;
    }

    @Override
    public boolean requiereMentor() {
        return this.platosMenu < 15;
    }

    public String toString() {
        return super.toString() + "\nTipo: Gastronomico | Especialidad: " + especialidad +
               " | Delivery: " + servicioDelivery;
    }
}