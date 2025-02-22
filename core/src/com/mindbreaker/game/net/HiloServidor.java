package com.mindbreaker.game.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class HiloServidor extends Thread {
	
	private DatagramSocket conexion;
	private boolean fin = false;
	private DireccionRed[] clientes = new DireccionRed[2];
	private int cantClientes = 0;
	
	public HiloServidor() {
		
		
		try {
			
			conexion = new DatagramSocket(91218);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	private void enviarMensaje(String msg, InetAddress ip, int puerto) {
		byte[] data = msg.getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ip, puerto);
		
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
		
		if (msg.equals("Conexion")) {
			if (cantClientes < 2) {
				clientes[cantClientes++] = new DireccionRed(dp.getAddress(), dp.getPort());
				enviarMensaje("Ok", clientes[cantClientes].getIp(), clientes[cantClientes].getPuerto());
			} else if (cantClientes == 2) {
				for (int i = 0; i < clientes.length; i++) {
					// enviarMensaje("Empieza", clientes[i].getIp(), clientes);
				}
			}
		}
	
	}
}
