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

### 总结与建议

1.  **先用 `Scanner`**：对于笔试，`Scanner` 的便利性通常优于其性能短板。先把上面的模板记熟。
2.  **复制粘贴**：把这些模板代码保存在一个地方，笔试时直接复制粘贴，然后往里填核心逻辑，能节省大量时间。
3.  **练习一下**：牛客网有专门的 ACM 模式输入输出练习场，在笔试前花10-20分钟去熟悉一下手感。

祝你笔试顺利！