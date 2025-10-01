package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private List<Investor> investors = new ArrayList<>();
    private double price;

    public void registerInvestor(Investor inv) {
        investors.add(inv);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Investor inv : investors) {
            inv.update(price);
        }
    }
}
