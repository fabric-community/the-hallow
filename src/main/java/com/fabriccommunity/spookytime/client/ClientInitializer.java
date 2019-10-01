package com.fabriccommunity.spookytime.client;

import net.fabricmc.api.ClientModInitializer;

public class ClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SpookyColors.init();
    }
}
