package com.aoftion.sdk2.dynamodb.connect.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aoftion.sdk2.dynamodb.connect.entity.OrderEntity;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class OrderRepository {
    private static final String TABLE_NAME = "Order";

    @Autowired
    private DynamoDbEnhancedClient ddbEnhancedClient;

    public void save(final OrderEntity order) {
        DynamoDbTable<OrderEntity> orderTable = getTable();
        orderTable.putItem(order);
    }

    public OrderEntity getOrder(final String customerID, final String orderID) {
        DynamoDbTable<OrderEntity> orderTable = getTable();
        // Construct the key with partition and sort key
        Key key = Key.builder().partitionValue(customerID)
                .sortValue(orderID)
                .build();

        OrderEntity order = orderTable.getItem(key);

        return order;
    }

    private final DynamoDbTable<OrderEntity> getTable() {
        DynamoDbTable<OrderEntity> orderTable = ddbEnhancedClient.table(TABLE_NAME,
                TableSchema.fromBean(OrderEntity.class));
        return orderTable;
    }
}
