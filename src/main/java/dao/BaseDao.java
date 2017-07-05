package dao;

import util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2017-06-29.
 */
public abstract  class BaseDao <TDtoModel> {
    protected abstract  String getTableName();

    //所有界面共用一个连接，没啥问题
    private   static Connection connection;
    protected Connection getConn() throws Exception {
        if(connection==null){
            synchronized (this){
                if(connection==null){
                    Jdbcinfo jdbcinfo=getConninfo();
                    Class.forName(jdbcinfo.getDriver());
                    connection= DriverManager.getConnection(jdbcinfo.getUrl(),jdbcinfo.getUsername(),jdbcinfo.getPaswword());
                }
            }

        }
        return this.connection;
    }
    public PreparedStatement getPreStmt(String sql) throws Exception {
        connection=getConn();
        PreparedStatement pstmt=connection.prepareStatement(sql);
        return pstmt;
    }
    public int insert(TDtoModel dtoModel){


        return 0;
    }

    public int update(TDtoModel dtoModel){


        return 0;
    }

    protected List<Map<String,Object>> getAll() throws Exception {

        return getData("");
    }

    protected List<Map<String,Object>> getData(String sqlWhere)throws Exception {
        return getData("",sqlWhere);
    }
    protected List<Map<String,Object>> getData(String filed,String sqlWhere)throws Exception {
        List<Map<String,Object>> retMapList=new ArrayList<Map<String,Object>>();
        if(StringUtils.isNullOrEmpty(filed)){
            filed="*";
        }
        String sql="select "+filed+"  from "+getTableName()+" t ";
        if(StringUtils.isNotNullAndNotEmpty(sqlWhere)){
            sql+=" "+sqlWhere;
        }
        PreparedStatement pstmt=getPreStmt(sql);
        ResultSet rs=pstmt.executeQuery();
        ResultSetMetaData rsm =rs.getMetaData(); //获得列集
        int col = rsm.getColumnCount();
        while (rs.next()){
            Map<String,Object> map=new HashMap<String,Object>();
            for (int i = 1; i <=col ; i++) {
                String columnName=rsm.getColumnName(i);
                map.put(columnName,rs.getString(columnName));
            }
            retMapList.add(map);
        }
        return retMapList;
    }

    private Jdbcinfo getConninfo(){
        Jdbcinfo  jdbcinfo=new Jdbcinfo();
        jdbcinfo.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:mcs100");
        jdbcinfo.setPaswword("test1");
        jdbcinfo.setUsername("jats221");
        jdbcinfo.setDriver("oracle.jdbc.driver.OracleDriver");
        return jdbcinfo;
    }

    class Jdbcinfo{
        private String url;
        private String username;
        private String paswword;
        private String driver;

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPaswword() {
            return paswword;
        }

        public void setPaswword(String paswword) {
            this.paswword = paswword;
        }
    }
}
