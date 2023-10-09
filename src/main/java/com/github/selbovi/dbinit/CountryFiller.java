package com.github.selbovi.dbinit;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.selbovi.domain.Country;
import com.github.selbovi.repo.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CountryFiller implements InitializingBean {

    private static String SQL = "/country.json";
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        try (InputStream resourceAsStream = this.getClass().getResourceAsStream(SQL)) {
            List<Country> countries = objectMapper.readValue(resourceAsStream, new TypeReference<>() {
            });
            countryRepository.saveAll(countries);
        }
    }

}
