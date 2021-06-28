package com.webcamarada.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.webcamarada.entities.Bullet;
import com.webcamarada.entities.Enemy;
import com.webcamarada.entities.Entity;
import com.webcamarada.entities.Lifepack;
import com.webcamarada.entities.Weapon;
import com.webcamarada.main.Game;

public class World {

	private Tile[] tiles;
	public static int WIDTH, HEIGHT;
	
	public World (String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			for(int xx = 0; xx < WIDTH; xx++) {
				for(int yy = 0; yy < HEIGHT; yy++) {
					int pixelAtual = pixels[xx + (yy*WIDTH)];
					tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);
					switch(pixelAtual){
						case 0xFF000000: tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR); break;//Chão
						case 0xFFFFFFFF: tiles[xx + (yy*WIDTH)] = new WallTile(xx*16, yy*16,Tile.TILE_WALL);   break;//Parede
						case 0xFFFF6A00: Game.player.setX(xx*16); Game.player.setY(yy*16);                     break;//Jogador
						case 0xFF007F0E: Game.entities.add(new Lifepack(xx*16, yy*16, 16, 16, Entity.LIFEPACK_EN)); break;//Vida
						case 0xFF00137F: Game.entities.add(new Weapon(xx*16, yy*16, 16, 16, Entity.WEAPON_EN));break;
						case 0xFFFFD800: Game.entities.add(new Bullet(xx*16, yy*16, 16, 16, Entity.BULLET_EN));break;//Munição
						case 0xFFFF0000: Game.entities.add(new Enemy(xx*16, yy*16, 16, 16, Entity.ENEMY_EN));  break;//Inimigo
						default: tiles[xx + (yy*WIDTH)] = new FloorTile(xx*16, yy*16,Tile.TILE_FLOOR);         break;//Chão
					}
						
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render (Graphics g) {
		for(int xx = 0; xx < WIDTH; xx++) {
			for(int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
