package com.openclassrooms.mddapi.paylod.response;


import com.openclassrooms.mddapi.models.Theme;
import lombok.Getter;

import java.util.List;

/**
 * Represents the structure of the response returned when fetching themes.
 */
@Getter
public class ThemesResponse {

    /**
     * List of theme returned in the response.
     * -- GETTER --
     *  Retrieves the list of themes.
     *
     */
    private List<Theme> themes;

    /**
     * Constructs a RentalsResponse with the provided list of rentals.
     *
     * @param themes List of RentalModel objects.
     */
    public ThemesResponse(List<Theme> themes) {
        this.themes = themes;
    }

    /**
     * Sets the list of themes.
     *
     * @param themes A list of Theme objects.
     */
    public void setRentals(List<Theme> themes) {
        this.themes = themes;
    }
}
