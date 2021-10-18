package com.mindque.userdata.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "sk_by_tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
    private String ninetyFiveInterval;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String ninetyNineInterval;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String tags;

    @DynamoDBAttribute
    @Setter
    @Getter
    private String version;

}


