/**
 * Represents a property with attributes such as price, address, number of bedrooms,
 * presence of a swimming pool, residence type, and property ID. Provides a constructor
 * to initialize a property object with these attributes and methods to retrieve the
 * details of the property.
 *
 * @author Samson James Ordonez
 * @version 1.0
 */
public class Property
{
    private final Address   addressLists;
    private final int       numOfBedrooms;
    private final boolean   isSwimmingPool;
    private final String    residenceType;
    private final String    propertyId;

    private double priceUSD;

    private static final int MIN_PRICE_USD = 0;
    private static final int MIN_NUMBERS_OF_BEDROOM = 1;
    private static final int MAX_NUMBERS_OF_BEDROOM = 20;
    private static final int MIN_PROPERTYID_LENGTH = 1;
    private static final int MAX_PROPERTYID_LENGTH = 6;

    /**
     * Constructor to initialize a Property object with specified attributes.
     * @param priceUSD       The price of the property in USD.
     * @param addressLists   The address of the property.
     * @param numOfBedrooms  The number of bedrooms in the property.
     * @param isSwimmingPool Indicates whether the property has a swimming pool.
     * @param residenceType  The type of residence (e.g., residence, commercial, retail).
     * @param propertyId     The unique identifier of the property.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     * @throws NullPointerException     if addressLists, residenceType, or propertyId is null.
     */
    public Property(final double priceUSD,
                    final Address addressLists,
                    final int numOfBedrooms,
                    final boolean isSwimmingPool,
                    final String residenceType,
                    final String propertyId)
    {
        if(priceUSD <= MIN_PRICE_USD)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUSD);
        }
        if(addressLists == null) {
            throw new NullPointerException("Invalid address: null");
        }
        if(numOfBedrooms < MIN_NUMBERS_OF_BEDROOM || numOfBedrooms > MAX_NUMBERS_OF_BEDROOM) {
            throw new IllegalArgumentException("Invalid number of bedrooms: " + numOfBedrooms);
        }
        if(residenceType == null)
        {
            throw new NullPointerException("Invalid property type: null");
        }
        if(residenceType !=null &&
                (!residenceType.equalsIgnoreCase("residence")
                && !residenceType.equalsIgnoreCase("commercial")
                && !residenceType.equalsIgnoreCase("retail")))
        {
            throw new IllegalArgumentException("Invalid property type: " + residenceType);
        }
        if(propertyId == null)
        {
            throw new NullPointerException("Invalid property id: null");
        }
        if(propertyId.length() < MIN_PROPERTYID_LENGTH || propertyId.length() > MAX_PROPERTYID_LENGTH)
        {
            throw new IllegalArgumentException("Invalid property id: " + propertyId);
        }

        this.priceUSD = priceUSD;
        this.addressLists = addressLists;
        this.numOfBedrooms = numOfBedrooms;
        this.isSwimmingPool = isSwimmingPool;
        this.residenceType = residenceType;
        this.propertyId = propertyId;
    }

    /**
     * Returns the price of the property in USD.
     * @return the price of the property in USD.
     */
    public double getPriceUsd()
    {
        return priceUSD;
    }

    /**
     * Returns the Address of the property.
     * @return the Address of the property.
     */
    public Address getAddress()
    {
        return addressLists;
    }

    /**
     * Returns the number of bedrooms in the property.
     * @return the number of bedrooms in the property.
     */
    public int getNumberOfBedrooms()
    {
        return numOfBedrooms;
    }

    /**
     * Checks if the property has a swimming pool.
     * @return true if the property has a swimming pool, false otherwise.
     */
    public boolean hasSwimmingPool()
    {
        return isSwimmingPool;
    }

    /**
     * Returns the type of residence (e.g., residence, commercial, retail).
     * @return the type of residence.
     */
    public String getType()
    {
        return residenceType;
    }

    /**
     * Returns the property ID.
     * @return the property ID.
     */
    public String getPropertyId()
    {
        return propertyId;
    }

    /**
     * Sets the price of the property in USD.
     * @param priceUSD the new price of the property in USD.
     */
    public void setPriceUsd(final int priceUSD)
    {
        if(priceUSD <= MIN_PRICE_USD)
        {
            throw new IllegalArgumentException("Invalid price: " + priceUSD);
        }
        this.priceUSD = priceUSD;
    }
}
