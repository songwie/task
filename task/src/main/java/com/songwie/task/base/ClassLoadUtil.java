package com.songwie.task.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class ClassLoadUtil extends ClassLoader {
	public synchronized Class loadClass(String className, String jarpath) throws ClassNotFoundException {
		Class result = null;
		byte[] classData = null;
		try {
			classData = getByteArrayFromJarFile(className, jarpath);
			result = defineClass(className, classData, 0, classData.length);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private byte[] getByteArrayFromJarFile(String className, String jarpath) {
		int len;
		byte buffer[] = new byte[1024];
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarpath);
		} catch (Exception ex) {
		}
		className = className.replace('.', '/');
		try {
			ZipEntry zipEntry = jarFile.getEntry(className + ".class");
			if (zipEntry != null) {
				try {
					InputStream inputStream = jarFile.getInputStream(zipEntry);
					int arrayLength = inputStream.available();
					byte[] bytes = new byte[arrayLength];

					int pos = 0;
					while (true) {
						int n = inputStream.read(bytes, pos, arrayLength - pos);
						if (n <= 0)
							break;
						pos += n;
					}
					inputStream.close();
					return bytes;
				} catch (IOException exp) {
					exp.printStackTrace();
				}
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
			return null;
		}
		return null;
	}
}