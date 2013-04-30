package dk.freecode.validation.validators;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.BeanUtils;

import dk.freecode.validation.constraints.FieldMatch;

/**
 * <p>
 * Empty string ("") and {@code null} are considered equal.
 * </p>
 * 
 * Developed from <a href="http://stackoverflow.com/a/2155576">http://stackoverflow.com/a/2155576</a>.
 */
@Slf4j
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            Object first = BeanUtils.getProperty(value, firstFieldName);
            Object second = BeanUtils.getProperty(value, secondFieldName);

            first = nullifyEmptyString(first);
            second = nullifyEmptyString(second);

            return Objects.equals(first, second);
        } catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Could not get field properties", e);
            return true;
        }
    }

    private Object nullifyEmptyString(final Object object) {
        return object instanceof String && ((String) object).isEmpty() ? null : object;
    }
}