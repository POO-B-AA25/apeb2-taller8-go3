import java.util.ArrayList;

abstract class Menu {
    public  String nombrePlato;
    public double valorInicial;
    public double valorMenu;

    public Menu(String nombrePlato, double valorMenu, double valorInicial) {
        this.nombrePlato = nombrePlato;
        this.valorMenu = valorMenu;
        this.valorInicial = valorInicial;
    }

    public abstract double calcularTotal();

    public String toString() {
        return "Plato: " + nombrePlato + ", Valor Inicial: " + valorInicial + ", Valor Menu: " + valorMenu;
    }
}

class MenuCarta extends Menu {
    public double valorPorcionGuarnicion;
    public double valorBebida;
    public double porcentajeServicio;

    public MenuCarta(String nombrePlato, double valorMenu, double valorInicial, double valorPorcionGuarnicion, double valorBebida, double porcentajeServicio) {
        super(nombrePlato, valorMenu, valorInicial);
        this.valorPorcionGuarnicion = valorPorcionGuarnicion;
        this.valorBebida = valorBebida;
        this.porcentajeServicio = porcentajeServicio;
    }

    public double calcularTotal() {
        return valorInicial + valorPorcionGuarnicion + valorBebida + (valorMenu * porcentajeServicio / 100);
    }

    public String toString() {
        return super.toString() + ", Guarnicion: " + valorPorcionGuarnicion + ", Bebida: " + valorBebida + ", Servicio: " + porcentajeServicio + "%";
    }
}
class MenuDia extends Menu {
    public double valorPostre;
    public double valorBebida;

    public MenuDia(String nombrePlato, double valorMenu, double valorInicial, double valorPostre, double valorBebida) {
        super(nombrePlato, valorMenu, valorInicial);
        this.valorPostre = valorPostre;
        this.valorBebida = valorBebida;
    }

    public double calcularTotal() {
        return valorInicial + valorPostre + valorBebida;
    }

    public String toString() {
        return super.toString() + ", Postre: " + valorPostre + ", Bebida: " + valorBebida;
    }
}
 class MenuNinos extends Menu {
    public double valorHelado;
    public double valorPastel;

    public MenuNinos(String nombrePlato, double valorMenu, double valorInicial, double valorHelado, double valorPastel) {
        super(nombrePlato, valorMenu, valorInicial);
        this.valorHelado = valorHelado;
        this.valorPastel = valorPastel;
    }

    public double calcularTotal() {
        return valorInicial + valorHelado + valorPastel;
    }

    public String toString() {
        return super.toString() + ", Helado: " + valorHelado + ", Pastel: " + valorPastel;
    }
}

class MenuEconomico extends Menu {
    public double porcentajeDescuento;

    public MenuEconomico(String nombrePlato, double valorMenu, double valorInicial, double porcentajeDescuento) {
        super(nombrePlato, valorMenu, valorInicial);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double calcularTotal() {
        return valorInicial - (valorMenu * porcentajeDescuento / 100);
    }

    public String toString() {
        return super.toString() + ", Descuento: " + porcentajeDescuento + "%";
    }
}

class Cuenta {
    public String nombreCliente;
    public ArrayList<Menu> menu;
    public double subtotal;
    public double iva;
    public double total;

    public Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.menu = new ArrayList<>();
        this.subtotal = 0;
        this.iva = 0;
        this.total = 0;
    }

    public void agregarMenu(Menu menu) {
        this.menu.add(menu);
    }

    public double calcularSubtotal() {
        subtotal = 0;
        for (Menu m : menu) {
            subtotal += m.calcularTotal();
        }
        return subtotal;
    }

    public void calcularTotal() {
        subtotal = calcularSubtotal();
        iva = subtotal * 0.12;
        total = subtotal + iva;
    }

    public String toString() {
        String info = "Cliente: " + nombreCliente + "\n";
        for (Menu m : menu) {
            info += m.toString() + "\n";
        }
        info += "Subtotal: " + subtotal + ", IVA: " + iva + ", Total: " + total;
        return info;
    }
}

public class Problema2_Restaurant {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta("Rogger Carrion ");

        MenuCarta carta = new MenuCarta("Arroz con camarones", 10, 5, 1, 1.5, 10);
        MenuDia menuDia = new MenuDia("Pollo asado", 8, 4, 2, 1);
        MenuNinos menuNinos = new MenuNinos("Hamburguesa", 6, 3, 1, 1);
        MenuEconomico menuEco = new MenuEconomico("Ensalada", 7, 4, 15);

        cuenta.agregarMenu(carta);
        cuenta.agregarMenu(menuDia);
        cuenta.agregarMenu(menuNinos);
        cuenta.agregarMenu(menuEco);

        cuenta.calcularTotal();

        System.out.println(cuenta);
    }
}