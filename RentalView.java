package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RentalView extends JFrame {
    public JTextField txtId = new JTextField();
    public JTextField txtNama = new JTextField();
    public JTextField txtKontak = new JTextField();
    public JTextField txtJenis = new JTextField();
    public JTextField txtDurasi = new JTextField();
    public JComboBox<String> cbStatus = new JComboBox<>(new String[]{"Lunas", "Belum Lunas"});
    public JButton tambahButton = new JButton("Tambah");
    public JButton updateButton = new JButton("Update");
    public JButton deleteButton = new JButton("Delete");
    public JButton clearButton = new JButton("Clear");
    public JTable table;
    public DefaultTableModel model;

    public RentalView() {
        setTitle("GoCarindo Rental");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(7, 2));
        form.add(new JLabel("ID")); form.add(txtId); txtId.setEditable(false);
        form.add(new JLabel("Nama")); form.add(txtNama);
        form.add(new JLabel("Kontak")); form.add(txtKontak);
        form.add(new JLabel("Jenis Mobil")); form.add(txtJenis);
        form.add(new JLabel("Durasi")); form.add(txtDurasi);
        form.add(new JLabel("Status Pembayaran")); form.add(cbStatus);

        JPanel buttons = new JPanel();
        buttons.add(tambahButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(clearButton);

        model = new DefaultTableModel(new String[]{"ID", "Nama", "Kontak", "Jenis", "Durasi", "Total", "Status"}, 0);
        table = new JTable(model);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }
}
