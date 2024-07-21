package view;

import data_access.StockQuantityDataAccessObject;
import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.dashboard.DashboardInputData;
import use_case.dashboard.DashboardInteractor;
import use_case.dashboard.DashboardOutputData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "dashboard";
    private final JButton buyButton;
    private final JButton sellButton;
    private JTable dashboardTable;
    private DashboardViewModel viewModel;
    private DashboardController controller;

    public DashboardView(DashboardViewModel viewModel, DashboardController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);

        // Constructing the UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Set up the frame
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);


        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.DARK_GRAY);


        // Top panel for user information
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel(this.viewModel.WELCOME_LABEL);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(welcomeLabel);

        JLabel portfolioValueLabel = new JLabel(this.viewModel.PORTFOLIO_LABEL);
        portfolioValueLabel.setForeground(Color.WHITE);
        portfolioValueLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(portfolioValueLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table panel
        String[] columnNames = {"Name", "Ticker", "Qty", "Cost Basis", "Current Price", "Gain ($)", "Gain (%)"};
        Object[][] data = {{}};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        this.dashboardTable = new JTable(tableModel);
        this.dashboardTable.setFillsViewportHeight(true);
        this.dashboardTable.setRowHeight(30);
        // Custom cell renderer for table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < this.dashboardTable.getColumnCount(); i++) {
            this.dashboardTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        JScrollPane scrollPane = new JScrollPane(this.dashboardTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);



        // Right panel for market status and buttons
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1, 0, 10));
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setPreferredSize(new Dimension(250, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel marketStatusLabel = new JLabel("Market is OPEN");
        marketStatusLabel.setForeground(Color.WHITE);
        marketStatusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        marketStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(marketStatusLabel);

        // Get current date and time
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        JLabel dateTimeLabel = new JLabel("Date: " + formattedDate);
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(dateTimeLabel);

        buyButton = createButton(this.viewModel.BUY_BUTTON_LABEL, new Color(34, 139, 34), "Buy stocks");
        rightPanel.add(buyButton);

        buyButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Buy button pressed");
                    }
                }
        );

        sellButton = createButton(this.viewModel.SELL_BUTTON_LABEL, new Color(220, 20, 60), "Sell stocks");
        rightPanel.add(sellButton);

        sellButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Sell button pressed");
                        DashboardOutputData dashboardOutputData = new DashboardOutputData(viewModel.getState().getUsername());
                        controller.sellButtonClicked(dashboardOutputData);
                    }
                }
        );

        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private JButton createButton(String text, Color backgroundColor, String toolTip) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setToolTipText(toolTip);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("UserLoggedIn")){
            String[] columnNames = {"Name", "Ticker", "Qty", "Cost Basis", "Current Price", "Gain ($)", "Gain (%)"};
            Object[][] data = controller.getUserPortfolioArrays(this.viewModel.getState().getUsername());
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            dashboardTable.setModel(tableModel);
        }

    }
}
