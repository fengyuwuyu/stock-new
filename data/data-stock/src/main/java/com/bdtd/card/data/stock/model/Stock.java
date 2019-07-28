package com.bdtd.card.data.stock.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.base.card.common.util.StringUtil;
/**
 * <p>
 * 
 * </p>
 *
 * @author
 * @since 2019-03-21
 */
@TableName("stock")
public class Stock extends Model<Stock> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("symbol")
    private String symbol;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("area")
    private String area;

    @TableField("industry")
    private String industry;

    @TableField("market")
    private String market;

	public Stock () {
		super();
	}
	
	public Stock (String symbol, String code, String name, String area, String industry, String market) {
		super();
		this.symbol = symbol;
		this.code = code;
		this.name = name;
		this.area = area;
		this.industry = industry;
		this.market = market;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
		StringBuilder sb = new StringBuilder("Stock { ");	
			sb.append("id: ").append(id).append(',').append(' ');
			sb.append("symbol: ").append(StringUtil.stringToString(symbol)).append(',').append(' ');
			sb.append("code: ").append(StringUtil.stringToString(code)).append(',').append(' ');
			sb.append("name: ").append(StringUtil.stringToString(name)).append(',').append(' ');
			sb.append("area: ").append(StringUtil.stringToString(area)).append(',').append(' ');
			sb.append("industry: ").append(StringUtil.stringToString(industry)).append(',').append(' ');
			sb.append("market: ").append(StringUtil.stringToString(market));
		sb.append('}'); 
        return sb.toString(); 
    }
}

/*
	String symbol = null;
	String code = null;
	String name = null;
	String area = null;
	String industry = null;
	String market = null;
*/
