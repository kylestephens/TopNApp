package com.kyle.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class TopN {

    private PriorityQueue<Integer> inputQueue;

    private Stack<Integer> outputStack;

    public TopN(){
        outputStack = new Stack<Integer>();
    }

    /**
     * Returns the N highest values, highest first,
     * from a file containing individual numbers on each line
     *
     * @param path: Path of the input file
     * @param topN: Number of highest value elements to return
     */
    public void getTopN(String path, int topN) {

        // Ensure a number is supplied
        if(topN <= 0) {
            return;
        }

        FileInputStream inputStream = null;
        Scanner scanner = null;

        // The elements of the priority queue are ordered
        // according to their natural ordering
        // (or by a Comparator provided at queue construction time)
        inputQueue = new PriorityQueue<Integer>(topN);

        try {

            inputStream = new FileInputStream(path);
            scanner = new Scanner(inputStream, "UTF-8");

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                int lineToInt = Integer.parseInt(line);

                if(inputQueue.size() < topN){
                    inputQueue.add(lineToInt);
                }
                else{
                    // retrieves, but do not remove, the head of this queue
                    int min  = inputQueue.peek();

                    if(lineToInt > min){
                        inputQueue.remove();
                        inputQueue.add(lineToInt);
                    }
                }

            }

            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }

            printTopN();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }

        }
    }

    /**
     * Print the top N elements in the file
     */
    public void printTopN() {

        while(!inputQueue.isEmpty()){
            outputStack.push(inputQueue.remove());
        }

        System.out.printf("Outputting the top %s elements\n", outputStack.size());

        while(!outputStack.isEmpty()){
            System.out.print(outputStack.pop());
            String spacer = (outputStack.size() > 0) ? ", " : ".";
            System.out.print(spacer);
        }

    }

}