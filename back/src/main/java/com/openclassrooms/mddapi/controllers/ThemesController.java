package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.dto.response.ThemesResponse;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for managing theme-related actions.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping(value = "themes")
public class ThemesController {

    @Autowired
    private ThemeRepository themeRepository;

    /**
     * Retrieves all themes available in the platform.
     *
     * @return A ResponseEntity containing all themes or an empty body if no themes exist.
     */
    @GetMapping
    public ResponseEntity<ThemesResponse> getAllThemes() {
        List<Theme> themes = themeRepository.findAll();
        if (themes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            ThemesResponse response = new ThemesResponse(themes);
            return ResponseEntity.ok(response);
        }
    }
}

