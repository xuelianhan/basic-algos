package org.ict.algorithm.company.coupang;

/**
 * @author sniper
 * @date 17 Aug 2023
 */
public class ProductOfArrayElementsExceptSelf {

    /**
     * This code first initializes two arrays, leftProducts and rightProducts.
     * The leftProducts array stores the product of all elements to the left of the current element,
     * and the rightProducts array stores the product of all elements to the right of the current element.
     *
     * The code then iterates over the elements in the array and computes the product of all elements except self.
     * This is done by multiplying the current element's left product with its right product.
     *
     * The code finally returns the array of products of all elements except self.
     * @see <a href="github.com/drjoewolfe/quetzal-algorithms"></a>
     * @param arr
     * @return
     */
    public int[] productExceptSelf(int[] arr) {
        int n = arr.length;

        // Initialize the left and right products.
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];

        leftProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * arr[i - 1];
        }

        rightProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * arr[i + 1];
        }

        // Compute the product of all elements except self.
        int[] productOfAllElementsExceptSelf = new int[n];
        for (int i = 0; i < n; i++) {
            productOfAllElementsExceptSelf[i] = leftProducts[i] * rightProducts[i];
        }

        return productOfAllElementsExceptSelf;
    }
}
