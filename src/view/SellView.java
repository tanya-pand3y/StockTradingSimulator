package view;

import interface_adapter.sell.SellController;
import interface_adapter.sell.SellViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellView extends JFrame {
    public final String viewName = "sell";
    private SellViewModel viewModel;
    private final SellController controller;

    private JComboBox<String> stockComboBox;
    private JTextField quantityField;
    private JButton sellButton;

    public SellView(SellViewModel viewModel, SellController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sell menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        // Stock selection
        JLabel stockLabel = new JLabel(this.viewModel.TITLE_LABEL);
        System.out.println(controller.getHeldStocks());
//        stockComboBox = new JComboBox<>(new String[]{"AAPL", "GOOGL", "MSFT"}); // TODO implement
        stockComboBox = new JComboBox<>(controller.getHeldStocks());

        // Quantity input
        JLabel quantityLabel = new JLabel(this.viewModel.QUANTITY_LABEL);
        quantityField = new JTextField();

        // Sell button
        sellButton = new JButton(this.viewModel.EXECUTE_LABEL);
        sellButton.addActionListener(new ActionListener() {
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
