package com.example.leetcode.Folder.interview;

import java.util.*;

public class sync {

    public static void main(String[] args) {

//        List< String> listStr= Arrays.asList("eat","tea","tan","ate","nat","bat");

        List< String> listStr= Arrays.asList ("listen", "silent", "enlist", "abc", "cab", "bac", "rat", "tar", "art", "forge");
        List<String> temp=new ArrayList<>();

        int len=listStr.size();
        Map<String, List<String>> map1=new HashMap<>();
        for(int i=0;i<len;i++){
            String val=listStr.get(i) ;
            System.out.println(val);
            char[] ch=val.toCharArray();
            Arrays.sort(ch);
            String keyy=String.valueOf(ch);

            System.out.println(keyy);
            temp=new ArrayList<>();
            temp=  map1.get(keyy) ; // nul ["listen", "silent", "enlist"]
//            temp=

            System.out.println(temp);

            System.out.println(temp);
           if(temp!=null  ){
               System.out.println("map1.get(keyy) ;"+ map1.get(keyy) );
               temp=map1.get(keyy) ;
               temp.add(val.toString());
               System.out.println("aftertemp after");
           }
           else{
                System.out.println("in else");
                temp=new ArrayList<>();
               temp.add(val.toString());
           }
            System.out.println("aftertemp");


            map1.put(keyy,temp);
        }

        System.out.println(map1);


    }
}
