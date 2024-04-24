package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class ReservationDao {

    private final Connection con;

    public ReservationDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Reservation> findAll() {

        ArrayList<Reservation> reservationsList = new ArrayList<>();

        String query = "SELECT * FROM public.reservation";

        try {

            ResultSet rs = this.con.createStatement().executeQuery(query);

            while (rs.next()) {

                reservationsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return reservationsList;

    }

    public Reservation getById(int id) {

        Reservation obj = null;

        String query = "SELECT * FROM public.reservation WHERE id = ?";

        try {

            PreparedStatement ps = this.con.prepareStatement(query);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                obj = this.match(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return obj;

    }

    public Reservation match(ResultSet rs) throws SQLException {

        Reservation reservation = new Reservation();

        reservation.setId(rs.getInt("id"));

        reservation.setRoomId(rs.getInt("room_id"));

        reservation.setCheckInDate(LocalDate.parse(rs.getString("check_in_date")));

        reservation.setCheckOutDate(LocalDate.parse(rs.getString("check_out_date")));

        reservation.setTotalPrice(rs.getInt("total_price"));

        reservation.setGuestCount(rs.getInt("guest_count"));

        reservation.setGuestName(rs.getString("guest_name"));

        reservation.setGuestCitizenId(rs.getString("guest_citizen_id"));

        reservation.setGuestMail(rs.getString("guest_mail"));

        reservation.setGuestPhone(rs.getString("guest_phone"));

        return reservation;

    }

    public ArrayList<Reservation> selectByQuery(String reservationQuery) {

        ArrayList<Reservation> reservations = new ArrayList<>();

        try {

            ResultSet rs = this.con.createStatement().executeQuery(reservationQuery);


            while (rs.next()) {

                reservations.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return reservations;

    }

    public boolean save(Reservation reservation) {

        String query = "INSERT INTO public.reservation" +

                "(" +

                "room_id," +

                "check_in_date," +

                "check_out_date," +

                "total_price," +

                "guest_count," +

                "guest_name," +

                "guest_citizen_id," +

                "guest_mail," +

                "guest_phone" +

                ")" +

                "VALUES (?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = con.prepareStatement(query);

            pr.setInt(1, reservation.getRoomId());

            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));

            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));

            pr.setDouble(4, reservation.getTotalPrice());

            pr.setInt(5, reservation.getGuestCount());

            pr.setString(6, reservation.getGuestName());

            pr.setString(7, reservation.getGuestCitizenId());

            pr.setString(8, reservation.getGuestMail());

            pr.setString(9, reservation.getGuestPhone());

            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        return true;
    }

    public boolean update(Reservation reservation) {

        try {

            String query = "UPDATE public.reservation SET " +

                    "room_id = ?," +

                    "check_in_date = ?," +

                    "check_out_date = ?," +

                    "total_price = ?," +

                    "guest_count = ?," +

                    "guest_name = ?," +

                    "guest_citizen_id = ?," +

                    "guest_mail = ?," +

                    "guest_phone = ? " +

                    "WHERE id = ?";

            PreparedStatement pr = con.prepareStatement(query);

            pr.setInt(1, reservation.getRoomId());

            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));

            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));

            pr.setDouble(4, reservation.getTotalPrice());

            pr.setInt(5, reservation.getGuestCount());

            pr.setString(6, reservation.getGuestName());

            pr.setString(7, reservation.getGuestCitizenId());

            pr.setString(8, reservation.getGuestMail());

            pr.setString(9, reservation.getGuestPhone());

            pr.setInt(10, reservation.getId());

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public boolean delete(int hotel_id) {

        try {

            String query = "DELETE FROM public.reservation WHERE id = ?";

            PreparedStatement pr = con.prepareStatement(query);

            pr.setInt(1, hotel_id);

            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }

        return true;

    }

    public ArrayList<Reservation> getByListReservationId(int id) {

        return this.selectByQuery("SELECT * FROM public.reservation WHERE id = " + id);

    }

}