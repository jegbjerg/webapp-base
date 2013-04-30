package dk.freecode.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import dk.freecode.validation.validators.FieldMatchValidator;

/**
 * Validation annotation to validate that 2 fields have the same value. An array of fields and their matching
 * confirmation fields can be supplied.
 * <p>
 * Example, compare 1 pair of fields:
 * 
 * <pre>
 * &#064;FieldMatch(first = "password", second = "confirmPassword")}
 * </pre>
 * 
 * Example, compare more than 1 pair of fields:
 * 
 * <pre>
 * &#064;FieldMatch.List({
 *    &#064;FieldMatch(first = "password", second = "confirmPassword"),
 *    &#064;FieldMatch(first = "email", second = "confirmEmail")})
 * </pre>
 * 
 * Developed from <a href="http://stackoverflow.com/a/2155576">http://stackoverflow.com/a/2155576</a>.
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {

    String message() default "{dk.freecode.validation.constraints.fieldmatch}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     * 
     * @see FieldMatch
     */
    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        FieldMatch[] value();
    }
}