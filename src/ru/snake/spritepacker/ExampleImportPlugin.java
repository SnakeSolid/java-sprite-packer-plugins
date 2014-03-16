package ru.snake.spritepacker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ru.snake.spritepacker.core.CoreFactory;
import ru.snake.spritepacker.core.Sprite;
import ru.snake.spritepacker.core.Texture;
import ru.snake.spritepacker.core.TextureLoader;
import ru.snake.spritepacker.plugin.ImportPlugin;

public class ExampleImportPlugin implements ImportPlugin {

	private static final String NAME = "sample-import-plugin";
	private static final String MENU_ITEM_TEXT = "Sample import plugin...";

	private static final int TEXTURE_SIZE = 64;
	private static final int SPRITE_OFFSET = TEXTURE_SIZE / 2;
	private static final int TEXTURE_GRID_STEP = 6;

	private CoreFactory factory;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getMenuItemText() {
		return MENU_ITEM_TEXT;
	}

	@Override
	public void setFactory(CoreFactory factory) {
		this.factory = factory;
	}

	@Override
	public void showImport(Component parent) {
		TextureLoader loader = factory.getTextureLoader();
		List<Sprite> sprites = new ArrayList<Sprite>();

		sprites.add(createCheckerSprite(loader, 1, Color.BLACK, Color.WHITE));
		sprites.add(createCheckerSprite(loader, 2, Color.DARK_GRAY, Color.WHITE));
		sprites.add(createCheckerSprite(loader, 3, Color.DARK_GRAY,
				Color.LIGHT_GRAY));
		sprites.add(createCheckerSprite(loader, 4, Color.GRAY, Color.LIGHT_GRAY));
		sprites.add(createCheckerSprite(loader, 5, Color.GRAY, Color.GRAY));
		sprites.add(createCheckerSprite(loader, 6, Color.LIGHT_GRAY, Color.GRAY));
		sprites.add(createCheckerSprite(loader, 7, Color.LIGHT_GRAY,
				Color.DARK_GRAY));
		sprites.add(createCheckerSprite(loader, 8, Color.WHITE, Color.DARK_GRAY));
		sprites.add(createCheckerSprite(loader, 9, Color.WHITE, Color.BLACK));

		factory.createAnimation("example-checkers", sprites);

		JOptionPane.showMessageDialog(parent, "Checkers created", NAME,
				JOptionPane.PLAIN_MESSAGE);
	}

	private Sprite createCheckerSprite(TextureLoader loader, int index,
			Color color1, Color color2) {
		BufferedImage image = new BufferedImage(TEXTURE_SIZE, TEXTURE_SIZE,
				BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();

		for (int j = 0; j < TEXTURE_SIZE - 2; j += TEXTURE_GRID_STEP) {
			for (int i = 0; i < TEXTURE_SIZE - 2; i += TEXTURE_GRID_STEP) {
				if ((i + j) % (2 * TEXTURE_GRID_STEP) == 0) {
					g.setColor(color1);
				} else {
					g.setColor(color2);
				}

				g.fillRect(i, j, TEXTURE_GRID_STEP - 1, TEXTURE_GRID_STEP - 1);
			}
		}

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, TEXTURE_SIZE - 1, TEXTURE_SIZE - 1);
		g.dispose();

		Texture texture = loader.load(image);
		StringBuilder builder = new StringBuilder();

		builder.append("example-name-");
		builder.append(index);

		Sprite sprite = new Sprite(SPRITE_OFFSET, SPRITE_OFFSET,
				builder.toString(), texture);

		return sprite;
	}
}
