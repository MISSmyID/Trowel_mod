package missmyidmods.trowelmod;

import missmyidmods.trowelmod.Items.Trowel;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.ItemHelper;
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
		trowel = ItemHelper.createItem(MOD_ID,new Trowel("Trowel",32005),"trowel.png").setMaxStackSize(1);
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady(){
		RecipeBuilder.Shaped(MOD_ID).setShape("   "," II","S  ").addInput('I',Item.ingotIron).addInput('S',Item.stick).create("trowel",new ItemStack(trowel,1));
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(MOD_ID);
	}
}
