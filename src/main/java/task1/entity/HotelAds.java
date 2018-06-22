package task1.entity;

import java.time.LocalDate;

public class HotelAds {
    private int advertising;
    private LocalDate date;
    private int hotelId;

    public HotelAds(int advertising, LocalDate date, int hotelId) {
        this.advertising = advertising;
        this.date = date;
        this.hotelId = hotelId;
    }

    public int getAdvertising() {
        return advertising;
    }

    public void setAdvertising(int advertising) {
        this.advertising = advertising;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "\n HotelAds{" +
                "advertising=" + advertising +
                ", date=" + date +
                ", hotelId='" + hotelId + '\'' +
                '}';
    }
}
