package gurecn.testloader;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader extends ClassLoader {

	private String jarFile;
	private boolean isEncrypt;

	public JarClassLoader(String jarFile, boolean isEncrypt) {
		this.jarFile =  jarFile;
		this.isEncrypt = isEncrypt;
	}

	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		try {
			JarFile jar = new JarFile(this.jarFile);
			String cla = className.replace('.', '/') + ".class";
			JarEntry entry = jar.getJarEntry(cla);
			if (entry == null) {
				throw new ClassNotFoundException(className + ".class");
			}
			InputStream is = jar.getInputStream(entry);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			if(isEncrypt) {
				EnDecrpt(is, byteStream);//解密
			} else {
				int read = 0;
				byte[] buffer = new byte[4096];
				while ((read = is.read(buffer)) != -1) {
					if (read > 0) {
						byteStream.write(buffer, 0, read);
					}
				}
			}
			byte[] classByte = byteStream.toByteArray();
			Class result = super.defineClass(className, classByte, 0, classByte.length, null);
			if (result == null) {
				System.err.println(className + ".class");
			}
			return result;
		} catch (Exception e) {
			return super.findSystemClass(className);
		}
	}

	public static void EnDecrpt(InputStream in, OutputStream out) throws IOException{
		int b=-1;
		while((b=in.read())!=-1){
			out.write(b^0xff);
		}
	}

}
