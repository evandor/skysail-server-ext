//package de.twenty11.skysail.server.ext.dbviewer.internal;
//
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.ResultSet;
//
//import javax.sql.DataSource;
//
//import de.twenty11.skysail.server.ext.dbviewer.internal.entities.DbTable;
//
//public class DbTableService {
//
//    public DbTable getFromDb(DataSource ds) throws Exception {
//
//        DbTable result = new DbTable();
//        Connection connection = ds.getConnection();
//        DatabaseMetaData meta = connection.getMetaData();
//
//        ResultSet tables = meta.getTables(null, null, null, new String[] { "TABLE" });
//        while (tables.next()) {
//            result.setTableName(tables.getString("TABLE_NAME"));
//        }
//        return result;
//    }
//
//}
