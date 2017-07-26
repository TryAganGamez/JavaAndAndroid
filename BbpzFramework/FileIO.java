package com.bbpz.Framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;

//This interface is pretty straight forward...We handle IOExceptions so that
//we don't need a try and catch statement.
//SharedPreferences is an Android interface that lets you access and modify
//preference data.

public interface FileIO {
	public InputStream readFile(String file)throws IOException;
	public OutputStream writeFile(String file)throws IOException;
	public InputStream readAsset(String file)throws IOException;
	public SharedPreferences getSharedPref();	

}
