public class CuentaCorrienteEmpresa extends CuentaBancaria {
    private double maximoDescubierto;
    private double interesDescubierto;
    private double comisionDescubierto;

    public CuentaCorrienteEmpresa(Persona titular, String iban, double saldo, double maximoDescubierto, double interesDescubierto, double comisionDescubierto) {
        super(titular, iban, saldo);
        this.maximoDescubierto = maximoDescubierto;
        this.interesDescubierto = interesDescubierto;
        this.comisionDescubierto = comisionDescubierto;
    }

    @Override
    public String devolverInfoString() {
        return "Cuenta Corriente Empresa - IBAN: " + getIban() + ", Titular: " + getTitular() + ", Saldo: " + getSaldo() + ", Máximo descubierto: " + maximoDescubierto + ", Interés por descubierto: " + interesDescubierto + "%, Comisión por descubierto: " + comisionDescubierto;
    }
}
