package com.sss.cost;

import com.sss.types.ShipmentCost;
import com.sss.types.ShipmentOption;

import java.math.BigDecimal;

public class WeightedCostStrategy implements CostStrategy {
    private MonetaryCostStrategy monetaryCostStrategy;
    private CarbonCostStrategy carbonCostStrategy;

    public WeightedCostStrategy(MonetaryCostStrategy monetaryCostStrategy, CarbonCostStrategy carbonCostStrategy) {
        this.monetaryCostStrategy = monetaryCostStrategy;
        this.carbonCostStrategy = carbonCostStrategy;
    }

    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        ShipmentCost carbonCost = this.carbonCostStrategy.getCost(shipmentOption);
        ShipmentCost monetaryCost = this.monetaryCostStrategy.getCost(shipmentOption);

//
        BigDecimal cost = monetaryCost.getCost().multiply(BigDecimal.valueOf(0.8)).add(carbonCost.getCost().multiply(BigDecimal.valueOf(.2)));
//
        return new ShipmentCost(shipmentOption, cost);
    }
}
