package service;

import bean.HsiRight;

import java.util.List;

/**
 * Created by lyd on 2017-06-30.
 * uc定义
 */
public interface UcDefineService {
    public List<HsiRight> getAllUc() throws Exception;
    public List<HsiRight> getAllUc( String condition) throws Exception;
    public HsiRight getUc(String functionno) throws Exception;
    /**
     *
     * @param delList
     * @param addUcList
     * @param editUcList
     * @throws Exception
     */
    public void save(List<String> delList,List<HsiRight> addUcList,List<HsiRight> editUcList) throws Exception;

}
