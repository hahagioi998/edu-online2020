package com.qiyu.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiyu.aclservice.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 获取全部菜单
     * @return
     */
    List<Permission> queryAllMenu();
    //递归删除菜单
    void removeChildById(String id);

    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
    //根据角色获取菜单
    List<Permission> selectAllMenu(String roleId);

    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);
}
