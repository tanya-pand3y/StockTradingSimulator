package view;

import interface_adapter.dashboard.DashboardController;
import interface_adapter.dashboard.DashboardViewModel;

import javax.swing.*;

public class DashboardView {
    private final DashboardViewModel viewModel;
    private JButton buy;
    private JButton sell;

    public DashboardView(DashboardViewModel viewModel) {
        this.viewModel = viewModel;
    }

}
