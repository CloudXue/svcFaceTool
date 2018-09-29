package service.impl;

import constant.ENWarningLevel;
import constant.EnActionEvent;
import dao.DaoFactory;
import dao.GenCodeDao;
import exception.ViewException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.SvcService;
import util.LogUtil;

/**
 * 功能说明:
 * 系统版本: 2.4.2.0
 *
 * @author: lyd
 * 开发时间: 2018-09-29
 */
public class GenerateCodeServiceImpl {
    private static Logger logger = LogUtil.getLogger(GenerateCodeServiceImpl.class);
    GenCodeDao genCodeDao = DaoFactory.getGenCodeDao();
    SvcService svcService = ServiceFactory.getSvcService();




}
