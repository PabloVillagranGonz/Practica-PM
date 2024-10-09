import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    // Método para abrir una nueva cuenta en el banco
    public static void abrirCuenta(Scanner scanner, Banco banco) {
        try {
            // Declaración de variables
            String nombre, apellidos, dni, iban;
            double saldoInicial, interesAnual, maxDescubierto, comisionDescubierto,
                    interesDescubierto, comisionMantenimiento;
            int tipoCuenta;

            // Solicita al usuario el nombre, apellidos y DNI del titular
            System.out.println("Introduce el nombre del titular:");
            nombre = scanner.next();
            System.out.println("Introduce los apellidos del titular:");
            apellidos = scanner.next();
            System.out.println("Introduce el DNI del titular:");
            dni = scanner.next();

            // Se crea una instancia de la clase Persona para el titular
            Persona titular = new Persona(nombre, apellidos, dni);

            // Solicita al usuario el IBAN y saldo inicial
            System.out.println("Introduce el IBAN de la nueva cuenta:");
            iban = scanner.next();
            System.out.println("Introduce el saldo inicial:");
            saldoInicial = Double.parseDouble(scanner.next());

            // Solicita al usuario seleccionar el tipo de cuenta
            System.out.println("Elige el tipo de cuenta a crear:");
            System.out.println("1. Cuenta de Ahorro");
            System.out.println("2. Cuenta Corriente Personal");
            System.out.println("3. Cuenta Corriente de Empresa");
            tipoCuenta = Integer.parseInt(scanner.next());

            // Inicializa la cuenta bancaria
            CuentaBancaria nuevaCuenta = null;

            // Según el tipo de cuenta seleccionado, se solicita información adicional
            // y se crea una instancia de la clase correspondiente
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

            // Intenta abrir la cuenta en el banco, si es exitosa, muestra un mensaje
            if (banco.abrirCuenta(nuevaCuenta)) {
                System.out.println("Cuenta abierta exitosamente.");
            } else {
                System.out.println("Hubo un error al abrir la cuenta.");
            }

            // Captura excepciones por formato numérico incorrecto o tipo de dato inválido
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un número válido.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Tipo de dato incorrecto.");
            scanner.next();
        }
    }

    // Método para listar las cuentas existentes en el banco
    public static void listarCuentas(Banco banco) {
        for (String info : banco.listadoCuentas()) {
            System.out.println(info);
        }
    }

    // Método para realizar ingresos en una cuenta
    public static void realizarIngresos(Scanner scanner, Banco banco) {
        double cantidadIngreso;
        try {
            System.out.println("Introduce el IBAN de la cuenta donde deseas ingresar dinero:");
            String ibanIngreso = scanner.next();

            System.out.println("Introduce la cantidad que deseas ingresar:");
            cantidadIngreso = Double.parseDouble(scanner.next());

            // Intenta realizar el ingreso en la cuenta, y muestra si fue exitoso o no
            if (banco.ingresoCuenta(ibanIngreso, cantidadIngreso)) {
                System.out.println("Ingreso realizado correctamente.");
            } else {
                System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce una cantidad válida.");
        }
    }

    // Método para retirar efectivo de una cuenta
    public static void retirarEfectivo(Scanner scanner, Banco banco) {
        double cantidadRetiro;
        try {
            System.out.println("Introduce el IBAN de la cuenta donde deseas retirar dinero:");
            String ibanRetiro = scanner.next();

            System.out.println("Introduce la cantidad que deseas retirar:");
            cantidadRetiro = Double.parseDouble(scanner.next());

            // Intenta realizar el retiro de la cuenta, y muestra si fue exitoso o no
            if (banco.retiradaCuenta(ibanRetiro, cantidadRetiro)) {
                System.out.println("Retiro realizado correctamente.");
            } else {
                System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce una cantidad válida.");
        }
    }

    // Método para consultar el saldo de una cuenta
    public static void consultarSaldo(Scanner scanner, Banco banco) {
        String ibanSaldo;
        double saldo;
        try {
            System.out.println("Introduce el IBAN de la cuenta para consultar el saldo:");
            ibanSaldo = scanner.next();

            // Obtiene el saldo de la cuenta
            saldo = banco.obtenerSaldo(ibanSaldo);
            if (saldo >= 0) {
                System.out.println("El saldo actual de la cuenta con IBAN " + ibanSaldo + " es: " + saldo + " euros.");
            } else {
                System.out.println("No se ha encontrado la cuenta con el IBAN proporcionado.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: IBAN no válido.");
            scanner.next(); // Limpiamos la entrada
        }
    }

    public static void menu() {
        Banco banco = new Banco(); // Se crea una instancia del banco
        Scanner scanner = new Scanner(System.in); // Se crea el objeto Scanner para leer entrada del usuario
        int opcion = 0;

        // Bucle que muestra el menú hasta que se elija la opción de salir
        do {
            System.out.println("1. Abrir nueva cuenta");
            System.out.println("2. Listar cuentas");
            System.out.println("3. Realizar ingreso");
            System.out.println("4. Retirar efectivo");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            // Ejecuta la opcion correspondiente
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

        scanner.close(); // Cerramos el Scanner una vez finalizado el programa
    }

    // Método principal que ejecuta el menú
    public static void main(String[] args) {
        menu();
    }
}