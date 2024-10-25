package com.example.ruleengine.controller;

import com.example.ruleengine.dto.EvaluateRuleRequest;
import com.example.ruleengine.model.ASTNode;
import com.example.ruleengine.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/create_rule")
    public ASTNode createRule(@RequestBody String ruleString) {
        return ruleService.createRule(ruleString);
    }


    /*@PostMapping("/combine_rules")
    public ASTNode combineRules(List<ASTNode> rules) {
        ASTNode combinedRule = rules.get(0);
        for (int i = 1; i < rules.size(); i++) {
            combinedRule = new ASTNode("operator", "AND", combinedRule, rules.get(i));
        }
        return combinedRule;
    }*/
    @PostMapping("/combine_rules")
    public ASTNode combineRules(@RequestBody ArrayList<ASTNode> rules) {
        ASTNode combinedRule = ruleService.combineRules(rules);
        return combinedRule;
    }

    @PostMapping("/evaluate_rule")
    public boolean evaluateRule(@RequestBody EvaluateRuleRequest payload) {
        ASTNode rule = payload.getRuleAST();
        Map<String, Object> userData = payload.getUserData();

        return ruleService.evaluateRule(rule, userData);
    }


}
