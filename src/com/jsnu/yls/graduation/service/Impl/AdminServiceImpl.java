package com.jsnu.yls.graduation.service.Impl;

import com.jsnu.yls.graduation.dal.impl.AdminDAOImpl;
import com.jsnu.yls.graduation.persistence.entities.Admin;
import com.jsnu.yls.graduation.plugin.utils.DataUtil;
import com.jsnu.yls.graduation.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Ser
 * Created by chenwei on 2015/9/17.
 */
@Service(value = "adminService")
public class AdminServiceImpl implements BaseService<Admin> {

    @Autowired
    private AdminDAOImpl adminDAO;

    /**
     * 根据ID取得用户对象
     *
     * @param id
     * @return
     */
    public Admin getAdminByID(Integer id) {
        return adminDAO.getEntity(id);
    }

    /**
     * 登录
     *
     * @param admin
     * @return
     */
    public Admin login(Admin admin) {
        return adminDAO.checkInfo(admin);
    }

    /**
     * 更新账户信息，若无此用户则新建
     *
     * @param admin
     */
    public void saveOrUpdateAccount(Admin admin) {
        admin.setPassword(DataUtil.md5(admin.getPassword()));
        adminDAO.saveOrUpdateEntity(admin);
    }

    /**
     * 取得所有用户对象
     *
     * @return
     */
    public List<Admin> getAdmins() {
        return adminDAO.getAllAdmins();
    }


    /**
     * 删除账户
     *
     * @param admin
     */
    public void removeAccount(Admin admin){
        adminDAO.deleteEntity(admin);
    }


}
