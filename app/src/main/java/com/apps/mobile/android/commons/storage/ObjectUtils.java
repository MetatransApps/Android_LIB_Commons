package com.apps.mobile.android.commons.storage;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class ObjectUtils {

	
	public static Object copyObject(Object src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		OutputStream buffer1 = new BufferedOutputStream(baos);
		ObjectOutput output = new ObjectOutputStream(buffer1);
		
		output.writeObject(src);
		
		output.flush();
		output.close();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		InputStream buffer2 = new BufferedInputStream(bais);
		ObjectInputStream input = new ObjectInputStream(buffer2);
		return input.readObject();
	}
	
	
	public static void writeObjectToStreem(OutputStream os, Object obj) throws IOException {
		OutputStream buffer = new BufferedOutputStream(os);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(obj);
		output.flush();
	}
	
	
	public static Object readObjectFromStreem(InputStream os) throws IOException, ClassNotFoundException {
		InputStream buffer = new BufferedInputStream(os);
		ObjectInputStream output = new ObjectInputStream(buffer);
		return output.readObject();
	}
}
