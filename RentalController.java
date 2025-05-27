package controller;

import model.RentalModel;
import view.RentalView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.Vector;

public class RentalController {
    RentalModel model;
    RentalView view;

    public RentalController(RentalModel model, RentalView view) {
        this.model = model;
        this.view = view;
        refreshTable();

        view.tambahButton.addActionListener(e -> {
            try {
                model.tambahData(view.txtNama.getText(), view.txtKontak.getText(),
                        view.txtJenis.getText(), Integer.parseInt(view.txtDurasi.getText()),
                        view.cbStatus.getSelectedItem().toString());
                refreshTable();
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Input tidak valid");
            }
        });

        view.updateButton.addActionListener(e -> {
            try {
                model.updateData(Integer.parseInt(view.txtId.getText()), view.txtNama.getText(),
                        view.txtKontak.getText(), view.txtJenis.getText(),
                        Integer.parseInt(view.txtDurasi.getText()),
                        view.cbStatus.getSelectedItem().toString());
                refreshTable();
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Pilih data untuk diupdate");
            }
        });

        view.deleteButton.addActionListener(e -> {
            try {
                model.deleteData(Integer.parseInt(view.txtId.getText()));
                refreshTable();
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Pilih data untuk dihapus");
            }
        });

        view.clearButton.addActionListener(e -> clearForm());

        view.table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int i = view.table.getSelectedRow();
                if (i >= 0) {
                    view.txtId.setText(view.model.getValueAt(i, 0).toString());
                    view.txtNama.setText(view.model.getValueAt(i, 1).toString());
                    view.txtKontak.setText(view.model.getValueAt(i, 2).toString());
                    view.txtJenis.setText(view.model.getValueAt(i, 3).toString());
                    view.txtDurasi.setText(view.model.getValueAt(i, 4).toString());
                    view.cbStatus.setSelectedItem(view.model.getValueAt(i, 6).toString());
                }
            }
        });
    }

    void refreshTable() {
        view.model.setRowCount(0);
        for (Vector<Object> row : model.getAllData()) {
            view.model.addRow(row);
        }
    }

    void clearForm() {
        view.txtId.setText("");
        view.txtNama.setText("");
        view.txtKontak.setText("");
        view.txtJenis.setText("");
        view.txtDurasi.setText("");
        view.cbStatus.setSelectedIndex(0);
    }
}
