package entity;
import core.ComboItem;

/**
 * @author Nida Başer
 * April 2024
 */

public class Pension {

    private int id;

    private int hotelId;

    private Pension.Types pension_type;

    public enum Types {

        Ultra_All_Inclusive,

        All_Inclusive,

        Full_Pension,

        Half_Board_with_Room_Breakfast,

        Just_Bed,

        Excluding_Alcohol_Full_Credit

        }

    public Pension(){}

    public Types getTypes(){
        return pension_type;
    }

    public void setTypes(Types types){
        this.pension_type = types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getTypes().toString());
    }

}