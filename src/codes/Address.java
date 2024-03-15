/**
 * Represents an address with attributes such as unit number, street number, street name,
 * postal code, and city name. Provides a constructor to initialize an address object
 * with these attributes and methods to retrieve the details of the address.
 *
 * @author Samson James Ordonez
 * @version 1.0
 */
public class Address
{
    private final String  unitNumber;
    private final int     streetNumber;
    private final String  streetName;
    private final String  postalCode;
    private final String  cityName;

    private static final int MIN_UNIT_NUMBER_LENGTH = 1;
    private static final int MAX_UNIT_NUMBER_LENGTH = 4;
    private static final int MIN_STREET_NUMBER_LENGTH = 0;
    private static final int MAX_STREET_NUMBER_LENGTH = 999999;
    private static final int MIN_STREET_NAME_LENGTH = 1;
    private static final int MAX_STREET_NAME_LENGTH = 20;
    private static final int MIN_POSTAL_CODE_LENGTH = 5;
    private static final int MAX_POSTAL_CODE_LENGTH = 6;
    private static final int MIN_CITY_NAME_LENGTH = 1;
    private static final int MAX_CITY_NAME_LENGTH = 30;

    /**
     * Constructor to initialize an Address object with specified attributes.
     * @param unitNumber    The unit number of the address (optional).
     * @param streetNumber  The street number of the address.
     * @param streetName    The street name of the address.
     * @param postalCode    The postal code of the address.
     * @param cityName      The city name of the address.
     * @throws IllegalArgumentException if any of the input parameters are invalid.
     * @throws NullPointerException     if streetName, postalCode, or cityName is null.
     */
    public Address(final String unitNumber,
                   final int    streetNumber,
                   final String streetName,
                   final String postalCode,
                   final String cityName)
    {
        if(unitNumber != null && (unitNumber.length() < MIN_UNIT_NUMBER_LENGTH || unitNumber.length() > MAX_UNIT_NUMBER_LENGTH))
        {
            throw new IllegalArgumentException("Invalid unit number: " + unitNumber);
        }


        if(streetNumber <= MIN_STREET_NUMBER_LENGTH || streetNumber > MAX_STREET_NUMBER_LENGTH)
        {
            throw new IllegalArgumentException("Invalid street number: " + streetNumber);
        }


        if(streetName == null)
        {
            throw new NullPointerException("Invalid street name: null");
        }


        if(streetName.length() > MAX_STREET_NAME_LENGTH || streetName.length() < MIN_STREET_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Invalid street name: " + streetName);
        }


        if(postalCode == null)
        {
            throw new NullPointerException("Invalid postal code: null");
        }


        if(postalCode.length() > MAX_POSTAL_CODE_LENGTH || postalCode.length() < MIN_POSTAL_CODE_LENGTH) {
            throw new IllegalArgumentException("Invalid postal code: " + postalCode);
        }


        if(cityName == null)
        {
            throw new NullPointerException("Invalid city: null");
        }


        if(cityName.length() > MAX_CITY_NAME_LENGTH || cityName.length() < MIN_CITY_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Invalid city: " + cityName);
        }

        this.unitNumber     = unitNumber;
        this.streetNumber   = streetNumber;
        this.streetName     = streetName;
        this.postalCode     = postalCode;
        this.cityName       = cityName;
    }

    /**
     * Returns the unit number of the address.
     * @return the unit number of the address.
     */
    public String getUnitNumber()
    {
        return unitNumber;
    }

    /**
     * Returns the street number of the address.
     * @return the street number of the address.
     */
    public int getStreetNumber()
    {
        return streetNumber;
    }

    /**
     * Returns the street name of the address.
     * @return the street name of the address.
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * Returns the postal code of the address.
     * @return the postal code of the address.
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Returns the city name of the address.
     * @return the city name of the address.
     */
    public String getCity()
    {
        return cityName;
    }
}
