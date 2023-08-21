package sg.edu.rp.c346.id22038283.carpark;

public class CarPark {
    private String carpark_no;
    private String datetime;
    private int totallots;
    private String lottype;
    private int availlots;

    public CarPark(String carpark_no, String datetime, int totallots, String lottype, int availlots) {
        this.carpark_no = carpark_no;
        this.datetime = datetime;
        this.totallots = totallots;
        this.lottype = lottype;
        this.availlots = availlots;
    }

    public String getCarpark_no() {
        return carpark_no;
    }

    public void setCarpark_no(String carpark_no) {
        this.carpark_no = carpark_no;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getTotallots() {
        return totallots;
    }

    public void setTotallots(int totallots) {
        this.totallots = totallots;
    }

    public String getLottype() {
        return lottype;
    }

    public void setLottype(String lottype) {
        this.lottype = lottype;
    }

    public int getAvaillots() {
        return availlots;
    }

    public void setAvaillots(int availlots) {
        this.availlots = availlots;
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "carpark_no='" + carpark_no + '\'' +
                ", datetime='" + datetime + '\'' +
                ", totallots=" + totallots +
                ", lottype='" + lottype + '\'' +
                ", availlots=" + availlots +
                '}';
    }
}