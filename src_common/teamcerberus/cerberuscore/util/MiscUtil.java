package teamcerberus.cerberuscore.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MiscUtil {
	public static List<String> addPrefixToList(List<String> o, String prefix) {
		List<String> n = new ArrayList<String>();
		for (String s : o) {
			n.add(prefix + s);
		}
		return n;
	}

	public static List<String> removePrefixFromList(List<String> o,
			String prefix) {
		List<String> n = new ArrayList<String>();
		for (String s : o) {
			n.add(s.replaceFirst(prefix, ""));
		}
		return n;
	}

	public static String[] addPrefixToArray(String[] o, String prefix) {
		String[] n = new String[o.length];
		for (int i = 0; i < o.length; i++) {
			n[i] = prefix + o[i];
		}
		return n;
	}

	public static String[] removePrefixFromArray(String[] o, String prefix) {
		String[] n = new String[o.length];
		for (int i = 0; i < o.length; i++) {
			n[i] = o[i].replaceFirst(prefix, "");
		}
		return n;
	}
	
	public static byte[] objectToBytes(Object obj) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(out);
			os.writeObject(obj);
			return out.toByteArray();
		} catch (Exception e) {}
		return new byte[] {};

	}

	public static Object objectFromBytes(byte[] data) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			ObjectInputStream is = new ObjectInputStream(in);
			return is.readObject();
		} catch (Exception e) {}
		return null;
	}
}
