package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class PensionManager {

    private final PensionDao pensionDao;

    public PensionManager(){
        this.pensionDao = new PensionDao();
    }

    public Pension getById(int id){
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public String getByTypeName(int id) {
        return this.pensionDao.getByTypeName(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensionList) {

        ArrayList<Object[]> pensionObjectList = new ArrayList<>();

        for (Pension object : pensionList) {

            int i = 0;

            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();

            rowObject[i++] = object.getHotelId();

            rowObject[i++] = object.getTypes();

            pensionObjectList.add(rowObject);

        }

        return pensionObjectList;

    }

    public boolean save(Pension pension) {

        if (this.getById(pension.getId()) != null) {

            Helper.showMsg("Error: Pension with ID " + pension.getId() + " already exists.");

            return false;

        }

        return this.pensionDao.save(pension);

    }

    public ArrayList<Pension> getPensionByHotelID(int key) {
        return this.pensionDao.findPensionByHotelId(key);
    }

}