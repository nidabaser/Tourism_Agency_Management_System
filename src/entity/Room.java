package entity;

/**
 * @author Nida Başer
 * April 2024
 */

public class Room {

    private int id;

    private int hotelId;

    private int pensionId;

    private int seasonId;

    private String type;

    private int stock;

    private double adultPrice;

    private double childPrice;

    private int bedCapacity;

    private int squareMeter;

    private boolean television;

    private boolean minibar;

    private boolean gameConsole;

    private boolean cashBox;

    private boolean projection;

    private Room.Types room_types;

    private Hotel hotel;

    private Pension pension;

    private Season season;

    public enum Types {

        Single,

        Double,

        Suite,

        Junior_Suite

    }

    public Room() {
    }

    public Room.Types getTypes(){
        return room_types;
    }

    public void setTypes(Room.Types types){
        this.room_types = types;
    }

    public Hotel getHotel(){
        return hotel;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public Pension getPension(){
        return pension;
    }

    public void setPension(Pension pension){
        this.pension = pension;
    }

    public Season getSeason(){
        return season;
    }

    public void setSeason(Season season){
        this.season = season;
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

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public int setStock(int stock) {
        this.stock = stock;
        return stock;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isCashBox() {
        return cashBox;
    }

    public void setCashBox(boolean cashBox) {
        this.cashBox = cashBox;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

}