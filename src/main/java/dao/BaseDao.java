package dao;

import bean.BaseBean;
import bean.SystemData;
import org.apache.log4j.Logger;
import util.BeanUtils;
import util.LogUtil;
import util.StringUtils;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by lyd on 2017-06-29.
 */
public abstract  class BaseDao <TDtoModel extends BaseBean> implements IBaseDao<TDtoModel> {
    private static Logger logger= LogUtil.getLogger(BaseDao.class);
    protected abstract  String getTableName();

    /**
     * 获取可以更新的字段名称列表
     * 需与对应的实体类的字段的名称一致
     * 全部小写
     * 不包含字段：urid,rowversion
     *
     * @return
     */
    protected abstract String[] createUpdateFieldNames();

    /**
     * 获取添加操作时的字段
     * 区分大小写，需与对应的实体类的字段的名称一致
     *
     * @return
     */
    protected abstract String[] createInsertFieldNames();
    /**
     * 开启事务
     */
    public void openTransaction() throws Exception {
        getConn();
        connection.setAutoCommit(false);

    }
    /**
     * 回滚事务
     */
    public void rollbackTransaction()  {
        try {
            getConn();
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            logger.error("回滚数据异常",e);
        }
    }
    /**
     * 提交事务
     */
    public void commitTransaction() throws Exception {
        getConn();
        connection.commit();
        connection.setAutoCommit(true);
    }
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
    public Statement getStatement() throws Exception {
        connection=getConn();
        Statement pstmt=connection.createStatement();
        return pstmt;
    }
    public int del(TDtoModel dtoModel) throws Exception {
        String sql="delete "+getTableName()+" where "+dtoModel.getKeyStr().get(0)+"='"+dtoModel.getKeyValue()+"'";
        return executeSql(sql);
    }
    public boolean insert(TDtoModel dtoModel) throws Exception {
        //获取model的map<属性名称，属性值>
        Map<String, Object> mapFields = BeanUtils.getProperties(dtoModel);
        //可更新的字段的名称的列表
        String[] insertFieldNames = this.createInsertFieldNames();
        //SQL语句（参数化）
        StringBuilder stringSqlBuilder = new StringBuilder();
        //SQL语句所对应的参数
        List<Object> objectSqlParams = new ArrayList<Object>();
        //SQL语句字段部分
        StringBuilder stringSqlFields = new StringBuilder();
        //SQL语句字段值部分
        StringBuilder stringSqlValues = new StringBuilder();
        //是否为第一次循环，用于遍历时ＳＱＬ组装
        boolean isFirst = true;
        //执行ＳＱＬ语句的返回结果
        int tmpCount;

        Object objFieldValue = null;
        for (String filedName : insertFieldNames) {
            if (!isFirst) {
                stringSqlFields.append(",");
                stringSqlValues.append(",");
            }
            objFieldValue = mapFields.get(filedName);
            if (objFieldValue instanceof java.util.Date) {
                objectSqlParams.add(new java.sql.Timestamp(((java.util.Date) objFieldValue).getTime()));
            } else {
                objectSqlParams.add(objFieldValue);
            }
            stringSqlFields.append(String.format(" %s ", filedName));
            stringSqlValues.append("?");
            isFirst = false;
        }
        stringSqlBuilder.append(String.format("insert into %s (%s) values(%s) ", getTableName(), stringSqlFields.toString(), stringSqlValues.toString()));

        tmpCount = this.executeSQL(stringSqlBuilder.toString(), objectSqlParams.toArray());
        if (1 == tmpCount) {
            return true;
        } else {
            return false;
        }
    }

    public void update(TDtoModel dtoModel) throws Exception {
         update(dtoModel, null);
    }

    public void update(TDtoModel dtoModel, String[] fieldNames) throws Exception {

        //获取model的map<属性名称，属性值>
        Map<String, Object> mapFields = BeanUtils.getProperties(dtoModel);
        //可更新的字段的名称的列表
        String[] updateFieldNames = this.createUpdateFieldNames();
        //SQL语句（参数化）
        StringBuilder stringSqlBuilder = new StringBuilder();
        //SQL语句所对应的参数
        List<Object> objectSqlParams = new ArrayList<Object>();
        //是否为自定义的字段更新
        boolean isUpdateField;
        if (null != fieldNames && fieldNames.length > 0) {
            isUpdateField = true;
        } else {
            isUpdateField = false;
        }

        stringSqlBuilder.append(String.format("UPDATE %s SET  ", this.getTableName()));

        Object objFieldValue;
        boolean isfirst=true;
        for (String filedName : updateFieldNames) {

            if (isUpdateField) {
                if (!this.checkChild(fieldNames, filedName)) {
                    //如果字段不在需要更新的字段中，则跳过
                    continue;
                }
            }
            objFieldValue = mapFields.get(filedName.toLowerCase());
            if (objFieldValue instanceof java.util.Date) {
                objectSqlParams.add(new java.sql.Timestamp(((java.util.Date) objFieldValue).getTime()));
            }else {
                objectSqlParams.add(objFieldValue);
            }
            if (isfirst) {
                stringSqlBuilder.append(String.format("%s=?", filedName));
                isfirst=false;
            } else {
                stringSqlBuilder.append(String.format(",%s=?", filedName));
            }
        }

        stringSqlBuilder.append(" WHERE 1=1  ");
        for(String key : dtoModel.getKeyStr()){
            stringSqlBuilder.append(" and "+key+"=?  ");
        }
        for(String keyValue : dtoModel.getKeyValueStr()){
            objectSqlParams.add(mapFields.get(keyValue.toLowerCase()));
        }


        int tmpCount = this.executeSQL(stringSqlBuilder.toString(), objectSqlParams.toArray());
        if (1 != tmpCount) {
           throw new Exception("更新行数为："+tmpCount);
        }
    }
    /**
     * 检查字符串数组中是否包含有一个字符串
     *
     * @param parentsList 数组
     * @param checkValue  字符串
     * @return 包含：true；不包含：false
     */
    protected boolean checkChild(String[] parentsList, String checkValue) {
        if (parentsList != null && parentsList.length > 0) {
            for (String childValue : parentsList) {
                if (childValue.equals(checkValue)) {
                    return true;
                }
            }
        }
        return false;
    }
    protected int executeSQL(String sql, Object[] params) throws Exception {
        int result;
        PreparedStatement pstmt = getPreStmt(sql);
        try {
            result = 0;
            fillStatement(pstmt, params);
            result = pstmt.executeUpdate();
        } finally {
            pstmt.close();
        }
        return result;
    }
    private static void fillStatement(PreparedStatement pstmt, Object... params) throws SQLException {
        if(params != null) {
            int i = 0;

            for(int n = params.length; i < n; ++i) {
                if(params[i] != null) {
                    pstmt.setObject(i + 1, params[i]);
                } else {
                    pstmt.setNull(i + 1, 12);
                }
            }

        }
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
        try {
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsm =rs.getMetaData(); //获得列集
            int col = rsm.getColumnCount();
            while (rs.next()){
                Map<String,Object> map=new HashMap<String,Object>();
                for (int i = 1; i <=col ; i++) {
                    String columnName=rsm.getColumnLabel(i);
                    map.put(columnName,rs.getString(columnName));
                }
                retMapList.add(map);
            }
        } finally {
            pstmt.close();
        }
        return retMapList;
    }
    public  List<Map<String,Object>> queryForList(String sql)throws Exception {
        List<Map<String,Object>> retMapList=new ArrayList<Map<String,Object>>();
        PreparedStatement pstmt=getPreStmt(sql);
        try {
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsm =rs.getMetaData(); //获得列集
            int col = rsm.getColumnCount();
            while (rs.next()){
                Map<String,Object> map=new HashMap<String,Object>();
                for (int i = 1; i <=col ; i++) {
                    String columnName=rsm.getColumnLabel(i);
                    map.put(columnName,rs.getString(columnName));
                }
                retMapList.add(map);
            }
        } finally {
            pstmt.close();
        }
        return retMapList;
    }
    public int executeSql(String sql) throws Exception {
        Statement statement=getStatement();
        try {
            return statement.executeUpdate(sql);
        } finally {
            statement.close();
        }
    }

    private Jdbcinfo getConninfo(){
        Jdbcinfo  jdbcinfo=new Jdbcinfo();

        jdbcinfo.setUrl(SystemData.getDataConnInfo().getJdbcurl());
        jdbcinfo.setPaswword(SystemData.getDataConnInfo().getPaswword());
        jdbcinfo.setUsername(SystemData.getDataConnInfo().getUsername());

        if(SystemData.DataConnInfo.MYSQL.equalsIgnoreCase(SystemData.getDataConnInfo().getDrivertype())){
            jdbcinfo.setDriver("com.mysql.jdbc.Driver");
        }else{
            jdbcinfo.setDriver("oracle.jdbc.driver.OracleDriver");
        }
        /*jdbcinfo.setUrl( "jdbc:mysql://localhost:3306/crm?"+
                "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

        jdbcinfo.setPaswword("root");
        jdbcinfo.setUsername("root");
        */
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
