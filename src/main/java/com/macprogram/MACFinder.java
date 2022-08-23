package com.macprogram;

import java.net.InetAddress;
import java.net.NetworkInterface;

// https://www.javatpoint.com/java-swing -- as a reference for learning
// https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html -- layouts...

public class MACFinder {
	InetAddress localHost;

	public String getMac() throws Exception {
		// gets Internet address of local machine
		final InetAddress ip = InetAddress.getLocalHost();

		// get network interface that has ip address bound to it
		final NetworkInterface network = NetworkInterface.getByInetAddress(ip);

		// get MAC address from the network interface in byte
		final byte[] mac = network.getHardwareAddress();

		// displays MAC address
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}

		return ("MAC address: " + sb.toString());
	}
}
