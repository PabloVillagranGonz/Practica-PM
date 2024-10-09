import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void abrirCuenta(Scanner scanner, Banco banco) {
        try {
            String nombre, apellidos, dni, iban;
            double saldoInicial, interesAnual, maxDescubierto, comisionDescubierto,
                    interesDescubierto, comisionMantenimiento;
            int tipoCuenta;

            System.out.println("Introduce el nombre del titular:");
            nombre = scanner.next();
            System.out.println("Introduce los apellidos del titular:");
            apellidos = scanner.next();
            System.out.println("Introduce el DNI del titular:");
            dni = scanner.next();

            Persona titular = new Persona(nombre, apellidos, dni);

            System.out.println("Introduce el IBAN de la nueva cuenta:");
            iban = scanner.next();
            System.out.println("Introduce el saldo inicial:");
            saldoInicial = Double.parseDouble(scanner.next());

            System.out.println("Elige el tipo de cuenta a crear:");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta Corriente Personal");
            System.out.println("3. Cuenta Corriente de Empresa");
            tipoCuenta = Integer.parseInt(scanner.next());

            CuentaBancaria nuevaCuenta = null;

            switch (tipoCuenta) {
                case 1:
                    System.out.println("Introduce el tipo de interés anual:");
                    interesAnual = Double.parseDouble(scanner.next());
                    nuevaCuenta = new CuentaAhorro(titular, iban, saldoInicial, interesAnual);
                    break;

                case 2:
                    System.out.println("Introduce la comisión de mantenimiento:");
                    comisionMantenimiento = Double.parseDouble(scanner.next());
                    nuevaCuenta = new CuentaCorrientePersonal(titular, iban, saldoInicial, comisionMantenimiento);
                    break;

                case 3:
                    System.out.println("Introduce el máximo descubierto permitido:");
                    maxDescubierto = Double.parseDouble(scanner.next());
                    System.out.println("Introduce el tipo de interés por descubierto:");
                    interesDescubierto = Double.parseDouble(scanner.next());
                    System.out.println("Introduce la comisión fija por descubierto:");
                    comisionDescubierto = Double.parseDouble(scanner.next());
                    nuevaCuenta = new CuentaCorrienteEmpresa(titular, iban, saldoInicial, maxDescubierto, interesDescubierto, comisionDescubierto);
                    break;

                default:
                    System.out.println("Tipo de cuenta no válido.");
                    return;
            }

            if (banco.abrirCuenta(nuevaCuenta)) {
                System.out.println("Cuenta abierta exitosamente.");
            } else {
                System.out.println("Hubo un error al abrir la cuenta.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un número válido.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Tipo de dato incorrecto.");
            scanner.next(); // Limpiar la entrada incorrecta
        }
    }

    public static void listarCuentas(Banco banco) {
        for (String info : banco.listadoCuentas()) {
            System.out.println(info);
        }
    }

    public static void realizarIngresos(Scanner scanner, Banco banco) {
        try {
            System.out.println("Introduce el IBAN de la cuenta donde deseas ingresar dinero:");
            String ibanIngreso = scanner.next();

            System.out.println("Introduce la cantidad que deseas ingresar:");
            double cantidadIngreso = Double.parseDouble(scanner.next());

            if (banco.ingresoCuenta(ibanIngreso, cantidadIngreso)) {
                System.out.println("Ingreso realizado correctamente.");
            } else {
                System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce una cantidad válida.");
        }
    }

    public static void retirarEfectivo(Scanner scanner, Banco banco) {
        try {
            System.out.println("Introduce el IBAN de la cuenta donde deseas retirar dinero:");
            String ibanRetiro = scanner.next();

            System.out.println("Introduce la cantidad que deseas retirar:");
            double cantidadRetiro = Double.parseDouble(scanner.next());

            if (banco.retiradaCuenta(ibanRetiro, cantidadRetiro)) {
                System.out.println("Retiro realizado correctamente.");
            } else {
                System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce una cantidad válida.");
        }
    }

    public static void consultarSaldo(Scanner scanner, Banco banco) {
        try {
            System.out.println("Introduce el IBAN de la cuenta para consultar el saldo:");
            String ibanSaldo = scanner.next();

            double saldo = banco.obtenerSaldo(ibanSaldo);
            if (saldo >= 0) {
                System.out.println("El saldo actual de la cuenta con IBAN " + ibanSaldo + " es: " + saldo + " euros.");
            } else {
                System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: IBAN no válido.");
            scanner.next(); // Limpiar la entrada incorrecta
        }
    }

    public static void menu() {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("1. Abrir nueva cuenta");
            System.out.println("2. Listar cuentas");
            System.out.println("3. Realizar ingreso");
            System.out.println("4. Retirar efectivo");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    abrirCuenta(scanner, banco);
                    break;
                case 2:
                    listarCuentas(banco);
                    break;
                case 3:
                    realizarIngresos(scanner, banco);
                    break;
                case 4:
                    retirarEfectivo(scanner, banco);
                    break;
                case 5:
                    consultarSaldo(scanner, banco);
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();

    }

    public static void main(String[] args) {
        menu();
        }
}