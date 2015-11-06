/**
 * Copyright (c) Lambda Innovation, 2013-2015
 * 本作品版权由Lambda Innovation所有。
 * http://www.li-dev.cn/
 *
 * This project is open-source, and it is distributed under  
 * the terms of GNU General Public License. You can modify
 * and distribute freely as long as you follow the license.
 * 本项目是一个开源项目，且遵循GNU通用公共授权协议。
 * 在遵照该协议的情况下，您可以自由传播和修改。
 * http://www.gnu.org/licenses/gpl.html
 */
package cn.academy.core.client.render.ray;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import static org.lwjgl.opengl.GL11.*;

import cn.academy.core.entity.IRay;
import cn.lambdalib.util.client.RenderUtils;
import cn.lambdalib.util.deprecated.ViewOptimize;
import cn.lambdalib.util.generic.VecUtils;
import cn.lambdalib.util.helper.Motion3D;

/**
 * Renderer to draw glow texture
 * @author WeAthFolD
 */
public abstract class RendererRayBaseGlow<T extends IRay> extends Render {
	
	{
		this.shadowOpaque = 0;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, 
			float a, float b) {
		T ray = (T) entity;
		
		Minecraft mc = Minecraft.getMinecraft();
		
		glPushMatrix();
		
		doTransform(ray);
		
		Vec3 position = ray.getPosition();
		Vec3 relativePosition = VecUtils.subtract(position, 
				VecUtils.vec(RenderManager.renderPosX, RenderManager.renderPosY, RenderManager.renderPosZ));
		glTranslated(x, y, z);
		
		//Calculate the most appropriate 'billboard-up' direction.
		//The ray viewing direction.
		Vec3 dir = new Motion3D(entity, true).getMotionVec();
		//Pick two far enough start and end point.
		Vec3 start = VecUtils.multiply(dir, ray.getStartFix()), 
			end = VecUtils.add(start, VecUtils.multiply(dir, ray.getLength() - ray.getStartFix()));
		
		Vec3 upDir;
		boolean firstPerson = ViewOptimize.isFirstPerson(ray);
		if(firstPerson) {
			upDir = VecUtils.vec(0, 1, -0.5);
		} else {
			//Get closest point for view judging.
			Vec3 pt = VecUtils.vec(0, 0, 0);
			
			//The player viewing direction towards pt.
			Vec3 perpViewDir = VecUtils.add(pt, relativePosition);
			
			// cross product to get the 'up' vector
			upDir = VecUtils.crossProduct(perpViewDir, dir);
		}
		
		upDir = upDir.normalize();
		
		//DEBUG
//		GL11.glDisable(GL11.GL_TEXTURE_2D);
//		Tessellator t = Tessellator.instance;
//		
//		t.startDrawing(GL11.GL_LINES);
//		//VecUtils.tessellate(start);
//		//VecUtils.tessellate(end);
//		
//		VecUtils.tessellate(pt);
//		VecUtils.tessellate(VecUtils.add(pt, VecUtils.scalarMultiply(upDir, 5)));
//		t.draw();
//		
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//DEBUG END
		
		if(ray.needsViewOptimize()) {
			Vec3 vec = ViewOptimize.getFixVector(ray);
			vec.rotateAroundY((float) ((270 - entity.rotationYaw) / 180 * Math.PI));
			start = VecUtils.add(start, vec);
			
			// Don't fix end to get accurate pointing direction
			// end = VecUtils.add(end, vec);
		}
		
		doTransform(ray);
		
		//Now delegate to the render itself~
		draw(ray, start, end, upDir);
		
		glPopMatrix();
	}
	
	protected void doPostTransform(T ray) {}
	
	protected void doTransform(T ray) {}
	
	protected void drawBoard(Vec3 start, Vec3 end, Vec3 upDir, double width) {
		width /= 2;
		Vec3 
			v1 = VecUtils.add(start, VecUtils.multiply(upDir, width)),
			v2 = VecUtils.add(start, VecUtils.multiply(upDir, -width)),
			v3 = VecUtils.add(end, 	 VecUtils.multiply(upDir, -width)),
			v4 = VecUtils.add(end,   VecUtils.multiply(upDir, width));
		
		Tessellator t = Tessellator.instance;
		
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		glBegin(GL_QUADS);
		RenderUtils.addVertexLegacy(v1, 0, 1);
		RenderUtils.addVertexLegacy(v2, 0, 0);
		RenderUtils.addVertexLegacy(v3, 1, 0);
		RenderUtils.addVertexLegacy(v4, 1, 1);
		glEnd();
	}
	
	/**
	 * Draw the ray at the origin. The ray's heading direction should be toward x+, 
	 * and normal is always in z direction.
	 * @param start The start point
	 * @param end The end point
	 * @param sideDir the suggested billboard-up direction. You can ignore this if not drawing a billboard.
	 */
	protected abstract void draw(T ray, Vec3 start, Vec3 end, Vec3 sideDir);

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

}
