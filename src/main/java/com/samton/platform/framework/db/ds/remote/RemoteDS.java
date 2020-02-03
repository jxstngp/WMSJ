package com.samton.platform.framework.db.ds.remote;

public class RemoteDS {
	
	private static  ThreadLocal<String> remoteDSkey=new ThreadLocal<String>();
	
	public static void setRemoteKey(String remoteKey){
		remoteDSkey.set(remoteKey);
	}
	
	public static String getRemoteKey(){
		return remoteDSkey.get();
	}
	
	public static void removeRemoteKey(){
		remoteDSkey.remove();
	}
	
}