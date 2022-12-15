/**
 * 
 */
package com.disys.analyzer.config.dialect;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2008Dialect;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author Saiid
 * @since Nov 2, 2017
 */

/*
 * To register new data types with the hibernate that are returned from the stored procedures.
 * 
 */

public class SQLServerDBDialect extends SQLServer2008Dialect {
	public SQLServerDBDialect() {
		super();
		registerHibernateType(Types.NCHAR,
				StandardBasicTypes.CHARACTER.getName());
		registerHibernateType(Types.NCHAR, 1,
				StandardBasicTypes.CHARACTER.getName());
		registerHibernateType(Types.NCHAR, 255,
				StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.NVARCHAR,
				StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.LONGNVARCHAR,
				StandardBasicTypes.TEXT.getName());
		registerHibernateType(Types.NCLOB, StandardBasicTypes.CLOB.getName());
		
		/*registerColumnType( Types.NCLOB, "nvarchar(MAX)" );
        registerColumnType( Types.LONGNVARCHAR, "nvarchar(MAX)" );
        registerColumnType( Types.NVARCHAR, "nvarchar(MAX)" );
        registerColumnType( Types.NVARCHAR, 4000, "nvarchar($1)" );*/
		
	}
}
