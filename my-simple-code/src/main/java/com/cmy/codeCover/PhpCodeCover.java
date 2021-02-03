package com.cmy.codeCover;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: cmy
 * @Date: Created in  2021/1/20 8:01 下午
 * @Description:
 */
public class PhpCodeCover {
    public static void main(String[] args) {
        String path = "/Users/cmy/CodeSpace/cmy/demo/my-simple-code/src/main/resources/patch.txt";

        HashMap<String, List<Integer>> addLineHashMap = getData(path);
        HashMap<String, List<Integer>> uncCovMap = new HashMap<>();
        List<Integer> testList = new ArrayList<>();
        testList.add(25);
        testList.add(26);
        testList.add(27);
        uncCovMap.put("StudentController.php", testList);
        calCov(addLineHashMap, uncCovMap);
    }

    public static HashMap<String, List<Integer>> getData(String file) {
        try {
            HashMap<String, List<Integer>> hashMap = new HashMap<>();
            InputStream fileInputStream = new FileInputStream(new File(file));
            InputStreamReader isr = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(isr);
            String result = "";
            boolean flag = false;
            String fileName = "";
            while ((result = bufferedReader.readLine()) != null) {
                if (result.startsWith("+++")) {
                    String[] array = result.split("/");
                    fileName = array[array.length - 1];
                    flag = true;
                }
                if (flag && result.startsWith("@@")) {
                    String regex = "((\\+)[0-9]+(,[0-9]+)) ?(?=\\@\\@)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(result);
                    List<Integer> numList = hashMap.getOrDefault(fileName, new ArrayList<>());
                    if (matcher.find()) {
                        String res = matcher.group(0).trim();
                        String[] nums = res.split(",");
                        int start = Integer.valueOf(nums[0]);
                        int length = Integer.valueOf(nums[1]);
                        for (int i = 0; i < length; i++) {
                            numList.add(start + i);
                        }
                    }
                    hashMap.put(fileName, numList);
                }
            }
            return hashMap;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param addLine   代码增量信息
     * @param unCovLine 代码覆盖情况
     */
    public static void calCov(HashMap<String, List<Integer>> addLine, Map<String, List<Integer>> unCovLine) {
        int totalLineNum = 0;
        int unCovLineNum = 0;
        for (String fileName : addLine.keySet()) {
            List<Integer> fileUnCovLineList = new ArrayList<>();
            List<Integer> addLineList = addLine.get(fileName);
            if (unCovLine.containsKey(fileName)) {
                List<Integer> unCovLineList = unCovLine.get(fileName);
                for (Integer lineNum : addLineList) {
                    if (unCovLineList.contains(lineNum)) {
                        fileUnCovLineList.add(lineNum);
                    }
                }
            } else {
                fileUnCovLineList.addAll(addLineList);
            }
            double percent = 1 - (double) fileUnCovLineList.size() / addLineList.size();
            System.out.println(String.format("文件%s 覆盖率 %.2f", fileName, percent));
            System.out.println(String.format("      行号 %s 没有被覆盖到", fileUnCovLineList.toString()));

            unCovLineNum += fileUnCovLineList.size();
            totalLineNum += addLineList.size();
        }
        double allPercent = 1 - (double) unCovLineNum / totalLineNum;
        System.out.println(String.format("覆盖率 %.2f ", allPercent));
    }
}