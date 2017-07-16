package com.db.parser.where.expressions.comparison;

import java.util.List;

import com.db.parser.where.ValueType;
import com.db.parser.where.expressions.WhereField;
import com.db.parser.where.expressions.WhereValue;
import com.db.stucture.Value;

public class WhereEqualGroup extends ComparisonExpression{
	
	public WhereEqualGroup(){}
	
	public WhereEqualGroup(WhereField field, WhereValue val) {
		super(field, val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean check(List<Value> values) {
		for (Value value : values) { //values from the query
			if (value.field.name.equals(getField().getFieldName()))
//				if (value.field.type == getValue().getType().val) {
					if (getValue().getType() == ValueType.DOUBLE) {
						if ((Long) value.value == Double.parseDouble(getValue().getValue().toString()))
							return true;
					} else if (getValue().getType() == ValueType.STRING) {
						if (((String) value.value).equals((String) getValue().getValue()))
							return true;
					} else if (getValue().getType() == ValueType.LIST_NUM) {
						for(Object val : (List<Object>) getValue().getValue()){
							if(value.field.type == ValueType.DOUBLE.val){
								if(Double.parseDouble((String)val) == (Long)value.value)
									return true;
							}else if (value.field.type == ValueType.STRING.val){
								if (val.equals(((String) value.value)))
									return true;

							}
						}
					}
		}
		return false;
	}
}
