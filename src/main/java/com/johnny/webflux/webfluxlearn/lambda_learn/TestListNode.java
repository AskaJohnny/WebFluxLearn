package com.johnny.webflux.webfluxlearn.lambda_learn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author johnny
 * @create 2020-02-19 下午7:34
 **/


public class TestListNode {


    public static void main(String[] args) {

        ListNode listNode = new ListNode(2);
        ListNode listNode_next1 = new ListNode(4);
        ListNode listNode_next2 = new ListNode(3);
        listNode.next = listNode_next1;
        listNode_next1.next = listNode_next2;
        listNode_next2.next = null;

        ListNode listNode2 = new ListNode(5);
        ListNode listNode2_next1 = new ListNode(6);
        ListNode listNode2_next2 = new ListNode(4);
        listNode2.next = listNode2_next1;
        listNode2_next1.next = listNode2_next2;
        listNode2_next2.next = null;

        Solution solution1 = new Solution();

        ListNode resultListNode = solution1.calculateMethod(listNode, listNode2);


        System.out.println(resultListNode);
    }

    static class Solution {
        public ListNode calculateMethod(ListNode listNode1, ListNode listNode2) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();

            recursion(listNode1, list1);
            recursion(listNode2, list2);
            String firstNum = "";
            String secondNum = "";

            for (Integer integer : list1) {
                firstNum = firstNum + String.valueOf(integer);
            }
            for (Integer integer : list2) {
                secondNum = secondNum + String.valueOf(integer);
            }

            Integer result = Integer.valueOf(firstNum) + Integer.valueOf(secondNum);
            System.out.println(result);
            //再把结果设置回 ListNode 即可

            return setListNodeResult(result);
        }

        private void recursion(ListNode listNode, List<Integer> list) {
            list.add(listNode.getValue());
            if (listNode.next != null) {
                ListNode nextNode = listNode.next;
                recursion(nextNode, list);
            }
        }

        private ListNode setListNodeResult(Integer result) {
            List<Integer> list = new ArrayList<>();
            while (result > 0) {
                System.out.print(result % 10 + "，");
                int temp = result % 10;
                list.add(temp);
                result /= 10;
            }

            ListNode resultListNode = new ListNode(list.get(0));
            list.remove(0);
            recursion2(resultListNode, list);

            return resultListNode;
        }

        private void recursion2(ListNode listNode, List<Integer> list) {
            if (list.size() > 0) {
                int value = list.get(0);
                ListNode nextNode = new ListNode(value);
                listNode.next = nextNode;
                list.remove(0);
                recursion2(nextNode, list);
            }else{
                listNode.next = null;
            }

        }

    }

}