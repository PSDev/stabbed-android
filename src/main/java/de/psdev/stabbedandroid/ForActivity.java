package de.psdev.stabbedandroid;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Qualifier annotation to explicitly differentiate dependencies between application and activity context.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForActivity {
}
