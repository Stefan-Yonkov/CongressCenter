package com.course.congress.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.course.congress.datastorage.CongressData;

public class Serialization {

	public static CongressData load(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        CongressData data = (CongressData) in.readObject();
        in.close();
        fileIn.close();
        
        return data;
	}

	public static void save(CongressData data, String fileName) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(data);
		out.close();
		fileOut.close();
	}

}
