package dao;

import core.Db;
import entity.Pension;
import entity.Room;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class RoomDao {

    private final Connection conn;

    private final HotelDao hotelDao;

    private final PensionDao pensionDao;

    private final SeasonDao seasonDao;

    public RoomDao(){

        this.conn = Db.getInstance();

        this.hotelDao = new HotelDao();

        this.pensionDao = new PensionDao();

        this.seasonDao = new SeasonDao();

    }

    public Room getById(int id){

        Room obj = null;

        String query = "SELECT * FROM public.room WHERE id = ?";

        try{

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                obj = this.match(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return obj;

    }

    public ArrayList<Room> findRoomByHotelId(int selectedHotelId){

        ArrayList<Room> roomsList = new ArrayList<>();

        String query = "SELECT * FROM public.room WHERE hotel_id = ? ORDER BY id ASC";

        try{

            PreparedStatement pr = this.conn.prepareStatement(query);

            pr.setInt(1, selectedHotelId);

            ResultSet rs = pr.executeQuery();

            while(rs.next()){
                roomsList.add(this.match(rs));
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return roomsList;

    }

    public Room match(ResultSet rs) throws SQLException{

        Room room = new Room();

        room.setId(rs.getInt("id"));

        room.setHotelId(rs.getInt("hotel_id"));

        room.setPensionId(rs.getInt("pension_id"));

        room.setSeasonId(rs.getInt("season_id"));

        room.setType(rs.getString("type"));

        room.setStock(rs.getInt("stock"));

        room.setAdultPrice(rs.getInt("adult_price"));

        room.setChildPrice(rs.getInt("child_price"));

        room.setBedCapacity(rs.getInt("bed_capacity"));

        room.setSquareMeter(rs.getInt("square_meter"));

        room.setTelevision(rs.getBoolean("television"));

        room.setMinibar(rs.getBoolean("minibar"));

        room.setGameConsole(rs.getBoolean("game_console"));

        room.setCashBox(rs.getBoolean("cash_box"));

        room.setProjection(rs.getBoolean("projection"));

        room.setHotel(this.hotelDao.getById(room.getHotelId()));

        room.setPension(this.pensionDao.getById(room.getPensionId()));

        room.setSeason(this.seasonDao.getById(room.getSeasonId()));

        return room;

    }

    public ArrayList<Room> findAll() {

        ArrayList<Room> roomsList = new ArrayList<>();

        String query = "SELECT * FROM public.room ORDER BY id ASC";

        try{

            ResultSet rs = this.conn.createStatement().executeQuery(query);

            while (rs.next()) {

                roomsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return roomsList;

    }

    public boolean delete(int selectedRoomId) {

        String query = "DELETE FROM public.room WHERE id = ? ";

        try {

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, selectedRoomId);

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public boolean save(Room room) {

        String query = "INSERT INTO public.room " +

                "(" +

                "hotel_id," +

                "pension_id," +

                "season_id," +

                "type," +

                "stock," +

                "adult_price," +

                "child_price," +

                "bed_capacity," +

                "square_meter," +

                "television," +

                "minibar," +

                "game_console," +

                "cash_box," +

                "projection" +

                ")" +

                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            PreparedStatement ps = this.conn.prepareStatement(query);

            ps.setInt(1, room.getHotelId());

            ps.setInt(2, room.getPensionId());

            ps.setInt(3, room.getSeasonId());

            ps.setString(4, room.getType());

            ps.setInt(5, room.getStock());

            ps.setDouble(6, room.getAdultPrice());

            ps.setDouble(7, room.getChildPrice());

            ps.setInt(8, room.getBedCapacity());

            ps.setInt(9, room.getSquareMeter());

            ps.setBoolean(10, room.isTelevision());

            ps.setBoolean(11, room.isMinibar());

            ps.setBoolean(12, room.isGameConsole());

            ps.setBoolean(13, room.isCashBox());

            ps.setBoolean(14, room.isProjection());

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

    public ArrayList<Room> selectByQuery(String query) {

        ArrayList<Room> rooms = new ArrayList<>();

        try {

            ResultSet rs = this.conn.createStatement().executeQuery(query);

            while (rs.next()) {

                rooms.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return rooms;

    }

    // Method for update room stock
    public boolean updateStock(Room room){

        String query = "UPDATE public.room SET stock = ? WHERE id = ? ";

        try {

            PreparedStatement pr = this.conn.prepareStatement(query);

            pr.setInt(1, room.getStock());

            pr.setInt(2, room.getId());

            pr.executeUpdate();

        }catch (SQLException e){


            e.printStackTrace();

        }

        return true;

    }

}