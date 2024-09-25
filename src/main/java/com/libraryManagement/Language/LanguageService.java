package com.libraryManagement.Language;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public List<Language> getLanguage() {
        return languageRepository.findAll();
    }
}
