package teamcerberus.cerberuscore.config;

import java.lang.reflect.Field;
import java.util.Locale.Category;

import teamcerberus.cerberuscore.util.CerberusLogger;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigurationParser {

	/**
	 * Parse the passed class for annotated fields with the
	 * <i>@ConfigurationOption</i>
	 * 
	 * @author Cayde Dixon (cazzar)
	 * @param instance
	 *            the instance of the configurations option if it is non static
	 * @param config
	 */
	@SuppressWarnings("rawtypes")
	public static void Parse(Object instance, Configuration config) {
		Class clazz = instance.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (!field.isAccessible()) field.setAccessible(true);
			
			ConfigurationOption annotation = field
					.getAnnotation(ConfigurationOption.class);
			if (annotation == null) {
				continue;
			}

			String category = annotation.category();
			String comment = annotation.comment();
			String key = annotation.key();

			try {
				parseField(field, instance, category, key, comment, config);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			if (config.hasChanged()) {
				config.save();
			}
		}
	}

	private static void parseField(Field field, Object instance,
			String category, String key, String comment, Configuration config)
			throws IllegalArgumentException, IllegalAccessException {
		Class type = field.getType();

		if (type == boolean.class || type == Boolean.class) {
			Object def = field.get(instance);

			Object value;
			if (!comment.isEmpty()) value = config.get(category, key,
					(Boolean) def, comment).getBoolean((Boolean) def);
			else value = config.get(category, key, (Boolean) def).getBoolean(
					(Boolean) def);

			field.set(instance, value);
		} else if (type == double.class || type == Double.class) {
			Object def = field.get(instance);

			Object value;
			if (!comment.isEmpty()) value = config.get(category, key,
					(Double) def, comment).getDouble((Double) def);
			else value = config.get(category, key, (Double) def).getDouble(
					(Double) def);

			field.set(instance, value);
		} else if (type == String.class) {
			Object def = field.get(instance);

			Object value;
			if (!comment.isEmpty()) value = config.get(category, key,
					(String) def, comment).getString();
			else value = config.get(category, key, (String) def).getString();

			field.set(instance, value);
		} else if (type == Integer.class || type == int.class) {
			if (field.isAnnotationPresent(BlockID.class)) {
				Object def = field.get(instance);

				Object value;
				if (!comment.isEmpty()) value = config.getBlock(category, key,
						(Integer) def, comment).getInt();
				else value = config.getBlock(category, key, (Integer) def).getInt();
				
				field.set(instance, value);
				return;
			}
			if (field.isAnnotationPresent(ItemID.class)) {
				Object def = field.get(instance);

				Object value;
				if (!comment.isEmpty()) value = config.getItem(category, key,
						(Integer) def, comment).getInt();
				else value = config.getItem(category, key, (Integer) def).getInt();
				
				field.set(instance, value);
				return;
			}
			
			Object def = field.get(instance);

			Object value;
			if (!comment.isEmpty()) value = config.get(category, key,
					(Integer) def, comment).getInt((Integer) def);
			else value = config.get(category, key, (Integer) def).getInt(
					(Integer) def);

			field.set(instance, value);
		} else if (type == float.class) {
			double def = field.getFloat(instance);
			double value;
			if (!comment.isEmpty()) value = config.get(category, key, def,
					comment).getDouble(def);
			else value = config.get(category, key, def).getDouble(def);

			float actualValue = Float.valueOf(String.valueOf(value));
			field.setFloat(instance, actualValue);

		} else {
			CerberusLogger.logWarning("Type \"" + type.getName()
					+ "\" is not supportd with annotations");
		}
	}	

	public static void ParseClass(Object instance, Configuration config) {
		Class clazz = instance.getClass();
		
		ConfigurationClass annotation = (ConfigurationClass) clazz
				.getAnnotation(ConfigurationClass.class);
		if (annotation == null) {
			return;
		}
		String category = (annotation.category().isEmpty()) ? clazz.getSimpleName() : annotation.category();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			if (!field.isAccessible()) field.setAccessible(true);
			ConfigurationComment commentAnnotation = field.getAnnotation(ConfigurationComment.class);
			String comment = (commentAnnotation == null) ?  "" : commentAnnotation.value();
			String key = field.getName();
			
			try {
				parseField(field, instance, category, key, comment, config);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}
