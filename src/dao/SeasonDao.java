package dao;

import core.Db;
import entity.Season;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class SeasonDao {

    private final Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Season> findSeasonByHotelId(int selectedHotelId) {

        ArrayList<Season> seasonsList = new ArrayList<>();

        String query = "SELECT * FROM public.season WHERE hotel_id = ?";

        try {

            PreparedStatement pr = this.connection.prepareStatement(query);

            pr.setInt(1, selectedHotelId);

            ResultSet rs = pr.executeQuery();

            while (rs.next()) {

                seasonsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return seasonsList;

    }

    public Season getById(int id) {

        Season obj = null;

        String query = "SELECT * FROM public.season WHERE id = ?";

        try {

            PreparedStatement ps = this.connection.prepareStatement(query);

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

    public Season match(ResultSet rs) throws SQLException {

        Season season = new Season();

        season.setId(rs.getInt("id"));

        season.setHotelId(rs.getInt("hotel_id"));

        season.setStartDate(LocalDate.parse(rs.getString("start_date")));

        season.setFinishDate(LocalDate.parse(rs.getString("finish_date")));

        return season;

    }

    public ArrayList<Season> findAll() {

        ArrayList<Season> seasonsList = new ArrayList<>();

        String query = "SELECT * FROM public.season ORDER BY id ASC";

        try {

            ResultSet rs = this.connection.createStatement().executeQuery(query);

            while (rs.next()) {

                seasonsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return seasonsList;

    }

    public boolean save(Season season) {

        String query = "INSERT INTO public.season " +

                "(" +

                "hotel_id," +

                "start_date," +

                "finish_date" +

                ")" +

                "VALUES (?,?,?)";

        try {

            PreparedStatement ps = this.connection.prepareStatement(query);

            ps.setInt(1, season.getHotelId());

            ps.setDate(2, Date.valueOf(season.getStartDate()));

            ps.setDate(3, Date.valueOf(season.getFinishDate()));

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

}