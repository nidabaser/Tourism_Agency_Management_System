package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Nida Başer
 * April 2024
 */

public class SeasonView extends Layout {

    private JPanel container;

    private JLabel lbl_season_save;

    private JLabel lbl_hotel_name;

    private JButton btn_season_save;

    private JComboBox<ComboItem> cmb_hotel_name;

    private JFormattedTextField fld_start_date;

    private JFormattedTextField fld_finish_date;

    private JLabel lbl_start_date;

    private JLabel lbl_finish_date;

    private Season season;

    private SeasonManager seasonManager;

    private HotelManager hotelManager;

    public SeasonView(Season season) {

        this.season = season;

        this.seasonManager = new SeasonManager();

        this.hotelManager = new HotelManager();

        this.add(container);

        this.guiInitialize(300, 400);

        for (Hotel hotel : this.hotelManager.findAll()) {

            this.cmb_hotel_name.addItem(hotel.getComboItem());

        }

        btn_season_save.addActionListener(e -> {

            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_start_date, this.fld_finish_date})) {

                Helper.showMsg("Fill");

            } else {

                boolean result;

                ComboItem selectedHotel = (ComboItem) this.cmb_hotel_name.getSelectedItem();

                this.season.setHotelId(selectedHotel.getKey());

                this.season.setStartDate(LocalDate.parse(this.fld_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                this.season.setFinishDate(LocalDate.parse(this.fld_finish_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                result = this.seasonManager.save(this.season);

                if (result) {

                    Helper.showMsg("Done");

                    dispose();

                } else {

                    Helper.showMsg("Error");

                }
            }
        });
    }

    private void createUIComponents() throws ParseException {

        // TODO: place custom component creation code here

        this.fld_start_date = new JFormattedTextField(new MaskFormatter("##/##/####"));

        this.fld_start_date.setText("01/01/2024");

        this.fld_finish_date = new JFormattedTextField(new MaskFormatter("##/##/####"));

        this.fld_finish_date.setText("01/12/2024");

    }
}
