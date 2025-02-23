package com.mindbreaker.game.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.mindbreaker.game.pantallas.PantallaJuegoMultijugador;
import com.mindbreaker.game.utiles.Globales;

public class HiloServidor extends Thread {
	
	private DatagramSocket conexion;
	private boolean fin = false;
	private DireccionRed[] clientes = new DireccionRed[2];
	private int cantClientes = 0;
	private PantallaJuegoMultijugador app;
	
	public HiloServidor(PantallaJuegoMultijugador app) {
		this.app = app;
		
		try {
			
			conexion = new DatagramSocket(9998);
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
		String msg = (new String(dp.getData())).trim();
		int nroCliente = -1;
		
		if (cantClientes > 1) {
			for (int i = 0; i < clientes.length; i++) {
				if (dp.getPort()==clientes[i].getPuerto() && dp.getAddress().equals(clientes[i].getIp())) {
					nroCliente = i;
				}
			}
		}
		System.out.println("Clientes actuales: " + cantClientes);
		System.out.println("Intento de conexión de: " + dp.getAddress() + ":" + dp.getPort());
		
		if (cantClientes < 2) {
			if (msg.equals("Conexion")) {
				
				if (cantClientes < 2) {
					clientes[cantClientes] = new DireccionRed(dp.getAddress(), dp.getPort());
					enviarMensaje("Ok", clientes[cantClientes].getIp(), clientes[cantClientes++].getPuerto());
				} else if (cantClientes == 2) {
					Globales.empieza = true;
					for (int i = 0; i < clientes.length; i++) {

						enviarMensaje("Empieza", clientes[i].getIp(), clientes[i].getPuerto());

					}
				}
			}
		} else {
			if (nroCliente !=-1) {
				if (msg.equals("Abajo")) {
					if (nroCliente==0) {
						app.isDown1 = true;
					} else {
						app.isDown2 = true;
					}
				} else if (msg.equals("Arriba")) {
					if (nroCliente==0) {
						app.isUp1 = true;
					} else {
						app.isUp2 = true;
					}
				} else if (msg.equals("DejeApretarAbajo")) {
					if (nroCliente==0) {
						app.isDown1 = false;
					} else {
						app.isDown2 = false;
					}
				} else if (msg.equals("DejeApretarArriba")) {
					if (nroCliente==0) {
						app.isUp1 = false;
					} else {
						app.isUp2 = false;
					}
				}
			}
		}
	}
}
