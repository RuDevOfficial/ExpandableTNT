package rudevmods.expandable_tnt.functional_blocks;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityExpandableTNT extends TileEntity {

    private int maxEplosionRadius = 10;

    private int explosionRadius = 0;
    private float explosionPitch = 0.0F;

    public TileEntityExpandableTNT() {

    }

    public boolean CanIncrease(EntityPlayer entity){
        if(explosionRadius < maxEplosionRadius) {
            if(entity.inventory.getCurrentItem().itemID == Item.sulphur.itemID){
                return true;
            }
        }

        return false;
    }

    public boolean IsHoldingItem(EntityPlayer entity)
    {
        if(entity.inventory.getCurrentItem() != null) {
            return true;
        }

        return false;
    }

    public boolean IsHoldingSulphur(EntityPlayer entity)
    {
        if(entity.inventory.getCurrentItem().itemID == Item.sulphur.itemID) {
            return true;
        }

        return false;
    }

    public boolean IsHoldingFireStriker(EntityPlayer entity)
    {
        if(entity.inventory.getCurrentItem().itemID == Item.toolFirestriker.itemID) {
            return true;
        }

        return false;
    }

    public void IncreaseBlastRadius() {
        explosionRadius += 1;
        explosionPitch += 0.1f;
    }

    public float GetPitch() {
        return this.explosionPitch;
    }

    public int GetBlastRadius() {
        return this.explosionRadius;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
    }
}
