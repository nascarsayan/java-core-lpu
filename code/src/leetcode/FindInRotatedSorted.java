package leetcode;

public class FindInRotatedSorted {
    static class Solution {
        // To make it FFFFTTTTT
        private boolean checkIfAfterRotation(int[] nums, int idx) {
            return nums[idx] < nums[0];
        }

        public int findRotationPoint(int[] nums) {
            int n = nums.length;
            // If last > first, array not rotated.
            if (nums[n-1] > nums[0]) return 0;
            int st = 0, fl = n-1, ans = 0;
            while (st <= fl) {
                var mid = (st + fl) / 2;
                // We are searching for the first true location
                // of the expression `nums[mid] < nums[0]`
                if (checkIfAfterRotation(nums, mid)) {
                    // inflection already happened.
                    ans = mid;
                    fl = mid-1;
                    continue;
                }
                st = mid+1;
            }
            return ans;
        }

        // To make it FFFFTTTTT
        private boolean checkIfGTE(int num, int target) {
            return target <= num;
        }

        public int findTarget(int[] nums, int st, int fl, int target) {
            int ans = st;
            while (st <= fl) {
                var mid = (st + fl) / 2;
                // We are searching for the first true location
                if (checkIfGTE(nums[mid], target)) {
                    ans = mid;
                    fl = mid-1;
                    continue;
                }
                st = mid+1;
            }
            if (nums[ans] == target) return ans;
            return -1;
        }

        public int search(int[] nums, int target) {
            var start = findRotationPoint(nums);
            // System.out.printf("Point of rotation: %d\n", start);
            int st, fl;
            if (start == 0) {
                st = 0; fl = nums.length-1;
            }
            else if (target < nums[0]) {
                // search within start and n-1.
                st = start; fl = nums.length-1;
            } else {
                // search within 0 and start-1.
                st = 0; fl = start-1;

            }
            return findTarget(nums, st, fl, target);
        }

        public static void main(String[] args) {
            int[] arr = {1,3};
            System.out.println((new Solution()).search(arr, 3));
        }
    }
}
