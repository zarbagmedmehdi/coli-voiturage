package Util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Util {

    public List<String> removeOneString (List<String> list,String elem) {
        List<String> list2 = new ArrayList();
        for(int i=0;i<list.size();i++){
            String get=list.get(i);
            if(get.equals(elem)==false)
                list2.add(get);
        }
        return list2;
    }
}
