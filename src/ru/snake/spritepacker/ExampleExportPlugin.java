package ru.snake.spritepacker;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import ru.snake.spritepacker.core.CoreFactory;
import ru.snake.spritepacker.core.Sprite;
import ru.snake.spritepacker.plugin.ExportPlugin;
import ru.snake.spritepacker.util.Dialogs;
import ru.snake.spritepacker.util.Util;

public class ExampleExportPlugin implements ExportPlugin {

	private static final String NAME = "sample-plugin";
	private static final String MENU_ITEM_TEXT = "Sample export plugin...";

	private static final String FILE_EXTENSION = "png";

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
	public void showExport(Component parent) {
		Sprite sprite = factory.getActiveSprite();

		if (sprite == null) {
			JOptionPane.showMessageDialog(parent, "Спрайт не выбран.", NAME,
					JOptionPane.PLAIN_MESSAGE);

			return;
		} else {
		}

		File dir = Dialogs.selectDirectory(parent);

		if (dir == null) {
			return;
		}

		File file = new File(dir, Util.getValidFileName(sprite.name,
				FILE_EXTENSION));

		try {
			ImageIO.write(sprite.texture.image, FILE_EXTENSION, file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parent, e.getLocalizedMessage(),
					NAME, JOptionPane.PLAIN_MESSAGE);

			return;
		}

		JOptionPane.showMessageDialog(parent, "Спрайт сохранен.\n", NAME,
				JOptionPane.PLAIN_MESSAGE);
	}

}
