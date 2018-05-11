# spring-scheduling-samples

Sample application for scheduling jobs with Spring’s `@Scheduled` annotation.

The simple rules that need to be followed to annotate a method with `@Scheduled` are:

***1. The method should have void return type***

***2. The method should not accept any parameters***

To enable the support for scheduling tasks and the `@Scheduled` annotation in Spring – we can use the Java enable-style annotation `@EnableScheduling`

`@EnableScheduling` ensures that a background task executor is created. Without it, nothing gets scheduled.

## Create a scheduled task

The `@Scheduled` annotation defines when a particular method runs.

Some common scheduling approaches are:

1. `@Scheduled(fixedRate = 1000)` which specifies the interval between method invocations measured from the start time of each invocation.

2. `@Scheduled(fixedDelay = 5000)` which specifies the interval between invocations measured from the completion of the task.

3. `@Scheduled(cron=". . .")` expressions for more sophisticated task scheduling.
The breakdown for the cron expression is `@Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")` example: `@Scheduled(cron = "0 15 10 15 * ?")`

We can use some special characters for the cron expression like:

* \* represents all values. So, if it is used in the second field, it means every second.
* ? represents no specific value and can be used in either the day of month or day of week field — where using one invalidates the other. If we specify it to trigger on the 15th day of a month, then a ? will be used in the Day of week field.
* \- represents an inclusive range of values. For example, 1-3 in the hours field means the hours 1, 2, and 3.
* , represents additional values. For example, putting MON,WED,SUN in the day of week field means on Monday, Wednesday, and Sunday.
* / represents increments. For example 0/15 in the seconds field triggers every 15 seconds starting from 0 (0, 15, 3,0 and 45).
* L represents the last day of the week or month. Remember that Saturday is the end of the week in this context, so using L in the day of week field will trigger on a Saturday. This can be used in conjunction with a number in the day of month field, such as 6L to represent the last Friday of the month or an expression like L-3 denoting the third from the last day of the month. If we specify a value in the day of week field, we must use ? in the day of month field, and vice versa.
* W represents the nearest weekday of the month. For example, 15W will trigger on the 15th day of the month if it is a weekday. Otherwise, it will run on the closest weekday. This value cannot be used in a list of day values.
* \# specifies both the day of the week and the week that the task should trigger. For example, 5#2 means the second Thursday of the month. If the day and week you specified overflows into the next month, then it will not trigger.

We can make use of Spring Expressions to externalize the configuration of the tasks – and store these in properties files:

`@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")`

The code sample here is a Maven project, so it should be easy to import and run as it is.

