package com.ibm.itacademy.animaland;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.AbstractListHandler;

public class VisitorsResultSetHandler extends AbstractListHandler<Visitor>{

	@Override
	protected Visitor handleRow(ResultSet rs) throws SQLException {
		Visitor visitor = new Visitor();
		visitor.setId(rs.getLong("id"));
		visitor.setType(VisitorType.valueOf(rs.getString("type")));
		return visitor;
	}

}
