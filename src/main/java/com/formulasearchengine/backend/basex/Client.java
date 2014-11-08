package com.formulasearchengine.backend.basex;

import net.xqj.basex.BaseXXQDataSource;

import javax.xml.xquery.*;

/**
 * Created by Moritz on 08.11.2014.
 */
public class Client {
	/**
	 * Connects with the BaseX database, sending the given query and saves the
	 * result in a list
	 */
	public static Long basex(String query) {
		XQDataSource xqs = new BaseXXQDataSource();
		Long measurement = -1L;
		try {
			xqs.setProperty("serverName", "localhost");
			xqs.setProperty("port", "1984");
			xqs.setProperty("databaseName", "math");

			XQConnection conn = xqs.getConnection("admin", "admin");

			XQPreparedExpression xqpe = conn.prepareExpression(query);
			measurement = System.nanoTime();
			XQResultSequence rs = xqpe.executeQuery();
			measurement = System.nanoTime() - measurement;

			while (rs.next()) {
				System.out.println(rs.getItemAsString( null ));
				//basexReplies.add(rs.getItemAsString(null));
			}

			conn.close();
		} catch (XQException e) {
			e.printStackTrace();
			return measurement;
		}

		return measurement;
	}
}
