package com.example.ruleengine.model;

import javax.persistence.*;

@Entity
public class Rule {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleAST() {
        return ruleAST;
    }

    public void setRuleAST(String ruleAST) {
        this.ruleAST = ruleAST;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    @Lob
    private String ruleAST; // Serialized AST (e.g., JSON)

    // Getters and Setters
}
