package sg.edu.rp.c346.reservation;

public class Reservation {

    private String name;
    private int tele;
    private int size;
    private String smoke;
    private String date;
    private String time;

    public Reservation(String name, int tele, int size, String smoke, String date, String time) {
        this.name = name;
        this.tele = tele;
        this.size = size;
        this.smoke = smoke;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTele() {
        return tele;
    }

    public int getSize() {
        return size;
    }

    public String getSmoke() {
        return smoke;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
