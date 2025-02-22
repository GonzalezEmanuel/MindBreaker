package com.mindbreaker.game.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HiloCliente extends Thread {
	
	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puerto = 91218;
	private boolean fin = false;
	
	public HiloCliente() {
		
		
		try {
			ipServer = InetAddress.getByName("255.255.255.");
			conexion = new DatagramSocket(91218);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
		enviarMensaje("Conexion");
	}
	
	private void enviarMensaje(String msg) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ipServer, puerto);
		
		try {
			conexion.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		do {
			
			byte[] data = new byte[1024];
			DatagramPacket dp = new DatagramPacket(data,data.length);
			
			try {
				conexion.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
			procesarMensaje(dp);
		} while (!fin);
	}

	private void procesarMensaje(DatagramPacket dp) {
		String msg = dp.getData().toString().trim();
		
		if (msg.equals("Ok")) {
			ipServer = dp.getAddress();
		}
	}
	
}
