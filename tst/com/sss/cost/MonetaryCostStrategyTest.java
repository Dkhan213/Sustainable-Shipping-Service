package com.sss.cost;

import com.amazon.ata.types.*;
import com.amazon.sss.types.*;
import com.sss.types.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonetaryCostStrategyTest {

    private static final Packaging BOX_10x10x20 =
        new Box(Material.CORRUGATE, BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(20));
    private static final Packaging POLYBAG =
            new PolyBag(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(10));

    private MonetaryCostStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new MonetaryCostStrategy();
    }

    @Test
    void getCost_corrugateMaterial_returnsCorrectCost() {
        // GIVEN
        ShipmentOption option = ShipmentOption.builder()
            .withPackaging(BOX_10x10x20)
            .build();

        // WHEN
        ShipmentCost shipmentCost = strategy.getCost(option);

        // THEN
        assertTrue(BigDecimal.valueOf(5.43).compareTo(shipmentCost.getCost()) == 0,
            "Incorrect monetary cost calculation for a box with dimensions 10x10x20.");
    }
    @Test
    void getCost_laminatedPlasticMaterial_returnsCorrectCost() {
        // GIVEN
        ShipmentOption option = ShipmentOption.builder()
                .withPackaging(POLYBAG)
                .build();

        // WHEN
        ShipmentCost shipmentCost = strategy.getCost(option);

        // THEN
        assertTrue(BigDecimal.valueOf(0.680).compareTo(shipmentCost.getCost()) == 0,
                "Incorrect monetary cost calculation for a polybag with dimensions 10x10x20.");
    }
}
