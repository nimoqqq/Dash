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

  | 修饰符 |   返回类型    |           方法           |                   说明                    |            示例             |
  | :----: | :-----------: | :----------------------: | :---------------------------------------: | :-------------------------: |
  | static |   LocalTime   |          now()           |               获取当前时间                |       LocalTime.now()       |
  | static |   LocalTime   | of(int hour, int minute) |   从一小时分钟获取一个 LocalTime的实例    |    LocalTime.of(12, 31)     |
  | static |   LocalTime   | parse(CharSequence text) |   从一小时分钟获取一个 LocalTime的实例    |  LocalTime.parse("12:31")   |
  |   -    | LocalDateTime |  atDate(LocalDate date)  | 结合这个时间创建一个`LocalDateTime`的日期 | now.atDate(LocalDate.now()) |

  

* LocalDateTime

  > ```apl
  > * 表示日期和时间的结合
  > LocalDateTime now = LocalDateTime.now();
  > ```

  | 修饰符 |   返回类型    |                             方法                             |                        说明                        |                            示例                             |
  | :----: | :-----------: | :----------------------------------------------------------: | :------------------------------------------------: | :---------------------------------------------------------: |
  | static | LocalDateTime |                            now()                             |                    获取当前日期                    |                    LocalDateTime.now();                     |
  | static | LocalDateTime | of(int year, Month month, int dayOfMonth, int hour, int minute) | 从年，月，日，小时和分钟获得 `LocalDateTime`的实例 |     LocalDateTime.of(2021, Month.DECEMBER, 28, 06, 30)      |
  | static | LocalDateTime |              of(LocalDate date,LocalTime time)               |     从日期和时间获取一个 `LocalDateTime`的实例     |     LocalDateTime.of(LocalDate.now(), LocalTime.now())      |
  |   -    | LocalDateTime |               with(TemporalAdjuster adjuster)                |          通过调用指定调整器,进行复杂运算           | now.with(TemporalAdjusters.firstDayOfNextMonth())下月第1 天 |

  

* ZonedDateTime

  > ~~~apl
  > * ZoneDateTime 类来表示某时区下的时间。
  > ~~~

  | 修饰符 | 返回类型 | 方法 | 说明 | 示例 |
  | :----: | :------: | :--: | :--: | :--: |
  |        |          |      |      |      |
  |        |          |      |      |      |
  |        |          |      |      |      |
  |        |          |      |      |      |

  

* Instant

  > ~~~apl
  > * 这个当前时间戳在java.time中以Instant类型表示，我们用Instant.now()获取当前时间戳，效果和System.currentTimeMillis()类似。
  > * Instant内部只有两个核心字段：一个是以秒为单位的时间戳，一个是更精确的纳秒精度。它和System.currentTimeMillis()返回的long相比，只是多了更高精度的纳秒。如果只是为了获取秒数或者毫秒数，使用System.currentTimeMillis()来得更为方便.
  > 
  > Instant instant = Instant.now();
  > ~~~

  | 修饰符 | 返回类型 |       方法       |    说明     |           示例           |
  | :----: | :------: | :--------------: | :---------: | :----------------------: |
  |   -    |   long   | getEpochSecond() |  时间戳/秒  | instant.getEpochSecond() |
  |   -    |   long   |  toEpochMilli()  | 时间戳/毫秒 |  instant.toEpochMilli()  |

  

* Duration

  > ~~~apl
  > * 用于计算两个时间（秒，纳秒）间隔。
  > ~~~

  | 修饰符 | 返回类型 |                             方法                             |                        说明                         | 示例 |
  | :----: | :------: | :----------------------------------------------------------: | :-------------------------------------------------: | :--: |
  |        | Duration | **[between](https://www.matools.com/file/manual/jdk_api_1.8_google/java/time/Duration.html#between-java.time.temporal.Temporal-java.time.temporal.Temporal-)**([Temporal](https://www.matools.com/file/manual/jdk_api_1.8_google/java/time/temporal/Temporal.html) startInclusive, [Temporal](https://www.matools.com/file/manual/jdk_api_1.8_google/java/time/temporal/Temporal.html) endExclusive) | 获取一个 `Duration`表示两个时间对象之间的持续时间。 |      |

  

* Period

  > ~~~apl
  > * 用于计算两个日期（年月日）间隔。
  > ~~~

  | 修饰符 | 返回类型 |                             方法                             |                          说明                           | 示例 |
  | :----: | :------: | :----------------------------------------------------------: | :-----------------------------------------------------: | :--: |
  | Static |  Period  | between(LocalDate startDateInclusive, LocalDate endDateExclusive) | 获得一个`Period` ，包括两个日期之间的年数，月份和日期。 |      |

  

* DateTimeFormatter

  > ~~~apl
  > * 格式化器用于打印和解析日期时间对象。
  > * 更复杂的格式化程序由DateTimeFormatterBuilder提供
  > LocalDateTime now = LocalDateTime.now();
  > //指定格式 使用系统默认的格式 2021-12-28T13:00:27.118951
  > DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  > ~~~

  | 修饰符 |     返回类型      |                       方法                        |                        说明                        | 示例 |
  | :----: | :---------------: | :-----------------------------------------------: | :------------------------------------------------: | :--: |
  |   -    |      String       |         format(TemporalAccessor temporal)         | 这将使用格式化程序的规则将日期时间格式化为String。 |      |
  | static | DateTimeFormatter |             ofPattern(String pattern)             |           使用指定的模式创建格式化程序。           |      |
  |   -    | TemporalAccessor  | parse(CharSequence text,  ParsePosition position) |         *将字符串解析为一个 日期时间类型*          |      |

  

* TemporalAdjuster

  > ~~~apl
  > * 调整时间对象的策略接口。
  > * 调整器是修改时间物体的关键工具。 它们存在于外部化调整过程中，根据策略设计模式允许不同的方法。
  > ~~~

  | 修饰符 | 返回类型 |             方法              |                             说明                             | 示例 |
  | :----: | :------: | :---------------------------: | :----------------------------------------------------------: | :--: |
  |   -    | Temporal | adjustInto(Temporal temporal) | 调整指定的时间对象。这使用实现类中封装的逻辑来调整指定的时间对象 |      |

  

* TemporalAdjusters

  > ~~~apl
  > * 实现了一些标准的调整器：
  > ~~~

  | 修饰符 |     返回类型     |               方法                |                             说明                             | 示例 |
  | :----: | :--------------: | :-------------------------------: | :----------------------------------------------------------: | :--: |
  | static | TemporalAdjuster |         firstDayOfMonth()         |                返回设置为当月的第一天的新日期                |      |
  | static | TemporalAdjuster |         lastDayOfMonth()          |   返回“最后一个月的”调整器，该日期设置为当前月份的最后一天   |      |
  | static | TemporalAdjuster |          lastDayOfYear()          |             返回设置为当前年份的最后一天的新日期             |      |
  | static | TemporalAdjuster | firstInMonth(DayOfWeek dayOfWeek) | 在同一个月内返回与第一个匹配的星期几的新日期。 这是用于表达，如“3月的第一个星期二”。 |      |

  

## 四、新旧日期时间 API 的区别

> **JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat*，官方给出的解释：simple beautiful strong immutable thread-safe。***
>
> ~~~mipsasm
>   JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat，官方给出的解释：simple beautiful strong immutable thread-safe							——引用《阿里巴巴Java开发手册》	
> ~~~



## 五、时间戳

**工具类**

~~~java
/**
  * LocalDateTime转毫秒时间戳
  * @param localDateTime LocalDateTime
  * @return 时间戳
  */
public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
    try {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return instant.toEpochMilli();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

/**
 * 时间戳转LocalDateTime
 * @param timestamp 时间戳
 * @return LocalDateTime
 */
public static LocalDateTime timestampToLocalDateTime(long timestamp) {
    try {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

~~~



**参考博客**

> [Java 8 API](https://www.cnblogs.com/Mystogan/p/15305587.html)
>
> https://www.matools.com/api/java8
>
> [廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744/1303791989162017)

