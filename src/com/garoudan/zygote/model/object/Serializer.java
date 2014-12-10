package com.garoudan.zygote.model.object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.util.Log;

public class Serializer {

	public static void write(Object object, String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
			fos.close();
		} catch(IOException excecao) {
			Log.e(Serializer.class.getCanonicalName(), excecao.getMessage());
		}
	}

	public static Object read(String path) {
		Object objeto = null;
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			objeto =  ois.readObject();
			ois.close();
			fis.close();
			return objeto;
		} catch (Exception excecao) {
			Log.e(Serializer.class.getCanonicalName(), excecao.getMessage());
		}
		
		return objeto;
	}
	
	public static <G> G read(String path, Class<G> targetClass) {
		Object objeto = read(path);
		
		if (objeto != null) {
			return targetClass.cast(objeto);
		} else {
			return null;
		}
	}
}