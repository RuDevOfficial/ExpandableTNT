package rudevmods.expandable_tnt.render;//

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import rudevmods.expandable_tnt.Expandable_TNT;
import rudevmods.expandable_tnt.entities.EntityExpandableTNTPrimed;

public class RenderExpandableTNTPrimed extends Render {
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderExpandableTNTPrimed() {
        this.shadowSize = 0.5F;
    }

    public void func_153_a(EntityExpandableTNTPrimed entityexpandabletntprimed, double d, double d1, double d2, float f, float f1) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        float f3;
        float f4;
        if ((float)entityexpandabletntprimed.fuse - f1 + 1.0F < 10.0F) {
            f3 = 1.0F - ((float)entityexpandabletntprimed.fuse - f1 + 1.0F) / 10.0F;
            if (f3 < 0.0F) {
                f3 = 0.0F;
            }

            if (f3 > 1.0F) {
                f3 = 1.0F;
            }

            f3 *= f3;
            f3 *= f3;
            f4 = 1.0F + f3 * 0.3F;
            GL11.glScalef(f4, f4, f4);
        }

        f3 = (1.0F - ((float)entityexpandabletntprimed.fuse - f1 + 1.0F) / 100.0F) * 0.8F;
        this.loadTexture("/terrain.png");
        f4 = entityexpandabletntprimed.getEntityBrightness(f1);
        if (Minecraft.getMinecraft().fullbright) {
            f4 = 1.0F;
        }

        this.blockRenderer.renderBlockOnInventory(Expandable_TNT.expandableTNT, 0, f4);
        if (entityexpandabletntprimed.fuse / 5 % 2 == 0) {
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 772);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f3);
            this.blockRenderer.renderBlockOnInventory(Expandable_TNT.expandableTNT, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
        }

        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.func_153_a((EntityExpandableTNTPrimed)entity, d, d1, d2, f, f1);
    }
}
