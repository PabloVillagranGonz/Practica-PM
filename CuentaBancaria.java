public abstract class CuentaBancaria {
    private Persona titular;
    private String iban;
    private double saldo;

    public CuentaBancaria(Persona titular, String iban, double saldo) {
        this.titular = titular;
        this.iban = iban;
        this.saldo = saldo;
    }

    public Persona getTitular() { return titular; }
    public String getIban() { return iban; }
    public double getSaldo() { return saldo; }

    public void ingresar(double cantidad) {
        saldo += cantidad;
    }

    public boolean retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    public abstract String devolverInfoString();
}
