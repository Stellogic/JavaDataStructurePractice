好的，没问题！从 LeetCode 的核心代码模式切换到牛客的 ACM 模式，主要就是需要自己处理输入和输出。别担心，这并不复杂，记住几个核心模板就能快速上手。

### ACM 模式速成指南 (Java)

在牛客等平台的 ACM 模式下，你需要：
1.  创建一个 `main` 方法作为程序入口。
2.  使用 `java.util.Scanner` 或 `java.io.BufferedReader` 从标准输入流 (`System.in`) 读取数据。
3.  将解题逻辑写在 `main` 方法中。
4.  使用 `System.out.println()` 或 `System.out.print()` 将结果输出到标准输出流。

---

### 核心工具：Scanner vs BufferedReader

对于大多数笔试题，`Scanner` 更简单易用，是快速上手的首选。但在处理大规模输入时，`BufferedReader` 效率更高。

*   **`Scanner`**: 使用方便，可以直接读取 `int`, `long`, `double`, `String` 等类型。
*   **`BufferedReader`**: 速度更快，但只能按行读取字符串，需要手动进行类型转换和分割。

**建议**：时间紧急的话，先熟练掌握 `Scanner` 的用法。

---

### 关键：输入处理模板

以下是几种最常见的输入场景，直接套用模板即可：

#### 场景一：单行或多行固定数量的输入

这是最简单的情况，提前知道要读取多少个数据。

**模板代码 (Scanner):**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 读取一个整数
        int n = sc.nextInt();
        
        // 读取一个字符串
        String s = sc.next();
        
        // 读取一个浮点数
        double d = sc.nextDouble();
        
        // 你的解题逻辑...
        System.out.println("输出结果");
        
        sc.close();
    }
}
```

#### 场景二：读取数量不定的多组数据 (循环读取)

通常题目会说“处理多组输入数据”或“输入到文件末尾结束”。使用 `while(sc.hasNext())` 循环是标准做法。

**模板代码 (Scanner):**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) { // 或者 sc.hasNextInt() 等
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            // 当输入 a 和 b 均为 0 时结束 (某些题目的特定要求)
            if (a == 0 && b == 0) {
                break;
            }
            
            System.out.println(a + b);
        }
        sc.close();
    }
}
```

#### 场景三：第一行指定后续输入的行数或数量

这是非常经典和常见的模式，比如第一行输入一个整数 `n`，表示接下来有 `n` 行数据或 `n` 个数据。

**模板代码 (Scanner):**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 读取第一行的整数，确定后续循环次数
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            // 在这里读取每行的数据
            int val1 = sc.nextInt();
            String str1 = sc.next();
            
            // 你的解题逻辑...
        }
        
        sc.close();
    }
}
```

---

### 数据结构输入模板

刷 Hot 100 肯定会遇到数组、链表等结构，下面是它们的 ACM 输入模板。

#### 读取一维数组

**情况 A: 第一行是数组长度，第二行是数组元素**
*   输入示例:
    ```
    5
    1 2 3 4 5
    ```

**模板代码:**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 读取数组长度
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt(); // 循环读取数组元素
        }
        
        // 接下来就可以使用 nums 数组进行解题了
        
        sc.close();
    }
}
```

**情况 B: 一行内输入未知数量的数字**
*   输入示例:
    ```
    1 2 3 4 5
    ```

**模板代码:**
```java
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine(); // 读取整行
        String[] parts = line.split(" "); // 按空格分割
        
        List<Integer> list = new ArrayList<>();
        for (String part : parts) {
            list.add(Integer.parseInt(part));
        }
        
        // 接下来可以使用 list 进行解题
        
        sc.close();
    }
}
```

#### 读取二维数组/矩阵

*   输入示例 (第一行为行数和列数):
    ```
    3 4
    1 2 3 4
    5 6 7 8
    9 10 11 12
    ```

**模板代码:**
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt(); // 行数
        int cols = sc.nextInt(); // 列数
        
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        
        // 接下来就可以使用 matrix 进行解题了
        
        sc.close();
    }
}
```

---

### 重要避坑技巧

*   **`nextInt()` 和 `nextLine()` 的混用问题**：`nextInt()` 只会读取数字，不会读取数字后面的换行符。如果紧接着调用 `nextLine()`，它会立刻读到那个换行符并返回一个空字符串。
    *   **解决方案**：在 `nextInt()` 之后，如果需要读取下一行字符串，额外加一个 `sc.nextLine()` 来消耗掉换行符。

    ```java
    int n = sc.nextInt(); 
    sc.nextLine(); // 关键！消耗掉 n 后面的换行符
    String s = sc.nextLine(); // 这样才能正确读取下一行的字符串
    ```

*   **全局只创建一个 Scanner 对象**：在一个程序中，`new` 一个 `Scanner(System.in)` 就够了，不要在循环里反复创建，否则可能导致不可预知的问题。

### 高性能模板 (BufferedReader)

如果输入数据量特别大 (例如超过 10^5)，使用 `BufferedReader` 会更保险。

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            System.out.println(a + b);
        }
    }
}
```

非常好的问题！这正是从 LeetCode 模式过渡到 ACM 模式的核心思想：**将你的解题逻辑封装在独立的方法中，然后在 `main` 函数里处理输入输出并调用这个方法**。

这样做的好处是巨大的：
1.  **结构清晰**：`main` 函数负责“脏活累活”（处理输入输出），而你的核心方法专注于算法本身。
2.  **无缝衔接**：你写的那个核心方法，其实就和你在 LeetCode 上写的函数一模一样！
3.  **便于调试**：你可以很方便地在 `main` 函数里构造不同的测试用例来调用你的核心方法。

下面我们通过实例来讲解如何在 ACM 模式中定义和使用方法。

---

### 方式一：使用 `static` 静态方法 (最常用、最推荐)

在笔试和竞赛中，通常整个程序都在一个 `Main` 类里。因为 `main` 函数本身是 `static` 的，所以从 `main` 函数里直接调用的方法也最好定义为 `static`。这是最简单、最直接的方式。

#### 示例：两数之和问题

假设题目是：输入两数 a 和 b，输出它们的和。

**1. LeetCode 核心代码 (你熟悉的方式)**
你可能会写一个这样的方法：
```java
class Solution {
    public int sum(int a, int b) {
        return a + b;
    }
}
```

**2. ACM 模式下的实现 (带 `static` 方法)**
现在，我们把它放进一个完整的 ACM 模式程序里。

```java
import java.util.Scanner;

public class Main {

    // 1. 将你的核心解题逻辑放在一个 static 方法里
    // 这个方法和你写 LeetCode 函数几乎完全一样！
    public static int solve(int a, int b) {
        return a + b;
    }

    // 2. main 函数是程序入口，负责处理输入输出
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 循环处理多组输入
        while (sc.hasNextInt()) {
            // a. 在 main 中读取输入
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            // b. 在 main 中调用你的核心方法
            int result = solve(a, b);
            
            // c. 在 main 中打印结果
            System.out.println(result);
        }
        
        sc.close();
    }
}
```

**看，`solve(int a, int b)` 方法和你在 LeetCode 写的几乎没区别。你只需要额外编写 `main` 函数来处理 I/O，并调用它即可。**

---

### 方式二：创建类的实例来调用方法 (面向对象方式)

如果你不想用 `static` 方法，或者你的解法需要维护一些类成员变量（虽然在笔试中较少见），你可以创建 `Main` 类自己的一个实例，然后通过这个实例来调用方法。

#### 示例：还是两数之和

```java
import java.util.Scanner;

public class Main {

    // 1. 定义一个普通的成员方法 (非 static)
    public int solve(int a, int b) {
        // 假设这里有一些复杂的逻辑，可能需要用到类的成员变量
        return a + b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 2. 创建 Main 类的一个实例
        Main mySolution = new Main();
        
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            // 3. 通过实例来调用方法
            int result = mySolution.solve(a, b);
            
            System.out.println(result);
        }
        
        sc.close();
    }
}
```
这种方式在功能上完全等价，只是写法上更偏向于传统的面向对象编程。

---

### 实践一下：力扣 Hot 100 题目改造

我们拿一个你可能刷过的 Hot 100 题目来举例，比如 **“两数之和” (Two Sum)**。

**题目描述 (ACM 模式)**：
第一行是一个整数 `n`，代表数组的长度。
第二行是 `n` 个整数，代表数组元素。
第三行是一个整数 `target`。
你需要找出数组中和为 `target` 的两个数的下标，并按升序输出。

**输入示例**：
```
4
2 7 11 15
9
```
**输出示例**：
```
0 1
```

**改造后的 ACM 代码：**

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // 这是你的 LeetCode 核心代码，几乎不用改动！
    // 只是返回值根据题目要求可能需要调整
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        // 题目保证有解，这里只是为了编译通过
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // --- 输入处理 ---
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        
        // --- 调用核心逻辑 ---
        // 因为 twoSum 不是 static 的，所以需要创建实例
        Main solution = new Main(); 
        int[] result = solution.twoSum(nums, target);
        
        // --- 输出处理 ---
        // 根据题目要求，可能需要排序后再输出
        if (result[0] > result[1]) {
            int temp = result[0];
            result[0] = result[1];
            result[1] = temp;
        }
        System.out.println(result[0] + " " + result[1]);
        
        sc.close();
    }
}
```

