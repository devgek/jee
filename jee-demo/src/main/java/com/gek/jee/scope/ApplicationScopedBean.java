package com.gek.jee.scope;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Named
@Component
@Scope("singleton")
public @interface ApplicationScopedBean {

}
