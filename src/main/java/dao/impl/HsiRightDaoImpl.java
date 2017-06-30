package dao.impl;

import bean.HsiRight;
import dao.BaseDao;
import dao.HsiRightDao;
import util.BeanUtils;
import util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lyd on 2017-06-29.
 */
public class HsiRightDaoImpl extends BaseDao<HsiRight> implements HsiRightDao  {
    @Override
    public List<HsiRight> getAllData() throws Exception {
        List<Map<String,Object>> maplist=getAll();
        List<HsiRight> hsiRightList=new ArrayList<HsiRight>();
        for (Map<String,Object> map : maplist){
            HsiRight hsiRight=new HsiRight();
             BeanUtils.covertMapToBean(hsiRight,map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    public HsiRight getHsiRigh(String functionno) throws Exception {
        String sqlWhere=" where C_FUNCTIONNO ='"+functionno+"'";
        HsiRight hsiRight=new HsiRight();
        List<Map<String,Object>> maplist=getData(sqlWhere);
        if(maplist.size()>0){
            BeanUtils.covertMapToBeanWithoutNull(hsiRight,maplist.get(0));
        }
        return hsiRight;
    }

    @Override
    public List<HsiRight> getHsiRighFuzzy(String condition) throws Exception {
        List<HsiRight> hsiRightList=new ArrayList<HsiRight>();

        String sqlWhere= "";
        if (StringUtils.isNotNullAndNotEmpty(condition)) {
            sqlWhere = " where C_FUNCTIONNO like '%"+condition+"%' " +
                    " or C_RIGHTCODE like '%"+condition+"%'" +
                    " or C_RIGHTNAME like '%"+condition+"%'";
        }
        List<Map<String,Object>> maplist=getData(sqlWhere);
        for (Map<String,Object> map : maplist){
            HsiRight hsiRight=new HsiRight();
            BeanUtils.covertMapToBean(hsiRight,map);
            hsiRightList.add(hsiRight);
        }
        return hsiRightList;
    }

    @Override
    protected String getTableName() {
        return HsiRight.getTableName();
    }

}
