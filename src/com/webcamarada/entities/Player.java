package com.webcamarada.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.webcamarada.main.Game;
import com.webcamarada.world.Camera;
import com.webcamarada.world.World;

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
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public double speed = 1.4;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	private int frames = 0, maxFrames = 5, rIndex = 0, lIndex = 3, minIndex = 0, maxIndex = 3, uIndex = 0, dIndex = 3;
	private boolean moved = false;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		
		/*rightPlayer[0] = Game.spritesheet.getSprite(32, 0, 16, 16);
		rightPlayer[1] = Game.spritesheet.getSprite(64, 0, 16, 16);
		rightPlayer[2] = Game.spritesheet.getSprite(80, 0, 16, 16);
		*/
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
			upPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 32, 16, 16);
			downPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 48, 16, 16);
		}
	}
	
	public void tick() {
		moved = false;
		if(right && World.isFree((int)(getX()+speed), (int)getY())) {
			moved = true;
			dir = right_dir;
			setX(getX()+speed);
		}else if(left && World.isFree((int)(getX()-speed), (int)getY())) {
			moved = true;
			dir = left_dir;
			setX(getX()-speed);
		}
		
		if(up && World.isFree((int)getX(), (int)(getY()-speed))) {
			moved = true;
			dir = up_dir;
			setY(getY()-speed);
		}else if(down && World.isFree((int)getX(), (int)(getY()+speed))) {
			moved = true;
			dir = down_dir;
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
					}
				}else if(left) {
					lIndex--;
					if(lIndex < minIndex) {
						lIndex = 3;
					}
				}else if(up) {
					uIndex++;
					if(uIndex > maxIndex) {
						uIndex = 0;
					}
				}else if(down) {
					dIndex--;
					if(dIndex < minIndex) {
						dIndex = 3;
					}
				}
			}
		}else {
			rIndex = 0;
			lIndex = 3;
			uIndex = 0;
			dIndex = 3;
		}
		
		Camera.x = Camera.clamp((int)this.getX() - (Game.WIDTH/2), 0, (World.WIDTH*16) - Game.WIDTH);
		Camera.y = Camera.clamp((int)this.getY() - (Game.HEIGHT/2), 0, (World.HEIGHT*16) - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[rIndex], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		}
		else if(dir == left_dir) {
			g.drawImage(leftPlayer[lIndex], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		}
		else if(dir == up_dir) {
			g.drawImage(upPlayer[uIndex], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		}
		else if(dir == down_dir) {
			g.drawImage(downPlayer[dIndex], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		}
	}

}
