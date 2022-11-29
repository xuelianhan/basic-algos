class BinarySearch:
    def search(self, nums: List[int], target: int) -> int:
        lo, hi = 0, len(nums) - 1
        while lo <= hi:
            mid = lo + int((hi - lo) / 2)
            if target < nums[mid]:
                hi = mid - 1
            elif target > nums[mid]:
                lo = mid + 1
            else:
                return mid
        return -1


if __name__ == '__main__':
    import sys
    # read the integers from a file
    with open(sys.argv[1]) as f:
        whitelist = [int(i) for i in f]
    whitelist = sorted(whitelist)

    # read integer key from standard input; print if not in whitelist
    bs = BinarySearch()
    for line in sys.stdin:
        key = int(line)
        if bs.search(whitelist, key) == -1:
            print(key)