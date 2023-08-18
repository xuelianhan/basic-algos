package org.ict.algorithm.leetcode.coupang;

/**
 * Is dynamodb an ACID?
 *
 * Yes, DynamoDB is an ACID database. ACID stands for Atomicity, Consistency, Isolation, and Durability.
 *
 * Atomicity ensures that all or none of a transaction is committed. This means that if one part of a transaction fails, the entire transaction will be rolled back.
 * Consistency ensures that all reads will return the most up-to-date data. This means that if you read the same item twice, you will get the same value both times.
 * Isolation ensures that concurrent transactions do not interfere with each other. This means that if two transactions are trying to update the same item, only one of the transactions will be successful.
 * Durability ensures that data is not lost even if there is a system failure. This means that if DynamoDB crashes, your data will be restored when it comes back online.
 * DynamoDB achieves ACID compliance by using a combination of techniques, including:
 *
 * Consistent hashing: DynamoDB uses consistent hashing to distribute data across multiple nodes. This ensures that all reads and writes will always go to the same node, regardless of the number of nodes in the cluster.
 * Replication: DynamoDB replicates data across multiple nodes. This ensures that if one node fails, the data will still be available on other nodes.
 * Write-ahead logs: DynamoDB uses write-ahead logs to ensure that all writes are committed to disk before they are acknowledged to the client. This ensures that data is not lost if there is a system failure.
 * Overall, DynamoDB is a highly scalable and reliable database that provides strong ACID guarantees.
 * @author sniper
 * @date 17 Aug 2023
 */
public class IsDynamoDBSupportACID {

}
