import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DongNuoc {

    static class State {
        int a, b;

        public State(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return a * 31 + b;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (!(obj instanceof State))
                return false;
            State other = (State) obj;
            return a == other.a && b == other.b;
        }
    }

    static State actionWater(int maxA, int maxB, int target){
        Set<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        Queue<State> closed = new LinkedList<>();

        Queue<State> opend = new LinkedList<>();

        queue.offer(new State(0,0));

        while (!queue.isEmpty()){
            State current = queue.poll();
            int a = current.a;
            int b = current.b;
            closed.offer(current);
            System.out.print(current.a + "," + current.b + "          ");

            if((a == target && b == 0) || (b == target && a == 0))
                return current;

//            FILL BINH 1
            if (!visited.contains(new State(maxA,b))){
                visited.add(new State(maxA, b));
                if (!closed.contains(new State(maxA, b))){
                    queue.offer(new State(maxA, b));
                    opend.add(new State(maxA, b));
                }
            }

//            FILL BINH 2
            if (!visited.contains(new State(a,maxB))){
                visited.add(new State(a, maxB));
                if (!closed.contains(new State(a, maxB))){
                    queue.offer(new State(a, maxB));
                    opend.add(new State(a, maxB));
                }
            }

//            Empty binh 1
            if (!visited.contains(new State(0,b))){
                visited.add(new State(0, b));
                if (!closed.contains(new State(0, b))){
                    queue.offer(new State(0, b));
                    opend.add(new State(0, b));
                }
            }

//            Empty binh 2
            if (!visited.contains(new State(a, 0))){
                visited.add(new State(a, 0));
                if (!closed.contains(new State(a, 0))){
                    queue.offer(new State(a, 0));
                    opend.add(new State(a, 0));
                }
            }

//            Pour 1 sang 2
            int pourAmount1to2 = Math.min(a, maxB-b);
            if (!visited.contains(new State(a-pourAmount1to2, b +pourAmount1to2))){
                visited.add(new State(a - pourAmount1to2, b + pourAmount1to2));
                if (!closed.contains(new State(a - pourAmount1to2, b + pourAmount1to2))){
                    queue.offer(new State(a - pourAmount1to2, b + pourAmount1to2));
                    opend.add(new State(a - pourAmount1to2, b + pourAmount1to2));
                }
            }

//            Pour 2 sang 1
            int pourAmount2to1 = Math.min(maxA - a, b);
            if (!visited.contains(new State(a + pourAmount2to1, b - pourAmount2to1))){
                visited.add(new State(a + pourAmount2to1, b - pourAmount2to1));
                if (!closed.contains(new State(a + pourAmount2to1, b - pourAmount2to1))){
                    queue.offer(new State(a + pourAmount2to1, b - pourAmount2to1));
                    opend.add(new State(a + pourAmount2to1, b - pourAmount2to1));
                }
            }

            for (State ans : opend){
                System.out.print(ans.a + "," + ans.b + " ");
            }

            opend.clear();

            System.out.print("          ");

            for (State ans : closed)
                System.out.print(ans.a + "," + ans.b + " ");

            System.out.println(" ");
        }
        return null;
    }

    public static void main(String args[]) {
        int maxA = 4;
        int maxB = 3;
        int target = 2;

        System.out.println("VISITED" + "        " + "OPENED" + "          " + "CLOSED");

        State result = actionWater(maxA, maxB, target);

    }
}
