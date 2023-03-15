package com.sss.dao;

import com.sss.datastore.PackagingDatastore;
import com.sss.exceptions.NoPackagingFitsItemException;
import com.sss.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.types.*;
import com.amazon.sss.types.*;
import com.sss.types.*;

import java.util.*;

/**
 * Access data for which packaging is available at which fulfillment center.
 */
public class PackagingDAO {
    /**
     * A list of fulfillment centers with a packaging options they provide.
     */
    private Map<FulfillmentCenter, Set<FcPackagingOption>> fcPackagingOptions;
//    private List<FcPackagingOption> fcPackagingOptions;

    /**
     * Instantiates a PackagingDAO object.
     * @param datastore Where to pull the data from for fulfillment center/packaging available mappings.
     */
    public PackagingDAO(PackagingDatastore datastore) {
        this.fcPackagingOptions = new HashMap<>();
        for (FcPackagingOption fcPackagingOption : datastore.getFcPackagingOptions()) {
            if (this.fcPackagingOptions.containsKey(fcPackagingOption.getFulfillmentCenter())) {
                fcPackagingOptions.get(fcPackagingOption.getFulfillmentCenter()).add(fcPackagingOption);
            } else {
                Set<FcPackagingOption> fcPackagingOptionSet = new HashSet<>();
                fcPackagingOptionSet.add(fcPackagingOption);
                this.fcPackagingOptions.put(fcPackagingOption.getFulfillmentCenter(), fcPackagingOptionSet);
            }
        }

    }

    /**
     * Returns the packaging options available for a given item at the specified fulfillment center. The API
     * used to call this method handles null inputs, so we don't have to.
     *
     * @param item the item to pack
     * @param fulfillmentCenter fulfillment center to fulfill the order from
     * @return the shipping options available for that item; this can never be empty, because if there is no
     * acceptable option an exception will be thrown
     * @throws UnknownFulfillmentCenterException if the fulfillmentCenter is not in the fcPackagingOptions list
     * @throws NoPackagingFitsItemException if the item doesn't fit in any packaging at the FC
     */
    public List<ShipmentOption> findShipmentOptions(Item item, FulfillmentCenter fulfillmentCenter)
            throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {

        // Check all FcPackagingOptions for a suitable Packaging in the given FulfillmentCenter
        List<ShipmentOption> result = new ArrayList<>();
        boolean fcFound = false;
        if (fcPackagingOptions.containsKey(fulfillmentCenter)) {
            for (FcPackagingOption fcPackagingOption : fcPackagingOptions.get(fulfillmentCenter)) {
                Packaging packaging = fcPackagingOption.getPackaging();
                String fcCode = fcPackagingOption.getFulfillmentCenter().getFcCode();
                if (fcCode.equals(fulfillmentCenter.getFcCode())) {
                    fcFound = true;
                    if (packaging.canFitItem(item)) {
                        result.add(ShipmentOption.builder()
                                .withItem(item)
                                .withPackaging(packaging)
                                .withFulfillmentCenter(fulfillmentCenter)
                                .build());
                    }
                }

            }
        }

        // Notify caller about unexpected results
        if (!fcFound) {
            throw new UnknownFulfillmentCenterException(
                    String.format("Unknown FC: %s!", fulfillmentCenter.getFcCode()));
        }

        if (result.isEmpty()) {
            throw new NoPackagingFitsItemException(
                    String.format("No packaging at %s fits %s!", fulfillmentCenter.getFcCode(), item));
        }

        return result;
    }
}
