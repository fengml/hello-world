package com.fsl.entity;

public class DictCode {
    private Integer id;
    private String dictName;
    private String itemCode;
    private String itemDesc;
    
    public DictCode(){
    }
    public DictCode(Integer id, String dictName, String itemCode, String itemDesc) {
        this.id = id;
        this.dictName = dictName;
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDictName() {
        return dictName;
    }
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public String getItemDesc() {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
    @Override
    public String toString() {
        return "DictCode [id=" + id + ", dictName=" + dictName + ", itemCode=" + itemCode + ", itemDesc=" + itemDesc + "]";
    }
    
    

}
