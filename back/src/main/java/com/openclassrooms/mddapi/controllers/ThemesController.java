package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.paylod.response.ThemesResponse;
import com.openclassrooms.mddapi.paylod.response.UserResponse;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "themes")
public class ThemesController {

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping("")
    public ResponseEntity<ThemesResponse> getAllThemes() {
        List<Theme> themes = themeRepository.findAll();
        ThemesResponse response = new ThemesResponse(themes);
        return ResponseEntity.ok(response);
    }
}
