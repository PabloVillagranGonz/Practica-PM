import java.util.Scanner;

public class Principal {

    public static void abrirCuenta(Scanner scanner, Banco banco){
        String nombre, apellidos, dni, iban;
        double saldoInicial, interesAnual, maxDescubierto, comisionDescubierto,
                interesDescubierto, comisionMantenimiento;
        int tipoCuenta;

        System.out.println("Introduce el nombre del titular:");
        nombre = scanner.nextLine();
        System.out.println("Introduce los apellidos del titular:");
        apellidos = scanner.nextLine();

        System.out.println("Introduce el DNI del titular:");
        dni = scanner.nextLine();

        Persona titular = new Persona(nombre, apellidos, dni);

        System.out.println("Introduce el IBAN de la nueva cuenta:");
        iban = scanner.nextLine();
        System.out.println("Introduce el saldo inicial:");
        saldoInicial = Double.parseDouble(scanner.nextLine());

        System.out.println("Elige el tipo de cuenta a crear:");
        System.out.println("1. Cuenta de Ahorro");
        System.out.println("2. Cuenta Corriente Personal");
        System.out.println("3. Cuenta Corriente de Empresa");
        tipoCuenta = Integer.parseInt(scanner.nextLine());

        CuentaBancaria nuevaCuenta = null;

        switch (tipoCuenta) {
            case 1:
                System.out.println("Introduce el tipo de interés anual:");
                interesAnual = scanner.nextDouble();
                nuevaCuenta = new CuentaAhorro(titular, iban, saldoInicial, interesAnual);
                break;
            case 2:
                System.out.println("Introduce la comisión de mantenimiento:");
                comisionMantenimiento = scanner.nextDouble();
                nuevaCuenta = new CuentaCorrientePersonal(titular, iban, saldoInicial, comisionMantenimiento);
                break;
            case 3:
                System.out.println("Introduce el máximo descubierto permitido:");
                maxDescubierto = scanner.nextDouble();
                System.out.println("Introduce el tipo de interés por descubierto:");
                interesDescubierto = scanner.nextDouble();
                System.out.println("Introduce la comisión fija por descubierto:");
                comisionDescubierto = scanner.nextDouble();
                nuevaCuenta = new CuentaCorrienteEmpresa(titular, iban, saldoInicial, maxDescubierto, interesDescubierto, comisionDescubierto);
                break;
            default:
                System.out.println("Tipo de cuenta no válido.");
                break;
        }

        if (nuevaCuenta != null) {
            if (banco.abrirCuenta(nuevaCuenta)) {
                System.out.println("Cuenta abierta exitosamente.");
            } else {
                System.out.println("Hubo un error al abrir la cuenta.");
            }
        }
    }
    public static void listarCuentas( Banco banco){
        for (String info : banco.listadoCuentas()) {
            System.out.println(info);
        }
    }

    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        int opcion;

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
                case 3:

                    System.out.println("Introduce el IBAN de la cuenta donde deseas ingresar dinero:");
                    String ibanIngreso = scanner.nextLine();

                    System.out.println("Introduce la cantidad que deseas ingresar:");
                    double cantidadIngreso = Double.parseDouble(scanner.nextLine());

                    if (banco.ingresoCuenta(ibanIngreso, cantidadIngreso)) {
                        System.out.println("Ingreso realizado correctamente.");
                    } else {
                        System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
                    }
                    break;

                case 4:
                    // Lógica para retirada
                    break;
                case 5:
                    System.out.println("Introduce el IBAN de la cuenta para consultar el saldo:");
                    String ibanSaldo = scanner.nextLine();

                    double saldo = banco.obtenerSaldo(ibanSaldo);
                    if (saldo >= 0) {
                        System.out.println("El saldo actual de la cuenta con IBAN " + ibanSaldo + " es: " + saldo + " euros.");
                    } else {
                        System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }
}
