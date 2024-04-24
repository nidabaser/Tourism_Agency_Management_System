package dao;

import core.Db;
import entity.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class HotelDao {

    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {

        ArrayList<Hotel> hotelsList = new ArrayList<>();

        String query = "SELECT * FROM public.hotel ORDER BY id ASC";

        try {

            ResultSet rs = this.con.createStatement().executeQuery(query);

            while (rs.next()) {

                hotelsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return hotelsList;

    }

    public Hotel match(ResultSet rs) throws SQLException {

        Hotel hotel = new Hotel();

        hotel.setId(rs.getInt("id"));

        hotel.setName(rs.getString("name"));

        hotel.setCity(rs.getString("city"));

        hotel.setRegion(rs.getString("region"));

        hotel.setAddress(rs.getString("address"));

        hotel.setMail(rs.getString("email"));

        hotel.setPhone(rs.getString("phone"));

        hotel.setStar(Hotel.Star.valueOf(rs.getString("star")));

        hotel.setCarPark(rs.getBoolean("car_park"));

        hotel.setWifi(rs.getBoolean("wifi"));

        hotel.setPool(rs.getBoolean("pool"));

        hotel.setFitness(rs.getBoolean("fitness"));

        hotel.setConcierge(rs.getBoolean("concierge"));

        hotel.setSpa(rs.getBoolean("spa"));

        hotel.setRoomService(rs.getBoolean("room_service"));

        return hotel;

    }

    public Hotel getById(int hotel_id) {

        Hotel obj = null;

        String query = "SELECT * FROM public.hotel WHERE id = ?";

        try {

            PreparedStatement pr = this.con.prepareStatement(query);

            pr.setInt(1, hotel_id);

            ResultSet rs = pr.executeQuery();

            if (rs.next()) {

                obj = this.match(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return obj;

    }

    public boolean save(Hotel hotel) {

        String query = "INSERT INTO public.hotel" +

                "(" +

                "name," +

                "city," +

                "region," +

                "address," +

                "email," +

                "phone," +

                "star," +

                "car_park, wifi, pool, fitness, concierge, spa, room_service" +

                ")" +

                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement pr = this.con.prepareStatement(query);

            pr.setString(1, hotel.getName());

            pr.setString(2, hotel.getCity());

            pr.setString(3, hotel.getRegion());

            pr.setString(4, hotel.getAddress());

            pr.setString(5, hotel.getMail());

            pr.setString(6, hotel.getPhone());

            pr.setString(7, hotel.getStar().toString());

            pr.setBoolean(8, hotel.isCarPark());

            pr.setBoolean(9, hotel.isWifi());

            pr.setBoolean(10, hotel.isPool());

            pr.setBoolean(11, hotel.isFitness());

            pr.setBoolean(12, hotel.isConcierge());

            pr.setBoolean(13, hotel.isSpa());

            pr.setBoolean(14, hotel.isRoomService());

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public boolean delete(int hotelId) {

        String query = "DELETE FROM public.hotel WHERE id = ? ";

        try {

            PreparedStatement ps = this.con.prepareStatement(query);

            ps.setInt(1, hotelId);


            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public boolean update(Hotel hotel) {

        String query = "UPDATE public.hotel SET " +

                "name = ?, " +

                "city = ?, " +

                "region = ?, " +

                "address = ?, " +

                "email = ?, " +

                "phone = ?, " +

                "star = ?, " +

                "car_park = ?, " +

                "wifi = ?, " +

                "pool = ?, " +

                "fitness = ?, " +

                "concierge = ?, " +

                "spa = ?, " +

                "room_service = ? " +

                "WHERE id = ?";

        try {

            PreparedStatement pr = this.con.prepareStatement(query);

            pr.setString(1, hotel.getName());

            pr.setString(2, hotel.getCity());

            pr.setString(3, hotel.getRegion());

            pr.setString(4, hotel.getAddress());

            pr.setString(5, hotel.getMail());

            pr.setString(6, hotel.getPhone());

            pr.setString(7, hotel.getStar().toString());

            pr.setBoolean(8, hotel.isCarPark());

            pr.setBoolean(9, hotel.isWifi());

            pr.setBoolean(10, hotel.isPool());

            pr.setBoolean(11, hotel.isFitness());

            pr.setBoolean(12, hotel.isConcierge());

            pr.setBoolean(13, hotel.isSpa());

            pr.setBoolean(14, hotel.isRoomService());

            pr.setInt(15, hotel.getId());

            return pr.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

}