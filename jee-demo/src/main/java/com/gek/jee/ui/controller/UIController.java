package com.gek.jee.ui.controller;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Named
@Component
@Scope("request")
public @interface UIController {

}
