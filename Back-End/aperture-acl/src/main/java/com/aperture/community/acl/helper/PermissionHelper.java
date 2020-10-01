package com.aperture.community.acl.helper;

import com.aperture.community.acl.entity.ScsPermission;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-9-26 19:11
 * @Description:
 */
public class PermissionHelper {

    /**
     * 通过递归将权限集合构建为树形结构的菜单
     * @param treeNodes
     * @return
     */
    public static List<ScsPermission> build(List<ScsPermission> treeNodes) {
        // 存放树形结构的权限菜单结果集
        List<ScsPermission> trees = new ArrayList<>();
        for (ScsPermission treeNode : treeNodes) {
            if ("9".equals(treeNode.getPid())) {
                // 最高级菜单level设置为1
                treeNode.setLevel(1);
                // 递归获取并设置当前节点treeNode下的所有子菜单
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 查询并获取所有子菜单
     * @param treeNode
     * @param treeNodes
     * @return
     */
    private static ScsPermission findChildren(ScsPermission treeNode, List<ScsPermission> treeNodes) {
        treeNode.setChildren(new ArrayList<ScsPermission>());
        for (ScsPermission it : treeNodes) {
            if (treeNode.getId().equals(it.getPid())) {
                // 子菜单的level层级
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        // 每次递归都返回菜单
        return treeNode;
    }

}