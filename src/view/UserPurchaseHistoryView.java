package view;

import entity.Holding;
import entity.Portfolio;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryController;

import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class UserPurchaseHistoryView extends JPanel implements ActionListener, PropertyChangeListener {
    public String viewName = "UserPurchaseHistory";
    private UserPurchaseHistoryController controller;
    private UserPurchaseHistoryViewModel viewModel;
    private final JButton searchButton = new JButton("Purchase History");
    private final JButton backButton = new JButton("Back");
    private JComboBox<String> dropdownMenu; // Dropdown menu

    // Table components
    private JTable purchaseHistoryTable;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Date", "Price", "Quantity"};
    private Object[][] data = {}; // Initially no data

    public UserPurchaseHistoryView(UserPurchaseHistoryViewModel viewModel, UserPurchaseHistoryController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);

        // Set layout
        this.setLayout(new BorderLayout());

        // Initialize table
        purchaseHistoryTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(purchaseHistoryTable);


        dropdownMenu = new JComboBox<>();
        dropdownMenu.setPreferredSize(new Dimension(150, 30));
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(searchButton, BorderLayout.CENTER);
        topPanel.add(dropdownMenu, BorderLayout.EAST);

        // Add components to panel
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        this.searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Purchase History button pressed");
                        String ticker = (String) dropdownMenu.getSelectedItem();
                        HashMap<String, String[]> data = controller.getUserHistoryArrays(viewModel.getState().getPortfolio(), ticker);

                        // Prepare data for the table
                        String[] dates = data.get("dates");
                        String[] prices = data.get("prices");
                        String[] quantities = data.get("quantities");

                        Object[][] tableData = new Object[dates.length][3];
                        for (int i = 0; i < dates.length; i++) {
                            tableData[i][0] = dates[i];
                            tableData[i][1] = prices[i];
                            tableData[i][2] = quantities[i];
                        }

                        // Update table model
                        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
                        purchaseHistoryTable.setModel(tableModel);
                    }
                }
        );

        this.backButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Back button pressed");
                        dropdownMenu.removeAllItems();
                        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
                        purchaseHistoryTable.setModel(tableModel);
                        controller.backButtonPressed();
                    }
                }
        );
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Additional actions can be added here if needed
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            ArrayList<String> dropdown = new ArrayList<String>();
            Portfolio portfolio = this.viewModel.getState().getPortfolio();
            for (Holding holding: portfolio.getHoldings()){
                dropdown.add(holding.getStock().getTicker());
            }
            dropdownMenu.removeAllItems(); // Clear existing items
            for (String item : dropdown) {
                dropdownMenu.addItem(item); // Add new items
            }
        }
        // Handle other property changes to update the table if needed
    }
}
