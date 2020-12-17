package model.vo;

public class Role_Resource {
    private int id;
    private int resourceId;
    private int roleId;

    @Override
    public String toString() {
        return "Role_Resource{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", roleId=" + roleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Role_Resource() {
    }
}
