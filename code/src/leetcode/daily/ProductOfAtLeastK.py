class ProductOfNumbers:

    def __init__(self):
        self.pre: list[int] = []

    def add(self, num: int) -> None:
        if num == 0:
            self.pre = []
            return
        if self.pre:
            num *= self.pre[-1]
        self.pre.append(num)

    def getProduct(self, k: int) -> int:
        if not self.pre or k > len(self.pre):
            return 0
        div = 1
        if k < len(self.pre):
            div = self.pre[len(self.pre)-k-1]
        return self.pre[-1] // div
        


# Your ProductOfNumbers object will be instantiated and called as such:
# obj = ProductOfNumbers()
# obj.add(num)
# param_2 = obj.getProduct(k)