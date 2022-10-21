package org.ict.algorithm.leetcode.sql;

/**
 * SQL Schema
 * Table: Weather
 *
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | recordDate    | date    |
 * | temperature   | int     |
 * +---------------+---------+
 * id is the primary key for this table.
 * This table contains information about the temperature on a certain day.
 *
 *
 * Write an SQL query to find all dates' Id with higher temperatures compared to its previous dates (yesterday).
 *
 * Return the result table in any order.
 *
 * The query result format is in the following example.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * Weather table:
 * +----+------------+-------------+
 * | id | recordDate | temperature |
 * +----+------------+-------------+
 * | 1  | 2015-01-01 | 10          |
 * | 2  | 2015-01-02 | 25          |
 * | 3  | 2015-01-03 | 20          |
 * | 4  | 2015-01-04 | 30          |
 * +----+------------+-------------+
 * Output:
 * +----+
 * | id |
 * +----+
 * | 2  |
 * | 4  |
 * +----+
 * Explanation:
 * In 2015-01-02, the temperature was higher than the previous day (10 -> 25).
 * In 2015-01-04, the temperature was higher than the previous day (20 -> 30).
 * @author sniper
 * @date 21 Oct, 2022
 * LC197
 */
public class RisingTemperature {
    /**
     * select
     *  tod.id
     * from
     *  weather tod,
     *  weather yest
     * where
     *  tod.temperature > yest.temperature and
     *  datediff(tod.RecordDate, yest.RecordDate) = 1;
     */

    /**
     * SELECT w1.id AS 'id'
     * FROM
     * weather w1 INNER JOIN weather w2 ON DATEDIFF(w1.recordDate, w2.recordDate) = 1
     * AND w1.temperature > w2.temperature
     */
}
