package gurecn.testloader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestGurecn {
    public static void main(String[] args) throws InstantiationException, ClassNotFoundException, IllegalAccessException {

//        encrpt("C:\\Users\\Administrator\\Desktop\\CoreSdkMaster.class","C:\\Users\\Administrator\\Desktop\\sdkjiami\\CoreSdkMaster.class");

        System.out.println("start");
        String sdkPath = "C:\\Users\\Administrator\\Desktop\\sdk\\sdk_java.jar";
        JarClassLoader cl = new JarClassLoader(sdkPath, false);
        try {
            Class clazz = cl.loadClass("gurecn.testloader.CoreSdkMaster");
            Method method = clazz.getMethod("nativeSdkAuth");
            method.invoke(clazz.newInstance());
        } catch (InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        String sdkEncryptPath = "C:\\Users\\Administrator\\Desktop\\sdk\\sdk_java.jar";
        JarClassLoader clEncrypt = new JarClassLoader(sdkEncryptPath, false);
        try {
            Class clazz = clEncrypt.loadClass("gurecn.testloader.CoreSdkMaster");
            Method method = clazz.getMethod("nativeSdkAuth");
            method.invoke(clazz.newInstance());
        } catch (InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }


    private static void encrpt(String srcPath,String desPath){
        try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(desPath);
            JarClassLoader.EnDecrpt(fis, fos);
            fis.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
