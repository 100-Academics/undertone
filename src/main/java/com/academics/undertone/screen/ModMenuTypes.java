package com.academics.undertone.screen;

import com.academics.undertone.Undertone;
import com.academics.undertone.screen.custom.LevelingAltarMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Undertone.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<LevelingAltarMenu>> LEVELING_ALTAR_MENU =
            MENUS.register("leveling_altar_menu", () -> IMenuTypeExtension.create(LevelingAltarMenu::new));

    public static void register(IEventBus bus){
        MENUS.register(bus);
    }

}
