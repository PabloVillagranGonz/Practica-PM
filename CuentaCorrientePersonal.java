public class CuentaCorrientePersonal extends CuentaBancaria {
    private double comisionMantenimiento;

    public CuentaCorrientePersonal(Persona titular, String iban, double saldo, double comisionMantenimiento) {
        super(titular, iban, saldo);
        this.comisionMantenimiento = comisionMantenimiento;
    }

    @Override
    public String devolverInfoString() {
        return "Cuenta Corriente Personal - IBAN: " + getIban() + ", Titular: " + getTitular() + ", Saldo: " + getSaldo() + ", Comisión de mantenimiento: " + comisionMantenimiento;
    }
}
