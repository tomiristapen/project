package MVC.model;

public class Seat {
        private int row;
        private int number;
        private boolean isAvailable;

        public Seat(int row, int number) {
            this.row = row;
            this.number = number;
            this.isAvailable = true;
        }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void toggleAvailability() {
        this.isAvailable = !this.isAvailable;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row: " + row +
                ", number: " + number +
                ", isAvailable: " + isAvailable +
                '}';
    }
}
