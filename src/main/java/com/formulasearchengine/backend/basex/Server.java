package com.formulasearchengine.backend.basex;

import org.basex.BaseXServer;
import org.basex.core.cmd.CreateDB;
import org.basex.server.ClientQuery;
import org.basex.server.ClientSession;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Moritz on 08.11.2014.
 */
public class Server {
	private final BaseXServer server;
	private final ClientSession session;

	public Server () throws IOException {
		server = new BaseXServer();
		session = new ClientSession("localhost", 1984, "admin", "admin");
	}

	public void importData(String path) throws IOException {
		session.execute(new CreateDB("math", path));
	}

	public void runQuery(String queryString, PrintStream output) throws IOException {
		session.setOutputStream(output);
		ClientQuery query = session.query( queryString );
		query.execute();
	}

	public ClientQuery getQuery(String queryString) throws IOException {
		return session.query( queryString );
	}




}
