package view;

import business.PensionManager;
import entity.Pension;
import business.HotelManager;
import entity.Hotel;
import core.ComboItem;
import core.Helper;

import javax.swing.*;

/**
 * @author Nida Başer
 * April 2024
 */

public class PensionView extends Layout {

    private JPanel container;

    private JLabel lbl_add_pension;

    private JLabel lbl_pension_type;

    private JComboBox cmb_pension_type;

    private JButton btn_save_pension;

    private JLabel lbl_hotel_name;

    private JComboBox<ComboItem> cmb_hotels_list;

    private Pension pension;

    private PensionManager pensionManager;

    private HotelManager hotelManager;

    public PensionView(Pension pension) {

        this.pension = pension;

        this.pensionManager = new PensionManager();

        this.hotelManager = new HotelManager();

        this.add(container);

        this.guiInitialize(500, 400);

        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>(Pension.Types.values()));

        for (Hotel hotel : this.hotelManager.findAll()) {

            this.cmb_hotels_list.addItem(hotel.getComboItem());

        }

        btn_save_pension.addActionListener(e -> {

            boolean result;

            ComboItem selectedHotel = (ComboItem) this.cmb_hotels_list.getSelectedItem();

            this.pension.setHotelId(selectedHotel.getKey());

            this.pension.setTypes((Pension.Types) this.cmb_pension_type.getSelectedItem());

            result = this.pensionManager.save(this.pension);

            if (result) {

                Helper.showMsg("Done");

                dispose();

            } else {

                Helper.showMsg("Error");

            }
        });
    }
}
