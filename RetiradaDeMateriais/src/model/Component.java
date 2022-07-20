package model;

public class Component {
    private int id;
    private String component;
    private int qtdAvailable;
    private int qtdUnavailable;

    public Component(int id, String component, int qtdAvailable, int qtdUnavailable) {
        this.id = id;
        this.component = component;
        this.qtdAvailable = qtdAvailable;
        this.qtdUnavailable = qtdUnavailable;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getComponent() {
        return component;
    }
    public void setComponent(String component) {
        this.component = component;
    }
    public int getQtdAvailable() {
        return qtdAvailable;
    }
    public void setQtdAvailable(int qtdAvailable) {
        this.qtdAvailable = qtdAvailable;
    }
    public int getQtdUnavailable() {
        return qtdUnavailable;
    }
    public void setQtdUnavailable(int qtdUnavailable) {
        this.qtdUnavailable = qtdUnavailable;
    }
}
