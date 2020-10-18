

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class JvmClassLoaderPrintPath {
	
	public static void main(String[] args) {
		// �����������
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		System.out.println("�����������");
		for (URL url : urls) {
			System.out.println(" ==> " + url.toExternalForm());
		}
		
		// ��չ�������
		printClassLoader("��չ�������", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
		
		// Ӧ���������
		printClassLoader("Ӧ���������", JvmClassLoaderPrintPath.class.getClassLoader());
	}
	
	public static void printClassLoader(String name, ClassLoader CL) {
		if (CL != null) {
			System.out.println(name + " ClassLoader -> " + CL.toString());
			printURLForClassLoader(CL);
		} else {
			System.out.println(name + " ClassLoader -> null");
		}
	}

	private static void printURLForClassLoader(ClassLoader CL) {
		Object ucp = insightField(CL, "ucp");
		Object path = insightField(ucp, "path");
		ArrayList ps = (ArrayList) path;
		for (Object p : ps) {
			System.out.println(" ==> " + p.toString());
		}
	}

	private static Object insightField(Object obj, String name) {
		try {
			Field f = null;
			if (obj instanceof URLClassLoader) {
				f = URLClassLoader.class.getDeclaredField(name);
			} else {
				f = obj.getClass().getDeclaredField(name);
			}
			f.setAccessible(true);
			return f.get(obj);
		} catch( Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}