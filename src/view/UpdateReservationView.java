package view;

import business.ReservationManager;
import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author Nida Başer
 * April 2024
 */

public class UpdateReservationView extends Layout {

    private JPanel container;

    private JPanel pnl_top;

    private JPanel pnl_middle;

    private JPanel pnl_bottom;

    private JLabel lbl_hotel_info;

    private JTextField fld_hotel_name;

    private JTextField fld_city_name;

    private JLabel lbl_city;

    private JLabel lbl_hotel_name;

    private JTextField fld_region_name;

    private JLabel lbl_region;

    private JTextField fld_stars;

    private JLabel lbl_stars;

    private JRadioButton rdbtn_car_park;

    private JRadioButton rdbtn_wifi;

    private JRadioButton rdbtn_pool;

    private JRadioButton rdbtn_gym;

    private JRadioButton rdbtn_concierge;

    private JRadioButton rdbtn_spa;

    private JRadioButton rdbtn_room_service;

    private JLabel lbl_room_info;

    private JLabel lbl_room_type;

    private JTextField fld_room_type;

    private JLabel lbl_pension_type;

    private JTextField fld_pension_type;

    private JLabel lbl_start_date;

    private JTextField fld_start_date;

    private JLabel lbl_finish_date;

    private JTextField fld_finish_date;

    private JLabel lbl_total_amount;

    private JTextField fld_total_amount;

    private JLabel lbl_bed_capacity;

    private JTextField fld_bed_capacity;

    private JLabel lbl_room_area;

    private JTextField fld_room_area_sqmt;

    private JRadioButton rdbtn_tv;

    private JRadioButton rdbtn_minibar;

    private JRadioButton rdbtn_game_console;

    private JRadioButton rdbtn_cash_box;

    private JRadioButton rdbtn_projection;

    private JLabel lbl_guest_info;

    private JLabel lbl_guest_number;

    private JTextField fld_total_guest_number;

    private JTextField fld_guest_mail;

    private JTextField fld_guest_name;

    private JTextField fld_guest_phone;

    private JLabel lbl_guest_ID_no;

    private JTextField fld_guest_ID_no;

    private JLabel lbl_guest_mail;

    private JLabel lbl_guest_name;

    private JLabel lbl_guest_phone;

    private JButton btn_update_reservation;

    private JLabel fld_day_count;

    private JTextField fld_total_days;

    private JLabel lbl_reservation;

    private Reservation reservation;

    private ReservationManager reservationManager;

    private Hotel hotel;

    private HotelManager hotelManager;

    private Room room;

    private RoomManager roomManager;

    private Season season;

    private SeasonManager seasonManager;

    private Pension pension;

    private PensionManager pensionManager;

    private String check_in_date;

    private String check_out_date;

    public UpdateReservationView(Room room, String check_in_date, String check_out_date, int adult_number, int child_number, Reservation reservation) {

        this.add(container);

        this.guiInitialize(1000, 750);

        this.reservation = reservation;

        this.roomManager = new RoomManager();

        this.hotelManager = new HotelManager();

        this.pensionManager = new PensionManager();

        this.seasonManager = new SeasonManager();

        this.reservationManager = new ReservationManager();

        this.room = room;

        this.check_in_date = check_in_date;

        this.check_out_date = check_out_date;

        if (this.reservation == null) {

            this.reservation = this.reservationManager.getById(reservation.getId());

            this.roomManager = new RoomManager();

        }

        // Fill Hotel Info

        this.fld_hotel_name.setText(this.room.getHotel().getName());

        this.fld_city_name.setText(this.room.getHotel().getCity());

        this.fld_region_name.setText(this.room.getHotel().getRegion());

        this.fld_stars.setText(this.room.getHotel().getStar().toString());

        this.rdbtn_car_park.setSelected(this.room.getHotel().isCarPark());

        this.rdbtn_wifi.setSelected(this.room.getHotel().isWifi());

        this.rdbtn_pool.setSelected(this.room.getHotel().isPool());

        this.rdbtn_gym.setSelected(this.room.getHotel().isFitness());

        this.rdbtn_spa.setSelected(this.room.getHotel().isSpa());

        this.rdbtn_concierge.setSelected(this.room.getHotel().isConcierge());

        this.rdbtn_room_service.setSelected(this.room.getHotel().isRoomService());

        // Fill Room Info

        this.fld_room_type.setText(this.room.getType());

        this.fld_pension_type.setText(this.pensionManager.getByTypeName(room.getPensionId()));

        this.fld_start_date.setText(String.valueOf(this.check_in_date));

        this.fld_finish_date.setText(String.valueOf(this.check_out_date));

        this.fld_bed_capacity.setText(String.valueOf(this.room.getBedCapacity()));

        this.fld_room_area_sqmt.setText(String.valueOf(this.room.getSquareMeter()));

        this.rdbtn_tv.setSelected(this.room.isTelevision());

        this.rdbtn_game_console.setSelected(this.room.isGameConsole());

        this.rdbtn_cash_box.setSelected(this.room.isCashBox());

        this.rdbtn_projection.setSelected(this.room.isProjection());

        this.rdbtn_minibar.setSelected(this.room.isMinibar());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate checkindate = LocalDate.parse(check_in_date, formatter);

        LocalDate checkoutdate = LocalDate.parse(check_out_date, formatter);

        long total_days = ChronoUnit.DAYS.between(checkindate, checkoutdate);

        this.fld_total_days.setText(String.valueOf(total_days));

        this.fld_total_amount.setText(String.valueOf(reservation.getTotalPrice()));

        this.fld_total_guest_number.setText(String.valueOf(reservation.getGuestCount()));

        if (this.reservation != null) {

            this.fld_guest_name.setText(String.valueOf(this.reservation.getGuestName()));

            this.fld_guest_ID_no.setText(String.valueOf(this.reservation.getGuestCitizenId()));

            this.fld_guest_mail.setText(String.valueOf(this.reservation.getGuestMail()));

            this.fld_guest_phone.setText(String.valueOf(this.reservation.getGuestPhone()));

        }

        btn_update_reservation.addActionListener(e -> {

            JTextField[] checkFieldEmpty = {this.fld_guest_name, this.fld_guest_ID_no, this.fld_guest_mail, this.fld_guest_phone};

            if (Helper.isFieldListEmpty(checkFieldEmpty)) {

                Helper.showMsg("Fill");

            } else {

                boolean result;

                this.reservation.setGuestName(this.fld_guest_name.getText());

                this.reservation.setGuestCitizenId(this.fld_guest_ID_no.getText());

                this.reservation.setGuestMail(this.fld_guest_mail.getText());

                this.reservation.setGuestPhone(this.fld_guest_phone.getText());

                if (this.reservation.getId() != 0) {

                    result = this.reservationManager.update(this.reservation);

                } else {

                    result = false;

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