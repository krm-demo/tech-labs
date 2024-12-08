package org.krmdemo.techlabs.leet_code_2000_3000;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h3><a href="https://leetcode.com/problems/shortest-cycle-in-a-graph/description/">
 *     2608. Shortest Cycle in a Graph
 * </a></h3>
 *
 * There is a <b>bidirectional</b> (un-directed) graph with <b><code>N</code></b> vertices,
 * where each vertex is labeled from <code>0</code> to <code>N - 1</code>.
 * The edges in the graph are represented by a given 2D integer array <b><code>edges</code></b>,
 * where <code>edges[<b>i</b>] = [u<b>i</b>, v<b>i</b>]</code> denotes
 * an edge between vertex <code>u<b>i</b></code> and vertex <code>v<b>i</b></code>.
 * Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * <hr/>
 * Return the length of the <b>shortest</b> cycle in the graph. If no cycle exists, return <code>-1</code>.
 * <hr/>
 * A cycle is a path that starts and ends at the same node, and each edge in the path is used only once.
 * <h5>Constraints:</h5><pre>
 *      2 <= n <= 1000
 * 1 <= edges.length <= 1000
 *   edges[i].length == 2
 *     0 <= ui, vi < n
 *        ui != vi
 * There are no repeated edges.
 * </pre>
 *
 * @see <a href="https://www.geeksforgeeks.org/shortest-cycle-in-an-undirected-unweighted-graph/">
 *     Shortest cycle in an undirected unweighted graph
 * </a>
 * @see <a href="https://www.geeksforgeeks.org/eulerian-path-and-circuit/">
 *     Eulerian path and circuit for undirected graph
 * </a>
 */
public interface Problem_2608__Shortest_Cycle_in_a_Graph {

    /**
     * Solution entry-point
     *
     * @param N the number of nodes
     * @param edges list of unique bi-directional edges
     * @return the length of the shortest cycle
     */
    int findShortestCycle(int N, int[][] edges);

    enum Solution implements Problem_2608__Shortest_Cycle_in_a_Graph {
        DEFAULT;

        @Override
        public int findShortestCycle(int N, int[][] edgesArr) {
            Graph graph = new Graph(N, edgesArr);
            System.out.println(graph);
            List<Graph.SubGraph> subGraphList = graph.subGraphList();
            System.out.printf("%d DFS-Spanning Trees were found:%n", subGraphList.size());
            subGraphList.forEach(System.out::println);
            return 0; // TODO: use Tarjan or Kasaraju algorithm
        }

        private static class Graph {
            private static class Node {
                final int id;
                final BitSet neighbours = new BitSet();
                Node(int id) { this.id = id; }
                void addNeighbour(int neighbourId) {
                    neighbours.set(neighbourId);
                }
                @Override
                public String toString() {
                    return String.format("( %d :: %s )", id, neighbours);
                }
                public String dump() {
                    return String.format("(%d)", id);
                }
            }
            private final int N;
            private final Map<Integer, Node> nodesMap = new HashMap<>();
            private final BitSet visited = new BitSet();
            Graph(int N) {
                this.N = N;
            }
            Graph(int N, int[][] edges) {
                this(N);
                Arrays.stream(edges).forEach(this::addEdge);
            }
            void addEdge(int[] edge) {
                node(edge[0]).addNeighbour(edge[1]);
                node(edge[1]).addNeighbour(edge[0]);
            }
            Node node(int id) {
                return nodesMap.computeIfAbsent(id, Node::new);
            }
            @Override
            public String toString() {
                return IntStream.range(0, this.N)
                    .mapToObj(this::node)
                    .map(Node::toString)
                    .collect(Collectors.joining(
                        System.lineSeparator(),
                        String.format("---- the graph of %d nodes: ----%n", this.N),
                        String.format("%n-------------------------------")
                    ));
            }
            private class SubNode {
                final Node node;
                final int order;
                List<SubNode> backRefList = new ArrayList<>();
                public SubNode(int id, int order) {
                    this.node = node(id);
                    this.order = order;
                }
                int order() {
                    return this.order;
                }
                void addBackRef(SubNode subNode) {
                    if (subNode.order > this.order + 1) {
                        backRefList.add(subNode);
                    }
                }
                @Override
                public String toString() {
                    return this.dump() + (backRefList.isEmpty() ? "" : this.dumpBackRefs());
                }
                public String dump() {
                    return String.format("#%d%s", order, node.dump());
                }
                public String dumpBackRefs() {
                    return backRefList.stream()
                        .map(SubNode::dump)
                        .collect(Collectors.joining(",","{","}"));
                }
             }
            private class SubGraph {
                Map<Integer, SubNode> subNodesMap = new HashMap<>();
                Deque<SubNode> eulerialPath = new ArrayDeque<>();
                SubGraph(int rootId) {
                    newSubNode(rootId);
                }
                void newSubNode(int id) {
                    SubNode subNode = new SubNode(id, subNodesMap.size());
                    subNodesMap.put(id, subNode);
                    int[] neighbourIDs = subNode.node.neighbours.stream()
                        .filter(neighbourId -> !visited.get(neighbourId))
                        .toArray();
                    for (int neighbourId : neighbourIDs) {
                        SubNode backNode = subNodesMap.get(neighbourId);
                        if (backNode != null) {
                            subNode.addBackRef(backNode);
                        } else {
                            eulerialPath.addLast(subNode);
                            newSubNode(neighbourId);
                        }
                    }
                    eulerialPath.addLast(subNode);
                }
                @Override
                public String toString() {
                    return subNodesMap.values().stream()
                        .sorted(Comparator.comparingInt(SubNode::order))
                        .map(SubNode::toString)
                        .collect(Collectors.joining(
                            System.lineSeparator(),
                            String.format("~~~ sub-graph of %d nodes: ~~~%n", subNodesMap.size()),
                            String.format("%n:: %s ::", dumpEulerialPath())
                        ));
                }
                public String dumpEulerialPath() {
                    return eulerialPath.stream()
                        .map(SubNode::dump)
                        .collect(Collectors.joining(";"));
                }
            }
            List<SubGraph> subGraphList() {
                visited.clear();
                List<SubGraph> resultList = new ArrayList<>();
                int rootId = visited.nextClearBit(0);
                while (rootId < this.N) {
                    SubGraph subGraph = new SubGraph(rootId);
                    subGraph.subNodesMap.keySet().forEach(visited::set);
                    resultList.add(subGraph);
                    rootId = visited.nextClearBit(rootId);
                }
                return resultList;
            }
        }
    }
}
