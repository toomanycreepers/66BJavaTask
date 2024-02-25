package com.TMC.WebFootballers.Utility;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Converter(autoApply = true)
@Component
public class CountryDBConverter implements AttributeConverter<Country, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Country country) {
        if (country == null) {
            return null;
        }
        return country.getId();
    }

    @Override
    public Country convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return Stream.of(Country.values())
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
