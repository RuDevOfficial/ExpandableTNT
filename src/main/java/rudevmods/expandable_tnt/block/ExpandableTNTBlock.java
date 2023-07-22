package rudevmods.expandable_tnt.block;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import rudevmods.expandable_tnt.Expandable_TNT;
import rudevmods.expandable_tnt.entities.EntityExpandableTNTPrimed;
import rudevmods.expandable_tnt.functional_blocks.TileEntityExpandableTNT;

import java.util.Random;

public class ExpandableTNTBlock extends BlockContainer {

    Minecraft mc = Minecraft.getMinecraft();

    public ExpandableTNTBlock(int i){
        super(i, Material.tnt);
    }

    public int idDropped(int i, Random random) { return Expandable_TNT.expandableTNT.blockID; }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {
        super.onBlockDestroyedByPlayer(world, i, j, k, l);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
        TileEntityExpandableTNT expandable = (TileEntityExpandableTNT)world.getBlockTileEntity(i, j, k);
        if(expandable.IsHoldingItem(entityplayer))
        {
            if(expandable.IsHoldingSulphur(entityplayer) == true
                    && expandable.CanIncrease(entityplayer) == true) {
                expandable.IncreaseBlastRadius();

                if(!world.isMultiplayerAndNotHost) {
                    mc.thePlayer.inventory.getCurrentItem().consumeItem(mc.thePlayer);
                    world.playSoundEffect(i, j, k, "random.fizz", 1.0F, expandable.GetPitch());
                }
                else {
                    world.playSoundAtEntity(entityplayer, "random.fizz", 1.0F, expandable.GetPitch());
                }

                world.spawnParticle("smoke", (double)i + 0.5, (double)j + 1, (double)k + 0.5, 0, 0.05, 0);
            } else if (expandable.IsHoldingFireStriker(entityplayer) == true ) {
                this.ignite(world, i, j, k, entityplayer, expandable.GetBlastRadius());
            }
        }

        return true;
    }

    public void ignite(World world, int x, int y, int z, EntityPlayer player, int explosionRadius) {
        if (world.isMultiplayerAndNotHost) {
            world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), "random.fuse", 1.0F, 1.0F);
            if (player != null && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Item.toolFirestriker.itemID) {
                player.inventory.getCurrentItem().damageItem(1, player);
            }

        } else {
            world.setBlockWithNotify(x, y, z, 0);
            EntityExpandableTNTPrimed e = new EntityExpandableTNTPrimed(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), explosionRadius, player);
            world.entityJoinedWorld(e);
            world.playSoundAtEntity(e, "random.fuse", 1.0F, 1.0F);
            if (player != null && player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Item.toolFirestriker.itemID) {
                player.inventory.getCurrentItem().damageItem(1, player);
            }

        }
    }

    protected TileEntity getBlockEntity() {
        return new TileEntityExpandableTNT();
    }
}
