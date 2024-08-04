package view;

import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryController;
import interface_adapter.UserPurchaseHistory.UserPurchaseHistoryViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserPurchaseHistoryView extends JPanel implements ActionListener, PropertyChangeListener {
    private UserPurchaseHistoryController controller;
    private UserPurchaseHistoryViewModel viewModel;
    private final JButton searchButton = new JButton("Purchase History");


    public UserPurchaseHistoryView(UserPurchaseHistoryViewModel viewModel, UserPurchaseHistoryController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.viewModel.addPropertyChangeListener(this);

        this.searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Purchase History button pressed");
                        controller.getUserHistoryArrays(viewModel.getState().getPortfolio());
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

    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }



}
