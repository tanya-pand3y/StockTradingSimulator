package use_case.sell;

import entity.Stock;

public class SellInteractor implements SellInputBoundary {
    final SellOutputBoundary sellPresenter;

    public SellInteractor(SellOutputBoundary sellOutputBoundary) {
        this.sellPresenter = sellOutputBoundary;
    }

    public void execute(SellInputData sellInputData) {
        if (sellInputData.getQuantity() > sellInputData.getQuantityHeld()) {
            sellPresenter.prepareFailView("Insufficient quantity in account");
        } else if (sellInputData.getQuantity() == sellInputData.getQuantityHeld()) {
            // TODO use the sheet DAO to remove the holding from the sheet
            SellOutputData sellOutputData = new SellOutputData();
            sellInputData.getPortfolio().removeHolding(sellInputData.getStock()); // remove holding from portfolio

            sellPresenter.prepareSuccessView(sellOutputData);
        } else {
            // TODO modify holding in portfolio to reduce quantity held and update sheet
            sellInputData.getPortfolio().getHolding(sellInputData.getStock())
                        .reduceQuantity(sellInputData.getQuantity());
            SellOutputData sellOutputData = new SellOutputData();
            sellPresenter.prepareSuccessView(sellOutputData);
        }
    }
}
