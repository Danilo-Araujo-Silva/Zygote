package com.garoudan.zygote.model.object;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.util.Log;

public class Serializer {

	public static void write(Object objeto, String caminho) {
		try {
			FileOutputStream fos = new FileOutputStream(caminho);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(objeto);
			oos.close();
			fos.close();
		} catch(IOException excecao) {
			Log.e(Serializer.class.getCanonicalName(), excecao.getMessage());
		}
	}

	public static Object read(String caminho) {
		Object objeto = null;
		
		try {
			FileInputStream fis = new FileInputStream(caminho);
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
	
	public static <G> G read(String caminho, Class<G> c) {
		Object objeto = read(caminho);
		
		if (objeto != null) {
			return c.cast(objeto);
		} else {
			return null;
		}
	}
}