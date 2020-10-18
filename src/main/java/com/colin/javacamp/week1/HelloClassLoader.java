package com.colin.javacamp.week1;

import java.util.Base64;

public class HelloClassLoader extends ClassLoader {

	public static void main(String[] args) {
		try {
			new HelloClassLoader().findClass("com.colin.javacamp.week1.Hello").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String helloBase64 = "yv66vgAAADEAHwoABgARCQASABMIABQKABUAFgcAFwcAGAEABjxpbml0PgEAAygpVgEAB"
				+ "ENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQAgTGNvbS9jb"
				+ "2xpbi9qYXZhY2FtcC93ZWVrMS9IZWxsbzsBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qY"
				+ "XZhDAAHAAgHABkMABoAGwEAGEhlbGxvIENsYXNzIEluaXRpYWxpemVkIQcAHAwAHQAeAQAeY29tL2Nvb"
				+ "GluL2phdmFjYW1wL3dlZWsxL0hlbGxvAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9TeXN0Z"
				+ "W0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFtAQAHcHJpb"
				+ "nRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgAhAAUABgAAAAAAAgABAAcACAABAAkAAAAvAAEAAQAAA"
				+ "AUqtwABsQAAAAIACgAAAAYAAQAAAAMACwAAAAwAAQAAAAUADAANAAAACAAOAAgAAQAJAAAAJQACAAAAA"
				+ "AAJsgACEgO2AASxAAAAAQAKAAAACgACAAAABQAIAAYAAQAPAAAAAgAQ";
		byte[] bytes = decode(helloBase64);
		return defineClass(name, bytes, 0, bytes.length);
	}

	public byte[] decode(String base64) {
		return Base64.getDecoder().decode(base64);
	}

}
