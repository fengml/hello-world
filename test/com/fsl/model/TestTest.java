package com.fsl.model;

public class TestTest extends BaseBean 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 主键 [主键]
	private Long              id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
    //
    private String name;
    //
    private Integer number;
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setNumber(Integer number)
    {
        this.number=number;
    }
    public Integer getNumber()
    {
        return this.number;
    }

    //TODO
}

