package org.darkstorm.minecraft.gui.font;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

public class UnicodeFontRenderer extends FontRenderer {

	public UnicodeFontRenderer(GameSettings p_i1035_1_,
			ResourceLocation p_i1035_2_, TextureManager p_i1035_3_,
			boolean p_i1035_4_) {
		super(p_i1035_1_, p_i1035_2_, p_i1035_3_, p_i1035_4_);
		// TODO Auto-generated constructor stub
	}
//	private final UnicodeFont font;
//
//	@SuppressWarnings("unchecked")
//	public UnicodeFontRenderer(Font awtFont) {
//		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
//
//		font = new UnicodeFont(awtFont);
//		font.addAsciiGlyphs();
//		font.getEffects().add(new ColorEffect(Color.WHITE));
//		try {
//			font.loadGlyphs();
//		} catch(SlickException exception) {
//			throw new RuntimeException(exception);
//		}
//		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
//		FONT_HEIGHT = font.getHeight(alphabet) / 2;
//	}
//
//	@Override
//	public int drawString(String string, int x, int y, int color) {
//		if(string == null)
//			return 0;
//		// glClear(256);
//		// glMatrixMode(GL_PROJECTION);
//		// glLoadIdentity();
//		// IntBuffer buffer = BufferUtils.createIntBuffer(16);
//		// glGetInteger(GL_VIEWPORT, buffer);
//		// glOrtho(0, buffer.get(2), buffer.get(3), 0, 1000, 3000);
//		// glMatrixMode(GL_MODELVIEW);
//		// glLoadIdentity();
//		// glTranslatef(0, 0, -2000);
//		glPushMatrix();
//		glScaled(0.5, 0.5, 0.5);
//
//		boolean blend = glIsEnabled(GL_BLEND);
//		boolean lighting = glIsEnabled(GL_LIGHTING);
//		boolean texture = glIsEnabled(GL_TEXTURE_2D);
//		if(!blend)
//			glEnable(GL_BLEND);
//		if(lighting)
//			glDisable(GL_LIGHTING);
//		if(texture)
//			glDisable(GL_TEXTURE_2D);
//		x *= 2;
//		y *= 2;
//		// glBegin(GL_LINES);
//		// glVertex3d(x, y, 0);
//		// glVertex3d(x + getStringWidth(string), y + FONT_HEIGHT, 0);
//		// glEnd();
//
//		font.drawString(x, y, string, new org.newdawn.slick.Color(color));
//
//		if(texture)
//			glEnable(GL_TEXTURE_2D);
//		if(lighting)
//			glEnable(GL_LIGHTING);
//		if(!blend)
//			glDisable(GL_BLEND);
//		glPopMatrix();
//		return x;
//	}
//
//	
//	
//	@Override
//	public int drawString(String string, float x, float y, int color, boolean shadow) {
//		return this.drawString(string, (int) x, (int) y, color, true);
//	}
//
//	@Override
//	public int getCharWidth(char c) {
//		return getStringWidth(Character.toString(c));
//	}
//
//	@Override
//	public int getStringWidth(String string) {
//		return font.getWidth(string) / 2;
//	}
//
//	public int getStringHeight(String string) {
//		return font.getHeight(string) / 2;
//	}
}
