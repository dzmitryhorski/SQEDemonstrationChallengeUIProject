package com.sample.utils;

import com.google.inject.AbstractModule;
import com.sample.services.InfoDialogService;
import com.sample.services.OrderPageService;
import com.sample.verifications.OrderPageVerification;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(OrderPageService.class).asEagerSingleton();
        bind(InfoDialogService.class).asEagerSingleton();
        bind(OrderPageVerification.class).asEagerSingleton();
    }

}
