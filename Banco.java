import java.util.ArrayList;

public class Banco {
    private ArrayList<CuentaBancaria> cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public boolean abrirCuenta(CuentaBancaria cuenta) {
        return cuentas.add(cuenta);
    }

    public ArrayList<String> listadoCuentas() {
        ArrayList<String> listado = new ArrayList<>();
        for (CuentaBancaria cuenta : cuentas) {
            listado.add(cuenta.devolverInfoString());
        }
        return listado;
    }

    public CuentaBancaria buscarCuenta(String iban) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getIban().equals(iban)) {
                return cuenta;
            }
        }
        return null;
    }

    public boolean ingresoCuenta(String iban, double cantidad) {
        CuentaBancaria cuenta = buscarCuenta(iban);
        if (cuenta != null) {
            cuenta.ingresar(cantidad);
            return true;
        }
        return false;
    }

    public boolean retiradaCuenta(String iban, double cantidad) {
        CuentaBancaria cuenta = buscarCuenta(iban);
        if (cuenta != null) {
            return cuenta.retirar(cantidad);
        }
        return false;
    }

    public double obtenerSaldo(String iban) {
        CuentaBancaria cuenta = buscarCuenta(iban);
        if (cuenta != null) {
            return cuenta.getSaldo();
        }
        return -1;
    }
}
