package view;

import interface_adapter.sell.SellController;
import interface_adapter.sell.SellViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SellView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sell";
    private SellViewModel viewModel;
    private final SellController controller;

    private JComboBox stockComboBox;
    private JTextField quantityField;
    private JButton sellButton;

    public SellView(SellViewModel viewModel, SellController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.initializeUI();
        this.viewModel.addPropertyChangeListener(this);
    }

    private void initializeUI() {
        setSize(50, 100);
        setLayout(new BorderLayout());
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        // Stock selection
        JLabel stockLabel = new JLabel(this.viewModel.TITLE_LABEL);
//        stockComboBox = new JComboBox<>(new String[]{"AAPL", "GOOGL", "MSFT"}); // TODO implement
        this.stockComboBox = new JComboBox<>(new String[]{""});

        // Quantity input
        JLabel quantityLabel = new JLabel(this.viewModel.QUANTITY_LABEL);
        this.quantityField = new JTextField();

        // Sell button
        this.sellButton = new JButton(this.viewModel.EXECUTE_LABEL);
        this.sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //executeSellOrder();
                System.out.println("Sell " + quantityField.getText());
            }
        });

        // Add components to the panel
        mainPanel.add(stockLabel);
        mainPanel.add(stockComboBox);
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel()); // empty cell
        mainPanel.add(sellButton);

        // Add main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")){
            ArrayList<String> tickers = controller.getHeldStocks(this.viewModel.getState().getUsername());
            String[] tickersArray = tickers.toArray(new String[0]);
            this.stockComboBox.setModel(new DefaultComboBoxModel<String>(tickersArray));
        }
    }

//    private void executeSellOrder() {
//        String selectedStock = (String) stockComboBox.getSelectedItem();
//        int quantity;
//        try {
//            quantity = Integer.parseInt(quantityField.getText());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid quantity", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        // Execute the sell order using SellInteractor
//        SellInteractor sellInteractor = new SellInteractor();
//        sellInteractor.execute(selectedStock, quantity);
//    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new SellView().setVisible(true));
//    }
}
