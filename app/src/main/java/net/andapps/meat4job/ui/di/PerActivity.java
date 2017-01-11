package net.andapps.meat4job.ui.di;

import javax.inject.Scope;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by joanbarroso on 11/01/2017.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {}