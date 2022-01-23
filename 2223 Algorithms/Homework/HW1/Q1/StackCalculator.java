public class StackCalculator {


    public static void evaluate(String[] expression) {
        {
            Stack<String> ops = new Stack<String>();
            Stack<Double> vals = new Stack<Double>();

            //String[] expression = new String[]{"(", "2", "*", "54", ")"};

            for (String token : expression) { // Read token, push if operator.
                System.out.println(token);
                String s = token;

                if (s.equals("(")) ;
                else if (s.equals("+")) ops.push(s);
                else if (s.equals("-")) ops.push(s);
                else if (s.equals("*")) ops.push(s);
                else if (s.equals("/")) ops.push(s);
                else if (s.equals("sqrt")) ops.push(s);
                else if (s.equals("^")) ops.push(s); // Student added operator*******
                else if (s.equals("%")) ops.push(s); // Student added operator*******
                else if (s.equals(")")) { // Pop, evaluate, and push result if token is ")".
                    String op = ops.pop();
                    double v = vals.pop();

                    if (op.equals("+")) v = vals.pop() + v;
                    else if (op.equals("-")) v = vals.pop() - v;
                    else if (op.equals("*")) v = vals.pop() * v;
                    else if (op.equals("/")) v = vals.pop() / v;
                    else if (op.equals("sqrt")) v = Math.sqrt(v);
                    else if (op.equals("^"))
                    {v = Math.pow(vals.pop(), v);}
                    else if (op.equals("%")) v = vals.pop() % v;
                    vals.push(v);

                } // Token not operator or paren: push double value.
                else vals.push(Double.parseDouble(s));
            }

            System.out.println(vals.pop());
        }
    }
}
