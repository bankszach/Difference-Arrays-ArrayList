# Difference-Arrays-ArrayLists

In Java, an **array** is a contiguous block of memory with an immutable length chosen at instantiation. Its elements are stored in place—primitives remain un-boxed—so indexed access (`arr[i]`) is a constant-time operation with strong cache locality (Oracle, 2025c). Arrays, however, provide only the minimal operations hard-wired into the language: no built-in insertion, deletion, or bounds checks beyond the JVM’s runtime exception mechanism.

An **`ArrayList<E>`** belongs to the Collections Framework. Internally it wraps a private, grow-able array that is reallocated and copied whenever a call to `add()` would exceed capacity (Oracle, 2025a). This yields amortised **O(1)** appends while offering rich mutator APIs (`add`, `remove`, `sort`) and fail-fast iterators. Because all entries are references, primitives are boxed to wrapper objects, incurring modest heap overhead but enabling a single, uniform container.

The architectural gap drives contrasting performance characteristics. Overwriting an array slot is always **O(1)**; removing the third element of an `ArrayList` is **O(n)** because every subsequent element shifts left. Memory safety also differs: arrays are covariant (`Number[] nums = new Integer[5]`), which may trigger a runtime `ArrayStoreException`; generics make `ArrayList<Number>` type-safe at compile time, eliminating that hazard. Arrays support direct multidimensional layouts (`int[][] grid`), whereas nested `ArrayList` instances add object headers and indirection.

Choosing wisely is therefore context-dependent. Arrays excel in fixed-size, performance-critical domains—numeric kernels, lookup tables, byte buffers. `ArrayList` shines when collection cardinality fluctuates or when readability and API breadth outweigh sporadic reallocation costs. Skilled developers master both abstractions and deploy the one that minimises accidental complexity for each algorithmic scenario (Oracle, 2025b).

---

## Build & Run

```bash
# compile
javac -d out src/edu/zachbanks/daa/DifferenceDemo.java
# execute
java  -cp out edu.zachbanks.daa.DifferenceDemo
Sample Output
yaml
Copy
Edit
Array contents     : [1, 2, 99, 4, 5]
ArrayList contents : [1, 2, 99, 4, 5, 6]
Array push 100 k ints : 0.8 ms
List  push 100 k ints : 3.0 ms
```
###  References
Oracle. (2025a). Java Platform, Standard Edition 8 API Specification: ArrayList<E> class. https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html

Oracle. (2025b). Java Platform, Standard Edition 8 API Specification: Arrays class. https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html

Oracle. (2025c). The Java™ Tutorials: Arrays. https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html