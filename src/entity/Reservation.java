package entity;

import java.time.LocalDate;

/**
 * @author Nida Başer
 * April 2024
 */

public class Reservation {

    private int id;

    private int roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private double totalPrice;

    private int guestCount;

    private String guestName;

    private String guestCitizenId;

    private String guestMail;

    private String guestPhone;

    private int adult_count;

    private int child_count;

    public Reservation() {
    }

    // Constructor with parameters

    public Reservation(int id, int room_id, LocalDate checkInDate, LocalDate checkOutDate, double total_price, int guest_count, String guest_name, String guest_citizen_id, String guest_mail,String guest_phone) {

        this.id = id;

        this.roomId = room_id;

        this.checkInDate = checkInDate;

        this.checkOutDate = checkOutDate;

        this.totalPrice = total_price;

        this.guestCount = guest_count;

        this.guestName = guest_name;

        this.guestCitizenId = guest_citizen_id;

        this.guestMail = guest_mail;

        this.guestPhone = guest_phone;

    }

    public int getAdultCount() {
        return adult_count;
    }

    public void setAdultCount(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getChildCount() {
        return child_count;
    }

    public void setChildCount(int child_count) {
        this.child_count = child_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestCitizenId() {
        return guestCitizenId;
    }

    public void setGuestCitizenId(String guestCitizenId) {
        this.guestCitizenId = guestCitizenId;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

}