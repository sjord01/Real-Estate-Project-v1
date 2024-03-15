import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents a real estate agency managing properties.
 *
 * @author Samson James Ordonez
 * @version 1.0
 */
public class Agency
{
    private final String                agencyName;
    private final Map<String, Property> properties;

    private static final int MIN_AGENCY_NAME_LENGTH = 1;
    private static final int MAX_AGENCY_NAME_LENGTH = 31;

    /**
     * Constructs a new Agency object with the given name.
     * @param agencyName The name of the agency.
     */
    public Agency(final String agencyName)
    {

        if(agencyName == null &&(agencyName.length() < MIN_AGENCY_NAME_LENGTH || agencyName.length() > MAX_AGENCY_NAME_LENGTH))
        {
            throw new IllegalArgumentException("Invalid agency name");
        }


        this.agencyName = agencyName;
        this.properties = new HashMap<>();
    }

    /**
     * Adds a property to the agency's management.
     * @param property The property to add.
     */
    public void addProperty(final Property property)
    {
        if(property != null)
        {
            this.properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * Removes a property   from the agency's management.
     * @param propertyId    The ID of the property to remove.
     */
    public void removeProperty(final String propertyId)
    {
        properties.remove(propertyId);
    }

    /**
     * Retrieves a property by its ID.
     * @param propertyId    The ID of the property to retrieve.
     * @return              The property corresponding to the given ID, or null if not found.
     */
    public Property getProperty(final String propertyId)
    {
        return properties.get(propertyId);
    }

    /**
     * Calculates the total value of all properties managed by the agency.
     * @return The total value of all properties in USD.
     */
    public double getTotalPropertyValues()
    {
        double total = 0;
        for (final Property property : properties.values())
        {
            total += property.getPriceUsd();
        }
        return total;
    }

    /**
     * Retrieves properties with swimming pools.
     * @return An ArrayList containing properties with swimming pools, or null if none found.
     */
    public ArrayList<Property> getPropertiesWithPools()
    {
        final ArrayList<Property> matches;

        matches = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property.hasSwimmingPool())
            {
                matches.add(property);
            }
        }


        if(matches.isEmpty())
        {
            return null;
        }
        else
        {
            return matches;
        }
    }

    /**
     * Retrieves properties within a specified price range in USD.
     * @param minUsd    The minimum price in USD.
     * @param maxUsd    The maximum price in USD.
     * @return          An array of properties within the specified price range, or null if none found.
     */
    public Property[] getPropertiesBetween(final double minUsd,
                                           final double maxUsd)
    {
        final ArrayList<Property> matches;

        matches = new ArrayList<>();

        for(final Property property : properties.values())
        {
            double price = property.getPriceUsd();

            if(price >= minUsd && price <= maxUsd)
            {
                matches.add(property);
            }
        }


        if(matches.isEmpty())
        {
            return null;
        }
        else
        {
            return matches.toArray(new Property[0]);
        }
    }

    /**
     * Retrieves properties located on a specified street.
     * @param streetName    The name of the street.
     * @return              An ArrayList containing addresses of properties on the specified street,
     *                      or null if none found.
     */
    public ArrayList<Address> getPropertiesOn(final String streetName)
    {
        final ArrayList<Address> matches;

        matches = new ArrayList<>();

        for(final Property property : properties.values())
        {
            if(property.getAddress().getStreetName().equalsIgnoreCase(streetName))
            {
                matches.add(property.getAddress());
            }
        }


        if(matches.isEmpty())
        {
            return null;
        }
        else
        {
            return matches;
        }
    }

    /**
     * Retrieves properties with a specified number of bedrooms.
     * @param minBedrooms   The minimum number of bedrooms.
     * @param maxBedrooms   The maximum number of bedrooms.
     * @return              A HashMap containing properties with bedrooms within the specified range,
     *                      or null if none found.
     */
    public HashMap<String, Property> getPropertiesWithBedrooms(final int minBedrooms,
                                                               final int maxBedrooms)
    {
        final HashMap<String, Property> matches;

        matches = new HashMap<>();

        for(final Property property : properties.values())
        {
            final int numOFBedrooms;

            numOFBedrooms = property.getNumberOfBedrooms();

            if(numOFBedrooms >= minBedrooms && numOFBedrooms <= maxBedrooms)
            {
                matches.put(property.getPropertyId(), property);
            }
        }

        if(matches.isEmpty())
        {
            return null;
        }
        else
        {
            return matches;
        }
    }

    /**
     * Retrieves properties of a specified type.
     * @param propertyType  The type of property.
     * @return              An ArrayList containing formatted strings of properties of the specified type,
     *                      or a message if none found.
     */
    public ArrayList<String> getPropertiesOfType(final String propertyType)
    {
        final ArrayList<String> matches;

        matches = new ArrayList<>();
        
        int propertyCount = 1; // Counter for dynamically numbering properties

        for(Property property : properties.values())
        {
            if(property.getType().equalsIgnoreCase(propertyType))
            {
                matches.add(formatProperty(property, propertyCount));
                propertyCount++;
            }
        }

        if(matches.isEmpty())
        {
            return getNoTypeFoundMessage(propertyType);
        }
        else
        {
            final ArrayList<String> formattedMatches;

            formattedMatches = new ArrayList<>();

            formattedMatches.add("Type: " + propertyType.toUpperCase() + "\n");

            formattedMatches.addAll(matches); //Or use the enhanced for-each loop

            return formattedMatches;
        }
    }

    /**
     * Formats a property for display.
     * @param property          The property to format.
     * @param propertyCount     The number assigned to the property for display.
     * @return                  A formatted string representation of the property.
     */
    private String formatProperty(final Property property,
                                  final int propertyCount)
    {
        final StringBuilder sb;
        final int formattedPrice;

        sb              = new StringBuilder();
        formattedPrice  = (int) property.getPriceUsd();

        sb.append(propertyCount)
                .append(") ")
                .append("Property ")
                .append(property.getPropertyId().toUpperCase())
                .append(": ");

        if(property.getAddress().getUnitNumber() != null && !property.getAddress().getUnitNumber().isBlank())
        {
            sb.append("unit #")
                    .append(property.getAddress().getUnitNumber())
                    .append(" at ");
        }

        sb.append(property.getAddress().getStreetNumber())
                .append(" ")
                .append(capitalizeWords(property.getAddress().getStreetName()))
                .append(" ")
                .append(property.getAddress().getPostalCode().toUpperCase())
                .append(" in ")
                .append(capitalizeWords(property.getAddress().getCity()))
                .append(" (")
                .append(property.getNumberOfBedrooms() > 1 ? property.getNumberOfBedrooms() + " bedrooms" :
                        property.getNumberOfBedrooms() + " bedroom");

        if(property.hasSwimmingPool())
        {
            sb.append(" plus pool): $");
        }
        else
        {
            sb.append("): $");
        }

        sb.append(formattedPrice).append(".\n");

        return sb.toString();
    }


    /**
     * Capitalizes the words in a string.
     * @param input The string to capitalize.
     * @return The input string with each word capitalized.
     */
    private String capitalizeWords(String input) {
        final StringBuilder sb;

        sb                      = new StringBuilder();
        boolean capitalizeNext  = true;

        for(char ch : input.toCharArray())
        {
            if(Character.isWhitespace(ch))
            {
                // Insert a space if the character is whitespace
                sb.append(' ');
                capitalizeNext = true;
            }
            else if(capitalizeNext)
            {
                // Capitalize the character if it's the start of a word
                sb.append(Character.toUpperCase(ch));
                capitalizeNext = false;
            }
            else
            {
                // Otherwise, keep the character as is
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString();
    }

    /**
     * Generates a message indicating that no properties of the specified type were found.
     * @param propertyType  The type of property.
     * @return              An ArrayList containing a formatted message indicating no properties of the specified type were found.
     */
    private ArrayList<String> getNoTypeFoundMessage(String propertyType)
    {
        final ArrayList<String> noTypeFound;

        noTypeFound = new ArrayList<>();

        noTypeFound.add("Type: " + propertyType.toUpperCase());
        noTypeFound.add("<none found>");

        return noTypeFound;
    }
}