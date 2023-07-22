package rudevmods.expandable_tnt;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rudevmods.expandable_tnt.block.ExpandableTNTBlock;
import rudevmods.expandable_tnt.entities.EntityExpandableTNTPrimed;
import rudevmods.expandable_tnt.render.RenderExpandableTNTPrimed;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.RecipeHelper;
import turniplabs.halplibe.helper.TextureHelper;
import rudevmods.expandable_tnt.functional_blocks.TileEntityExpandableTNT;

public class Expandable_TNT implements ModInitializer {
    public static final String MOD_ID = "expandable_tnt";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    Minecraft mc = Minecraft.getMinecraft();

    public static int _ID = 1000;

    public static Block expandableTNT = BlockHelper.createBlock(MOD_ID, new ExpandableTNTBlock(_ID),  "expandabletntblock", 23, 0,
            24, 0, 22, 0, 22, 0, 22, 0, 22, 0,
            Block.soundGrassFootstep, 0, 0, 0);

    @Override
    public void onInitialize() {
        TextureHelper.registerBlockTexture(MOD_ID, "expandabletntblock_front.png");
        TextureHelper.registerBlockTexture(MOD_ID, "expandabletntblock_top.png");
        TextureHelper.registerBlockTexture(MOD_ID, "expandabletntblock_sides.png");

        EntityHelper.createTileEntity(TileEntityExpandableTNT.class, "Expandable TNT");
        EntityHelper.createEntity(EntityExpandableTNTPrimed.class, new RenderExpandableTNTPrimed(), _ID + 2, "ExpandableTNT");

        RecipeHelper.Crafting.createRecipe(expandableTNT, 1, new Object[] {"ABA", "BCB", "ABA", 'A', Item.ingotSteel, 'B', Block.sand, 'C', Block.glass});
    }
}
