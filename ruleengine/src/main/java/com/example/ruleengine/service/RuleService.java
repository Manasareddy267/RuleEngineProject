package com.example.ruleengine.service;
import com.example.ruleengine.model.ASTNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleService {

    // Method to create a rule from a string
    public ASTNode createRule(String ruleString) {
        // Parse the rule string and construct AST
        // You can use a parser (e.g., using regex or an actual AST parser library)
        // For example, parsing "age > 30 AND department = 'Sales'"
        return parseRule(ruleString);
    }

    private ASTNode parseRule(String ruleString) {
        // Convert ruleString to ASTNode (example logic; needs to be expanded)
        // This is a simplified placeholder
        return new ASTNode("operator", "AND",
                new ASTNode("operand", "age > 30", null, null),
                new ASTNode("operand", "department = 'Sales'", null, null));
    }

    // Method to combine multiple AST rules
    public ASTNode combineRules(List<ASTNode> rules) {
        // Base case: if there’s only one rule, return it directly
        if (rules.size() == 1) {
            return rules.get(0);
        }
        // Recursive combination of rules with AND operator
        ASTNode combined = rules.get(0);
        for (int i = 1; i < rules.size(); i++) {
            combined = new ASTNode("operator", "AND", combined, rules.get(i));
        }
        return combined;
    }


    // Method to evaluate the rule against user data
    public boolean evaluateRule(ASTNode node, Map<String, Object> userData) {
        // Recursively evaluate the AST based on the node type and user data
        if (node.getType().equals("operator")) {
            if (node.getValue().equals("AND")) {
                return evaluateRule(node.getLeft(), userData) && evaluateRule(node.getRight(), userData);
            } else if (node.getValue().equals("OR")) {
                return evaluateRule(node.getLeft(), userData) || evaluateRule(node.getRight(), userData);
            }
        } else if (node.getType().equals("operand")) {
            // Evaluate the operand, e.g., "age > 30"
            return evaluateOperand(node.getValue(), userData);
        }
        return false;
    }

    private boolean evaluateOperand(String operand, Map<String, Object> userData) {
        // Implement the logic for evaluating operands, e.g., "age > 30"
        return true; // Simplified placeholder
    }
}
