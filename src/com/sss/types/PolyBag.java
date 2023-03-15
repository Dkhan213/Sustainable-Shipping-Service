package com.sss.types;

import java.math.BigDecimal;
import java.util.Objects;

public class PolyBag extends Packaging {

    private final Material material = Material.LAMINATED_PLASTIC;
    private BigDecimal volume;

    /**
     * Instantiates a new Packaging object.
     * @param material - the Material of the polybag
     * @param volume - the volume of the polybag
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
    }

    public BigDecimal getVolume() {
        return BigDecimal.valueOf(Math.ceil(Math.sqrt(volume.doubleValue())) * 0.6);
    }

    @Override
    public BigDecimal getMass() {
        return BigDecimal.valueOf(Math.ceil(Math.sqrt(getVolume().doubleValue()) *  0.6));
    }

    @Override
    public boolean canFitItem(Item item) {
        BigDecimal itemMass = item.getLength().multiply(item.getWidth().multiply(item.getHeight()));
        return this.getMass().compareTo(itemMass) > 0;
    }

    @Override
    public boolean equals(Object o) {
        // Can't be equal to null
        if (o == null) {
            return false;
        }

        // Referentially equal
        if (this == o) {
            return true;
        }

        // Check if it's a different type
        if (getClass() != o.getClass()) {
            return false;
        }

        Packaging packaging = (Packaging) o;
        return getMaterial() == packaging.getMaterial();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterial(), getMass());
    }
}
