package de.twenty11.skysail.server.ext.dbviewer.internal.entities;

// from DatabaseMetaData#getTables
//TABLE_CAT String => table catalog (may be null) 
//TABLE_SCHEM String => table schema (may be null) 
//TABLE_NAME String => table name 
//TABLE_TYPE String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM". 
//REMARKS String => explanatory comment on the table 
//TYPE_CAT String => the types catalog (may be null) 
//TYPE_SCHEM String => the types schema (may be null) 
//TYPE_NAME String => type name (may be null) 
//SELF_REFERENCING_COL_NAME String => name of the designated "identifier" column of a typed table (may be null) 
//REF_GENERATION String => specifies how values in SELF_REFERENCING_COL_NAME are created. Values are "SYSTEM", "USER", "DERIVED". (may be null) 

public class DbTable {

    private String tableCat;
    private String tableScheme;
    private String tableName;
    private String tableType;

    private String remarks;

    private String typeCat;
    private String typeScheme;
    private String typeName;

    private String selfReferencingColName;

    private String refGeneration;

    public String getTableCat() {
        return tableCat;
    }

    public void setTableCat(String tableCat) {
        this.tableCat = tableCat;
    }

    public String getTableScheme() {
        return tableScheme;
    }

    public void setTableScheme(String tableScheme) {
        this.tableScheme = tableScheme;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTypeCat() {
        return typeCat;
    }

    public void setTypeCat(String typeCat) {
        this.typeCat = typeCat;
    }

    public String getTypeScheme() {
        return typeScheme;
    }

    public void setTypeScheme(String typeScheme) {
        this.typeScheme = typeScheme;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSelfReferencingColName() {
        return selfReferencingColName;
    }

    public void setSelfReferencingColName(String selfReferencingColName) {
        this.selfReferencingColName = selfReferencingColName;
    }

    public String getRefGeneration() {
        return refGeneration;
    }

    public void setRefGeneration(String refGeneration) {
        this.refGeneration = refGeneration;
    }

}
