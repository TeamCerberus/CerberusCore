package teamcerberus.cerberuscore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.client.Minecraft;

public class FileUtil {
	public static void saveObject(Object o, File file) {
		saveObject(o, file.getPath());
	}

	public static Object readObject(File file) {
		return readObject(file.getPath());
	}
	
	public static void saveObject(Object o, String file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
		} catch (IOException i) {
		}
	}

	public static Object readObject(String file) {
		try {
			Object o;
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
			return o;
		} catch (Exception i) {
		}
		return null;
	}
}
