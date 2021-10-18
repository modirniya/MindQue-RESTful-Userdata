package com.mindque.userdata.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBConfiguration {

    @Bean
    fun dynamoDBMapper(): DynamoDBMapper =
        DynamoDBMapper(buildAmazonDynamoDB())

    private fun buildAmazonDynamoDB(): AmazonDynamoDB? =
        AmazonDynamoDBClientBuilder
            .standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    /*"https://dynamodb.us-west-1.amazonaws.com",
                    "us-west-1"*/
                    "http://127.0.0.1:8000", "us-west-1"
                )
            ).withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(
                        "dose not matter", "dose not matter"
                    )
                )
            ).build()
}