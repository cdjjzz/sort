package com.sort;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class NetConfig {
	
	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> interfaceNetworkInterface=NetworkInterface.getNetworkInterfaces();
		while(interfaceNetworkInterface.hasMoreElements()){
			NetworkInterface networkInterface=interfaceNetworkInterface.nextElement();
			List<InterfaceAddress> ll=networkInterface.getInterfaceAddresses();
			for(InterfaceAddress l:ll){
				System.out.println(l.getBroadcast());
			}
			System.out.println();
		}
	}

}
