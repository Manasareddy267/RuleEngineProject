package com.example.ruleengine.model;
public class ASTNode {
    private String type;
    private String value;
    private ASTNode left;
    private ASTNode right;

    // Default constructor
    public ASTNode() {
    }

    public ASTNode(String type, String value, ASTNode left, ASTNode right) {
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }
}
