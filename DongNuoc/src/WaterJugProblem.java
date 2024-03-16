import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WaterJugProblem {

    static class State {
        int jug4, jug3;
        State(int jug4, int jug3) {
            this.jug4 = jug4;
            this.jug3 = jug3;
        }
    }

    static void printState(State state) {
        System.out.println("Bình 4 lít: " + state.jug4 + " lít");
        System.out.println("Bình 3 lít: " + state.jug3 + " lít");
        System.out.println("--------------------");
    }

    static void waterJugBFS() {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new State(0, 0)); // Initial state

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.jug4 == 2 || curr.jug3 == 2) {
                printState(curr);
                break;
            }

            if (!visited.contains(curr.jug4 + "," + curr.jug3)) {
                visited.add(curr.jug4 + "," + curr.jug3);

                // Fill jug4
                queue.add(new State(4, curr.jug3));

                // Fill jug3
                queue.add(new State(curr.jug4, 3));

                // Empty jug4
                queue.add(new State(0, curr.jug3));

                // Empty jug3
                queue.add(new State(curr.jug4, 0));

                // Pour jug4 to jug3
                int pourAmount = Math.min(curr.jug4, 3 - curr.jug3);
                queue.add(new State(curr.jug4 - pourAmount, curr.jug3 + pourAmount));

                // Pour jug3 to jug4
                pourAmount = Math.min(curr.jug3, 4 - curr.jug4);
                queue.add(new State(curr.jug4 + pourAmount, curr.jug3 - pourAmount));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Các bước quá trình đổ nước:");
        waterJugBFS();
    }
}