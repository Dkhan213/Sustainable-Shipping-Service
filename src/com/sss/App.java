package com.sss;

import com.sss.cost.CarbonCostStrategy;
import com.sss.cost.CostStrategy;
import com.sss.cost.MonetaryCostStrategy;
import com.sss.cost.WeightedCostStrategy;
import com.sss.dao.PackagingDAO;
import com.sss.datastore.PackagingDatastore;
import com.sss.service.ShipmentService;

public class App {
    /* don't instantiate me */
    private App() {}

    private static PackagingDatastore getPackagingDatastore() {
        return new PackagingDatastore();
    }

    private static PackagingDAO getPackagingDAO() {
        return new PackagingDAO(getPackagingDatastore());
    }

    private static CostStrategy getCostStrategy() {
        return new WeightedCostStrategy(new MonetaryCostStrategy(), new CarbonCostStrategy());
    }

    public static ShipmentService getShipmentService() {
        return new ShipmentService(getPackagingDAO(), getCostStrategy());
    }
}
