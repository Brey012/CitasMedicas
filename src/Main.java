import java.util.Scanner;

class AgendaCitas {
    private static final String[][] citas = new String[3][2]; // Array para almacenar hasta 10 citas
    public static String[][] usuarios = new String[3][2];

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
            System.out.println("5. Mostrar doctores");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            if (!isAuthenticated) {
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

        for (int i = 0; i < citas.length; i++) {
            if (citas[i][0] == null) {
                citas[i][0] = nombre;
                citas[i][1] = fecha;
                System.out.println("Cita agendada exitosamente.");
                return;
            }
        }

        System.out.println("No hay espacio para más citas.");
    }

    private static void mostrarCitas() {
        System.out.println("Citas agendadas:");
        for (int i = 0; i < citas.length; i++) {
            if (citas[i][0] != null) {
                System.out.println("Paciente: " + citas[i][0] + ", Fecha: " + citas[i][1]);
            }
        }
    }

    private static void doctores() {
        System.out.println("Doctores disponibles:");

        String doctores[] = new String[3];
        doctores[0] = "Dr. Juan Pérez";
        doctores[1] = "Dra. María Gómez";
        doctores[2] = "Dr. Carlos Ramírez";

        for (int i = 0; i < doctores.length; i++) {
            System.out.println((i + 1) + ". " + doctores[i]);
        }
    }
}