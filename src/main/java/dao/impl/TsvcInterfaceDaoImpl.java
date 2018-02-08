package dao.impl;

import bean.TsvcInterface;
import dao.BaseDao;
import dao.TsvcInterfaceDao;
import util.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 2.3.7.0 <br>
 * 开发人员: lyd
 * 开发时间: 2018-01-23<br>
 * <br>
 */
public class TsvcInterfaceDaoImpl extends BaseDao<TsvcInterface> implements TsvcInterfaceDao{
    @Override
    public List<TsvcInterface> getOutTsvcInterface(String uc) throws Exception {
        String sqlWhere=" where c_flag='1' and C_FUNCTIONNO ='"+uc+"' " +
                " and not exists (select 1 from TSVCVIEWCONFIG t2  where " +
                " t2.c_functionno='"+uc+"' and t2.c_property=t.c_property)" +
                " order by t.l_no,t.c_fieldname ";
        List<Map<String,Object>> maplist=getData(" t.* ",sqlWhere);
        List<TsvcInterface> hsiRightList=new ArrayList<TsvcInterface>();
        for (Map<String,Object> map : maplist){
            TsvcInterface hsiRight=new TsvcInterface();
            BeanUtils.covertMapToBean(hsiRight,map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    public List<TsvcInterface> getTsvcInterfaceList(String uc) throws Exception {
        String sqlWhere=" where C_FUNCTIONNO ='"+uc+"'";
        List<Map<String,Object>> maplist=getData(" t.* ",sqlWhere);
        List<TsvcInterface> hsiRightList=new ArrayList<TsvcInterface>();
        for (Map<String,Object> map : maplist){
            TsvcInterface hsiRight=new TsvcInterface();
            BeanUtils.covertMapToBean(hsiRight,map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    public List<TsvcInterface> getTsvcInterfaceListHasOrder(String uc) throws Exception {
        String sqlWhere=" where C_FUNCTIONNO ='"+uc+"' order by t.c_flag,t.l_no,t.c_fieldname";
        List<Map<String,Object>> maplist=getData(" t.*,t.rowid ",sqlWhere);
        List<TsvcInterface> hsiRightList=new ArrayList<TsvcInterface>();
        for (Map<String,Object> map : maplist){
            TsvcInterface hsiRight=new TsvcInterface();
            BeanUtils.covertMapToBean(hsiRight,map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    protected String getTableName() {
        return TsvcInterface.tableName;
    }

    @Override
    protected String[] createUpdateFieldNames() {
        return new String[]{"c_viewtype","c_dicname","c_isdefault","c_value","c_midsearchname",
                "l_no","c_notshowallitem","c_functionno","c_flag","c_packflag","c_fieldname",
                "c_explain","c_property","l_len","l_declen","c_fieldtype","c_fieldflag",
                "c_notnull","c_condition","c_existvalue","c_viewlevel"};
    }

    @Override
    protected String[] createInsertFieldNames() {
        return new String[]{"c_viewtype","c_dicname","c_isdefault","c_value","c_midsearchname",
                "l_no","c_notshowallitem","c_functionno","c_flag","c_packflag","c_fieldname",
                "c_explain","c_property","l_len","l_declen","c_fieldtype","c_fieldflag",
                "c_notnull","c_condition","c_existvalue","c_viewlevel"};
    }
}
