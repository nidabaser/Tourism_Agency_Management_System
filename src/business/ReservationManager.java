package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class ReservationManager {

    private final ReservationDao reservationDao;

    public ReservationManager(){
        this.reservationDao = new ReservationDao();
    }

    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservationList) {

        ArrayList<Object[]> reservetionObjectList = new ArrayList<>();

        for (Reservation object : reservationList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();

            rowObject[i++] = object.getRoomId();

            rowObject[i++] = object.getCheckInDate();

            rowObject[i++] = object.getCheckOutDate();

            rowObject[i++] = object.getTotalPrice();

            rowObject[i++] = object.getGuestCount();

            rowObject[i++] = object.getGuestName();

            rowObject[i++] = object.getGuestCitizenId();

            rowObject[i++] = object.getGuestMail();

            rowObject[i++] = object.getGuestPhone();

            reservetionObjectList.add(rowObject);

        }

        return reservetionObjectList;

    }

    // Save Reservation
    public boolean save(Reservation reservation){

        if(reservation.getId()!=0){

            Helper.showMsg("error");

        }

        return this.reservationDao.save(reservation);

    }

    // Update an existing Reservation
    public boolean update(Reservation reservation){

        if(this.getById(reservation.getId()) == null){

            Helper.showMsg("Reservation with ID: " + reservation.getId() + "is not found");

            return false;

        }

        return this.reservationDao.update(reservation);

    }

    // Delete Reservation By ID
    public boolean delete(int id){

        if (this.getById(id)==null){

            Helper.showMsg(" Reservation not found");

            return false;

        }

        for (Reservation reservation:this.reservationDao.getByListReservationId(id)){

            this.reservationDao.delete(reservation.getId());

        }

        return this.reservationDao.delete(id);

    }

}