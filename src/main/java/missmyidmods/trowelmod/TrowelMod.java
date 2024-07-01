package missmyidmods.trowelmod;

import missmyidmods.trowelmod.items.Trowel;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class TrowelMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "trowelmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Item trowel;

    @Override
    public void onInitialize() {
        LOGGER.info("TrowelMod initialized.");
    }

	@Override
	public void beforeGameStart() {
		trowel = new ItemBuilder(MOD_ID)
		    .setIcon("trowelmod:item/trowel")
		    .build(new Trowel("trowel", 32005));
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady(){
		RecipeBuilder.Shaped(MOD_ID)
		    .setShape("   ", " II", "S  ")
	        .addInput('I', Item.ingotIron)
	        .addInput('S', Item.stick)
	        .create("trowel", new ItemStack(trowel, 1));
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
	}
}
