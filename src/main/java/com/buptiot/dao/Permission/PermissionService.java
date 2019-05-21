package com.buptiot.dao.Permission;

import com.buptiot.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created by zyf on 2019/5/21.
 */
public interface PermissionService {

    List<Permission> findAllPermissions();

    List<Permission> findAllPermissionsByRoleId(int role_id);

    List<Permission> findAllNotOwnedPermissionsByRoleId(int role_id);

    List<Integer> findPermissionIdsByRoleId(int role_id);

    Set<String> findPermissionNamesByRoleId(int role_id);

    void saveRolePermissionRelation(int role_id,int permission_id);

    void deleteARelation(int role_id,int permission_id);

    Set<Permission> findAllByUserId(int user_id);

    Set<Permission> findBaseByAuthority(String authority);
}
