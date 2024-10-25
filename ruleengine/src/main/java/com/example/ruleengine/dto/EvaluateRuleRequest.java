package com.example.ruleengine.dto;

import com.example.ruleengine.model.ASTNode;

import java.util.Map;

public class EvaluateRuleRequest {
    private ASTNode ruleAST;
    private Map<String, Object> userData;

    // Getters and setters
    public ASTNode getRuleAST() {
        return ruleAST;
    }

    public void setRuleAST(ASTNode ruleAST) {
        this.ruleAST = ruleAST;
    }

    public Map<String, Object> getUserData() {
        return userData;
    }

    public void setUserData(Map<String, Object> userData) {
        this.userData = userData;
    }
}
