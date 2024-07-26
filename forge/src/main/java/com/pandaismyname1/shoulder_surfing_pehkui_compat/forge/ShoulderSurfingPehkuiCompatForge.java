package com.pandaismyname1.shoulder_surfing_pehkui_compat.forge;

import dev.architectury.platform.forge.EventBuses;
import com.pandaismyname1.shoulder_surfing_pehkui_compat.ShoulderSurfingPehkuiCompat;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ShoulderSurfingPehkuiCompat.MOD_ID)
public class ShoulderSurfingPehkuiCompatForge {
    public ShoulderSurfingPehkuiCompatForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ShoulderSurfingPehkuiCompat.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ShoulderSurfingPehkuiCompat.init();
    }
}