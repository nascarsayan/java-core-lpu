package leetcode;

public class RainwaterTrapping {
    class Solution {
        public int trap(int[] height) {
            int columnCount = height.length;
            int[] tallestFromLeft = new int[columnCount];
            int[] tallestFromRight = new int[columnCount];
            var mx = Integer.MIN_VALUE;
            for (int i = 0; i < columnCount; i++) {
                tallestFromLeft[i] = mx = Math.max(height[i], mx);
            }
            mx = Integer.MIN_VALUE;
            for (int i = columnCount-1; i >= 0; i--) {
                tallestFromRight[i] = mx = Math.max(height[i], mx);
            }

            int trapped = 0;
            for (int i = 0; i < columnCount; i++) {
                // Water level at this location will be the minimu height
                // among the tallest left and right.
                // Trapped amount = water height - height of block.
                trapped += Math.min(tallestFromLeft[i], tallestFromRight[i]) - height[i];
            }
            return trapped;
        }
    }
}
