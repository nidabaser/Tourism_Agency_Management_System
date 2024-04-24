package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Pension;
import entity.Reservation;
import entity.Room;
import entity.Season;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class RoomManager {

    private final RoomDao roomDao;

    private final Pension pension;

    private final ReservationDao reservationDao;

    private final HotelManager hotelManager;

    private final PensionManager pensionManager;

    private final SeasonManager seasonManager;

    public RoomManager() {

        this.roomDao = new RoomDao();

        this.reservationDao = new ReservationDao();

        this.pension = new Pension();

        this.pensionManager = new PensionManager();

        this.hotelManager = new HotelManager();

        this.seasonManager = new SeasonManager();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList) {

        ArrayList<Object[]> roomObjectList = new ArrayList<>();

        for (Room object : roomList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();

            rowObject[i++] = object.getHotel().getName();

            rowObject[i++] = pensionManager.getByTypeName(object.getPensionId());

            rowObject[i++] = object.getSeason().getComboItem().toString();

            rowObject[i++] = object.getType();

            rowObject[i++] = object.getStock();

            rowObject[i++] = object.getAdultPrice();

            rowObject[i++] = object.getChildPrice();

            rowObject[i++] = object.getBedCapacity();

            rowObject[i++] = object.getSquareMeter();

            rowObject[i++] = object.isTelevision();

            rowObject[i++] = object.isMinibar();

            rowObject[i++] = object.isGameConsole();

            rowObject[i++] = object.isCashBox();

            rowObject[i++] = object.isProjection();

            roomObjectList.add(rowObject);

        }

        return roomObjectList;

    }

    public ArrayList<Room> findRoomByHotelId(int selectedHotelId) {

        return this.roomDao.findRoomByHotelId(selectedHotelId);

    }

    public boolean delete(int selectedRoomId) {

        if (this.getById(selectedRoomId) == null) {

            Helper.showMsg("Error: Room with ID " + selectedRoomId + " not found for deletion.");

            return false;

        }

        return this.roomDao.delete(selectedRoomId);

    }

    public boolean save(Room room) {

        if (this.getById(room.getId()) != null) {

            Helper.showMsg("Error: Room with ID " + room.getId() + " already exists.");

            return false;

        }

        return this.roomDao.save(room);

    }

    // Method that filters rooms according to search criteria
    public ArrayList<Room> searchForTable(String hotelName, String city, String checkinDate, String checkoutDate, String adultNum, String childNum) {

        String query = "SELECT * from public.room r " +
                "LEFT JOIN public.hotel h ON r.hotel_id = h.id " +
                "LEFT JOIN public.season s ON r.season_id = s.id WHERE";

        ArrayList<String> whereList = new ArrayList<>();

        whereList.add(" r.stock > " + 0);

        if (checkinDate != null && !checkinDate.isEmpty() && checkoutDate != null && !checkoutDate.isEmpty()) {

            checkinDate = LocalDate.parse(checkinDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

            checkoutDate = LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

            whereList.add(" AND s.start_date <= '" + checkinDate + "'");

            whereList.add(" AND s.finish_date >='" + checkoutDate + "'");

        }

        if (hotelName != null) {

            whereList.add(" AND h.name ILIKE '%" + hotelName + "%'");

        }

        if (city != null) {

            whereList.add(" AND h.city ILIKE '%" + city + "%'");

        }

        if (adultNum != null && !adultNum.isEmpty() && childNum != null && !childNum.isEmpty()) {

            try {

                int adultNumber = Integer.parseInt(adultNum);

                int childNumber = Integer.parseInt(childNum);

                int totalNumber = adultNumber + childNumber;

                whereList.add(" AND r.bed_capacity >= '" + (totalNumber) + "'");

            } catch (NumberFormatException e) {

                e.printStackTrace();

            }

            query += String.join("", whereList);

            System.out.println(query); // <-- To print and check the query
        }

        ArrayList<Room> queryResult = this.roomDao.selectByQuery(query);

        return queryResult;
    }

    // Method to update room stock
    public boolean updateStock(Room room) {

        if (this.getById(room.getId()) == null) {

            return false;

        }

        return this.roomDao.updateStock(room);

    }
}