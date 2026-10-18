
/*
 *    这些为注册类,请将注册写在这里,而不是其它地方!
 */
package net.hhdsj.changed_creatures.init;

import net.minecraft.world.inventory.MenuType;

import net.hhdsj.changed_creatures.world.inventory.FINDGUIMenu;
import net.hhdsj.changed_creatures.world.inventory.CatalyzerguiMenu;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.hhdsj.changed_creatures.ChangedCreature;
public class ChangedCreatureModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ChangedCreature.MODID);
	public static final RegistryObject<MenuType<CatalyzerguiMenu>> CATALYZERGUI = REGISTRY.register("catalyzergui", () -> IForgeMenuType.create(CatalyzerguiMenu::new));
	public static final RegistryObject<MenuType<FINDGUIMenu>> FINDGUI = REGISTRY.register("findgui", () -> IForgeMenuType.create(FINDGUIMenu::new));
}
