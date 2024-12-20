package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h3><a href="https://leetcode.com/problems/course-schedule/description/?envType=problem-list-v2&envId=topological-sort">
 *     207. Course Schedule
 * </a></h3>
 * There are a total of <b><code>numCourses</code></b> courses you have to take,
 * labeled from <code>0</code> to <code>numCourses - 1</code>.
 * You are given an array <b><code>prerequisites</code></b>
 * where <code>prerequisites[<b>i</b>] = [a<b>i</b>, b<b>i</b>]</code>
 * indicates that you must take course <code>b<b>i</b></code> first if you want to take course <code>a<b>i</b></code>.
 * <hr/>
 * For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code>
 * you have to first take course <code>1</code>.
 * <hr/>
 * Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.
 * <h5>Constraints:</h5><pre>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses</pre>
 * All the pairs <code>prerequisites[i]</code> are unique.
 *
 * @see <a href="https://www.geeksforgeeks.org/topological-sorting/">
 *    (GFG) Topological Sorting
 * </a>
 */
public interface Problem_207__Course_Schedule {

    /**
     * Solution entry-point.
     *
     * @param numCourses total number of courses you have to take
     * @param prerequisites pair of pre-requisite conditions (the first must precede the second)
     * @return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>
     */
    boolean canFinish(int numCourses, int[][] prerequisites);

    interface CoursesGraph {
        interface Factory {
            CoursesGraph create(int numCourses, int[][] prerequisites);
        }
        boolean isEmpty();
        boolean hasFirstToRemove();
        void removeFirst();
    }

    enum Solution implements Problem_207__Course_Schedule, CoursesGraph.Factory {
        /**
         * This version of topology-sort uses {@link TreeSet}
         * <hr>
         * one-by-one topological deletion is not efficient (Leet-Code results in 168ms and 5.11% beats)
         */
        TOPOLOGY_SORT_TREE_SET {
            @Override
            public CoursesGraph create(int numCourses, int[][] prerequisites) {
                CoursesGraphTreeSet coursesGraph = new CoursesGraphTreeSet(numCourses);
                if (prerequisites != null && prerequisites.length > 0) {
                    Arrays.stream(prerequisites).forEach(coursesGraph::addEdge);
                }
                return coursesGraph;
            }

            private static class CoursesGraphTreeSet extends CoursesGraphBase {
                private static final Comparator<Node> topologicalBefore =
                    Comparator.comparingInt(Node::countBefore)
                        .thenComparingInt(Node::id);
                private final NavigableSet<Node> topologicalSet =
                    new TreeSet<>(topologicalBefore);

                CoursesGraphTreeSet(int numCourses) {
                    super(numCourses);
                }

                public void addEdge(int[] pair) {
                    Node nodeBefore = node(pair[0]);
                    Node nodeAfter = node(pair[1]);
                    topologicalSet.remove(nodeBefore);
                    topologicalSet.remove(nodeAfter);
                    nodeBefore.after.set(nodeAfter.id());
                    nodeAfter.before.set(nodeBefore.id());
                    topologicalSet.add(nodeBefore);
                    topologicalSet.add(nodeAfter);
                }
                private Optional<Node> topologicalFirst() {
                    return topologicalSet.isEmpty() ?
                        Optional.empty() :
                        Optional.of(topologicalSet.first());
                }
                @Override
                public boolean hasFirstToRemove() {
                    return topologicalFirst().map(Node::hasBefore).orElse(false);
                }
                @Override
                public void removeFirst() {
                    topologicalFirst().ifPresent(this::remove);
                }

                private void remove(Node nodeToRemove) {
                    final int idToRemove = nodeToRemove.id;
                    BitSet nodesToUpdate = new BitSet();
                    nodeToRemove.before.stream()
                        .filter(id -> id != idToRemove)
                        .mapToObj(this::node)
                        .forEach(nodeBefore -> {
                            topologicalSet.remove(nodeBefore);
                            nodeBefore.after.clear(idToRemove);
                            nodesToUpdate.set(nodeBefore.id);
                        });
                    nodeToRemove.after.stream()
                        .filter(id -> id != idToRemove)
                        .mapToObj(this::node)
                        .forEach(nodeAfter -> {
                            topologicalSet.remove(nodeAfter);
                            nodeAfter.before.clear(idToRemove);
                            nodesToUpdate.set(nodeAfter.id);
                        });
                    nodesToUpdate.stream()
                        .mapToObj(this::node)
                        .forEach(topologicalSet::add);
                    topologicalSet.remove(nodeToRemove);
                    nodes[idToRemove] = null;
                    size--;
                }
                @Override
                protected String dumpTopology() {
                    return topologicalSet.stream()
                        .map(Node::id)
                        .toList().toString();
                }
            }
        },
        /**
         * This version of topology-sort uses counting-map
         * @see org.krmdemo.techlabs.utils.CountingUtils
         */
        TOPOLOGY_SORT_COUNTING_MAP {
            @Override
            public CoursesGraph create(int numCourses, int[][] prerequisites) {
                CoursesGraphCountingMap coursesGraph = new CoursesGraphCountingMap(numCourses);
                if (prerequisites != null && prerequisites.length > 0) {
                    Arrays.stream(prerequisites).forEach(coursesGraph::addEdge);
                }
                return coursesGraph;
            }

            private static class CoursesGraphCountingMap extends CoursesGraphBase {
                final Map<Integer, Set<Integer>> topoCntMap = new HashMap<>();

                public CoursesGraphCountingMap(int numCourses) {
                    super(numCourses);
                }

                Set<Integer> topoLevel(int beforeCount) {
                    return topoCntMap.computeIfAbsent(beforeCount, _cnt -> new HashSet<>());
                }
                void topoLevelAdd(int id) {
                    topoLevel(node(id).countBefore()).add(id);
                }
                void topoLevelDel(int id) {
                    topoLevel(node(id).countBefore()).remove(id);
                }

                public void addEdge(int[] pair) {
                    addEdge(pair[0], pair[1]);
                }
                public void addEdge(int idNodeFrom, int idNodeTo) {
                    Node nodeFrom = node(idNodeFrom);
                    Node nodeTo = node(idNodeTo);
                    topoLevelDel(idNodeTo);
                    nodeFrom.after.set(idNodeTo);
                    nodeTo.before.set(idNodeFrom);
                    topoLevelAdd(idNodeTo);
                    topoLevelAdd(idNodeFrom);
                }
                public void removeEdge(int idNodeFrom, int idNodeTo) {
                    Node nodeFrom = node(idNodeFrom);
                    Node nodeTo = node(idNodeTo);
                    topoLevelDel(idNodeTo);
                    nodeFrom.after.clear(idNodeTo);
                    nodeTo.before.clear(idNodeFrom);
                    topoLevelAdd(idNodeTo);
                }

                @Override
                public boolean hasFirstToRemove() {
                    return !topoLevel(0).isEmpty();
                }

                @Override
                public void removeFirst() {
                    List<Integer> firstLevelList = topoLevel(0).stream().toList();
                    firstLevelList.forEach(this::removeNode);
                }

                private void removeNode(int idToRemove) {
                    topoLevelDel(idToRemove);
                    Node nodeToRemove = node(idToRemove);
                    nodeToRemove.before.stream().forEach(
                        idNodeFrom -> this.removeEdge(idNodeFrom, idToRemove)
                    );
                    nodeToRemove.after.stream().forEach(
                        idNodeTo-> this.removeEdge(idToRemove, idNodeTo)
                    );
                    nodes[idToRemove] = null;
                    size--;
                }
                @Override
                protected String dumpTopology() {

                    return topoCntMap.toString();
                }
            }
        };

        /**
         * Implementation of {@link Solution}'s entry-point,
         * where the details are encapsulated in instance of {@link CoursesGraph}
         *
         * @param numCourses total number of courses you have to take
         * @param prerequisites pair of pre-requisite conditions (the first must precede the second)
         * @return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>
         */
        @Override
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (numCourses <= 0) {
                throw new IllegalArgumentException(
                    "number of courses must be positive, but it equals to " + numCourses);
            }
            CoursesGraph coursesGraph = create(numCourses, prerequisites);
            System.out.println("before topological sort:");
            System.out.println(coursesGraph);
            while (coursesGraph.hasFirstToRemove()) {
                coursesGraph.removeFirst();
            }
            System.out.println("after topological sort:");
            System.out.println(coursesGraph);
            System.out.println("returning " + coursesGraph.isEmpty());
            System.out.println("===================================");
            return coursesGraph.isEmpty();
        }

        static abstract class CoursesGraphBase implements CoursesGraph {
            record Node (int id, BitSet before, BitSet after) {
                Node(int id) {
                    this(id, new BitSet(), new BitSet());
                }
                int countBefore() {
                    return before.cardinality();
                }
                boolean hasBefore() {
                    return countBefore() == 0;
                }
                @Override
                public String toString() {
                    return String.format("#%d --> before: %s, after: %s;",
                        this.id(), this.before(), this.after());
                }
            }
            final Node[] nodes;
            int size = 0;
            CoursesGraphBase(int numCourses) {
                this.nodes = new Node[numCourses];
            }
            public Node node(int id) {
                if (nodes[id] == null) {
                    nodes[id] = new Node(id);
                    size++;
                }
                return nodes[id];
            }
            @Override
            public boolean isEmpty() {
                return size == 0;
            }
            @Override
            public String toString() {
                return Arrays.stream(nodes)
                    .filter(Objects::nonNull)
                    .map(Node::toString)
                    .collect(Collectors.joining(
                        System.lineSeparator(),
                        String.format("---- %d courses: ----%n", size),
                        String.format("%n.....................%n")
                            + dumpTopology() +
                            String.format("%n---------------------%n")
                    ));
            }
            protected abstract String dumpTopology();
        }

    }
}
