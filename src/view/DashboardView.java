package view;

import entity.Portfolio;
import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardViewModel;
import use_case.dashboard.DashboardOutputData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DashboardView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "dashboard";
    private final JButton buyButton;
    private final JButton sellButton;
    private final JButton transactionHistoryButton;  // New transaction history button
    private final JButton logoutButton;  // Logout button
    private JTable dashboardTable;
    private DashboardViewModel viewModel;
    private DashboardController controller;
    private JLabel portfolioValueLabel;
    private JLabel welcomeLabel;
    private JLabel cashLabel;
    private JLabel profitLabel;

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
        topPanel.setLayout(new GridLayout(4, 1));  // Changed to 4 rows
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        welcomeLabel = new JLabel(this.viewModel.WELCOME_LABEL);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(welcomeLabel);

        portfolioValueLabel = new JLabel(this.viewModel.PORTFOLIO_LABEL);
        portfolioValueLabel.setForeground(Color.WHITE);
        portfolioValueLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(portfolioValueLabel);

        // New labels for cash and profit
        cashLabel = new JLabel("Cash: $0.00");
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(cashLabel);

        profitLabel = new JLabel("Profit: $0.00");
        profitLabel.setForeground(Color.WHITE);
        profitLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        topPanel.add(profitLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table panel
        String[] columnNames = {"Name", "Ticker", "Qty", "Current Price"};
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
        rightPanel.setLayout(new GridLayout(7, 1, 0, 10));  // Increased rows to 7 for the new button
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
                        DashboardOutputData dashboardOutputData = new DashboardOutputData(viewModel.getState().getUsername(), viewModel.getState().getPortfolio());
                        controller.buyButtonClicked(dashboardOutputData);
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
                        DashboardOutputData dashboardOutputData = new DashboardOutputData(viewModel.getState().getUsername(), viewModel.getState().getPortfolio());
                        controller.sellButtonClicked(dashboardOutputData);
                    }
                }
        );

        // Create the transaction history button and add it to the right panel
        transactionHistoryButton = createButton("Transaction History", new Color(70, 130, 180), "View transaction history");
        rightPanel.add(transactionHistoryButton);

        transactionHistoryButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Transaction History button pressed");
                        controller.transactionHistoryPressed(viewModel.getState().getPortfolio());
                    }
                }
        );

        // Create the logout button and add it to the right panel
        logoutButton = createButton("Logout", new Color(128, 0, 0), "Logout from the application");
        rightPanel.add(logoutButton);

        logoutButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.logoutPressed();
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
        if (evt.getPropertyName().equals("UserLoggedIn")) {
            String[] columnNames = {"Name", "Ticker", "Qty", "Current Price"};

            Portfolio portfolio;
            if (this.viewModel.getState().getPortfolio() == null) {
                portfolio = controller.getUserPortfolio(this.viewModel.getState().getUsername());
                this.viewModel.getState().setPortfolio(portfolio);
                Object[][] data = controller.getUserPortfolioArrays(portfolio);
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
                dashboardTable.setModel(tableModel);
            } else {
                portfolio = this.viewModel.getState().getPortfolio();
                Object[][] data = controller.getUserPortfolioArrays(portfolio);
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
                dashboardTable.setModel(tableModel);
            }
            this.portfolioValueLabel.setText(this.viewModel.PORTFOLIO_LABEL + portfolio.getPortfolioValue());
            this.welcomeLabel.setText(this.viewModel.WELCOME_LABEL + this.viewModel.getState().getUsername());

            // Update cash and profit labels
            this.cashLabel.setText("Cash: $" + portfolio.getCash());
            this.profitLabel.setText("Profit: $" + portfolio.getPnL());
        }

        if (evt.getPropertyName().equals("UserLogout")) {
            String[] columnNames = {"Name", "Ticker", "Qty", "Current Price"};
            Object[][] data = {};
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            dashboardTable.setModel(tableModel);

            // Reset cash and profit labels
            this.cashLabel.setText("Cash: $0.00");
            this.profitLabel.setText("Profit: $0.00");
            this.portfolioValueLabel.setText("Portfolio Value: $0.00");
        }
    }
}
