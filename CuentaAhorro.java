public class CuentaAhorro extends CuentaBancaria {
    private double interesAnual;

    public CuentaAhorro(Persona titular, String iban, double saldo, double interesAnual) {
        super(titular, iban, saldo);
        this.interesAnual = interesAnual;
    }

    @Override
    public String devolverInfoString() {
        return "Cuenta de Ahorro - IBAN: " + getIban() + ", Titular: " + getTitular() + ", Saldo: " + getSaldo() + ", Interés anual: " + interesAnual + "%";
    }
}
