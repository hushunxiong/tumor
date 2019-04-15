package com.wonders.health.tumor.test.gen;


import com.wonders.health.tumor.gen.utils.GenUtils;

/**
 *
 */
public class Main {

    public static final String ENTITY_TYPE_GRID = "grid";
//    public static final String ENTITY_TYPE_TREE = "tree";

    static {
        String packageName = "com.wonders.health.tumor";
        String dbName = GenUtils.ORACLE;
        String dbIp = "10.240.21.10";
        String dbPort = "1521";
        String db = "ora11g";
        String username = "zlzfx";
        String password = "ZLZFX";
        GenUtils.init(packageName, dbName, dbIp, dbPort, db, username, password);
    }

    public static void main(String[] args) {
        createCancerHistory();
    }

    private static void createCancerHistory() { //
        String schema = "ZLZFX";
        String tableName = "CANCER_HISTORY";
        String moduleName = "tumor";
        String className = "CancerHistory";
        GenUtils.createMain(schema, tableName, moduleName, className, ENTITY_TYPE_GRID, "历史", "xuguobing");
    }

}
