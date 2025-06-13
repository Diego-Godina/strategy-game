package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {

    public static int validateIntBetween(Scanner sc, String message, int min, int max) {

        int value;
        while(true) {
            System.out.print(message);
            try {
                value = sc.nextInt();
                sc.nextLine();

                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Introduza um número inteiro entre " + min + " e " + max);

            } catch (InputMismatchException e) {
                System.out.println("Introduza um número inteiro entre " + min + " e " + max);
                sc.nextLine();
            }
        }
    }

    public static double validateDouble(Scanner sc, String message) {
        double value;
        while (true) {
            try {
                System.out.print(message);
                value = sc.nextDouble();
                sc.nextLine();

                return value;

            } catch (Exception e) {
                System.out.println("Introduza um número.");
                sc.nextLine();
            }
        }
    }

    public static int validateIntGT0(Scanner sc, String message) {

        while (true) {
            try {
                System.out.print(message);
                int value = sc.nextInt();
                sc.nextLine();

                if (value > 0) {
                    return value;
                }
                System.out.println("Introduza um número inteiro maior do que 0.");

            } catch (InputMismatchException e) {
                System.out.println("Introduza um número inteiro maior do que 0.");
                sc.nextLine();
            }
        }
    }

    public static int validateInt(Scanner sc, String message) {
        int i;
        while (true) {
            try {
                System.out.print(message);
                i = sc.nextInt();
                sc.nextLine();

                return i;

            } catch (Exception e) {
                System.out.println("Introduza um número inteiro.");
                sc.nextLine();
            }
        }
    }

    public static double validateDoubleBetween(Scanner sc, String message, double min, double max) {

        double value;
        while(true) {
            System.out.print(message);
            try {
                value = sc.nextDouble();
                sc.nextLine();

                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Introduza um número entre " + min + " e " + max);

            } catch (InputMismatchException e) {
                System.out.println("Introduza um número entre " + min + " e " + max);
                sc.nextLine();
            }
        }
    }

    public static int validateIntGE0(Scanner sc, String message) {

        while(true) {

            try {
                System.out.print(message);
                int value = sc.nextInt();
                sc.nextLine();

                if (value >= 0) {
                    return value;

                }
                System.out.println("Introduza um número inteiro maior ou igual a zero.");

            } catch (InputMismatchException e) {
                System.out.println("Introduza um número inteiro maior ou igual a zero.");
                sc.nextLine();
            }
        }
    }

    public static double validateDoubleGT0(Scanner sc, String message) {

        while (true) {
            try {
                System.out.print(message);
                double value = sc.nextDouble();
                sc.nextLine();

                if (value > 0.0) {
                    return value;
                }
                System.out.println("Introduza um número maior do que 0.");

            } catch (InputMismatchException e) {
                System.out.println("Introduza um número maior do que 0");
                sc.nextLine();
            }
        }
    }

    public static double validateDoubleGE0(Scanner sc, String message) {

        while (true) {
            try {
                System.out.print(message);
                double value = sc.nextDouble();
                sc.nextLine();

                if (value >= 0.0) {
                    return value;
                }
                System.out.println("Introduza um número maior ou igual a 0.");

            } catch (InputMismatchException e) {
                System.out.println("Introduza um número maior ou igual a 0");
                sc.nextLine();
            }
        }
    }

    public static LocalDate validateDate(Scanner sc, String message) {

        while (true) {
            try {
                System.out.print(message);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(sc.nextLine(), dateFormat);

            } catch (DateTimeParseException e) {
                System.out.println("Introduza uma data válida (dd/mm/aaaa)");
            }
        }
    }
}
