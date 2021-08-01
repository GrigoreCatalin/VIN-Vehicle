public class main {
    public static void main(String[] args) {

        double positionX = 4;
        double positionX2 = 2;
        double positionY = 4;
        double positionY2 = 3;

        double currentPositionX = positionX - positionX2;
        double currentPositionY = positionY - positionY2;

        Vehicle car1 = new Vehicle(2007, "1M8GDM9AXKP042788", "B-17-XMX", 125600, 2010);
        car1.moveVehicle(1234.123, 123.12);
        car1.isVinValid();

    }
}
