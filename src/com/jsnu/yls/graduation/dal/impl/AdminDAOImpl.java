package com.jsnu.yls.graduation.dal.impl;

import com.jsnu.yls.graduation.dal.BaseDAO;
import com.jsnu.yls.graduation.persistence.entities.Admin;
import com.jsnu.yls.graduation.plugin.utils.DataUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * 账户模块持久层
 *
 * Created by WeiXY on 2015/9/17.
 */
@Repository
public class AdminDAOImpl extends BaseDAOImpl<Admin> implements BaseDAO<Admin> {

    /**
     * 验证信息，若信息正确，返回true，若错误，返回false
     * 尚可优化
     *
     * @param admin
     * @return
     */
    public Admin checkInfo(Admin admin) {
        String jpql = "SELECT a FROM Admin a WHERE a.userName=?1 AND a.password=?2";
        Query query = this.entityManager.createQuery(jpql).setParameter(1, admin.getUserName().trim())
                .setParameter(2, DataUtil.md5(admin.getPassword()));

        if (query.getResultList().size() == 0) {
            return null;
        } else {
            return (Admin) query.getResultList().get(0);
        }
    }

    /**
     * 对密码做md5码加密并注册
     *
     * @param admin
     */
    public void encryptionAndSaveAdmin(Admin admin) {
        admin.setPassword(DataUtil.md5(admin.getPassword()));
        this.entityManager.persist(admin);
    }

    /**
     * 取得所有用户账号
     *
     * @return
     */
    public List<Admin> getAllAdmins() {
        String jpql = "SELECT a FROM Admin a";
        return this.getEntitiesByJPQL(jpql);
    }

}
