package uk.ac.cam.jp775.Algorithms.tick3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import uk.ac.cam.rkh23.Algorithms.Tick3.GraphBase;
import uk.ac.cam.rkh23.Algorithms.Tick3.InvalidEdgeException;
import uk.ac.cam.rkh23.Algorithms.Tick3.MaxFlowNetwork;
import uk.ac.cam.rkh23.Algorithms.Tick3.TargetUnreachable;

public class Graph extends GraphBase {

	public Graph(int[][] adj) {
		super(adj);
	}

	public Graph(String file) throws IOException {
		super(file);
	}

	public Graph(URL url) throws IOException {
		super(url);
	}

	@Override
	public List<Integer> getFewestEdgesPath(int src, int target) throws TargetUnreachable {
		Queue<Integer> toexplore = new LinkedList<Integer>();

		boolean[] visited = new boolean[getNumVertices()];
		int[] comeFrom = new int[getNumVertices()];
		for (int i = 0; i < comeFrom.length; i++) {
			comeFrom[i] = -1;
		}

		toexplore.add(src);
		visited[src] = true;

		while (!toexplore.isEmpty()) {
			int v = toexplore.poll();
			List<Integer> neighbours = getNeighbours(v);
			for (int w : neighbours) {
				if (!visited[w]) {
					toexplore.add(w);
					visited[w] = true;
					comeFrom[w] = v;
				}
			}
		}

		if (!visited[target]) {
			throw new TargetUnreachable();
		} else {
			List<Integer> path = new ArrayList<Integer>();
			path.add(target);
			while (comeFrom[path.get(0)] != src) {
				path.add(0, comeFrom[path.get(0)]);
			}
			path.add(0, src);
			return path;
		}
	}

	private List<Integer> getNeighbours(int v) {
		List<Integer> neighbours = new ArrayList<Integer>();
		for (int i = 0; i < this.getNumVertices(); i++) {
			try {
				if (v != i && getWeight(v, i) > 0) {
					neighbours.add(i);
				}
			} catch (InvalidEdgeException e) {
				e.printStackTrace();
			}
		}
		return neighbours;
	}

	@Override
	public MaxFlowNetwork getMaxFlow(int s, int t) {
		try {
			// graph with remaining available capacity after flow's been added
			Graph resGraph = new Graph(this.mAdj);
			int flow = 0;
			while (true) {
				HashSet<Integer> set = constructSet(resGraph, s); // Construct Set using BFS

				// Acutal Ford-Fulkerson stuff
				if (set.contains(t)) {
					List<Integer> path = resGraph.getFewestEdgesPath(s, t);
					int delta = Integer.MAX_VALUE;
					for (int i = 0; i < path.size() - 1; i++) {
						int v = path.get(i);
						int w = path.get(i + 1);
						if (getWeight(v, w) > 0) {
							delta = Math.min(resGraph.getWeight(v, w), delta);
						} else {
							delta = Math.min(this.getWeight(w, v) - resGraph.getWeight(w, v), delta);
						}
					}

					for (int i = 0; i < path.size() - 1; i++) {
						int v = path.get(i);
						int w = path.get(i + 1);
						resGraph.mAdj[v][w] -= delta;
						resGraph.mAdj[w][v] += delta;
					}
					flow += delta;
				} else {
					break;
				}
			}

			// Convert graph into a flow one
			for (int i = 0; i < resGraph.mAdj.length; i++) {
				for (int j = 0; j < resGraph.mAdj[i].length; j++) {
					resGraph.mAdj[i][j] = mAdj[i][j] - resGraph.mAdj[i][j];
				}
			}
			MaxFlowNetwork network = new MaxFlowNetwork(flow, resGraph);
			return network;
		} catch (InvalidEdgeException | TargetUnreachable e) {
			e.printStackTrace();
			return null;
		}
	}

	private HashSet<Integer> constructSet(Graph resGraph, int s) throws InvalidEdgeException {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(s);
		Queue<Integer> toexplore = new LinkedList<Integer>();

		boolean[] visited = new boolean[getNumVertices()];

		toexplore.add(s);
		visited[s] = true;

		while (!toexplore.isEmpty()) {
			int v = toexplore.poll();
			List<Integer> neighbours = resGraph.getNeighbours(v);
			for (int w : neighbours) {
				if (!visited[w]) {
					toexplore.add(w);
					visited[w] = true;

					if (set.contains(v) && !set.contains(w)) {
						if (resGraph.getWeight(v, w) > 0 || this.getWeight(w, v) - resGraph.getWeight(w, v) > 0) {
							set.add(w);
						}
					}
				}
			}
		}
		return set;
	}
}
