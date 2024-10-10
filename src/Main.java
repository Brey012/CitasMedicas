import java.util.Scanner;

class AgendaCitas {
    private static String[][][] citas = new String[3][10][2]; // 3 doctores, 10 citas cada uno
    private static String[][] usuarios = new String[3][2];
    private static boolean isAuthenticated = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Citas");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Agendar cita");
            System.out.println("4. Mostrar citas");
            System.out.println("5. Eliminar cita");
            System.out.println("6. Editar cita");
            System.out.println("7. Mostrar doctores");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            if (!isAuthenticated && opcion > 2) {
                System.out.println("Debe iniciar sesión para continuar.");
                login();
            }

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    agendarCita(scanner);
                    break;
                case 4:
                    mostrarCitas();
                    break;
                case 5:
                    eliminarCita(scanner);
                    break;
                case 6:
                    editarCita(scanner);
                    break;
                case 7:
                    doctores();
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema de citas.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void crearUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la contraseña: ");
        String contraseña = scanner.nextLine();
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0] == null) {
                usuarios[i][0] = nombre;
                usuarios[i][1] = contraseña;
                System.out.println("Usuario creado exitosamente.");
                return;
            }
        }
    }

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la contraseña: ");
        String contraseña = scanner.nextLine();
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0] != null && usuarios[i][0].equals(nombre) && usuarios[i][1].equals(contraseña)) {
                System.out.println("Bienvenido " + nombre);
                isAuthenticated = true;
                return;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        isAuthenticated = false;
    }

    private static void agendarCita(Scanner scanner) {
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la fecha de la cita (dd/mm/yyyy): ");
        String fecha = scanner.nextLine();

        System.out.println("Seleccione el doctor:");
        String[] doctores = {"Dr. Juanito Pérez", "Dra. María Gómez", "Dr. Carlos Sainz"};
        for (int i = 0; i < doctores.length; i++) {
            System.out.println((i + 1) + ". " + doctores[i]);
        }
        int doctorIndex = Integer.parseInt(scanner.nextLine()) - 1;

        for (int i = 0; i < citas[doctorIndex].length; i++) {
            if (citas[doctorIndex][i][0] == null) {
                citas[doctorIndex][i][0] = nombre;
                citas[doctorIndex][i][1] = fecha;
                System.out.println("Cita agendada exitosamente con " + doctores[doctorIndex]);
                return;
            }
        }

        System.out.println("No hay espacio para más citas con " + doctores[doctorIndex]);
    }

    private static void mostrarCitas() {
        String[] doctores = {"Dr. Juanito Pérez", "Dra. María Gómez", "Dr. Carlos Sainz"};
        for (int d = 0; d < doctores.length; d++) {
            System.out.println("Citas con " + doctores[d] + ":");
            for (int i = 0; i < citas[d].length; i++) {
                if (citas[d][i][0] != null) {
                    System.out.println("Paciente: " + citas[d][i][0] + ", Fecha: " + citas[d][i][1]);
                }
            }
        }
    }

    private static void eliminarCita(Scanner scanner) {
        System.out.print("Ingrese el nombre del paciente cuya cita desea eliminar: ");
        String nombre = scanner.nextLine();

        for (int d = 0; d < citas.length; d++) {
            for (int i = 0; i < citas[d].length; i++) {
                if (citas[d][i][0] != null && citas[d][i][0].equals(nombre)) {
                    citas[d][i][0] = null;
                    citas[d][i][1] = null;
                    System.out.println("Cita eliminada exitosamente.");
                    return;
                }
            }
        }

        System.out.println("No se encontró una cita para el paciente especificado.");
    }

    private static void editarCita(Scanner scanner) {
        System.out.print("Ingrese el nombre del paciente cuya cita desea editar: ");
        String nombre = scanner.nextLine();

        for (int d = 0; d < citas.length; d++) {
            for (int i = 0; i < citas[d].length; i++) {
                if (citas[d][i][0] != null && citas[d][i][0].equals(nombre)) {
                    System.out.print("Ingrese la nueva fecha de la cita (dd/mm/yyyy): ");
                    String nuevaFecha = scanner.nextLine();
                    citas[d][i][1] = nuevaFecha;
                    System.out.println("Cita editada exitosamente.");
                    return;
                }
            }
        }

        System.out.println("No se encontró una cita para el paciente especificado.");
    }

    private static void doctores() {
        System.out.println("Doctores disponibles:");
        String[] doctores = {"Dr. Juanito Pérez", "Dra. María Gómez", "Dr. Carlos Sainz"};
        for (int i = 0; i < doctores.length; i++) {
            System.out.println((i + 1) + ". " + doctores[i]);
        }
    }
}