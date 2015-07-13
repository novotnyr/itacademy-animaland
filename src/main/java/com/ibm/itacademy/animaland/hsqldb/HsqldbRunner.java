package com.ibm.itacademy.animaland.hsqldb;

import org.hsqldb.server.Server;

public class HsqldbRunner {
	public static void main(String[] args) {
		Server server = new Server();
		server.setDatabasePath(0, "db/zoo");
		server.setDatabaseName(0, "zoo");
		server.start();
	}
}
