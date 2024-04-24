package dao;

import core.Db;
import entity.Pension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class PensionDao {

    private final Connection con;

    public PensionDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Pension> findPensionByHotelId(int selectedHotelId){

        ArrayList<Pension> pensionsList = new ArrayList<>();

        String query = "SELECT * FROM public.pension_type WHERE hotel_id = ?";

        try{

            PreparedStatement ps = this.con.prepareStatement(query);

            ps.setInt(1, selectedHotelId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                pensionsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return pensionsList;

    }

    public Pension getById(int id){

        Pension obj = null;

        String query = "SELECT * FROM public.pension_type WHERE id = ?";

        try{

            PreparedStatement ps = this.con.prepareStatement(query);

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

    public String getByTypeName(int id){

        String typename = null;

        String query = "SELECT pension_type FROM public.pension_type WHERE id = ?";

        try{

            PreparedStatement ps = this.con.prepareStatement(query);

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){

                typename = rs.getString("pension_type");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return typename;

    }

    public Pension match(ResultSet rs) throws SQLException{

        Pension pension = new Pension();

        pension.setId(rs.getInt("id"));

        pension.setHotelId(rs.getInt("hotel_id"));

        pension.setTypes(Pension.Types.valueOf(rs.getString("pension_type")));

        return pension;

    }

    public ArrayList<Pension> findAll() {

        ArrayList<Pension> pensionsList = new ArrayList<>();

        String query = "SELECT * FROM public.pension_type ORDER BY id ASC";

        try{

            ResultSet rs = this.con.createStatement().executeQuery(query);

            while (rs.next()) {

                pensionsList.add(this.match(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return pensionsList;

    }

    public boolean save(Pension pension) {

        String query = "INSERT INTO public.pension_type " +

                "(" +

                "hotel_id," +

                "pension_type" +

                ")" +

                "VALUES (?,?)";

        try {

            PreparedStatement ps = this.con.prepareStatement(query);

            ps.setInt(1, pension.getHotelId());

            ps.setString(2, pension.getTypes().toString());

            return ps.executeUpdate() != -1;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return true;

    }

}