package com.openclassrooms.mddapi.dto.response;

import com.openclassrooms.mddapi.models.Theme;
import lombok.Getter;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response for theme fetch operations.
 */
@Getter
public class ThemesResponse {

    /**
     * A list of Theme objects representing the themes included in the response.
     */
    private List<Theme> themes;

    /**
     * Constructs a ThemesResponse with a provided list of Theme objects.
     *
     * @param themes A list of Theme objects to be included in the response.
     */
    public ThemesResponse(List<Theme> themes) {
        this.themes = themes;
    }
}
