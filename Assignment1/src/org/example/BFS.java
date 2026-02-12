package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BFS {
    public PriorityQueue<Node> toProcessHeap = new PriorityQueue<>(Comparator.comparingInt(Node::distance));
    public PriorityQueue<Node> processingHeap = new PriorityQueue<>(Comparator.comparingInt(Node::distance));
    public CellData start;
    public CellData end;
    public int numThreads;
    public BFS(int numThreads) {
        this.start = GridData.getStart();
        this.end = GridData.getEnd();
        toProcessHeap.add(new Node(start, 0));
        this.numThreads = numThreads;

    }
    public void start() {
        this.start = GridData.getStart();
        this.end = GridData.getEnd();
        try {
            ExecutorService pool = Executors.newFixedThreadPool(numThreads);
            ExecutorCompletionService<Void> completionService = new ExecutorCompletionService<>(pool);
            for (int i = 0; i < numThreads; i++) {
                completionService.submit(new CellProcessor(end, this),null);
            }
            try {
                completionService.take();
            }
            catch (Exception e) {
                e.printStackTrace();
            }


            pool.shutdown();
        }
        catch (Exception e) {
            System.out.println("Start / End probably missing");
        }

    }

    public synchronized void enqueue(Node node) {
        toProcessHeap.add(node);
    }

    public synchronized Node pop(Node processedNode) {
        if(processedNode != null) {
            processingHeap.remove(processedNode);
        }
        Node popped = toProcessHeap.poll();
        if(popped != null) {
            processingHeap.add(popped);
        }
        return popped;
    }

    public boolean validate(Node processedNode) {
        int distance = processedNode.distance();
        Node processingNode = processingHeap.peek();
        Node toprocessNode = toProcessHeap.peek();
        if(processingNode != null && processingNode.distance() < distance) {
            return false;
        }
        if(toprocessNode != null && toprocessNode.distance() < distance) {
            return false;
        }
        return true;
    }
}

