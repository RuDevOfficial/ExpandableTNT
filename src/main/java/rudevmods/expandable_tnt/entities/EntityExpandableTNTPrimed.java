package rudevmods.expandable_tnt.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class EntityExpandableTNTPrimed extends Entity {
    public int fuse;
    public int radius;

    EntityPlayer player;

    public EntityExpandableTNTPrimed(World world, int radius) {
        super(world);
        this.fuse = 0;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
        this.radius = radius;
    }

    public EntityExpandableTNTPrimed(World world, double d, double d1, double d2, int radius, EntityPlayer player) {
        this(world, radius);
        this.setPosition(d, d1, d2);
        float f = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.motionX = (double)(-MathHelper.sin(f * 3.141593F / 180.0F) * 0.02F);
        this.motionY = 0.20000000298023224;
        this.motionZ = (double)(-MathHelper.cos(f * 3.141593F / 180.0F) * 0.02F);
        this.fuse = 80;
        this.prevPosX = d;
        this.prevPosY = d1;
        this.prevPosZ = d2;
        this.player = player;
    }

    protected void entityInit() {
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863;
        this.motionY *= 0.9800000190734863;
        this.motionZ *= 0.9800000190734863;
        if (this.onGround) {
            this.motionX *= 0.699999988079071;
            this.motionZ *= 0.699999988079071;
            this.motionY *= -0.5;
        }

        if (this.fuse-- <= 0) {
            if (!this.worldObj.isMultiplayerAndNotHost) {
                this.setEntityDead();
                this.explode();
            } else {
                this.setEntityDead();
            }
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
        }

    }

    private void explode() {
        if(this.radius > 0){
            this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, radius);
        } else {
            Minecraft mc = Minecraft.getMinecraft();
            if(!worldObj.isMultiplayerAndNotHost)
            {
                worldObj.playSoundAtEntity(player, "random.pop", 1.0F, 1.0F);
            } else {
                worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.pop", 1.0F, 1.0F);
            }
        }

    }

    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.setByte("Fuse", (byte)this.fuse);
    }

    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        this.fuse = nbttagcompound.getByte("Fuse");
    }

    public float getShadowSize() {
        return 0.0F;
    }
}
