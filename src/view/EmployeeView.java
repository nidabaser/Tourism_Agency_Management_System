package view;

import core.ComboItem;
import core.Helper;
import entity.*;
import business.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Nida Başer
 * April 2024
 */

public class EmployeeView extends Layout {

    private JPanel container;

    private JLabel lbl_welcome;

    private JTabbedPane pnl_top;

    private JTable tbl_hotel;

    private JButton btn_exit;

    private JTable tbl_pension;

    private JTable tbl_season;

    private JTable tbl_room;

    private JTable tbl_reservation;

    private JButton btn_search_room;

    private JScrollPane scroll_hotel;

    private JScrollPane scroll_pension;

    private JScrollPane scroll_season;

    private JLabel lbl_hotel_table;

    private JLabel lbl_pension_type_table;

    private JLabel lbl_season_table;

    private JScrollPane scroll_room;

    private JScrollPane scroll_reservation;

    private JLabel lbl_search_room;

    private JLabel lbl_list_of_rooms;

    private JButton btn_clear;

    private JTextField fld_search_room_by_start_date;

    private JTextField fld_search_room_by_finish_date;

    private JTextField fld_adult_number;

    private JTextField fld_child_number;

    private JTextField fld_search_room_by_hotel;

    private JTextField fld_search_room_by_city;

    private User user;

    private DefaultTableModel tableModel_hotel;

    private HotelManager hotelManager;

    private JPopupMenu hotel_menu;

    private Object[] col_hotel;

    private DefaultTableModel tableModel_pension;

    private PensionManager pensionManager;

    private JPopupMenu pension_menu;

    private Object[] col_pension;

    private DefaultTableModel tableModel_season;

    private SeasonManager seasonManager;

    private JPopupMenu season_menu;

    private Object[] col_season;

    private DefaultTableModel tableModel_room;

    private RoomManager roomManager;

    private JPopupMenu room_menu;

    private Object[] col_room;

    private DefaultTableModel tableModel_reservation;

    private ReservationManager reservationManager;

    private JPopupMenu reservation_menu;

    private Object[] col_reservation;

    public EmployeeView(User user) {

        this.user = user;

        this.hotelManager = new HotelManager();

        this.tableModel_hotel = new DefaultTableModel();

        this.pensionManager = new PensionManager();

        this.tableModel_pension = new DefaultTableModel();

        this.seasonManager = new SeasonManager();

        this.tableModel_season = new DefaultTableModel();

        this.roomManager = new RoomManager();

        this.tableModel_room = new DefaultTableModel();

        this.reservationManager = new ReservationManager();

        this.tableModel_reservation = new DefaultTableModel();

        this.add(container);

        this.guiInitialize(1000, 500);

        if (this.user == null) {
            dispose();
        }

        this.lbl_welcome.setText(" WELCOME EMPLOYEE " + this.user.getUsername().toUpperCase());

        // General Code
        loadComponent();

        // Hotel Tab Menu
        loadHotelTable();

        loadHotelComponent();

        // Pensions table in Hotel Tab Menu
        loadPensionTable();

        loadPensionComponent();

        // Seasons table in Hotel Tab Menu
        loadSeasonTable();

        loadSeasonComponent();

        // Room Tab Menu
        loadRoomTable(null);

        loadRoomComponent();

        // Reservation Tab menu
        loadReservationTable(null);

        loadReservationComponent();

    }

    public void loadComponent() {

        btn_exit.addActionListener(e -> {

            LoginView loginView = new LoginView();

            dispose();

        });
    }

    public void loadHotelTable() {

        col_hotel = new Object[]{"ID", "HOTEL NAME", "CITY", "REGION", "ADDRESS", "EMAIL", "PHONE", "STAR", "PARKING LOT", "WIFI", "POOL", "FITNESS/GYM", "CONCIERGE", "SPA", "7/24 ROOM SERVICE"};

        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());

        createTable(this.tableModel_hotel, this.tbl_hotel, col_hotel, hotelList);

    }

    public void loadPensionTable() {

        col_pension = new Object[]{"ID", "HOTEL ID ", "PENSION TYPE"};

        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());

        createTable(this.tableModel_pension, this.tbl_pension, col_pension, pensionList);

    }

    public void loadSeasonTable() {

        col_season = new Object[]{"ID", "HOTEL ID", "START DATE", "FINISH DATE"};

        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());

        createTable(this.tableModel_season, this.tbl_season, col_season, seasonList);

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {

        this.col_room = new Object[]{"ID", "Hotel", "Pension", "Season", "Room Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "TV", "Minibar", "Game Console", "Cash Box", "Projection"};

        if (roomList == null) {

            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());

        }

        createTable(tableModel_room, this.tbl_room, col_room, roomList);

    }

    public void loadReservationTable(ArrayList<Object[]> reservationList) {

        this.col_reservation = new Object[]{"ID", "ROOM ID", "Check In Date", "Check Out Date", "Total Price", "Guest Count", "Guest Name", "Citizen ID", "Mail", "Phone"};

        if (reservationList == null) {

            reservationList = this.reservationManager.getForTable(this.col_reservation.length, this.reservationManager.findAll());

        }

        createTable(tableModel_reservation, this.tbl_reservation, col_reservation, reservationList);

    }

    public void loadHotelComponent() {

        // ADD, UPDATE, DELETE HOTEL

        tableRowSelect(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();

        this.hotel_menu.add("Add New Hotel").addActionListener(e -> {

            HotelSaveView hotelSaveView = new HotelSaveView(new Hotel());

            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadHotelTable();

                }
            });
        });

        this.hotel_menu.add("Update Hotel").addActionListener(e -> {

            int selectedHotelId = this.getTableSelectedRow(tbl_hotel, 0);

            HotelSaveView hotelSaveView = new HotelSaveView(this.hotelManager.getById(selectedHotelId));

            hotelSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadHotelTable();

                    loadRoomTable(null);

                }
            });
        });

        this.hotel_menu.add("Delete Hotel").addActionListener(e -> {

            if (Helper.confirm("sure")) {

                int selectedHotelId = this.getTableSelectedRow(tbl_hotel, 0);

                if (this.hotelManager.delete(selectedHotelId)) {

                    Helper.showMsg("Done");

                    loadHotelTable();

                    loadPensionTable();


                    loadSeasonTable();

                    loadRoomTable(null);

                } else {

                    Helper.showMsg("Error");

                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(this.hotel_menu);

    }

    public void loadPensionComponent() {

        // ADD PENSION

        tableRowSelect(this.tbl_pension);

        this.pension_menu = new JPopupMenu();

        this.pension_menu.add("Add New Pension").addActionListener(e -> {

            PensionView pensionView = new PensionView(new Pension());

            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadPensionTable();

                }
            });
        });

        this.tbl_pension.setComponentPopupMenu(this.pension_menu);

    }

    public void loadSeasonComponent() {

        // ADD SEASON

        tableRowSelect(this.tbl_season);

        this.season_menu = new JPopupMenu();

        this.season_menu.add("Add New Season").addActionListener(e -> {

            SeasonView seasonView = new SeasonView(new Season());

            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });

        });

        this.tbl_season.setComponentPopupMenu(this.season_menu);

    }

    public void loadRoomComponent() {

        // ADD, DELETE, SEARCH ROOM

        tableRowSelect(this.tbl_room);

        this.room_menu = new JPopupMenu();

        this.room_menu.add("Add New Room").addActionListener(e -> {

            RoomSaveView roomSaveView = new RoomSaveView(new Room());

            roomSaveView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadRoomTable(null);

                }
            });
        });

        this.room_menu.add("Delete Room").addActionListener(e -> {

            if (Helper.confirm("Sure")) {

                int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);

                if (this.roomManager.delete(selectedRoomId)) {

                    Helper.showMsg("Done");

                    loadRoomTable(null);

                    loadReservationTable(null);

                } else {

                    Helper.showMsg("Error");

                }
            }
        });

        // ROOM SEARCH

        btn_search_room.addActionListener(e -> {

            JTextField[] roomJTextField = new JTextField[]{fld_adult_number, fld_child_number};

            if (Helper.isFieldListEmpty(roomJTextField)) {

                Helper.showMsg("Please enter the number of adults and children");

            } else {

                int selectedAdult = Integer.parseInt(fld_adult_number.getText());

                int selectedChild = Integer.parseInt(fld_child_number.getText());

                if (selectedAdult < 0 || selectedChild < 0) {

                    Helper.showMsg("Please enter the number of children and adults greater than 0");

                }

                ArrayList<Room> roomList = roomManager.searchForTable(

                        fld_search_room_by_hotel.getText(),

                        fld_search_room_by_city.getText(),

                        fld_search_room_by_start_date.getText(),

                        fld_search_room_by_finish_date.getText(),

                        fld_adult_number.getText(),

                        fld_child_number.getText()

                );

                ArrayList<Object[]> searchResult = roomManager.getForTable(col_room.length, roomList);

                loadRoomTable(searchResult);

            }
        });

        // MAKE RESERVATION FOR SELECTED ROOM

        this.room_menu.add("Make Reservation for this room").addActionListener(e -> {

            int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);

            JTextField[] roomJTextField = new JTextField[]{fld_search_room_by_start_date, fld_search_room_by_finish_date, fld_adult_number, fld_child_number};

            if (Helper.isFieldListEmpty(roomJTextField)) {

                Helper.showMsg("Please fill search filters");

            } else {

                int adult_number = Integer.parseInt(this.fld_adult_number.getText());

                int child_number = Integer.parseInt(this.fld_child_number.getText());


                ReservationView reservationView = new ReservationView(this.roomManager.getById(selectedRoomId), this.fld_search_room_by_start_date.getText(), fld_search_room_by_finish_date.getText(), adult_number, child_number, null);

                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {

                        loadRoomTable(null);

                        loadReservationTable(null);

                    }
                });
            }
        });

        this.tbl_room.setComponentPopupMenu(this.room_menu);

        // CLEAR SEARCH

        this.btn_clear.addActionListener(e -> {

            this.fld_search_room_by_hotel.setText(null);

            this.fld_search_room_by_city.setText(null);

            this.fld_search_room_by_start_date.setText("01/06/2024");

            this.fld_search_room_by_finish_date.setText("15/06/2024");

            this.fld_adult_number.setText(null);

            this.fld_child_number.setText(null);

            loadRoomTable(null);

        });
    }

    public void loadReservationComponent() {

        // LIST, UPDATE, DELETE RESERVATION

        tableRowSelect(this.tbl_reservation);

        this.reservation_menu = new JPopupMenu();

        reservation_menu.add("Update Reservation").addActionListener(e -> {

            int selectedReservationId = this.getTableSelectedRow(tbl_reservation, 0);

            Reservation selectedReservation = this.reservationManager.getById(selectedReservationId);

            int selectRoomId = selectedReservation.getRoomId();

            Room selectedRoom = this.roomManager.getById(selectRoomId);

            UpdateReservationView updateReservationView = new UpdateReservationView(selectedRoom,

                    selectedReservation.getCheckInDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),

                    selectedReservation.getCheckOutDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),

                    selectedReservation.getAdultCount(), selectedReservation.getChildCount(),

                    selectedReservation);

            updateReservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadReservationTable(null);

                }
            });
        });

        reservation_menu.add("Delete").addActionListener(e -> {

            if (Helper.confirm("sure")) {

                int selectResId = this.getTableSelectedRow(tbl_reservation, 0);

                int selectRoomId = this.reservationManager.getById(selectResId).getRoomId();

                Room selectedRoom = this.roomManager.getById(selectRoomId);

                selectedRoom.setStock(selectedRoom.getStock() + 1); //AFTER DELETE RESERVATION ROOM STOCK INCREASE

                this.roomManager.updateStock(selectedRoom);

                if (this.reservationManager.delete(selectResId)) {

                    Helper.showMsg("done");

                    loadRoomTable(null);

                    loadReservationTable(null);

                } else {

                    Helper.showMsg("error");

                }
            }
        });

        this.tbl_reservation.setComponentPopupMenu(this.reservation_menu);

    }

    private void createUIComponents() throws ParseException {

        // TODO: place custom component creation code here

        this.fld_search_room_by_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));

        this.fld_search_room_by_start_date.setText("01/06/2024");

        this.fld_search_room_by_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));

        this.fld_search_room_by_finish_date.setText("15/06/2024");

    }

}