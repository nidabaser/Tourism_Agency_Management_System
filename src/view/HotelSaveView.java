package view;

import business.HotelManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

/**
 * @author Nida Başer
 * April 2024
 */

public class HotelSaveView extends Layout {

    private JPanel container;

    private JLabel lbl_save_hotel;

    private JTextField fld_hotel_name;

    private JTextField fld_hotel_city;

    private JTextField fld_hotel_region;

    private JTextField fld_hotel_address;

    private JTextField fld_hotel_mail;

    private JTextField fld_hotel_phone;

    private JComboBox cmb_stars;

    private JRadioButton rdbtn_parking_lot;

    private JRadioButton rdbtn_wifi;

    private JRadioButton rdbtn_pool;

    private JRadioButton rdbtn_fitness;

    private JRadioButton rdbtn_concierge;

    private JRadioButton rdbtn_spa;

    private JRadioButton rdbtn_room_service;

    private JButton btn_save;

    private Hotel hotel;

    private HotelManager hotelManager;

    public HotelSaveView(Hotel hotel) {

        this.hotel = hotel;

        this.hotelManager = new HotelManager();

        this.add(container);

        this.guiInitialize(600, 500);

        this.cmb_stars.setModel(new DefaultComboBoxModel<>(Hotel.Star.values()));

        if (this.hotel.getId() != 0) {

            this.cmb_stars.getModel().setSelectedItem(hotel.getStar());

            this.fld_hotel_name.setText(hotel.getName());

            this.fld_hotel_city.setText(hotel.getCity());

            this.fld_hotel_region.setText(hotel.getRegion());

            this.fld_hotel_address.setText(hotel.getAddress());

            this.fld_hotel_mail.setText(hotel.getMail());

            this.fld_hotel_phone.setText(hotel.getPhone());

            this.rdbtn_parking_lot.setSelected(hotel.isCarPark());

            this.rdbtn_wifi.setSelected(hotel.isWifi());

            this.rdbtn_pool.setSelected(hotel.isPool());

            this.rdbtn_fitness.setSelected(hotel.isFitness());

            this.rdbtn_concierge.setSelected(hotel.isConcierge());

            this.rdbtn_spa.setSelected(hotel.isSpa());

            this.rdbtn_room_service.setSelected(hotel.isRoomService());

        }

        this.btn_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_name, this.fld_hotel_city, this.fld_hotel_region, this.fld_hotel_address, this.fld_hotel_mail, this.fld_hotel_phone})) {

                Helper.showMsg("Fill all fields");

            } else {

                boolean result;

                this.hotel.setName(this.fld_hotel_name.getText());

                this.hotel.setCity(this.fld_hotel_city.getText());

                this.hotel.setRegion(this.fld_hotel_region.getText());

                this.hotel.setAddress(this.fld_hotel_address.getText());

                this.hotel.setMail(this.fld_hotel_mail.getText());

                this.hotel.setPhone(this.fld_hotel_phone.getText());

                this.hotel.setStar((Hotel.Star) this.cmb_stars.getSelectedItem());

                this.hotel.setCarPark(this.rdbtn_parking_lot.isSelected());

                this.hotel.setWifi(this.rdbtn_wifi.isSelected());

                this.hotel.setPool(this.rdbtn_pool.isSelected());

                this.hotel.setFitness(this.rdbtn_fitness.isSelected());

                this.hotel.setConcierge(this.rdbtn_concierge.isSelected());

                this.hotel.setSpa(this.rdbtn_spa.isSelected());

                this.hotel.setRoomService(this.rdbtn_room_service.isSelected());

                if (this.hotel.getId() != 0) {

                    result = this.hotelManager.update(this.hotel);

                } else {

                    result = this.hotelManager.save(this.hotel);

                }

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
