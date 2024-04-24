package entity;

import core.ComboItem;
import java.time.LocalDate;

/**
 * @author Nida Başer
 * April 2024
 */

public class Season {

    private int id;

    private int hotelId;

    private LocalDate startDate;

    private LocalDate finishDate;

    public Season(int hotelId, LocalDate startDate, LocalDate finishDate) {

        this.hotelId = hotelId;

        this.startDate = startDate;

        this.finishDate = finishDate;

    }

    public Season(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getStartDate().toString() + " - " + this.getFinishDate().toString());
    }

}