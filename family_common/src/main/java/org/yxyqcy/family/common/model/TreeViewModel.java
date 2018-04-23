package org.yxyqcy.family.common.model;

import java.util.List;

/**
 * Created by lcy on 16/7/30.
 *
 * https://www.npmjs.com/package/bootstrap-treeview
 *
 * bootstrap treeview
 */
public class TreeViewModel {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String text;

    private String href;

    private String icon;

    private String selectedIcon;

    private String color;

    private String backColor;

    private String pid;

    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public boolean isShowTags() {
        return showTags;
    }

    public void setShowTags(boolean showTags) {
        this.showTags = showTags;
    }

    private boolean showTags = false;

    private TreeViewStateModel state;

    public TreeViewStateModel getState() {
        return state;
    }

    public void setState(TreeViewStateModel state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    private String[] tags = new String[]{};

    private boolean selectable = false;  //是否选中

    public List<TreeViewModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeViewModel> node) {
        this.nodes = node;
    }

    private List<TreeViewModel> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }



    public TreeViewModel() {
    }

    //state
    public class  TreeViewStateModel {

        public TreeViewStateModel() {

        }

        boolean checked = false;
        boolean disabled = false;
        boolean expanded = false;
        boolean selected = false;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public void setDisabled(boolean disabled) {
            this.disabled = disabled;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}
