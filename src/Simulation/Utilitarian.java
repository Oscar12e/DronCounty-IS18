package Simulation;

import java.util.ArrayList;
import java.util.List;

public class Utilitarian {

    public static int closestLowerNumber(List<Integer> pSearchList, int pValue){
        if (pSearchList.size() == 1)
            return pSearchList.get(0);

        int half = pSearchList.size() / 2;

        if (pSearchList.get(half) == pValue)
            return pValue;
        else if  (pSearchList.get(half) > pValue)
            return closestLowerNumber( pSearchList.subList(0, half), pValue);
        else
            return closestLowerNumber( pSearchList.subList(half, pSearchList.size()), pValue);
    }

    public ArrayList mergeSort(ArrayList<Node> whole){
        ArrayList <Node> left = new ArrayList<>();
        ArrayList <Node> right = new ArrayList<>();
        int center;

        if(whole.size()==1)
            return whole;
        else{
            center = whole.size()/2;
            for (int i=0; i<center; i++)
                left.add(whole.get(i));

            for (int i=center; i<whole.size(); i++)
                right.add(whole.get(i));

            left = mergeSort(left);
            right = mergeSort(right);

            merge(left, right, whole);
        }
        return whole;
    }

    public void merge(ArrayList<Node> left, ArrayList<Node> right, ArrayList<Node> whole){
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
/*
        while(leftIndex<left.size() && rightIndex<right.size()){
            if((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0){
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            }
            else{
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
        ArrayList<Node> rest;
        int restIndex;

        if(leftIndex>=left.size()){
            rest = right;
            restIndex = rightIndex;
        }
        else{
            rest = left;
            restIndex = leftIndex;
        }

        for (int i=restIndex; i<rest.size(); i++){
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }*/
    }

}
