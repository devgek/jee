package com.gek.jee.ui.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Named
//without this annotation spring autowiring is not working on composed annotations
@Component
@Scope("session")
@Target(ElementType.TYPE)
@Inherited
public @interface UIModel {
}
