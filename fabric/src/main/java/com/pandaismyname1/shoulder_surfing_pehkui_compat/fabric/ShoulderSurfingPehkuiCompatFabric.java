package com.pandaismyname1.shoulder_surfing_pehkui_compat.fabric;

import com.pandaismyname1.shoulder_surfing_pehkui_compat.ShoulderSurfingPehkuiCompat;
import net.fabricmc.api.ModInitializer;

public class ShoulderSurfingPehkuiCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ShoulderSurfingPehkuiCompat.init();
    }
}