package com.example.ruleengine;

import com.example.ruleengine.model.ASTNode;
import com.example.ruleengine.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RuleServiceTest {

    @Autowired
    private RuleService ruleService;

    @Test
    public void testCreateRule1() {
        String rule1 = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)";
        ASTNode astRule1 = ruleService.createRule(rule1);

        // Verify structure - example of verifying the main operator and first level nodes
        assertNotNull(astRule1);
        assertEquals("AND", astRule1.getValue());
        assertEquals("OR", astRule1.getLeft().getValue());
        assertEquals("OR", astRule1.getRight().getValue());
    }

    @Test
    public void testCreateRule2() {
        String rule2 = "((age > 30 AND department = 'Marketing')) AND (salary > 20000 OR experience > 5)";
        ASTNode astRule2 = ruleService.createRule(rule2);

        // Verify structure
        assertNotNull(astRule2);
        assertEquals("AND", astRule2.getValue());
        assertEquals("AND", astRule2.getLeft().getValue());
        assertEquals("OR", astRule2.getRight().getValue());
    }

    @Test
    public void testCombineRules() {
        // Define example rules as strings
        String rule1 = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)";
        String rule2 = "((age > 30 AND department = 'Marketing')) AND (salary > 20000 OR experience > 5)";

        // Create individual ASTs
        ASTNode astRule1 = ruleService.createRule(rule1);
        ASTNode astRule2 = ruleService.createRule(rule2);

        // Combine ASTs
        List<ASTNode> rules = Arrays.asList(astRule1, astRule2);
        ASTNode combinedAST = ruleService.combineRules(rules);



        // Verify that the combined AST has an AND root node with rule1 and rule2 as children
        assertNotNull(combinedAST);
        assertEquals("AND", combinedAST.getValue());
        assertEquals(astRule1, combinedAST.getLeft());
        assertEquals(astRule2, combinedAST.getRight());
    }
    @Test
    public void testEvaluateRuleScenario1() {
        String rule1 = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)";
        ASTNode astRule1 = ruleService.createRule(rule1);

        Map<String, Object> userData1 = new HashMap<>();
        userData1.put("age", 35);
        userData1.put("department", "Sales");
        userData1.put("salary", 60000);
        userData1.put("experience", 3);

        assertTrue(ruleService.evaluateRule(astRule1, userData1));
    }

    @Test
    public void testEvaluateRuleScenario2() {
        String rule2 = "((age > 30 AND department = 'Marketing')) AND (salary > 20000 OR experience > 5)";
        ASTNode astRule2 = ruleService.createRule(rule2);

        // Sample user data that should match rule2
        Map<String, Object> userData1 = new HashMap<>();
        userData1.put("age", 32);
        userData1.put("department", "Marketing");
        userData1.put("salary", 25000);
        userData1.put("experience", 4);
        assertTrue(ruleService.evaluateRule(astRule2, userData1));

        // Sample user data that should not match rule2
        Map<String, Object> userData2 = new HashMap<>();
        userData2.put("age", 29);
        userData2.put("department", "Marketing");
        userData2.put("salary", 15000);
        userData2.put("experience", 3);
        assertFalse(ruleService.evaluateRule(astRule2, userData2));
    }

    @Test
    public void testEvaluateCombinedRules() {
        String rule1 = "((age > 30 AND department = 'Sales') OR (age < 25 AND department = 'Marketing')) AND (salary > 50000 OR experience > 5)";
        String rule2 = "((age > 30 AND department = 'Marketing')) AND (salary > 20000 OR experience > 5)";
        ASTNode astRule1 = ruleService.createRule(rule1);
        ASTNode astRule2 = ruleService.createRule(rule2);

        // Combine rule1 and rule2
        ASTNode combinedRule = ruleService.combineRules(Arrays.asList(astRule1, astRule2));

        // Sample user data that should match the combined rule
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 35);
        userData.put("department", "Marketing");
        userData.put("salary", 30000);
        userData.put("experience", 6);
        assertTrue(ruleService.evaluateRule(combinedRule, userData));

        // Sample user data that should not match the combined rule
        Map<String, Object> nonMatchingData = new HashMap<>();
        nonMatchingData.put("age", 22);
        nonMatchingData.put("department", "HR");
        nonMatchingData.put("salary", 15000);
        nonMatchingData.put("experience", 1);
        assertFalse(ruleService.evaluateRule(combinedRule, nonMatchingData));
    }


}
