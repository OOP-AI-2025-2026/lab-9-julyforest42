package ua.opnu;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Iterator;

public class Task {
    public static void main(String[] args) {

    }

    public void removeShorterStrings(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int start = (list.size() % 2 == 0) ? list.size() - 2 : list.size() - 3;

        for (int i = start; i >= 0; i -= 2) {
            String s1 = list.get(i);
            String s2 = list.get(i + 1);

            if (s1.length() < s2.length()) {
                list.remove(i);
            } else if (s2.length() < s1.length()) {
                list.remove(i + 1);
            } else {
                list.remove(i);
            }
        }
    }

    public void stutter(List<String> list) {
        if (list == null) {
            return;
        }

        int originalSize = list.size();
        for (int i = 0; i < originalSize; i++) {
            String s = list.get(2 * i);
            list.add(2 * i + 1, s);
        }
    }

    public void switchPairs(List<String> list) {
        if (list == null) {
            return;
        }

        for (int i = 0; i + 1 < list.size(); i += 2) {
            String tmp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, tmp);
        }
    }

    public void removeDuplicates(List<String> list) {
        if (list == null || list.size() < 2) {
            return;
        }

        int i = 1;
        while (i < list.size()) {
            if (list.get(i).equals(list.get(i - 1))) {
                list.remove(i);
            } else {
                i++;
            }
        }
    }

    public void markLength4(List<String> list) {
        if (list == null) {
            return;
        }

        int i = 0;
        while (i < list.size()) {
            String s = list.get(i);
            if (s.length() == 4) {
                list.add(i, "****");
                i += 2;
            } else {
                i++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        if (queue == null) {
            return true;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            queue.add(value);
            stack.push(value);
        }

        boolean isPal = true;

        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            int fromStack = stack.pop();
            if (value != fromStack) {
                isPal = false;
            }
            queue.add(value);
        }

        return isPal;
    }

    public void reorder(Queue<Integer> queue) {
        if (queue == null || queue.size() <= 1) {
            return;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();
        int positivesCount = 0;

        // Розділяємо на від'ємні (у стек) і невід'ємні (залишаємо в черзі)
        for (int i = 0; i < size; i++) {
            int x = queue.remove();
            if (x < 0) {
                stack.push(x);   // послідовність за зростанням |x| → у стек
            } else {
                queue.add(x);
                positivesCount++;
            }
        }

        // Додаємо від'ємні в чергу (у правильній для них послідовності)
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // Повертаємо від'ємні на початок, роблячи ротацію
        for (int i = 0; i < positivesCount; i++) {
            queue.add(queue.remove());
        }
    }

    public void rearrange(Queue<Integer> queue) {
        if (queue == null || queue.size() <= 1) {
            return;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int n = queue.size();

        for (int i = 0; i < n; i++) {
            int x = queue.remove();
            if (x % 2 == 0) {
                queue.add(x);
            } else {
                stack.push(x);
            }
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        int n2 = queue.size();
        stack.clear();
        for (int i = 0; i < n2; i++) {
            int x = queue.remove();
            if (x % 2 == 0) {
                queue.add(x);
            } else {
                stack.push(x);
            }
        }

        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    public int maxLength(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return 0;
        }

        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return;
        }

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.length() % 2 == 0) {
                it.remove();
            }
        }
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list2 == null) {
            return 0;
        }

        Set<Integer> set1 = new HashSet<>();
        for (Integer x : list1) {
            set1.add(x);
        }

        Set<Integer> seen = new HashSet<>();
        int count = 0;

        for (Integer x : list2) {
            if (set1.contains(x) && !seen.contains(x)) {
                seen.add(x);
                count++;
            }
        }

        return count;
    }

    public boolean isUnique(Map<String, String> map) {
        if (map == null) {
            return true;
        }

        Set<String> values = new HashSet<>();
        for (String value : map.values()) {
            if (!values.add(value)) {
                return false;
            }
        }
        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();

        if (map1 == null || map2 == null) {
            return result;
        }

        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (map2.containsKey(key) && value.equals(map2.get(key))) {
                result.put(key, value);
            }
        }

        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();

        if (map == null) {
            return result;
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            result.put(value, key);
        }

        return result;
    }

    public int rarest(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Map is empty");
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer value : map.values()) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        int bestValue = 0;
        int bestCount = Integer.MAX_VALUE;
        boolean first = true;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            if (first || count < bestCount || (count == bestCount && value < bestValue)) {
                bestValue = value;
                bestCount = count;
                first = false;
            }
        }

        return bestValue;
    }

    public int maxOccurrences(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;

        for (Integer x : list) {
            int newCount = freq.getOrDefault(x, 0) + 1;
            freq.put(x, newCount);
            if (newCount > max) {
                max = newCount;
            }
        }

        return max;
    }

}
