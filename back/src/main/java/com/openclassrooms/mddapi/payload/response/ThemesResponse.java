package com.openclassrooms.mddapi.payload.response;

import com.openclassrooms.mddapi.models.Theme;
import lombok.Getter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response for theme fetch operations.
 * This class encapsulates a list of themes, typically used in responses to requests for retrieving themes.
 */
@Getter
public class ThemesResponse {

    /**
     * A list of Theme objects representing the themes included in the response.
     * This list contains Theme model objects, each representing a theme entity.
     */
    private List<Theme> themes;

    /**
     * Constructs a ThemesResponse with a provided list of Theme objects.
     * This constructor is used to create a response object containing the specified list of themes.
     *
     * @param themes A list of Theme objects to be included in the response.
     */
    public ThemesResponse(List<Theme> themes) {
        this.themes = themes;
    }

    /**
     * Sets the list of themes in the response.
     * This method allows updating the list of Theme objects encapsulated in this response object.
     *
     * @param themes A list of Theme objects to be set.
     */
    public void setRentals(List<Theme> themes) {
        this.themes = themes;
    }
}
