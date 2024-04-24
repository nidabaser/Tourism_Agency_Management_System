package view;

import business.PensionManager;
import business.SeasonManager;
import business.RoomManager;
import business.HotelManager;
import entity.Pension;
import entity.Season;
import entity.Room;
import entity.Hotel;
import core.ComboItem;
import core.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class RoomSaveView extends Layout {

    private JPanel container;

    private JLabel lbl_add_room;

    private JPanel pnl_room_save_left;

    private JPanel pnl_room_save_right;

    private JLabel lbl_hotel_name;

    private JComboBox cmb_hotel_name;

    private JLabel lbl_pension_name;

    private JComboBox cmb_pension_name;

    private JLabel lbl_season;

    private JComboBox cmb_season;

    private JLabel lbl_room_type;

    private JComboBox cmb_room_type;

    private JLabel lbl_stock;

    private JTextField fld_stock;

    private JLabel lbl_adult_price;

    private JTextField fld_adult_price;

    private JTextField fld_child_price;

    private JLabel lbl_child_price;

    private JLabel lbl_bed_capacity;

    private JTextField fld_bed_capacity;

    private JLabel lbl_square_meter;

    private JTextField fld_square_meter;

    private JLabel lbl_tv;

    private JRadioButton rdbtn_tv;

    private JLabel lbl_minibar;

    private JRadioButton rdbtn_minibar;

    private JLabel lbl_game_console;

    private JRadioButton rdbtn_game_console;

    private JLabel lbl_cash_box;

    private JRadioButton rdbtn_cash_box;

    private JLabel lbl_projection;

    private JRadioButton rdbtn_projection;

    private JButton btn_save_room;

    private Hotel hotel;

    private HotelManager hotelManager;

    private Room room;

    private RoomManager roomManager;

    private Season season;

    private SeasonManager seasonManager;

    private Pension pension;

    private PensionManager pensionManager;

    public RoomSaveView(Room room) {

        this.room = room;

        this.roomManager = new RoomManager();

        this.hotelManager = new HotelManager();

        this.pensionManager = new PensionManager();

        this.seasonManager = new SeasonManager();

        this.add(container);

        this.guiInitialize(600, 400);

        for (Hotel hotel : this.hotelManager.findAll()) {

            this.cmb_hotel_name.addItem(hotel.getComboItem());

        }

        this.cmb_hotel_name.addActionListener(e -> {

            ComboItem selectedHotelItem = (ComboItem) cmb_hotel_name.getSelectedItem();

            int selectedHotelId = selectedHotelItem.getKey();

            ArrayList<Pension> pensions = pensionManager.getPensionByHotelID(selectedHotelId);

            this.cmb_pension_name.removeAllItems();

            for (Pension pension : pensions) {

                this.cmb_pension_name.addItem(pension.getComboItem());

            }

            ArrayList<Season> seasons = seasonManager.getSeasonsByHotelId(selectedHotelId);

            this.cmb_season.removeAllItems();

            for (Season season : seasons) {

                this.cmb_season.addItem(season.getComboItem());

            }
        });

        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.Types.values()));

        btn_save_room.addActionListener(e -> {

            JTextField[] selectedRoomList = new JTextField[]{this.fld_stock, this.fld_adult_price, this.fld_child_price, this.fld_bed_capacity, this.fld_square_meter};

            if (Helper.isFieldListEmpty(selectedRoomList)) {

                Helper.showMsg("Fill");

            } else {

                boolean result = false;

                ComboItem selectedHotel = (ComboItem) cmb_hotel_name.getSelectedItem();

                ComboItem selectedPension = (ComboItem) cmb_pension_name.getSelectedItem();

                ComboItem selectedSeason = (ComboItem) cmb_season.getSelectedItem();

                room.setSeasonId(selectedSeason.getKey());

                room.setPensionId(selectedPension.getKey());

                room.setHotelId(selectedHotel.getKey());

                room.setType(cmb_room_type.getSelectedItem().toString());

                room.setStock(Integer.parseInt(fld_stock.getText()));

                room.setAdultPrice(Double.parseDouble(fld_adult_price.getText()));

                room.setChildPrice(Double.parseDouble(fld_child_price.getText()));

                room.setBedCapacity(Integer.parseInt(fld_bed_capacity.getText()));

                room.setSquareMeter(Integer.parseInt(fld_square_meter.getText()));

                room.setTelevision(rdbtn_tv.isSelected());

                room.setMinibar(rdbtn_minibar.isSelected());

                room.setGameConsole(rdbtn_game_console.isSelected());

                room.setCashBox(rdbtn_cash_box.isSelected());

                room.setProjection(rdbtn_projection.isSelected());

                result = roomManager.save(room);

                if (result) {

                    Helper.showMsg("done");

                    dispose();

                } else {

                    Helper.showMsg("error");

                }
            }
        });
    }
}
