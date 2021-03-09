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
        return helper();
    }

    int index;
    String[] expression;

    private List<String> helper() {
        // evalue current level from index s in expression
        // expression[s] must be a "("
        // the current level ends on a ")"
        // after this helper is done, index should be 
        // pointing to the next token after the ")" 
        // which ends current level
        List<String> res = new LinkedList<>();
        Stack<String> st = new Stack<>();
        index++;
        while (!expression[index].equals(")")) {
            String token = expression[index];
            if (isNumber(token)) {
                res.add(token);
                index++;
            } else if (token.equals("(")) {
                res.addAll(helper());
            } else { // an operator
                while (!st.isEmpty() && prio(st.peek()) >= prio(token)) {
                    res.add(st.pop());
                }
                st.push(token);
                index++;
            }
        }

        while (!st.isEmpty()) {
            res.add(st.pop());
        }
        index++;
        return res;
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
