import java.util.Random;

public class Problema1_JuegoRoles {
    public static Personaje guerrero;
    public static Personaje mago;
    public static Personaje arquero;

    public static void main(String[] args) {
        guerrero = new Guerrero("Gigante", 3);
        mago = new Mago("Invisibilidad", 3);
        arquero = new Arquero("Arco más grande del mundo", 3);

        System.out.println("EMPEZANDO EL JUEGO");

        while (guerrero.vidas > 0 && mago.vidas > 0) {
            guerrero.ataque(mago);
            if (mago.vidas > 0) {
                mago.ataque(guerrero);
            }
            System.out.println(guerrero);
            System.out.println(mago);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("FIN DEL JUEGO");
        if (guerrero.vidas > 0) {
            System.out.println("El Guerrero gano!");
        } else if (mago.vidas > 0) {
            System.out.println("El Mago gano!");
        } else {
            System.out.println("Empate!");
        }
    }
}
abstract class Personaje {
    public int vidas, experiencia, batallasGanadas;

    public Personaje(int vidas) {
        this.vidas = vidas;
    }

    public abstract boolean ataque(Personaje personaje);
    public abstract int defensa();

    public String toString() {
        return "Personaje{" + "vidas=" + vidas + ", experiencia=" + experiencia + ", batallasGanadas=" + batallasGanadas + '}';
    }
}

class Guerrero extends Personaje {
    public String habilidades;

    public Guerrero(String habilidades, int vidas) {
        super(vidas);
        this.habilidades = habilidades;
    }

    public boolean ataque(Personaje personaje) {
        int numero = (int) (Math.random() * 2); 
        boolean gana = (numero == 1);
        if (gana) {
            int daño = 1;
            int defensa = personaje.defensa();
            if (defensa < daño) {
                personaje.vidas -= 1;
            }
            this.experiencia++;
            this.batallasGanadas++;
            System.out.println("Guerrero golpea con fuerza");
        } else {
            this.vidas--;
            personaje.experiencia++;
            personaje.batallasGanadas++;
            System.out.println("Guerrero falla y recibe daño");
        }
        return gana;
    }

    public int defensa() {
        return 0; 
    }

    public String toString() {
        return "Guerrero{" + "habilidades=" + habilidades + "}   " + super.toString();
    }
}

class Mago extends Personaje {
    public String estrategias;

    public Mago(String estrategias, int vidas) {
        super(vidas);
        this.estrategias = estrategias;
    }

    public boolean ataque(Personaje personaje) {
        Random r = new Random();
        int exito = r.nextInt(100);
        boolean gana = exito < 60;
        if (gana) {
            int daño = 1;
            int defensa = personaje.defensa();
            if (defensa < daño) {
                personaje.vidas -= 1;
            }
            this.experiencia++;
            this.batallasGanadas++;
            System.out.println("Mago lanza hechizo ");
        } else {
            this.vidas--;
            personaje.experiencia++;
            personaje.batallasGanadas++;
            System.out.println("Mago falla el hechizo y recibe danio");
        }
        return gana;
    }

    public int defensa() {
        Random r = new Random();
        int defensa = r.nextInt(2); 
        System.out.println("Mago defiende con magia: " + defensa);
        return defensa;
    }

    public String toString() {
        return "Mago{" + "estrategias=" + estrategias + "}   " + super.toString();
    }
}

class Arquero extends Personaje {
    public String atributos;

    public Arquero(String atributos, int vidas) {
        super(vidas);
        this.atributos = atributos;
    }

    public boolean ataque(Personaje personaje) {
        Random r = new Random();
        int exito = r.nextInt(100);
        boolean gana = exito < 70; 
        if (gana) {
            int daño = 1;
            int defensa = personaje.defensa();
            if (defensa < daño) {
                personaje.vidas -= 1;
            }
            this.experiencia++;
            this.batallasGanadas++;
            System.out.println("Arquero acierta su flecha");
        } else {
            this.vidas--;
            personaje.experiencia++;
            personaje.batallasGanadas++;
            System.out.println("Arquero falla el disparo y sufre daño");
        }
        return gana;
    }

    public int defensa() {
        Random r = new Random();
        int defensa = r.nextInt(2);
        System.out.println("Arquero esquiva con defensa: " + defensa);
        return defensa;
    }

    public String toString() {
        return "Arquero{" + "atributos=" + atributos + "}   " + super.toString();
    }
}