package Simulation;

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

}
