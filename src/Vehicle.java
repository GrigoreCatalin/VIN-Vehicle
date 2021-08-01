import java.util.Scanner;

public class Vehicle {
    static final private String producedBy = "Ford";
    final private int productionYear;
    final private String vin;
    private String plateNumber;
    private double kilometers;
    private int lastSoldOnYear;
    private double positionX;
    private double positionY;

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getKilometers() {
        return this.kilometers;
    }

    public Vehicle(int productionYear, String vin, String plateNumber, int kilometers, int lastSoldOnYear) {
        this(productionYear, vin, kilometers, lastSoldOnYear);
        this.plateNumber = plateNumber;
    }

    public Vehicle(int productionYear, String vin, int kilometers, int lastSoldOnYear) {
        this(productionYear, vin);
        this.kilometers = kilometers;
        this.lastSoldOnYear = lastSoldOnYear;
    }

    public Vehicle(int productionYear, String vin) {
        this.productionYear = productionYear;
        this.vin = vin;
    }

    public void sellVehicle(String plateNumber, int lastSoldOnYear) {
        this.plateNumber = plateNumber;
        this.lastSoldOnYear = lastSoldOnYear;
    }

    public void currentPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double moveVehicle(double newPositionX, double newPositionY) {
        double newKilometers = Math.sqrt((Math.pow(newPositionX - this.positionX, 2)) + Math.pow(newPositionY - this.positionY, 2));
        this.kilometers += newKilometers;
        this.positionX = newPositionX;
        this.positionY = newPositionY;
        return this.kilometers;
    }

    public boolean isVinValid() {
        boolean isDrivingInNorthAmerica = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Autovehiculul circula in America de Nord? Raspundeti cu Da sau Nu");
        String n = sc.next();

        if (n.equals("Da")) {
            isDrivingInNorthAmerica = isVinValid(isDrivingInNorthAmerica);
            if (isDrivingInNorthAmerica) {
                System.out.println("Seria sasiului este valida." + "\n");
            } else {
                System.out.println("Seria sasiului nu este valida." + "\n");
            }

            printVinDecomposed();
            display();
            return isDrivingInNorthAmerica;

        } else {
            System.out.println("Verificarea nu a avut loc pentru ca vehiculul nu circula in America de Nord. " + " \n");
            printVinDecomposed();
            display();
            return false;
        }
    }

    private boolean isVinValid(boolean isDrivingInNorthAmerica) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 0, 7, 0, 9,
                2, 3, 4, 5, 6, 7, 8, 9};
        int[] weights = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2};

        int valueForVin = 0;
        int weight = 0;
        int sum = 0;


        for (int i = 0; i < this.vin.length(); i++) {
            if (Character.isLetter(this.vin.charAt(i))) {
                char c = this.vin.charAt(i);
                valueForVin = numbers[c - 'A'];
                weight = weights[i];
            } else {
                valueForVin = this.vin.charAt(i) - '0';
                weight = weights[i];
            }

            sum += valueForVin * weight;
        }

        int check = sum % 11;
        if (check == 10 && this.vin.charAt(8) == 'X') {
            return true;
        } else if (check == checkLetters(this.vin.charAt(8), check)) {
            return true;
        }
        return false;
    }

    private int checkLetters(char c, int check) {
        int number = 0;
        if (Character.isLetter(c)) {
            if ((c == 'A') || (c == 'J')) {
                number = 1;
            } else if ((c == 'B') || (c == 'K') || (c == 'S')) {
                number = 2;
            } else if ((c == 'C') || (c == 'L') || (c == 'T')) {
                number = 3;
            } else if ((c == 'D') || (c == 'M') || (c == 'U')) {
                number = 4;
            } else if ((c == 'E') || (c == 'N') || (c == 'V')) {
                number = 5;
            } else if ((c == 'F') || (c == 'W')) {
                number = 6;
            } else if ((c == 'G') || (c == 'P') || (c == 'X')) {
                number = 7;
            } else if ((c == 'H') || (c == 'Y')) {
                number = 8;
            } else if ((c == 'R') || (c == 'Z')) {
                number = 9;
            }
        } else
            number = Character.getNumericValue(c);
        if (number == check) {
            return number;
        }
        return number;
    }

    private void printVinDecomposed() {
        String substring1 = this.vin.substring(0, 3);
        System.out.println("Identificatorul producatorului: " + substring1);

        String substring2 = this.vin.substring(3, 8);
        System.out.println("Atributele vehiculului: " + substring2);

        System.out.println("Cifra de verificare: " + this.vin.charAt(8) + "\n" + "Anul productiei modelului: "
                + this.vin.charAt(9) + "\n" + "Seria fabricii: "  + this.vin.charAt(10));

        String substring3 = this.vin.substring(11, 17);
        System.out.println("Identificatorul numeric: " + substring3);
    }

    private void display() {
        System.out.println("\n" + "Datele autovehiculului: " + "\n" + "Marca autovehicului: " + producedBy + "\n" + "Anul fabricatiei: "
                + this.productionYear + "\n" + "Vin-ul autovehiculului " + this.vin + "\n" + "Numarul de inmatriculare "
                + this.plateNumber + "\n" + "Kilometri parcursi " + this.kilometers + " km" + "\n" + "Anul ultimei vanzari: " + this.lastSoldOnYear);
    }


}
