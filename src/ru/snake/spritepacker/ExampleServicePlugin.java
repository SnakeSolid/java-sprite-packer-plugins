package ru.snake.spritepacker;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import ru.snake.spritepacker.core.Animation;
import ru.snake.spritepacker.core.CoreFactory;
import ru.snake.spritepacker.core.Sprite;
import ru.snake.spritepacker.plugin.ServicePlugin;

public class ExampleServicePlugin implements ServicePlugin {

	private static final String NAME = "sample-service-plugin";
	private static final String MENU_ITEM_TEXT = "Sample service plugin...";

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
	public void showService(Component parent) {
		Animation animation = factory.getActiveAnimation();

		if (animation == null) {
			JOptionPane.showMessageDialog(parent, "Анимация не выбрана.", NAME,
					JOptionPane.PLAIN_MESSAGE);

			return;
		}

		List<Sprite> sprites = new ArrayList<Sprite>(animation.getSprites());

		Collections.shuffle(sprites);

		animation.setSprites(sprites);
		factory.updateSprites();

		JOptionPane.showMessageDialog(parent, "Анимация перемешана.\n", NAME,
				JOptionPane.PLAIN_MESSAGE);
	}

}
