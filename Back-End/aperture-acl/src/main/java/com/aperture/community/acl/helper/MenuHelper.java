package com.aperture.community.acl.helper;

import com.alibaba.fastjson.JSONObject;
import com.aperture.community.acl.entity.ScsPermission;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: JayV
 * @Date: 2020-9-26 19:11
 * @Description:
 */
public class MenuHelper {

    /**
     * 构建菜单
     * @param treeNodes
     * @return
     */
    public static List<JSONObject> bulid(List<ScsPermission> treeNodes) {
        List<JSONObject> menus = new ArrayList<>();
        if(treeNodes.size() == 1) {
            ScsPermission topNode = treeNodes.get(0);
            //左侧一级菜单
            List<ScsPermission> oneMenuList = topNode.getChildren();
            for(ScsPermission one : oneMenuList) {
                JSONObject oneMenu = new JSONObject();
                oneMenu.put("path", one.getPath());
                oneMenu.put("component", one.getComponent());
                oneMenu.put("redirect", "noredirect");
                oneMenu.put("name", "name_"+one.getId());
                oneMenu.put("hidden", false);

                JSONObject oneMeta = new JSONObject();
                oneMeta.put("title", one.getName());
                oneMeta.put("icon", one.getIcon());
                oneMenu.put("meta", oneMeta);
                List<JSONObject> children = new ArrayList<>();

                // 第二级节点
                List<ScsPermission> twoMenuList = one.getChildren();
                for(ScsPermission two : twoMenuList) {
                    JSONObject twoMenu = new JSONObject();
                    twoMenu.put("path", two.getPath());
                    twoMenu.put("component", two.getComponent());
                    twoMenu.put("name", "name_"+two.getId());
                    twoMenu.put("hidden", false);

                    JSONObject twoMeta = new JSONObject();
                    twoMeta.put("title", two.getName());
                    twoMenu.put("meta", twoMeta);
                    // 一级菜单添加二级菜单作为子菜单
                    children.add(twoMenu);

                    // 第三级节点
                    List<ScsPermission> threeMenuList = two.getChildren();
                    for(ScsPermission tree : threeMenuList) {
                        if(StringUtils.isEmpty(tree.getPath())) continue;

                        JSONObject threeMenu = new JSONObject();
                        threeMenu.put("path", tree.getPath());
                        threeMenu.put("component", tree.getComponent());
                        threeMenu.put("name", "name_"+tree.getId());
                        threeMenu.put("hidden", true);

                        JSONObject treeMeta = new JSONObject();
                        treeMeta.put("title", tree.getName());

                        threeMenu.put("meta", treeMeta);

                        // 一级菜单添加第三级菜单作为子菜单
                        children.add(threeMenu);
                    }
                }
                oneMenu.put("children", children);
                menus.add(oneMenu);
            }
        }
        return menus;
    }
}