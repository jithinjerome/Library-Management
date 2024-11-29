package com.libraryManagement.Language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    //To add a new language.
    @PostMapping(path = "/addLanguage")
    public Language addLanguage(@RequestBody Language language){
        return languageService.save(language);
    }

    //List of all languages.
    @GetMapping(path = "/getLanguage")
    public List<Language> getLanguage(){
        return languageService.getLanguage();
    }
}
