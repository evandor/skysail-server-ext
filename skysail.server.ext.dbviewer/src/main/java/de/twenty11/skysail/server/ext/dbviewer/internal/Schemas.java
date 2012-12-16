//package de.twenty11.skysail.server.ext.dbviewer.internal;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import de.twenty11.skysail.common.ext.dbviewer.SchemaDetails;
//
//public class Schemas {
//
//    private Map<String, SchemaDetails> schemaDetails = new HashMap<String, SchemaDetails>();
//
//    public void add(SchemaDetails details) {
//        schemaDetails.put(details.getId(), details);
//    }
//
//    public Set<String> list() {
//        return schemaDetails.keySet();
//    }
//
//    public SchemaDetails get(String connectionName) {
//        return schemaDetails.get(connectionName);
//    }
//
//    public SchemaDetails delete(String key) {
//        return schemaDetails.remove(key);
//    }
//
//    public void update(String key, SchemaDetails newConnectioDetails) {
//        schemaDetails.put(key, newConnectioDetails);
//    }
//
//    public void rename(String oldName, String newName) {
//        if (schemaDetails.containsKey(newName)) {
//            throw new IllegalArgumentException("name '" + newName + "' already exists");
//        }
//        schemaDetails.put(newName, schemaDetails.get(oldName));
//        schemaDetails.remove(oldName);
//    }
//}
