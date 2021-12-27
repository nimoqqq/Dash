# Java8 新的日期和时间API

## 一、为什么在 java8 中引入新的时间和日期

 **因为旧的 API 存在很多历史遗留问题**

1. 设计很差：在 `java.util` 和 `java.sql` 的包中都有日期类。用于解析和格式化的类` SimpleDateFormat` 在 ` java.text`包中定义。
2. 非线程安全：` java.util`这个包里，主要包含了 ` Date`、` Calendar`、和 ` TimeZone`、都不是线程安全的
3. 时区处理麻烦：日期类不提供国际化，没有时区支持。

## 二、旧的 API

* Date：表示一个日期和时间
* Calendar：可以进行简单的日期和时间的计算
* TimeZone：获取时区

~~~java
public class OldDate {

    public static void main(String[] args) throws ParseException {
        System.out.println("-------------Date start-----------------");
        dateDemo();
        System.out.println("-------------Date end-----------------");
        System.out.println("-------------Calendar start-----------------");
        calendarDemo();
        System.out.println("-------------Calendar end-----------------");
        System.out.println("-------------SimpleDateFormat start-----------------");
        simpleDateFormatDemo();
        System.out.println("-------------SimpleDateFormat end-----------------");
    }

    /**
     * Date: 最早的时间API(1.0)
     * {@link Date} java.util.Date
     */
    private static void dateDemo() {
        // 当前日期
        Date date = new Date();
        // 创建时间对象，并指定年月日(年份是从 1900 开始、月份是 0~11，所以 2021/12/25 所以传参为 121、11、25)
        Date date1 = new Date(121, 11, 25);

        System.out.println(date);
        System.out.println(date1);
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        System.out.println(date.getTime()); // 当前时间毫秒数（时间戳），一般使用 System.currentTimeMillis()
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
    }

    /**
     * Calendar: 设置获取时间
     * {@link Calendar} java.util.Calendar
     */
    private static void calendarDemo() {
        Calendar calendar = Calendar.getInstance(); // 获取当前时间
        // 获取当前时间:
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = 1 + c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);
        int w = c.get(Calendar.DAY_OF_WEEK);
        int hh = c.get(Calendar.HOUR_OF_DAY);
        int mm = c.get(Calendar.MINUTE);
        int ss = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);
        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);
        // 清除所有:
        c.clear();
        // 设置年月日:
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, 11);
        c.set(Calendar.DATE, 25);
        // 设置时间:
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 35);
        c.set(Calendar.SECOND, 12);
        // 转为 Date 对象
        System.out.println(c.getTime());
    }

    /**
     * SimpleDateFormat: 时间格式化
     * {@link SimpleDateFormat} java.text.SimpleDateFormat
     */
    private static void simpleDateFormatDemo() throws ParseException {
        //使用指定模型创建对象
        //yyyy-MM-dd hh:mm:ss 十二小时制、yyyy-MM-dd HH:mm:ss 二十四小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Date 转 String
        final String format = sdf.format(new Date());
        System.out.println(format);
        //String 转 Date
        final Date parse = sdf.parse("2021-12-25 23:46:20");
        System.out.println(parse);
    }
}
~~~



## 三、新的 API

* LocalDate

  > ```apl
  > * {@link LocalDate} 用于表示当前日期，他只用于表示日期，不包含时间
  > * 设置日期不用在像{@link java.util.Date}一样，从 1990 开始计算。
  > * 日期所见即所得
  > final LocalDate now = LocalDate.now();
  > ```

  | 修饰符 | 返回类型  |                  方法                   |                  说明                  |                  示例                  |
  | :----: | :-------: | :-------------------------------------: | :------------------------------------: | :------------------------------------: |
  | static | LocalDate |                  now()                  |              获取今天日期              | final LocalDate now = LocalDate.now(); |
  | static | LocalDate | of(int year, int month, int dayOfMonth) |          设置日期,并创建对象           |      LocalDate.of(2021, 12, 25);       |
  | static | LocalDate |        parse(CharSequence text)         |          设置日期,并创建对象           |     LocalDate.parse("2021-12-25");     |
  |   -    | LocalDate |        plusDays(long daysToAdd)         |             当前时间加一天             |            now.plusDays(1);            |
  |   -    | DayOfWeek |             getDayOfWeek()              | 获取星期几字段，这是一个枚举 DayOfWeek |           now.getDayOfWeek()           |
  |   -    |    int    |             getDayOfMonth()             |              获取月份字段              |          now.getDayOfMonth()           |

  

* LocalTime

  > ```apl
  > * {@link LocalTime} 类似于 {@link LocalDate}，一个只用来表示时间的类
  > * 默认格式：hh:mm:ss:nnn
  > LocalTime now = LocalTime.now();
  > ```

  | 修饰符 | 返回类型 | 方法  |     说明     |      示例       |
  | :----: | :------: | :---: | :----------: | :-------------: |
  |        |          | now() | 获取当前时间 | LocalTime.now() |
  |        |          |       |              |                 |
  |        |          |       |              |                 |
  |        |          |       |              |                 |

  

* LocalDateTime

  |      |      |      |      |
  | ---- | ---- | ---- | ---- |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |
  |      |      |      |      |

  

* ZonedDateTime

* Instant

* Duration

* Period

* DateTimeFormatter

* TemporalAdjuster

~~~java

~~~



## 四、新旧日期时间 API 的区别

> **JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat*，官方给出的解释：simple beautiful strong immutable thread-safe。***
>
> ~~~mipsasm
>   JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat，官方给出的解释：simple beautiful strong immutable thread-safe							——引用《阿里巴巴Java开发手册》	
> ~~~



## 五、时间戳



