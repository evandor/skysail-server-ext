//package de.twenty11.skysail.server.ext.dbviewer.internal;
//
//import javax.sql.DataSource;
//
//import de.twenty11.skysail.server.services.DataSourceProvider;
//
//public class SkysailDataSource {
//    
//    private static DataSourceProvider dataSourceProvider;
//
//    public void setDataSource(DataSourceProvider skysailDataSourceProvider) {
//        SkysailDataSource.dataSourceProvider = skysailDataSourceProvider;
//    }
//
//    public void unsetDataSource(DataSourceProvider skysailDataSourceProvider) {
//        SkysailDataSource.dataSourceProvider = null;
//    }
//
//    public static DataSource get() {
//        if (dataSourceProvider != null) 
//            return dataSourceProvider.get();
//        return null;
//    }
//    
//}
