package com.mohsen.game.application.helper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
	
	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
			
		}
		return false;
	}
	
	
	public static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) ||
				Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	
	
	public static File writeFile(String destinationFileName, byte[] data) throws IOException {
		return writeFile(new File(destinationFileName), data);
	}
	
	public static File writeFile(File destinationFile, byte[] data) throws IOException {
		if (destinationFile.getParentFile().exists() == false)
			destinationFile.getParentFile().mkdirs();
		FileOutputStream out = new FileOutputStream(destinationFile);
		out.write(data);
		out.close();
		
		return destinationFile;
	}
	
	
	public static byte[] readFileInBytes(String path) throws FileNotFoundException, IOException {
		File file = new File(path);
		return readFileInBytes(file);
	}
	
	public static byte[] readFileInBytes(File file) throws FileNotFoundException, IOException {
		
		if (file.exists() == false) return new byte[]{};
		
		int size = (int) file.length();
		byte[] bytes = new byte[size];
		
		BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
		buf.read(bytes, 0, bytes.length);
		buf.close();
		
		return bytes;
	}
	
	public static String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			
		}
		return outputStream.toString();
	}
	
	public static List<File> getListOfFilesInPath(String dirPath) {
		
		List<File> result = new ArrayList<File>();
		if (isExternalStorageReadable()) {
			
			
			File dir = new File(dirPath);
			
			if (dir.getParentFile().exists() == false)
				dir.getParentFile().mkdirs();
			
			if (dir.exists() == false)
				dir.mkdir();
			
			if (dir.listFiles() != null)
				for (File f : dir.listFiles()) {
					if (f.isFile())
						result.add(f);
				}
		}
		
		return result;
	}
	
	public static List<File> getListOfDirectoriesInPath(String dirPath) {
		
		List<File> result = new ArrayList<File>();
		if (isExternalStorageReadable()) {
			
			
			File dir = new File(dirPath);
			
			if (dir.getParentFile().exists() == false)
				dir.getParentFile().mkdirs();
			
			if (dir.exists() == false)
				dir.mkdir();
			
			for (File f : dir.listFiles()) {
				if (f.isDirectory())
					result.add(f);
			}
		}
		
		return result;
	}
	
	
	public static void copyFile(String sourcePath, String destinationPath) throws IOException {
		File srcDB = new File(sourcePath);
		File dstDB = new File(destinationPath);
		
		
		if (dstDB.getParentFile().exists() == false)
			dstDB.getParentFile().mkdirs();
		
		if (dstDB.exists() == false)
			dstDB.createNewFile();
		
		FileChannel src = new FileInputStream(srcDB).getChannel();
		FileChannel dst = new FileOutputStream(dstDB).getChannel();
		dst.transferFrom(src, 0, src.size());
		src.close();
		dst.close();
	}
	
	public static void writePersianTextToFile(String destinationFile, String Content) throws IOException {
		
		
		File outFile = new File(destinationFile);
		
		if (outFile.getParentFile().exists() == false)
			outFile.getParentFile().mkdirs();
		
		OutputStream os = new FileOutputStream(outFile);
		os.write(239);
		os.write(187);
		os.write(191);
		PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
		w.write(Content);
		w.flush();
		w.close();
	}
	
	public static String sanitizeFilename(String name) {
		return name.replaceAll("[:\\\\/*?|<>]", "_");
	}
	
	public static boolean isFileExist(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}
	
	public static String getPath(Context context, Uri uri) {
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection = {"_data"};
			Cursor cursor = null;
			
			try {
				cursor = context.getContentResolver().query(uri, projection, null, null, null);
				int column_index = cursor.getColumnIndexOrThrow("_data");
				if (cursor.moveToFirst()) {
					return cursor.getString(column_index);
				}
			} catch (Exception e) {
				// Eat it
			}
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}
		
		return null;
	}
}
