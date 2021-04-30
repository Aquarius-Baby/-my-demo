package com.cmy.temp;


/**
 * @Author: cmy
 * @Date: Created in  2021/3/2 7:38 下午
 * @Description:
 */
public class Binary  {

    /**
     * 普通二分搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public int normalBinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 普通左边界二分搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public int normalLeftBinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 检查越界情况
        // 1 2 2 4 target = 6
        if (left > nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }


    /**
     * 普通右边界二分搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public int normalRightBinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 检查越界情况
        // 1 2 2 4 target = 0
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }


}
