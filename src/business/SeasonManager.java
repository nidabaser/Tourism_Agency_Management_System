package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class SeasonManager {

    private final SeasonDao seasonDao;

    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }

    public Season getById(int id){
        return this.seasonDao.getById(id);
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasonList) {

        ArrayList<Object[]> seasonObjectList = new ArrayList<>();

        for (Season object : seasonList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();

            rowObject[i++] = object.getHotelId();

            rowObject[i++] = object.getStartDate();

            rowObject[i++] = object.getFinishDate();

            seasonObjectList.add(rowObject);

        }

        return seasonObjectList;

    }

    public boolean save(Season season) {

        if (this.getById(season.getId()) != null) {

            Helper.showMsg("Error: Season with ID " + season.getId() + " already exists.");

            return false;

        }

        return this.seasonDao.save(season);

    }

    public ArrayList<Season> getSeasonsByHotelId(int key) {

        return this.seasonDao.findSeasonByHotelId(key);

    }

}