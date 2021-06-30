package com.webcamarada.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.webcamarada.main.Game;
import com.webcamarada.world.Camera;

public class Entity {
	
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(96, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(96, 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(112, 16, 16, 16);
	
	private double x, y;
	private int width, height;
	private BufferedImage sprite;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void render (Graphics g) {
		g.drawImage(sprite, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		
	}
	
	public void tick() {
		
	}
}
