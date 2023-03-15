package com.sss.cost;

import com.sss.types.Material;
import com.sss.types.Packaging;
import com.sss.types.ShipmentCost;
import com.sss.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarbonCostStrategy implements CostStrategy {
    private final Map<Material, BigDecimal> carbonCostPerGram;

    public CarbonCostStrategy() {
        carbonCostPerGram = new HashMap<>();
        carbonCostPerGram.put(Material.CORRUGATE, BigDecimal.valueOf(0.017));
        carbonCostPerGram.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(0.012));
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();
        BigDecimal carbonCost = this.carbonCostPerGram.get(packaging.getMaterial());

        BigDecimal cost = packaging.getMass().multiply(carbonCost);

        return new ShipmentCost(shipmentOption, cost);
    }
}
