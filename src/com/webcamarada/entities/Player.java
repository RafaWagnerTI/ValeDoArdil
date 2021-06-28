package com.webcamarada.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.webcamarada.main.Game;

public class Player extends Entity {

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean right, up, left, down;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	public double speed = 1.4;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private int frames = 0, maxFrames = 5, rIndex = 0, lIndex = 3, minIndex = 0, maxIndex = 3;
	private boolean moved = false;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		
		rightPlayer[0] = Game.spritesheet.getSprite(32, 0, 16, 16);
		rightPlayer[1] = Game.spritesheet.getSprite(64, 0, 16, 16);
		rightPlayer[2] = Game.spritesheet.getSprite(80, 0, 16, 16);
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
	}
	
	public void tick() {
		moved = false;
		if(right) {
			moved = true;
			dir = right_dir;
			setX(getX()+speed);
		}else if(left) {
			moved = true;
			dir = left_dir;
			setX(getX()-speed);
		}
		
		if(up) {
			moved = true;
			setY(getY()-speed);
		}else if(down) {
			moved = true;
			setY(getY()+speed);
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				if(right) {
					rIndex++;
					if(rIndex > maxIndex) {
						rIndex = 0;
					}else if(rIndex == 1) {
						rIndex = 2;
					}
				}else if(left) {
					lIndex--;
					if(lIndex < minIndex) {
						lIndex = 3;
					}else if(lIndex == 2) {
						lIndex = 1;
					}
				}
			}
		}else {
			rIndex = 0;
			lIndex = 3;
		}
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[rIndex], (int)this.getX(), (int)this.getY(), null);
		}
		else if(dir == left_dir) {
			g.drawImage(leftPlayer[lIndex], (int)this.getX(), (int)this.getY(), null);
		}
	}

}
