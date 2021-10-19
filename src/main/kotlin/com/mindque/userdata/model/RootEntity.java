package com.mindque.userdata.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "root.userdata")
public class RootEntity {

    // ------  ------  ------  ------ Key Values ------  ------  ------  ------ \\

    @JsonIgnore
    private String pk;

    @JsonIgnore
    private String sk;

    @JsonIgnore
    private String tag;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "sk_by_tag")
    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return pk;
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return sk;
    }

    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "sk_by_tag")
    public String getTag() {
        return tag;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    // ------  ------  ------  ------ Utility ------  ------  ------  ------ \\

    @DynamoDBAttribute
    private String userId;

    @DynamoDBAttribute
    private String dataPointId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDataPointId() {
        return dataPointId;
    }

    public void setDataPointId(String dataPointId) {
        this.dataPointId = dataPointId;
    }

    // ------  ------  ------  ------ Userdata ------  ------  ------  ------ \\


    @DynamoDBAttribute
    @Setter
    @Getter
    private Float value;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String dataPointGenerationDate;

    @DynamoDBAttribute
    @Setter
    @Getter
    private int numOfSamples;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String sampleStartDate;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String sampleEndDate;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String dataType;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String samplingScheme;

    @DynamoDBAttribute
    @Setter
    @Getter
    private Float ninetyFiveInterval;

    @DynamoDBAttribute
    @Setter
    @Getter
    private Float ninetyNineInterval;

    @DynamoDBAttribute
    private List<String> tags;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String version;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}


