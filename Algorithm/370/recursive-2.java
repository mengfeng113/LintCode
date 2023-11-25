public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public List<String> convertToRPN(String[] expression) {
        this.expression = new String[expression.length + 2];
        this.expression[0] = "(";
        for (int i = 0; i < expression.length; i++) 
            this.expression[i + 1] = expression[i]; 
        this.expression[this.expression.length - 1] = ")";
        index = 0;
        helper();
        return res;
    }

    int index;
    String[] expression;
    List<String> res = new LinkedList<>();
    Stack<String> st = new Stack<>();

    private void helper() {
        // evalue current level from index s in expression
        // expression[s] must be a "("
        // the current level ends on a ")"
        // after this helper is done, index should be 
        // pointing to the next token after the ")" 
        // which ends current level
        
        index++;
        st.push("("); // marked the beginning of current level
        while (!")".equals(expression[index])) {
            String token = expression[index];
            if (isNumber(token)) {
                res.add(token);
                index++;
            } else if ("(".equals(token)) {
                helper();
            } else { // an operator
                while (!st.peek().equals("(") && prio(st.peek()) >= prio(token)) {
                    res.add(st.pop());
                }
                st.push(token);
                index++;
            }
        }

        while (!st.peek().equals("(")) {
            res.add(st.pop());
        }
        st.pop(); // pop the "(" which marked the beginning of current level
        index++;
    }

    private boolean isNumber(String token) {
        // expr match
        return token.matches("[+-]*[0-9]+");
    }

    private int prio(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default: // should not be here
                return 0;
        }
    }
}