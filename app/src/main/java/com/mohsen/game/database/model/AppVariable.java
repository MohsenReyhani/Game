
package com.mohsen.game.database.model;

public class AppVariable {

    private String mAppVariableId;
    private String mValue;
    private String mDomainId;



    public AppVariable(){}

    public AppVariable(String AppVariableId, String Value , String DomainId){
        setAppVariableId(AppVariableId);
        setValue(Value);
        setDomainId(DomainId);

    }

    public String getAppVariableId() {
        return mAppVariableId;
    }

    public void setAppVariableId(String mAppVariableId) {
        this.mAppVariableId = mAppVariableId;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String mAppVariableName) {
        this.mValue = mAppVariableName;
    }


    public String getDomainId() {
        return mDomainId;
    }

    public void setDomainId(String mDomainId) {
        this.mDomainId = mDomainId;
    }
}
