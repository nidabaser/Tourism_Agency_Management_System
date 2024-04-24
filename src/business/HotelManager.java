package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao = new HotelDao();
    }

    public Hotel getById(int id){
        return this.hotelDao.getById(id);
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotelList) {

        ArrayList<Object[]> hotelObjectList = new ArrayList<>();

        for (Hotel object : hotelList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();

            rowObject[i++] = object.getName();

            rowObject[i++] = object.getCity();

            rowObject[i++] = object.getRegion();

            rowObject[i++] = object.getAddress();

            rowObject[i++] = object.getMail();

            rowObject[i++] = object.getPhone();

            rowObject[i++] = object.getStar();

            rowObject[i++] = object.isCarPark();

            rowObject[i++] = object.isWifi();

            rowObject[i++] = object.isPool();

            rowObject[i++] = object.isFitness();

            rowObject[i++] = object.isConcierge();

            rowObject[i++] = object.isSpa();

            rowObject[i++] = object.isRoomService();

            hotelObjectList.add(rowObject);

        }

        return hotelObjectList;

    }

    public boolean delete(int id) {

        if (this.getById(id) == null) {

            Helper.showMsg("Error: Hotel with ID " + id + " not found for deletion.");

            return false;

        }

        return this.hotelDao.delete(id);

    }

    public boolean update(Hotel hotel) {

        if (this.getById(hotel.getId()) == null) {

            Helper.showMsg("Error: Hotel with ID " + hotel.getId() + " not found for update.");

            return false;

        }

        return this.hotelDao.update(hotel);

    }

    public boolean save(Hotel hotel) {

        if (this.getById(hotel.getId()) != null) {

            Helper.showMsg("Error: Hotel with ID " + hotel.getId() + " already exists.");

            return false;

        }

        return this.hotelDao.save(hotel);

    }
}