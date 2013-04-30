package dk.freecode.support.conversion;

import org.springframework.core.convert.converter.Converter;

public class StringToTrimmedStringConverter implements Converter<String, String> {

    @Override
    public String convert(final String source) {
        return source.trim();
    }

}
